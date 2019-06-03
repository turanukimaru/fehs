package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Action


/**
 * アニメーションが終了したことを自分に伝えるアクション.
 * dt0でアクションの順番が回ってきたのと同時に発動する
 */
class EndOfAnimationAction(private val action: ActionListener, private var dt: Float) : Action() {

    override fun act(delta: Float): Boolean {
        dt -= delta
//        println("ACT : dt : $dt")
        if (dt <= 0) {
            action.uiActionDone()
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
