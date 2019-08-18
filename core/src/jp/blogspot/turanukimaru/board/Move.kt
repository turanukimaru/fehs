package jp.blogspot.turanukimaru.board

import java.util.*

/**
 * 駒の操作状態と移動経路。タッチ時間・ドラッグ判定もここ。moveもうちょいでimmutableにできそうな気がする
 * ゲームにより挙動が変わるかもしんないなあ。
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
        println("toucheStart $piece")
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
        //最後の枡のままの時は何もしない
        if (routeStack.isNotEmpty() && routeStack.last == touchedPosition) {
            return
        }
        //ただしスタックに有ったらそこまで戻す
        while (routeStack.contains(touchedPosition)) {
            routeStack.pop()
        }
        routeStack.push(touchedPosition)
    }

    /**
     * ドラッグされたときに呼び出される
     */
    fun drag(position: UiBoard.Position, dx: Float, dy: Float) {
        println("board dragged on $position")
        touch?.let { it.touchedPiece?.touchDragged(it.touchedPosition, dx, dy) }
        drag(touch!!, position)
    }

    private fun drag(touch: Touch<UNIT, GROUND>, position: UiBoard.Position) {
        println("drag  $touch at $position")        //駒をドラッグしてるとき
        if (touch.drag(position, board)) {
            moving = Dragging(this, touch.touchedPiece!!, touch.touchedPiece!!.existsPosition!!, touch.touchedPosition, null, null)
            board.findActionRoute(position, Pair(0, 0), listOf(position), touch.touchedPosition, touch.touchedPiece!!)
        }
    }


    /**
     * 盤面タップ
     * 指を離したときに呼び出される。ドラッグもここになるのでここからdrop()を呼び出している
     */
    fun boardClicked(position: UiBoard.Position) {
        println("boardClicked $position")
        moving = moving.boardClick(position)
        touchRelease()
    }

    /**
     * オプションタップ
     * 成るとか成らないとかのオプションを使う予定。
     * イベントとして処理しているのでBoardへのTouchは走らないはず
     */
    fun optionClicked(listener: ActionListener) {
        println("optionClicked")
        moving = moving.optionClick()
    }

    /**
     * 駒タップ
     * 指を離したときに呼び出される。ドラッグもここになるのでここからdrop()を呼び出している
     */
    fun pieceClicked(position: UiBoard.Position, piece: Piece<UNIT, GROUND>) {
        println("pieceClick")
        //盤面タップ
        println("moving.pieceClick($position, $piece)")
        moving = moving.pieceClick(position, piece)
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
