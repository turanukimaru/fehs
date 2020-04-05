import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest7 {

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レーギャルン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 46, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 39, unitA.spd)
        assertEquals("def", 36, unitA.def)
        assertEquals("spd", 17, unitA.res)
        assertEquals("spd", 690, unitA.score)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 6, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 37, fightResult[1].damage)
        assertEquals("hitPoint fail", 9, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 6, fightResult[2].damage)
        assertEquals("hitPoint fail", 9, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[2].target.hp)
    }

    @Test
    fun flTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レーギャルン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val uB = BattleUnit(unitB, unitB.maxHp)
        uB.atkBuff = 1
        uB.spdBuff = 2
        uB.defBuff = 3
        uB.resBuff = 4
        val fightResult = attacker.fightAndAfterEffect(uB)
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 33, fightResult[1].damage)
        assertEquals("hitPoint fail", 13, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 7, fightResult[2].damage)
        assertEquals("hitPoint fail", 13, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[2].target.hp)
    }

    @Test
    fun flRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.レーギャルン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 37, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 39, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 6, fightResult[2].damage)
        assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[2].target.hp)
    }

    @Test
    fun opTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レーヴァテイン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 59, unitA.atk)
        assertEquals("spd", 34, unitA.spd)
        assertEquals("def", 37, unitA.def)
        assertEquals("spd", 25, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 29, fightResult[1].damage)
        assertEquals("hitPoint fail", 10, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 15, fightResult[2].damage)
        assertEquals("hitPoint fail", 4, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }

    @Test
    fun opRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レーヴァテイン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        unitA.atkBuff = 1
        unitA.spdBuff = 2
        unitA.defBuff = 3
        unitA.resBuff = 4

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 29, fightResult[1].damage)
        assertEquals("hitPoint fail", 10, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 15, fightResult[2].damage)
        assertEquals("hitPoint fail", 4, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }

    @Test
    fun nnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヘルビンディ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 49, unitA.maxHp)
        assertEquals("atk", 51, unitA.atk)
        assertEquals("spd", 18, unitA.spd)
        assertEquals("def", 35, unitA.def)
        assertEquals("spd", 31, unitA.res)
        assertEquals("spd", 690, unitA.score)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 28, fightResult[0].damage)
        assertEquals("hitPoint fail", 49, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 5, fightResult[2].damage)
        assertEquals("hitPoint fail", 39, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[2].target.hp)
    }

    @Test
    fun nnRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘルビンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 5, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 28, fightResult[1].damage)
        assertEquals("hitPoint fail", 17, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 5, fightResult[2].damage)
        assertEquals("hitPoint fail", 17, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[2].target.hp)
    }
}