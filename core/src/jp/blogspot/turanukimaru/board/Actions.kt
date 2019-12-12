package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Action
import jp.blogspot.turanukimaru.fehs.GameInterface
import jp.blogspot.turanukimaru.playboard.BoardListener


/**
 * アニメーションが終了したことを自分に伝えるアクション.
 * dt0でアクションの順番が回ってきたのと同時に発動する
 * dt = 0f のデフォルト入れたらInstant Runエラー。コンストラクタを明示的に書く必要がある
 */
class EndOfAnimationAction(private val action: ActionListener, private var dt: Float, private val boardListener: GameInterface? = null) : Action() {

    override fun act(delta: Float): Boolean {
        dt -= delta
        if (dt <= 0) {
            action.actionDone()
            boardListener?.actionDone()
            return true
        }
        return false
    }

}

