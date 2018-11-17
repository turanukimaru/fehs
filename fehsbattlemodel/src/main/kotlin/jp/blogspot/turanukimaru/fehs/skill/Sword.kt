package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Sword(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    IronSword(Name.IronSword, SkillType.SWORD, 6, Skill.NONE, SpType.IRON),
    SteelSword(Name.SteelSword, SkillType.SWORD, 8, IronSword, SpType.STEEL),
    SilverSword(Name.SilverSword, SkillType.SWORD, 11, SteelSword, SpType.SILVER),
    SilverSword2(Name.SilverSword2, SkillType.SWORD, 15, SilverSword, SpType.PLUS, RefinedSkill.RefineType.Range1),
    ArmorSlayer(Name.ArmorSlayer, SkillType.SWORD, 8, SteelSword, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    ArmorSlayer2(Name.ArmorSlayer2, SkillType.SWORD, 12, ArmorSlayer, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    Armorsmasher2(Name.Armorsmasher2, SkillType.SWORD, 14, ArmorSlayer2, SpType.PLUS, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    BraveSword(Name.BraveSword, SkillType.SWORD, 5, SteelSword, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveSword2(Name.BraveSword2, SkillType.SWORD, 8, BraveSword, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    RubySword(Name.RubySword, SkillType.SWORD, 8, SteelSword, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    RubySword2(Name.RubySword2, SkillType.SWORD, 12, RubySword, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    KillingEdge(Name.KillingEdge, SkillType.SWORD, 7, SteelSword, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillingEdge2(Name.KillingEdge2, SkillType.SWORD, 11, KillingEdge, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    WaoDao(Name.WaoDao, SkillType.SWORD, 9, SteelSword, SpType.SILVER) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    WaoDao2(Name.WaoDao2, SkillType.SWORD, 13, WaoDao, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Zanbato(Name.Zanbato, SkillType.SWORD, 10, SteelSword, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Zanbato2(Name.Zanbato2, SkillType.SWORD, 14, Zanbato, SpType.PLUS, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    SlayingEdge(Name.SlayingEdge, SkillType.SWORD, 10, SteelSword, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingEdge2(Name.SlayingEdge2, SkillType.SWORD, 14, SlayingEdge, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    AyrasBlade(Name.AyrasBlade, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 3)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 3)
    },
    Folkvangr(Name.Folkvangr, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, 2)
    },
    FalchionM(Name.FalchionM, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    FalchionA(Name.FalchionA, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    FalchionC(Name.FalchionC, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    BindingBlade(Name.BindingBlade, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 2), 2)
    },
    Durandal(Name.Durandal, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, 4)
    },
    SolKatti(Name.SolKatti, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 2)
    },
    Yato(Name.Yato, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, 4)
    },
    Raijinto(Name.Raijinto, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Sieglinde(Name.Sieglinde, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1),
    Tyrfing(Name.Tyrfing, SkillType.SWORD, 16, SilverSword) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) {
                battleUnit.defEffect += 4
            }
            return battleUnit
        }
    },
    Mystletainn(Name.Mystletainn, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Eckesachs(Name.Eckesachs, SkillType.SWORD, 16, SilverSword),
    Siegfried(Name.Siegfried, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Ragnell(Name.Ragnell, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    BlazingDurandal(Name.BlazingDurandal, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 3)
    },
    Amiti(Name.Amiti, SkillType.SWORD, 11, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(equipSpd(armedHero, 3), lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Alondite(Name.Alondite, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    DivineTyrfing(Name.DivineTyrfing, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (results.isEmpty() && source.armedHero.effectiveRange == 2 && source.armedHero.isMagicWeapon()) damage - damage / 2 else damage
    },
    RegalBlade(Name.RegalBlade, SkillType.SWORD, 16, SilverSword) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 2)
    },
    ResoluteBlade(Name.ResoluteBlade, SkillType.SWORD, 16, WaoDao) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Audhulma(Name.Audhulma, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(equipRes(armedHero, 5), lv)
    },
    DarkGreatsword(Name.DarkGreatsword, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(blowAtk(battleUnit, 4), 4)
    },
    FiresweepSword(Name.FiresweepSword, SkillType.SWORD, 11, SteelSword, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    FiresweepSword2(Name.FiresweepSword2, SkillType.SWORD, 15, FiresweepSword, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    Kadomatsu(Name.Kadomatsu, SkillType.SWORD, 10, SteelSword, SpType.SILVER),
    Kadomatsu2(Name.Kadomatsu2, SkillType.SWORD, 14, Kadomatsu, SpType.PLUS, RefinedSkill.RefineType.Range1),
    WingSword(Name.WingSword, SkillType.SWORD, 16, ArmorSlayer2, SpType.LEGEND_W, RefinedSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)),
    BelovedZofia(Name.BelovedZofia, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAllBonus(battleUnit, 4)
    },
    SealedFalchion(Name.SealedFalchion, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = notFullHpAllBonus(battleUnit, 5)
    },
    LightBrand(Name.LightBrand, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defHigherThanResBonus(battleUnit, enemy)
    },
    Meisterschwert(Name.Meisterschwert, SkillType.SWORD, 11, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    NamelessBlade(Name.NamelessBlade, SkillType.SWORD, 16, Sword.KillingEdge2, SpType.LEGEND_W, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    DarkMystletainn(Name.DarkMystletainn, SkillType.SWORD, 16, Sword.SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Safeguard(Name.Safeguard, SkillType.SWORD, 10, SteelSword, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(battleUnit, 7)
    },
    Safeguard2(Name.Safeguard2, SkillType.SWORD, 14, Safeguard, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(battleUnit, 7)
    },
    BarrierBlade(Name.BarrierBlade, SkillType.SWORD, 10, SteelSword, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 7)
    },
    BarrierBlade2(Name.BarrierBlade2, SkillType.SWORD, 14, Safeguard, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 7)
    },
    VassalsBlade(Name.VassalsBlade, SkillType.SWORD, 16, Sword.SlayingEdge) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun stateFlat(battleUnit: BattleUnit, enemy: BattleUnit): Int = spdFlat(battleUnit, enemy)
    },
    Skuld(Name.Skuld, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W),
    RoyalSword(Name.LightBrand, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = accelerateAttackCooldownWithAlly(battleUnit)
    },
    ExaltedFalchion(Name.ExaltedFalchion, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        //周りのバフ分強化するのは周り参照なので書けない…
    },
    Laevatein(Name.Laevatein, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Niu(Name.Niu, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            //これならあってるか？
            battleUnit.atkEffect += enemy.totalBuff / 2
            battleUnit.spdEffect += enemy.totalBuff / 2
            battleUnit.defEffect += enemy.totalBuff / 2
            battleUnit.resEffect += enemy.totalBuff / 2
            return battleUnit
        }
    },
    Missiletainn(Name.Missiletainn, SkillType.SWORD, 16, Sword.SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateTargetCooldown = 1;return battleUnit
        }
    },
    StormSieglinde(Name.StormSieglinde, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            return blowDef(blowRes(battleUnit, 3), 3)
        }

    },
    GoldenDagger(Name.GoldenDagger, SkillType.SWORD, 16, SlayingEdge, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SolitaryBlade(Name.SolitaryBlade, SkillType.SWORD, 16, SlayingEdge, SpType.PLUS, RefinedSkill.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },

    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     */
    override val value get() = name
}