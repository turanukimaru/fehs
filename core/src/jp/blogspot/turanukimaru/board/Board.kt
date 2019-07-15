package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position
import java.util.*

/**
 * 論理盤面.操作系と演算系別にするとかちょっと分割したいな…
 */
class Board<UNIT, GROUND>(val horizontalLines: Int, val verticalLines: Int) {
    /**
     * 盤上の駒
     * 駒が無いところはnull
     */
    private val pieceMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()
    private val copyMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()
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
     * 現在盤上の所有権を持つプレイヤー。NONE作ったほうがいいかな…？
     */
    var owner: Player = Player.None

    var listener: BoardListener? = null
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

    /**
     * -1で埋めたマップのマトリクス。ルートや影響範囲に使う
     */
    private val filledMatrix: MutableList<MutableList<Int>>
        get() {
            val routeMatrix = mutableListOf<MutableList<Int>>()
            horizontalIndexes.forEach {
                val unitLine = mutableListOf<Int>()
                verticalIndexes.forEach { unitLine.add(-1) }
                routeMatrix.add(unitLine)
            }
            return routeMatrix
        }

    init {
        //マップを初期化する
        horizontalIndexes.forEach {
            val unitLine = mutableListOf<Piece<UNIT, GROUND>?>()
            verticalIndexes.forEach { unitLine.add(null) }
            pieceMatrix.add(unitLine)

            val boxLine = mutableListOf<GROUND?>()
            verticalIndexes.forEach { boxLine.add(null) }
            groundMatrix.add(boxLine)
        }
    }

    /**
     * 対象の場所にある駒
     */
    fun pieceAt(position: Position): Piece<UNIT, GROUND>? = if (position.x in 0 until horizontalLines && position.y in 0 until verticalLines) pieceMatrix[position.x][position.y] else null
    /**
     * 対象の場所にある地形
     */
    fun groundAt(position: Position): GROUND? = if (position.x in 0 until horizontalLines && position.y in 0 until verticalLines)  groundMatrix[position.x][position.y] else null

    /**
     * タッチされたときに呼び出される
     */
    fun touch(position: Position) {
        move.touch(position, pieceAt(position)
        )
    }
    /**
     * タッチされたときに呼び出される
     */
    fun drag(position: Position) {
        move.drag(position)
    }

    /**
     * 地形のマトリックスをXY入れ替えてコピーする。視覚的に直感的なMatrixと記述上に直感的なMatrixXYはxyが入れ替わっているので入れ替える
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
    private fun searchUnitPosition(piece: Piece<*, *>): Position? {
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
        if (pieceMatrix[x][y] != null) throw RuntimeException("pieceMatrix[$x][$y] is スクワットのスペルが分からん  by ${pieceMatrix[x][y]}")
        pieceMatrix[x][y] = piece
        pieceList.add(piece)
        piece.putOn(x, y)
    }

    /**
     * 対象の枡に駒を置く。移動元が見つからないときは例外を吐く
     * Actionとの関係を整理したほうが良いな
     */
    fun moveToPosition(piece: Piece<UNIT, GROUND>, position: Position, x: Int = position.x, y: Int = position.y) {
        println("Drag中にここが呼ばれたらまずい")
        if (x < 0 || y < 0 || x >= horizontalLines || y >= verticalLines) {
            throw RuntimeException("out of range pieceMatrix[$x][$y]")
        }
        if (isAnotherPiece(piece, position)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        val oldSquare = searchUnitPosition(piece)!!
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceMatrix[x][y]
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("another pieceAt is at $targetSquaresUnit")
        }
        pieceMatrix[oldSquare.x][oldSquare.y] = null
        pieceMatrix[x][y] = piece
    }

    /**
     * 盤面の座標が盤上に有るか。つまりIndexがマイナスになったり幅を超えたりしていないか
     */
    fun positionIsOnBoard(position: Position): Boolean = position.x >= 0 && position.y >= 0 && position.x < horizontalLines && position.y < verticalLines

    fun updateInfo(updateInfo: (uiBoard: UiBoard) -> Boolean = { _ -> true }, rank: Int = 0) {
        listener?.updateInfo(updateInfo, rank)
    }

