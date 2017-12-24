package jp.blogspot.turanukimaru.reposroom.persistence

import android.content.Context
import android.util.Log
import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.ModelObjectRepository

/**
 * 戦闘用クラスのDBアクセス。名前が酷いのでそのうち変えたい
 */
object RoomArmedHeroContent : ModelObjectRepository<ArmedHero> {

    private val tag = "RealmArmedHeroContent"
    /**
     * realmのkotlin用ハンドラ
     */

    var appContext: Context? = null
    val heroDao get() = UsersDatabase.getInstance(appContext!!).heroDao()


    override fun complexQuery(item: ArmedHero): List<ArmedHero> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(item: ArmedHero): Int {
        heroDao.deleteHero(item.name)
        return 1
    }

    override fun deleteById(id: String): Int {
        Log.i(tag, "delete $id")
        heroDao.deleteHero(id)
        return 1
    }


    override fun createOrUpdate(item: ArmedHero): ArmedHero {
        println(item)
        item.apply {
            heroDao.insertHero(RoomArmedHero(name, baseHero.name.toString(), weapon.value, refinedWeapon.value, assist.value, special.value, aSkill.value, bSkill.value, cSkill.value, seal.value, rarity, levelBoost, boon.name, bane.name
                    , defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur))
        }
        return item
    }

    override fun find(item: ArmedHero): ArmedHero? {
        //TODO:複数項目指定しての検索をうまくやるほうほうがそのうち必要になるはず
        return heroDao.getHeroById(item.name).toModelObject()
    }

    override fun allItems(): List<ArmedHero> {
        return heroDao.allHeroes().map { e -> e.toModelObject() }
    }

    override fun getById(id: String): ArmedHero? = heroDao.getHeroById(id)?.toModelObject()

}