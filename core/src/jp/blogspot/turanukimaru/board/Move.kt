package jp.blogspot.turanukimaru.board

import java.util.*

/**
 * 駒の操作状態と移動経路。タッチ時間・ドラッグ判定もここ。moveもうちょいでimmutableにできそうな気がする
 */
class Move<UNIT, GROUND>(val board: Board<UNIT, GROUND>) {
    //ドラッグ判定用データ
    var dx = 0
    var dy = 0
    var holdStart = 0L
    private val graspThreshold = 500
    /**
     * ドラッグ中か。一定値以上ドラッグかホールド時間で判定しとくか
     */
    fun dragging(x: Int = 0, y: Int = 0): Boolean {
        dx += x
        dy += y
        return System.currentTimeMillis() - holdStart > graspThreshold || dx > 14 || dx < -14 || dy > 14 || dy < -14//clickListenerの閾値は14
    }

    /**
     * タッチ開始したときの駒。タッチ開始とUp開始が同じ駒の時でないとタッチ判定しないほうが良いだろう
     */
    var touchedPiece: Piece<UNIT, GROUND>? = null
    /**
     * 現在盤上で選択されている駒
     */
    var selectedPiece: Piece<UNIT, GROUND>? = null
    /**
     * 駒を移動中に移動元の枡を記録しておく
     */
    var oldPosition: UiBoard.Position? = null
    /**
     * タッチ開始したときの位置
     */
    private var touchedPosition: UiBoard.Position? = null
    /**
     * 駒を移動中に移動先の枡を記録しておく
     */
    var newPosition: UiBoard.Position? = null
    /**
     * アクション先。駒に持たせてもいいのだが多分こっちでいいはず。
     */
    var actionTargetPiece: Piece<UNIT, GROUND>? = null
    /**
     * アクション先。駒に持たせてもいいのだが多分こっちでいいはず。
     */
    var actionTargetPos: UiBoard.Position? = null
    /**
     * 選択されている駒が動かされて移動が確定していないときの動かした道筋
     */
    val routeStack = ArrayDeque<UiBoard.Position>()

    /**
     * タップやドラッグの時にどこから始めたかで動作が変わるのでその判定
     */
    private val tapType
        get() = when {
            touchedPiece == null && selectedPiece == null -> TapType.N_TAP
            touchedPiece == null && selectedPiece != null -> TapType.SELECTED_FIELD_TAP
            //↑ここまで盤面スタート↓キャラスタート
            touchedPiece != null && selectedPiece == null -> TapType.SIMPLE_TAP
            selectedPiece != null && touchedPiece == selectedPiece -> TapType.SELECTED_SELF_TAP
            selectedPiece != null && touchedPiece != selectedPiece -> TapType.SELECTED_TARGET_TAP
            else -> TapType.NOP
        }

    /**
     * 保持している駒の動かし方をクリアする
     */
    fun clear() {
        selectedPiece = null
        oldPosition = null
        newPosition = null
        actionTargetPiece = null
        actionTargetPos = null
        routeStack.clear()
    }

    /**
     * 離したときはタッチユニットをNullにする。キャラ選択が外れるわけではない
     */
    private fun touchRelease() {
        touchedPiece = null
        dx = 0
        dy = 0
    }

    //コマを推し始めた場合は引き上げ時にクリックORドラッグ終了とする。推し始めデータだけ初期化して終了※ここでルートのサーチなどをすると、攻撃の対象にしたときなどにもサーチが走ってしまう
    fun toucheStart(piece: Piece<UNIT, GROUND>, xyToPosition: UiBoard.Position) {
        dx = 0
        dy = 0
        holdStart = System.currentTimeMillis()
        touchedPiece = piece
        touchedPosition = xyToPosition
    }

    override fun toString(): String = "dx:$dx dy:$dy p:$touchedPosition piece:$touchedPiece s:$selectedPiece"

    /**
     * 選択した駒の移動をキャンセルして非選択にする。盤上ではすでに動いてる扱いなので元の位置に戻している。選択してる駒の状態も変化させる
     */
    fun moveCancel() {
        println("moveCancel $selectedPiece")
        if (selectedPiece != null && oldPosition != null) board.moveToPosition(selectedPiece!!, oldPosition!!)
        selectedPiece?.moveCancel()
        clear()
    }

    /**
     * 駒の移動中に、移動経路を記録する
     */
    fun stackRoute(touchedPosition: UiBoard.Position) {
        println("stackRoute $touchedPosition")
        //最後の枡のままの時は何もしない
        if (routeStack.isNotEmpty() && routeStack.last == touchedPosition) {
            return
        }
//        if (onRoute) {//TODO:動線が変わったら一度クリアしたいんだけどアルゴリズムが思い浮かばない
//            routeStack.clear()
//        }
        //ただしスタックに有ったらそこまで戻す
        while (routeStack.contains(touchedPosition)) {
            routeStack.pop()
        }
        routeStack.push(touchedPosition)
        newPosition = touchedPosition
    }

