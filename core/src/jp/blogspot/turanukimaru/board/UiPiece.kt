package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

/**
 * 駒とLibGDXの間
 * 駒をドラッグ中は駒を動かすことになるがそうすると離したときにクリック扱いになるのでonClickを使わないように
 */
open class UiPiece(val actor: Actor, val uiBoard: UiBoard,
                   /**
                    * 論理駒
                    */
                   open var piece: Piece<*, *>
) : ClickListener() {

    /**
     * その駒に触れてる最中かどうか。ドラッグ中とPiece側から状態を取得するために使う。がなくなりそう…
     */
    var touched = TouchPhase.NONE

    /**
     * ドラッグを駒に伝える
     * ドラッグはクリックではない.x,yは移動量。ただこれって枡と同じサイズじゃないと想定通りに動かないんだよな。ここにあるべきじゃないか…
     */
    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
        super.touchDragged(event, x, y, pointer)
        //タッチのつもりでドラッグってやっぱりあるのかなぁ
        println("touchDragged($x, $y, $pointer)")
        //盤面のチェックは有るべきか
        if (!uiBoard.pieceActive) {
            println("!uiBoard.pieceActive")
            return
        }
        //升目以外にドラッグした場合は無視
        if (!uiBoard.posIsOnBoard(Vector3(x, y, 0f))) {
            println("!uiBoard.posIsOnBoard")
            return
        }
        //ドラッグ判定に合わないときは無視
        if (!uiBoard.board.move.dragging(x.toInt(), y.toInt())) {
            println("!uiBoard.board.move.dragging")
            return
        }
        touched = TouchPhase.DRAG
        val touchedSquare = stackTouchedRoute()
        piece.touchDragged(touchedSquare, x, y)
    }

    private fun stackTouchedRoute(): UiBoard.Position {
        val touchedSquare = uiBoard.touchedPosition()
        stackRoute(touchedSquare)
        return touchedSquare
    }

    //対象の枡が通れるときはスタックに積む…とまれるときか？ともかくこれは駒側主導ではあるが人の操作ではないわけでなんか分けたいなあ。通れる・泊まれるを判断するから無理か。
    private fun stackRoute(position: UiBoard.Position) {
        if (piece.searchedRouteOf(position) > 0) {
            uiBoard.board.move.stackRoute(position)
        }
    }

    /**
     * 指を離したのを駒に伝える。んだったんだけどこれもういらないよなあ
     * x,yはきっとタッチ始めた位置からの差分
     */
    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        super.touchUp(event, x, y, pointer, button)
        if (!uiBoard.pieceActive) return
        actor.zIndex = 0
        touched = TouchPhase.RELEASE
        val position = uiBoard.touchedPosition()

        piece.touchUp(position)
    }

    /**
     * タッチしたのを駒に伝える
     */
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        val result = super.touchDown(event, x, y, pointer, button)
        println("touchDown($x, $y, $pointer)")
        //本当はユニットの情報を表示するとかいろいろある
        if (!uiBoard.pieceActive) return result
//駒に対する操作を始めたのを伝える
        piece.startPiece(uiBoard.xyToPosition(x, y))
        actor.zIndex = 0
        touched = TouchPhase.TOUCH
        piece.touchDown()
        return result
    }

    /**
     * LibGDXのアップデートで呼ばれる。
     */
    fun libUpdate() {
        if (piece.charPosition == null) {
            return // ボード上に無いときは何もしない。消す処理を書いてもいいか？
        }
        piece.libUpdate()//これどっちが先に動くべきなんだろうな…？
        update()
    }

    open fun update() {
    }
}

enum class TouchPhase {
    NONE,//触ってない
    TOUCH,//触り始めた
    DRAG,//ドラッグ中
    RELEASE,//ドラッグ離した
}
