import jp.blogspot.turanukimaru.fehs.*
import jp.blogspot.turanukimaru.fehs.skill.Axe
import jp.blogspot.turanukimaru.fehs.skill.Lance
import jp.blogspot.turanukimaru.fehs.skill.RefinedSkill
import jp.blogspot.turanukimaru.fehs.skill.Special
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * これからは新キャラのテストをちゃんとやっていきたい
 * Created by turanukimaru on 2017/11/02.
 */
class RefinedHeroTest1 {

    @Test
    fun elTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エルトシャン.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Mystletainn
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 48, unitA.maxHp)
        assertEquals("atk", 54, unitA.atk)
        assertEquals("spd", 30, unitA.spd)
        assertEquals("def", 40, unitA.def)
        assertEquals("res", 25, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.エフラム.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 12, fightResult[0].damage)
        assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 21, fightResult[1].damage)
        assertEquals("hitPoint fail", 27, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 15, fightResult[2].source.hp)//furry12damage
        assertEquals("hitPoint fail", 21, fightResult[2].target.hp)
    }

    @Test
    fun snTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シャニー2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.ShannasLance
        unitA.equip()
        assertEquals("maxHp", 42, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 29, unitA.res)
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 16, fightResult[0].damage)
        assertEquals("hitPoint fail", 42, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 36, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 40, fightResult[2].damage)
        assertEquals("hitPoint fail", 36, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun btTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.バアトル2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.AxeOfVirility
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 52, unitA.maxHp)
        assertEquals("atk", 58, unitA.atk)
        assertEquals("spd", 31, unitA.spd)
        assertEquals("def", 39, unitA.def)
        assertEquals("res", 19, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 31, fightResult[0].damage)
        assertEquals("hitPoint fail", 52, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 34, fightResult[1].source.hp)//fury
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
    }

    @Test
    fun flTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フロリーナ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.FlorinasLance
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 45, unitA.maxHp)
        assertEquals("atk", 45, unitA.atk)
        assertEquals("spd", 27, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 41, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 39, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 41, fightResult[2].damage)
        assertEquals("hitPoint fail", 39, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }


    @Test
    fun flRTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フロリーナ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.FlorinasLance
        unitA.equip()

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val attacker = BattleUnit(unitB, unitB.maxHp)

        val target = BattleUnit(unitA, unitA.maxHp)
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 2, fightResult[0].damage)
        assertEquals("hitPoint fail", 50, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 43, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 2, fightResult[1].damage)
        assertEquals("hitPoint fail", 50, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 41, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 41, fightResult[2].damage)
        assertEquals("hitPoint fail", 9, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 41, fightResult[2].target.hp)
    }

    @Test
    fun lndTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リンダ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Aura
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 35, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 39, unitA.spd)
        assertEquals("def", 14, unitA.def)
        assertEquals("res", 27, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 29, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 10, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 29, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun lnd2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リンダ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Aura
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 35, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 10, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 5, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 35, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun ryTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ロイ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.BindingBlade2
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 47, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 31, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 28, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.シーマ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 28, fightResult[0].damage)
        assertEquals("hitPoint fail", 47, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 47, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 20, fightResult[2].damage)
        assertEquals("hitPoint fail", 47, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun ry2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ロイ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.BindingBlade2
        unitA.equip()
        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val attacker = BattleUnit(unitB, unitB.maxHp)
        val target = BattleUnit(unitA, unitA.maxHp)
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        assertEquals("size == 4", 4, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 9, fightResult[0].damage)
        assertEquals("hitPoint fail", 50, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 38, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 9, fightResult[1].damage)
        assertEquals("hitPoint fail", 50, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 7, fightResult[2].damage)
        assertEquals("hitPoint fail", 43, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[2].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        assertEquals("buildDamage fail", 4, fightResult[3].damage)
        assertEquals("hitPoint fail", 39, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 29, fightResult[3].target.hp)
    }

    @Test
    fun rvTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.レイヴァン2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Basilikos
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 44, unitA.maxHp)
        assertEquals("atk", 55, unitA.atk)
        assertEquals("spd", 40, unitA.spd)
        assertEquals("def", 20, unitA.def)
        assertEquals("res", 17, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 5, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 45, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 25, fightResult[1].damage)
        assertEquals("hitPoint fail", 19, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 45, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 5, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)//太陽
        assertEquals("hitPoint fail", 40, fightResult[2].target.hp)
    }

    @Test
    fun tkTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.チキ__幼_2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.BreathOfFog
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 44, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 30, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("res", 29, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 10, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 11, fightResult[1].damage)
        assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 7, fightResult[2].damage)
        assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[2].target.hp)
    }

    @Test
    fun tk2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.チキ__幼_2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.BreathOfFog
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 38, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 35, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 10, fightResult[2].damage)
        assertEquals("hitPoint fail", 38, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[2].target.hp)
    }


    @Test
    fun leoTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.レオン.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Brynhildr
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.リンダ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
    }


    @Test
    fun cmTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.カミラ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.CamillasAxe
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 32, unitA.spd)
        assertEquals("def", 28, unitA.def)
        assertEquals("res", 31, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 17, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)//奥義か…
        assertEquals("hitPoint fail", 35, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 20, fightResult[2].target.hp)
    }

    @Test
    fun cm2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.カミラ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.CamillasAxe
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 27, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 35, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 27, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 16, fightResult[2].damage)//奥義か…
        assertEquals("hitPoint fail", 35, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 11, fightResult[2].target.hp)
    }

    @Test
    fun ccTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.セルジュ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.CherchesAxe
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 49, unitA.maxHp)
        assertEquals("atk", 52, unitA.atk)
        assertEquals("spd", 20, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("res", 16, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 4", 4, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 49, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 3, fightResult[1].damage)
        assertEquals("hitPoint fail", 49, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 13, fightResult[2].damage)
        assertEquals("hitPoint fail", 36, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[2].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[3].side)
        assertEquals("buildDamage fail", 13, fightResult[3].damage)
        assertEquals("hitPoint fail", 23, fightResult[3].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[3].target.hp)
    }


    @Test
    fun skTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.サナキ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Cymbeline
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 33, unitA.maxHp)
        assertEquals("atk", 51, unitA.atk)
        assertEquals("spd", 26, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("res", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 21, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 21, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun sk2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.サナキ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Cymbeline
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1
        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 27, fightResult[0].damage)
        assertEquals("hitPoint fail", 33, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 7, fightResult[1].damage)
        assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 13, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 27, fightResult[2].damage)
        assertEquals("hitPoint fail", 26, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }


    @Test
    fun nfTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ネフェニー2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.DauntlessLance
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 49, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 34, unitA.def)
        assertEquals("res", 20, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 48, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 2, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 2, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 59, fightResult[2].damage)//奥義なんだっけ？月虹？
        assertEquals("hitPoint fail", 40, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun nf2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ネフェニー2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.DauntlessLance
        unitA.equip()
        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val attacker = BattleUnit(unitB, unitB.maxHp)
val target = BattleUnit(unitA, unitA.maxHp)
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 49, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 33, fightResult[1].damage)
        assertEquals("hitPoint fail", 16, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 35, fightResult[2].damage)//奥義なんだっけ？月虹？
        assertEquals("hitPoint fail", 0, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[2].target.hp)
    }


    @Test
    fun jfTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ジャファル.jp)!!)
        unitA.refinedWeapon = RefinedSkill.DeathlyDagger2
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 41, unitA.maxHp)
        assertEquals("atk", 45, unitA.atk)
        assertEquals("spd", 38, unitA.spd)
        assertEquals("def", 20, unitA.def)
        assertEquals("res", 17, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 17, fightResult[0].damage)
        assertEquals("hitPoint fail", 41, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 17, fightResult[1].damage)
        assertEquals("hitPoint fail", 41, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[1].target.hp)//-10
    }


    @Test
    fun tmTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ティアマト2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.DraconicPoleax
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 44, unitA.atk)
        assertEquals("spd", 34, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 30, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 23, fightResult[2].damage)
        assertEquals("hitPoint fail", 40, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 3, fightResult[2].target.hp)
    }

    @Test
    fun zfTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ゼフィール.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Eckesachs
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 58, unitA.maxHp)
        assertEquals("atk", 56, unitA.atk)
        assertEquals("spd", 21, unitA.spd)
        assertEquals("def", 33, unitA.def)
        assertEquals("res", 19, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 17, fightResult[0].damage)
        assertEquals("hitPoint fail", 58, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 53, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
    }

    @Test
    fun zf2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ゼフィール.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Eckesachs
        unitA.equip()
        val unitB = ArmedHero(StandardBaseHero.get(Name.リンダ.jp)!!)
        val attacker = BattleUnit(unitB, unitB.maxHp)
        val target = BattleUnit(unitA, unitA.maxHp)
        val fightResult = attacker.fightAndAfterEffect(target)
        println(fightResult[0])
        assertEquals("size == 1", 1, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 33, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[0].target.hp)
    }

    @Test
    fun mcTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.マリク.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Excalibur
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 48, unitA.maxHp)
        assertEquals("atk", 40, unitA.atk)
        assertEquals("spd", 32, unitA.spd)
        assertEquals("def", 28, unitA.def)
        assertEquals("res", 19, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 36, fightResult[1].damage)
        assertEquals("hitPoint fail", 12, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 37, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 3, fightResult[2].damage)
        assertEquals("hitPoint fail", 12, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 34, fightResult[2].target.hp)
    }

    @Test
    fun mc2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.マリク.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Excalibur
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1
        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 7, fightResult[0].damage)
        assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 36, fightResult[1].damage)
        assertEquals("hitPoint fail", 12, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 7, fightResult[2].damage)
        assertEquals("hitPoint fail", 12, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[2].target.hp)
    }

    @Test
    fun frTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フェリシア2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.FeliciasPlate
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 34, unitA.maxHp)
        assertEquals("atk", 37, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 18, unitA.def)
        assertEquals("res", 38, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 9, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 8, fightResult[1].damage)
        assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 39, fightResult[2].damage)//奥義たまるのか…
        assertEquals("hitPoint fail", 26, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun srTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シャロン.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Fensalir
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 46, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 29, unitA.def)
        assertEquals("res", 22, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 2, fightResult[1].damage)
        assertEquals("hitPoint fail", 44, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 44, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[2].target.hp)
    }
    @Test
    fun sr2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シャロン.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Fensalir
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
attacker.adjacentUnits = 1
        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 46, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 46, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[2].target.hp)
    }

    @Test
    fun alTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.アルフォンス.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Folkvangr
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 46, unitA.maxHp)
        assertEquals("atk", 51, unitA.atk)
        assertEquals("spd", 25, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("res", 22, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 32, fightResult[1].target.hp)
    }
    @Test
    fun al2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.アルフォンス.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Folkvangr
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.シーマ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 2", 2, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 43, fightResult[0].damage)
        assertEquals("hitPoint fail", 46, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 2, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 46, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 2, fightResult[1].target.hp)
    }

    @Test
    fun llTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リリーナ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Forblaze
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 35, unitA.maxHp)
        assertEquals("atk", 54, unitA.atk)
        assertEquals("spd", 25, unitA.spd)
        assertEquals("def", 19, unitA.def)
        assertEquals("res", 31, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 31, fightResult[0].damage)
        assertEquals("hitPoint fail", 35, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 9, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 31, fightResult[2].damage)
        assertEquals("hitPoint fail", 20, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun tkmTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.タクミ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.FujinYumi
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 33, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 18, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 28, fightResult[1].damage)
        assertEquals("hitPoint fail", 12, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 12, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 4, fightResult[2].target.hp)
    }

    @Test
    fun mnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ミネルバ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Hauteclere
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 43, unitA.maxHp)
        assertEquals("atk", 52, unitA.atk)
        assertEquals("spd", 38, unitA.spd)
        assertEquals("def", 27, unitA.def)
        assertEquals("res", 17, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 18, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 3, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[2].target.hp)
    }

    @Test
    fun mn2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ミネルバ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Hauteclere
        unitA.special = Special.Moonbow
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 18, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 24, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 23, fightResult[2].target.hp)
    }

    @Test
    fun hnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ヒノカ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.HinokasSpear
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 44, unitA.maxHp)
        assertEquals("atk", 51, unitA.atk)
        assertEquals("spd", 32, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 24, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 6, fightResult[1].damage)
        assertEquals("hitPoint fail", 38, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
            assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 38, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[2].target.hp)
    }

    @Test
    fun firTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フィル2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.NamelessBlade
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 44, unitA.maxHp)
        assertEquals("atk", 41, unitA.atk)
        assertEquals("spd", 39, unitA.spd)
        assertEquals("def", 24, unitA.def)
        assertEquals("res", 31, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 2, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 48, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 48, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 2, fightResult[2].damage)
        assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 46, fightResult[2].target.hp)
    }

    @Test
    fun fir2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.フィル2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.NamelessBlade
        unitA.special = Special.Moonbow
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)


        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 2, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 48, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 30, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 48, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 23, fightResult[2].damage)
        assertEquals("hitPoint fail", 30, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 25, fightResult[2].target.hp)
    }

    @Test
    fun annaTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.アンナ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Noatun
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 44, unitA.maxHp)
        assertEquals("atk", 45, unitA.atk)
        assertEquals("spd", 38, unitA.spd)
        assertEquals("def", 22, unitA.def)
        assertEquals("res", 28, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ウェンディ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 16, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 11, fightResult[1].damage)
        assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 33, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 12, fightResult[2].damage)
        assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 21, fightResult[2].target.hp)
    }

    @Test
    fun odTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.オーディン2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.OdinsGrimoire
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 43, unitA.maxHp)
        assertEquals("atk", 36, unitA.atk)
        assertEquals("spd", 32, unitA.spd)
        assertEquals("def", 25, unitA.def)
        assertEquals("res", 25, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 43, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 31, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 26, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 31, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 12, fightResult[2].target.hp)
    }

    @Test
    fun jrTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ジョルジュ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Parthia2
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 37, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 32, unitA.spd)
        assertEquals("def", 24, unitA.def)
        assertEquals("res", 22, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 37, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 17, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 24, fightResult[2].damage)
        assertEquals("hitPoint fail", 20, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun clcTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.セリカ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Ragnarok
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 33, unitA.spd)
        assertEquals("def", 22, unitA.def)
        assertEquals("res", 22, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 24, fightResult[1].damage)
        assertEquals("hitPoint fail", 15, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 18, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 10, fightResult[2].source.hp)//雷なロックの反動で-5
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }
    @Test
    fun clc2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.セリカ.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Ragnarok
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp *7 / 10)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 24, fightResult[0].damage)
        assertEquals("hitPoint fail", 27, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 24, fightResult[1].damage)
        assertEquals("hitPoint fail", 3, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 16, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 24, fightResult[2].damage)
        assertEquals("hitPoint fail", 3, fightResult[2].source.hp)//雷なロックの反動なし
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }


    @Test
    fun ktTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.カタリナ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.ReesesTome
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 34, unitA.maxHp)
        assertEquals("atk", 46, unitA.atk)
        assertEquals("spd", 34, unitA.spd)
        assertEquals("def", 15, unitA.def)
        assertEquals("res", 32, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 21, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 21, fightResult[2].damage)
        assertEquals("hitPoint fail", 20, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun kt2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.カタリナ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.ReesesTome
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1
        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 23, fightResult[0].damage)
        assertEquals("hitPoint fail", 34, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 22, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 17, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 23, fightResult[2].damage)
        assertEquals("hitPoint fail", 22, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun lydTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ロイド.jp)!!)
        unitA.refinedWeapon = RefinedSkill.RegalBlade
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 44, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 20, unitA.def)
        assertEquals("res", 30, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 18, fightResult[1].damage)
        assertEquals("hitPoint fail", 26, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 11, fightResult[2].damage)
        assertEquals("hitPoint fail", 26, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
    }

    @Test
    fun lyd2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.ロイド.jp)!!)
        unitA.refinedWeapon = RefinedSkill.RegalBlade
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        attacker.adjacentUnits = 1

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 14, fightResult[0].damage)
        assertEquals("hitPoint fail", 44, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 36, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 15, fightResult[1].damage)
        assertEquals("hitPoint fail", 29, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 36, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 14, fightResult[2].damage)
        assertEquals("hitPoint fail", 29, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 22, fightResult[2].target.hp)
    }

    @Test
    fun clTest() {
            val unitA = ArmedHero(StandardBaseHero.get(Name.クレア2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Rhomphaia
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 42, unitA.atk)
        assertEquals("spd", 36, unitA.spd)
        assertEquals("def", 24, unitA.def)
        assertEquals("res", 33, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 36, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 7, fightResult[1].damage)
        assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 36, fightResult[2].damage)
        assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun cl2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.クレア2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Rhomphaia
        unitA.equip()
        unitA.special = Special.Luna
        val attacker = BattleUnit(unitA, unitA.maxHp)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 36, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 7, fightResult[1].damage)
        assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 14, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 55, fightResult[2].damage)
        assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun epTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エフラム.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Siegmund
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 48, unitA.maxHp)
        assertEquals("atk", 51, unitA.atk)
        assertEquals("spd", 25, unitA.spd)
        assertEquals("def", 32, unitA.def)
        assertEquals("res", 20, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 48, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 0, fightResult[1].damage)
        assertEquals("hitPoint fail", 48, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 33, fightResult[2].damage)//月虹
        assertEquals("hitPoint fail", 48, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }

    @Test
    fun erTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.エイリーク.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Sieglinde
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 45, unitA.maxHp)
        assertEquals("atk", 42, unitA.atk)
        assertEquals("spd", 35, unitA.spd)
        assertEquals("def", 26, unitA.def)
        assertEquals("res", 28, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 3, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 33, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 47, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 3, fightResult[2].damage)
        assertEquals("hitPoint fail", 33, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 44, fightResult[2].target.hp)
    }

    @Test
    fun rnTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リン.jp)!!)
        unitA.refinedWeapon = RefinedSkill.SolKatti
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 40, unitA.maxHp)
        assertEquals("atk", 44, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 26, unitA.def)
        assertEquals("res", 29, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 5, fightResult[0].damage)
        assertEquals("hitPoint fail", 40, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 45, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 12, fightResult[1].damage)
        assertEquals("hitPoint fail", 28, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 45, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 5, fightResult[2].damage)
        assertEquals("hitPoint fail", 28, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 40, fightResult[2].target.hp)
    }

    @Test
    fun rn2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.リン.jp)!!)
        unitA.refinedWeapon = RefinedSkill.SolKatti
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp / 2)

        val unitB = ArmedHero(StandardBaseHero.get(Name.リン.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 18, fightResult[0].damage)
        assertEquals("hitPoint fail", 20, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 19, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[1].side)
        assertEquals("buildDamage fail", 18, fightResult[1].damage)
        assertEquals("hitPoint fail", 20, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[2].side)
        assertEquals("buildDamage fail", 18, fightResult[2].damage)
        assertEquals("hitPoint fail", 2, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 1, fightResult[2].target.hp)
    }


    @Test
    fun snrTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.セネリオ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.WindsBrand
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 36, unitA.maxHp)
        assertEquals("atk", 47, unitA.atk)
        assertEquals("spd", 33, unitA.spd)
        assertEquals("def", 17, unitA.def)
        assertEquals("res", 29, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ソフィーヤ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 1", 1, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 9, fightResult[0].damage)
        assertEquals("hitPoint fail", 36, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 31, fightResult[0].target.hp)
//スキル効果で反撃も追撃もない
    }

    @Test
    fun cdTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シーダ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.WingSword
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 39, unitA.maxHp)
        assertEquals("atk", 41, unitA.atk)
        assertEquals("spd", 37, unitA.spd)
        assertEquals("def", 24, unitA.def)
        assertEquals("res", 34, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 22, fightResult[2].damage)
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 6, fightResult[2].target.hp)
    }

    @Test
    fun cd2Test() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.シーダ2.jp)!!)
        unitA.refinedWeapon = RefinedSkill.WingSword
        unitA.equip()
        unitA.special = Special.Luna
        val attacker = BattleUnit(unitA, unitA.maxHp)
        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 22, fightResult[0].damage)
        assertEquals("hitPoint fail", 39, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 14, fightResult[1].damage)
        assertEquals("hitPoint fail", 25, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 41, fightResult[2].damage)//月光で＋１９か
        assertEquals("hitPoint fail", 25, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 0, fightResult[2].target.hp)
    }
    @Test
    fun kmiTest() {
        val unitA = ArmedHero(StandardBaseHero.get(Name.カムイ__男_.jp)!!)
        unitA.refinedWeapon = RefinedSkill.Yato2
        unitA.equip()
        val attacker = BattleUnit(unitA, unitA.maxHp)
        assertEquals("maxHp", 45, unitA.maxHp)
        assertEquals("atk", 48, unitA.atk)
        assertEquals("spd", 32, unitA.spd)
        assertEquals("def", 31, unitA.def)
        assertEquals("res", 24, unitA.res)

        val unitB = ArmedHero(StandardBaseHero.get(Name.ドーガ.jp)!!)
        val fightResult = attacker.fightAndAfterEffect(BattleUnit(unitB, unitB.maxHp))
        println(fightResult[0])
        assertEquals("size == 3", 3, fightResult.size)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[0].side)
        assertEquals("buildDamage fail", 11, fightResult[0].damage)
        assertEquals("hitPoint fail", 45, fightResult[0].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[0].target.hp)
        assertEquals("attack side fail", SIDES.COUNTER, fightResult[1].side)
        assertEquals("buildDamage fail", 5, fightResult[1].damage)
        assertEquals("hitPoint fail", 40, fightResult[1].source.hp)
        assertEquals("hitPoint fail", 39, fightResult[1].target.hp)
        assertEquals("attack side fail", SIDES.ATTACKER, fightResult[2].side)
        assertEquals("buildDamage fail", 11, fightResult[2].damage)
        assertEquals("hitPoint fail", 40, fightResult[2].source.hp)
        assertEquals("hitPoint fail", 28, fightResult[2].target.hp)
    }


}