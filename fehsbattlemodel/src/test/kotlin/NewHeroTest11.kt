import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest11 {

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フリーズ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 50, unitA.atk)
        assertEquals("spd", 39, unitA.spd)
        assertEquals("def", 27, unitA.def)
        assertEquals("res", 21, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.シャロン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 13, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 31, fightResult[1].damage)
        assertEquals("hitPoint fail", 7, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 38, fightResult[2].damage)
        assertEquals("hitPoint fail", 7, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun flRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シャロン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.フリーズ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 31, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 13, fightResult[1].damage)
        assertEquals("hitPoint fail", 32, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 38, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[2].target.hp)
    }

    @Test
    fun opTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フリーズ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 36, unitA.maxHp)
        assertEquals("atk", 54, unitA.atk)
        assertEquals("spd", 30, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("res", 30, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.シャロン.jp)!!)
        val target =BattleUnit(unitB, unitB.maxHp)
        target.atkDebuff = -3
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 5, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 26, fightResult[1].damage)
        assertEquals("hitPoint fail", 10, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 26, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[2].target.hp)
    }

    @Test
    fun opRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シャロン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.atkDebuff = -4

        val unitB = ArmedHero(StandardBaseHero.get(Name.フリーズ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 26, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 10, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 32, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 10, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 26, fightResult[2].damage)
        assertEquals("hitPoint fail", 32, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

}