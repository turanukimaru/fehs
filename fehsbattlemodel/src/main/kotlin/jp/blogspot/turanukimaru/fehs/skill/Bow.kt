package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Bow(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W,override  val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    IronBow(Name.IronBow, SkillType.BOW, 4, Skill.NONE, SpType.IRON, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SteelBow(Name.SteelBow, SkillType.BOW, 6, IronBow, SpType.STEEL, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SilverBow(Name.SilverBow, SkillType.BOW, 9, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SilverBow2(Name.SilverBow2, SkillType.BOW, 13, SilverBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    BraveBow(Name.BraveBow, SkillType.BOW, 4, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveBow2(Name.BraveBow2, SkillType.BOW, 7, BraveBow, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    KillerBow(Name.KillerBow, SkillType.BOW, 5, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillerBow2(Name.KillerBow2, SkillType.BOW, 9, KillerBow, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    AssassinsBow(Name.AssassinsBow, SkillType.BOW, 7, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.DAGGER, 3)
    },
    AssassinsBow2(Name.AssassinsBow2, SkillType.BOW, 11, AssassinsBow, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.DAGGER, 3)
    },
    GuardBow2(Name.GuardBow2, SkillType.BOW, 12, AssassinsBow2, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    FiresweepBow(Name.FiresweepBow, SkillType.BOW, 7, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    FiresweepBow2(Name.FiresweepBow2, SkillType.BOW, 11, FiresweepBow, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    CupidArrow(Name.CupidArrow, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    CupidArrow2(Name.CupidArrow2, SkillType.BOW, 12, CupidArrow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    RefreshingBolt(Name.RefreshingBolt, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    RefreshingBolt2(Name.RefreshingBolt2, SkillType.BOW, 12, RefreshingBolt, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    ClarissesBow(Name.ClarissesBow, SkillType.BOW, 7, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    ClarissesBow2(Name.ClarissesBow2, SkillType.BOW, 11, ClarissesBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SlayingBow(Name.SlayingBow, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingBow2(Name.SlayingBow2, SkillType.BOW, 12, SlayingBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Parthia(Name.Parthia, SkillType.BOW, 14, SilverBow, SpType.LEGEND_W, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 2)
    },
    FujinYumi(Name.FujinYumi, SkillType.BOW, 14, SilverBow, SpType.LEGEND_W, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    Nidhogg(Name.Nidhogg, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Mulagir(Name.Mulagir, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiMagicBuffBonus(battleUnit, enemy)
    },
    MonstrousBow(Name.MonstrousBow, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    MonstrousBow2(Name.MonstrousBow2, SkillType.BOW, 12, MonstrousBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    HamaYa(Name.HamaYa, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    HamaYa2(Name.HamaYa2, SkillType.BOW, 12, HamaYa, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    Gratia(Name.Gratia, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    Gratia2(Name.Gratia2, SkillType.BOW, 12, Gratia, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    Skadi(Name.Skadi, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
    },
    WarriorPrincess(Name.WarriorPrincess, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER, MoveType.ARMORED)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
    },
    SwiftMulagir(Name.SwiftMulagir, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        //マップ実装時には敵ユニット数との判定が必要
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0)
            blowAtk(blowSpd(battleUnit, 5), 5)
        else battleUnit
    },
    Cocobow(Name.Cocobow, SkillType.BOW, 8, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Cocobow2(Name.Cocobow2, SkillType.BOW, 12, Cocobow, SpType.PLUS, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    FishieBow(Name.FishieBow, SkillType.BOW, 10, SteelBow, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    FishieBow2(Name.FishieBow2, SkillType.BOW, 12, FishieBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    Thogn(Name.Thogn, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        //マップ実装時には敵ユニット数との判定が必要
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (enemy.armedHero.effectiveRange == 1) allBonus(battleUnit, 4) else battleUnit
    },
    ShiningBow(Name.ShiningBow, SkillType.BOW, 8, SteelBow, SpType.SILVER, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defHigherThanResBonus(battleUnit, enemy)
    },
    ShiningBow2(Name.ShiningBow2, SkillType.BOW, 12, ShiningBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defHigherThanResBonus(battleUnit, enemy)
    },
    DevilishBow(Name.DevilishBow, SkillType.BOW, 8, SteelBow, SpType.SILVER, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiAccelerateCooldown(battleUnit, enemy, 11)
    },
    DevilishBow2(Name.DevilishBow2, SkillType.BOW, 12, ShiningBow, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiAccelerateCooldown(battleUnit, enemy, 11)
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}