package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.playboard.*

/**
 * 駒を継承してそのゲームにおける駒のルールを記述。画像としての処理もとりあえずここ。Actionは括りだすべきか？
 */
class MyPiece(val containUnit: BattleUnit, board: Board<MyPiece, Tile>, owner: Player, actionListener: IActionListener? = null) : Piece<MyPiece, Tile>(null, board, owner) {
    val actionListeners = if (actionListener != null) listOf<IActionListener>(actionListener) else listOf<IActionListener>()
    override val myPiece get() = this
    override fun isStoppable(piece: Piece<MyPiece, Tile>?): Boolean = piece == null || piece == this

    override fun isMovable(piece: Piece<MyPiece, Tile>?, tile: Tile?, orientation: Int, steps: Int, straight: Boolean, rotated: Int): Boolean {
        //デフォルトでは上下左右0,2,4,6にしておこう
//        println("move to $pieceAt $ground $orientation $steps")
        return (piece == null || piece.owner == owner) && ((tile == Tile.P && steps < containUnit.movableSteps) || (tile == Tile.W && steps + 1 < containUnit.movableSteps))
    }

    override fun isEffective(piece: Piece<MyPiece, Tile>?, tile: Tile?, orientation: Int, steps: Int, rotated: Int): Boolean {//これ steps はdx+dyでいい気がしてきた…
//        println("step:$steps range:${containUnit.actionRange}")range計算はMap側で終わってるから要らないかな…
        return steps < containUnit.effectiveRange && !(piece != null && piece.owner == owner)//味方は範囲に数えない
    }

    /**
     * 味方にサポートできる範囲か。優先度は攻撃より低いんだっけ？
     */
    override fun isSupportable(grounds: PiecesAndTiles<MyPiece, Tile>, orientation: Int, steps: Int, rotated: Int): Boolean {
        //support skill によって変わるのだがとりあえず一歩押す奴。//中心に対象がいないときは false でいいのかな・・・？
        return grounds.Piece0 != null && grounds.Piece0?.owner == owner && grounds.Piece1 == null && grounds.Piece0?.isMovable(null, grounds.TILE1, orientation, 0, false) ?: false
    }

    override fun countStep(piece: Piece<MyPiece, Tile>?, tile: Tile?, orientation: Int, steps: Int, rotated: Int): Int {
//        println("count step $pieceAt $ground $orientation $steps")
        return if (steps < containUnit.movableSteps) {
            steps + (tile?.cost ?: 0)
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
        actionListeners.forEach { it.updateInfo(this, true) }
        return true
    }

    //対象がルート上かとかの引数を増やすべきだな
    override fun dragged(position: Position): Boolean {
        return true//showActionResult(position)
    }

    private fun showActionResult(position: Position): Boolean {
        val target = board.physics.pieceAt(position)
        println("呼ばれてるはずなんだけど上書きされてるのかな…？$target ${actionableAt(position)} ${board.move.touch?.touchedPiece} 表示関数に優先順位でもつけるか？")
        //敵ユニットに重ねたときは戦闘結果を計算して表示
        if (actionableAt(position) > 0 && target != null && target.owner != owner) {
            //戦闘後効果は確か入ってなかったはず。マップ奥義は含まれるんだよな…そのうちやんなきゃな…
            val results = containUnit.fight(target.myPiece.containUnit)
            for (result in results) {
                println(result)
            }

            val fightResult = FightResult(containUnit, charPosition!!, target.myPiece.containUnit, position, containUnit.fight(target.myPiece.containUnit))
            actionListeners.forEach { it.updateActionResult(fightResult, true) }
        }
        return true

    }

    //行動結果表示。ドラッグと同じ
    override fun boardAction(source: Position, target: Position, targetPiece: Piece<MyPiece, Tile>): Boolean {
        return showActionResult(target)
    }

    //行動。相手がいるかは判定済み。向きをここに入れるかはちょっと難しいな…でも入れるしかないか。補助と言っても回復もあるんだしな
    override fun boardActionCommit(source: Position, target: Position, targetPiece: Piece<MyPiece, Tile>): Boolean {
        if (targetPiece.owner == owner) assistTouchedPoint(source, target, targetPiece) else actionTouchedPoint(target, targetPiece)
        return true
    }

    /**
     * touchUp/boardのタッチから呼び出される
     * 対象を移動させるから向きがいるな…
     */
    private fun assistTouchedPoint(position: Position, targetPosition: Position, target: Piece<MyPiece, Tile>): ActionPhase {
        val subP = targetPosition.sub(position)
        val to = targetPosition.plus(subP)
        board.physics.move(target, to)
        target.boardSlide(to)
        action(ActionPhase.ACTED, ActionEvent.MoveToCharPosition)//実際は押す動作になる
        return ActionPhase.ACTED
    }

    /**
     * touchUp/boardのタッチから呼び出される
     * 対象の piece が not null かは難しいところだな。…でもユニットのいないところへのアクションって移動になるからシステム的に作れないよな？
     */
    private fun actionTouchedPoint(position: Position, target: Piece<MyPiece, Tile>?): ActionPhase {

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
            //敵味方判別して行動。攻撃なら一歩手前に移動するし移動補助ならそれが発動する
            println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
            println(board.physics.pieceAt(position))
            println("attack to $position")
            println("attack from $attackPos")
            println(board.move.routeStack)
            println("!!!!!!!!!!!!!!!action!!!!!!!!!!!!!!!!")
            val fightResult = FightResult(containUnit, attackPos.p, target.myPiece.containUnit, position, containUnit.fight(target.myPiece.containUnit))
            action(ActionPhase.ACTED, ActionEvent.Attack, fightResult)
            target.myPiece.action(ActionPhase.DISABLED, ActionEvent.Attacked, fightResult)
            //表示にはfightResultのHPを使うがマップ上では最終的なHPをそのまま使える
            containUnit.hp = fightResult.attackResults.last().source.hp
            target.myPiece.containUnit.hp = fightResult.attackResults.last().target.hp

            if (containUnit.hp == 0) {
                board.physics.remove(this, attackPos.p)
            }
            if (target.myPiece.containUnit.hp == 0) {
                board.physics.remove(target, position)
            }

            actionListeners.forEach { it.updateInfo(this, false) }
            return ActionPhase.ACTED
        }
        return ActionPhase.READY
    }

    override fun update() {
        actionListeners.forEach { it.libUpdate() }
        localUpdate()
    }

    override fun touchDragged(position: Position, dx: Float, dy: Float): Boolean {
        //行動できないときは反応しない
        if (!isActionable) {
            return false
        }
        //ドラッグしてる絵を動かす
        actionListeners.forEach { it.directPos(position, dx, dy) }
        //移動可能範囲内で移動したらスタックに積む...
        return dragged(position)
    }

    /**
     * アクション。アクションと同時に次の状態に移行することを想定しているが…この関数あんまし要らん気がするな
     */
    fun action(nextPhase: ActionPhase, actionEvent: ActionEvent, fightResult: FightResult) {
        actionListeners.forEach { it.action(actionEvent, nextPhase, charPosition, fightResult) }
        this.actionPhase = nextPhase
    }

    /**
     * アクション。アクションと同時に次の状態に移行することを想定しているが…この関数あんまし要らん気がするな
     */
    override fun action(nextPhase: ActionPhase, actionEvent: ActionEvent) {
        //アクション後、readyかselectedかactedかで3種類はあるな.これ関数であるべきじゃないな…
        actionListeners.forEach { it.action(actionEvent, nextPhase, charPosition) }
        this.actionPhase = nextPhase
    }


    override fun toString(): String = containUnit.armedHero.name
}

