package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Btome(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedWeaponType: RefinedWeapon.RefineType = RefinedWeapon.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    Thunder(Name.Thunder, SkillType.BTOME, 4, Skill.NONE, SpType.IRON),
    Elthunder(Name.Elthunder, SkillType.BTOME, 6, Thunder, SpType.STEEL),
    Blarwolf(Name.Blarwolf, SkillType.BTOME, 6, Elthunder, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Blarwolf2(Name.Blarwolf2, SkillType.BTOME, 10, Blarwolf, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    KeenBlarwolf2(Name.KeenBlarwolf2, SkillType.BTOME, 12, Blarwolf2, SpType.PLUS, RefinedWeapon.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Blarraven(Name.Blarraven, SkillType.BTOME, 7, Elthunder, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Blarraven2(Name.Blarraven2, SkillType.BTOME, 11, Blarraven, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Blarblade(Name.Blarblade, SkillType.BTOME, 9, Elthunder, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Blarblade2(Name.Blarblade2, SkillType.BTOME, 13, Blarblade, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Blarowl(Name.Blarowl, SkillType.BTOME, 6, Elthunder, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Blarowl2(Name.Blarowl2, SkillType.BTOME, 10, Blarowl, SpType.PLUS, RefinedWeapon.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Blarserpent(Name.Blarserpent, SkillType.BTOME, 8, Elthunder, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    Blarserpent2(Name.Blarserpent2, SkillType.BTOME, 12, Blarserpent, SpType.PLUS, RefinedWeapon.RefineType.Range2) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    Thoron(Name.Thoron, SkillType.BTOME, 9, Elthunder, SpType.SILVER),
    Thoron2(Name.Thoron2, SkillType.BTOME, 13, Thoron, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    BlueEgg(Name.BlueEgg, SkillType.BTOME, 7, Elthunder, SpType.SILVER),
    BlueEgg2(Name.BlueEgg2, SkillType.BTOME, 11, BlueEgg, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    BlessedBouquet(Name.BlessedBouquet, SkillType.BTOME, 9, Elthunder, SpType.SILVER),
    BlessedBouquet2(Name.BlessedBouquet2, SkillType.BTOME, 12, BlessedBouquet, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    SealifeTome(Name.SealifeTome, SkillType.BTOME, 8, Elthunder, SpType.SILVER),
    SealifeTome2(Name.SealifeTome2, SkillType.BTOME, 12, SealifeTome, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    DancersScore(Name.DancersScore, SkillType.BTOME, 8, Elthunder, SpType.SILVER),
    DancersScore2(Name.DancersScore2, SkillType.BTOME, 12, DancersScore, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    DarkAura(Name.DarkAura, SkillType.BTOME, 14, Thoron),
    Valaskjalf(Name.Valaskjalf, SkillType.BTOME, 14, Thoron) {
        override fun counterPlan(fightPlan: FightPlan, lv: Int): FightPlan = vantage(fightPlan, 2)
    },
    Aura(Name.Aura, SkillType.BTOME, 14, Thoron, SpType.LEGEND_W, RefinedWeapon.RefineType.Range2),
    DireThunder(Name.DireThunder, SkillType.BTOME, 9, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    WeirdingTome(Name.WeirdingTome, SkillType.BTOME, 14, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
    },
    Thani(Name.Thani, SkillType.BTOME, 14, Thoron, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if ((source.armedHero.baseHero.moveType == MoveType.ARMORED || source.armedHero.baseHero.moveType == MoveType.CAVALRY)
                        && source.armedHero.effectiveRange == 2
                        && (!results.any { r -> r.side != battleUnit.side })) damage - damage * 3 / 10 else damage
    },
    Ivaldi(Name.Ivaldi, SkillType.BTOME, 14, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 3)
    },
    BlueGift(Name.BlueGift, SkillType.BTOME, 8, Thoron, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    BlueGift2(Name.BlueGift2, SkillType.BTOME, 12, BlueGift, SpType.PLUS, RefinedWeapon.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    HuginnsEgg(Name.HuginnsEgg, SkillType.BTOME, 14, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
    },
    WargodsTome(Name.WargodsTome, SkillType.BTOME, 14, Thoron),
    Mjolnir(Name.Mjolnir, SkillType.BTOME, 14, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, 6)
    },
    FreshBouquet(Name.FreshBouquet, SkillType.BTOME, 8, Thoron, SpType.SILVER),
    FreshBouquet2(Name.FreshBouquet2, SkillType.BTOME, 12, FreshBouquet, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    JuicyWave(Name.JuicyWave, SkillType.BTOME, 10, Thoron, SpType.SILVER) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    JuicyWave2(Name.JuicyWave2, SkillType.BTOME, 12, JuicyWave, SpType.PLUS, RefinedWeapon.RefineType.Range2) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    Missiletainn(Name.Missiletainn, SkillType.BTOME, 14, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    OdinsGrimoire(Name.OdinsGrimoire, SkillType.BTOME, 14, Blarblade2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range2) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Sagittae(Name.Sagittae, SkillType.BTOME, 14, Thoron) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.atk + 5 <= enemy.atk) allBonus(battleUnit, 5) else battleUnit
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     */
    override val value get() = name
}