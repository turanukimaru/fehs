import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class RefinedHeroTest2 {

    @Test
    fun julTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ユリア3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 26, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("spd", 35, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 16, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 20, fightResult[1].damage)
        assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
    }

    @Test
    fun julRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ユリア3.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 16, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 16, fightResult[1].damage)
        assertEquals("hitPoint fail", 24, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
    }

    @Test
    fun deaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ディアドラ3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 36, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 28, unitA.spd)
        assertEquals("def", 16, unitA.def)
        assertEquals("spd", 35, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 36, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 17, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 36, fightResult[2].damage)
        assertEquals("hitPoint fail", 19, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
//スキル効果で反撃も追撃もない
    }

    @Test
    fun teTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ティルテュ3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("spd", 27, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 33, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 10, fightResult[1].damage)
        assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 33, fightResult[2].damage)
        assertEquals("hitPoint fail", 29, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun seliphTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.セリス3.jp)!!)//どーすっかな祈りすごいめんどい
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 50, unitA.maxHp)
        assertEquals("atk", 50, unitA.atk)
        assertEquals("spd", 24, unitA.spd)
        assertEquals("def", 30, unitA.def)
        assertEquals("spd", 22, unitA.res)
        attacker.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__伝承英雄_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 11, fightResult[2].damage)
        assertEquals("hitPoint fail", 40, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
    }

}