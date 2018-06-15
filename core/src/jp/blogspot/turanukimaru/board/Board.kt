package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position
import java.util.*

/**
 * 論理盤面
 */
class Board<UNIT, GROUND>(val horizontalLines: Int, val verticalLines: Int) {
    /**
     * 盤上の駒
     * 駒が無いところはnull
     */
    val pieceMatrix = arrayListOf<ArrayList<Piece<UNIT, GROUND>?>>()
    /**
     * 盤上の地形
     * 地形が無いところはnull.nullObjectとか床を作るべきか？でも床のないボードゲームのが多いよな
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

    val hand = Hand()
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
            val unitLine = arrayListOf<Piece<UNIT, GROUND>?>()
            verticalIndexes.forEach { unitLine.add(null) }
            pieceMatrix.add(unitLine)

            val boxLine = arrayListOf<GROUND?>()
            verticalIndexes.forEach { boxLine.add(null) }
            groundMatrix.add(boxLine)
        }
    }

    /**
     * 地形のマトリックスをコピーする。視覚的に直感的なMatrixと記述上に直感的なMatrix[x][y]はxyが入れ替わっているので入れ替えてコピーする
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
        if (pieceMatrix[x][y] != null) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        pieceMatrix[x][y] = piece
piece.putOn(x,y)    }

    /**
     * 対象の枡に駒を移動する。自分以外の駒が配置済みだったら例外を吐く
     */
    private fun moveToPosition(piece: Piece<UNIT, GROUND>, x: Int, y: Int) {
        if (isAnotherPiece(piece, x, y)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        println("moveToPosition $piece $x $y")
        setToPositionWithoutAction(piece, x, y)
//        piece.uiPiece.actor.clearActions()
//        piece.uiPiece.actor.addAction(uiBoard.actionMoveToPosition(piece.uiPiece, x, y))
    }

    /**
     * 対象の枡に駒を置く。移動元が見つからないときは例外を吐く
     * Actionとの関係を整理したほうが良いな
     */
    private fun setPiece(piece: Piece<UNIT, GROUND>, x: Int, y: Int) {
        val oldSquare = searchUnitPosition(piece)!!
        //移動範囲外は旧枡に戻す.キャンセルはやりすぎかなあ
        if (x < 0 || y < 0 || x >= horizontalLines || y >= verticalLines || searchedRoute[x][y] < 0) {
//            piece.uiPiece.actor.actions.clear()
//            piece.uiPiece.actor.addAction(uiBoard.actionMoveToPosition(piece.uiPiece, oldSquare.x, oldSquare.y))
            return
        }
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceMatrix[x][y]
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("another piece $targetSquaresUnit")
        }
        pieceMatrix[oldSquare.x][oldSquare.y] = null
        pieceMatrix[x][y] = piece
    }

    fun moveToPosition(piece: Piece<UNIT, GROUND>, position: Position) {
        moveToPosition(piece, position.x, position.y)
    }

    fun setToPositionWithoutAction(piece: Piece<UNIT, GROUND>, x: Int, y: Int) {
//        uiBoard.setToPosition(piece.uiPiece, x, y)
        setPiece(piece, x, y)
    }

