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
    fun tileAt(position: Position): TILE? = tileAt(position.x, position.y)
    fun tileAt(x: Int, y: Int): TILE? = if (x in horizontalIndexes && y in verticalIndexes) tileMatrix[x][y] else null

    /**
     * 対象の場所にある駒
     */
    fun pieceAt(position: Position): Piece<P, TILE>? = if (position.x in horizontalIndexes && position.y in verticalIndexes) pieceAt(position.x, position.y) else null

    /**
     * 盤面の座標が盤上に有るか。つまりIndexがマイナスになったり幅を超えたりしていないか
     */
    fun positionIsOnBoard(position: Position): Boolean = positionIsOnBoard(position.x, position.y)
    fun positionIsOnBoard(x: Int, y: Int): Boolean = x in horizontalIndexes && y in verticalIndexes


    open fun positionOf(piece: Piece<P, TILE>) = positionMap[piece]
    open fun pieceAt(x: Int, y: Int): Piece<P, TILE>? = pieceMatrix[x][y]

    /**
     * 対象の枡に駒を置く。駒が配置済みだったら例外を吐く。同じ駒を2回Putとか自分がすでにいるところにPutしたケースも例外を吐くべきか？でも後にするか…
     */
    fun put(piece: Piece<P, TILE>, x: Int, y: Int, orientation: Int = 0) {
        if (!positionIsOnBoard(x, y) || pieceMatrix[x][y] != null) throw RuntimeException("pieceMatrix[$x][$y] is スクワットのスペルが分からん  by ${pieceMatrix[x][y]}")
        localPut(piece, x, y)
        piece.putOn(x, y, orientation)
    }

    /**
     * 集約に駒を追加する。例えば永続化するときはここを Override することになる。
     */
    protected open fun localPut(piece: Piece<P, TILE>, x: Int, y: Int, orientation: Int = 0) {
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }

    protected open fun localMove(piece: Piece<P, TILE>, x: Int, y: Int, oldX: Int, oldY: Int, orientation: Int = 0) {
        pieceMatrix[oldX][oldY] = null
        pieceMatrix[x][y] = piece
        positionMap[piece] = Position(x, y)
    }

    /**
     * 対象の駒を対象の枡に移動する。移動元が見つからないときは例外を吐く
     * すでに駒があるところへは動かせない（例外をはく）ので先に明示的に取り除くこと。
     * 自分のいるところへ移動するのはアリにすべきだよな…
     */
    fun move(piece: Piece<P, TILE>, position: Position, x: Int = position.x, y: Int = position.y) {
        if (!positionIsOnBoard(position)) throw RuntimeException("pieceMatrix[$x][$y] is スクワットのスペルが分からん  by ${pieceMatrix[x][y]}")
        //移動元を消して今回の駒をセット
        val targetSquaresUnit = pieceAt(position)
        if (targetSquaresUnit != null && targetSquaresUnit != piece) {
            throw RuntimeException("found another piece at $targetSquaresUnit")
        }
        val oldSquare = positionOf(piece)!!
        piece.boardMoveCommit(position)
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
     * サイズが違うときはエラーを吐くべきかなあ
     */
    fun copyTilesSwitchXY(matrix: Array<Array<TILE?>>) {
        tileMatrix.clear()
        horizontalIndexes.forEach { x ->
            val line = mutableListOf<TILE?>()
            verticalIndexes.forEach { y -> line.add(matrix[verticalIndexes.last - y][x]) }
            tileMatrix.add(line)
        }
    }

    /**
     * 永続化からロードして駒をセットする。永続化していなければ何も起きない
     */
    open fun load(board: Board<P, TILE>, matrix: MutableList<MutableList<Piece<P, TILE>?>> = pieceMatrix, map: MutableMap<Piece<P, TILE>, Position> = positionMap) {
    }

    /**
     * 盤面を永続化せずにバックアップする。ゲーム開始時とかに使う
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
     * backup から戻す
     */
    fun restore() {
        pieceMatrix.clear()
        copyMatrix.forEachIndexed { x, it ->
            val newLine = mutableListOf<Piece<P, TILE>?>()
            pieceMatrix.add(newLine)
            it.forEachIndexed { y, e ->
                newLine.add(e)
                if (e != null) {
                    val p = Position(x, y)
                    e.existsPosition = p
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
