package jp.blogspot.turanukimaru.board
//雑多なクラスやEnum

/**
 * プレイヤー。盤の所有者に使う
 */
class Player {
    val pieceList = mutableListOf<Piece<*, *>>()
    companion object {
        val None = Player()
    }
}

