package jp.blogspot.turanukimaru.fehs

/**
 * キャラを格納する場所。デフォルトキャラをDBとは別に管理してみたかったのでこんな構成になっているが
 * 正直DBに初期値を放り込むのでよいと思う。
 */
object BattleUnitRepository {
    var repo: ModelObjectRepository<BattleClass>? = null

    fun getById(id: String): BattleClass? = if(StandardBattleClass.containsKey(id)) StandardBattleClass.get(id)else repo!!.getById(id)
    fun allItems(includeDb: Boolean = false): MutableList<BattleClass> = if (includeDb) StandardBattleClass.allItems().union(repo!!.allItems()).toMutableList() else StandardBattleClass.allItems()
    fun isStandardBattleClass(id: String): Boolean = StandardBattleClass.containsKey(id)
    fun createItem(battleClass: BattleClass){
        repo!!.createOrUpdate(battleClass)

    }
}