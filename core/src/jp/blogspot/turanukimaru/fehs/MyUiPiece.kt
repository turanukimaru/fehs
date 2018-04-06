package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.board.Piece
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.board.UiPiece

class MyUiPiece(actor: Actor, uiBoard: UiBoard, var myPiece: MyPiece) : UiPiece(actor, uiBoard, myPiece) {
  override  fun update() {
        //アニメーションが登録されていないときは適当に動かす。行動前後で色とアニメーションを変える。これアニメーションをくくりださないと大変だな
        if (myPiece.actionPhase == Piece.ActionPhase.ACTED) {
            //戦闘結果を持ってるときはそれを動かす
            if(myPiece.fightResult != null){

                resultToSeq(
                        myPiece.fightResult!!
                )
                myPiece.fightResult = null
            }
            //一連の動作が終わってるか判定したいんだけど…
//            actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f) }
        } else {
            actors.forEach { a -> a.setColor(1f, 1f, 1f, 1f) }
            //TODO:ループアクションを設定する
//            if (actors.size < 2) return
//            val base = actors[0]
//            val face = actors[1]
//            if (animateCount == 8) {
//                base.y = base.y + 2
//                face.x = face.x + 2
//            } else if (animateCount == 15) {
//                base.y = base.y - 2
//                face.x = face.x - 2
//            }
//            animateCount = if (animateCount < 16) {
//                animateCount + 1
//            } else {
//                0
//            }
        }
    }

fun resultToSeq(fightResult:FightResult){
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
        targetSeq.addAction(attackAction(fightResult.targetPos.x, fightResult.targetPos.y,  fightResult.attackPos.x,  fightResult.attackPos.y))
        sourceSeq.addAction(Actions.delay(0.4f))
        uiBoard.showNumbers( fightResult.attackPos.x,  fightResult.attackPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
    }
    if (result.source.hp == 0) {
        sourceSeq.addAction(Actions.fadeOut(1f))
    }
    if (result.target.hp == 0) {
        targetSeq.addAction(Actions.fadeOut(1f))
    }
    }
    sourceSeq.addAction(CallbackAction({true}))
    //行動終了
    actor.addAction(sourceSeq)
//    fightResult.attackResults.last().target.uiPiece!!.actor.addAction(targetSeq)

}    //
    class CallbackAction(val f:()->Boolean) :Action (){

        override fun  act ( delta:Float) :Boolean{
            f()
            return true
        }

    }
    /**
     * 攻撃処理のアニメ。移動差分を駒に登録する.TODO:アクターのサイズが分からないから中心が出ない...あと単にタイミングがおかしい
     * */
    fun attackAction(x: Int, y: Int, targetX: Int, targetY: Int): SequenceAction {
        val seq = SequenceAction()
        val toX = targetX - x
        val toY = targetY - y
        seq.addAction(Actions.moveBy(96 * toX.toFloat(), 96 * toY.toFloat(), 0.2f))
        seq.addAction(Actions.moveBy(-96 * toX.toFloat(), -96 * toY.toFloat(), 0.2f))
        return seq
    }

    val attackAction:(actor: Actor) ->Boolean= {
        it.clearActions()
        true}

}