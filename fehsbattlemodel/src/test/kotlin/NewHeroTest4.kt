import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest4 {

    @Test
    fun epTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.エフラム__総選挙_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.effect.buffDebuffTrigger = true

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.マルス.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 17, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 19, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 27, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 34, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 27, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun epRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.マルス.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.エフラム__総選挙_.jp)!!)
        unitB.buffDebuffTrigger = true
        val target = BattleUnit(unitB, unitB.maxHp)
        target.effect.buffDebuffTrigger = true
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        Assert.assertEquals("size == 4", 4, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 13, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 17, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 24, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 13, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 24, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 20, fightResult[2].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        Assert.assertEquals("buildDamage fail", 34, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 20, fightResult[3].target.hp)
    }

    @Test
    fun hecTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル__総選挙_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 4", 4, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 6, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 46, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 46, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 37, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[2].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 2, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[3].target.hp)
    }

    @Test
    fun hecRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヘクトル__総選挙_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 19, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 52, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 29, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 29, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 9, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 29, fightResult[2].target.hp)
    }

    @Test
    fun cerTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.セリカ__総選挙_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp - 1)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.セリカ__闇_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 26, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 13, fightResult[1].target.hp)
    }

    @Test
    fun cerTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.セリカ__総選挙_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        val unitB = ArmedHero(StandardBaseHero.get(HeroName.セリカ__闇_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 23, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].source.hp)// double lion -1
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
    }


    @Test
    fun cerRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.セリカ__闇_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.セリカ__総選挙_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 26, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 15, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 21, fightResult[1].source.hp)//武器でHP4減少
        Assert.assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
    }

    @Test
    fun verTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ヴェロニカ__総選挙_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 22, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 22, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
    }


    @Test
    fun verRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ヘンリー")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ヴェロニカ__総選挙_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 21, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 12, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[2].target.hp)
    }


}