    /**
     * 選択済みの駒を動かす。
     */
    private fun moveSelectedPiece(position: UiBoard.Position): Boolean {
        println(" moveSelectedPiece($position: UiBoard.Position) $selectedPiece")
        val movable = selectedPiece!!.boardMove(this, position)
        if (movable) {
            newPosition = position
            board.moveToPosition(selectedPiece!!, position)
            stackRoute(position)
        } else moveCancel()
        return movable
    }

    /**
     * ドラッグされたときに呼び出される
     */
    fun drag(position: UiBoard.Position) {
        println("board dragged on $position")
        //ドラッグは動かせるコマでのみ有効
        if (touchedPiece != null && touchedPosition != null && touchedPiece!!.isActionable && touchedPiece!!.owner == board.owner) {
            selectPiece(touchedPiece!!, touchedPosition!!)
            //TODO:ここでスタックルートに乗せるべきか
        }
    }

    /**
     * ドラッグ終了時に呼び出される
     */
    fun drop(position: UiBoard.Position) {
        println("drop touched $touchedPiece　select $selectedPiece")
        val piece = touchedPiece ?: return        //盤面をドラッグしても何も起きない。N_TAPと同じなのでここでアニメの省略とか
        if (!selectPiece(piece, position)) return //選択できなかったら終わり
        if (piece.owner != board.owner) return        //自分の駒じゃないのをドラッグしても何もしない
        val target = board.piece(position)
        val effective = selectedPiece!!.effectiveRouteOf(position)
        val movable = selectedPiece!!.searchedRouteOf(position)
        //TapType は常に SELECTED_SELF_TAP だ…これ定数化する意味なかったなあ
        println("target $target, movable : $movable, effective $effective")
        when {
            target == piece -> moveSelectedPiece(position)//動かそうとしてやめたときは枠に合わせる
            piece.existsPosition == position -> moveCancel()//動いてないときはこれで十分かな？
            target == null && movable < 1 -> moveCancel()//範囲外へ移動
            target == null && movable > 0 -> moveSelectedPiece(position)//範囲内に移動
            target != null && effective < 1 -> moveCancel()//攻撃範囲外へ攻撃
            target != null && effective > 0 -> {//攻撃範囲内へ攻撃
                actionPiece(target, position);actionCommit(target, position)
            }
            else -> println("UNEXPECTED drop ACTION $tapType ${piece.actionPhase}")
        }
    }

    /**
     * 指を離したときに呼び出される。ドラッグもここになるのでここからdrop()を呼び出している
     */
    fun clicked(position: UiBoard.Position) {
        //一定時間以上たってのクリックはドラッグ判定
        if (dragging()) {
            drop(position)
            touchRelease()
            return
        }
        //盤面タップ
        println("touched $touchedPiece　select $selectedPiece")
        println("tapType $tapType")
        // タップ
        println("touchedPiece $touchedPiece, ${touchedPiece!!.actionPhase}")
        when (tapType) {
            TapType.N_TAP->moveSelectedPiece(position)        //単に盤面をタップしたときはアニメキャンセルとか。未実装
            //駒を選択して盤面をタップ（駒をタップではない）したときには移動。範囲外だと未選択
            TapType.SELECTED_FIELD_TAP->moveSelectedPiece(position)//確定はしてないよ！。そもそも範囲外でキャンセルされてるかもしれないよ！
            //駒をタップ
            TapType.SIMPLE_TAP -> selectPiece(touchedPiece!!, position)
            //選択した駒をタップしたときは動いてたらそこで終了、アクション準備なら取り消して移動中、 移動してなかったら解除
            TapType.SELECTED_SELF_TAP -> {//when 入れ子になりそう…
                if (newPosition == oldPosition) {
                    moveCancel()
                } else {
                    //アクション準備時は実行、そうでないなら移動実行
                    if (this.actionTargetPiece != null && actionTargetPos != null) {
                        println("action commit")
                        actionCommit(this.actionTargetPiece!!, this.actionTargetPos!!)
                        clear()
                    } else {
                        println("move commit")
                        touchedPiece!!.boardMoveCommit(this, position)
                        clear()
                    }
                }
            }
            TapType.SELECTED_TARGET_TAP -> actionPiece(touchedPiece!!, position)
            else -> println("UNEXPECTED clicked ACTION $tapType ${touchedPiece!!.actionPhase}")
        }
        touchRelease()
    }