    fun setToPosition(piece: Piece<UNIT, GROUND>, position: Position) {
        setToPositionWithoutAction(piece, position.x, position.y)
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
    fun searchRoute(piece: Piece<UNIT, GROUND>): ArrayList<ArrayList<Int>> {
        println("searchRoute $piece")
        val routeMatrix = arrayListOf<ArrayList<Int>>()
        horizontalIndexes.forEach {
            val unitLine = arrayListOf<Int>()
            verticalIndexes.forEach { unitLine.add(-1) }
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
    private fun step(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, routeMatrix: ArrayList<ArrayList<Int>>) {
        routeMatrix[position.x][position.y] = steps
        val orientations = piece.orientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
            //枠内
            if (targetPos.x >= 0 && targetPos.x < horizontalLines && targetPos.y >= 0 && targetPos.y < verticalLines) {
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
    fun searchEffectiveRoute(piece: Piece<UNIT, GROUND>): ArrayList<ArrayList<Int>> {
        println("searchEffectiveRoute $piece")
        val routeMatrix = arrayListOf<ArrayList<Int>>()
        horizontalIndexes.forEach {
            val unitLine = arrayListOf<Int>()
            verticalIndexes.forEach { unitLine.add(-1) }
            routeMatrix.add(unitLine)

        }
        horizontalIndexes.forEach { x ->
            verticalIndexes.forEach { y ->
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
    private fun stepEffect(piece: Piece<UNIT, GROUND>, position: Position, steps: Int, routeMatrix: ArrayList<ArrayList<Int>>) {
        if (steps > 0) {
            routeMatrix[position.x][position.y] = steps
        }
        val orientations = piece.effectiveOrientations()
        orientations.forEach { v ->
            val targetPos = moveWithOrientation(v, position)
//            println("effect to $targetPos")
            //枠内
            if (targetPos.x >= 0 && targetPos.x < horizontalLines && targetPos.y >= 0 && targetPos.y < verticalLines) {
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
     * クリック時の動作だけどtouchDown/touchUpが同じオブジェクトの時には常に起動するので画面全体を覆うときは実質touchUp
     */
    fun clicked(position: Position) {
        //何も選択してないときは何もしない。ドラッグを選択に含めるかは難しいよなぁ
        if(!hand.select) return
        //盤外/移動範囲外/効果範囲外は最初に戻す。状態は関係なし
        if (!positionIsOnBoard(position) || (searchedRoute[position.x][position.y] < 0 && effectiveRoute[position.x][position.y] < 0)) {
            moveCancel()
            updateInfo = { _ -> true }
            return
        }
        //ドラッグ終了時には攻撃Or移動

        if (hand.dragging()) {


            //ドラッグでないときはそこへ移動・行動。底に何があるかは枡経由で見るべきだな
        } else {
                hand.selectedPiece!!.boardClicked(hand, position)
        }
//hand.selectedPiece?.boardClicked(hand, position)
    }

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
        hand.clear()
    }

    /**
     * 駒を選択状態にする。掴むとかそういう名前のほうが良いか？
     */
    fun selectPiece(piece: Piece<UNIT, GROUND>) {
        println("selectPiece $piece")
        hand.clear()
        searchRoute(piece)
        searchEffectiveRoute(piece)
        hand.selectedPiece = piece
        hand.oldPosition = searchUnitPosition(piece)!!
    }

    /**
     * 選択した駒の移動をキャンセルして非選択にする。選択してる駒の状態も変化させる
     */
    fun moveCancel() {
        println("moveCancel")
        hand.selectedPiece?.actionPhase = Piece.ActionPhase.READY
//        if (selectedPiece != null) {
//            moveToPosition(selectedPiece!!, oldPosition!!)
//        }
        hand.clear()
    }

    /**
     * プレイヤー。盤の所有者に使う
     */
    class Player {
        val pieceList = arrayListOf<Piece<*, *>>()
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
            val lastIndexOfPos = hand.routeStack.lastIndexOf(pos)
            if (lastIndexOfPos > lastIndexOfAttackPos) {
                lastIndexOfAttackPos = lastIndexOfPos
                attackPos = pos
            }
        }
        //もしnullだったら経路逆算しなきゃな・・・
        return attackPos
    }

    /**
     * 盤上から駒を取り除く.とりあえず駒と場所が一致しているか判定するか？どちらかだけでいいことにするか？
     */
    fun removePiece(piece: Piece<*, *>, position: Position) {
        pieceMatrix[position.x][position.y] = null
        //死んだときにやることが出来たら追加しないとな
//        piece.uiPiece.actor.remove()
    }

    fun stackRoute(position: UiBoard.Position){
      if(searchedRoute[position.x][position.y] > 0){  hand.stackRoute(position)}else {hand.routeOut()}
    }

}
