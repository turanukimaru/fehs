package jp.blogspot.turanukimaru.fieldrepos

import android.util.Log
import io.realm.Realm
import jp.blogspot.turanukimaru.fehs.ArmedHero
import kotlin.properties.Delegates

/**
 * 戦闘用クラスのDBアクセス。名前が酷いのでそのうち変えたい
 */
object RealmArmedHeroContent : RealmContent<ArmedHero>() {

    private val tag = "RealmArmedHeroContent"
    /**
     * realmのkotlin用ハンドラ
     */

    private var realm: Realm by Delegates.notNull()

    /**
     * 初期化ブロック。テーブル変更時などはここでマイグレーションすることになる
     */
    init {
        // Open the realm for the UI thread.
        realm = Realm.getDefaultInstance()

        // テスト中はマイグレーションが面倒なので全部削除
        realm.executeTransaction {
            //            realm.deleteAll()
        }
    }


    override fun complexQuery(item: ArmedHero): List<ArmedHero> {
        return arrayListOf()
    }

    override fun delete(item: ArmedHero): Int {
        val results = realm.where(RealmArmedHero::class.java).equalTo("nickname", item.name).findAll()
        realm.executeTransaction {
            results.deleteAllFromRealm()
        }
        return results.size
    }

    override fun deleteById(id: String): Int {
        Log.i(tag, "delete $id")
//        println(id)
        val results = realm.where(RealmArmedHero::class.java).equalTo("nickname", id).findAll()
        realm.executeTransaction {
            Log.i(tag, results.toString())
            results.deleteAllFromRealm()
        }
        return results.size
    }


    override fun createOrUpdate(item: ArmedHero): ArmedHero {
        Log.i("RealmArmedHeroContent", item.toString())
        item.apply {
            realm.executeTransaction {
                realm.copyToRealmOrUpdate(RealmArmedHero(name, baseHero.heroName.toString(), weapon.value, refinedWeapon.value, assist.value, special.value, aSkill.value, bSkill.value, cSkill.value, seal.value, rarity, levelBoost, boon.name, bane.name
                        , defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur))

                val target = realm.createObject(RealmArmedHero::class.java, "ニックネーム")
                target.baseName = "更新"
            }
        }
        return item
    }

    fun anotherCcreateOrUpdate(item: ArmedHero): ArmedHero {
        Log.i("RealmArmedHeroContent", item.toString())
        item.apply {
            realm.executeTransaction {
                val target = realm.createObject(RealmArmedHero::class.java, "ニックネーム")
                target.baseName = "更新後"
            }
            val take = realm.where(RealmArmedHero::class.java).equalTo("nickname", item.name).findFirst()!!
            println(take.baseName)//"更新後"
        }
        return item
    }

    override fun find(item: ArmedHero): ArmedHero? {
        return realm.where(RealmArmedHero::class.java).equalTo("nickname", item.name).findFirst()?.toModelObject()
    }

    override fun allItems(): List<ArmedHero> {
        return realm.where(RealmArmedHero::class.java).findAll().map { e -> e.toModelObject() }
    }

    override fun getById(id: String): ArmedHero? = realm.where(RealmArmedHero::class.java).equalTo("nickname", id).findFirst()?.toModelObject()
    override fun create(initialItem: ArmedHero): ArmedHero {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}