package jp.blogspot.turanukimaru.repos

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.realm.Realm
import io.realm.RealmConfiguration
import jp.blogspot.turanukimaru.fehs.ArmedHeroRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented Solution, which will execute on an Android device.
 *
 * @see [Testing documentation](http://baseDamage.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class RealmInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under Solution.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("jp.blogspot.turanukimaru.repos.Solution", appContext.packageName)
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(appContext)
        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
        //オブジェクト構文で作った奴をそのまま渡しても大丈夫.Realm.init()より後に評価される
        ArmedHeroRepository.repo = RealmArmedHeroContent

        val heroes = ArmedHeroRepository.allItems()
        assertTrue(heroes.isNotEmpty())
//        println("start println")
//        heroes.forEach{e->println(e)}
//        println("end println")
    }
}
