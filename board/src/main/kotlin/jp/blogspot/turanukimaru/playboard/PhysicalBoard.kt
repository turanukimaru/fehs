package jp.blogspot.turanukimaru.playboard

/**
 * 自然な盤と駒の配置。物理的な、ではなく「駒を動かしたときは動かす元と動かす先がある」ようなアリストテレス的運動を実現する
 * 例えば、MatrixではなくHexを使う盤や枡内に複数の駒を置ける盤なども考えられ、そのときはこのクラスと同格のクラスとして実装することになる。
 * 駒が無いところはnull
 */
open class PhysicalBoard<P, TILE>(val horizontalLines: Int, val verticalLines: Int, val id: Int = 0) {
    private val pieceMatrix = mutableListOf<MutableList<Piece<P, TILE>?>>()
    private val positionMap = mutableMapOf<Piece<P, TILE>, Position>()
    private val copyMatrix = mutableListOf<MutableList<Piece<P, TILE>?>>()

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
     * 盤上の地形
     * 地形が無いところはnull.nullObjectとか床を作るべきか？でも床のないボードゲームのが多いよな
     */
    private val tileMatrix = mutableListOf<MutableList<TILE?>>()

    /**
     * 盤上の駒リスト。ターン終了時に全部Disableにするとか     *
     */
    val pieceList get() = positionMap.keys.toList()

    /**
     * 対象の場所にある地形
     */
    fun tileAt(position: Position): TILE? = if (position.x in horizontalIndexes && position.y in verticalIndexes) tileMatrix[position.x][position.y] else null

    /**
     * 対象の場所にある駒
     */
    fun pieceAt(position: Position): Piece<P, TILE>? = if (position.x in horizontalIndexes && position.y in verticalIndexes) getPiece(position.x, position.y) else null

    open fun positionOf(piece: Piece<P, TILE>) = positionMap[piece]
    open fun getPiece(x: Int, y: Int): Piece<P, TILE>? = pieceMatrix[x][y]

    /**
     * 対象の枡に駒を置く。駒が配置済みだったら例外を吐く。自分がすでにいるところにPutしたケースはまだけんとうしなくていいか
     */
    fun put(piece: Piece<P, TILE>, x: Int, y: Int, orientation: Int = 0) {
        if (pieceMatrix[x][y] != null) throw RuntimeException("pieceMatrix[$x][$y] is スクワットのスペルが分からん  by ${pieceMatrix[x][y]}")
        localPut(piece, x, y)
        piece.putOn(x, y, orientation)
    }

    //集約としてみたコード...これあんまし良くないな
  open fun localPut(piece: Piece<P, TILE>, x: Int, y: Int, orientation: Int = 0) {
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }

    open fun localMove(piece: Piece<P, TILE>, x: Int, y: Int, oldX: Int? = null, oldY: Int? = null, orientation: Int = 0) {
        println("localMove")
        if (oldX != null && oldY != null) pieceMatrix[oldX][oldY] = null
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }
    open fun commitMove(piece: Piece<P, TILE>, x: Int, y: Int, oldX: Int? = null, oldY: Int? = null, orientation: Int = 0) {
        println("localMove")
        if (oldX != null && oldY != null) pieceMatrix[oldX][oldY] = null
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }
//    open fun findPiece(x: Int, y: Int): Piece<UNIT, TILE>? = positionMap.entries.find { it.value.x == x && it.value.y == y }?.key
    /**
     * 対象の駒を対象の枡に移動する。移動元が見つからないときは例外を吐く
     * すでに駒があるところへは動かせない（例外をはく）ので先に明示的に取り除くこと。
     */
    fun move(piece: Piece<P, TILE>, position: Position, x: Int = position.x, y: Int = position.y) {
        println("move")
        if (x !in horizontalIndexes || y !in verticalIndexes) {
            throw RuntimeException("out of range pieceMatrix[$x][$y]")
        }
//        if (isAnotherPiece(piece, position)) throw RuntimeException("${pieceMatrix[x][y]} is at pieceMatrix[$x][$y]")
        val oldSquare = positionOf(piece)!!
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = getPiece(x, y)
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("found another piece at $targetSquaresUnit")
        }
        localMove(piece, x, y, oldSquare.x, oldSquare.y)
    }

    /**
     * 盤上から駒を取り除く.とりあえず駒と場所が一致しているか判定するか？どちらかだけでいいことにするか？
     */
    fun remove(piece: Piece<P, TILE>, position: Position? = positionMap[piece]) {
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
    fun copyTilesSwitchXY(matrix: Array<Array<TILE>>) {
        tileMatrix.clear()
        println(horizontalIndexes)
        horizontalIndexes.forEach { x ->
            val line = mutableListOf<TILE?>()
            verticalIndexes.forEach { y -> line.add(matrix[verticalIndexes.last - y][x]) }
            tileMatrix.add(line)
        }
    }

    /**
     * 盤面をバックアップする。ゲーム開始時とかに使う
     */
    fun backup() {
        copyMatrix.clear()
        pieceMatrix.forEach {
            val newLine = mutableListOf<Piece<P, TILE>?>()
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
            val newLine = mutableListOf<Piece<P, TILE>?>()
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
        horizontalIndexes.forEach { _ ->
            val unitLine = mutableListOf<Piece<P, TILE>?>()
            verticalIndexes.forEach { _ -> unitLine.add(null) }
            pieceMatrix.add(unitLine)

            val boxLine = mutableListOf<TILE?>()
            verticalIndexes.forEach { _ -> boxLine.add(null) }
            tileMatrix.add(boxLine)
        }
    }

}
