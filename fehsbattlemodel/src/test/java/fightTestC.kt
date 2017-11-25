import jp.blogspot.turanukimaru.fehs.ArmedClass
import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.SIDES
import jp.blogspot.turanukimaru.fehs.StandardBattleClass
import org.junit.Assert
import org.junit.Test

/**
 * モジュールを新規作成したときは他のプロジェクトに対応するようにbulid.gradleが作成されるが
 * libGDXのプロジェクトはtestディレクトリが無いので自分で作る必要がある
 * testディレクトリ以外ではjunitが参照できずテストが作れない。
 * Created by turanukimaru on 2017/11/02.
 */
class fightTestC {

    @Test
    fun specialPreventTestA() {
        val unitA = ArmedClass(StandardBattleClass.get("アイク")!!)
        val unitB = ArmedClass(StandardBattleClass.get("カイン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 24, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 8, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 17, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 1, fightResult[2].target.hp)
        Assert.assertEquals("size == 2", 3, fightResult.size)
    }

    @Test
    fun effectiveAgainstTestA() {
        val unitA = ArmedClass(StandardBattleClass.get("ロイ")!!)
        val unitB = ArmedClass(StandardBattleClass.get("カイン")!!)
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
        val unitA = ArmedClass(StandardBattleClass.get("ロイ")!!)
        val unitB = ArmedClass(StandardBattleClass.get("ジェイガン")!!)
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
        val unitA = ArmedClass(StandardBattleClass.get("ロイ")!!)
        val unitB = ArmedClass(StandardBattleClass.get("レイヴァン")!!)
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
    fun BraveTestA() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("エルトシャン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 21, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 16, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 21, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[3].target.hp)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[4].target.hp)
        Assert.assertEquals("size == 3", 5, fightResult.size)
    }

    @Test
    fun BraveTestB() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("シグルド")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 16, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 14, fightResult[3].target.hp)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 28, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 5, fightResult[4].target.hp)
        Assert.assertEquals("size == 3", 5, fightResult.size)
    }

    @Test
    fun BraveTestC() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("アイラ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 18, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 18, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 5, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 31, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 5, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 18, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].target.hp)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun BraveTestD() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("クロム（春）")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 3, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 3, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 35, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 9, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 35, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[3].target.hp)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun BraveTestE() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("ティアマト")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 0, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 0, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 31, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 31, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[3].target.hp)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun BraveTestF() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("アクア（舞踏祭）")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 5, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 30, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 5, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 25, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 42, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 2, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 25, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 42, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 25, fightResult[3].target.hp)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun BraveTestG() {
        val unitA = ArmedClass(StandardBattleClass.get("アベル")!!)
        val unitB = ArmedClass(StandardBattleClass.get("ローロー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 8, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 8, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 30, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 42, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 2, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 30, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 38, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 30, fightResult[3].target.hp)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

}