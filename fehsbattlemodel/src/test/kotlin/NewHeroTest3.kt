import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class NewHeroTest3 {

    @Test
    fun mikayaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ミカヤ__夏祭り_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ヘクトル.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 72, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }

    @Test
    fun mikayaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リン__バレンタイン_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ミカヤ__夏祭り_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 17, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 27, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 10, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 17, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 10, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun ryomaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リョウマ__夏祭り_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.漆黒の騎士.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 51, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }

    @Test
    fun ryomaTest2() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リョウマ__夏祭り_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ヘンリー__収穫祭_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        fightResult.forEach { println(it) }
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 46, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
    }


    @Test
    fun ryomaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ヘンリー__収穫祭_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.リョウマ__夏祭り_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 46, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }

    @Test
    fun xandarTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.マークス__夏祭り_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("ウルスラ")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 51, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }


    @Test
    fun xandarRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("セシリア")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.マークス__夏祭り_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 1", 1, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 39, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
    }

    @Test
    fun elinciaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エリンシア__夏祭り_.jp)!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.チキ__大人_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 21, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 34, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
    }


    @Test
    fun elinciaRTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ヘンリー")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.エリンシア__夏祭り_.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 19, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 5, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 5, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[2].target.hp)//push 1 loss
    }


}