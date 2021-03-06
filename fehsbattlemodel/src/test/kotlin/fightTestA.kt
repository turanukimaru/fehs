import jp.blogspot.turanukimaru.fehs.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 主に速さ・スキルを使用した際の行動順のテスト
 * モジュールを新規作成したときは他のプロジェクトに対応するようにbulid.gradleが作成されるが
 * libGDXのプロジェクトはtestディレクトリが無いので自分で作る必要がある
 * testディレクトリ以外ではjunitが参照できずテストが作れない。
 * Created by turanukimaru on 2017/11/02.
 */
class fightTestA {
    @Test
    fun noSkillSpeedTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("マルス（仮面）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス（仮面）")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        assertEquals("size == 2", 2, fightResult.size)
        println(fightResult[0])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 25, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        println(fightResult[1])
        assertEquals("buildDamage fail", 25, fightResult[1].damage)
        assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
    }

    @Test
    fun noSkillSpeedTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("マルス（仮面）")!!)
        val kazahanaA = ArmedHero(StandardBaseHero.get("カザハナ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(kazahanaA, kazahanaA.maxHp))
        assertEquals("size == 3", 3, fightResult.size)
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 32, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 27, fightResult[1].damage)
        assertEquals("hitPoint fail", 16, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 27, fightResult[2].damage)
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[2].target.hp)
        val fightResultC = BattleUnit(kazahanaA, kazahanaA.maxHp).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResultC[0])
        println(fightResultC[1])
        println(fightResultC[2])
        assertEquals("size == 3", 3, fightResultC.size)
        assertEquals("attack side fail", fightResultC[0].side, SIDES.ATTACKER)
        assertEquals("attack side fail", fightResultC[1].side, SIDES.COUNTER)
        assertEquals("attack side fail", fightResultC[2].side, SIDES.ATTACKER)
    }

    @Test
    fun noSkillSpeedTestC() {
        val unitA = ArmedHero(StandardBaseHero.get("マルス（仮面）")!!)

        val grayA = ArmedHero(StandardBaseHero.get("グレイ")!!)
        val fightPlan = FightPlan(BattleUnit(grayA, grayA.maxHp), BattleUnit(unitA, unitA.maxHp))
        val fightResult = fightPlan.fight()
        assertEquals("size == 2", 2, fightResult.size)

        val fightPlanD = FightPlan(BattleUnit(unitA, unitA.maxHp), BattleUnit(grayA, grayA.maxHp))
        val fightResultD = fightPlanD.fight()
        assertEquals("size == 2", 2, fightResultD.size)
    }

    @Test
    fun noBraveTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("マルス（仮面）")!!)
        val dogaA = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(dogaA, dogaA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 13, fightResult[1].damage)
        assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 11, fightResult[2].damage)
        assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun braveTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("マルス（仮面）")!!)
        val dogaA = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(dogaA, dogaA.maxHp).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 13, fightResult[0].damage)
        assertEquals("hitPoint fail", 50, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 30, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 13, fightResult[1].damage)
        assertEquals("hitPoint fail", 50, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 11, fightResult[2].damage)
        assertEquals("hitPoint fail", 39, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[2].target.hp)
        println(fightResult[3])
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        assertEquals("buildDamage fail", 6, fightResult[3].damage)
        assertEquals("hitPoint fail", 33, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[3].target.hp)
        assertEquals("size == 4", 4, fightResult.size)
    }

    @Test
    fun braveTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("エリンシア")!!)
        val dogaA = ArmedHero(StandardBaseHero.get("ドーガ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(dogaA, dogaA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 12, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[2].target.hp)
        println(fightResult[3])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[3].side)
        assertEquals("buildDamage fail", 6, fightResult[3].damage)
        assertEquals("hitPoint fail", 21, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[3].target.hp)
        println(fightResult[4])
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[4].side)
        assertEquals("buildDamage fail", 12, fightResult[4].damage)
        assertEquals("hitPoint fail", 21, fightResult[4].source.hp)
        assertEquals("hitPoint fail", 8, fightResult[4].target.hp)
        assertEquals("size == 5", 5, fightResult.size)
    }

    @Test
    fun vantageTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp * 3 / 4))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 8, fightResult[0].damage)
        assertEquals("hitPoint fail", 29, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 17, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun vantageTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp * 3 / 4 + 1))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 15, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 8, fightResult[2].damage)
        assertEquals("hitPoint fail", 17, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun vantageTestC() {
        val unitA = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitB, unitB.maxHp * 3 / 4).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 8, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 18, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 18, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun vantageTestD() {
        val unitA = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitB, unitB.maxHp * 3 / 4 + 1).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 8, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 19, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun desperationTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("アルム")!!)
        val unitB = ArmedHero(StandardBaseHero.get("シャニー")!!)
        val fightResult = BattleUnit(unitB, unitB.maxHp * 3 / 4).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 21, fightResult[0].damage)
        assertEquals("hitPoint fail", 29, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 21, fightResult[1].damage)
        assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 17, fightResult[2].damage)
        assertEquals("hitPoint fail", 12, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun desperationTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("アルム")!!)
        val unitB = ArmedHero(StandardBaseHero.get("シャニー")!!)
        val fightResult = BattleUnit(unitB, unitB.maxHp * 3 / 4 + 1).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 21, fightResult[0].damage)
        assertEquals("hitPoint fail", 30, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 17, fightResult[1].damage)
        assertEquals("hitPoint fail", 13, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 35, fightResult[2].damage)
        assertEquals("hitPoint fail", 13, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun desperationTestC() {
        val unitA = ArmedHero(StandardBaseHero.get("グレイ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("シャニー")!!)
        val fightResultA = BattleUnit(unitB, unitB.maxHp * 3 / 4).fight(BattleUnit(unitA, unitA.maxHp))
        assertEquals("size == 2", 2, fightResultA.size)
        val fightResultB = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp * 3 / 4))
        assertEquals("size == 2", 2, fightResultB.size)
    }

    @Test
    fun quickRiposteTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("ツバキ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("シャニー")!!)
        val fightResult = BattleUnit(unitB, unitB.maxHp).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 6, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 27, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun quickRiposteTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("クレイン")!!)
        val unitB = ArmedHero(StandardBaseHero.get("オルエン")!!)
        val fightResult = BattleUnit(unitB, unitB.maxHp).fight(BattleUnit(unitA, unitA.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 11, fightResult[1].damage)
        assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 16, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[2].target.hp)
        println(fightResult[3])
        assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 18, fightResult[3].damage)
        assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[3].target.hp)
        assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun quickRiposteTestC() {
        val unitA = ArmedHero(StandardBaseHero.get("クレイン")!!)
        val unitB = ArmedHero(StandardBaseHero.get("オルエン")!!)
        val buffedUnitA = BattleUnit(unitA, unitA.maxHp)
        val buffedUnitB = BattleUnit(unitB, unitB.maxHp)
        buffedUnitA.resBuff = 4
        buffedUnitB.spdBuff = 4
        val fightResult = buffedUnitB.fight(buffedUnitA)
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 7, fightResult[1].damage)
        assertEquals("hitPoint fail", 34, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 16, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[2].target.hp)
        println(fightResult[3])
        assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 7, fightResult[3].damage)
        assertEquals("hitPoint fail", 16, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[3].target.hp)
        println(fightResult[4])
        assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 7, fightResult[4].damage)
        assertEquals("hitPoint fail", 16, fightResult[4].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[4].target.hp)
        println(fightResult[5])
        //※奥義発動して＋22ダメージ.バフで威力上がって24+4の8割か
        assertEquals("attack side fail", fightResult[5].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 40, fightResult[5].damage)
        assertEquals("hitPoint fail", 0, fightResult[5].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[5].target.hp)
        assertEquals("size == 3", 6, fightResult.size)
    }

    @Test
    fun brashAssaultTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("ヒナタ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 23, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 8, fightResult[1].damage)
        assertEquals("hitPoint fail", 15, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[2].target.hp)
        println(fightResult[3])
        assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 4, fightResult[3].damage)
        assertEquals("hitPoint fail", 11, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[3].target.hp)
        assertEquals("size == 4", 4, fightResult.size)
    }

    @Test
    fun brashAssaultTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("ヒナタ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp / 2 + 1).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 24, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 8, fightResult[1].damage)
        assertEquals("hitPoint fail", 16, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 8, fightResult[2].damage)
        assertEquals("hitPoint fail", 8, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun brashAssaultTestC() {
        val unitB = ArmedHero(StandardBaseHero.get("ヒナタ")!!)
        val unitA = ArmedHero(StandardBaseHero.get("マルス")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp / 2))
        println(fightResult[0])
        assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 8, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[0].target.hp)
        println(fightResult[1])
        assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        assertEquals("buildDamage fail", 18, fightResult[1].damage)
        assertEquals("hitPoint fail", 23, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 15, fightResult[1].target.hp)
        println(fightResult[2])
        assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        assertEquals("buildDamage fail", 8, fightResult[2].damage)
        assertEquals("hitPoint fail", 23, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 7, fightResult[2].target.hp)
        assertEquals("size == 3", 3, fightResult.size)
    }


}
