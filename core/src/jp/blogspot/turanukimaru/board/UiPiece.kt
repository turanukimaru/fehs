package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import javax.swing.text.Position

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
     * 中に含むActorのリスト。アニメーションで体の部位を動かすのに使うのだがボーンモデル別に作るべき
     */
    val actors = arrayListOf<Actor>()


    /**
     * その駒に触れてる最中かどうか。Piece側から状態を取得するために使う
     */
    var touched = false

    /**
     * ドラッグを駒に伝える
     * ドラッグはクリックではない.x,yは移動量。//駒の動きを駒に書くのは妥当か//無効の時に動かさないのどうすっかなこれ
     */
    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
        super.touchDragged(event, x, y, pointer)
        if(!uiBoard.pieceActive) return
//ドラッグしたら駒の表示を追従させているが移動量検出しないとちゃたるなこれ
        if (!piece.isActionable) {
            return
        }
        //升目以外にドラッグした場合は無視
        if (!uiBoard.posIsOnBoard(Vector3(x, y, 0f))) {
            return
        }
        //ドラッグ判定に合わないときは無視
        if (!uiBoard.board.hand.dragging(x.toInt(), y.toInt())) {
            return
        }
        //ドラッグ中は駒は指に追随。Updateでは何もしない
        actor.setPosition(actor.x + x, actor.y + y)
        piece.actionPhase = Piece.ActionPhase.DRAGGING
        //ルートをスタックする
        val touchedSquare = uiBoard.stackTouchedRoute()
        piece.touchDragged(touchedSquare)
    }

    /**
     * 指を話したのを駒に伝える。んだったんだけどこれもういらないよなあ
     * x,yはきっとタッチ始めた位置からの差分
     */
    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        super.touchUp(event, x, y, pointer, button)
        if(!uiBoard.pieceActive) return
        actor.zIndex = 0
        touched = false
        val position = uiBoard.touchedPosition()

        piece.touchUp(position)
    }

    /**
     * タッチしたのを駒に伝える
     */
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        val result = super.touchDown(event, x, y, pointer, button)
        if(!uiBoard.pieceActive) return result
        piece.startPiece(uiBoard.xyToPosition(x,y))
        actor.zIndex = 0
        touched = true
        piece.touchDown()
        return result
    }

    /**
     * LibGDXのアップデートで呼ばれる。localと名前を逆にすべきだなこれ
     */
    fun libUpdate() {
        if (piece.position == null) {
            return // ボード上に無いときは何もしない。消す処理を書いてもいいか？
        }
        update()
    }

    open fun update() {

    }


    //TODO:アクターのサイズが分からないから中心が出ない...
    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun actionMoveToPosition(position: UiBoard.Position?): SequenceAction {
        val seq = SequenceAction()
        if(position == null) return seq
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY =uiBoard. squareYtoPosY(position.y)
        seq.addAction(Actions.moveBy(finalX - actor.x, finalY - actor.y, 0.2f))
//        seq.addAction(Actions.moveTo(finalX, finalY))
        return seq
    }

    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun setToPosition(position: UiBoard.Position?) {
        if(position == null) return
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY = uiBoard.squareYtoPosY(position.y)
        actor.setPosition(finalX, finalY)
    }
}