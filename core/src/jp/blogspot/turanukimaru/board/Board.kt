package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position

/**
 * 論理盤面.操作系と演算系別にするとかちょっと分割したいな…
 */
class Board<UNIT, GROUND>(val horizontalLines: Int, val verticalLines: Int) {
    /**
     * 盤上の駒
     * 駒が無いところはnull
     */
    val pieceMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()
    /**
     * 盤上の駒リスト。ターン終了時に全部Disableにするとか     *
     */
    val pieceList = mutableListOf<Piece<UNIT, GROUND>>()
    /**
     * 盤上の地形
     * 地形が無いところはnull.nullObjectとか床を作るべきか？でも床のないボードゲームのが多いよな
     */
    private val groundMatrix = mutableListOf<MutableList<GROUND?>>()
    /**
     * 現在盤上の所有権を持つプレイヤー
     */
    var owner: Player? = null

    /**
     * 現在の指し手
     */
    val move = Move(this)

    /**
     * 横の0..last
     * 0..x-1 は 0 until x と書ける
     */
    val horizontalIndexes = 0 until horizontalLines

    /**
     * 縦の0..last
     */
    val verticalIndexes = 0 until verticalLines

    init {
        horizontalIndexes.forEach {
            val unitLine = mutableListOf<Piece<UNIT, GROUND>?>()
            verticalIndexes.forEach { unitLine.add(null) }
            pieceMatrix.add(unitLine)

            val boxLine = mutableListOf<GROUND?>()
            verticalIndexes.forEach { boxLine.add(null) }
            groundMatrix.add(boxLine)
        }
    }

    fun touch (position: Position){
println("Board.touch x:${position.x} y:${position.y}")
        verticalIndexes.forEach { y ->
            horizontalIndexes.forEach { x ->
                //移動範囲から計算。これ移動しないときと処理が区別できるようにしたほうが良いな
                print("${pieceMatrix[x][y]},")
            }
            println()
        }

        move.touch(position, pieceMatrix[position.x][position.y])
    }
    /**
     * 地形のマトリックスをコピーする。視覚的に直感的なMatrixと記述上に直感的なMatrixxyはxyが入れ替わっているので入れ替えてコピーする
     */
    fun copyGroundSwitchXY(matrix: Array<Array<GROUND>>) {
        groundMatrix.forEach { e -> e.clear() }
        groundMatrix.forEachIndexed { x, line ->
            verticalIndexes.forEach { y -> line.add(matrix[verticalLines - 1 - y][x]) }
        }
    }

    /**
     * 駒の位置を探す。見つからなかったらnullを返すようにしてるけど例外を投げるべきか？何らかの原因であるべき駒が無いんだから。
     */
    fun searchUnitPosition(piece: Piece<*, *>): Position? {
        println("searchUnitPosition $piece")
        horizontalIndexes.forEach { x ->
            verticalIndexes.forEach { y ->
                if (pieceMatrix[x][y] == piece) {
                    return Position(x, y)
                }
            }
        }
        return null
    }

    /**
     * 対象の枡に駒を置く。駒が配置済みだったら例外を吐く。自分がすでにいるところにPutしたケースはまだけんとうしなくていいか
     */
    fun put(piece: Piece<UNIT, GROUND>, x: Int, y: Int) {
        if (pieceMatrix[x][y] != null) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        pieceMatrix[x][y] = piece
        pieceList.add(piece)
        piece.putOn(x, y)
    }

    /**
     * 対象の枡に駒を置く。移動元が見つからないときは例外を吐く
     * Actionとの関係を整理したほうが良いな
     */
    fun moveToPosition(piece: Piece<UNIT, GROUND>, position: Position, x :Int = position.x, y:Int = position.y) {
        if (x < 0 || y < 0 || x >= horizontalLines || y >= verticalLines || piece.searchedRoute[x][y] < 0) {
            throw RuntimeException("out of range pieceMatrix[$x][$y]")
        }
        if (isAnotherPiece(piece, x, y)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        println("moveTon $piece $x $y")
        val oldSquare = searchUnitPosition(piece)!!
        println("moveFrom $piece ${oldSquare.x} ${oldSquare.y}")
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceMatrix[x][y]
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("another piece $targetSquaresUnit")
        }
        pieceMatrix[oldSquare.x][oldSquare.y] = null
        pieceMatrix[x][y] = piece
    }

    /**
     * 盤面の座標が盤上に有るか。つまりIndexがマイナスになったり幅を超えたりしていないか
     */
    fun positionIsOnBoard(position: Position): Boolean {
        return position.x >= 0 && position.y >= 0 && position.x < horizontalLines && position.y < verticalLines
    }

