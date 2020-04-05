import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 冬
 */
class NewHeroTest15 {
    val LOCALE = Locale.OTHER
    @Test
    fun gunTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ティバーン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 41, unitA.maxHp)
        assertEquals("atk", 55, unitA.atk)
        assertEquals("spd", 31, unitA.spd)
        assertEquals("def", 34, unitA.def)
        assertEquals("spd", 18, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 19, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 17, fightResult[1].damage)
        assertEquals("hitPoint fail", 24, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 19, fightResult[2].damage)
        assertEquals("hitPoint fail", 24, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[2].target.hp)
    }

    @Test
    fun gunRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エフラム.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ティバーン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 27, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 31, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 17, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[2].target.hp)
    }


    @Test
    fun hrTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ネサラ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 41, unitA.spd)
        assertEquals("def", 24, unitA.def)
        assertEquals("spd", 29, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 4", 4, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 12, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 40, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 38, fightResult[1].damage)
        assertEquals("hitPoint fail", 1, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 40, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 1, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        assertEquals("buildDamage fail", 38, fightResult[3].damage)
        assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[3].target.hp)
    }

    @Test
    fun hrRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ネサラ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 38, fightResult[0].damage)
        assertEquals("hitPoint fail", 52, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 9, fightResult[1].damage)
        assertEquals("hitPoint fail", 43, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 9, fightResult[2].damage)
        assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[2].target.hp)
    }

    @Test
    fun fjTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ニケ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 45, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("spd", 23, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 21, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 41, fightResult[2].damage)
        assertEquals("hitPoint fail", 26, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun fjAdTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エフィ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.effect.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ニケ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 9, fightResult[0].damage)
        assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 38, fightResult[1].damage)
        assertEquals("hitPoint fail", 4, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 21, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[2].target.hp)
    }

    @Test
    fun lvTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.リュシオン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 37, unitA.maxHp)
        assertEquals("atk", 43, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 24, unitA.def)
        assertEquals("spd", 26, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__伝承英雄_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 19, fightResult[1].damage)
        assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[1].target.hp)
    }


    @Test
    fun lgTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.リアーネ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 36, unitA.maxHp)
        assertEquals("atk", 41, unitA.atk)
        assertEquals("spd", 40, unitA.spd)
        assertEquals("def", 16, unitA.def)
        assertEquals("spd", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__幼_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 9, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 24, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 9, fightResult[2].damage)
        assertEquals("hitPoint fail", 24, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[2].target.hp)
    }

    @Test
    fun royTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ロイ__伝承英雄_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 50, unitA.atk)
        assertEquals("spd", 38, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("spd", 26, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 27, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 28, fightResult[1].damage)
        assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 27, fightResult[2].damage)
        assertEquals("hitPoint fail", 11, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun roy2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ロイ__伝承英雄_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.atkBuff = 1
        attacker.resBuff = 2

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 30, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 24, fightResult[1].damage)
        assertEquals("hitPoint fail", 15, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 30, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }
}