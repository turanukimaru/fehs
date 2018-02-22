package jp.blogspot.turanukimaru.reposroom

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import jp.blogspot.turanukimaru.reposroom.persistence.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented Solution, which will execute on an Android device.
 *
 * @see [Testing documentation](http://baseDamage.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class RoomInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under Solution.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("jp.blogspot.turanukimaru.reposroom", appContext.packageName)
        appContext.deleteDatabase("Sample.db")
        val dao = UsersDatabase.getInstance(appContext).userDao()
        val user = User(userName = "turanukimal")
        dao.insertUser(user)
        val insertedUser = dao.getUserById(user.id)
        assertEquals("not equals",user.userName, insertedUser.userName)
        dao.deleteAllUsers()
        val deletedUser = dao.getUserById(user.id)
        assertEquals("null?exception?",deletedUser,null)

        val heroDao =UsersDatabase.getInstance(appContext).heroDao()
        val hero = RoomArmedHero()
        heroDao.insertHero(hero)
        val insertedHero =heroDao.getHeroById("")
        assertEquals(hero.nickname,insertedHero.nickname)
        heroDao.deleteAllHeroes()

        RoomArmedHeroContent.appContext = appContext
        val modelHero = ArmedHero(StandardBaseHero.get("エフラム")!!,"new エフラム")
        RoomArmedHeroContent.createOrUpdate(modelHero)
        val insertedArmedHero = RoomArmedHeroContent.getById("new エフラム")
        assertEquals("new エフラム",insertedArmedHero!!.name)
    }
}
