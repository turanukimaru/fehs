package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
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
     * 中に含むActorのリスト。アニメーションで体の部位を動かすのに使うのだがボーンモデル別に作るべき
     */
    val actors = arrayListOf<Actor>()


    /**
     * その駒に触れてる最中かどうか。Piece側から状態を取得するために使う
     */
    var touched = false

    /**
     * 使用不可能フラグ。TODO:盤側から操作を伝えるかどうかに使う予定。
     */
    var disabled = false

    /**
     * ドラッグを駒に伝える
     * ドラッグはクリックではない.x,yは移動量。//駒の動きを駒に書くのは妥当か//無効の時に動かさないのどうすっかなこれ
     */
    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
        super.touchDragged(event, x, y, pointer)
//ドラッグしたら駒の表示を追従させているが移動量検出しないとちゃたるなこれ
        if (!piece.isActionable()) {
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
     * 指を話したのを駒に伝える
     * x,yはきっとタッチ始めた位置からの差分
     */
    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        super.touchUp(event, x, y, pointer, button)
        touched = false
        val position = uiBoard.touchedPosition()

        piece.touchUp(position)
    }

    /**
     * タッチしたのを駒に伝える
     */
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        val result = super.touchDown(event, x, y, pointer, button)
        uiBoard.board.hand.startPiece(piece)
//        actor.zIndex = 0
        touched = true
        piece.touchDown()
        return result
    }

    /**
     * LibGDXのアップデートで呼ばれる。localと名前を逆にすべきだなこれ
     */
    fun update() {
        if (!piece.isOnBoard) {
            return // ボード上に無いときは何もしない。消す処理を書いてもいいか？
        }
        localUpdate()
    }

    open fun localUpdate() {
        if (piece.actionPhase == Piece.ActionPhase.DISABLED) {
            if (piece.animationCount == 0) {
                actor.setPosition(uiBoard.squareXtoPosX(piece.x ?: 0), uiBoard.squareYtoPosY(piece.y ?: 0))
                uiBoard.stage.addActor(actor)
            }

        }

    }


    //TODO:アクターのサイズが分からないから中心が出ない...
    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun actionMoveToPosition(x: Int, y: Int): SequenceAction {
        val seq = SequenceAction()
        val finalX = uiBoard.squareXtoPosX(x)
        val finalY =uiBoard. squareYtoPosY(y)
        seq.addAction(Actions.moveBy(finalX - actor.x, finalY - actor.y, 0.2f))
//        seq.addAction(Actions.moveTo(finalX, finalY))
        return seq
    }

    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun setToPosition(x: Int, y: Int) {
        val finalX = uiBoard.squareXtoPosX(x)
        val finalY = uiBoard.squareYtoPosY(y)
        actor.setPosition(finalX, finalY)
    }
}