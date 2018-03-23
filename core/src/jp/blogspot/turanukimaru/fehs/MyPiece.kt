package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.board.Board
import jp.blogspot.turanukimaru.board.Piece
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.board.UiPiece

/**
 * 駒を継承してそのゲームにおける駒のルールを記述。画像としての処理もとりあえずここ。Actionは括りだすべきか？
 */
class MyPiece(containUnit: BattleUnit, board: Board<Ground>, owner: Board.Player) : Piece<BattleUnit, Ground>(containUnit,  board, owner) {

    override fun isStopable(otherUnit: BattleUnit?): Boolean =
            otherUnit == null


    override fun isMovable(piece: Piece<*, *>?, ground: Ground?, orientation: Int, steps: Int): Boolean {
        //デフォルトでは上下左右0,2,4,6にしておこう
        println("move to $piece $ground $orientation $steps")
        return piece == null && ((ground == Ground.P && steps < containUnit.movableSteps) || (ground == Ground.W && steps + 1 < containUnit.movableSteps))
    }

    override fun isEffective(piece: Piece<*, *>?, ground: Ground?, orientation: Int, steps: Int): Boolean {
        println("step:$steps range:${containUnit.effectiveRange}")
        return steps < containUnit.effectiveRange
    }

    override fun countStep(piece: Piece<*, *>?, ground: Ground?, orientation: Int, steps: Int): Int {
        println("count step $piece $ground $orientation $steps")
        return if (steps < containUnit.movableSteps) {
            steps + (ground?.cost ?: 0)
        } else {
            -1
        }
    }

    override fun effectiveOrientations(): kotlin.Array<Int> {
        //武器の射程によって変わってくるな。よく考えたら補助と攻撃じゃ範囲が違うわ・・・補助にしてもりぶろーは射程2だし・・・
        return arrayOf(0, 2, 4, 6)
        //return arrayOf(1, 3, 5, 7, 8, 12, 16, 20)
    }

    override fun countEffectiveStep(piece: Piece<*, *>?, ground: Ground?, orientation: Int, steps: Int): Int {
        //とりあえず再帰しないのでこれで
        return if (steps == 0) {
            containUnit.effectiveRange
        } else {
            -1
        }
    }

    override fun touchDown(): Boolean {
        if (!super.touchDown()) {
            return false
        }
        //その駒の情報を表示する。別クラスにするべきか
        board.updateInfo = { b ->
            //フォントサイズ替えたいところではある
//            b.bitmapFont.draw(b.batch, containUnit.armedHero.baseHero.name, 80f, 940f)
//            b.bitmapFont.draw(b.batch, "HP", 50f, 900f)
//            b.bitmapFont.draw(b.batch, containUnit.hp.toString(), 100f, 900f)
//            b.bitmapFont.draw(b.batch, "/", 140f, 900f)
//            b.bitmapFont.draw(b.batch, containUnit.armedHero.maxHp.toString(), 160f, 900f)
//            b.bitmapFont.draw(b.batch, "攻撃", 50f, 860f)
//            b.bitmapFont.draw(b.batch, containUnit.atk.toString(), 120f, 860f)
//            b.bitmapFont.draw(b.batch, "早さ", 180f, 860f)
//            b.bitmapFont.draw(b.batch, containUnit.spd.toString(), 240f, 860f)
//            b.bitmapFont.draw(b.batch, "守備", 50f, 820f)
//            b.bitmapFont.draw(b.batch, containUnit.def.toString(), 120f, 820f)
//            b.bitmapFont.draw(b.batch, "魔防", 180f, 820f)
//            b.bitmapFont.draw(b.batch, containUnit.res.toString(), 240f, 820f)
            true
        }
        return true
    }

    override fun touchDragged(touchedSquare: UiBoard.Position): Boolean {
        if (!super.touchDragged(touchedSquare)) {
            return false
        }
        val target = board.pieceMatrix[touchedSquare.x][touchedSquare.y]?.containUnit as? BattleUnit
        //敵ユニットに重ねたときは戦闘結果を計算して表示
        if (board.selectedPiece != null && board.effectiveRoute[touchedSquare.x][touchedSquare.y] > 0 && target != null && target != board.selectedPiece?.containUnit) {
            //キャストしたくないなあ。Boardにユニットの型入れるかなぁ
            val fightResult = containUnit.fight(target)
            for (result in fightResult) {
                println(result)
            }
            val expected = fightResult.last()

            board.updateInfo = { b ->
//                b.bitmapFont.draw(b.batch, containUnit.armedHero.baseHero.name, 50f, 940f)
//                b.bitmapFont.draw(b.batch, target.armedHero.baseHero.name, 320f, 940f)
//                b.bitmapFont.draw(b.batch, "HP", 240f, 900f)
//                b.bitmapFont.draw(b.batch, containUnit.hp.toString(), 20f, 900f)
//                b.bitmapFont.draw(b.batch, "→", 80f, 900f)
//                b.bitmapFont.draw(b.batch, expected.source.hp.toString(), 120f, 900f)
//                b.bitmapFont.draw(b.batch, target.hp.toString(), 290f, 900f)
//                b.bitmapFont.draw(b.batch, "→", 350f, 900f)
//                b.bitmapFont.draw(b.batch, expected.target.hp.toString(), 390f, 900f)

                //ダメージｘ回数表示は後でいいか
                true
            }
        }
        return true
    }