    /**
     * 移動可能な経路を調べる。
     */
    fun searchRoute(piece: Piece<UNIT, GROUND>): MutableList<MutableList<Int>> {
        println("searchRoute $piece")
        val routeMatrix = filledMatrix
        val square = searchUnitPosition(piece) ?: throw RuntimeException("ユニットが見つからない")
        val steps = 0
        println("first step at $piece $square $steps ")
        step(piece, square, steps, routeMatrix)
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
        val routeMatrix = filledMatrix
        horizontalIndexes.forEach { x ->
            verticalIndexes.forEach { y ->
                val square = Position(x, y)
                if (piece.searchedRouteAt(square) >= 0 && piece.isStoppable(pieceAt(square))) stepEffect(piece, square, 0, routeMatrix)
            }
        }
        return routeMatrix
    }

    /**
     * 効果範囲探索.一歩進んで再帰するけどこれ再帰しないほうが良い気がしてきた
     */
    private fun stepEffect(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, attackMatrix: MutableList<MutableList<Int>>) {
//        if (steps > 0) {
//            attackMatrix[position.x][position.y] = steps
//        }
        val orientations = piece.effectiveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                //targetStepsが-1のときに終了するという技もあるがどうしよう？
                val targetSteps = piece.countEffectiveStep(targetUnit, targetSquare, v, steps)
                val stepped = attackMatrix[targetPos.x][targetPos.y]

                //対象の前後２枡筒を確認してサポートスキルが発動できるか。ちょっと人には見せられないコードだな！
                val pos1 =  moveWithOrientation(v, targetPos)
                val pos2 =  moveWithOrientation(v, pos1)
                val pos_1 =  moveWithOrientation(v, targetPos,-1)
                val pos_2 =  moveWithOrientation(v, pos_1,-1)
                val p1 = pieceAt(pos1)
                val g1 = groundAt(pos1)
                val p2 = pieceAt(pos2)
                val g2 = groundAt(pos2)
                val p_1 = pieceAt(pos_1)
                val g_1 = groundAt(pos_1)
                val p_2 = pieceAt(pos_2)
                val g_2 = groundAt(pos_2)
                val piecesAndGrounds = PiecesAndGrounds(targetUnit,p1,p2,p_1,p_2,targetSquare,g1,g2,g_1,g_2)
                //一応効果範囲内だったら再帰するようにしてはいるが攻撃範囲は再帰しないほうが良いので削除するかも
                //対象が未検索 || ステップの短いほう優先というアルゴリズムだけどいいのかな？いいか。これ別マトリクスにするか…
                if (piece.isSupportable(piecesAndGrounds, v, steps) && (stepped == -1 || stepped > targetSteps)) {
                    attackMatrix[targetPos.x][targetPos.y] = targetSteps + 128
                }
                if (piece.isEffective(targetUnit, targetSquare, v, steps) && (stepped == -1 || stepped > targetSteps)) {
                    attackMatrix[targetPos.x][targetPos.y] = targetSteps
//                    stepEffect(piece, targetPos, targetSteps, attackMatrix)
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
    fun turnStart(owner: Player) {
        move.moveCancel()
        this.owner = owner
//一度全部の駒を使用不可にしてから手番の人の駒を有効にする
        pieceList.forEach { it.disabled() }
        pieceList.filter { it.owner == owner }.forEach { it.ready() }
    }

    /**
     * ゲーム開始。リトライ用に初期状態を保持する。
     */
    fun gameStart(owner: Player) {
        copyMatrix.clear()
        pieceMatrix.forEach {
            val newLine = mutableListOf<Piece<UNIT, GROUND>?>()
            copyMatrix.add(newLine)
            it.forEach { e -> newLine.add(e) }
        }
        turnStart(owner)
    }

    /**
     * ゲーム開始。リトライ用に初期状態を保持する。
     */
    fun gameReset(owner: Player) {
        println("gameReset")
        move.clear()//deselectPieceでクリアしてるはずなのだが…
        pieceMatrix.clear()
        pieceList.clear()
        copyMatrix.forEachIndexed { x, it ->
            val newLine = mutableListOf<Piece<UNIT, GROUND>?>()
            pieceMatrix.add(newLine)
            it.forEachIndexed { y, e ->
                newLine.add(e)
                if (e != null) {
                    println("$e at x:$x,y:$y")
                    e.existsPosition = Position(x, y)
                    e.reset()
                    pieceList.add(e)
                }
            }
        }
        turnStart(owner)
    }

    /**
     * 対象の枡に自分以外の駒があるときにtrue
     */
    private fun isAnotherPiece(piece: Piece<*, GROUND>, position: Position): Boolean = pieceAt(position) != null && pieceAt(position) != piece

    /**
     * 対象の位置から移動経路をさかのぼり攻撃場所を探す。 経路中に無いときには攻撃可能位置を探す
     */
    fun findActionPos(piece: Piece<UNIT, GROUND>, targetPos: Position, startPos: Position): Position {
        println("findActionPos $targetPos")
        val orientations = piece.effectiveOrientations()
        //攻撃可能位置のリストを作成する
        val actionablePositions = orientations.map { moveWithOrientation(it, targetPos, -1) }.filter { piece.searchedRouteAt(it) > -1 }
        return findRoute(actionablePositions, startPos, piece)
    }

    /**
     * ルート探索だけどこれ Move にあるべきな気がしてきた
     */
    fun findRoute(targetPositions: List<Position>, startPos: Position, piece: Piece<UNIT, GROUND>): Position {
//        println("$targetPositions がおかしいのかな…？")
        //現在値が攻撃可能なら探さなくていい
        if (targetPositions.contains(startPos)) return startPos
        val routeClone = move.routeStack.clone()
        if (routeClone.isEmpty() || routeClone.first != piece.existsPosition) routeClone.addFirst(piece.existsPosition)//routeStack作成時にに現在地だけ入れておきたいなあ
        val digRoute = digStack(routeClone, piece, targetPositions)
        //ここから一歩引いて探索か…移動時に途中の経路を探索するのにも使えるはず
        //ここで途中をスタックに詰める必要がある...なお掘る向きが逆
        val it = digRoute.first()
        while (move.routeStack.contains(it)) {
            move.routeStack.pop()
        }
        digRoute.forEach { move.stackRoute(it) }
        //            moveToPosition(piece, digRoute.last())
        return digRoute.last()
    }

    private fun digStack(stack: ArrayDeque<Position>, piece: Piece<UNIT, GROUND>, actionablePositions: List<Position>): MutableList<Position> {
        println("stack:${stack.last} -> $actionablePositions")
        val last = stack.last
        stack.removeLast()
        val lastStep = piece.searchedRouteAt(last)
        val route = findStep(piece, last, lastStep, actionablePositions, mutableListOf())
        if (route.isNotEmpty() || stack.isEmpty()) {
            println(route)
            route.add(0,last) // どこから始めたのかが分からないためスタックのどこからかってのを返す必要がある
            return route
        }
        return digStack(stack, piece, actionablePositions)
    }

    /**
     * 経路探索中に一歩進んで再帰する
     */
    private fun findStep(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, targetPositions: List<Position>, routeList: MutableList<Position> = mutableListOf()): MutableList<Position> {
        val orientations = piece.orientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                val targetSteps = piece.countStep(targetUnit, targetSquare, v, steps)
//                println("ひょっとして targetPos : $targetPos steps : $steps  targetSteps : $targetSteps & ${piece.isMovable(targetUnit, targetSquare, v, steps)}")
                //移動出来て歩数が増えてなければ。でふぉ-1はやめたほうがいいかな。
                if (piece.isMovable(targetUnit, targetSquare, v, steps)) {
                    routeList.add(targetPos)//泊まれるかのマトリクスが要るか targetPos に複数の情報を入れるか…
//                    println("pre : $routeList")
                    if (targetPositions.contains(targetPos)) {
//                        println("探索終了")
                        return routeList//探索終了
                    } else {
                        findStep(piece, targetPos, targetSteps, targetPositions, routeList)
                        if (targetPositions.contains(routeList.lastOrNull())) {
//                            println("探索終了")
                            return routeList//探索終了
                        }
                        routeList.removeAt(routeList.size - 1)//探索失敗したら消す
                    }
//                    println("deleted : $routeList")
                }
            }
        }
        return routeList
    }

    /**
     * 盤上から駒を取り除く.とりあえず駒と場所が一致しているか判定するか？どちらかだけでいいことにするか？
     */
    fun removePiece(piece: Piece<*, *>, position: Position) {
        println("removePiece $piece $position")
        pieceMatrix[position.x][position.y] = null
        pieceList.remove(piece)
        piece.remove()
    }

    fun clicked(position: Position) {
        move.clicked(position,pieceAt(position))
    }
}
