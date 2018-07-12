import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.*
import org.junit.Assert
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest {

    @Test
    fun tanaTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ターナ（夏）")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 34, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 31, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 4, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 34, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 3, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun cordeliaTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ティアモ（夏）")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 30, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 7, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 30, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun cordeliaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("ティアモ（夏）")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 13, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
    }

    @Test
    fun innesTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ヒーニアス（夏）")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 8, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 29, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 8, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[2].target.hp)
    }

    @Test
    fun innesRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("ヒーニアス（夏）")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 31, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 11, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 6, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 11, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 6, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 11, fightResult[2].target.hp)
    }


    @Test
    fun noireTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ノワール（夏）")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 22, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 14, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 16, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 14, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[2].target.hp)
    }

    @Test
    fun noireRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("ノワール（夏）")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 24, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
    }


}