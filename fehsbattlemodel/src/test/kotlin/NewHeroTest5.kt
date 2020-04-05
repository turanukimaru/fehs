import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest5 {

    @Test
    fun marTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.マルス__伝承英雄_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
    }

    @Test
    fun marRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.マルス__伝承英雄_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 33, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 38, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[2].target.hp)
    }

    @Test
    fun marTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.マルス__伝承英雄_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        unitB.spdSpur = 40
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
    }

    @Test
    fun marRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ノノ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        unitA.spdSpur = 40

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.マルス__伝承英雄_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 33, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 38, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[2].target.hp)
    }

    @Test
    fun jamTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ジャムカ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ゴードン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 27, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 27, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 40, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 26, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun jamRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ゴードン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ジャムカ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 4", 4, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 12, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 16, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 27, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[2].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        Assert.assertEquals("buildDamage fail", 40, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[3].target.hp)
    }

    @Test
    fun sTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.シルヴィア.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.ファ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 24, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 7, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 24, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun sRTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.ファ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        val unitB = ArmedHero(StandardBaseHero.get(HeroName.シルヴィア.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 0, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 29, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 17, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 40, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 29, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 40, fightResult[2].target.hp)
    }


    @Test
    fun qTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.キュアン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.シグルド.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 29, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 5, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
    }

    @Test
    fun qRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.シグルド.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.キュアン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 1, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 35, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 6, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 43, fightResult[1].target.hp)
    }


    @Test
    fun lewTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.レヴィン.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.リリーナ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 11, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 11, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 13, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 38, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 13, fightResult[2].target.hp)//ワンパンか…サーリャとかのがいいかな.いやサーリャ死んでるなぎむおか？
    }

    @Test
    fun lewRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(HeroName.リリーナ.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(HeroName.レヴィン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 38, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)//ワンパンか…サーリャとかのがいいかな
    }


}