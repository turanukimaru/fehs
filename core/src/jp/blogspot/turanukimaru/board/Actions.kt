package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Action


/**
 * アニメーションが終了したことを自分に伝えるアクション.汎用を使うので十分な気がしてきた
 */
class EndOfAnimationAction(private val uiPiece: UiPiece) : Action() {

    override fun act(delta: Float): Boolean {
        uiPiece.actionTerminate()
        return true
    }

}

/**
 * 汎用コールバックアクション
 */
class CallbackAction(val f: () -> Boolean) : Action() {

    override fun act(delta: Float): Boolean {
        f()
        return true
    }

}
