package jp.blogspot.turanukimaru.fehs

/**
 * キャラを格納する場所。デフォルトキャラをDBとは別に管理してみたかったのでこんな構成になっているが
 * 正直DBに初期値を放り込むのでよいと思う。
 */
object BattleUnitRepository {
    var repo: ModelObjectRepository<ArmedClass>? = null

    fun getById(id: String): ArmedClass? = if (StandardBattleClass.containsKey(id)) ArmedClass(StandardBattleClass.get(id)!!) else repo!!.getById(id)
    fun allItems(includeDb: Boolean = false): List<ArmedClass> = if (includeDb) StandardBattleClass.allItems().map { e -> ArmedClass(e) }.union(repo!!.allItems()).toList() else StandardBattleClass.allItems().map { e -> ArmedClass(e) }
    fun isStandardBattleClass(id: String): Boolean = StandardBattleClass.containsKey(id)
    fun createItem(battleClass: ArmedClass) {
        repo!!.createOrUpdate(battleClass)

    }
}