package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.FightPlan
import jp.blogspot.turanukimaru.fehs.MoveType
import java.util.*

/**
 * スキル。補助
 */
enum class Refinery(override val jp: String, override val type: Skill.SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE,val refineryType: Refinery.RefineryType = Refinery.RefineryType.NONE) : Skill {
    Range1Atk("攻撃(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipAtk(equipHp(armedHero, 5), 1), 2)
    },
    Range1Spd("速さ(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipAtk(equipHp(armedHero, 5), 1), 3)
    },
    Range1Def("守備(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipAtk(equipHp(armedHero, 5), 1), 4)
    },
    Range1Res("魔防(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(equipAtk(equipHp(armedHero, 5), 1), 4)
    },
    Range2Atk("攻撃(射程2)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipHp(armedHero, 3), 1)
    },
    Range2Spd("速さ(射程2)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipHp(armedHero, 3), 2)
    },
    Range2Def("守備(射程2)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipHp(armedHero, 3), 3)
    },
    Range2Res("魔防(射程2)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(equipHp(armedHero, 3), 3)
    },
    RidersbaneAtk("攻撃(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipAtk(equipHp(armedHero, 5), 1), 2)
    },
    RidersbaneSpd("速さ(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipAtk(equipHp(armedHero, 5), 1), 3)
    },
    RidersbaneDef("守備(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipAtk(equipHp(armedHero, 5), 1), 4)
    },
    RidersbaneRes("魔防(射程1)", Skill.SkillType.REFINERY, refineryType= RefineryType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(equipAtk(equipHp(armedHero, 5), 1), 4)
    },
    //盗賊の暗器系統の数字が解らん・・・
    DaggerAtk("", Skill.SkillType.REFINERY, 0, Weapon.SilverDagger2, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipHp(equipAtk(armedHero, 3),4),1)
    },
    DaggerSpd("", Skill.SkillType.REFINERY, 0, Weapon.SilverDagger2, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipHp(equipAtk(armedHero, 3),4),3)
    },
    DaggerDef("", Skill.SkillType.REFINERY, 0, Weapon.SilverDagger2, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipHp(equipAtk(armedHero, 3),4),4)
    },
    DaggerRes("", Skill.SkillType.REFINERY, 0, Weapon.SilverDagger2, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(equipHp(equipAtk(armedHero, 3),4),4)
    },

    SolKatti("", Skill.SkillType.REFINERY, 0, Weapon.SolKatti, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, 3)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = brashAssault(battleUnit, 75)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, lv)
    },
    Mystletainn("", Skill.SkillType.REFINERY, 0, Weapon.Mystletainn, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = furry(equipHp(armedHero, 3), 3)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHpLoss(battleUnit, lv * 3)
    },
    Siegmund("", Skill.SkillType.REFINERY, 0, Weapon.Siegmund, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, 3)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit  {
            if(battleUnit.hp >= battleUnit.armedHero.maxHp * 9 / 10){battleUnit.followupable = true}
            return battleUnit
        }
    },
    Hauteclere("", Skill.SkillType.REFINERY, 0, Weapon.Hauteclere, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, 3)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int, lv: Int): Int = damage + 10
    },
    FujinYumi("", Skill.SkillType.REFINERY, 0, Weapon.FujinYumi, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, 3)
    },
    DeathlyDagger("", Skill.SkillType.REFINERY, 0, Weapon.DeathlyDagger, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipAtk(armedHero, 3),3)
    },
    DeathlyDaggerAtk("", Skill.SkillType.REFINERY, 0, Weapon.DeathlyDagger, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipHp(equipAtk(armedHero, 3),3),2)
    },
    DeathlyDaggerSpd("", Skill.SkillType.REFINERY, 0, Weapon.DeathlyDagger, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipHp(equipAtk(armedHero, 3),3),3)
    },
    DeathlyDaggerDef("", Skill.SkillType.REFINERY, 0, Weapon.DeathlyDagger, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipHp(equipAtk(armedHero, 3),3),4)
    },
    DeathlyDaggerRes("", Skill.SkillType.REFINERY, 0, Weapon.DeathlyDagger, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(equipHp(equipAtk(armedHero, 3),3),4)
    },

    Ridersbane2("", Skill.SkillType.REFINERY, 0, Weapon.Ridersbane2, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, 3)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit))
    },
    ArmorSlayer2("", Skill.SkillType.REFINERY, 0, Weapon.ArmorSlayer2, refineryType= RefineryType.DependWeapon) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, 3)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit))
    },

    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name.replace("2", "+")

    override fun localeName(locale: Locale): String {
        return when (locale) {
            Locale.JAPAN -> jp
            Locale.JAPANESE -> jp
            else -> value
        }
    }

    companion object {
        val itemMap = mutableMapOf<String, Skill>()
        fun spreadItems(): List<Skill> = values().fold(arrayListOf<Skill>(Skill.NONE), { list, e -> list.add(e);list })

        fun valueOfOrNONE(key: String?): Skill = if (key == null) Skill.NONE else try {
            if (itemMap.isEmpty()) {
                values().forEach { e -> itemMap.put(e.jp, e) }
                values().forEach { e -> itemMap.put(e.value, e) }
            }
            itemMap[key] ?: valueOf(key)
        } catch (e: Exception) {
            Skill.NONE
        }
    }

    enum class RefineryType{
        NONE,
        Range1,
        Range2,
        Slaying,
        Ridersbane,
        Armorsmasher,
        Special,
        DependWeapon,
    }
}