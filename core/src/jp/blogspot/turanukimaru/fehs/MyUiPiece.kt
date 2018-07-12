package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.board.Piece
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.board.UiPiece

class MyUiPiece(actor: Actor, uiBoard: UiBoard, var myPiece: MyPiece) : UiPiece(actor, uiBoard, myPiece) {

    //これMy側じゃない気がしてきた
    override fun update() {
        //アニメーション中かどうかは別の基準がいるかなあ。ループ中でも位置の移動は有るしなあ
        if (piece.animationCount > 0) return

        if (uiBoard.board.hand.touchedPiece == myPiece && uiBoard.board.hand.dragging()) {
            actor.clearActions()
            return
        }
        //アクションフェイズよりもここにドラッグ判定があるべきか
        when (piece.actionPhase) {
            Piece.ActionPhase.PUTTED -> {//おかれた直後なので初期化してDisabledに
                actor.setPosition(uiBoard.squareXtoPosX(piece.position!!.x), uiBoard.squareYtoPosY(piece.position!!.y))
                uiBoard.stage.addActor(actor)
                piece.action(Piece.ActionPhase.DISABLED)
            }
            Piece.ActionPhase.DISABLED ->//ターンが違うなど操作不能の時は枡に合わせてセット
                actor.setPosition(uiBoard.squareXtoPosX(piece.position!!.x), uiBoard.squareYtoPosY(piece.position!!.y))
            Piece.ActionPhase.READY -> {//現在位置に表示。ずれてる場合は徐々に移動させる
                actor.addAction(actionMoveToPosition(piece.position))
                //TODO:ループアクションを設定する
//                if (actors.size < 2) return
//                val base = actors[0]
//                val face = actors[1]
//                if (piece.animationCount == 8) {
//                    base.y = base.y + 2
//                    face.x = face.x + 2
//                } else if (piece.animationCount == 15) {
//                    base.y = base.y - 2
//                    face.x = face.x - 2
//                }
//                piece.animationCount = if (piece.animationCount < 16) {
//                    piece.animationCount + 1
//                } else {
//                    0
//                }

            }

            Piece.ActionPhase.MOVED -> {//移動予定個所に表示。ずれてる場合は徐々に移動させる
                actionMoveToPosition(uiBoard.board.hand.newPosition)
            }
//            Piece.ActionPhase.DRAGGING -> {//ドラッグ中は指に追随する。駒に書くかここに書くかは難しいな
//            }
            Piece.ActionPhase.ATTACK -> {//戦闘中のアクションはそのうち考える
            }
            Piece.ActionPhase.ACTED -> {
                //戦闘結果を持ってるときはそれを動かす
                if (myPiece.fightResult != null) {
                    attackResultToSeq(
                            myPiece.fightResult!!
                            , true)
                    myPiece.fightResult = null
                } else {
                    //アニメーション中でなければ灰色にする
                    actionSetToPosition(piece.position)
                    actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f) }
                }
            }
            Piece.ActionPhase.REMOVED -> {//画面から消す
            }
            else -> {//行動後の現在位置に表示。ずれてる場合は直接移動させる
                actionSetToPosition(piece.position)
                actors.forEach { a -> a.setColor(1f, 1f, 1f, 1f) }
            }

        }
        //カウントを一つ進める。開始かのBooleanのがいいかなあ
        piece.animationCount++
    }

    private fun attackResultToSeq(fightResult: FightResult, isAttacker: Boolean) {
        var attackCount = 0
        val sourceSeq = SequenceAction()
        val targetSeq = SequenceAction()
        for (result in myPiece.fightResult!!.attackResults) {
            println(result)
            //ダメージを与える側が攻撃側の時
            if (result.side == SIDES.ATTACKER) {
                sourceSeq.addAction(attackAction(fightResult.attackPos.x, fightResult.attackPos.y, fightResult.targetPos.x, fightResult.targetPos.y))
                targetSeq.addAction(Actions.delay(0.4f))
                uiBoard.showNumbers(fightResult.targetPos.x, fightResult.targetPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
            } else {
                targetSeq.addAction(attackAction(fightResult.targetPos.x, fightResult.targetPos.y, fightResult.attackPos.x, fightResult.attackPos.y))
                sourceSeq.addAction(Actions.delay(0.4f))
                uiBoard.showNumbers(fightResult.attackPos.x, fightResult.attackPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
            }
            if (result.source.hp == 0) {
                sourceSeq.addAction(Actions.fadeOut(1f))
            }
            if (result.target.hp == 0) {
                targetSeq.addAction(Actions.fadeOut(1f))
            }
        }
        sourceSeq.addAction(CallbackAction { true })
        sourceSeq.addAction(EndOfAnimationAction(this))
        if (isAttacker) sourceSeq.addAction(EndOfAnimationAction(this)) else targetSeq.addAction(EndOfAnimationAction(this))

        //行動終了
        actor.addAction(sourceSeq)
//    fightResult.attackResults.last().target.uiPiece!!.actor.addAction(targetSeq)

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

    var nowAnimation = false

    /**
     * アニメーションが終了したことを自分に伝えるアクション.ボードに伝えたいところだが
     * TODO:Myの外に移動
     */
    class EndOfAnimationAction(private val myUiPiece: MyUiPiece) : Action() {

        override fun act(delta: Float): Boolean {
            myUiPiece.nowAnimation = false
            return true
        }

    }

    /**
     * 攻撃処理のアニメ。移動差分を駒に登録する.TODO:アクターのサイズが分からないから中心が出ない...あと単にタイミングがおかしい
     * */
    private fun attackAction(x: Int, y: Int, targetX: Int, targetY: Int): SequenceAction {
        val seq = SequenceAction()
        val toX = targetX - x
        val toY = targetY - y
        seq.addAction(Actions.moveBy(96 * toX.toFloat(), 96 * toY.toFloat(), 0.2f))
        seq.addAction(Actions.moveBy(-96 * toX.toFloat(), -96 * toY.toFloat(), 0.2f))
        return seq
    }

    val attackAction: (actor: Actor) -> Boolean = {
        it.clearActions()
        true
    }

}