    override fun touchUp(position: UiBoard.Position): Boolean {
        if (!super.touchUp(position)) {
            return false
        }
        this.actionPhase = actionTouchedPoint(position)
        return true
    }

//    //これ要らない気がするな？touchupで動くはずだし
//    override fun boardClicked(event: InputEvent, position: UiBoard.Position): Boolean {
//        if (!super.boardClicked(event, position)) {
//            return false
//        }
//        //盤外/移動範囲外/効果範囲外は最初に戻す。状態は関係なし
//        this.actionPhase =
//                actionTouchedPoint(position)
//        return true
//    }

    /**
     * touchUp/boardのタッチから呼び出される。
     */
    fun actionTouchedPoint(position: UiBoard.Position): ActionPhase {
        println("actionTouchedPoint")
        println("position: $position")
        println("position: " + board.positionIsOnBoard(position))
        println("routeStack: $board.routeStack")

        //攻撃範囲かつユニットがいたらそいつにアクション.とりあえず攻撃位置に移動しているかドラッグで攻撃位置を経由しているかに限る
        val target = board.pieceMatrix[position.x][position.y]
        if (board.oldPosition != position && board.effectiveRoute[position.x][position.y] > 0 && target != null && target != this) {
            //攻撃位置探索.TODO:見つからなかったら移動可能場所の中から攻撃可能地点を探す必要がある。

            val attackPos = board.findAttackPos(this, position)
            if (attackPos != null) {
                //敵味方判別して行動。攻撃なら一歩手前に移動するし移動補助ならそれが発動する
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                println(board.pieceMatrix[position.x][position.y])
                println("attack to $position")
                println("attack from $attackPos")
                println(board.routeStack)
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                uiPiece!!.actor.clearActions()
                //とりあえずノーモーションで枡に戻してからアニメさせているが、一度不通に戻すべきかなあ
                board.setToPosition(this, attackPos)

                //キャストしたくないなあ。Boardにユニットの型入れるかなぁ
                val fightResult = containUnit.fight(target.containUnit as BattleUnit)
                val sourceSeq = SequenceAction()
                val targetSeq = SequenceAction()

                var attackCount = 0
                for (result in fightResult) {
                    println(result)
                    //ダメージを与える側が攻撃側の時
                    if (result.side == SIDES.ATTACKER) {
                        sourceSeq.addAction(attackAction(attackPos.x, attackPos.y, position.x, position.y))
                        targetSeq.addAction(Actions.delay(0.4f))
                        uiPiece!!.uiBoard.showNumbers(position.x, position.y, result.damage, attackCount++ * 0.4f + 0.2f)
                    } else {
                        targetSeq.addAction(attackAction(position.x, position.y, attackPos.x, attackPos.y))
                        sourceSeq.addAction(Actions.delay(0.4f))
                        uiPiece!!.uiBoard.showNumbers(attackPos.x, attackPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
                    }
                    containUnit.hp = result.source.hp
                    target.containUnit.hp = result.target.hp

                    if (containUnit.hp == 0) {
                        sourceSeq.addAction(Actions.fadeOut(1f))
                        board.removePiece(this, attackPos)
                    }
                    if (target.containUnit.hp == 0) {
                        targetSeq.addAction(Actions.fadeOut(1f))
                        board.removePiece(target, position)
                    }
                }
                //行動終了
                uiPiece!!.actor.addAction(sourceSeq)

                target.uiPiece!!.actor.addAction(targetSeq)
                board.deselectPiece()
                board.updateInfo = { _ -> true }
                return ActionPhase.ACTED
            } else {
                throw RuntimeException("lost attackpos to $position")
                //攻撃位置が見つからなかった場合は戻る
                //return ActionPhase.MOVED
            }
        }
        //動いてないときも画像位置調整のためその場にセット
        if (target == this && board.oldPosition == position) {
//        if (board.routeStack.isEmpty() || board.oldPosition == position) {
            println("board.moveToPossition(this, $board.oldPosition!!)")
            if (actionPhase == ActionPhase.MOVED) {
                board.moveCancel()
                return ActionPhase.READY
            }
            board.moveToPosition(this, position)
            return ActionPhase.MOVED
        }
        //移動後に自分自身を推したときは行動終了.クリックに移動すると両方起動してしまうから受付時間の処理とか要るな
        if (target == this && board.oldPosition != position && actionPhase == ActionPhase.MOVED) {
            //行動終了
            board.moveToPosition(this, position)
            board.deselectPiece()
            board.updateInfo = { _ -> true }
            return ActionPhase.ACTED
        }
        //目標に誰もおらず移動範囲外の時は移動キャンセル
        if (target == null && board.searchedRoute[position.x][position.y] < 0) {
            board.moveCancel()
            return ActionPhase.READY
        }
        //目標に誰もいないときはそこに動く
        if (target == null) {
            println("board.moveToPossition(this, $position)")
            board.moveToPosition(this, position)
        }
        return ActionPhase.MOVED
    }

    //
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

}

