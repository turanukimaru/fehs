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
     * matrix と map を一つのクラスにまとめてガードするべきなんだが…
     */
    private val pieceMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()
    private val positionMap = mutableMapOf<Piece<UNIT, GROUND>, Position>()
    private val copyMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()//Mapのコピーのほうが軽いんだよな。軽い必要ないけど…
    /**
     * 盤上の駒リスト。ターン終了時に全部Disableにするとか     *
     */
    val pieceList get() = positionMap.keys.toList()
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
    private fun groundAt(position: Position): GROUND? = if (position.x in 0 until horizontalLines && position.y in 0 until verticalLines) groundMatrix[position.x][position.y] else null

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
        if (positionMap[piece] != null) return positionMap[piece]
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
    fun put(piece: Piece<UNIT, GROUND>, x: Int, y: Int, orientation: Int = 0) {
        if (pieceMatrix[x][y] != null) throw RuntimeException("pieceMatrix[$x][$y] is スクワットのスペルが分からん  by ${pieceMatrix[x][y]}")
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
        piece.putOn(x, y, orientation)
    }

    /**
     * 対象の枡に駒を置く。移動元が見つからないときは例外を吐く
     * すでに駒があるところへは動かせない（例外をはく）ので先に明示的に取り除くこと。
     */
    fun moveToPosition(piece: Piece<UNIT, GROUND>, position: Position, x: Int = position.x, y: Int = position.y) {
        if (x < 0 || y < 0 || x >= horizontalLines || y >= verticalLines) {
            throw RuntimeException("out of range pieceMatrix[$x][$y]")
        }
//        if (isAnotherPiece(piece, position)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        val oldSquare = positionMap[piece]!!
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceMatrix[x][y]
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("another pieceAt is at $targetSquaresUnit")
        }
        pieceMatrix[oldSquare.x][oldSquare.y] = null
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }

    /**
     * 盤面の座標が盤上に有るか。つまりIndexがマイナスになったり幅を超えたりしていないか
     */
    fun positionIsOnBoard(position: Position): Boolean = position.x >= 0 && position.y >= 0 && position.x < horizontalLines && position.y < verticalLines

    /**
     * 情報枠を更新する。描画関数をリスナに渡してるだけだけど…
     */
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
        step(piece, square, steps, null, routeMatrix)
        return routeMatrix
    }

    /**
     * 経路探索中に一歩進んで再帰する
     */
    private fun step(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, orientation: Int?, routeMatrix: MutableList<MutableList<Int>>) {
        routeMatrix[position.x][position.y] = steps
        val orientations = piece.moveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                val targetSteps = piece.countStep(targetUnit, targetSquare, v, steps)
                val stepped = routeMatrix[targetPos.x][targetPos.y]
                //移動出来て歩数が増えてなければ。
                if (piece.isMovable(targetUnit, targetSquare, v, steps, v == orientation) && (stepped == -1 || stepped > targetSteps)) {
                    step(piece, targetPos, targetSteps, v, routeMatrix)
                }
            }
        }
    }

    /**
     * 効果範囲を探す。
     */
    fun searchActionRoute(piece: Piece<UNIT, GROUND>, existsPos: Position, targetPos: Position): MutableList<MutableList<Int>> {
        println("searchActionRoute $piece")
        //最大／最小射程
        val max = piece.actionRange().first
        val min = piece.actionRange().second
        val routeMatrix = filledMatrix
        horizontalIndexes.forEach { x ->
            verticalIndexes.forEach { y ->
                val square = Position(x, y)
                //まずこの枡が未計算でユニットからの射程内だったらマーク。駒の場所使ってるけど外から供給したいなあ…
                // println("p:$position s:$square r:${position.distance(square)}")
                if (routeMatrix[x][y] == -1 && square.range(existsPos, max, min)) routeMatrix[x][y] = square.distance(piece.existsPosition!!)
                if (piece.searchedRouteAt(square) >= 0 && piece.isStoppable(pieceAt(square))) stepActionRoute(piece, square, 0, routeMatrix)
            }
        }
        return routeMatrix
    }

    /**
     * 効果範囲探索.再帰できるようになってはいるけど今は再帰させてない。射程で計算するとき再帰させようと思ってたけどよく考えたら距離計算できればそれでいいよな…
     */
    private fun stepActionRoute(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, attackMatrix: MutableList<MutableList<Int>>) {
        val orientations = piece.actionOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                //再帰して距離を計算するつもりだったけど射程は再帰要らないよな。1固定でいっか
                val targetSteps = 1
                val stepped = attackMatrix[targetPos.x][targetPos.y]
                val piecesAndGrounds = aroundPiecesAndGrounds(v, targetPos, targetUnit, targetSquare)
                //対象が未検索 || ステップの短いほう優先というアルゴリズムだけどいいのかな？いいか。これ別マトリクスにするか…
                if (piece.isSupportable(piecesAndGrounds, v, steps) && (stepped == -1 || stepped > targetSteps)) {
                    attackMatrix[targetPos.x][targetPos.y] = targetSteps + 128
                }
                if (piece.isEffective(targetUnit, targetSquare, v, steps) && (stepped == -1 || stepped > targetSteps)) {
                    attackMatrix[targetPos.x][targetPos.y] = targetSteps
//                    stepActionRoute(piece, targetPos, targetSteps, attackMatrix)
                }
            }
        }
    }

    /**
     * 対象と前後２枡の枡・駒を出力する。補助スキル判定に使う
     */
    private fun aroundPiecesAndGrounds(v: Int, targetPos: Position, targetUnit: Piece<UNIT, GROUND>? = pieceAt(targetPos), targetSquare: GROUND? = groundAt(targetPos)): PiecesAndGrounds<UNIT, GROUND> {
        //対象の前後２枡筒を確認してサポートスキルが発動できるか。ちょっと人には見せられないコードだな！
        val pos1 = moveWithOrientation(v, targetPos)
        val pos2 = moveWithOrientation(v, pos1)
        val posM1 = moveWithOrientation(v, targetPos, -1)
        val posM2 = moveWithOrientation(v, posM1, -1)
        val p1 = pieceAt(pos1)
        val g1 = groundAt(pos1)
        val p2 = pieceAt(pos2)
        val g2 = groundAt(pos2)
        val pM1 = pieceAt(posM1)
        val gM1 = groundAt(posM1)
        val pM2 = pieceAt(posM2)
        val gM2 = groundAt(posM2)
        return PiecesAndGrounds(targetUnit, p1, p2, pM1, pM2, targetSquare, g1, g2, gM1, gM2)
    }

    /**
     * 移動方向から升目を計算する。真上スタート。
     * 実はx=x(x-4)が正か負か、yは(y-2)(y-6)、2歩も同様に計算できるのだがきっとwhenのが分かりやすい
     */
    private fun moveWithOrientation(v: Int, position: Position, sign: Int = 1): Position {
        return when (v) {
            //1
            0 -> Position(position.x, position.y + 1 * sign)
            1 -> Position(position.x + 1 * sign, position.y + 1 * sign)
            2 -> Position(position.x + 1 * sign, position.y)
            3 -> Position(position.x + 1 * sign, position.y - 1 * sign)
            4 -> Position(position.x, position.y - 1 * sign)
            5 -> Position(position.x - 1 * sign, position.y - 1 * sign)
            6 -> Position(position.x - 1 * sign, position.y)
            7 -> Position(position.x - 1 * sign, position.y + 1 * sign)
            //2
            8 -> Position(position.x, position.y + 2 * sign)
            9 -> Position(position.x + 1 * sign, position.y + 2 * sign)
            10 -> Position(position.x + 2 * sign, position.y + 2 * sign)
            11 -> Position(position.x + 2 * sign, position.y + 1 * sign)
            12 -> Position(position.x + 2 * sign, position.y)
            13 -> Position(position.x + 2 * sign, position.y - 1 * sign)
            14 -> Position(position.x + 2 * sign, position.y - 2 * sign)
            15 -> Position(position.x + 1 * sign, position.y - 2 * sign)
            16 -> Position(position.x, position.y - 2 * sign)
            17 -> Position(position.x - 1 * sign, position.y - 2 * sign)
            18 -> Position(position.x - 2 * sign, position.y - 2 * sign)
            19 -> Position(position.x - 2 * sign, position.y - 1 * sign)
            20 -> Position(position.x - 2 * sign, position.y)
            21 -> Position(position.x - 2 * sign, position.y + 1 * sign)
            22 -> Position(position.x - 2 * sign, position.y + 2 * sign)
            23 -> Position(position.x - 1 * sign, position.y + 2 * sign)
            //3
            24 -> Position(position.x, position.y + 3 * sign)
            25 -> Position(position.x + 1 * sign, position.y + 3 * sign)
            26 -> Position(position.x + 2 * sign, position.y + 3 * sign)
            27 -> Position(position.x + 3 * sign, position.y + 3 * sign)
            28 -> Position(position.x + 3 * sign, position.y + 2 * sign)
            29 -> Position(position.x + 3 * sign, position.y + 1 * sign)
            30 -> Position(position.x + 3 * sign, position.y + 0 * sign)
            31 -> Position(position.x + 3 * sign, position.y - 1 * sign)
            32 -> Position(position.x + 3 * sign, position.y - 2 * sign)
            33 -> Position(position.x + 3 * sign, position.y - 3 * sign)
            34 -> Position(position.x + 2 * sign, position.y - 3 * sign)
            35 -> Position(position.x + 1 * sign, position.y - 3 * sign)
            36 -> Position(position.x + 0 * sign, position.y - 3 * sign)
            37 -> Position(position.x - 1 * sign, position.y - 3 * sign)
            38 -> Position(position.x - 2 * sign, position.y - 3 * sign)
            39 -> Position(position.x - 3 * sign, position.y - 3 * sign)
            40 -> Position(position.x - 3 * sign, position.y - 2 * sign)
            41 -> Position(position.x - 3 * sign, position.y - 1 * sign)
            42 -> Position(position.x - 3 * sign, position.y - 0 * sign)
            43 -> Position(position.x - 3 * sign, position.y + 1 * sign)
            44 -> Position(position.x - 3 * sign, position.y + 2 * sign)
            45 -> Position(position.x - 3 * sign, position.y + 3 * sign)
            46 -> Position(position.x - 2 * sign, position.y + 3 * sign)
            47 -> Position(position.x - 1 * sign, position.y + 3 * sign)
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
        copyMatrix.forEachIndexed { x, it ->
            val newLine = mutableListOf<Piece<UNIT, GROUND>?>()
            pieceMatrix.add(newLine)
            it.forEachIndexed { y, e ->
                newLine.add(e)
                if (e != null) {
                    println("$e at x:$x,y:$y")
                    val p = Position(x, y)
                    e.existsPosition = p
                    positionMap[e] = p
                    e.reset()
                }
            }
        }
        turnStart(owner)
    }

    /**
     * 対象の位置から移動経路をさかのぼり攻撃場所を探す。 経路中に無いときには攻撃可能位置を探す
     * 動かなくても攻撃対象がRange内の時はそこから攻撃できるようにしてみた。パラメータ引き継ぎまくれば移動しつつ長いレンジで攻撃とかもできるけど必要になってからでいいよね
     */
    fun findActionPos(piece: Piece<UNIT, GROUND>, targetPos: Position, startPos: Position): Position? {
        println("findActionPos $targetPos")
        val orientations = piece.actionOrientations()
        //攻撃可能位置のリストを作成する。//盤面・駒に向きがあるとき用
        val actionablePositions = orientations.map { moveWithOrientation(it, targetPos, -1) }.filter { piece.searchedRouteAt(it) > -1 }
        return findActionRoute(targetPos, piece.actionRange(), actionablePositions, startPos, piece)
    }

    /**
     * 対象の位置から移動経路をさかのぼり補助場所を探す。 アクションと別なのは移動が絡むため。
     */
    fun findAssistPos(piece: Piece<UNIT, GROUND>, targetPos: Position, startPos: Position): Position? {
        println("findAssistPos $targetPos")
        val orientations = piece.assistOrientations()
        //補助可能位置のリストを作成する
        val actionablePositions = orientations.filter {
            val p = moveWithOrientation(it, targetPos, -1) //まず候補を選出これ要らないのか…
            val piecesAndGrounds = aroundPiecesAndGrounds(it, targetPos) //対象への向きと前後枡抽出
            println("$p $piecesAndGrounds ${piece.isSupportable(piecesAndGrounds, it, 0)}")
            piece.isSupportable(piecesAndGrounds, it, 0)//補助行動可能か
        }.map {
            moveWithOrientation(it, targetPos, -1)
        }.filter {
            piece.searchedRouteAt(it) > -1
        }

        //えーとこのリストを補助可能判定でフィルタすればいいんだな。range も外から供給せんとな…
        return findActionRoute(targetPos, piece.assistRange(), actionablePositions, startPos, piece)
    }

    /**
     * 攻撃ルート探索
     */
    fun findActionRoute(targetPosition: Position, range: Pair<Int, Int>, targetPositions: List<Position>, startPos: Position, piece: Piece<UNIT, GROUND>): Position? {
//        println("$targetPositions がおかしいのかな…？")
        //現在値が攻撃可能なら探さなくていい
        if (targetPosition.range(startPos, range.first, range.second) || targetPositions.contains(startPos)) return startPos
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
        return digRoute.lastOrNull()
    }

    /**
     * 攻撃ルートスタック堀
     */
    private fun digStack(stack: ArrayDeque<Position>, piece: Piece<UNIT, GROUND>, actionablePositions: List<Position>): MutableList<Position> {
        println("stack:${stack.last} -> $actionablePositions")
        val last = stack.last
        stack.removeLast()
        val lastStep = piece.searchedRouteAt(last)
        val route = findStep(piece, last, lastStep, actionablePositions, null, mutableListOf())
        if (route.isNotEmpty() || stack.isEmpty()) {
//            println(route)
            route.add(0, last) // どこから始めたのかが分からないためスタックのどこからかってのを返す必要がある
            return route
        }
        return digStack(stack, piece, actionablePositions)//一歩戻ってもう一回探索
    }

    /**
     * 経路探索中に一歩進んで再帰する
     */
    private fun findStep(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, targetPositions: List<Position>, orientation: Int?, routeList: MutableList<Position> = mutableListOf()): MutableList<Position> {
        val orientations = piece.moveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = pieceMatrix[targetPos.x][targetPos.y]
                val targetSquare = groundMatrix[targetPos.x][targetPos.y]
                val targetSteps = piece.countStep(targetUnit, targetSquare, v, steps)
                //移動出来て歩数が増えてなければ。でふぉ-1はやめたほうがいいかな。
                if (piece.isMovable(targetUnit, targetSquare, v, steps, v == orientation)) {
                    routeList.add(targetPos)
//                    println("pre : $routeList")
                    if (targetPositions.contains(targetPos)) {
//                        println("探索終了")
                        return routeList//探索終了
                    } else {
                        findStep(piece, targetPos, targetSteps, targetPositions, v, routeList)
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
    fun removePiece(piece: Piece<UNIT, GROUND>, position: Position? = positionMap[piece]) {
        println("removePiece $piece $position")
        if (position == null) return//例外吐くべきかなあ
        pieceMatrix[position.x][position.y] = null
        positionMap.remove(piece)
        piece.remove()
    }

    /**
     * 盤面クリック。駒クリックと区別したいところだが残念ながらドラッグと区別がつかないのだ…！
     */
    fun clicked(position: Position) {
        val target = pieceAt(position)
        if (target != null) move.pieceClicked(position, target) else move.boardClicked(position)
    }

    /**
     * 駒クリック。将来一枡中に複数駒置けるようになった時のために残しておく
     * ...でも piece はタッチ始めた駒と同じだし役に立たない気もするなあ
     */
    fun pieceClicked(position: Position, piece: Piece<*, *>) {
        val target = pieceAt(position)
        println("search piece at ${searchUnitPosition(piece)} / $position ${target == piece}")
        clicked(position)
    }

    /**
     * タッチされたときに呼び出される
     */
    fun touch(position: Position) {
        println("touch at ${pieceAt(position)} / $position")
        move.touch(position, pieceAt(position))
    }

    /**
     * タッチされたときに呼び出される
     */
    fun pieceTouch(position: Position, piece: Piece<*, *>) {
        println("pieceTouch at ${searchUnitPosition(piece)} / $position")
        move.touch(position, pieceAt(position))
    }

    /**
     * タッチされたときに呼び出される
     */
    fun drag(position: Position, dx: Float, dy: Float) {
        move.drag(position, dx, dy)
    }

    fun clickOption(listener: ActionListener) {
        move.optionClicked(listener)
    }
}
