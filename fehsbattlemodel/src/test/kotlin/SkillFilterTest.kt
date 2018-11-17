import jp.blogspot.turanukimaru.fehs.skill.SkillA
import jp.blogspot.turanukimaru.fehs.skill.SkillB
import jp.blogspot.turanukimaru.fehs.skill.SkillC
import org.junit.Test

/**
 * 奥義によるダメージ減少・激化のテスト
 * Created by turanukimaru on 2017/11/02.
 */
class SkillFilterTest {

    @Test
    fun maxLvTest() {
        val skillA = SkillA.spreadMaxLvItems()
        println(skillA.toString().replace(",", ",\n"))
        val skillB = SkillB.spreadMaxLvItems()
        println(skillB.toString().replace(",", ",\n"))
        val skillC = SkillC.spreadMaxLvItems()
        println(skillC.toString().replace(",", ",\n"))
        val seals = SkillC.spreadMaxLvItems()
        println(seals.toString().replace(",", ",\n"))
    }
}