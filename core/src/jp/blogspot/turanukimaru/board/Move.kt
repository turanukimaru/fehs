package jp.blogspot.turanukimaru.board

import java.util.*

/**
 * 駒の操作状態と移動経路。タッチ時間・ドラッグ判定もここ。moveもうちょいでimmutableにできそうな気がする
 */
class Move<UNIT, GROUND>(val board: Board<UNIT, GROUND>) {

    var touch: Touch<UNIT, GROUND>? = null//タッチしてなけりゃそりゃNullだよなあ

    var moving: Moving<UNIT, GROUND> = NoMove(this)
    /**
     * 選択されている駒が動かされて移動が確定していないときの動かした道筋
     */
    val routeStack = ArrayDeque<UiBoard.Position>()

    /**
     * 離したときはタッチユニットをNullにする。キャラ選択が外れるわけではない
     */
    private fun touchRelease() {
        touch = null
    }

    //コマを推し始めた場合は引き上げ時にクリックORドラッグ終了とする。推し始めデータだけ初期化して終了※ここでルートのサーチなどをすると、攻撃の対象にしたときなどにもサーチが走ってしまう
    fun toucheStart(piece: Piece<UNIT, GROUND>, position: UiBoard.Position) {
        touch = Touch(piece, position, System.currentTimeMillis())
    }

    override fun toString(): String = "p:${moving.selectedPiece} ${moving.from} to ${moving.to}"

    /**
     * 選択した駒の移動をキャンセルして非選択にする。盤上ではすでに動いてる扱いなので元の位置に戻している。選択してる駒の状態も変化させる
     */
    fun moveCancel() {
        moving = moving.moveCancel()
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
    }

    /**
     * ドラッグされたときに呼び出される
     */
    fun drag(position: UiBoard.Position) {
        println("board dragged on $position")
        touch?.dragged = true
        //ドラッグは動かせるコマでのみ有効
        if (touch?.touchedPiece?.isActionable ?: false && touch?.touchedPiece?.owner == board.owner) {
            moving = Dragging(this, touch!!.touchedPiece!!, touch!!.touchedPosition!!, touch!!.touchedPosition!!)
            //TODO:ここでスタックルートに乗せるべきか
        }
    }


    /**
     * 指を離したときに呼び出される。ドラッグもここになるのでここからdrop()を呼び出している
     */
    fun clicked(position: UiBoard.Position) {
        //盤面タップ
        moving = if (touch!!.hasPiece) {
            moving.pieceClick(position, touch!!.touchedPiece!!)
        } else {
            moving.boardClick(position)
        }
        touchRelease()
    }

    //タッチ開始時にboardから呼ばれる。今までのタッチとかはガン無視。リセット処理いるかな？要らないか。対象に攻撃するときとかはSelectedPieceが必要になるし
    fun touch(position: UiBoard.Position, touchedPiece: Piece<UNIT, GROUND>?) {
        println("touch x:${position.x}, y:${position.y} Piece $touchedPiece")//あれ？touch二つあるような？
        touch = Touch(touchedPiece, position, System.currentTimeMillis())
    }

    fun touchUp() {
// click が用をなすからこれ要らんな…
    }

    fun clear() {
        moving = NoMove(this)
    }

    fun moveCommit() {
        moving = moving.moveCommit()
    }

}
