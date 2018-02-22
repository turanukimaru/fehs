package jp.blogspot.turanukimaru.fehbs

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented Solution, which will execute on an Android device.
 *
 * See [testing documentation](http://baseDamage.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under Solution.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("jp.blogspot.turanukimaru.fehbs", appContext.packageName)
    }
}
