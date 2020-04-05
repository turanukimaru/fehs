import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 冬
 */
class NewHeroTest14 {

    @Test
    fun gunTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.スリーズ__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 50, unitA.atk)
        assertEquals("spd", 40, unitA.spd)
        assertEquals("def", 27, unitA.def)
        assertEquals("spd", 26, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 12, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 34, fightResult[1].damage)
        assertEquals("hitPoint fail", 4, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)//凶星
        assertEquals("hitPoint fail", 4, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }

    @Test
    fun gunRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.スリーズ__正月_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 34, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 8, fightResult[1].damage)
        assertEquals("hitPoint fail", 37, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[2].target.hp)
    }


    @Test
    fun hrTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.フリーズ__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 42, unitA.maxHp)
        assertEquals("atk", 52, unitA.atk)
        assertEquals("spd", 19, unitA.spd)
        assertEquals("def", 36, unitA.def)
        assertEquals("spd", 27, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 10, fightResult[0].damage)
        assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 25, fightResult[1].damage)
        assertEquals("hitPoint fail", 17, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 35, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[2].target.hp)
    }

    @Test
    fun hrRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.フリーズ__正月_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 25, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 10, fightResult[1].damage)
        assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 35, fightResult[2].damage)
        assertEquals("hitPoint fail", 35, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun hr3RTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ルカ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.フリーズ__正月_.jp)!!)
        val target = BattleUnit(unitB, unitB.maxHp)
        target.effect.adjacentUnits = 1
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 10, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 3, fightResult[1].damage)
        assertEquals("hitPoint fail", 42, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 3, fightResult[2].damage)
        assertEquals("hitPoint fail", 39, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[2].target.hp)
    }

    @Test
    fun hrAdTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.フリーズ__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.effect.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.アイク__伝承英雄_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 11, fightResult[1].damage)
        assertEquals("hitPoint fail", 31, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 31, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[2].target.hp)
    }

    @Test
    fun fjTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.フィヨルム__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 20, unitA.def)
        assertEquals("spd", 21, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 34, fightResult[1].damage)
        assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 3, fightResult[2].damage)
        assertEquals("hitPoint fail", 5, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[2].target.hp)
    }

    @Test
    fun fjAdTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.フィヨルム__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.effect.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 34, fightResult[1].damage)
        assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 7, fightResult[2].damage)
        assertEquals("hitPoint fail", 5, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[2].target.hp)
    }

    @Test
    fun lvTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レーヴァテイン__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 33, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 30, unitA.spd)
        assertEquals("def", 27, unitA.def)
        assertEquals("spd", 18, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 28, fightResult[1].damage)
        assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 24, fightResult[2].damage)
        assertEquals("hitPoint fail", 5, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }


    @Test
    fun lgTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レーギャルン__正月_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 35, unitA.def)
        assertEquals("spd", 17, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__幼_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 20, fightResult[1].damage)
        assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 23, fightResult[2].damage)
        assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun lfRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.アクア__伝承英雄_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)//duelで+5か
        assertEquals("atk", 44, unitA.atk)
        assertEquals("spd", 38, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("spd", 24, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))
        println(fightResult[0].target.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].target.statusText(Locale.JAPANESE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 28, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 13, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 28, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

}