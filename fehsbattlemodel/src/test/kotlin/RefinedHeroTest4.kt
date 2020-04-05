import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class RefinedHeroTest4 {

    @Test
    fun julTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ソフィーヤ3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 50, unitA.atk)
        assertEquals("spd", 22, unitA.spd)
        assertEquals("def", 31, unitA.def)
        assertEquals("spd", 32, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.レベッカ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 33, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 3, fightResult[1].damage)
        assertEquals("hitPoint fail", 37, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 3, fightResult[2].damage)
        assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[2].target.hp)
    }

    @Test
    fun julRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レベッカ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ソフィーヤ3.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 0, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 40, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 33, fightResult[1].damage)
        assertEquals("hitPoint fail", 4, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 40, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 0, fightResult[2].damage)
        assertEquals("hitPoint fail", 4, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 40, fightResult[2].target.hp)
    }

    @Test
    fun deaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.クレイン3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 39, unitA.atk)
        assertEquals("spd", 31, unitA.spd)
        assertEquals("def", 20, unitA.def)
        assertEquals("spd", 24, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 5", 5, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 10, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 10, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[2].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[3].side)
        assertEquals("buildDamage fail", 10, fightResult[3].damage)
        assertEquals("hitPoint fail", 18, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 10, fightResult[3].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[4].side)
        assertEquals("buildDamage fail", 29, fightResult[4].damage)
        assertEquals("hitPoint fail", 18, fightResult[4].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[4].target.hp)
    }

    @Test
    fun thaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.デューテ3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
//        assertEquals("maxHp", 39, unitA.maxHp)
//        assertEquals("atk", 46, unitA.atk)
//        assertEquals("spd", 34, unitA.spd)
//        assertEquals("def", 23, unitA.def)
//        assertEquals("spd", 20, unitA.res)
        attacker.effect.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ゼルギウス.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 1", 1, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 52, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }

    @Test
    fun teTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ソニア3.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 36, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 31, unitA.spd)
        assertEquals("def", 15, unitA.def)
        assertEquals("spd", 32, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ハーディン__闇_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 25, fightResult[1].damage)
        assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
    }

    @Test
    fun o2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.オボロ3.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 52, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 26, fightResult[1].damage)
        assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 26, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[2].target.hp)
    }

    @Test
    fun seliphTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ゼト3.jp)!!)//
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 45, unitA.atk)
        assertEquals("spd", 31, unitA.spd)
        assertEquals("def", 37, unitA.def)
        assertEquals("spd", 22, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 26, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 26, fightResult[2].damage)
        assertEquals("hitPoint fail", 40, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun hinataTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヒナタ3.jp)!!)//
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 50, unitA.maxHp)
        assertEquals("atk", 54, unitA.atk)
        assertEquals("spd", 30, unitA.spd)
        assertEquals("def", 42, unitA.def)
        assertEquals("spd", 24, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 27, fightResult[0].damage)
        assertEquals("hitPoint fail", 50, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 50, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 27, fightResult[2].damage)
        assertEquals("hitPoint fail", 38, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun hinata2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)//
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヒナタ3.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 0, fightResult[0].damage)
        assertEquals("hitPoint fail", 52, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 50, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 32, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 50, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 32, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[2].target.hp)
    }

    @Test
    fun zeroTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ゼロ3.jp)!!)//
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 37, unitA.maxHp)
        assertEquals("atk", 39, unitA.atk)
        assertEquals("spd", 34, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("spd", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])

        println(fightResult[0].source.activatedSkillText(Locale.JAPANESE))
        println(fightResult[0].source.statusText(Locale.JAPANESE))

        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 43, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 27, fightResult[1].damage)
        assertEquals("hitPoint fail", 37, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
    }


}