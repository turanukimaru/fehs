package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Sword(override val jp: SkillName, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedWeaponType: RefinedWeapon.RefineType = RefinedWeapon.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    IronSword(SkillName.IronSword, SkillType.SWORD, 6, Skill.NONE, SpType.IRON),
    SteelSword(SkillName.SteelSword, SkillType.SWORD, 8, IronSword, SpType.STEEL),
    SilverSword(SkillName.SilverSword, SkillType.SWORD, 11, SteelSword, SpType.SILVER),
    SilverSword2(SkillName.SilverSword2, SkillType.SWORD, 15, SilverSword, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    ArmorSlayer(SkillName.ArmorSlayer, SkillType.SWORD, 8, SteelSword, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    ArmorSlayer2(SkillName.ArmorSlayer2, SkillType.SWORD, 12, ArmorSlayer, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    Armorsmasher2(SkillName.Armorsmasher2, SkillType.SWORD, 14, ArmorSlayer2, SpType.PLUS, RefinedWeapon.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    BraveSword(SkillName.BraveSword, SkillType.SWORD, 5, SteelSword, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveSword2(SkillName.BraveSword2, SkillType.SWORD, 8, BraveSword, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    RubySword(SkillName.RubySword, SkillType.SWORD, 8, SteelSword, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    RubySword2(SkillName.RubySword2, SkillType.SWORD, 12, RubySword, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    KillingEdge(SkillName.KillingEdge, SkillType.SWORD, 7, SteelSword, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillingEdge2(SkillName.KillingEdge2, SkillType.SWORD, 11, KillingEdge, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    WaoDao(SkillName.WaoDao, SkillType.SWORD, 9, SteelSword, SpType.SILVER) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    WaoDao2(SkillName.WaoDao2, SkillType.SWORD, 13, WaoDao, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Zanbato(SkillName.Zanbato, SkillType.SWORD, 10, SteelSword, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Zanbato2(SkillName.Zanbato2, SkillType.SWORD, 14, Zanbato, SpType.PLUS, RefinedWeapon.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    SlayingEdge(SkillName.SlayingEdge, SkillType.SWORD, 10, SteelSword, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingEdge2(SkillName.SlayingEdge2, SkillType.SWORD, 14, SlayingEdge, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    AyrasBlade(SkillName.AyrasBlade, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 3)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 3)
    },
    Folkvangr(SkillName.Folkvangr, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, 2)
    },
    FalchionM(SkillName.FalchionM, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    FalchionA(SkillName.FalchionA, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    FalchionC(SkillName.FalchionC, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    BindingBlade(SkillName.BindingBlade, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = def(res(battleUnit, 2), 2)
    },
    Durandal(SkillName.Durandal, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atk(battleUnit, 4)
    },
    SolKatti(SkillName.SolKatti, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 2)
    },
    Yato(SkillName.Yato, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spd(battleUnit, 4)
    },
    Raijinto(SkillName.Raijinto, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Sieglinde(SkillName.Sieglinde, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1),
    Tyrfing(SkillName.Tyrfing, SkillType.SWORD, 16, SilverSword) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) {
                battleUnit.defEffect += 4
            }
            return battleUnit
        }
    },
    Mystletainn(SkillName.Mystletainn, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Eckesachs(SkillName.Eckesachs, SkillType.SWORD, 16, SilverSword),
    Siegfried(SkillName.Siegfried, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Ragnell(SkillName.Ragnell, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    BlazingDurandal(SkillName.BlazingDurandal, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 3)
    },
    Amiti(SkillName.Amiti, SkillType.SWORD, 11, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(equipSpd(armedHero, 3), lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Alondite(SkillName.Alondite, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    DivineTyrfing(SkillName.DivineTyrfing, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (results.isEmpty() && source.armedHero.effectiveRange == 2 && source.armedHero.isMagicWeapon()) damage - damage / 2 else damage
    },
    RegalBlade(SkillName.RegalBlade, SkillType.SWORD, 16, SilverSword) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 2)
    },
    ResoluteBlade(SkillName.ResoluteBlade, SkillType.SWORD, 16, WaoDao) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Audhulma(SkillName.Audhulma, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(equipRes(armedHero, 5), lv)
    },
    DarkGreatsword(SkillName.DarkGreatsword, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spd(atk(battleUnit, 4), 4)
    },
    FiresweepSword(SkillName.FiresweepSword, SkillType.SWORD, 11, SteelSword, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    FiresweepSword2(SkillName.FiresweepSword2, SkillType.SWORD, 15, FiresweepSword, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    Kadomatsu(SkillName.Kadomatsu, SkillType.SWORD, 10, SteelSword, SpType.SILVER),
    Kadomatsu2(SkillName.Kadomatsu2, SkillType.SWORD, 14, Kadomatsu, SpType.PLUS, RefinedWeapon.RefineType.Range1),
    WingSword(SkillName.WingSword, SkillType.SWORD, 16, ArmorSlayer2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)),
    BelovedZofia(SkillName.BelovedZofia, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAllBonus(battleUnit, 4)
    },
    SealedFalchion(SkillName.SealedFalchion, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = notFullHpAllBonus(battleUnit, 5)
    },
    LightBrand(SkillName.LightBrand, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defHigherThanResBonus(battleUnit, enemy)
    },
    Meisterschwert(SkillName.Meisterschwert, SkillType.SWORD, 11, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    NamelessBlade(SkillName.NamelessBlade, SkillType.SWORD, 16, Sword.KillingEdge2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    DarkMystletainn(SkillName.DarkMystletainn, SkillType.SWORD, 16, Sword.SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Safeguard(SkillName.Safeguard, SkillType.SWORD, 10, SteelSword, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = def(battleUnit, 7)
    },
    Safeguard2(SkillName.Safeguard2, SkillType.SWORD, 14, Safeguard, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = def(battleUnit, 7)
    },
    BarrierBlade(SkillName.BarrierBlade, SkillType.SWORD, 10, SteelSword, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = res(battleUnit, 7)
    },
    BarrierBlade2(SkillName.BarrierBlade2, SkillType.SWORD, 14, Safeguard, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = res(battleUnit, 7)
    },
    VassalsBlade(SkillName.VassalsBlade, SkillType.SWORD, 16, Sword.SlayingEdge) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun stateFlat(battleUnit: BattleUnit, enemy: BattleUnit): Int = spdFlat(battleUnit, enemy)
    },
    Skuld(SkillName.Skuld, SkillType.SWORD, 16, SilverSword, SpType.LEGEND_W),
    RoyalSword(SkillName.LightBrand, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = accelerateAttackCooldownWithAlly(battleUnit)
    },
    ExaltedFalchion(SkillName.ExaltedFalchion, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        //周りのバフ分強化するのは周り参照なので書けない…
    },
    Laevatein(SkillName.Laevatein, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Niu(SkillName.Niu, SkillType.SWORD, 16, SilverSword, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
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
    Missiletainn(SkillName.Missiletainn, SkillType.SWORD, 16, Sword.SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateTargetCooldown = 1
            return battleUnit
        }
    },
    StormSieglinde(SkillName.StormSieglinde, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.buffDebuffTrigger) {
                battleUnit.accelerateAttackCooldown = 1
                return def(res(battleUnit, 3), 3)
            }
            return battleUnit
        }

    },
    GoldenDagger(SkillName.GoldenDagger, SkillType.SWORD, 16, SlayingEdge, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SolitaryBlade(SkillName.SolitaryBlade, SkillType.SWORD, 16, SlayingEdge, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Gjoll(SkillName.Gjoll, SkillType.SWORD, 16, SilverSword) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.buffDebuffTrigger || enemy.isDebuffed) {
                battleUnit.followupable = true
                enemy.antiFollowup = true
            }
            return battleUnit
        }

    },
    GladiatorsBlade(SkillName.GladiatorsBlade, SkillType.SWORD, 16, KillingEdge, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 1)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 1)
    },
    ScarletSword(SkillName.ScarletSword, SkillType.SWORD, 16, SilverSword, SpType.PLUS, RefinedWeapon.RefineType.Range1) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    WhitewingBlade(SkillName.WhitewingBlade, SkillType.SWORD, 16, RubySword, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     */
    override val value get() = name
}