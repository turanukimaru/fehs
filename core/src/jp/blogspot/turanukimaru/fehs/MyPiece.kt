package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.board.*

/**
 * 駒を継承してそのゲームにおける駒のルールを記述。画像としての処理もとりあえずここ。Actionは括りだすべきか？
 */
class MyPiece(containUnit: BattleUnit, board: Board<BattleUnit, Ground>, owner: Player, actionListener: ActionListener) : Piece<BattleUnit, Ground>(containUnit, board, owner, actionListener) {
    override fun isStoppable(piece: Piece<BattleUnit, Ground>?): Boolean = piece == null || piece == this

    override fun isMovable(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int, straight: Boolean, rotated: Int): Boolean {
        //デフォルトでは上下左右0,2,4,6にしておこう
//        println("move to $pieceAt $ground $orientation $steps")
        return (piece == null || piece.owner == owner) && ((ground == Ground.P && steps < containUnit.movableSteps) || (ground == Ground.W && steps + 1 < containUnit.movableSteps))
    }

    override fun isEffective(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int, rotated: Int): Boolean {//これ steps はdx+dyでいい気がしてきた…
//        println("step:$steps range:${containUnit.actionRange}")range計算はMap側で終わってるから要らないかな…
        return steps < containUnit.effectiveRange && !(piece != null && piece.owner == owner)//味方は範囲に数えない
    }

    /**
     * 味方にサポートできる範囲か。優先度は攻撃より低いんだっけ？
     */
    override fun isSupportable(grounds: PiecesAndGrounds<BattleUnit, Ground>, orientation: Int, steps: Int, rotated: Int): Boolean {
        //support skill によって変わるのだがとりあえず一歩押す奴。
        return grounds.Piece0 != null && grounds.Piece0.owner == owner && grounds.Piece1 == null && grounds.Piece0.isMovable(null, grounds.Ground1, orientation, 0, false)
    }

    override fun countStep(piece: Piece<BattleUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int, rotated: Int): Int {
//        println("count step $pieceAt $ground $orientation $steps")
        return if (steps < containUnit.movableSteps) {
            steps + (ground?.cost ?: 0)
        } else {
            -1
        }
    }

    //武器の射程によって変わってくるな。よく考えたら補助と攻撃じゃ範囲が違うわ・・・補助にしてもりぶろーは射程2だし・・・あと、単に歩数を数えるならx,yを足し引きすればいいのか
    override fun actionOrientations(): Array<Int> =
            when (containUnit.effectiveRange) {
                1 -> arrayOf(0, 2, 4, 6)
                2 -> arrayOf(1, 3, 5, 7, 8, 12, 16, 20)
                3 -> arrayOf(9, 11, 13, 15, 17, 19, 21, 23, 24, 30, 36, 42)
                else -> arrayOf()
            }

    /**
     * 攻撃可能射程をアーチ並みにしてみる。動くかな？
     */
    override fun actionRange(): Pair<Int, Int> {
        return if (containUnit.effectiveRange == 2) Pair(7, 4) else Pair(1, 1)
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
        }, 1)
        return true
    }

    //対象がルート上かとかの引数を増やすべきだな
    override fun dragged(position: UiBoard.Position): Boolean {
        return true//showActionResult(position)
    }

    private fun showActionResult(position: UiBoard.Position): Boolean {
        val target = board.physic.pieceAt(position)
        println("呼ばれてるはずなんだけど上書きされてるのかな…？$target ${actionableAt(position)} ${board.move.touch?.touchedPiece} 表示関数に優先順位でもつけるか？")
        //敵ユニットに重ねたときは戦闘結果を計算して表示
        if (actionableAt(position) > 0 && target != null && target.owner != owner) {
            //戦闘後効果は確か入ってなかったはず。マップ奥義は含まれるんだよな…そのうちやんなきゃな…
            val fightResult = containUnit.fight(target.containUnit)
            for (result in fightResult) {
                println(result)
            }
            val expected = fightResult.last()

            board.updateInfo({ b ->
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
            }, 10)
        }
        return true

    }

    //行動結果表示。ドラッグと同じ
    override fun boardAction(source: UiBoard.Position, target: UiBoard.Position, targetPiece: Piece<BattleUnit, Ground>): Boolean {
        return showActionResult(target)
    }

    //行動。相手がいるかは判定済み。向きをここに入れるかはちょっと難しいな…でも入れるしかないか。補助と言っても回復もあるんだしな
    override fun boardActionCommit(source: UiBoard.Position, target: UiBoard.Position, targetPiece: Piece<BattleUnit, Ground>): Boolean {
        if (targetPiece.owner == owner) assistTouchedPoint(source, target, targetPiece) else actionTouchedPoint(target, targetPiece)
        return true
    }

    /**
     * touchUp/boardのタッチから呼び出される
     * 対象を移動させるから向きがいるな…
     */
    private fun assistTouchedPoint(position: UiBoard.Position, targetPosition: UiBoard.Position, target: Piece<BattleUnit, Ground>): ActionPhase {
        val subP = targetPosition.sub(position)
        val to = targetPosition.plus(subP)
        board.physic.move(target, to)
        target.boardSlide(to)
        action(ActionPhase.ACTED, ActionEvent.MoveToCharPosition)//実際は押す動作になる
        return ActionPhase.ACTED
    }

    /**
     * touchUp/boardのタッチから呼び出される
     * 対象の piece が not null かは難しいところだな。…でもユニットのいないところへのアクションって移動になるからシステム的に作れないよな？
     */
    private fun actionTouchedPoint(position: UiBoard.Position, target: Piece<BattleUnit, Ground>?): ActionPhase {

        println("actionTouchedPoint")
        println("charPosition: $position")
        println("charPosition: " + board.positionIsOnBoard(position))
        println("existPosition: $existsPosition // これをそのまま attackPos として使えないとおかしい")
        println("routeStack: ${board.move.routeStack}")
        println("${board.move.moving.from} ${actionableAt(position)} $target ${target == this}")

        //敵は攻撃。味方はアシスト。アシストのロジックまだ考えてないけどな！
        if (target != null && target != this) {
//戦闘アクションは流石にここで登録してもいい気がするが…いやダメか…Updateで読む方法考えないとな
            val attackPos = existsPosition
            if (attackPos != null) {
                //敵味方判別して行動。攻撃なら一歩手前に移動するし移動補助ならそれが発動する
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                println(board.physic.pieceAt(position))
                println("attack to $position")
                println("attack from $attackPos")
                println(board.move.routeStack)
                println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
                val fightResult = FightResult(containUnit, attackPos, target.containUnit, position, containUnit.fight(target.containUnit))
                action(ActionPhase.ACTED, ActionEvent.Attack, fightResult)
                target.action(ActionPhase.DISABLED, ActionEvent.Attacked, fightResult)
                //表示にはfightResultのHPを使うがマップ上では最終的なHPをそのまま使える
                containUnit.hp = fightResult.attackResults.last().source.hp
                target.containUnit.hp = fightResult.attackResults.last().target.hp

                if (containUnit.hp == 0) {
                    board.physic.remove(this, attackPos)
                }
                if (target.containUnit.hp == 0) {
                    board.physic.remove(target, position)
                }

                board.updateInfo()
                return ActionPhase.ACTED
            } else {
                throw RuntimeException("ここに来るはずないし")
            }
        }
        return ActionPhase.READY
    }

    override fun toString(): String = containUnit.armedHero.name
}

