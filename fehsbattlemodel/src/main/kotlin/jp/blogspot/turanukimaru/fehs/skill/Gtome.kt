package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Gtome(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    Wind(Name.Wind, SkillType.GTOME, 4, Skill.NONE, SpType.IRON),
    Elwind(Name.Elwind, SkillType.GTOME, 6, Wind, SpType.STEEL),
    Rexcalibur(Name.Rexcalibur, SkillType.GTOME, 9, Elwind, SpType.SILVER),
    Rexcalibur2(Name.Rexcalibur2, SkillType.GTOME, 14, Rexcalibur, SpType.PLUS, RefinedSkill.RefineType.Range2),
    Gronnwolf(Name.Gronnwolf, SkillType.GTOME, 6, Elwind, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Gronnwolf2(Name.Gronnwolf2, SkillType.GTOME, 10, Gronnwolf, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    KeenGronnwolf2(Name.KeenGronnwolf2, SkillType.GTOME, 12, Gronnwolf2, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Gronnraven(Name.Gronnraven, SkillType.GTOME, 7, Elwind, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Gronnraven2(Name.Gronnraven2, SkillType.GTOME, 11, Gronnraven, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Gronnblade(Name.Gronnblade, SkillType.GTOME, 9, Elwind, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Gronnblade2(Name.Gronnblade2, SkillType.GTOME, 13, Gronnblade, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Gronnowl(Name.Gronnowl, SkillType.GTOME, 6, Elwind, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Gronnowl2(Name.Gronnowl2, SkillType.GTOME, 10, Gronnowl, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    GreenEgg(Name.GreenEgg, SkillType.GTOME, 7, Elwind, SpType.SILVER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    GreenEgg2(Name.GreenEgg2, SkillType.GTOME, 11, GreenEgg, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    HibiscusTome(Name.HibiscusTome, SkillType.GTOME, 8, Elwind, SpType.SILVER),
    HibiscusTome2(Name.HibiscusTome2, SkillType.GTOME, 12, HibiscusTome, SpType.PLUS, RefinedSkill.RefineType.Range2),
    DancersRing(Name.DancersRing, SkillType.GTOME, 8, Elwind, SpType.SILVER),
    DancersRing2(Name.DancersRing2, SkillType.GTOME, 12, DancersRing, SpType.PLUS, RefinedSkill.RefineType.Range2),
    Elivagar(Name.Elivagar, SkillType.GTOME, 14, Rexcalibur),
    Excalibur(Name.Excalibur, SkillType.GTOME, 14, Rexcalibur, SpType.LEGEND_W, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    Naga(Name.Naga, SkillType.GTOME, 14, Rexcalibur, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 2), 2)
    },
    DarkExcalibur(Name.DarkExcalibur, SkillType.GTOME, 14, Rexcalibur) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    DivineNaga(Name.DivineNaga, SkillType.GTOME, 14, Rexcalibur, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(battleUnit, enemy)
    },
    SpectralTome(Name.SpectralTome, SkillType.GTOME, 8, Elwind, SpType.SILVER),
    SpectralTome2(Name.SpectralTome2, SkillType.GTOME, 12, SpectralTome, SpType.PLUS, RefinedSkill.RefineType.Range2),
    Blizzard(Name.Blizzard, SkillType.GTOME, 14, Rexcalibur) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = debuffBonus(battleUnit, enemy)
    },
    GreenGift(Name.GreenGift, SkillType.GTOME, 8, Elwind, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    GreenGift2(Name.GreenGift2, SkillType.GTOME, 12, GreenGift, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    WindsBrand(Name.WindsBrand, SkillType.GTOME, 14, Rexcalibur, SpType.LEGEND_W, RefinedSkill.RefineType.Range2),
    MuninnsEgg(Name.MuninnsEgg, SkillType.BTOME, 14, Rexcalibur) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
    },
    Thunderhead(Name.Thunderhead, SkillType.GTOME, 14, Rexcalibur) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    NiflFrostflowers(Name.NiflFrostflowers, SkillType.GTOME, 14, Rexcalibur) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, battleUnit.adjacentUnits * 2), battleUnit.adjacentUnits * 2)
    },
    GigaExcalibur(Name.GigaExcalibur, SkillType.GTOME, 14, Rexcalibur) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun stateFlat(battleUnit: BattleUnit, enemy: BattleUnit): Int = spdFlat(battleUnit, enemy)
    },
    Forseti(Name.Forseti, SkillType.GTOME, 14, Rexcalibur) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = forseti(fightPlan, 2)//攻めたてと同じLV倍率にしとこう
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}