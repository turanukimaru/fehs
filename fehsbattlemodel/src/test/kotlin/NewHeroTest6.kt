import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest6 {

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フローラ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 23, unitA.spd)
        assertEquals("def", 18, unitA.def)
        assertEquals("res", 38, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 10, fightResult[1].damage)
        assertEquals("hitPoint fail", 28, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 33, fightResult[2].damage)//あれキラー武器だっけ？確認しなおさないとな
        assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[2].target.hp)
    }

    @Test
    fun flTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フローラ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 27, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 4, fightResult[1].damage)
        assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 49, fightResult[2].damage)
        assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun flRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.レヴィン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.フローラ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 0, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 38, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 69, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[2].target.hp)
    }

    @Test
    fun opTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.オフェリア.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 51, unitA.atk)
        assertEquals("spd", 33, unitA.spd)
        assertEquals("def", 20, unitA.def)
        assertEquals("res", 23, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ボーイ.jp)!!)
        unitB.spdSpur = 20
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 26, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 23, fightResult[1].damage)
        assertEquals("hitPoint fail", 15, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 26, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun opRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ボーイ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        unitA.spdSpur = 20

        val unitB = ArmedHero(StandardBaseHero.get(Name.オフェリア.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 23, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 23, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }

    @Test
    fun nnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エポニーヌ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 35, unitA.maxHp)
        assertEquals("atk", 44, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 16, unitA.def)
        assertEquals("res", 31, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ジャムカ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 32, fightResult[1].damage)
        assertEquals("hitPoint fail", 3, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 30, fightResult[2].damage)
        assertEquals("hitPoint fail", 3, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun nnTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エポニーヌ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ジョルジュ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 20, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 30, fightResult[1].damage)
        assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 27, fightResult[2].damage)
        assertEquals("hitPoint fail", 5, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun nnRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ジャムカ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.エポニーヌ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 32, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 16, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 30, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[2].target.hp)
    }

    @Test
    fun sTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.サイラス.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 24, unitA.spd)
        assertEquals("def", 36, unitA.def)
        assertEquals("res", 21, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ファ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 34, fightResult[1].damage)
        assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[1].target.hp)
    }

    @Test
    fun sRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ファ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        val unitB = ArmedHero(StandardBaseHero.get(Name.サイラス.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 34, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 31, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[1].target.hp)
    }


    @Test
    fun qTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ガロン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 43, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 22, unitA.spd)
        assertEquals("def", 33, unitA.def)
        assertEquals("res", 32, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.マルス.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 26, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }

    @Test
    fun qRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.マルス.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ガロン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 26, fightResult[1].damage)
        assertEquals("hitPoint fail", 15, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }
}