package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Action

/**
 * 汎用コールバックアクション。とりあえず作ったはいいけど使ってないな…消すか…
 */
class CallbackAction(val terminateAction: () -> Boolean) : Action() {
    override fun act(delta: Float): Boolean {
        terminateAction()
        return true
    }

}
