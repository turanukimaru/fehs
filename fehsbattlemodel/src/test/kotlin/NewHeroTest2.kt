import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest2 {

    @Test
    fun sumiaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.スミア.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 20, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 27, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 20, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 27, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 2, fightResult[2].target.hp)
    }

    @Test
    fun sumiaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.スミア.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 6, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 28, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 14, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 28, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[2].target.hp)
    }

    @Test
    fun maribelleTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.マリアベル.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
    }


    @Test
    fun maribelleumiaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.マリアベル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 35, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }

    @Test
    fun oliviaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.オリヴィエ__聖王国_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 25, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 25, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
    }


    @Test
    fun oliviaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.オリヴィエ__聖王国_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 21, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 14, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 21, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun libraTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リベラ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 7, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 33, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 7, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 35, fightResult[1].target.hp)
    }


    @Test
    fun libraRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.リベラ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 33, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 7, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
    }


}