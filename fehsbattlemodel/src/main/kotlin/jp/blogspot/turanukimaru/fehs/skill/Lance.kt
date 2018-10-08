package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Lance(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W,override  val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    IronLance(Name.IronLance, SkillType.LANCE, 6, Skill.NONE, SpType.IRON),
    SteelLance(Name.SteelLance, SkillType.LANCE, 8, IronLance, SpType.STEEL),
    SilverLance(Name.SilverLance, SkillType.LANCE, 11, SteelLance, SpType.SILVER),
    SilverLance2(Name.SilverLance2, SkillType.LANCE, 15, SilverLance, SpType.PLUS, RefinedSkill.RefineType.Range1),
    KillerLance(Name.KillerLance, SkillType.LANCE, 7, SteelLance, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillerLance2(Name.KillerLance2, SkillType.LANCE, 11, KillerLance, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    BraveLance(Name.BraveLance, SkillType.LANCE, 5, SteelLance, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveLance2(Name.BraveLance2, SkillType.LANCE, 8, BraveLance, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    SapphireLance(Name.SapphireLance, SkillType.LANCE, 8, SteelLance, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    SapphireLance2(Name.SapphireLance2, SkillType.LANCE, 12, SapphireLance, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    HeavySpear(Name.HeavySpear, SkillType.LANCE, 8, SteelLance, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    HeavySpear2(Name.HeavySpear2, SkillType.LANCE, 12, HeavySpear, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    SlayingSpear(Name.SlayingSpear, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingSpear2(Name.SlayingSpear2, SkillType.LANCE, 14, SlayingSpear, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    CarrotLance(Name.CarrotLance, SkillType.LANCE, 9, SteelLance, SpType.SILVER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotLance2(Name.CarrotLance2, SkillType.LANCE, 13, CarrotLance, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    FirstBite(Name.FirstBite, SkillType.LANCE, 10, SteelLance, SpType.SILVER),
    FirstBite2(Name.FirstBite2, SkillType.LANCE, 14, FirstBite, SpType.PLUS, RefinedSkill.RefineType.Range1),
    FiresweepLance(Name.FiresweepLance, SkillType.LANCE, 11, SteelLance, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    FiresweepLance2(Name.FiresweepLance2, SkillType.LANCE, 15, FiresweepLance) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    DeftHarpoon(Name.DeftHarpoon, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    DeftHarpoon2(Name.DeftHarpoon2, SkillType.LANCE, 14, DeftHarpoon, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    Ridersbane(Name.Ridersbane, SkillType.LANCE, 10, SteelLance, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Ridersbane2(Name.Ridersbane2, SkillType.LANCE, 14, Ridersbane, SpType.PLUS, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    BerkutsLance(Name.BerkutsLance, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },
    BerkutsLance2(Name.BerkutsLance2, SkillType.LANCE, 14, BerkutsLance, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },
    SlayingLance(Name.SlayingLance, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingLance2(Name.SlayingLance2, SkillType.LANCE, 14, SlayingLance, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Fensalir(Name.Fensalir, SkillType.LANCE, 16, SilverLance, SpType.LEGEND_W, RefinedSkill.RefineType.Range1),
    Siegmund(Name.Siegmund, SkillType.LANCE, 16, SilverLance, SpType.LEGEND_W, RefinedSkill.RefineType.Range1),
    Gradivus(Name.Gradivus, SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Vidofinir(Name.Vidofinir, SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            val weapon = enemy.armedHero.baseHero.weaponType
            if (weapon == WeaponType.SWORD || weapon == WeaponType.LANCE || weapon == WeaponType.AXE) {
                battleUnit.defEffect += 7
            }
            return battleUnit
        }
    },
    CursedLance(Name.CursedLance, SkillType.LANCE, 16, SilverLance) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(equipAtk(equipSpd(armedHero, 2), 2), lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, 4)
    },
    Geirskogul(Name.Geirskogul, SkillType.LANCE, 16, SilverLance) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
    },
    Leiptr(Name.Leiptr, SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    BrightNaginata(Name.BrightNaginata, SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowAtk(battleUnit, 4), 4)
    },
    Tannenboom(Name.Tannenboom, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Tannenboom2(Name.Tannenboom2, SkillType.LANCE, 14, Tannenboom, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    CasaBlanca(Name.CasaBlanca, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    CasaBlanca2(Name.CasaBlanca2, SkillType.LANCE, 14, CasaBlanca, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    HinokasSpear(Name.HinokasSpear, SkillType.LANCE, 16, SilverLance, SpType.LEGEND_W, RefinedSkill.RefineType.Range1) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 4), 4) else battleUnit
    },
    FlameSiegmund(Name.FlameSiegmund, SkillType.LANCE, 16, SilverLance) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 10)
    },
    HarmonicLance(Name.HarmonicLance, SkillType.LANCE, 9, SteelLance, SpType.SILVER) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    HarmonicLance2(Name.HarmonicLance2, SkillType.LANCE, 13, HarmonicLance, SpType.PLUS, refinedSkillType = RefinedSkill.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Rhomphaia(Name.Rhomphaia, SkillType.LANCE, 16, SilverLance2, SpType.LEGEND_W, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)),
    ShellLance(Name.ShellLance, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    ShellLance2(Name.ShellLance2, SkillType.LANCE, 14, ShellLance, SpType.PLUS, refinedSkillType = RefinedSkill.RefineType.Range1) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    DauntlessLance(Name.DauntlessLance, SkillType.LANCE, 16, SlayingSpear2, SpType.LEGEND_W, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    ReprisalLance(Name.ReprisalLance, SkillType.LANCE, 10, SteelLance, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, 6)
    },
    ReprisalLance2(Name.ReprisalLance2, SkillType.LANCE, 14, ReprisalLance, SpType.PLUS, refinedSkillType = RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, 6)
    },
    Maltet(Name.Maltet, SkillType.LANCE, 16, SilverLance, SpType.LEGEND_W) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 5)
    },
    GaeBolg(Name.GaeBolg, SkillType.LANCE, 16, SilverLance, SpType.LEGEND_W) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (enemy.armedHero.baseHero.moveType == MoveType.INFANTRY || enemy.armedHero.baseHero.moveType == MoveType.ARMORED || enemy.armedHero.baseHero.moveType == MoveType.CAVALRY) blowAtk(blowDef(battleUnit, 5), 5) else battleUnit
    },
   ;
    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}