import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.SIDES
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 殺し/激化テスト
 * Created by turanukimaru on 2017/11/02.
 */
class BreakerTest {

    @Test
    fun swordBreakerTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2 - 1).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 20, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 5, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        assertEquals("size == 4", 2, fightResult.size)
    }

    @Test
    fun swordBreakerTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 21, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("damage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 6, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestC() {
        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitA = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2 - 1))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[1].target.hp)
        assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun swordBreakerTestD() {
        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitA = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 22, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[1].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("damage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestE() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2 - 1).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 20, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 8, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("damage fail", 8, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestF() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 21, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 9, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("damage fail", 29, fightResult[2].damage)
        assertEquals("hitPoint fail", 9, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun swordBreakerTestG() {
        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitA = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("damage fail", 8, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("damage fail", 29, fightResult[1].damage)
        assertEquals("hitPoint fail", 16, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[1].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("damage fail", 29, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("ロイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 19, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 15, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("ロイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ジェイガン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 0, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 38, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestC() {
        val unitA = ArmedHero(StandardBaseHero.get("ロイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("レイヴァン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 39, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 1, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }


    @Test
    fun effectiveAgainstTestE() {
        val unitA = ArmedHero(StandardBaseHero.get("アルム")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アーダン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 11, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 49, fightResult[0].target.hp)
        Assert.assertEquals("size == 1", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestF() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 28, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 0, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        Assert.assertEquals("size == 3", 2, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestG() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ティアマト")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 39, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 3", 1, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestD() {
        val unitA = ArmedHero(StandardBaseHero.get("マチルダ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アテナ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 24, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 23, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 29, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }


}