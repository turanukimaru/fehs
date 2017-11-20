package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position
import java.util.*

/**
 * 論理盤面
 */
class Board<GROUND>(val uiBoard: UiBoard) {
    /**
     * 現在盤上で選択されている駒
     */
    var selectedPiece: Piece<*, GROUND>? = null
    /**
     * 盤上の駒
     * 駒が無いところはnull
     */
    val pieceMatrix = arrayListOf<ArrayList<Piece<*, *>?>>()
    /**
     * 盤上の地形
     * 地形が無いところはnull
     */
    val groundMatrix = arrayListOf<ArrayList<GROUND?>>()
    /**
     * 現在選択されている駒の移動可能範囲
     */
    val searchedRoute = arrayListOf<ArrayList<Int>>()
    /**
     * 現在選択されている駒の移動範囲から更に効果を及ぼすことのできる範囲
     */
    val effectiveRoute = arrayListOf<ArrayList<Int>>()
    /**
     * 現在盤上の所有権を持つプレイヤー
     */
    var owner: Player? = null
    /**
     * 選択されている駒が動かされて移動が確定していないときの動かした道筋
     */
    val routeStack = ArrayDeque<Position>()
    /**
     * 駒を移動中に移動元の枡を記録しておく
     */
    var oldPosition: Position? = null

    /**
     * 横の0..last
     * 0..x-1 は 0 until x と書ける。関数にすることはなかったな・・・
     */
    fun horizontalIndexes() = 0 until uiBoard.horizontalLines

    /**
     * 縦の0..last
     */
    fun verticalIndexes() = 0 until uiBoard.verticalLines

    init {
        horizontalIndexes().forEach {
            val unitLine = arrayListOf<Piece<*, *>?>()
            verticalIndexes().forEach { unitLine.add(null) }
            pieceMatrix.add(unitLine)

            val boxLine = arrayListOf<GROUND?>()
            verticalIndexes().forEach { boxLine.add(null) }
            groundMatrix.add(boxLine)
        }
        uiBoard.setBoardListener(this)
    }

    /**
     * 地形のマトリックスをコピーする。視覚的に直感的なMatrixと記述上に直感的なMatrix[x][y]はxyが入れ替わっているので入れ替えてコピーする
     */
    fun copyGroundSwitchXY(matrix: Array<Array<GROUND>>) {
        groundMatrix.forEach { e -> e.clear() }
        groundMatrix.forEachIndexed { x, line ->
            verticalIndexes().forEach { y -> line.add(matrix[uiBoard.verticalLines - 1 - y][x]) }
        }
    }

