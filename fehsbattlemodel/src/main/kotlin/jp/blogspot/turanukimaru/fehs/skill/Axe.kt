package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Axe(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedWeaponType: RefinedWeapon.RefineType = RefinedWeapon.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    IronAxe(Name.IronAxe, SkillType.AXE, 6, Skill.NONE, SpType.IRON),
    SteelAxe(Name.SteelAxe, SkillType.AXE, 8, IronAxe, SpType.STEEL),
    SilverAxe(Name.SilverAxe, SkillType.AXE, 11, SteelAxe, SpType.SILVER),
    SilverAxe2(Name.SilverAxe2, SkillType.AXE, 15, SilverAxe, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    KillerAxe(Name.KillerAxe, SkillType.AXE, 7, SteelAxe, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillerAxe2(Name.KillerAxe2, SkillType.AXE, 11, KillerAxe, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    BraveAxe(Name.BraveAxe, SkillType.AXE, 5, SteelAxe, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveAxe2(Name.BraveAxe2, SkillType.AXE, 8, BraveAxe, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Hammer(Name.Hammer, SkillType.AXE, 8, SteelAxe, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    Hammer2(Name.Hammer2, SkillType.AXE, 12, Hammer, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    SlayingHammer2(Name.SlayingHammer2, SkillType.AXE, 14, Hammer2, SpType.PLUS, RefinedWeapon.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    EmeraldAxe(Name.EmeraldAxe, SkillType.AXE, 8, SteelAxe, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    EmeraldAxe2(Name.EmeraldAxe2, SkillType.AXE, 12, EmeraldAxe, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    SlayingAxe(Name.SlayingAxe, SkillType.AXE, 10, SteelAxe, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingAxe2(Name.SlayingAxe2, SkillType.AXE, 14, SlayingAxe, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    CarrotAxe(Name.CarrotAxe, SkillType.AXE, 9, SteelAxe, SpType.SILVER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotAxe2(Name.CarrotAxe2, SkillType.AXE, 13, CarrotAxe, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    LegionsAxe(Name.LegionsAxe, SkillType.AXE, 10, SteelAxe, SpType.SILVER),
    LegionsAxe2(Name.LegionsAxe2, SkillType.AXE, 14, LegionsAxe, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    MelonCrusher(Name.MelonCrusher, SkillType.AXE, 10, SteelAxe, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    MelonCrusher2(Name.MelonCrusher2, SkillType.AXE, 14, MelonCrusher, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    LilithFloatie(Name.LilithFloatie, SkillType.AXE, 10, SteelAxe, SpType.SILVER),
    LilithFloatie2(Name.LilithFloatie2, SkillType.AXE, 14, LilithFloatie, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    Noatun(Name.Noatun, SkillType.AXE, 16, SilverAxe, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1),
    Hauteclere(Name.Hauteclere, SkillType.AXE, 16, SilverAxe, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Armoads(Name.Armoads, SkillType.AXE, 16, SilverAxe) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 2)
    },
    ThunderArmoads(Name.ThunderArmoads, SkillType.AXE, 16, SilverAxe) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiFollowupAdjast(battleUnit, enemy)
    },
    Urvan(Name.Urvan, SkillType.AXE, 16, SilverAxe) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (results.isNotEmpty() && results.last().side != battleUnit.side) damage - damage * 8 / 10 else damage
    },
    Uror(Name.Uror, SkillType.AXE, 16, SilverAxe),
    StoutTomahawk(Name.StoutTomahawk, SkillType.AXE, 16, SilverAxe) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    SackOGifts(Name.SackOGifts, SkillType.AXE, 10, SteelAxe, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    SackOGifts2(Name.SackOGifts2, SkillType.AXE, 14, SackOGifts, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Handbell(Name.Handbell, SkillType.AXE, 10, SteelAxe, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Handbell2(Name.Handbell2, SkillType.AXE, 14, Handbell, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Hagoita(Name.Hagoita, SkillType.AXE, 10, SteelAxe, SpType.SILVER),
    Hagoita2(Name.Hagoita2, SkillType.AXE, 14, Hagoita, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    BerserkArmads(Name.BerserkArmads, SkillType.AXE, 16, SilverAxe) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = wrath(battleUnit, damage, 75)
    },
    Basilikos(Name.Basilikos, SkillType.AXE, 16, BraveAxe2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    GiantSpoon(Name.GiantSpoon, SkillType.AXE, 9, SteelAxe, SpType.SILVER) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    GiantSpoon2(Name.GiantSpoon2, SkillType.AXE, 13, GiantSpoon, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Poleaxe(Name.Poleaxe, SkillType.AXE, 10, SteelAxe, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Poleaxe2(Name.Poleaxe2, SkillType.AXE, 14, Poleaxe, SpType.PLUS, RefinedWeapon.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    CamillasAxe(Name.CamillasAxe, SkillType.AXE, 16, BraveAxe2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 4), 4) else battleUnit
    },
    ArdentService(Name.ArdentService, SkillType.AXE, 10, SteelAxe, SpType.SILVER),
    ArdentService2(Name.ArdentService2, SkillType.AXE, 14, ArdentService, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    BeachBanner(Name.BeachBanner, SkillType.AXE, 10, SteelAxe, SpType.SILVER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    BeachBanner2(Name.BeachBanner2, SkillType.AXE, 14, BeachBanner, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    DraconicPoleax(Name.DraconicPoleax, SkillType.AXE, 16, EmeraldAxe) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    WoGun(Name.WoGun, SkillType.AXE, 9, SteelAxe, SpType.SILVER) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    WoGun2(Name.WoGun2, SkillType.AXE, 13, WoGun, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Garm(Name.Garm, SkillType.AXE, 16, SilverAxe) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        //本来はバフがかかってるとき。…いや行軍はかかってるだろうしデフォルトで常に追撃可能でよくね
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.buffDebuffTrigger) followupable(battleUnit, 10) else battleUnit
    },
    Byleistr(Name.Byleistr, SkillType.AXE, 16, SilverAxe),//4種の波なので戦闘能力自体はない
    Sinmara(Name.Sinmara, SkillType.AXE, 16, SilverAxe) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
    },
    CherchesAxe(Name.CherchesAxe, SkillType.AXE, 11, Hammer2, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    AxeOfVirility(Name.AxeOfVirility, SkillType.AXE, 16, Hammer2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     */
    override val value get() = name
}