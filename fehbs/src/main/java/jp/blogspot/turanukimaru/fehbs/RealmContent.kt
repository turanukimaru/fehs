package jp.blogspot.turanukimaru.fehbs

import jp.blogspot.turanukimaru.fehs.ModelObjectRepository

/**
 * 複数のテーブルに共通して使える処理…例えばクエリの作成とかが書けそうなのだがまだテーブルは一つしかないし早すぎたか？
 */
open class RealmContent<T> : ModelObjectRepository<T> {
    override fun allItems(): List<T> {
        return listOf()
    }

    override fun getById(id: String): T? =null

    override fun find(item: T): T? {
        return null
    }

    override fun createOrUpdate(item: T): T {
        return item
    }

    override fun delete(item: T): Int {
        return 0
    }

    override fun deleteById(id: String): Int {
        return 0
    }

    override fun complexQuery(item: T): List<T> {
        return listOf()
    }

// TODO:where(S::class.java)さえ綺麗に掛ければかなり共通化できるのだが・・・
//    /**
//     * 条件付き検索
//     */
//    private fun complexQuery(day: String? = null): List<T> {
//        var status = "\n\nPerforming complex Query operation..."
//
//        // Realm implements the Closable interface, therefore we can make use of Kotlin's built-in
//        // extension method 'use' (pun intended).
//        Realm.getDefaultInstance().use {
//            // 'it' is the implicit lambda parameter of type Realm
//            status += "\nNumber of Sakes: ${it.where(S::class.java).count()}"
//
//            // Find all Sakes where age between 7 and 9 and name begins with "Sake".
//            val results = it
//                    .where(Sake::class.java)
//                    .run { day?.let { this.equalTo("date", day) } ?: this }
//                    .findAll()
//
//            status += "\nSize of result set: ${results.size}"
//
//            //useまたはtransaction外でカラムを読みだすとエラーになるのでコピーして使う。クローズしてからちゃんと読み出せるのかな・・・？
//            val res = ArrayList<Sake>()
//            SakeContent.showStatus("query !")
//            results.map { i ->
//                SakeContent.showStatus("query i")
//                SakeContent.showStatus(i.toString())
//
//                res.add(i.copy())
//            }
//
//            return res
//        }
//
//    }

}