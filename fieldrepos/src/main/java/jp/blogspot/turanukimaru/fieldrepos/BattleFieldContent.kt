package jp.blogspot.turanukimaru.fieldrepos

import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import jp.blogspot.turanukimaru.fehs.Ground
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.playboard.PhysicalBoard
import kotlin.properties.Delegates

/**
 * 戦闘フィールドのDBアクセス
 */
object BattleFieldContent : RealmContent<PhysicalBoard<MyPiece, Ground>>() {

    private val tag = "BattleFieldContent"
    /**
     * realmのkotlin用ハンドラ
     */

    val realm: Realm by lazy {
        //RealmFieldModule() が無いと、 ~is not part of schema 例外が出ることがある。出ずに使えることもあるからややこしいが、モジュールが複数あるときは危ない
        val realmConfig = RealmConfiguration.Builder().modules(RealmFieldModule(),Realm.getDefaultModule()).deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
        val r = Realm.getDefaultInstance()
        // テスト中はマイグレーションが面倒なので全部削除...Configが対象とするモジュールを間違えると消えないことがある…
        r.executeTransaction {
            r.deleteAll()
        }
        r
    }

    override fun complexQuery(item: PhysicalBoard<MyPiece, Ground>): List<PhysicalBoard<MyPiece, Ground>> {
        return arrayListOf()
    }

    override fun delete(item: PhysicalBoard<MyPiece, Ground>): Int {
        val results = realm.where(RealmBattleField::class.java).equalTo("id", item.id).findAll()
        realm.executeTransaction {
            results.deleteAllFromRealm()
        }
        return results.size
    }

    override fun deleteById(id: String): Int {
        Log.i(tag, "delete $id")
//        println(id)
        val results = realm.where(RealmBattleField::class.java).equalTo("id", id).findAll()
        realm.executeTransaction {
            Log.i(tag, results.toString())
            results.deleteAllFromRealm()
        }
        return results.size
    }


    /**
     * 初期値が一般化できないので初期化用オブジェクトを渡すしかないのか…
     */
    override fun create(initialItem: PhysicalBoard<MyPiece, Ground>): PhysicalBoard<MyPiece, Ground> {
        Log.i("RealmBattleFieldContent", initialItem.toString())
        realm.beginTransaction()
        val last = realm.where(RealmBattleField::class.java).max("id")
        println(last)
        val f = realm.createObject<RealmBattleField>((last?.toInt() ?: 0) + 1)
        println(f)
        val p = realm.createObject<RealmPhysicalBoard>((last?.toInt() ?: 0) + 1)//id は 1:1 だからこれでいいか
        println(p)
        f.horizontalLines = initialItem.horizontalLines
        f.verticalLines = initialItem.verticalLines
        f.name = ""//ボード名はなくていいかな？
        f.realmPhysicalBoard = p
        //ユーザとかも必要になるんだろな…
        realm.commitTransaction()
        println("realm.commitTransaction()")
        return PersistPhysicalBoard(f.horizontalLines, f.verticalLines, p, realm)
    }

    override fun createOrUpdate(item: PhysicalBoard<MyPiece, Ground>): PhysicalBoard<MyPiece, Ground> {
        Log.i("RealmBattleFieldContent", item.toString())
        item.apply {
            realm.executeTransaction {
                item.apply {
                    val newId = item.id
                            ?: realm.where(RealmBattleField::class.java).max("id")?.toInt() ?: 0 + 1
                    val p = realm.createObject<RealmPhysicalBoard>(newId)//id は 1:1 だからこれでいいか
                    realm.copyToRealmOrUpdate(RealmBattleField(newId, horizontalLines, verticalLines, "", p))

                }
            }
        }
        return item
    }

    override fun find(item: PhysicalBoard<MyPiece, Ground>): PhysicalBoard<MyPiece, Ground>? {
        return realm.where(RealmBattleField::class.java).equalTo("id", item.id).findFirst()?.toModelObject(realm)
    }

    override fun allItems(): List<PhysicalBoard<MyPiece, Ground>> {
        return realm.where(RealmBattleField::class.java).findAll().map { e -> e.toModelObject(realm) }
    }

    override fun getById(id: String): PhysicalBoard<MyPiece, Ground>? = realm.where(RealmBattleField::class.java).equalTo("nickname", id).findFirst()?.toModelObject(realm)

}