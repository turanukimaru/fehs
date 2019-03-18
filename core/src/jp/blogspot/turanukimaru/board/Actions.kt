package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Action


/**
 * アニメーションが終了したことを自分に伝えるアクション.汎用を使うので十分な気がしてきた
 */
class EndOfAnimationAction(private val uiPiece: UiPiece, private var dt: Float) : Action() {

    override fun act(delta: Float): Boolean {
        dt -= delta
        println("ACT : dt : $dt")
        if (dt <= 0) {
            uiPiece.uiActionDone()
            return true
        }
        return false
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
