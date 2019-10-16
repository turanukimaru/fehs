package jp.blogspot.turanukimaru.playboard

import java.util.*

/**
 * 論理盤面。手の保持やルート計算がメイン。盤面の操作は physic に対して行える
 */
class Board<UNIT, GROUND>(val horizontalLines: Int, val verticalLines: Int) {
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
     * 操作対象としての盤
     */
    val physic = PhysicBoard<UNIT, GROUND>(horizontalIndexes, verticalIndexes)

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

    /**
     * 盤面の座標が盤上に有るか。つまりIndexがマイナスになったり幅を超えたりしていないか
     */
    fun positionIsOnBoard(position: Position): Boolean = position.x in horizontalIndexes && position.y in verticalIndexes

    /**
     * 移動可能な経路を調べる。
     */
    fun searchRoute(piece: Piece<UNIT, GROUND>): MutableList<MutableList<Int>> {
        println("searchRoute $piece")
        val routeMatrix = filledMatrix
        val square = physic.positionOf(piece) ?: throw RuntimeException("ユニットが見つからない")
        val steps = 0
        println("first step at $piece $square $steps ")
        step(piece, square, steps, null, routeMatrix)
        return routeMatrix
    }

    /**
     * 経路探索
     * 一歩進んで再帰する
     */
    private fun step(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, orientation: Int?, routeMatrix: MutableList<MutableList<Int>>) {
        routeMatrix[position.x][position.y] = steps
        val orientations = piece.moveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = physic.pieceAt(targetPos)
                val targetSquare = physic.groundAt(targetPos)
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
                if (routeMatrix[x][y] == -1 && square.range(existsPos, max, min)) routeMatrix[x][y] = square.distance(piece.existsPosition.p)
                if (piece.searchedRouteAt(square) >= 0 && piece.isStoppable(physic.pieceAt(square))) stepActionRoute(piece, square, 0, routeMatrix)
            }
        }
        return routeMatrix
    }

    /**
     * 効果範囲探索.
     * 再帰できるようになってはいるけど今は再帰させてない。射程で計算するとき再帰させようと思ってたけどよく考えたら距離計算できればそれでいいよな…
     */
    private fun stepActionRoute(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, attackMatrix: MutableList<MutableList<Int>>) {
        val orientations = piece.actionOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = physic.pieceAt(targetPos)
                val targetSquare = physic.groundAt(targetPos)
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
    private fun aroundPiecesAndGrounds(v: Int, targetPos: Position, targetUnit: Piece<UNIT, GROUND>? = physic.pieceAt(targetPos), targetSquare: GROUND? = physic.groundAt(targetPos)): PiecesAndGrounds<UNIT, GROUND> {
        //対象の前後２枡計４枡を確認してサポートスキルが発動できるか。ちょっと人には見せられないコードだな！
        val pos1 = moveWithOrientation(v, targetPos)
        val pos2 = moveWithOrientation(v, pos1)
        val posM1 = moveWithOrientation(v, targetPos, -1)
        val posM2 = moveWithOrientation(v, posM1, -1)
        val p1 = physic.pieceAt(pos1)
        val g1 = physic.groundAt(pos1)
        val p2 = physic.pieceAt(pos2)
        val g2 = physic.groundAt(pos2)
        val pM1 = physic.pieceAt(posM1)
        val gM1 = physic.groundAt(posM1)
        val pM2 = physic.pieceAt(posM2)
        val gM2 = physic.groundAt(posM2)
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
        physic.pieceList.forEach { it.disabled() }
        physic.pieceList.filter { it.owner == owner }.forEach { it.ready() }
    }

    /**
     * ゲーム開始。リトライ用に初期状態を保持する。
     */
    fun gameStart(owner: Player) {
        physic.backup()
        turnStart(owner)
    }

    /**
     * リトライ
     */
    fun gameReset(owner: Player) {
        println("gameReset")
        move.clear()//deselectPieceでクリアしてるはずなのだが…
        physic.reset()
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
        if (routeClone.isEmpty() || routeClone.first != piece.existsPosition.p) routeClone.addFirst(piece.existsPosition.p)//routeStack作成時にに現在地だけ入れておきたいなあ
        val digRoute = digActionStack(routeClone, piece, targetPositions)
        //ここから一歩引いて探索か…移動時に途中の経路を探索するのにも使えるはず
        //ここで途中をスタックに詰める必要がある...なお掘る向きが逆
        val it = digRoute.first()
        while (move.routeStack.contains(it)) {
            move.routeStack.pop()
        }
        digRoute.forEach { move.stackRoute(it) }
        //            move(piece, digRoute.last())
        return digRoute.lastOrNull()
    }

    /**
     * 攻撃ルートスタック堀
     */
    private fun digActionStack(stack: ArrayDeque<Position>, piece: Piece<UNIT, GROUND>, actionablePositions: List<Position>): MutableList<Position> {
        println("stack:${stack.last} -> $actionablePositions")
        val last = stack.last
        stack.removeLast()
        val lastStep = piece.searchedRouteAt(last)
        val route = findActionStep(piece, last, lastStep, actionablePositions, null, mutableListOf())
        if (route.isNotEmpty() || stack.isEmpty()) {
//            println(route)
            route.add(0, last) // どこから始めたのかが分からないためスタックのどこからかってのを返す必要がある
            return route
        }
        return digActionStack(stack, piece, actionablePositions)//一歩戻ってもう一回探索
    }

    /**
     * 経路探索中に一歩進んで再帰する。なんかバグがあるんだけど分からないしスタートから再計算するだけなので後回し
     */
    private fun findActionStep(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, targetPositions: List<Position>, orientation: Int?, routeList: MutableList<Position> = mutableListOf()): MutableList<Position> {
        val orientations = piece.moveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x in 0 until horizontalLines && targetPos.y in 0 until verticalLines) {
                val targetUnit = physic.pieceAt(targetPos)
                val targetSquare = physic.groundAt(targetPos)
                val targetSteps = piece.countStep(targetUnit, targetSquare, v, steps)
                //移動出来て歩数が増えてなければ。でふぉ-1はやめたほうがいいかな。
                if (piece.isMovable(targetUnit, targetSquare, v, steps, v == orientation)) {
                    routeList.add(targetPos)
//                    println("pre : $routeList")
                    if (targetPositions.contains(targetPos)) {
//                        println("探索終了")
                        return routeList//探索終了
                    } else {
                        findActionStep(piece, targetPos, targetSteps, targetPositions, v, routeList)
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
     * 盤面クリック。駒クリックと区別したいところだが残念ながらドラッグ時に区別がつかないのだ…！
     */
    fun click(position: Position) {
        val target = physic.pieceAt(position)
        if (target != null) move.pieceClicked(position, target) else move.boardClicked(position)
    }

    /**
     * 駒クリック。将来一枡中に複数駒置けるようになった時のために残しておく
     * ...でも piece はタッチ始めた駒と同じだし役に立たない気もするなあ
     */
    fun pieceClick(position: Position, piece: Piece<*, *>) {
        val target = physic.pieceAt(position)
        click(position)
    }

    /**
     * タッチされたときに呼び出される
     */
    fun touch(position: Position) {
        println("touch at ${physic.pieceAt(position)} / $position")
        move.touch(position, physic.pieceAt(position))
    }

    /**
     * 駒にタッチされたときに呼び出される。現在は駒が増すより大きいときに誤動作すると思われるため機能していない。
     * 駒が増すより小さいとか枡内に複数存在するときは使う機会があるかもしれない。
     */
    fun pieceTouch(position: Position, piece: Piece<*, *>) {
        println("pieceTouch($position, $piece)")
    }

    /**
     * ドラッグ時に呼び出される
     */
    fun drag(position: Position, dx: Float, dy: Float) {
        move.drag(position, dx, dy)
    }

    /**
     * オプションをクリックされたときに呼び出される。
     */
    fun clickOption() {
        move.optionClicked()
    }
}
