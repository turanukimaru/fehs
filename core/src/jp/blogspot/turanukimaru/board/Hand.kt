package jp.blogspot.turanukimaru.board

import java.util.*

/**
 * 駒の操作状態と移動経路。タッチ時間・ドラッグ判定もここ。
 */
class Hand<UNIT, GROUND> {
    var dx = 0
    var dy = 0
    var holdStart = 0L
    var holdNow = 0L
    val select get() = selectedPiece != null
    /**
     * ドラッグ中か。一定値以上ドラッグかホールド時間で判定しとくか
     */
    fun dragging(dx: Int = 0, dy: Int = 0): Boolean {
        this.dx += dx
        this.dy += dy
        return holdNow - holdStart > 500 || dx > 24 || dx < -24 || dy > 24 || dy < -24
    }

    /**
     * タッチ開始したときの駒。タッチ開始とUp開始が同じ駒の時でないとタッチ判定しないほうが良いだろう
     */
    var touchDownPiece: Piece<*, *>? = null
    /**
     * 現在盤上で選択されている駒
     */
    var selectedPiece: Piece<*, *>? = null
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

    fun clear() {
        selectedPiece = null
        oldPosition = null
        dx = 0
        dy = 0
        routeStack.clear()
    }

    //フィールドから押し始めた場合は何にも反応させない
    fun startField() {
        clear()
    }

    //コマを推し始めた場合は引き上げ時にクリックORドラッグ終了とする。推し始めデータだけ初期化して終了
    fun startPiece(piece: Piece<*, *>) {
        dx = 0
        dy = 0
        touchDownPiece = piece
        touchedPosition
        holdStart = System.currentTimeMillis()//Dateのほうがいいかなあ？こっちのが早いよなあ？
    }

//    fun releasePiece(piece:Piece<*, *>){
//
//    }
//    fun dragg
    /**
     * 駒の移動中に、移動経路を記録する
     */
    fun stackRoute(touchedSquare: UiBoard.Position) {
        println("stackRoute $touchedSquare")
        if (routeStack.isEmpty() || routeStack.last != touchedSquare) {
            //ただしスタックに有ったらそこまで戻す
            while (routeStack.contains(touchedSquare)) {
                routeStack.pop()
            }
            routeStack.push(touchedSquare)
        }
    }

}