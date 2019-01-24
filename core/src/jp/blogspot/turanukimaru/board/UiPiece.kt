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
    val actors = mutableListOf<Actor>()


    /**
     * その駒に触れてる最中かどうか。ドラッグ中とPiece側から状態を取得するために使う
     */
    var touched = TouchPhase.NONE

    /**
     * アクション中か。アクション開始時にtrueにしたいな。無理か。
     */
    var actionNow = false

    var dx = 0.0f
    var dy = 0.0f

    /**
     * ドラッグを駒に伝える
     * ドラッグはクリックではない.x,yは移動量。//駒の動きを駒に書くのは妥当か//無効の時に動かさないのどうすっかなこれ
     */
    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
        super.touchDragged(event, x, y, pointer)
        //タッチのつもりでドラッグってやっぱりあるのかなぁ
        println("touchDragged($x, $y, $pointer)")
        if (!uiBoard.pieceActive) return
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
        touched = TouchPhase.DRAG
        dx += x
        dy += y
        val touchedSquare = stackTouchedRoute()
        piece.touchDragged(touchedSquare)
    }

    fun stackTouchedRoute(): UiBoard.Position {
        val touchedSquare = uiBoard.touchedPosition()
        stackRoute(touchedSquare)
        return touchedSquare
    }

    //対象の枡が通れるときはスタックに積む…とまれるときか？ともかくこれは駒側主導ではあるが人の操作ではないわけでなんか分けたいなあ。通れる・泊まれるを判断するから無理か。
    fun stackRoute(position: UiBoard.Position) {
        if (piece.searchedRoute[position.x][position.y] > 0) {
            uiBoard.board.hand.stackRoute(position)
        } else {
            uiBoard.board.hand.routeOut()
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
        update()
    }

    open fun update() {
    }


    //アクターのサイズが分からないから中心が出ない...けど画像の管理はまた別問題だな
    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun actionMoveToPosition(position: UiBoard.Position?): SequenceAction {
        val seq = SequenceAction()
        if (position == null) return seq
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY = uiBoard.squareYtoPosY(position.y)
        seq.addAction(Actions.moveBy(finalX - actor.x, finalY - actor.y, 0.1f))
        seq.addAction(EndOfAnimationAction(this, 0.1f))
        return seq
    }

    fun startAction(action: () -> SequenceAction) {
        if (actionNow) return
        actor.addAction(action())
        actionNow = true
    }

    fun noAction() = SequenceAction()
    /**
     * 位置移動直接。アクションをキャンセルして移動差分を駒に登録
     */
    fun actionSetToPosition(position: UiBoard.Position?) {
        if (position == null) return
        actor.clearActions()
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY = uiBoard.squareYtoPosY(position.y)
        actor.setPosition(finalX, finalY)
    }

    /**
     * EndOfAnimationActionから呼ばれるコールバック
     */
    fun actionTerminate() {
        println("actionTerminate")
//        touched = TouchPhase.NONE//RELEASE->NONEのはず
        actionNow = false
    }
}

enum class TouchPhase {
    NONE,//触ってない
    TOUCH,//触り始めた
    DRAG,//ドラッグ中
    RELEASE,//ドラッグ離した
}
