package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.board.*

/**
 * 駒を継承してそのゲームにおける駒のルールを記述。画像としての処理もとりあえずここ。Actionは括りだすべきか？
 */
class MyPiece(containUnit: BattleUnit, board: Board<BattleUnit, Ground>, owner: Player, actionListener: ActionListener) : Piece<BattleUnit, Ground>(containUnit, board, owner, actionListener) {
    var fightResult: FightResult? = null
    override fun isStoppable(otherUnit: BattleUnit?): Boolean = otherUnit == null

    override fun isMovable(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Boolean {
        //デフォルトでは上下左右0,2,4,6にしておこう
//        println("move to $pieceAt $ground $orientation $steps")
        return piece == null && ((ground == Ground.P && steps < containUnit.movableSteps) || (ground == Ground.W && steps + 1 < containUnit.movableSteps))
    }

    override fun isEffective(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Boolean {
//        println("step:$steps range:${containUnit.effectiveRange}")
        return steps < containUnit.effectiveRange
    }

    override fun countStep(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int): Int {
//        println("count step $pieceAt $ground $orientation $steps")
        return if (steps < containUnit.movableSteps) {
            steps + (ground?.cost ?: 0)
        } else {
            -1
        }
    }

    override fun effectiveOrientations(): Array<Int> {
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
        board.updateInfo({ b ->
            //フォントサイズ替えたいところではある
            b.bitmapFont.draw(b.batch, containUnit.armedHero.baseHero.heroName.jp, 80f, 940f)
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
        },1)
        return true
    }

    //対象がルート上かとかの引数を増やすべきだな
    override fun dragged(position: UiBoard.Position): Boolean {
        return showActionResult(position)
    }

    private fun showActionResult(position: UiBoard.Position): Boolean {
        val target = board.pieceAt(position)
        println("呼ばれてるはずなんだけど上書きされてるのかな…？$target ${effectiveRouteOf(position)} ${board.move.touch?.touchedPiece} 表示関数に優先順位でもつけるか？")
        //敵ユニットに重ねたときは戦闘結果を計算して表示
        if (effectiveRouteOf(position) > 0 && target != null && target.owner != owner) {
            //戦闘後効果は確か入ってなかったはず。マップ奥義は含まれるんだよな…そのうちやんなきゃな…
            val fightResult = containUnit.fight(target.containUnit)
            for (result in fightResult) {
                println(result)
            }
            val expected = fightResult.last()

            board.updateInfo ( { b ->
                b.bitmapFont.draw(b.batch, containUnit.armedHero.baseHero.heroName.jp, 50f, 940f)
                b.bitmapFont.draw(b.batch, target.containUnit.armedHero.baseHero.heroName.jp, 320f, 940f)
                b.bitmapFont.draw(b.batch, "HP", 240f, 900f)
                b.bitmapFont.draw(b.batch, containUnit.hp.toString(), 20f, 900f)
                b.bitmapFont.draw(b.batch, "→", 80f, 900f)
                b.bitmapFont.draw(b.batch, expected.source.hp.toString(), 120f, 900f)
                b.bitmapFont.draw(b.batch, target.containUnit.hp.toString(), 290f, 900f)
                b.bitmapFont.draw(b.batch, "→", 350f, 900f)
                b.bitmapFont.draw(b.batch, expected.target.hp.toString(), 390f, 900f)

                //ダメージｘ回数表示は後でいいか
                true
            },10)
        }
        return true

    }

    //行動結果表示。ドラッグと同じ
    override fun boardAction(position: UiBoard.Position, targetPiece: Piece<BattleUnit, Ground>?): Boolean {
        return showActionResult(position)
    }

    //行動。相手がいるかは判定済み。
    override fun boardActionCommit(position: UiBoard.Position, targetPiece: Piece<BattleUnit, Ground>?): Boolean {
        actionTouchedPoint(position, targetPiece)
        return true
    }

    /**
     * touchUp/boardのタッチから呼び出される。MyUiPieceが要るか…？
     */
    private fun actionTouchedPoint(position: UiBoard.Position, target: Piece<BattleUnit, Ground>?): ActionPhase {
        println("actionTouchedPoint")
        println("charPosition: $position")
        println("charPosition: " + board.positionIsOnBoard(position))
        println("routeStack: $board.routeStack")
        println("${board.move.moving.from} ${effectiveRouteOf(position)} $target ${target == this}")

        //敵は攻撃。味方はアシスト。アシストのロジックまだ考えてないけどな！
        if (board.move.moving.from != position && effectiveRouteOf(position) > 0 && target != null && target != this) {
//戦闘アクションは流石にここで登録してもいい気がするが…いやダメか…Updateで読む方法考えないとな
            val attackPos = board.findActionPos(this, position)
            if (attackPos != null) {
                //敵味方判別して行動。攻撃なら一歩手前に移動するし移動補助ならそれが発動する
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                println(board.pieceAt(position))
                println("attack to $position")
                println("attack from $attackPos")
                println(board.move.routeStack)
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                fightResult = FightResult(containUnit, attackPos, target.containUnit, position, containUnit.fight(target.containUnit))
                action(ActionPhase.ACTED, ActionEvent.Attack, fightResult)
                target.action(ActionPhase.DISABLED, ActionEvent.Attacked, fightResult)
                //HP減らすのきついな…表示は分けるか…？
                containUnit.hp = fightResult!!.attackResults.last().source.hp
                target.containUnit.hp = fightResult!!.attackResults.last().target.hp

                if (containUnit.hp == 0) {
                    board.removePiece(this, attackPos)
                }
                if (target.containUnit.hp == 0) {
                    board.removePiece(target, position)
                }

// 消すのは移動終わってから
//                this.existsPosition = attackPos
//                this.to = null

                board.updateInfo()
                return ActionPhase.ACTED
            } else {
                throw RuntimeException("ここに来るはずないし")
            }
        }
        return ActionPhase.READY
    }

    override fun toString(): String = "${containUnit.armedHero.name}"
}

data class FightResult(val attacker: BattleUnit, val attackPos: UiBoard.Position, val target: BattleUnit, val targetPos: UiBoard.Position, val attackResults: List<AttackResult>)