    /**
     * 駒の位置を探す。見つからなかったらnullを返すようにしてるけど例外を投げるべきか？何らかの原因であるべき駒が無いんだから。
     */
    fun searchUnitPosition(piece: Piece<*, GROUND>): Position? {
        println("searchUnitPosition $piece")
        horizontalIndexes().forEach { x ->
            verticalIndexes().forEach { y ->
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
    fun put(piece: Piece<*, GROUND>, x: Int, y: Int) {
        if (pieceMatrix[x][y] != null) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        pieceMatrix[x][y] = piece
        uiBoard.put(piece.uiPiece, x, y)
    }

    /**
     * 対象の枡に駒を移動する。自分以外の駒が配置済みだったら例外を吐く
     */
    fun moveToPosition(piece: Piece<*, GROUND>, x: Int, y: Int) {
        if (isAnotherPiece(piece, x, y)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        println("moveToPosition $piece $x $y")
        setToPositionWithoutAction(piece, x, y)
        piece.uiPiece.actor.clearActions()
        piece.uiPiece.actor.addAction(uiBoard.actionMoveToPosition(piece.uiPiece, x, y))
    }

    /**
     * 対象の枡に駒を置く。移動元が見つからないときは例外を吐く
     * Actionとの関係を整理したほうが良いな
     */
    fun setPosition(piece: Piece<*, GROUND>, x: Int, y: Int) {
        val oldSquare = searchUnitPosition(piece)!!
        //移動範囲外は旧枡に戻す.キャンセルはやりすぎかなあ
        if (x < 0 || y < 0 || x >= uiBoard.horizontalLines || y >= uiBoard.verticalLines || searchedRoute[x][y] < 0) {
            piece.uiPiece.actor.actions.clear()
            piece.uiPiece.actor.addAction(uiBoard.actionMoveToPosition(piece.uiPiece, oldSquare.x, oldSquare.y))
            return
        }
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceMatrix[x][y]
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("another piece $targetSquaresUnit")
        }
        pieceMatrix[oldSquare!!.x][oldSquare!!.y] = null
        pieceMatrix[x][y] = piece
    }

    fun moveToPosition(piece: Piece<*, GROUND>, position: Position) {
        moveToPosition(piece, position.x, position.y)
    }

    fun setToPositionWithoutAction(piece: Piece<*, GROUND>, x: Int, y: Int) {
        uiBoard.setToPosition(piece.uiPiece, x, y)
        setPosition(piece, x, y)
    }

    fun setToPosition(piece: Piece<*, GROUND>, attackPos: UiBoard.Position) {
        setToPositionWithoutAction(piece, attackPos.x, attackPos.y)
    }

    /**
     * uiBoardから呼ばれて枡の枠線を引く。また、駒のUpdateを起動する
     */
    fun update() {
        if (selectedPiece != null) {
            horizontalIndexes().forEach { x ->
                verticalIndexes().forEach { y ->
                    if (searchedRoute[x][y] != null && searchedRoute[x][y] >= 0) {
                        uiBoard.fillSquare(x, y, UiBoard.FillType.MOVABLE)
                    } else if (effectiveRoute[x][y] != null && effectiveRoute[x][y] >= 0) {
                        uiBoard.fillSquare(x, y, UiBoard.FillType.ATTACKABLE)
                    }
                }
            }
            //ルートから外れたら掘りなおさないとなあ。移動力超えたらか？直線矢印で十分かなあ
            //This inspection reports any declarations that can be destructuredが出たらこう書ける
            routeStack.forEach { (x, y) ->
                uiBoard.fillSquare(x, y, UiBoard.FillType.PASS)
            }
        }
        pieceMatrix.forEach { h -> h.forEach { v -> v?.update() } }
    }

    /**
     * 移動可能な経路を調べる
     */
    fun searchRoute(piece: Piece<*, GROUND>): ArrayList<ArrayList<Int>> {
        println("searchRoute $piece")
        val routeMatrix = arrayListOf<ArrayList<Int>>()
        horizontalIndexes().forEach {
            val unitLine = arrayListOf<Int>()
            verticalIndexes().forEach { unitLine.add(-1) }
            routeMatrix.add(unitLine)
        }
        //nullの時例外を吐きたいならこう
        val square = searchUnitPosition(piece) ?: throw RuntimeException("ユニットが見つからない")
        var steps = 0
        println("first step at $piece $square $steps ")
        step(piece, square, steps, routeMatrix)
        searchedRoute.clear()
        routeMatrix.forEach { v -> searchedRoute.add(v) }
        return routeMatrix
    }

    /**
     * 経路探索中に一歩進んで再帰する
     */
    private fun step(piece: Piece<*, GROUND>, position: Position, steps: Int, routeMatrix: ArrayList<ArrayList<Int>>) {
        routeMatrix[position.x][position.y] = steps
        val orientations = piece.orientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x >= 0 && targetPos.x < uiBoard.horizontalLines && targetPos.y >= 0 && targetPos.y < uiBoard.verticalLines) {
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
    fun searchEffectiveRoute(piece: Piece<*, GROUND>): ArrayList<ArrayList<Int>> {
        println("searchEffectiveRoute $piece")
        val routeMatrix = arrayListOf<ArrayList<Int>>()
        horizontalIndexes().forEach {
            val unitLine = arrayListOf<Int>()
            verticalIndexes().forEach { unitLine.add(-1) }
            routeMatrix.add(unitLine)

        }
        horizontalIndexes().forEach { x ->
            verticalIndexes().forEach { y ->
                val square = Position(x, y)
                //移動範囲から計算。これ移動しないときと処理が区別できるようにしたほうが良いな
                if (searchedRoute[x][y] >= 0) stepEffect(piece, square, 0, routeMatrix)
            }
        }
        effectiveRoute.clear()
        routeMatrix.forEach { v -> effectiveRoute.add(v) }
        return routeMatrix
    }

    /**
     * 効果範囲探索中に一歩進んで再帰するけどこれ再帰しないほうが良い気がしてきた
     */
    private fun stepEffect(piece: Piece<*, GROUND>, position: Position, steps: Int, routeMatrix: ArrayList<ArrayList<Int>>) {
        if (steps > 0) {
            routeMatrix[position.x][position.y] = steps
        }
        val orientations = piece.effectiveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
//            println("effect to $targetPos")
            //枠内
            if (targetPos.x >= 0 && targetPos.x < uiBoard.horizontalLines && targetPos.y >= 0 && targetPos.y < uiBoard.verticalLines) {
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
     * クリック時の動作だけどtouchUpとかぶるので削除予定
     */
//    fun clicked(event: InputEvent, position: Position) {
//        selectedPiece?.boardClicked(event, position)
//    }

    /**
     * ターン開始。盤の所有者をセットして、全ての駒を準備状態にする。動作を変えるときはきっと引数に関数を追加して、その関数を呼ぶのがいいと思う。
     */
    fun turn(owner: Player) {
        println("TODO:ターン移動処理")
        moveCancel()
        this.owner = owner
        owner.pieceList.forEach { p -> p.ready() }
    }

    /**
     * select解除
     */
    fun deselectPiece() {
        println("deselectPiece")
        //移動範囲のリセットってやるべきかなあ？
        selectedPiece = null
        oldPosition = null
        routeStack.clear()

    }

    /**
     * 駒を選択状態にする。掴むとかそういう名前のほうが良いか？
     */
    fun selectPiece(piece: Piece<*, GROUND>) {
        println("selectPiece $piece")
        if (selectedPiece != null) {
            deselectPiece()
        }
        searchRoute(piece)
        searchEffectiveRoute(piece)
        selectedPiece = piece
        oldPosition = searchUnitPosition(piece)!!

    }

    /**
     * 選択した駒の移動をキャンセルして非選択にする。選択してる駒の状態も変化させる
     */
    fun moveCancel() {
        println("moveCancel")
        selectedPiece?.actionPhase = Piece.ActionPhase.READY
        if (selectedPiece != null) {
            moveToPosition(selectedPiece!!, oldPosition!!)
        }
        deselectPiece()
    }

    /**
     * プレイヤー。盤の所有者に使う
     */
    class Player {
        val pieceList = arrayListOf<Piece<*, *>>()
    }

    /**
     * 駒の移動中に、移動経路を記録する
     */
    fun stackRoute(touchedSquare: UiBoard.Position) {
        println("stackRoute $touchedSquare")
        if (routeStack.isEmpty() || routeStack.last != touchedSquare && searchedRoute[touchedSquare.x][touchedSquare.y] >= 0) {
            //ただしスタックに有ったらそこまで戻す
            while (routeStack.contains(touchedSquare)) {
                routeStack.pop()
            }
            routeStack.push(touchedSquare)
        }

    }

    /**
     * 対象の枡に自分以外の駒があるときにtrue
     */
    fun isAnotherPiece(piece: Piece<*, GROUND>, position: Position): Boolean {
        return isAnotherPiece(piece, position.x, position.y)
    }

    /**
     * 対象の枡に自分以外の駒があるときにtrue
     */
    fun isAnotherPiece(piece: Piece<*, GROUND>, x: Int, y: Int): Boolean {
        val target = pieceMatrix[x][y]
        return effectiveRoute[x][y] > 0 && target != null && target != piece
    }

    /**
     * 対象の位置から移動経路をさかのぼり攻撃場所を探す。 TODO:経路中に無いときに適切な経路を逆算
     */
    fun <T1> findAttackPos(piece: Piece<T1, GROUND>, position: Position): Position? {
        println("findAttackPos $position")
        val orientations = piece.effectiveOrientations()
        var attackPos: Position? = null
        var lastIndexOfAttackPos = -1

        orientations.forEach { v ->
            //一歩手前を逆算
            val pos = moveWithOrientation(v, position, -1)
            val lastIndexOfPos = routeStack.lastIndexOf(pos)
            if (lastIndexOfPos > lastIndexOfAttackPos) {
                lastIndexOfAttackPos = lastIndexOfPos
                attackPos = pos
            }
        }
        //もしnullだったら経路逆算しなきゃな・・・
        return attackPos
    }

    /**
     * 盤上から駒を取り除く
     */
    fun removePiece(piece: Piece<*, *>, position: Position) {
        pieceMatrix[position.x][position.y] = null
        //死んだときにやることが出来たら追加しないとな
//        piece.uiPiece.actor.remove()
    }

}
