package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.playboard.PhysicalBoard

/**
 * キャラを格納する場所。デフォルトキャラをDBとは別に管理してみたかったのでこんな構成になっているが
 * 正直DBに初期値を放り込むのでよいと思う。
 */
object BattleFieldRepository {
    var repo: ModelObjectRepository<PhysicalBoard<MyPiece, Tile>>? = null

    fun getById(id: String): PhysicalBoard<MyPiece, Tile>? = repo!!.getById(id)
    fun createItem(field: PhysicalBoard<MyPiece, Tile>) = repo!!.createOrUpdate(field)
    fun create(x: Int, y: Int) = repo!!.create(PhysicalBoard(x, y))
}