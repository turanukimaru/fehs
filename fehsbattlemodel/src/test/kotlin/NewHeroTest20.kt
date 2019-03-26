import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 冬
 */
class NewHeroTest20 {
    val LOCALE = Locale.OTHER
    @Test
    fun gunTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.マリカ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 50, unitA.maxHp)
        assertEquals("atk", 43, unitA.atk)
        assertEquals("spd", 36, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("spd", 21, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 50, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 42, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 29, fightResult[1].damage)
        assertEquals("hitPoint fail", 21, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 42, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
    }

    @Test
    fun gunRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.マリカ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 3, fightResult[1].damage)
        assertEquals("hitPoint fail", 42, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[2].target.hp)
    }


    @Test
    fun hrTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヴェロニカ__春_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 43, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 39, unitA.spd)
        assertEquals("def", 16, unitA.def)
        assertEquals("spd", 25, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.リリーナ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 12, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 34, fightResult[1].damage)
        assertEquals("hitPoint fail", 9, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[1].target.hp)
    }

    @Test
    fun hrRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.リリーナ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヴェロニカ__春_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 34, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 23, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 5, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[2].target.hp)
    }

    @Test
    fun fjTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.マリカ__春_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 42, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 38, unitA.spd)
        assertEquals("def", 28, unitA.def)
        assertEquals("res", 21, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ロビン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 25, fightResult[0].damage)
        assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 7, fightResult[1].damage)
        assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 25, fightResult[2].damage)
        assertEquals("hitPoint fail", 35, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun fjAdTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ロビン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.マリカ__春_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 25, fightResult[1].damage)
        assertEquals("hitPoint fail", 21, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 25, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[2].target.hp)
    }

    @Test
    fun lvTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ブルーノ__春_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 41, unitA.maxHp)
        assertEquals("atk", 42, unitA.atk)
        assertEquals("spd", 24, unitA.spd)
        assertEquals("def", 35, unitA.def)
        assertEquals("spd", 14, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ゼロ__収穫祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 1", 1, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 9, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
    }


    @Test
    fun lgTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ゼロ__収穫祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ブルーノ__春_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 10, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 9, fightResult[1].damage)
        assertEquals("hitPoint fail", 28, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 10, fightResult[2].damage)
        assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[2].target.hp)//1ずれてる・・・？
    }

    @Test
    fun sueTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.パオラ__春_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 38, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 34, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("spd", 29, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.スルト.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 47, fightResult[0].damage)
        assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 47, fightResult[1].damage)
        assertEquals("hitPoint fail", 38, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
    }


    @Test
    fun suerTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.リン__伝承英雄_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.パオラ__春_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 37, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 37, fightResult[1].damage)
        assertEquals("hitPoint fail", 0, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[1].target.hp)
    }
}