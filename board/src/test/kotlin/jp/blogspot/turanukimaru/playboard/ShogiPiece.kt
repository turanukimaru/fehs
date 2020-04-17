package jp.blogspot.turanukimaru.playboard

/**
 * 将棋の駒みたいな。テスト用なので動きはおかしい
 */
class ShogiPiece(containUnit: ShogiUnit, board: Board<ShogiUnit, DummyTile>, owner: Player) : Piece<ShogiUnit, DummyTile>(containUnit, board, owner) {
    //相手の枡ではとまれるが自分の枡では泊まれない。最後の条件は自分がいる＝動かない。
    override fun isStoppable(piece: Piece<ShogiUnit, DummyTile>?): Boolean = piece == null || piece.owner != this.owner || piece == this

    override fun isMovable(piece: Piece<ShogiUnit, DummyTile>?, tile: DummyTile?, orientation: Int, payed: Int, ahead: Boolean, rotated: Int): Boolean =//向きは正しいか && 突入できるか && 直線移動が許されている方向か
            contains.orientations.contains(rotated) && (piece == null || piece.owner != owner) && (payed == 0 || (contains.recursiveOrientations.contains(rotated) && ahead && payed < 128))

    //将棋にアクションはないがテスト用に右にだけアクションを取れることにする
    override fun isActionable(piece: Piece<ShogiUnit, DummyTile>?, tile: DummyTile?, orientation: Int, payed: Int, rotated: Int): Boolean = orientation == 2

    //将棋にサポートはない
    override fun isSupportable(tiles: PiecesAndTiles<ShogiUnit, DummyTile>, orientation: Int, payed: Int, rotated: Int): Boolean = false

    //一歩進む。相手がいたら+128とかにすれば相手で止まれるか？
    override fun stepCost(piece: Piece<ShogiUnit, DummyTile>?, tile: DummyTile?, orientation: Int, payed: Int, rotated: Int): Int = if (piece == null) payed + 1 else 128

    //将棋にアクションはないがテスト用に左右に有る事にする…
    override fun actionOrientations(): Array<Int> = arrayOf(2, 6)

    //将棋にアクションはない
    override fun actionRange(): Pair<Int, Int> = Pair(0, 0)

    //今回のテストにはない
    override fun touched(): Boolean = true

    //これはもう親からも消したほうがいい気がしてきた…
    override fun dragged(position: Position): Boolean = true

    //行動結果表示。ドラッグと同じ
    override fun boardAction(source: Position, target: Position, targetPiece: Piece<ShogiUnit, DummyTile>): Boolean = true

    //行動。相手がいるかは判定済み。向きをここに入れるかはちょっと難しいな…でも入れるしかないか。補助と言っても回復もあるんだしな
    override fun boardActionCommit(source: Position, target: Position, targetPiece: Piece<ShogiUnit, DummyTile>): Boolean = true

    /**
     * 移動可能方向
     */
    override fun moveOrientations(): Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7)//Int Range は Array にならない…

    /**
     * 成る。containUnit を var にすると　と金が持ち駒になったときに金扱いされてよくない。
     * 対象があるかないかで分けるべきかなあ
     */
    override fun opt(actionTargetPiece: Piece<ShogiUnit, DummyTile>?, from: Position, actionTargetPos: Position) {
        if (contains.promotion != null) contains.promotion = ShogiUnit.Kin
        if (actionTargetPiece == null) return
        board.physics.remove(actionTargetPiece)
        boardMoveCommitAction(actionTargetPos)
        owner.takePiece(actionTargetPiece)
    }


    override fun toString(): String = contains.name
}

/**
 * 将棋の駒が持つ能力
 */
enum class ShogiUnit(val title: String, val orientations: List<Int>, val recursiveOrientations: List<Int>, var promotion: ShogiUnit?) {
    //あれ竜王と竜馬がうまく処理できないなこれ…まぁ継承でなんとかなるか
    Fu("歩", listOf(0), listOf(), null) {
    }
    ,
    Kyosya("香車", listOf(0), listOf(), null)
    ,
    Keima("桂馬", listOf(9, 23), listOf(), null)
    ,
    Gin("銀", listOf(0, 1, 3, 5, 7), listOf(), null)
    ,
    Kin("金", listOf(0, 1, 2, 4, 6, 7), listOf(), null)
    ,
    Kaku("角", listOf(1, 3, 5, 7), listOf(1, 3, 5, 7), null)
    ,
    Hisya("飛車", listOf(0, 2, 4, 6), listOf(0, 2, 4, 6), null)
    ,
    Gyoku("玉", listOf(0, 1, 2, 3, 4, 5, 6, 7), listOf(), null)
}

