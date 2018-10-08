package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Staff(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    Assault(Name.Assault, SkillType.STAFF, 10, Skill.NONE, SpType.IRON),
    Absorb(Name.Absorb, SkillType.STAFF, 4, Assault, SpType.STEEL) {
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int = battleUnit.heal(damage * 5 / 10)
    },
    Absorb2(Name.Absorb2, SkillType.STAFF, 7, Absorb, SpType.LEGEND_W, RefinedSkill.RefineType.Staff) {
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int = battleUnit.heal(damage * 5 / 10)
    },
    Candlelight(Name.Candlelight, SkillType.STAFF, 7, Assault, SpType.STEEL),
    Candlelight2(Name.Candlelight2, SkillType.STAFF, 11, Assault, SpType.IRON, RefinedSkill.RefineType.Staff),
    Gravity(Name.Gravity, SkillType.STAFF, 7, Assault, SpType.SILVER),
    Gravity2(Name.Gravity2, SkillType.STAFF, 10, Gravity, SpType.LEGEND_W, RefinedSkill.RefineType.Staff),
    Fear(Name.Fear, SkillType.STAFF, 5, Assault, SpType.SILVER),
    Fear2(Name.Fear2, SkillType.STAFF, 12, Fear, SpType.PLUS, RefinedSkill.RefineType.Staff),
    Slow(Name.Slow, SkillType.STAFF, 5, Assault, SpType.SILVER),
    Slow2(Name.Slow2, SkillType.STAFF, 12, Slow, SpType.PLUS, RefinedSkill.RefineType.Staff),
    Panic(Name.Panic, SkillType.STAFF, 6, Assault, SpType.SILVER),
    Panic2(Name.Panic2, SkillType.STAFF, 11, Panic, SpType.PLUS, RefinedSkill.RefineType.Staff),
    Pain(Name.Pain, SkillType.STAFF, 3, Assault, SpType.SILVER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 10)
    },
    Pain2(Name.Pain2, SkillType.STAFF, 10, Pain, SpType.PLUS, RefinedSkill.RefineType.Staff) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 10)
    },
    Trilemma(Name.Trilemma, SkillType.STAFF, 8, Assault, SpType.SILVER),
    Trilemma2(Name.Trilemma2, SkillType.STAFF, 12, Trilemma, SpType.PLUS, RefinedSkill.RefineType.Staff),
    Thokk(Name.Thokk, SkillType.STAFF, 14, Assault, SpType.PLUS) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = wrathfulStaff(battleUnit, enemy, lv)
    },
    Hliðskjálf(Name.Hliðskjálf, SkillType.STAFF, 14, Assault, SpType.PLUS) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = dazzling(battleUnit, enemy, 3)
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}