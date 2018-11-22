package jp.blogspot.turanukimaru.board

import java.util.*

/**
 * 駒の操作状態と移動経路。タッチ時間・ドラッグ判定もここ。
 */
class Hand<UNIT, GROUND>(val board: Board<UNIT, GROUND>) {
    var dx = 0
    var dy = 0
    var holdStart = 0L
    var holdNow = 0L
    var readyToAction = false
    var onRoute = false
    private val graspThreshold = 500
    /**
     * ドラッグ中か。一定値以上ドラッグかホールド時間で判定しとくか
     */
    fun dragging(x: Int = 0, y: Int = 0): Boolean {
        dx += x
        dy += y
        println("holdNow:$holdNow holdStart:$holdStart")
        println("dx:$dx dy:$dy")
        println(holdNow - holdStart > graspThreshold || dx > 24 || dx < -24 || dy > 24 || dy < -24)//clicklistnerの閾値は14
        return holdNow - holdStart > graspThreshold || dx > 24 || dx < -24 || dy > 24 || dy < -24
    }

    fun grasp(dx: Int = 0, dy: Int = 0): Boolean {
        val d = dragging(dx, dy)
        if (d) selectedPiece = touchedPiece//TODO:selectPiece
        return d
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
     * 選択されたかドラッグ中に対象になってる駒。移動範囲とかの表示対象※現物では選択されてるかとタッチされたかでは別扱い。タッチ開始直後は薄い。
     */
    val targetPiece get() = selectedPiece ?: touchedPiece
    /**
     * 駒を移動中に移動元の枡を記録しておく
     */
    var oldPosition: UiBoard.Position? = null
    /**
     * タッチ開始したときの位置
     */
    var touchedPosition: UiBoard.Position? = null
    /**
     * 駒を移動中に移動先の枡を記録しておく
     */
    var newPosition: UiBoard.Position? = null
    /**
     * 選択されている駒が動かされて移動が確定していないときの動かした道筋
     */
    val routeStack = ArrayDeque<UiBoard.Position>()

    //まとまんないな。ドラッグは別にするか。ここでは判定できないし
    private val handType
        get() = when {
            touchedPiece == null && selectedPiece == null -> HandType.N_TAP
            touchedPiece == null && selectedPiece != null -> HandType.FIELD_TAP
            touchedPiece != null && selectedPiece == null -> HandType.SIMPLE_TAP
            //↑ここまで盤面スタート↓キャラスタート
            selectedPiece != null && touchedPiece == selectedPiece -> HandType.SELECTED_TAP
            selectedPiece != null && touchedPiece != selectedPiece -> HandType.TARGET_TAP
            else -> HandType.NOP
        }

    fun clear() {
        selectedPiece = null
        oldPosition = null
        routeStack.clear()
    }

//    fun toggleSelect(piece: Piece<UNIT, GROUND>, xyToPosition: UiBoard.Position) {
//        if (selectedPiece == null) {
//            selectedPiece = piece
//            oldPosition = xyToPosition
//        } else {
//            clear()
//        }
//    }

    //離したときはタッチユニットをNullにする。キャラ選択が外れるわけではない
    private fun touchRelease() {
        touchedPiece = null
        dx = 0
        dy = 0
    }

    //コマを推し始めた場合は引き上げ時にクリックORドラッグ終了とする。推し始めデータだけ初期化して終了
    fun startPiece(piece: Piece<UNIT, GROUND>, xyToPosition: UiBoard.Position) {
        dx = 0
        dy = 0
        touchedPiece = piece
        touchedPosition = xyToPosition
        holdStart = System.currentTimeMillis()//Dateのほうがいいかなあ？こっちのが早いよなあ？
        board.searchRoute(piece)
        board.searchEffectiveRoute(piece)
    }

    override fun toString(): String = "dx:$dx dy:$dy p:$touchedPosition piece:$touchedPiece s:$selectedPiece"
    /**
     * クリック時の動作だけどtouchDown/touchUpが同じオブジェクトの時には常に起動するので画面全体を覆うときは実質touchUp
     * アルゴリズムはHand側へ移動したいな。そうすればOptionでHand入れ替えで済む。
     */
    fun clickedAction(position: UiBoard.Position, targetPiece: Piece<UNIT, GROUND>?, targetRoute: Int, targetEffective: Int) {
        //ドラッグ終了時には攻撃Or移動。対象の枡は枡の中心からの移動量で計算するべきか。
        if (dragging()) {
            //ユニットのタッチから始まってたらそこへアクションか移動。移動してないときは移動キャンセル
            if (targetPiece == touchedPiece) {
                moveCancel()
//                updateInfo = { _ -> true }
                return
            }
            when {
                //何かをドラッグしてないときは何もしない
                (touchedPiece == null) -> {
                }
                //対象が存在するときはそこへアクション
                (targetPiece != null && targetEffective >= 0) -> {
                    newPosition = position//一歩手前判定しないとな
                    touchedPiece?.boardActionCommit(this, position, targetPiece) ?: return
                }
                //何もないところへは移動。確定かどうかは設定による
                (targetPiece == null && targetRoute >= 0) -> {
                    newPosition = position
                    touchedPiece?.boardMove(this, position, targetPiece) ?: return
                    //効果範囲かつ対象がいないときは範囲外と同じ扱い.ただしFEHでは効果範囲内＆移動範囲外は単に無視する
                }
                //あーAssistは効果範囲が違うんだったな…Assistive追加しないとダメか
                else -> {
                    //移動後は移動後の場所へ戻す、移動前は移動キャンセル。これはめんどいな
                    moveCancel()
                }
            }

            //ドラッグでないときはそこへ移動・行動。底に何があるかは枡経由で見るべきだな
        } else {
            if ((targetPiece != null && selectedPiece != targetPiece && targetEffective >= 0) && readyToAction) {
                selectedPiece?.boardActionCommit(this, position, targetPiece)

            } else {
                //行動準備は基本False
                readyToAction = false
                when {//敵と味方で判定別にしないといけないんだよな辛い
//未選択の時は選択選択してないときに何もないところをタップしても何も起きない。
                    (selectedPiece == null) -> {
                        selectedPiece = targetPiece
                    }
//選択済み＆元の位置をクリックの時は移動またはキャンセル
                    (selectedPiece == targetPiece) -> {
                        moveCancel()
                    }
//選択済み＆移動済み＆自分駒をクリックしたときはそこへ移動確定。
                    (targetPiece == null && touchedPiece == selectedPiece) -> {
                        newPosition = position
                        selectedPiece?.boardMoveCommit(this, position, targetPiece)
                    }
//選択済み＆駒のあるところをクリックしたときは攻撃位置へ移動しそこへ行動。
                    (targetPiece != null && selectedPiece != targetPiece && targetEffective >= 0) -> {
                        readyToAction = true
                        val attackablePosition = board.findAttackPos(selectedPiece!!, position)
                        stackRoute(position)
                        selectedPiece?.boardMove(this, attackablePosition!!, targetPiece)
                        selectedPiece?.boardAction(this, position, targetPiece)
                    }
                    //選択済み＆動いてて相手がいない場合そこへ移動.ただしFEHでは効果範囲内＆移動範囲外は単に無視する
                    (targetPiece == null && targetRoute >= 0) -> {
                        stackRoute(position)
                        selectedPiece?.boardMove(this, position, targetPiece)
                    }
                    else -> {
                        moveCancel()
                    }
                }
            }
        }
        touchRelease()
    }

    /**
     * 選択した駒の移動をキャンセルして非選択にする。選択してる駒の状態も変化させる
     */
    fun moveCancel() {
        println("moveCancel")
        selectedPiece?.actionPhase = Piece.ActionPhase.READY
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
        if (onRoute) {
            //ただしスタックに有ったらそこまで戻す
            while (routeStack.contains(touchedPosition)) {
                routeStack.pop()
            }
        } else {
            //TODO:いずれルート探索。今は最終枡だけ保持しておくか
            routeStack.clear()
        }
        routeStack.push(touchedPosition)
        newPosition = touchedPosition
        onRoute = true
    }

    fun routeOut() {
        onRoute = false
    }

    private fun movePiece(position: UiBoard.Position) {
        println(" movePiece($position: UiBoard.Position) ")
        stackRoute(position)
        selectedPiece?.boardMove(this, position, targetPiece)

    }

    fun drop(position: UiBoard.Position) {
        println("touched $touchedPiece　selected $selectedPiece")
        //N_TAPと同じなのでここでアニメの省略とか
        val target = touchedPiece ?: return
        //自分の駒じゃないのをドラッグしても何もしない
        if (target.owner !=board.owner ) return
        when {
            //駒を選択して盤面をドロップってなんもせんわな
            handType == HandType.FIELD_TAP -> return//movePiece(position)
            //直接ドロップは移動してアクション準備.アクションフェイズかは分岐先で確認するべきか
            handType == HandType.SIMPLE_TAP -> movePiece(position)
            handType == HandType.SELECTED_TAP && target.actionPhase == Piece.ActionPhase.READY -> selectPiece(target)
            //選択してない駒をドロップしたときはアクションだけど敵と味方で効果範囲が違う…
            handType == HandType.TARGET_TAP && target.actionPhase == Piece.ActionPhase.READY -> selectPiece(target)
            else -> println("UNEXPECTED ACTION $handType ${target.actionPhase}")
        }
    }

    fun clicked(position: UiBoard.Position) {
        //条件をTypeのほうに移動していかなきゃならんな…敵と味方混ぜたほうがいいかなあ。敵をドラッグはだいたい意味ないからなあ
        //あとやっぱり先に状態を分けるべきだよなあ。選択済みかどうかは入れないほうがいいか…
        //一定時間以上たってのクリックはドラッグ判定
        if (dragging()) {
            drop(position)
        }
        println("touched $touchedPiece　selected $selectedPiece")
        //N_TAPと同じなのでここでアニメの省略とか
        val target = touchedPiece ?: return
        when {
            //駒を選択して盤面をタップ（駒をタップではない）したときには移動・アクション。範囲外だと未選択
            handType == HandType.FIELD_TAP -> movePiece(position)
            //選択した駒をタップしたときは動いてたらそこで終了、アクション準備なら取り消して移動中、 移動してなかったら解除
            handType == HandType.SELECTED_TAP && target.owner == board.owner && target.actionPhase == Piece.ActionPhase.READY -> selectPiece(target)
            //選択してない駒をタップしたときはアクションだけど敵と味方で効果範囲が違う…
            handType == HandType.TARGET_TAP && target.owner == board.owner && target.actionPhase == Piece.ActionPhase.READY -> selectPiece(target)
            handType == HandType.TARGET_TAP && target.owner != board.owner && target.actionPhase == Piece.ActionPhase.READY -> selectPiece(target)
            else -> println("UNEXPECTED ACTION $handType ${target.actionPhase}")
        }

    }

    /**
     * 駒を選択状態にする。掴むとかそういう名前のほうが良いか？
     */
    private fun selectPiece(piece: Piece<UNIT, GROUND>) {
        
        println("selectPiece $piece")
        clear()
        board.searchRoute(piece)
        board.searchEffectiveRoute(piece)
        selectedPiece = piece
        oldPosition = board.searchUnitPosition(piece)!!
    }


}


enum class HandType(val detail: String) {
    /** 自分クリック。行動確定 */
    SELECTED_TAP("自分クリック。行動確定"),
    /** ターゲットクリック */
    TARGET_TAP("ターゲットクリック"),
    /** 盤面クリック（移動やアクション） */
    FIELD_TAP("盤面クリック（移動やアクション）"),
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
enum class HandPhase(val clicked: (p: UiBoard.Position) -> HandPhase) {
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