    /**
     * update時に盤外に表示する関数
     */
    var updateInfo: (uiBoard: UiBoard) -> Boolean = { _ -> true }

    /**
     * 移動可能な経路を調べる
     */
    fun searchRoute(piece: Piece<UNIT, GROUND>): MutableList<MutableList<Int>> {
        println("searchRoute $piece")
        val routeMatrix = mutableListOf<MutableList<Int>>()
        horizontalIndexes.forEach {
            val unitLine = mutableListOf<Int>()
            verticalIndexes.forEach { unitLine.add(-1) }
            routeMatrix.add(unitLine)
        }
        //nullの時例外を吐きたいならこう
        val square = searchUnitPosition(piece) ?: throw RuntimeException("ユニットが見つからない")
        val steps = 0
        println("first step at $piece $square $steps ")
        step(piece, square, steps, routeMatrix)
        //これ更新形式と新しいマトリックスを渡す形どっちがいいかなあ
        piece.searchedRoute = routeMatrix
//        routeMatrix.forEach { v ->piece. searchedRoute.add(v) }
        return routeMatrix
    }

    /**
     * 経路探索中に一歩進んで再帰する
     */
    private fun step(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, routeMatrix: MutableList<MutableList<Int>>) {
        routeMatrix[position.x][position.y] = steps
        val orientations = piece.orientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                //targetStepsが-1のときに終了するという技もあるがどうしよう？
                val targetSteps = piece.countStep(targetUnit, targetSquare, v, steps)
                val stepped = routeMatrix[targetPos.x][targetPos.y]
                //移動出来て歩数が増えてなければ。でふぉ-1はやめたほうがいいかな。
                if (piece.isMovable(targetUnit, targetSquare, v, steps) && (stepped == -1 || stepped > targetSteps)) {
                    step(piece, targetPos, targetSteps, routeMatrix)
                }
            }
        }
    }

    /**
     * 効果範囲を探す。
     */
    fun searchEffectiveRoute(piece: Piece<UNIT, GROUND>): MutableList<MutableList<Int>> {
        println("searchEffectiveRoute $piece")
        val routeMatrix = mutableListOf<MutableList<Int>>()
        horizontalIndexes.forEach {
            val unitLine = mutableListOf<Int>()
            verticalIndexes.forEach { unitLine.add(-1) }
            routeMatrix.add(unitLine)

        }
        horizontalIndexes.forEach { x ->
            verticalIndexes.forEach { y ->
                val square = Position(x, y)
                //移動範囲から計算。これ移動しないときと処理が区別できるようにしたほうが良いな
                if (piece.searchedRoute[x][y] >= 0) stepEffect(piece, square, 0, routeMatrix)
            }
        }
        piece.effectiveRoute = routeMatrix
        return routeMatrix
    }

    /**
     * 効果範囲探索中に一歩進んで再帰するけどこれ再帰しないほうが良い気がしてきた
     */
    private fun stepEffect(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, routeMatrix: MutableList<MutableList<Int>>) {
        if (steps > 0) {
            routeMatrix[position.x][position.y] = steps
        }
        val orientations = piece.effectiveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
//            println("effect to $targetPos")
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                //targetStepsが-1のときに終了するという技もあるがどうしよう？
                val targetSteps = piece.countEffectiveStep(targetUnit, targetSquare, v, steps)
                val steped = routeMatrix[targetPos.x][targetPos.y]
                //一応効果範囲内だったら再帰するようにしてはいるが攻撃範囲は再帰しないほうが良いので削除するかも
                if (piece.isEffective(targetUnit, targetSquare, v, steps) && (steped == -1 || steped > targetSteps)) {
                    stepEffect(piece, targetPos, targetSteps, routeMatrix)
                }
            }
        }
    }

    /**
     * 移動方向から升目を計算する。実はx=x(x-4)が正か負か、yは(y-2)(y-6)、2歩も同様に計算できるのだがきっとwhenのが分かりやすい
     */
    private fun moveWithOrientation(v: Int, position: Position, sign: Int = 1): Position {
        return when (v) {
            0 -> Position(position.x, position.y - 1 * sign)
            1 -> Position(position.x + 1 * sign, position.y - 1 * sign)
            2 -> Position(position.x + 1 * sign, position.y)
            3 -> Position(position.x + 1 * sign, position.y + 1 * sign)
            4 -> Position(position.x, position.y + 1 * sign)
            5 -> Position(position.x - 1 * sign, position.y + 1 * sign)
            6 -> Position(position.x - 1 * sign, position.y)
            7 -> Position(position.x - 1 * sign, position.y - 1 * sign)
            8 -> Position(position.x, position.y - 2 * sign)
            9 -> Position(position.x + 1 * sign, position.y - 2 * sign)
            10 -> Position(position.x + 2 * sign, position.y - 2 * sign)
            11 -> Position(position.x + 2 * sign, position.y - 1 * sign)
            12 -> Position(position.x + 2 * sign, position.y)
            13 -> Position(position.x + 2 * sign, position.y + 1 * sign)
            14 -> Position(position.x + 2 * sign, position.y + 2 * sign)
            15 -> Position(position.x + 1 * sign, position.y + 2 * sign)
            16 -> Position(position.x, position.y + 2 * sign)
            17 -> Position(position.x - 1 * sign, position.y + 2 * sign)
            18 -> Position(position.x - 2 * sign, position.y + 2 * sign)
            19 -> Position(position.x - 2 * sign, position.y + 1 * sign)
            20 -> Position(position.x - 2 * sign, position.y)
            21 -> Position(position.x - 2 * sign, position.y - 1 * sign)
            22 -> Position(position.x - 2 * sign, position.y - 2 * sign)
            23 -> Position(position.x - 1 * sign, position.y - 2 * sign)
            else -> Position(position.x, position.y)
        }
    }

    /**
     * ターン開始。盤の所有者をセットして、全ての駒を準備状態にする。動作を変えるときはきっと引数に関数を追加して、その関数を呼ぶのがいいと思う。
     */
    fun turn(owner: Player) {
        println("TODO:ターン移動処理")
        move.moveCancel()
        this.owner = owner
//一度全部の駒を使用不可にしてから手番の人の駒を有効にする
        pieceList.forEach { it.action(Piece.ActionPhase.DISABLED) }
        owner.pieceList.forEach { it.ready() }
    }

    /**
     * select解除
     */
    fun deselectPiece() {
        println("deselectPiece")
        //移動範囲のリセットってやるべきかなあ？
        move.clear()
    }

    /**
     * プレイヤー。盤の所有者に使う
     */
    class Player {
        val pieceList = mutableListOf<Piece<*, *>>()
    }

    /**
     * 対象の枡に自分以外の駒があるときにtrue
     */
    private fun isAnotherPiece(piece: Piece<*, GROUND>, x: Int, y: Int): Boolean {
        val target = pieceMatrix[x][y]
        return /*piece.effectiveRoute[x][y] > 0 && ルート内かどうかは今更見なくていいかなあ*/ target != null && target != piece
    }

    /**
     * 対象の位置から移動経路をさかのぼり攻撃場所を探す。 経路中に無いときには攻撃可能位置を探す
     * 単にLastを出力しないのは射程２の武器のため
     */
    fun <T1> findAttackPos(piece: Piece<T1, GROUND>, position: Position): Position? {
        println("findAttackPos $position")
        val orientations = piece.effectiveOrientations()
        var attackPos: Position? = null
        var lastIndexOfAttackPos = -1

        orientations.forEach { v ->
            //一歩手前を逆算
            val pos = moveWithOrientation(v, position, -1)
            val lastIndexOfPos = move.routeStack.lastIndexOf(pos)
            if (lastIndexOfPos > lastIndexOfAttackPos) {
                lastIndexOfAttackPos = lastIndexOfPos
                attackPos = pos
            }
        }
        return attackPos ?: findAttackablePos(piece, position)
    }

    /**
     * 対象の位置へ攻撃できる場所を探す。移動経路の逆算はよく考えたら必要ないな
     */
    private fun <T1> findAttackablePos(piece: Piece<T1, GROUND>, position: Position): Position? {
        println("findAttackPos $position")
        val orientations = piece.effectiveOrientations()
        val attackableOrientation = orientations.find { v ->
            val pos = moveWithOrientation(v, position, -1)
            piece.searchedRoute[pos.x][pos.y] > -1
        }
        return if (attackableOrientation != null) moveWithOrientation(attackableOrientation, position, -1) else null
    }

    /**
     * 盤上から駒を取り除く.とりあえず駒と場所が一致しているか判定するか？どちらかだけでいいことにするか？
     */
    fun removePiece(piece: Piece<*, *>, position: Position) {
        pieceMatrix[position.x][position.y] = null
        //駒を取り除くときに駒側からアクションするか？不要な気がしてきた
        piece.action(Piece.ActionPhase.REMOVED)
        //死んだときにやることが出来たら追加しないとな
//        piece.uiPiece.actor.remove()
    }

}
