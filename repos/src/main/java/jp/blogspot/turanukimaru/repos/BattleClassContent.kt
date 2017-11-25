package jp.blogspot.turanukimaru.repos

import android.util.Log
import io.realm.Realm
import jp.blogspot.turanukimaru.fehs.ArmedClass
import jp.blogspot.turanukimaru.fehs.BoonType
import jp.blogspot.turanukimaru.fehs.skill.Skill
import kotlin.properties.Delegates

/**
 * 戦闘用クラスのDBアクセス。名前が酷いのでそのうち変えたい
 */
object BattleClassContent : RealmContent<ArmedClass>() {

    private val tag = "BattleClassContent"
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

    /**
     * アイテムを作成する。
     */

    fun createItem(
            name: String = "",
            baseName: String = "",
            weapon: Skill = Skill.NONE,
            assist: Skill = Skill.NONE,
            special: Skill = Skill.NONE,
            aSkill: Skill = Skill.NONE,
            bSkill: Skill = Skill.NONE,
            cSkill: Skill = Skill.NONE,
            seal: Skill = Skill.NONE,
            rarity: Int = 5,
            boost: Int = 0,
            boon: BoonType = BoonType.NONE,
            bane: BoonType = BoonType.NONE,
            defensiveTerrain: Boolean = false,
            atkBuff: Int = 0,
            spdBuff: Int = 0,
            defBuff: Int = 0,
            resBuff: Int = 0,
            atkSpur: Int = 0,
            spdSpur: Int = 0,
            defSpur: Int = 0,
            resSpur: Int = 0
    ) {
        Log.i("RealmBattleClass", "CRERATE RealmBattleClass")
        Log.i("RealmBattleClass", "nickname $name ")
        Log.i("RealmBattleClass", "baseName $baseName ")
        Log.i("RealmBattleClass", "weapon $weapon ")
        Log.i("RealmBattleClass", "assist $assist ")
        Log.i("RealmBattleClass", "special $special ")
        Log.i("RealmBattleClass", "SkillA $aSkill ")
        Log.i("RealmBattleClass", "SkillB $bSkill ")
        Log.i("RealmBattleClass", "SkillC $cSkill ")
        Log.i("RealmBattleClass", "seal $seal ")
        Log.i("RealmBattleClass", "rarity $rarity ")
        Log.i("RealmBattleClass", "levelBoost $boost ")
        Log.i("RealmBattleClass", "boon $boon ")
        Log.i("RealmBattleClass", "bane $bane ")
        val item = RealmBattleClass(name, baseName, weapon.value, assist.value, special.value, aSkill.value, bSkill.value, cSkill.value, seal.value, rarity, boost, boon.toString(), bane.toString(),
                defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur)
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(item)
        }

    }

    override fun complexQuery(item: ArmedClass): List<ArmedClass> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(item: ArmedClass): Int {
        val results = realm.where(RealmBattleClass::class.java).equalTo("nickname", item.name).findAll()
        realm.executeTransaction {
            results.deleteAllFromRealm()
        }
        return results.size
    }

    override fun deleteById(id: String): Int {
        Log.i(tag, "delete $id")
        println(id)
        val results = realm.where(RealmBattleClass::class.java).equalTo("nickname", id).findAll()
        realm.executeTransaction {
            Log.i(tag, results.toString())
            results.deleteAllFromRealm()
        }
        return results.size
    }


    override fun createOrUpdate(item: ArmedClass): ArmedClass {
        item.apply {
            realm.executeTransaction {
                realm.copyToRealmOrUpdate(RealmBattleClass(name, battleClass.name, weapon.value, assist.value, special.value, aSkill.value, bSkill.value, cSkill.value, seal.value, rarity, levelBoost, boon.name, bane.name
                        , defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur))
            }
        }
        return item
    }

    override fun find(item: ArmedClass): ArmedClass? {
        //TODO:複数項目指定しての検索をうまくやるほうほうがそのうち必要になるはず
        return realm.where(RealmBattleClass::class.java).equalTo("nickname", item.name).findFirst()?.toModelObject()
    }

    override fun allItems(): List<ArmedClass> {
        return realm.where(RealmBattleClass::class.java).findAll().map { e -> e.toModelObject() }
    }

    override fun getById(id: String): ArmedClass? = realm.where(RealmBattleClass::class.java).equalTo("nickname", id).findFirst()?.toModelObject()

}