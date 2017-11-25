import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.Skill
import jp.blogspot.turanukimaru.fehs.skill.SkillC
import jp.blogspot.turanukimaru.fehs.skill.Special
import jp.blogspot.turanukimaru.fehs.skill.Weapon
import org.junit.Assert
import org.junit.Test

/**
 * モジュールを新規作成したときは他のプロジェクトに対応するようにbulid.gradleが作成されるが
 * libGDXのプロジェクトはtestディレクトリが無いので自分で作る必要がある
 * testディレクトリ以外ではjunitが参照できずテストが作れない。
 * Created by turanukimaru on 2017/11/02.
 */
class fightTestF {

    @Test
    fun killerBow() {
        val unitA = ArmedClass(StandardBattleClass.get("レオ")!!)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.armedClass.bSkill = SkillC.BowBreaker.lv(3)
        attacker.armedClass.equip()

        val unitB = ArmedClass(StandardBattleClass.get("リン（総選挙）")!!)
        val fightResult = attacker.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 25, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[0].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 17, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 25, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun lv410() {
        val unitA = ArmedClass(StandardBattleClass.get("ソフィーヤ")!!, "ソフィーヤ10", Weapon.Fenrir2, Skill.NONE, Special.DragonFang, SkillC.WardingBlow.lv(3), Skill.NONE, Skill.NONE, Skill.NONE, 4
                , 10, BoonType.NONE, BoonType.NONE, false, 0, 0, 0, 0, 0, 0, 0, 0)
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.armedClass.equip()

        val unitB = ArmedClass(StandardBattleClass.get("ソフィーヤ")!!)
        val fightResult = attacker.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 25, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[0].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 17, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[1].target.hp)
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 25, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 22, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }
}