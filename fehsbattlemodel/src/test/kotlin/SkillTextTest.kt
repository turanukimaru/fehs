import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.SkillA
import org.junit.Test

class SkillTextTest {

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エイリーク__冬_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.armedHero.aSkill = SkillA.DeathBlow.lv(3)
        val unitB = ArmedHero(StandardBaseHero.get(HeroName.アイク.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.effect.activatedSkills.size)
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        val fightResult2 = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult2[0].source.effect.activatedSkills.size)
        println(fightResult2[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult2[0].target.activatedSkillText(Locale.JAPANESE))
        val fightResult3 = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult3[0].source.effect.activatedSkills.size)
        println(fightResult3[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult3[0].target.activatedSkillText(Locale.JAPANESE))
    }

    @Test
    fun fl2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エイリーク__冬_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp / 2)
        attacker.armedHero.aSkill = SkillA.BrazenAtkSpd.lv(3)
        val unitB = ArmedHero(StandardBaseHero.get(HeroName.アイク.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.effect.activatedSkills.size)
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
    }

    @Test
    fun fl3Test() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.パオラ3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp / 2)
//        attacker.effect.adjacentUnits = 2
//        attacker.armedHero.aSkill = SkillA.BrazenAtkSpd.lv(3)
        val unitB = ArmedHero(StandardBaseHero.get(HeroName.アイク.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.effect.activatedSkills.size)
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
    }
}