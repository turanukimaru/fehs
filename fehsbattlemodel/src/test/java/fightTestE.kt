import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert
import org.junit.Test

/**
 * モジュールを新規作成したときは他のプロジェクトに対応するようにbulid.gradleが作成されるが
 * libGDXのプロジェクトはtestディレクトリが無いので自分で作る必要がある
 * testディレクトリ以外ではjunitが参照できずテストが作れない。
 * Created by turanukimaru on 2017/11/02.
 */
class fightTestE {
    @Test
    fun noSkillSpeedTestA() {
        val unitA =ArmedClass(StandardBattleClass.get("マルス（仮面）")!!)
        unitA.boon = BoonType.ATK
        unitA.bane = BoonType.RES
        val unitB =ArmedClass(StandardBattleClass.get("マルス（仮面）")!!)
        unitB.boon = BoonType.SPD
        unitB.bane = BoonType.DEF
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        Assert.assertEquals("size == 2", 2, fightResult.size)
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 31, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        println(fightResult[1])
        Assert.assertEquals("damage fail", 25, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 12, fightResult[1].target.hp)
    }
    @Test
    fun noSkillSpeedTestB() {
        val unitA =ArmedClass(StandardBattleClass.get("マルス（仮面）")!!)
        unitA.boon = BoonType.SPD
        unitA.bane = BoonType.RES
        val unitB =ArmedClass(StandardBattleClass.get("マルス（仮面）")!!)
        unitB.boon = BoonType.DEF
        unitB.bane = BoonType.RES
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        Assert.assertEquals("size == 2", 2, fightResult.size)
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 21, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        println(fightResult[1])
        Assert.assertEquals("damage fail", 25, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
    }
    @Test
    fun noSkillSpeedTestC() {
        val unitA =ArmedClass(StandardBattleClass.get("アイク")!!)

        val unitB =ArmedClass(StandardBattleClass.get("ゼフィール")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 18, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 24, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 18, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[2].target.hp)
        Assert.assertEquals("size == 2", 3, fightResult.size)
    }
    @Test
    fun buffBladeTestA() {
        val classA =ArmedClass(StandardBattleClass.get("ニノ")!!)
        val unitA = BattleUnit(classA, classA.maxHp)
        unitA.atkBuff = 4
        unitA.spdBuff = 4
        unitA.defBuff = 4
        unitA.resBuff = 4

        val unitB =ArmedClass(StandardBattleClass.get("アイク")!!)
        val fightResult = unitA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 35, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 38, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }
    @Test
    fun buffBladeTestB() {
        val classA =ArmedClass(StandardBattleClass.get("ニノ")!!)
        val unitA = BattleUnit(classA, classA.maxHp)
        unitA.atkBuff = 4
        unitA.spdBuff = 4
        unitA.defBuff = 4
        unitA.resBuff = 4

        val unitB =ArmedClass(StandardBattleClass.get("ディアドラ")!!)
        val fightResult = unitA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 11, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 25, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 24, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 18, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[2].target.hp)
        Assert.assertEquals("size == 2", 3, fightResult.size)
    }
    @Test
    fun ravenTestA() {
        val unitA =ArmedClass(StandardBattleClass.get("セシリア")!!)

        val unitB =ArmedClass(StandardBattleClass.get("アイク")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 19, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 24, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 18, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[2].target.hp)
        Assert.assertEquals("size == 2", 3, fightResult.size)
    }
    @Test
    fun ravenTestB() {//TODO:激化のダメージが4ほど大きいので計算の確認が必要
        val unitA =ArmedClass(StandardBattleClass.get("セシリア")!!)

        val unitB =ArmedClass(StandardBattleClass.get("ヴィオール")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 42, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 14, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 18, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[2].target.hp)
        Assert.assertEquals("size == 2", 3, fightResult.size)
    }
    @Test
    fun ravenTestC() {
        val unitA =ArmedClass(StandardBattleClass.get("ヴィオール")!!)

        val unitB =ArmedClass(StandardBattleClass.get("セシリア")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        Assert.assertEquals("damage fail", 42, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 4, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        Assert.assertEquals("damage fail", 14, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 4, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[2].target.hp)
        Assert.assertEquals("size == 2", 3, fightResult.size)
    }
    @Test
    fun ravenTestD() {
        val unitA =ArmedClass(StandardBattleClass.get("セシリア")!!)
val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.armedClass.aSkill = Skills.TriangleAdept.lv(3)
        attacker.armedClass.equip()
        val unitB =ArmedClass(StandardBattleClass.get("ヴィオール")!!)
        val fightResult = attacker.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        Assert.assertEquals("damage fail", 47, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[0].target.hp)
        Assert.assertEquals("size == 2", 1, fightResult.size)
    }
}