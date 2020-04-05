import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 殺し/激化テスト
 * Created by turanukimaru on 2017/11/02.
 */
class BreakerTest {
    val LOCALE = Locale.OTHER

    @Test
    fun swordBreakerTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2 - 1).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 20, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        assertEquals("size == 4", 2, fightResult.size)
    }

    @Test
    fun swordBreakerTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 21, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 6, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestC() {
        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitA = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2 - 1))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[1].target.hp)
        assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun swordBreakerTestD() {
        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitA = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestE() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2 - 1).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 20, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 8, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 8, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestF() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 21, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 9, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 29, fightResult[2].damage)
        assertEquals("hitPoint fail", 9, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestG() {
        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitA = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 8, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 29, fightResult[1].damage)
        assertEquals("hitPoint fail", 16, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[1].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 29, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("ロイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 19, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 15, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("ロイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ジェイガン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 0, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 38, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestC() {
        val unitA = ArmedHero(StandardBaseHero.get("ロイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("レイヴァン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 39, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 1, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestE() {
        val unitA = ArmedHero(StandardBaseHero.get("アルム")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アーダン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 11, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 49, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestF() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 28, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 0, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestG() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ティアマト")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 39, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 3", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestD() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アテナ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 24, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 23, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 29, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestAS() {
        val unitA = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 6, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 31, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 16, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 31, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestAL() {
        val unitA = ArmedHero(StandardBaseHero.get("フロリーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 34, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 6, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 34, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestAA() {
        val unitA = ArmedHero(StandardBaseHero.get("フレデリク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 17, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestAB() {
        val unitA = ArmedHero(StandardBaseHero.get("ヒノカ（白き翼）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 30, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 20, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 30, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestCS() {
        val unitA = ArmedHero(StandardBaseHero.get("グレイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 46, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestCL() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 49, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestCA() {
        val unitA = ArmedHero(StandardBaseHero.get("ジェローム")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 32, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestFB() {
        val unitA = ArmedHero(StandardBaseHero.get("タクミ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カミラ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 41, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestAntiLoptous() {
        val unitA = ArmedHero(StandardBaseHero.get("ユリウス")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ユリア")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 21, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 21, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 17, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestLoptous() {
        val unitA = ArmedHero(StandardBaseHero.get("ユリウス")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マーク（男）")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 23, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 3, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 3, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 32, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestWarriorPrincessA() {
        val unitA = ArmedHero(StandardBaseHero.get("ヒノカ（白き翼）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 30, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 20, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 30, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestWarriorPrincessF() {
        val unitA = ArmedHero(StandardBaseHero.get("ヒノカ（白き翼）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ヒノカ（白き翼）")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 52, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestKittyR() {
        val unitA = ArmedHero(StandardBaseHero.get("サクラ（収穫祭）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("サーリャ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 37, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[0].target.hp)
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 11, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestKittyB() {
        val unitA = ArmedHero(StandardBaseHero.get("サクラ（収穫祭）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ミカヤ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 36, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestKittyG() {
        val unitA = ArmedHero(StandardBaseHero.get("サクラ（収穫祭）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ニノ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 41, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstThaniNC() {
        val unitA = ArmedHero(StandardBaseHero.get("ミカヤ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カミュ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        println(fightResult[0].target.activatedSkillText(LOCALE))
        println(fightResult[0].target.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 32, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 30, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 30, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[2].target.hp)
        Assert.assertEquals("size == 1", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstThaniC() {
        val unitA = ArmedHero(StandardBaseHero.get("ミカヤ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 66, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstThaniA() {
        val unitA = ArmedHero(StandardBaseHero.get("ミカヤ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 69, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstWSA() {
        val unitA = ArmedHero(StandardBaseHero.get("シーダ＋")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 22, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 14, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 22, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 6, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstWSC() {
        val unitA = ArmedHero(StandardBaseHero.get("シーダ＋")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 34, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 16, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[1].target.hp)
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 24, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 20, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstLFA() {
        val unitA = ArmedHero(StandardBaseHero.get("クレア＋")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 36, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 7, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 36, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstLFC() {
        val unitA = ArmedHero(StandardBaseHero.get("クレア＋")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0].source.activatedSkillText(LOCALE))
        println(fightResult[0].source.statusText(LOCALE))
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 48, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }
}