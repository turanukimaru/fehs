package jp.blogspot.turanukimaru.fehs

import java.util.*

/**
 * スキル。武器/補助/奥義
 */
enum class Specials(override val jp: String, override val type: Skill.SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE) : Skill {


    DayLight("陽影", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> = super.damage(battleUnit, target, results, this)
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
            battleUnit.heal(damage * 3 / 10)
            return damage
        }
    },
    Noontime("夕陽", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> = super.damage(battleUnit, target, results, this)
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
            battleUnit.heal(damage * 3 / 10)
            return damage
        }
    },
    Sol("太陽", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> = super.damage(battleUnit, target, results, this)
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
            battleUnit.heal(damage * 5 / 10)
            return damage
        }
    },
    NightSky("星影", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            return Pair(super.damage(battleUnit, target, results, this).first * 15 / 10, this)
        }
    },
    Glimmer("凶星", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            return Pair(super.damage(battleUnit, target, results, this).first * 15 / 10, this)
        }
    },
    Astra("流星", Skill.SkillType.SPECIAL_A, 5) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            return Pair(super.damage(battleUnit, target, results, this).first * 25 / 10, this)
        }
    },
    RegnalAstra("剣姫の流星", Skill.SkillType.SPECIAL_A, 2) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedSpd * 4 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Miracle("祈り", Skill.SkillType.SPECIAL_B, 5) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            //試しに使ってみたけど慣れるまではむしろわかりにくそうな表記だ
            return if (battleUnit.hp in 2..damage) Pair(battleUnit.hp - 1, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    DraconicAura("伏竜", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedAtk * 3 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    DragonGaze("竜裂", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedAtk * 3 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    DragonFang("竜穿", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedAtk * 5 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    GlowingEmber("蛍火", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedDef * 5 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Boonfire("緋炎", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedDef * 5 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Ignis("華炎", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedDef * 8 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    ChillingWind("氷点", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedRes * 5 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Iceberg("氷蒼", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedRes * 5 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Glacies("氷華", Skill.SkillType.SPECIAL_A, 4) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + battleUnit.effectedRes * 8 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },

    Aether("天空", Skill.SkillType.SPECIAL_A, 5) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack(), battleUnit.armedClass.battleClass.weaponType, 50)
            return Pair(damage, this)
        }

        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
            battleUnit.heal(damage * 5 / 10)
            return damage
        }
    },
    NewMoon("影月", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack(), battleUnit.armedClass.battleClass.weaponType, 30)
            return Pair(damage, this)
        }
    },
    Moonbow("月虹", Skill.SkillType.SPECIAL_A, 2) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack(), battleUnit.armedClass.battleClass.weaponType, 30)
            return Pair(damage, this)
        }
    },
    Luna("月光", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack(), battleUnit.armedClass.battleClass.weaponType, 50)
            return Pair(damage, this)
        }
    },
    BlackLuna("黒の月光", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack(), battleUnit.armedClass.battleClass.weaponType, 80)
            return Pair(damage, this)
        }
    },

    Retribution("雪辱", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + (battleUnit.armedClass.maxHp - battleUnit.hp) * 3 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Reprisal("血讐", Skill.SkillType.SPECIAL_A, 2) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + (battleUnit.armedClass.maxHp - battleUnit.hp) * 3 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    Vengeance("復讐", Skill.SkillType.SPECIAL_A, 3) {
        override fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill?): Pair<Int, Skill?> {
            val damage = target.preventByDefResTerrain(battleUnit.colorAttack() + (battleUnit.armedClass.maxHp - battleUnit.hp) * 5 / 10, battleUnit.armedClass.battleClass.weaponType)
            return Pair(damage, this)
        }
    },
    HolyVestments("聖衣", Skill.SkillType.SPECIAL_C, 3) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            return if (battleUnit.armedClass.battleClass.weaponType.range == 2) Pair(damage - damage * 3 / 10, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    SacredCowl("聖兜", Skill.SkillType.SPECIAL_C, 2) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            return if (battleUnit.armedClass.battleClass.weaponType.range == 2) Pair(damage - damage * 3 / 10, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    Aegis("聖盾", Skill.SkillType.SPECIAL_C, 3) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            return if (battleUnit.armedClass.battleClass.weaponType.range == 2) Pair(damage - damage * 5 / 10, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    Buckler("小盾", Skill.SkillType.SPECIAL_A, 3) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            return if (battleUnit.armedClass.battleClass.weaponType.range == 1) Pair(damage - damage * 3 / 10, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    Escutcheon("長盾", Skill.SkillType.SPECIAL_A, 2) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            log(battleUnit.enemy!!.armedClass.battleClass.weaponType.range)
            return if (battleUnit.enemy!!.armedClass.battleClass.weaponType.range == 1) Pair(damage - damage * 3 / 10, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    Pavise("大盾", Skill.SkillType.SPECIAL_A, 3) {
        override fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int): Pair<Int, Skill?> {
            return if (battleUnit.armedClass.battleClass.weaponType.range == 1) Pair(damage - damage * 5 / 10, this) else super.specialPrevent(battleUnit, damage, lv)
        }
    },
    Galeforce("疾風迅雷", Skill.SkillType.SPECIAL_A, 5),
    Imbue("治癒", Skill.SkillType.SPECIAL_A, 1),
    HeavenlyLight("天照", Skill.SkillType.SPECIAL_A, 2),
    SolidEarthBalm("大地の祝福", Skill.SkillType.SPECIAL_A, 1),
    SwiftWindsBalm("疾風の祝福", Skill.SkillType.SPECIAL_A, 1),
    KindledFireBalm("業火の祝福", Skill.SkillType.SPECIAL_A, 1),
    StillWaterBalm("静水の祝福", Skill.SkillType.SPECIAL_A, 1),

    RisingFlame("砕火", Skill.SkillType.SPECIAL_A, 5),
    BlazingFlame("烈火", Skill.SkillType.SPECIAL_A, 5),
    GrowingFlame("爆火", Skill.SkillType.SPECIAL_A, 5),
    RisingLight("砕光", Skill.SkillType.SPECIAL_A, 5),
    BlazingLight("烈光", Skill.SkillType.SPECIAL_A, 5),
    GrowingLight("爆光", Skill.SkillType.SPECIAL_A, 5),
    RisingWind("砕風", Skill.SkillType.SPECIAL_A, 5),
    BlazingWind("烈風", Skill.SkillType.SPECIAL_A, 5),
    GrowingWind("爆風", Skill.SkillType.SPECIAL_A, 5),
    RisingThunder("砕雷", Skill.SkillType.SPECIAL_A, 5),
    BlazingThunder("烈雷", Skill.SkillType.SPECIAL_A, 5),
    GrowingThunder("爆雷", Skill.SkillType.SPECIAL_A, 5),

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
}