package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.board.*

/**
 * 駒UIの拡張。ロジックは親が持つのでこっちは表示関係のみでいいはず
 */
class MyUiPiece(actor: Actor, uiBoard: UiBoard, private var myPiece: MyPiece) : UiPiece(actor, uiBoard, myPiece) {

    override fun update() {
        piece.animationStart = false
        when (piece.actionPhase) {
            Piece.ActionPhase.PUTTED -> {//おかれた直後なので初期化してDisabled.いずれ増援が出たr
                actionSetToPosition(piece.position)
                piece.action(Piece.ActionPhase.DISABLED)
            }
            Piece.ActionPhase.START -> {//おかれた直後なので初期化してREADY
                actionSetToPosition(piece.position)
//                uiBoard.stage.addActor(actor)
                piece.action(Piece.ActionPhase.READY)
            }
            Piece.ActionPhase.DISABLED ->//ターンが違うなど操作不能の時は枡に合わせてセット
                actor.setPosition(uiBoard.squareXtoPosX(piece.position!!.x), uiBoard.squareYtoPosY(piece.position!!.y))
            Piece.ActionPhase.READY -> {
                //ドラッグしてるときはアクションをクリアしてドラッグに追従表示.タッチじゃなくてドラッグ判定にせねば。実機では正しく動くがシミュ上ではめっちゃぶれる
                when (touched) {
                    TouchPhase.DRAG -> {
                        actor.clearActions()
                        actor.setPosition(actor.x + dx, actor.y + dy);dx = 0.0f;dy = 0.0f
                        actionNow = false//一括クリアもいるなこれは
                    }
                    TouchPhase.RELEASE -> {
                        //離した後は枡にフィットさせる。
//                    actor.setPosition(uiBoard.squareXtoPosX(piece.position!!.x), uiBoard.squareYtoPosY(piece.position!!.y))
                        when (uiBoard.board.hand) {
                            HandPhase.SELECTED -> startAction { actionMoveToPosition(piece.position) }//実機ではNONEとは絵が違う
                            HandPhase.MOVED -> startAction { actionMoveToPosition(uiBoard.board.hand.newPosition) }//実機ではNONEとは絵が違う
                            else -> startAction { actionMoveToPosition(piece.position) }
                        }
                    }
                    TouchPhase.NONE -> {
                        println("ここで落ちてる？")
                        startAction { readyAction() }
                    }
                    TouchPhase.TOUCH -> {
                        //タッチしてドラッグが始まる前はどうするかな？なにもしなくていいか？
                    }

                }
            }
            Piece.ActionPhase.ATTACK -> {//戦闘中のアクションはそのうち考える
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
            Piece.ActionPhase.ACTED -> {//現在の位置に灰色で表示
                actionSetToPosition(piece.position)
                actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f) }//これ灰色じゃねーな全部　r+g+b/3　にするのが正しいか？
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
        sourceSeq.addAction(EndOfAnimationAction(this, 4.0f))
        if (isAttacker) sourceSeq.addAction(EndOfAnimationAction(this, 4.0f)) else targetSeq.addAction(EndOfAnimationAction(this, 4.0f))

        //行動終了
        actor.addAction(sourceSeq)
//    fightResult.attackResults.last().target.uiPiece!!.actor.addAction(targetSeq)

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

    /**
     * 立ってるときのアニメ１ループ分。LibGDXにループ機能はあるが操作統一のため主導でループ
     * */
    private fun readyAction(): SequenceAction {
        println("readyAction")
//        if(myPiece.position == null) { return SequenceAction()}
//        //盤面に無いときの処理真面目に考えないとな…
//        val finalX = uiBoard.squareXtoPosX(myPiece.position.x)
//        val finalY = uiBoard.squareYtoPosY(position.y)
//        val seq = SequenceAction()
//        if(finalX - actor.x >1 || finalY - actor.y > 1){
//        seq.addAction(Actions.moveBy(finalX - actor.x, finalY - actor.y, 0.1f))
//        seq.addAction(EndOfAnimationAction(this))
//            actor.addAction(seq)
//        return seq
//        }
        val base = actors[0]
        val face = actors[1]
        //clearしないでアクション追加するとめっちゃ落ちる。デフォルト位置に戻す処理もいるな
        actor.setPosition(uiBoard.squareXtoPosX(piece.position!!.x), uiBoard.squareYtoPosY(piece.position!!.y))
        base.setPosition(0f, 0f)
        face.setPosition(0f, 0f)
        actor.clearActions()
        base.clearActions()
        face.clearActions()
        val seq1 = SequenceAction()
        seq1.addAction(Actions.moveBy(2.0f, 2.0f, 0.5f))
        seq1.addAction(Actions.moveBy(-2.0f, -2.0f, 0.5f))
        seq1.addAction(EndOfAnimationAction(this, 1f))//seq関係なくすぐ呼び出される…
        base.addAction(seq1)
        val seq2 = SequenceAction()
        seq2.addAction(Actions.moveBy(6.0f, 6.0f, 0.5f))
        seq2.addAction(Actions.moveBy(-6.0f, -6.0f, 0.5f))
        face.addAction(seq2)
        return seq1
    }

    val attackAction: (actor: Actor) -> Boolean = {
        it.clearActions()
        true
    }

}