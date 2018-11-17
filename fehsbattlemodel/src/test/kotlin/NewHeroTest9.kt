import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest9 {

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ウード.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 41, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 31, unitA.def)
        assertEquals("res", 22, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.エフラム.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 30, fightResult[1].damage)
        assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 17, fightResult[2].damage)
        assertEquals("hitPoint fail", 11, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[2].target.hp)
    }

    @Test
    fun flRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エフラム.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウード.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 30, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 17, fightResult[1].damage)
        assertEquals("hitPoint fail", 28, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 7, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[2].target.hp)
    }

    @Test
    fun opTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.クリフ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 37, unitA.maxHp)
        assertEquals("atk", 42, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 34, unitA.def)
        assertEquals("res", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ミカヤ__夏祭り_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 2, fightResult[1].damage)
        assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 35, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun opRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ミカヤ__夏祭り_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.クリフ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 2, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[2].target.hp)
    }

    @Test
    fun nnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ロキ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 47, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 16, unitA.def)
        assertEquals("res", 25, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.アルヴィス.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 47, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 23, fightResult[1].damage)
        assertEquals("hitPoint fail", 24, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
    }

    @Test
    fun nnRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.アルヴィス.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ロキ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
    }

    @Test
    fun miaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.インバース.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 46, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 36, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("res", 27, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.シグレ__舞踏祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 24, fightResult[1].damage)
        assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 22, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[2].target.hp)
    }

    @Test
    fun miaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シグレ__舞踏祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.インバース.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 6, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[2].target.hp)
    }
}