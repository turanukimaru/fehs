import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest8 {

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ミルラ__収穫祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 46, unitA.maxHp)
        assertEquals("atk", 54, unitA.atk)
        assertEquals("spd", 23, unitA.spd)
        assertEquals("def", 45, unitA.def)
        assertEquals("spd", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.シャロン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun flRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.シャロン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ミルラ__収穫祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 4", 4, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 12, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 21, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[2].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        assertEquals("buildDamage fail", 44, fightResult[3].damage)
        assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[3].target.hp)
    }

    @Test
    fun opTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ゼロ__収穫祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 37, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 39, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("spd", 29, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.シャロン__春_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 26, fightResult[1].damage)
        assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[1].target.hp)
    }

    @Test
    fun opRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.イシュタル.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ゼロ__収穫祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 29, fightResult[1].damage)
        assertEquals("hitPoint fail", 9, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 23, fightResult[2].damage)
        assertEquals("hitPoint fail", 9, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun nnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.カゲロウ__収穫祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 41, unitA.maxHp)
        assertEquals("atk", 50, unitA.atk)
        assertEquals("spd", 21, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("spd", 33, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.アルヴィス.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 27, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 20, fightResult[1].damage)
        assertEquals("hitPoint fail", 21, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 43, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun nnRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.アルヴィス.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.カゲロウ__収穫祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 23, fightResult[1].damage)
        assertEquals("hitPoint fail", 10, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 24, fightResult[2].damage)
        assertEquals("hitPoint fail", 10, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun miaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ワユ__収穫祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 36, unitA.maxHp)
        assertEquals("atk", 42, unitA.atk)
        assertEquals("spd", 34, unitA.spd)
        assertEquals("def", 22, unitA.def)
        assertEquals("spd", 28, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 4, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 41, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 30, fightResult[1].damage)
        assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 41, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 11, fightResult[2].damage)
        assertEquals("hitPoint fail", 6, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[2].target.hp)
    }

    @Test
    fun miaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.イシュタル.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ワユ__収穫祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(Locale.OTHER))
        println(fightResult[0].source.statusText(Locale.OTHER))
        println(fightResult[0].target.activatedSkillText(Locale.OTHER))
        println(fightResult[0].target.statusText(Locale.OTHER))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 8, fightResult[1].damage)
        assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 32, fightResult[2].damage)
        assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }
}