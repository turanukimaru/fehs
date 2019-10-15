package jp.blogspot.turanukimaru.playboard

/**
 * 自然な盤と駒の配置。物理的な、ではなく「駒を動かしたときは動かす元と動かす先がある」ようなアリストテレス的運動を実現する
 * 例えば、MatrixではなくHexを使う盤や枡内に複数の駒を置ける盤なども考えられ、そのときはこのクラスと同格のクラスとして実装することになる。
 * 駒が無いところはnull
 */
class PhysicBoard<UNIT, GROUND>(private val horizontalIndexes: IntRange, private val verticalIndexes: IntRange) {
    private val pieceMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()
    private val positionMap = mutableMapOf<Piece<UNIT, GROUND>, Position>()
    private val copyMatrix = mutableListOf<MutableList<Piece<UNIT, GROUND>?>>()
    /**
     * 盤上の地形
     * 地形が無いところはnull.nullObjectとか床を作るべきか？でも床のないボードゲームのが多いよな
     */
    private val groundMatrix = mutableListOf<MutableList<GROUND?>>()
    /**
     * 盤上の駒リスト。ターン終了時に全部Disableにするとか     *
     */
    val pieceList get() = positionMap.keys.toList()

    /**
     * 対象の場所にある地形
     */
    fun groundAt(position: Position): GROUND? = if (position.x in horizontalIndexes && position.y in verticalIndexes) groundMatrix[position.x][position.y] else null

    /**
     * 対象の場所にある駒
     */
    fun pieceAt(position: Position): Piece<UNIT, GROUND>? = if (position.x in horizontalIndexes && position.y in verticalIndexes) pieceMatrix[position.x][position.y] else null

    fun positionOf(piece: Piece<UNIT, GROUND>) = positionMap[piece]
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
     * 対象の駒を対象の枡に移動する。移動元が見つからないときは例外を吐く
     * すでに駒があるところへは動かせない（例外をはく）ので先に明示的に取り除くこと。
     */
    fun move(piece: Piece<UNIT, GROUND>, position: Position, x: Int = position.x, y: Int = position.y) {
        if (x !in horizontalIndexes || y !in verticalIndexes) {
            throw RuntimeException("out of range pieceMatrix[$x][$y]")
        }
//        if (isAnotherPiece(piece, position)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        val oldSquare = positionMap[piece]!!
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceMatrix[x][y]
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("found another piece at $targetSquaresUnit")
        }
        pieceMatrix[oldSquare.x][oldSquare.y] = null
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }

    /**
     * 盤上から駒を取り除く.とりあえず駒と場所が一致しているか判定するか？どちらかだけでいいことにするか？
     */
    fun remove(piece: Piece<UNIT, GROUND>, position: Position? = positionMap[piece]) {
        println("remove $piece $position")
        if (position == null) return//例外吐くべきかなあ
        pieceMatrix[position.x][position.y] = null
        positionMap.remove(piece)
        piece.remove()
    }

    /**
     * 地形のマトリックスをXY入れ替えてコピーする。視覚的に直感的なMatrixと記述上に直感的なMatrixXYはxyが入れ替わっているので入れ替える
     * yが上下逆なのはどうすっかなあ。
     */
    fun copyGroundSwitchXY(matrix: Array<Array<GROUND>>) {
        groundMatrix.forEach { e -> e.clear() }
        groundMatrix.forEachIndexed { x, line ->
            verticalIndexes.forEach { y -> line.add(matrix[verticalIndexes.last - y][x]) }
        }
    }

    /**
     * 盤面をバックアップする。ゲーム開始時とかに使う
     */
    fun backup() {
        copyMatrix.clear()
        pieceMatrix.forEach {
            val newLine = mutableListOf<Piece<UNIT, GROUND>?>()
            copyMatrix.add(newLine)
            it.forEach { e -> newLine.add(e) }
        }

    }

    /**
     * backup から戻すのってなんて言うんだっけ…？
     */
    fun reset() {
        pieceMatrix.clear()
        copyMatrix.forEachIndexed { x, it ->
            val newLine = mutableListOf<Piece<UNIT, GROUND>?>()
            pieceMatrix.add(newLine)
            it.forEachIndexed { y, e ->
                newLine.add(e)
                if (e != null) {
                    println("$e at x:$x,y:$y")
                    val p = Position(x, y)
                    e.existsPosition = Positioning(p, 0)//ああ向きが戻ってねえ…
                    positionMap[e] = p
                    e.reset()
                }
            }
        }
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

//    /**
//     * 駒の位置を探す。見つからなかったらnullを返すようにしてるけど例外を投げるべきか？何らかの原因であるべき駒が無いんだから。
//     */
//    private fun searchUnitPosition(piece: Piece<*, *>): Position? {
//        if (positionMap[piece] != null) return positionMap[piece]
//        println("searchUnitPosition $piece")
//        horizontalIndexes.forEach { x ->
//            verticalIndexes.forEach { y ->
//                if (pieceMatrix[x][y] == piece) {
//                    return Position(x, y)
//                }
//            }
//        }
//        return null
//    }
}
