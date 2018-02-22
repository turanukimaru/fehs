import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.SIDES
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import jp.blogspot.turanukimaru.fehs.skill.Special
import org.junit.Assert
import org.junit.Test

/**
 * 奥義によるダメージ減少・激化のテスト
 * Created by turanukimaru on 2017/11/02.
 */
class SpecialTest {

    @Test
    fun specialPreventTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("カイン")!!)
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
    fun specialPreventTestB() {
        val unitA = ArmedHero(StandardBaseHero.get("アルフォンス")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ジェイガン")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 18, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 22, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 21, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)
    }

    @Test
    fun noontimeTest() {
        val unitA = ArmedHero(StandardBaseHero.get("オグマ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ヘクトル")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 47, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 47, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 14, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 7, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[3].target.hp)
        Assert.assertEquals("Noontime fail", Special.Noontime, fightResult[3].sourceSpecial)
        Assert.assertEquals("Pavise fail", Special.Pavise, fightResult[3].targetSpecial)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 3, fightResult[4].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[5].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 14, fightResult[5].damage)
        Assert.assertEquals("hitPoint fail", 21, fightResult[5].source.hp)
        Assert.assertEquals("hitPoint fail", 3, fightResult[5].target.hp)
        Assert.assertEquals("size == 6", 6, fightResult.size)
    }

    @Test
    fun solTest() {
        val unitA = ArmedHero(StandardBaseHero.get("レイヴァン")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ウェンディ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        Assert.assertEquals("Pavise fail", Special.Escutcheon, fightResult[1].targetSpecial)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 8, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[3].target.hp)
        Assert.assertEquals("Noontime fail", Special.Sol, fightResult[3].sourceSpecial)
        Assert.assertEquals("Pavise fail", Special.Escutcheon, fightResult[3].targetSpecial)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[4].target.hp)
        Assert.assertEquals("size == 5", 5, fightResult.size)

    }

    @Test
    fun glimmerTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ルーナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ロンクー")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp * 3 / 4))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 8, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 29, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 15, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 12, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 17, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 18, fightResult[2].target.hp)
        Assert.assertEquals("Glimmer fail", Special.Glimmer, fightResult[2].sourceSpecial)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun astraTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アンナ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ウェンディ")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 2
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 11, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 28, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 5, fightResult[2].target.hp)
        Assert.assertEquals("targetSpecial fail", Special.Escutcheon, fightResult[2].targetSpecial)
        Assert.assertEquals("sourceSpecial fail", Special.Astra, fightResult[2].sourceSpecial)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun regnalAstraTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アベル")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アイラ")!!)
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
        Assert.assertEquals("RegnalAstra fail", Special.RegnalAstra, fightResult[2].sourceSpecial)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 18, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].target.hp)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun draconicAuraTest() {
        val unitA = ArmedHero(StandardBaseHero.get("カミラ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ウェンディ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 7, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 5, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 5, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 32, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 13, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 32, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[3].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.DraconicAura, fightResult[3].sourceSpecial)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 7, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 32, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[4].target.hp)
        Assert.assertEquals("size == 5", 5, fightResult.size)

    }

    @Test
    fun dragonFangTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ジークベルト")!!)
        val unitB = ArmedHero(StandardBaseHero.get("エフラム")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 2
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 29, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 30, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 11, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 29, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 46, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 11, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.DragonFang, fightResult[2].sourceSpecial)

    }

    @Test
    fun bonfireTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アベル")!!)
        val unitB = ArmedHero(StandardBaseHero.get("リズ（冬）")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 0, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 43, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 0, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 43, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 33, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 11, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 43, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 51, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 0, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 43, fightResult[3].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Bonfire, fightResult[3].sourceSpecial)
        Assert.assertEquals("size == 3", 4, fightResult.size)
    }

    @Test
    fun ignisTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ルフレ（女）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アーダン")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 3
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 44, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 39, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 5, fightResult[1].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Ignis, fightResult[1].sourceSpecial)
        Assert.assertEquals("size == 2", 2, fightResult.size)

    }

    @Test
    fun icebergTest() {
        val unitA = ArmedHero(StandardBaseHero.get("ゼロ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 6, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 6, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 31, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 26, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 31, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Iceberg, fightResult[2].sourceSpecial)
        Assert.assertEquals("size == 3", 3, fightResult.size)

    }

    @Test
    fun glaciesTest() {
        val unitA = ArmedHero(StandardBaseHero.get("フェリシア")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 2
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 5, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 35, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 8, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 35, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 35, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 26, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Glacies, fightResult[2].sourceSpecial)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun aetherTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 4
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 23, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 37, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 42, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Aether, fightResult[1].sourceSpecial)
        Assert.assertEquals("size == 2", 2, fightResult.size)

    }

    @Test
    fun radiantAetherTest() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク（伝承英雄）")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 3
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 24, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 38, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[1].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.RadiantAether, fightResult[1].sourceSpecial)
        Assert.assertEquals("size == 2", 2, fightResult.size)

    }

    @Test
    fun moonbowTest() {
        val unitA = ArmedHero(StandardBaseHero.get("エフラム")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アーダン")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 20, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 40, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 4, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 40, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 32, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Moonbow, fightResult[2].sourceSpecial)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 4, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 8, fightResult[3].target.hp)
        Assert.assertEquals("size == 4", 4, fightResult.size)

    }

    @Test
    fun lunaTest() {
        val unitA = ArmedHero(StandardBaseHero.get("カチュア")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アーダン")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 51, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 1, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 51, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 29, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 38, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Luna, fightResult[2].sourceSpecial)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 1, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 22, fightResult[3].target.hp)
        Assert.assertEquals("size == 4", 4, fightResult.size)

    }

    @Test
    fun blackLunaTest() {
        val unitA = ArmedHero(StandardBaseHero.get("漆黒の騎士")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アーダン")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 1
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 51, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 51, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 41, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.BlackLuna, fightResult[2].sourceSpecial)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 9, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 10, fightResult[3].target.hp)
        Assert.assertEquals("size == 4", 4, fightResult.size)

    }

    @Test
    fun reprisalTest() {
        val unitA = ArmedHero(StandardBaseHero.get("カレル")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ヘクトル")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        //uA.specialCount = 1
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 47, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 17, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 38, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 29, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Reprisal, fightResult[2].sourceSpecial)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 17, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 9, fightResult[3].target.hp)
        Assert.assertEquals("size == 4", 4, fightResult.size)

    }

    @Test
    fun vengeanceTest() {
        val unitA = ArmedHero(StandardBaseHero.get("サーリャ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ソフィーヤ")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 2
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 16, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 26, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 29, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 13, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Vengeance, fightResult[2].sourceSpecial)
        Assert.assertEquals("size == 3", 3, fightResult.size)

    }


    @Test
    fun miracleTest() {
        val unitA = ArmedHero(StandardBaseHero.get("デューテ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ヘクトル")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        uA.specialCount = 4
        val fightResult = uA.fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 26, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 32, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 1, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Miracle, fightResult[1].targetSpecial)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 26, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 1, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
        Assert.assertEquals("size == 3", 3, fightResult.size)
    }

    @Test
    fun sacredCowlTest() {
        val unitA = ArmedHero(StandardBaseHero.get("サーリャ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("エリウッド")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val uB = BattleUnit(unitB, unitB.maxHp)
        uB.specialCount = 1
        val fightResult = uA.fight(uB)
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 13, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 10, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.SacredCowl, fightResult[1].targetSpecial)
        Assert.assertEquals("size == 2", 2, fightResult.size)

    }

    @Test
    fun IceMirrorTest() {
        val unitA = ArmedHero(StandardBaseHero.get("マリク")!!)
        val unitB = ArmedHero(StandardBaseHero.get("フィヨルム")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val uB = BattleUnit(unitB, unitB.maxHp)
        uB.specialCount = 2
        val fightResult = uA.fight(uB)
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 5, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.IceMirror, fightResult[0].targetSpecial)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 18, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        Assert.assertEquals("size == 2", 2, fightResult.size)

    }

    @Test
    fun aegisTest() {
        val unitA = ArmedHero(StandardBaseHero.get("サーリャ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("アベル")!!)
        val uA = BattleUnit(unitA, unitA.maxHp)
        val uB = BattleUnit(unitB, unitB.maxHp)
        uB.specialCount = 2
        val fightResult = uA.fight(uB)
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 11, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 6, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 39, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 27, fightResult[1].target.hp)
        Assert.assertEquals("sourceSpecial fail", Special.Aegis, fightResult[1].targetSpecial)
        Assert.assertEquals("size == 2", 2, fightResult.size)

    }

    @Test
    fun escutcheonTest() {
        val unitA = ArmedHero(StandardBaseHero.get("レイヴァン")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ウェンディ")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 41, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        Assert.assertEquals("Pavise fail", Special.Escutcheon, fightResult[3].targetSpecial)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 8, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 9, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 19, fightResult[3].target.hp)
        Assert.assertEquals("Noontime fail", Special.Sol, fightResult[3].sourceSpecial)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 12, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 37, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 7, fightResult[4].target.hp)
        Assert.assertEquals("size == 5", 5, fightResult.size)

    }

    @Test
    fun paviseTest() {
        val unitA = ArmedHero(StandardBaseHero.get("オグマ")!!)
        val unitB = ArmedHero(StandardBaseHero.get("ヘクトル")!!)
        val fightResult = BattleUnit(unitA, unitA.maxHp).fight(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        Assert.assertEquals("attack side fail", fightResult[0].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[0].damage)
        Assert.assertEquals("hitPoint fail", 47, fightResult[0].source.hp)
        Assert.assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[1].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[1].damage)
        Assert.assertEquals("hitPoint fail", 47, fightResult[1].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[1].target.hp)
        println(fightResult[2])
        Assert.assertEquals("attack side fail", fightResult[2].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 14, fightResult[2].damage)
        Assert.assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        Assert.assertEquals("hitPoint fail", 24, fightResult[2].target.hp)
        Assert.assertEquals("size == 6", 6, fightResult.size)
        println(fightResult[3])
        Assert.assertEquals("attack side fail", fightResult[3].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 7, fightResult[3].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[3].source.hp)
        Assert.assertEquals("hitPoint fail", 17, fightResult[3].target.hp)
        Assert.assertEquals("Noontime fail", Special.Noontime, fightResult[3].sourceSpecial)
        Assert.assertEquals("Pavise fail", Special.Pavise, fightResult[3].targetSpecial)
        println(fightResult[4])
        Assert.assertEquals("attack side fail", fightResult[4].side, SIDES.ATTACKER)
        Assert.assertEquals("damage fail", 14, fightResult[4].damage)
        Assert.assertEquals("hitPoint fail", 35, fightResult[4].source.hp)
        Assert.assertEquals("hitPoint fail", 3, fightResult[4].target.hp)
        println(fightResult[1])
        Assert.assertEquals("attack side fail", fightResult[5].side, SIDES.COUNTER)
        Assert.assertEquals("damage fail", 14, fightResult[5].damage)
        Assert.assertEquals("hitPoint fail", 21, fightResult[5].source.hp)
        Assert.assertEquals("hitPoint fail", 3, fightResult[5].target.hp)
        Assert.assertEquals("size == 6", 6, fightResult.size)

    }
}