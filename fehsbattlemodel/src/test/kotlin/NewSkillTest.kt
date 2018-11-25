import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.*
import org.junit.Assert
import org.junit.Test

/**
 * キラー武器・☆4+10のテスト
 * Created by turanukimaru on 2017/11/02.
 */
class NewSkillTest {

    @Test
    fun killerBow() {
        val unitA = ArmedHero(StandardBaseHero.get("レオ")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.armedHero.bSkill = SkillB.BowBreaker.lv(3)
        attacker.armedHero.equip()

        val unitB = ArmedHero(StandardBaseHero.get("リン（総選挙）")!!)
        val fightResult = attacker.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 28, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 17, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 28, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun lv410() {
        val unitA = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!, "ソフィーヤ10", Rtome.Fenrir2, Skill.NONE, Skill.NONE, Special.DragonFang, SkillA.WardingBlow.lv(3), Skill.NONE, Skill.NONE, Skill.NONE, 4,4
                , 10, BoonType.NONE, BoonType.NONE, false, 0, 0, 0, 0, 0, 0, 0, 0)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.armedHero.equip()

        val unitB = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!)
        val fightResult = attacker.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 19, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 21, fightResult[0].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        //明鏡の一撃で6減る…
        Assert.assertEquals("buildDamage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 32, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 21, fightResult[1].target.hp)
    }

    @Test
    fun solarBrace() {
        val unitA = ArmedHero(StandardBaseHero.get("エフラム（伝承英雄）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("エフラム（伝承英雄）")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 18, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 25, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 26, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 17, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 25, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("buildDamage fail", 18, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 17, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("buildDamage fail", 26, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 20, fightResult[3].target.hp)
        Assert.assertEquals("Noontime fail", Special.Sol, fightResult[3].sourceSpecial)
        Assert.assertEquals("size == 4", 4, fightResult.size)

    }


    @Test
    fun celicaTest() {
        val unitA = ArmedHero(StandardBaseHero.get("セリカ")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get("アイク")!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("buildDamage fail", 33, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("buildDamage fail", 29, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 10, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("buildDamage fail", 33, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 5, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

}