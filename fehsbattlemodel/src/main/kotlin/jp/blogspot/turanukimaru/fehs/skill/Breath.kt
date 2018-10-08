package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Breath(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W,override  val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
   FireBreath(Name.FireBreath, SkillType.DRAGON, 6, Skill.NONE, SpType.IRON),
    FireBreath2(Name.FireBreath2, SkillType.DRAGON, 8, FireBreath, SpType.STEEL),
    LightningBreath(Name.LightningBreath, SkillType.DRAGON, 7, FireBreath2, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    LightningBreath2(Name.LightningBreath2, SkillType.DRAGON, 11, LightningBreath, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Flametongue(Name.Flametongue, SkillType.DRAGON, 11, FireBreath2, SpType.SILVER),
    Flametongue2(Name.Flametongue2, SkillType.DRAGON, 15, Flametongue, SpType.PLUS, RefinedSkill.RefineType.Range1),
    LightBreath(Name.LightBreath, SkillType.DRAGON, 9, FireBreath2, SpType.SILVER),
    LightBreath2(Name.LightBreath2, SkillType.DRAGON, 13, LightBreath, SpType.PLUS, RefinedSkill.RefineType.Range1),
    DarkBreath(Name.DarkBreath, SkillType.DRAGON, 9, FireBreath, SpType.SILVER),
    DarkBreath2(Name.DarkBreath2, SkillType.DRAGON, 13, DarkBreath, SpType.PLUS, RefinedSkill.RefineType.Range1),
    GreatFlame(Name.GreatFlame, SkillType.PENETRATE_DRAGON, 16, Flametongue) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiFollowupDef(battleUnit, enemy, 5)
    },
    Expiration(Name.Expiration, SkillType.PENETRATE_DRAGON, 16, Flametongue) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    WaterBreath(Name.WaterBreath, SkillType.PENETRATE_DRAGON, 10, FireBreath2, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 4), 4)
    },
    WaterBreath2(Name.WaterBreath2, SkillType.PENETRATE_DRAGON, 14, WaterBreath, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 4), 4)
    },
    BreathOfFog(Name.BreathOfFog, SkillType.PENETRATE_DRAGON, 16, Flametongue, SpType.PLUS, RefinedSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    SummersBreath(Name.SummersBreath, SkillType.PENETRATE_DRAGON, 16, Flametongue) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return battleUnit //blowRes(battleUnit, 4)受け性能アップはなかったよな？
        }
    },
    BreathOfBlight(Name.BreathOfBlight, SkillType.PENETRATE_DRAGON, 16, Flametongue) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit =
                antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.DRAGON)

        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit =
                antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.DRAGON)
    },
    DivineMist(Name.DivineMist, SkillType.PENETRATE_DRAGON, 16, Flametongue, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}