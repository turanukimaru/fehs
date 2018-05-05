package jp.blogspot.turanukimaru.board

import java.util.*

class Hand<UNIT, GROUND> {
    fun clear() {
        selectedPiece = null
        oldPosition = null
        routeStack.clear()

    }

    /**
     * 現在盤上で選択されている駒
     */
    var selectedPiece: Piece<UNIT, GROUND>? = null
    /**
     * 駒を移動中に移動元の枡を記録しておく
     */
    var oldPosition: UiBoard.Position? = null
    /**
     * 駒を移動中に移動先の枡を記録しておく
     */
    var newPosition: UiBoard.Position? = null
    /**
     * 選択されている駒が動かされて移動が確定していないときの動かした道筋
     */
    val routeStack = ArrayDeque<UiBoard.Position>()

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