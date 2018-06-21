package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.board.Board
import jp.blogspot.turanukimaru.board.Hand
import jp.blogspot.turanukimaru.board.Piece
import jp.blogspot.turanukimaru.board.UiBoard

/**
 * 駒を継承してそのゲームにおける駒のルールを記述。画像としての処理もとりあえずここ。Actionは括りだすべきか？
 */
class MyPiece(containUnit: BattleUnit, board: Board<BattleUnit, Ground>, owner: Board.Player) : Piece<BattleUnit, Ground>(containUnit, board, owner) {
    var fightResult: FightResult? = null
    override fun isStopable(otherUnit: BattleUnit?): Boolean = otherUnit == null

    override fun isMovable(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Boolean {
        //デフォルトでは上下左右0,2,4,6にしておこう
        println("move to $piece $ground $orientation $steps")
        return piece == null && ((ground == Ground.P && steps < containUnit.movableSteps) || (ground == Ground.W && steps + 1 < containUnit.movableSteps))
    }

    override fun isEffective(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Boolean {
        println("step:$steps range:${containUnit.effectiveRange}")
        return steps < containUnit.effectiveRange
    }

    override fun countStep(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Int {
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

    override fun countEffectiveStep(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Int {
        //とりあえず再帰しないのでこれで
        return if (steps == 0) {
            containUnit.effectiveRange
        } else {
            -1
        }
    }

    /**
     * タッチされたときの処理。コマの動きは共通処理なのでここは表示とか
     */
    override fun touched(): Boolean {
        //その駒の情報を表示する。enumにしたいけどcontainUnitがなあ。
        board.updateInfo = { b ->
            //フォントサイズ替えたいところではある
            b.bitmapFont.draw(b.batch, containUnit.armedHero.baseHero.name.jp, 80f, 940f)
            b.bitmapFont.draw(b.batch, "HP", 50f, 900f)
            b.bitmapFont.draw(b.batch, containUnit.hp.toString(), 100f, 900f)
            b.bitmapFont.draw(b.batch, "/", 140f, 900f)
            b.bitmapFont.draw(b.batch, containUnit.armedHero.maxHp.toString(), 160f, 900f)
            b.bitmapFont.draw(b.batch, "攻撃", 50f, 860f)
            b.bitmapFont.draw(b.batch, containUnit.atk.toString(), 120f, 860f)
            b.bitmapFont.draw(b.batch, "早さ", 180f, 860f)
            b.bitmapFont.draw(b.batch, containUnit.spd.toString(), 240f, 860f)
            b.bitmapFont.draw(b.batch, "守備", 50f, 820f)
            b.bitmapFont.draw(b.batch, containUnit.def.toString(), 120f, 820f)
            b.bitmapFont.draw(b.batch, "魔防", 180f, 820f)
            b.bitmapFont.draw(b.batch, containUnit.res.toString(), 240f, 820f)
            true
        }
        return true
    }

    override fun dragged(position: UiBoard.Position): Boolean {
        val target = board.pieceMatrix[position.x][position.y]
        //敵ユニットに重ねたときは戦闘結果を計算して表示
        if (board.hand.selectedPiece != null && board.effectiveRoute[position.x][position.y] > 0 && target != null && target != board.hand.selectedPiece) {
            val fightResult = containUnit.fight(target.containUnit)
            for (result in fightResult) {
                println(result)
            }
            val expected = fightResult.last()

            board.updateInfo = { b ->
                b.bitmapFont.draw(b.batch, containUnit.armedHero.baseHero.name.jp, 50f, 940f)
                b.bitmapFont.draw(b.batch, target.containUnit.armedHero.baseHero.name.jp, 320f, 940f)
                b.bitmapFont.draw(b.batch, "HP", 240f, 900f)
                b.bitmapFont.draw(b.batch, containUnit.hp.toString(), 20f, 900f)
                b.bitmapFont.draw(b.batch, "→", 80f, 900f)
                b.bitmapFont.draw(b.batch, expected.source.hp.toString(), 120f, 900f)
                b.bitmapFont.draw(b.batch, target.containUnit.hp.toString(), 290f, 900f)
                b.bitmapFont.draw(b.batch, "→", 350f, 900f)
                b.bitmapFont.draw(b.batch, expected.target.hp.toString(), 390f, 900f)

                //ダメージｘ回数表示は後でいいか
                true
            }
        }
        return true
    }

    //抽象化するならここか
    override fun boardAction(hand: Hand<BattleUnit, Ground>, position: UiBoard.Position, targetPiece: Piece<BattleUnit, Ground>?): Boolean {
        this.actionPhase = actionTouchedPoint(position)
        return true
    }

    /**
     * touchUp/boardのタッチから呼び出される。MyUiPieceが要るか…？
     */
    fun actionTouchedPoint(position: UiBoard.Position): ActionPhase {
        println("actionTouchedPoint")
        println("position: $position")
        println("position: " + board.positionIsOnBoard(position))
        println("routeStack: $board.routeStack")

        //攻撃範囲かつユニットがいたらそいつにアクション.とりあえず攻撃位置に移動しているかドラッグで攻撃位置を経由しているかに限る
        val target = board.pieceMatrix[position.x][position.y]
        if (board.hand.oldPosition != position && board.effectiveRoute[position.x][position.y] > 0 && target != null && target != this) {
            //攻撃位置探索.TODO:見つからなかったら移動可能場所の中から攻撃可能地点を探す必要がある。

            val attackPos = board.findAttackPos(this, position)
            if (attackPos != null) {
                //敵味方判別して行動。攻撃なら一歩手前に移動するし移動補助ならそれが発動する
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                println(board.pieceMatrix[position.x][position.y])
                println("attack to $position")
                println("attack from $attackPos")
                println(board.hand.routeStack)
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                //とりあえずノーモーションで枡に戻してからアニメさせているが、一度不通に戻すべきかなあ
                board.setToPosition(this, attackPos)

                fightResult = FightResult(attackPos, position, containUnit.fight(target.containUnit))
//                target.fightResult = fightResult
                //HP減らすのきついな…表示は分けるか…？
                containUnit.hp = fightResult!!.attackResults.last().source.hp
                target.containUnit.hp = fightResult!!.attackResults.last().target.hp

                if (containUnit.hp == 0) {
                    board.removePiece(this, attackPos)
                }
                if (target.containUnit.hp == 0) {
                    board.removePiece(target, position)
                }
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
        if (target == this && board.hand.oldPosition == position) {
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
        if (target == this && board.hand.oldPosition != position && actionPhase == ActionPhase.MOVED) {
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

}

data class FightResult(val attackPos: UiBoard.Position, val targetPos: UiBoard.Position, val attackResults: List<AttackResult>)