    /**
     * 行動準備/準備後同じ位置をクリックしたときは実行
     */
    private fun actionPiece(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position): Boolean {
        println("actionPiece $this to $targetPiece ")
        if (targetPiece.owner == board.owner) return false //アシストはそれはそれで大変そうだ…
        val targetEffective = selectedPiece!!.effectiveRouteOf(targetPos)
        if (targetEffective < 0) return false
        if (this.actionTargetPos == targetPos) {
            actionCommit(targetPiece, targetPos)
            //ここに後処理がいるとしてとりあえず動くところまで見よう
            return true
        }
        val attackablePosition = board.findActionPos(selectedPiece!!, targetPos, oldPosition)
//            stackRoute(targetPos)
        //攻撃可能位置が見つからなかったらどうするか…あと途中全部埋めたい
        if (attackablePosition != null && attackablePosition != targetPos) stackRoute(attackablePosition)
        moveSelectedPiece(attackablePosition!!)
        selectedPiece?.boardAction(this, targetPos, targetPiece)//攻撃準備
//この3行はひとまとめにすべきだよな…
        this.actionTargetPos = targetPos
        this.actionTargetPiece = targetPiece
        return true
    }

    /**
     * 行動実行
     */
    private fun actionCommit(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position): Boolean {
        selectedPiece?.boardActionCommit(this, targetPos, targetPiece)
        selectedPiece?.boardMoveCommit(this)
        board.deselectPiece()
        return true
    }

    /**
     * 駒を選択状態にする
     */
    private fun selectPiece(piece: Piece<UNIT, GROUND>, position: UiBoard.Position): Boolean {
        if (piece.owner != board.owner || (piece.actionPhase != Piece.ActionPhase.MOVING && piece.actionPhase != Piece.ActionPhase.READY)) return false
        println("selectPiece $selectedPiece  ")
        println("new selectPiece $piece")
        println("equal? ${selectedPiece == piece}")
        if (selectedPiece == piece) return true

        clear()
        selectedPiece = piece
        //↓二つは常にセットで存在するべき。クラスにするか？
        oldPosition = position
        newPosition = position//nullのが良い気もするが…
        piece.select()
        return true
    }

    //タッチ開始時にboardから呼ばれる。今までのタッチとかはガン無視。リセット処理いるかな？要らないか。対象に攻撃するときとかはSelectedPieceが必要になるし
    fun touch(position: UiBoard.Position) {
        //選択中は移動先で判定。これ盤面を新しい場所にしたほうがいいかっていうか旧盤面と新盤面の二つがあるべきなのかなあ
        touchedPiece = when {
            selectedPiece != null && position == newPosition -> selectedPiece
            selectedPiece != null && position == oldPosition -> null
            else -> board.piece(position)
        }
        println("touch x:${position.x}, y:${position.y} Piece $touchedPiece")//マトリックスから取得するの失敗してそうだな…
        holdStart = System.currentTimeMillis()//Dateのほうがいいかなあ？こっちのが早いよなあ？
        touchedPosition = position
    }

    fun touchUp() {
        touchedPiece = null
    }


}

enum class TapType(private val detail: String) {
    /** 自分クリック。行動確定 */
    SELECTED_SELF_TAP("自分クリック。行動確定"),
    /** ターゲットクリック */
    SELECTED_TARGET_TAP("ターゲットクリック"),
    /** 盤面クリック（移動やアクション） */
    SELECTED_FIELD_TAP("盤面クリック（移動やアクション）"),
    /** 選んでない状態からのタップ。直タップや直ドラッグ */
    SIMPLE_TAP("選んでない状態からのタップ。直タップや直ドラッグ"),
    /** 盤面クリック（駒を選択してないがアニメの省略とかに使うはず） */
    N_TAP("盤面クリック（駒を選択してないがアニメの省略とかに使うはず）"),
    /** 駒と関係なく盤面でドラッグなので無視 */
    NOP("駒と関係なく盤面でドラッグなので無視")
    ;

    override fun toString(): String {
        return "$name : $detail"
    }

}

//素直にOverrideで書いてもいいけど関数持ってるだけだけで自分をレシーバにする意味ないな…
enum class MovePhase(val clicked: (p: UiBoard.Position) -> MovePhase) {
    //なにも選択してない状態。全てが終わったらここへ戻る。ドラッグ開始後もドラッグ判定まではこれ。SELECTED-ANIMATIONは全部選択状態か。なんか定義できそうだな。
    NONE({ p -> NONE }),
    //選択状態
    SELECTED({ p -> /* TODO:選択状態解除 */NONE }),
    //移動したけど行動が確定してない
    MOVED({ p -> /* TODO:移動確定 */NONE }),
    //攻撃・補助などのアクション前。ドラッグ即実行の時はここには入らない
    READY_ACTION({ p -> /* TODO:移動確定 */MOVED }),
    //攻撃・補助などのアクション中。タップされたらアクションを完了してNONE
    ANIMATION({ p -> /* TODO:移動確定 */MOVED }),

    ;

    fun clicked() {

    }
}