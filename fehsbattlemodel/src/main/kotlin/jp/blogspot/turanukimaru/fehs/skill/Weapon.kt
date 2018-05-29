package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Weapon(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, val refineSkillType: RefineSkill.RefineType = RefineSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Skill {

// SWORD
    IronSword(Name.IronSword, SkillType.SWORD, 6),
    SteelSword(Name.SteelSword, SkillType.SWORD, 8, IronSword),
    SilverSword(Name.SilverSword, SkillType.SWORD, 11, SteelSword),
    SilverSword2(Name.SilverSword2, SkillType.SWORD, 15, SilverSword, RefineSkill.RefineType.Range1),
    ArmorSlayer(Name.ArmorSlayer, SkillType.SWORD, 8, SteelSword, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    ArmorSlayer2(Name.ArmorSlayer2, SkillType.SWORD, 12, ArmorSlayer, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    Armorsmasher2(Name.Armorsmasher2, SkillType.SWORD, 14, ArmorSlayer2, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    BraveSword(Name.BraveSword, SkillType.SWORD, 5, SteelSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveSword2(Name.BraveSword2, SkillType.SWORD, 8, BraveSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    RubySword(Name.RubySword, SkillType.SWORD, 8, SteelSword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    RubySword2(Name.RubySword2, SkillType.SWORD, 12, RubySword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    KillingEdge(Name.KillingEdge, SkillType.SWORD, 7, SteelSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillingEdge2(Name.KillingEdge2, SkillType.SWORD, 11, KillingEdge) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    WaoDao(Name.WaoDao, SkillType.SWORD, 9, SteelSword) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    WaoDao2(Name.WaoDao2, SkillType.SWORD, 13, WaoDao, RefineSkill.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Zanbato(Name.Zanbato, SkillType.SWORD, 10, SteelSword, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Zanbato2(Name.Zanbato2, SkillType.SWORD, 14, Zanbato, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    SlayingEdge(Name.SlayingEdge, SkillType.SWORD, 10, SteelSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingEdge2(Name.SlayingEdge2, SkillType.SWORD, 14, SlayingEdge, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    AyrasBlade(Name.AyrasBlade, SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipSpd(armedHero, 3), lv)
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 3)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 3)
    },
    Folkvangr(Name.Folkvangr, SkillType.SWORD, 16, SilverSword) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, 2)
    },
    FalchionM(Name.FalchionM, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    FalchionA(Name.FalchionA, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    FalchionC(Name.FalchionC, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)),
    BindingBlade(Name.BindingBlade, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 2), 2)
    },
    Durandal(Name.Durandal, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, 4)
    },
    SolKatti(Name.SolKatti, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 2)
    },
    Yato(Name.Yato, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, 4)
    },
    Raijinto(Name.Raijinto, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Sieglinde(Name.Sieglinde, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1),
    Tyrfing(Name.Tyrfing, SkillType.SWORD, 16, SilverSword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) {
                battleUnit.defEffect += 4
            }
            return battleUnit
        }
    },
    Mystletainn(Name.Mystletainn, SkillType.SWORD, 16, SilverSword, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Eckesachs(Name.Eckesachs, SkillType.SWORD, 16, SilverSword),
    Siegfried(Name.Siegfried, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Ragnell(Name.Ragnell, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    BlazingDurandal(Name.BlazingDurandal, SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipAtk(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 3)
    },
    Amiti(Name.Amiti, SkillType.SWORD, 11, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(equipSpd(armedHero, 3), lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Alondite(Name.Alondite, SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    DivineTyrfing(Name.DivineTyrfing, SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (results.isEmpty() && source.armedHero.effectiveRange == 2 && source.armedHero.isMagicWeapon()) damage - damage / 2 else damage
    },
    RegalBlade(Name.RegalBlade, SkillType.SWORD, 16, SilverSword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 2)
    },
    ResoluteBlade(Name.ResoluteBlade, SkillType.SWORD, 16, WaoDao) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipAtk(armedHero, 3), lv)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Audhulma(Name.Audhulma, SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(equipRes(armedHero, 5), lv)
    },
    DarkGreatsword(Name.DarkGreatsword, SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(blowAtk(battleUnit, 4), 4)
    },
    FiresweepSword(Name.FiresweepSword, SkillType.SWORD, 11, SteelSword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    FiresweepSword2(Name.FiresweepSword2, SkillType.SWORD, 15, FiresweepSword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    Kadomatsu(Name.Kadomatsu, SkillType.SWORD, 10, SteelSword, RefineSkill.RefineType.Range1),
    Kadomatsu2(Name.Kadomatsu2, SkillType.SWORD, 14, Kadomatsu, RefineSkill.RefineType.Range1),
    WingSword(Name.WingSword, SkillType.SWORD, 16, ArmorSlayer2, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)),
    BelovedZofia(Name.BelovedZofia, SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipDef(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAllBonus(battleUnit, 4)
    },
    SealedFalchion(Name.SealedFalchion, SkillType.SWORD, 16, SilverSword) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = notFullHpAllBonus(effectiveAgainst(WeaponType.DRAGON, battleUnit, enemy), 5)
    },
    LightBrand(Name.LightBrand, SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipDef(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defHigherThanResBonus(battleUnit, enemy)
    },
    Meisterschwert(Name.Meisterschwert, SkillType.SWORD, 11, SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    NamelessBlade(Name.NamelessBlade, SkillType.SWORD, 16, Weapon.KillingEdge2, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    DarkMystletainn(Name.DarkMystletainn, SkillType.SWORD, 16, Weapon.SilverSword) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Safeguard(Name.Safeguard, SkillType.SWORD, 10, SteelSword) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(battleUnit, 7)
    },
    Safeguard2(Name.Safeguard2, SkillType.SWORD, 14, Safeguard, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(battleUnit, 7)
    },

    //LANCE
    IronLance(Name.IronLance, SkillType.LANCE, 6),
    SteelLance(Name.SteelLance, SkillType.LANCE, 8, IronLance),
    SilverLance(Name.SilverLance, SkillType.LANCE, 11, SteelLance),
    SilverLance2(Name.SilverLance2, SkillType.LANCE, 15, SilverLance, RefineSkill.RefineType.Range1),
    KillerLance(Name.KillerLance, SkillType.LANCE, 7, SteelLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillerLance2(Name.KillerLance2, SkillType.LANCE, 11, KillerLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    BraveLance(Name.BraveLance, SkillType.LANCE, 5, SteelLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveLance2(Name.BraveLance2, SkillType.LANCE, 8, BraveLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    SapphireLance(Name.SapphireLance, SkillType.LANCE, 8, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    SapphireLance2(Name.SapphireLance2, SkillType.LANCE, 12, SapphireLance) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    HeavySpear(Name.HeavySpear, SkillType.LANCE, 8, SteelLance, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    HeavySpear2(Name.HeavySpear2, SkillType.LANCE, 12, HeavySpear, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    SlayingSpear(Name.SlayingSpear, SkillType.LANCE, 10) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingSpear2(Name.SlayingSpear2, SkillType.LANCE, 14, SlayingSpear, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    CarrotLance(Name.CarrotLance, SkillType.LANCE, 9, SteelLance) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotLance2(Name.CarrotLance2, SkillType.LANCE, 13, CarrotLance, RefineSkill.RefineType.Range1) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    FirstBite(Name.FirstBite, SkillType.LANCE, 10, SteelLance),
    FirstBite2(Name.FirstBite2, SkillType.LANCE, 14, FirstBite, RefineSkill.RefineType.Range1),
    FiresweepLance(Name.FiresweepLance, SkillType.LANCE, 11, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    FiresweepLance2(Name.FiresweepLance2, SkillType.LANCE, 15, FiresweepLance) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, enemy, 0)
    },
    DeftHarpoon(Name.DeftHarpoon, SkillType.LANCE, 10, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    DeftHarpoon2(Name.DeftHarpoon2, SkillType.LANCE, 14, DeftHarpoon, RefineSkill.RefineType.Range1) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    Ridersbane(Name.Ridersbane, SkillType.LANCE, 10, SteelLance, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Ridersbane2(Name.Ridersbane2, SkillType.LANCE, 14, Ridersbane, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    BerkutsLance(Name.BerkutsLance, SkillType.LANCE, 10, SteelLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },
    BerkutsLance2(Name.BerkutsLance2, SkillType.LANCE, 14, BerkutsLance, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },
    SlayingLance(Name.SlayingLance, SkillType.LANCE, 10, SteelLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingLance2(Name.SlayingLance2, SkillType.LANCE, 14, SlayingLance, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Fensalir(Name.Fensalir, SkillType.LANCE, 16, SilverLance),
    Siegmund(Name.Siegmund, SkillType.LANCE, 16, SilverLance, RefineSkill.RefineType.Range1),
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
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(equipAtk(equipSpd(armedHero, 2), 2), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHpLoss(battleUnit, 4)
    },
    Geirskogul(Name.Geirskogul, SkillType.LANCE, 16, SilverLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipDef(armedHero, 3), lv)
    },
    Leiptr(Name.Leiptr, SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    BrightNaginata(Name.BrightNaginata, SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowAtk(battleUnit, 4), 4)
    },
    Tannenboom(Name.Tannenboom, SkillType.LANCE, 10, SteelLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Tannenboom2(Name.Tannenboom2, SkillType.LANCE, 14, Tannenboom, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    CasaBlanca(Name.CasaBlanca, SkillType.LANCE, 10, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiBuffBonus(battleUnit, enemy)
    },
    CasaBlanca2(Name.CasaBlanca2, SkillType.LANCE, 14, CasaBlanca) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiBuffBonus(battleUnit, enemy)
    },
    HinokasSpear(Name.HinokasSpear, SkillType.LANCE, 16, SilverLance, RefineSkill.RefineType.Range1) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 4), 4) else battleUnit
    },
    FlameSiegmund(Name.FlameSiegmund, SkillType.LANCE, 16, SilverLance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipAtk(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 10)
    },
    HarmonicLance(Name.HarmonicLance, SkillType.LANCE, 9, SteelLance, RefineSkill.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    HarmonicLance2(Name.HarmonicLance2, SkillType.LANCE, 13, HarmonicLance, RefineSkill.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Rhomphaia(Name.Rhomphaia, SkillType.LANCE, 16, SilverLance2, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)),

    //AXE
    IronAxe(Name.IronAxe, SkillType.AXE, 6),
    SteelAxe(Name.SteelAxe, SkillType.AXE, 8, IronAxe),
    SilverAxe(Name.SilverAxe, SkillType.AXE, 11, SteelAxe),
    SilverAxe2(Name.SilverAxe2, SkillType.AXE, 15, SilverAxe, RefineSkill.RefineType.Range1),
    KillerAxe(Name.KillerAxe, SkillType.AXE, 7, SteelAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillerAxe2(Name.KillerAxe2, SkillType.AXE, 11, KillerAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    BraveAxe(Name.BraveAxe, SkillType.AXE, 5, SteelAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveAxe2(Name.BraveAxe2, SkillType.AXE, 8, BraveAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Hammer(Name.Hammer, SkillType.AXE, 8, SteelAxe, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    Hammer2(Name.Hammer2, SkillType.AXE, 12, Hammer, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    SlayingHammer2(Name.SlayingHammer2, SkillType.AXE, 14, Hammer2, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)),
    EmeraldAxe(Name.EmeraldAxe, SkillType.AXE, 8, SteelAxe) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    EmeraldAxe2(Name.EmeraldAxe2, SkillType.AXE, 12, EmeraldAxe) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    SlayingAxe(Name.SlayingAxe, SkillType.AXE, 10, SteelAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingAxe2(Name.SlayingAxe2, SkillType.AXE, 14, SlayingAxe, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    CarrotAxe(Name.CarrotAxe, SkillType.AXE, 9, SteelAxe) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotAxe2(Name.CarrotAxe2, SkillType.AXE, 13, CarrotAxe, RefineSkill.RefineType.Range1) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    LegionsAxe(Name.LegionsAxe, SkillType.AXE, 10, SteelAxe),
    LegionsAxe2(Name.LegionsAxe2, SkillType.AXE, 14, LegionsAxe, RefineSkill.RefineType.Range1),
    MelonCrusher(Name.MelonCrusher, SkillType.AXE, 10, SteelAxe) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    MelonCrusher2(Name.MelonCrusher2, SkillType.AXE, 14, MelonCrusher, RefineSkill.RefineType.Range1) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    LilithFloatie(Name.LilithFloatie, SkillType.AXE, 10, SteelAxe),
    LilithFloatie2(Name.LilithFloatie2, SkillType.AXE, 14, LilithFloatie, RefineSkill.RefineType.Range1),
    Noatun(Name.Noatun, SkillType.AXE, 16, SilverAxe),
    Hauteclere(Name.Hauteclere, SkillType.AXE, 16, SilverAxe, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Armoads(Name.Armoads, SkillType.AXE, 16, SilverAxe) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 2)
    },
    Urvan(Name.Urvan, SkillType.AXE, 16, SilverAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (results.isNotEmpty() && results.last().side != battleUnit.side) damage - damage * 8 / 10 else damage
    },
    Uror(Name.Uror, SkillType.AXE, 16, SilverAxe),
    StoutTomahawk(Name.StoutTomahawk, SkillType.AXE, 16, SilverAxe) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    SackOGifts(Name.SackOGifts, SkillType.AXE, 10, SteelAxe) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    SackOGifts2(Name.SackOGifts2, SkillType.AXE, 14, SackOGifts, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Handbell(Name.Handbell, SkillType.AXE, 10, SteelAxe) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Handbell2(Name.Handbell2, SkillType.AXE, 14, Handbell, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Hagoita(Name.Hagoita, SkillType.AXE, 10, SteelAxe, RefineSkill.RefineType.Range1),
    Hagoita2(Name.Hagoita2, SkillType.AXE, 14, Hagoita, RefineSkill.RefineType.Range1),
    BerserkArmads(Name.BerserkArmads, SkillType.AXE, 16, SilverAxe) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = wrath(battleUnit, damage, 75)
    },
    Basilikos(Name.Basilikos, SkillType.AXE, 16, BraveAxe2, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    GiantSpoon(Name.GiantSpoon, SkillType.AXE, 9, SteelAxe) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    GiantSpoon2(Name.GiantSpoon2, SkillType.AXE, 13, GiantSpoon, RefineSkill.RefineType.Range1) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Poleaxe(Name.Poleaxe, SkillType.AXE, 10, SteelAxe, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Poleaxe2(Name.Poleaxe2, SkillType.AXE, 14, Poleaxe, RefineSkill.RefineType.Range1, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    CamillasAxe(Name.CamillasAxe, SkillType.AXE, 16, BraveAxe2, RefineSkill.RefineType.Range1) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 4), 4) else battleUnit
    },
    ArdentService(Name.ArdentService, SkillType.AXE, 10, SteelAxe),
    ArdentService2(Name.ArdentService2, SkillType.AXE, 14, ArdentService, RefineSkill.RefineType.Range1),

    //BOW
    IronBow(Name.IronBow, SkillType.BOW, 4, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SteelBow(Name.SteelBow, SkillType.BOW, 6, IronBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SilverBow(Name.SilverBow, SkillType.BOW, 9, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SilverBow2(Name.SilverBow2, SkillType.BOW, 13, SilverBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    BraveBow(Name.BraveBow, SkillType.BOW, 4, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.attackEffect(doubleAttack(battleUnit), enemy, lv)
    },
    BraveBow2(Name.BraveBow2, SkillType.BOW, 7, BraveBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.attackEffect(doubleAttack(battleUnit), enemy, lv)
    },
    KillerBow(Name.KillerBow, SkillType.BOW, 5, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KillerBow2(Name.KillerBow2, SkillType.BOW, 9, KillerBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    AssassinsBow(Name.AssassinsBow, SkillType.BOW, 7, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(weaponBreaker(battleUnit, enemy, WeaponType.DAGGER, 3), enemy, lv)
    },
    AssassinsBow2(Name.AssassinsBow2, SkillType.BOW, 11, AssassinsBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(weaponBreaker(battleUnit, enemy, WeaponType.DAGGER, 3), enemy, lv)
    },
    GuardBow2(Name.GuardBow2, SkillType.BOW, 12, AssassinsBow2, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    FiresweepBow(Name.FiresweepBow, SkillType.BOW, 7, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(disableEachCounter(battleUnit, enemy, 0), enemy, lv)
    },
    FiresweepBow2(Name.FiresweepBow2, SkillType.BOW, 11, FiresweepBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(disableEachCounter(battleUnit, enemy, 0), enemy, lv)
    },
    CupidArrow(Name.CupidArrow, SkillType.BOW, 8, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    CupidArrow2(Name.CupidArrow2, SkillType.BOW, 12, CupidArrow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    RefreshingBolt(Name.RefreshingBolt, SkillType.BOW, 8, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(fullHpBonus(battleUnit, 2), enemy, lv)
    },
    RefreshingBolt2(Name.RefreshingBolt2, SkillType.BOW, 12, RefreshingBolt, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(fullHpBonus(battleUnit, 2), enemy, lv)
    },
    ClarissesBow(Name.ClarissesBow, SkillType.BOW, 7, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    ClarissesBow2(Name.ClarissesBow2, SkillType.BOW, 11, ClarissesBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    SlayingBow(Name.SlayingBow, SkillType.BOW, 8, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SlayingBow2(Name.SlayingBow2, SkillType.BOW, 12, SlayingBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    Parthia(Name.Parthia, SkillType.BOW, 14, SilverBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.attackEffect(blowRes(battleUnit, 2), enemy, lv)
    },
    FujinYumi(Name.FujinYumi, SkillType.BOW, 14, SilverBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    Nidhogg(Name.Nidhogg, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(allBonus(battleUnit, battleUnit.adjacentUnits * 2), enemy, lv)
    },
    Mulagir(Name.Mulagir, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipSpd(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(antiMagicBuffBonus(battleUnit, enemy), enemy, lv)
    },
    MonstrousBow(Name.MonstrousBow, SkillType.BOW, 8, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    MonstrousBow2(Name.MonstrousBow2, SkillType.BOW, 12, MonstrousBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    HamaYa(Name.HamaYa, SkillType.BOW, 8, SteelBow, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    HamaYa2(Name.HamaYa2, SkillType.BOW, 12, HamaYa, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    Gratia(Name.Gratia, SkillType.BOW, 8, SteelBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(antiRangedWeaponBuffBonus(battleUnit, enemy), enemy, lv)
    },
    Gratia2(Name.Gratia2, SkillType.BOW, 12, Gratia, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(antiRangedWeaponBuffBonus(battleUnit, enemy), enemy, lv)
    },
    Skadi(Name.Skadi, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipSpd(armedHero, 3), lv)
    },
    WarriorPrincess(Name.WarriorPrincess, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER, MoveType.ARMORED)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipSpd(armedHero, 3), lv)
    },
    SwiftMulagir(Name.SwiftMulagir, SkillType.BOW, 14, SilverBow, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
        //マップ実装時には敵ユニット数との判定が必要
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = super.bothEffect(if (battleUnit.adjacentUnits > 0) {
            blowAtk(blowSpd(battleUnit, 5), 5)
        } else battleUnit, enemy, lv)
    },

    //DAGGER
    IronDagger(Name.IronDagger, SkillType.DAGGER, 3),
    SteelDagger(Name.SteelDagger, SkillType.DAGGER, 5, IronDagger),
    SilverDagger(Name.SilverDagger, SkillType.DAGGER, 7, SteelDagger),
    SilverDagger2(Name.SilverDagger2, SkillType.DAGGER, 10, SilverDagger, RefineSkill.RefineType.Range2),
    RogueDagger(Name.RogueDagger, SkillType.DAGGER, 4, SteelDagger),
    RogueDagger2(Name.RogueDagger2, SkillType.DAGGER, 7, RogueDagger, RefineSkill.RefineType.Range2),
    SmokeDagger(Name.SmokeDagger, SkillType.DAGGER, 6, SteelDagger),
    SmokeDagger2(Name.SmokeDagger2, SkillType.DAGGER, 9, SmokeDagger, RefineSkill.RefineType.Range2),
    PoisonDagger(Name.PoisonDagger, SkillType.DAGGER, 2, SteelDagger, effectiveAgainstMoveType = arrayOf(MoveType.INFANTRY)),
    PoisonDagger2(Name.PoisonDagger2, SkillType.DAGGER, 5, PoisonDagger, effectiveAgainstMoveType = arrayOf(MoveType.INFANTRY)),
    Seashell(Name.Seashell, SkillType.DAGGER, 7, SteelDagger) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    Seashell2(Name.Seashell2, SkillType.DAGGER, 10, Seashell, RefineSkill.RefineType.Range2) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    DancersFan(Name.DancersFan, SkillType.DAGGER, 7, SteelDagger),
    DancersFan2(Name.DancersFan2, SkillType.DAGGER, 10, DancersFan, RefineSkill.RefineType.Range2),
    DeathlyDagger(Name.DeathlyDagger, SkillType.DAGGER, 11, SilverDagger, RefineSkill.RefineType.Range2) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 7)
    },
    KittyPaddle(Name.KittyPaddle, SkillType.DAGGER, 5, SteelDagger, effectiveAgainstWeaponType = arrayOf(WeaponType.RTOME, WeaponType.BTOME, WeaponType.GTOME)),
    KittyPaddle2(Name.KittyPaddle2, SkillType.DAGGER, 8, KittyPaddle, effectiveAgainstWeaponType = arrayOf(WeaponType.RTOME, WeaponType.BTOME, WeaponType.GTOME)),
    KagamiMochi(Name.KagamiMochi, SkillType.DAGGER, 8, SteelDagger) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KagamiMochi2(Name.KagamiMochi2, SkillType.DAGGER, 12, KagamiMochi, RefineSkill.RefineType.Range2) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    FeliciasPlate(Name.FeliciasPlate, SkillType.PENETRATE_DAGGER, 14, SilverDagger2, RefineSkill.RefineType.Range2),
    Peshkatz(Name.Peshkatz, SkillType.DAGGER, 14, SilverDagger),
    LethalCarrot(Name.LethalCarrot, SkillType.DAGGER, 8, SteelDagger) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    LethalCarrot2(Name.LethalCarrot2, SkillType.DAGGER, 12, LethalCarrot, RefineSkill.RefineType.Range2) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    BarbShuriken(Name.BarbShuriken, SkillType.DAGGER, 8, SteelDagger) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    BarbShuriken2(Name.BarbShuriken2, SkillType.DAGGER, 12, BarbShuriken, RefineSkill.RefineType.Range2) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },

    //STAFF
    Assault(Name.Assault, SkillType.STAFF, 10),
    Absorb(Name.Absorb, SkillType.STAFF, 4, Assault) {
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int = battleUnit.heal(damage * 5 / 10)
    },
    Absorb2(Name.Absorb2, SkillType.STAFF, 7, Absorb, RefineSkill.RefineType.Staff) {
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int = battleUnit.heal(damage * 5 / 10)
    },
    Candlelight(Name.Candlelight, SkillType.STAFF, 7, Assault),
    Candlelight2(Name.Candlelight2, SkillType.STAFF, 11, Assault, RefineSkill.RefineType.Staff),
    Gravity(Name.Gravity, SkillType.STAFF, 7, Assault),
    Gravity2(Name.Gravity2, SkillType.STAFF, 10, Gravity, RefineSkill.RefineType.Staff),
    Fear(Name.Fear, SkillType.STAFF, 5, Assault),
    Fear2(Name.Fear2, SkillType.STAFF, 12, Fear, RefineSkill.RefineType.Staff),
    Slow(Name.Slow, SkillType.STAFF, 5, Assault),
    Slow2(Name.Slow2, SkillType.STAFF, 12, Slow, RefineSkill.RefineType.Staff),
    Panic(Name.Panic, SkillType.STAFF, 6, Assault),
    Panic2(Name.Panic2, SkillType.STAFF, 11, Panic, RefineSkill.RefineType.Staff),
    Pain(Name.Pain, SkillType.STAFF, 3, Assault) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 10)
    },
    Pain2(Name.Pain2, SkillType.STAFF, 10, Pain, RefineSkill.RefineType.Staff) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 10)
    },

    //RTOME
    Flux(Name.Flux, SkillType.RTOME, 4),
    Fire(Name.Fire, SkillType.RTOME, 4),
    Ruin(Name.Ruin, SkillType.RTOME, 6, Flux),
    Elfire(Name.Elfire, SkillType.RTOME, 6, Fire),
    Rauorwolf(Name.Rauorwolf, SkillType.RTOME, 10, Elfire, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Rauorwolf2(Name.Rauorwolf2, SkillType.RTOME, 10, Rauorwolf, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    KeenRauorwolf2(Name.KeenRauorwolf2, SkillType.RTOME, 12, Rauorwolf2, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Rauorraven(Name.Rauorraven, SkillType.RTOME, 7, Elfire) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Rauorraven2(Name.Rauorraven2, SkillType.RTOME, 11, Rauorraven) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Rauorblade(Name.Rauorblade, SkillType.RTOME, 9, Elfire) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Rauorblade2(Name.Rauorblade2, SkillType.RTOME, 13, Rauorblade) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Rauorowl(Name.Rauorowl, SkillType.RTOME, 6, Elfire) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Rauorowl2(Name.Rauorowl2, SkillType.RTOME, 10, Rauorowl, RefineSkill.RefineType.Range2) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Fenrir(Name.Fenrir, SkillType.RTOME, 9, Elfire),
    Fenrir2(Name.Fenrir2, SkillType.RTOME, 13, Fenrir, RefineSkill.RefineType.Range2),
    Bolganone(Name.Bolganone, SkillType.RTOME, 9, Elfire),
    Bolganone2(Name.Bolganone2, SkillType.RTOME, 13, Bolganone, RefineSkill.RefineType.Range2),
    TomatoTome(Name.TomatoTome, SkillType.RTOME, 8, Elfire),
    TomatoTome2(Name.TomatoTome2, SkillType.RTOME, 12, TomatoTome, RefineSkill.RefineType.Range2),
    Brynhildr(Name.Brynhildr, SkillType.RTOME, 14, Bolganone, RefineSkill.RefineType.Range2),
    Cymbeline(Name.Cymbeline, SkillType.RTOME, 14, Bolganone, RefineSkill.RefineType.Range2),
    Ragnarok(Name.Ragnarok, SkillType.RTOME, 14, Bolganone) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAtkSpdBonus(battleUnit, 5)
    },
    Valflame(Name.Valflame, SkillType.RTOME, 14, Bolganone),
    Grimoire(Name.Grimoire, SkillType.RTOME, 14, Bolganone),
    Candelabra(Name.Candelabra, SkillType.RTOME, 8, Elfire) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Candelabra2(Name.Candelabra2, SkillType.RTOME, 12, Candelabra, RefineSkill.RefineType.Range2) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Gleipnir(Name.Gleipnir, SkillType.RTOME, 14, Bolganone) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 3)
    },
    Naglfar(Name.Naglfar, SkillType.RTOME, 14, Bolganone) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Forblaze(Name.Forblaze, SkillType.RTOME, 14, Bolganone2, RefineSkill.RefineType.Range2),
    Loptous(Name.Loptous, SkillType.BTOME, 14, Bolganone) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.weapon.effectiveAgainstWeaponType.contains(WeaponType.DRAGON)) {
                enemy.effectiveAgainst = EffectiveAgainst.DRAGON
            } else {
                println("called enemy.atkEffect -= 6")
                enemy.atkEffect -= 6
            }
            return battleUnit
        }
    },
    MuspellFireposy(Name.MuspellFireposy, SkillType.RTOME, 14, Bolganone) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipSpd(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, battleUnit.adjacentUnits * 2), battleUnit.adjacentUnits * 2)
    },

    //BTOME
    Thunder(Name.Thunder, SkillType.BTOME, 4),
    Elthunder(Name.Elthunder, SkillType.BTOME, 6, Thunder),
    Blarwolf(Name.Blarwolf, SkillType.BTOME, 6, Elthunder, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Blarwolf2(Name.Blarwolf2, SkillType.BTOME, 10, Blarwolf, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    KeenBlarwolf2(Name.KeenBlarwolf2, SkillType.BTOME, 12, Blarwolf2, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Blarraven(Name.Blarraven, SkillType.BTOME, 7, Elthunder) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Blarraven2(Name.Blarraven2, SkillType.BTOME, 11, Blarraven) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Blarblade(Name.Blarblade, SkillType.BTOME, 9, Elthunder) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Blarblade2(Name.Blarblade2, SkillType.BTOME, 13, Blarblade) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Blarowl(Name.Blarowl, SkillType.BTOME, 6, Elthunder) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Blarowl2(Name.Blarowl2, SkillType.BTOME, 10, Blarowl, RefineSkill.RefineType.Range2) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Blarserpent(Name.Blarserpent, SkillType.BTOME, 8, Elthunder, RefineSkill.RefineType.Range2) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    Blarserpent2(Name.Blarserpent2, SkillType.BTOME, 12, Blarserpent, RefineSkill.RefineType.Range2) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    Thoron(Name.Thoron, SkillType.BTOME, 9, Elthunder),
    Thoron2(Name.Thoron2, SkillType.BTOME, 13, Thoron, RefineSkill.RefineType.Range2),
    BlueEgg(Name.BlueEgg, SkillType.BTOME, 7, Elthunder),
    BlueEgg2(Name.BlueEgg2, SkillType.BTOME, 11, BlueEgg, RefineSkill.RefineType.Range2),
    BlessedBouquet(Name.BlessedBouquet, SkillType.BTOME, 9, Elthunder),
    BlessedBouquet2(Name.BlessedBouquet2, SkillType.BTOME, 12, BlessedBouquet, RefineSkill.RefineType.Range2),
    SealifeTome(Name.SealifeTome, SkillType.BTOME, 8, Elthunder),
    SealifeTome2(Name.SealifeTome2, SkillType.BTOME, 12, SealifeTome, RefineSkill.RefineType.Range2),
    DancersScore(Name.DancersScore, SkillType.BTOME, 8, Elthunder),
    DancersScore2(Name.DancersScore2, SkillType.BTOME, 12, DancersScore, RefineSkill.RefineType.Range2),
    DarkAura(Name.DarkAura, SkillType.BTOME, 14, Thoron),
    Valaskjalf(Name.Valaskjalf, SkillType.BTOME, 14, Thoron) {
        override fun counterPlan(fightPlan: FightPlan, lv: Int): FightPlan = vantage(fightPlan, 2)
    },
    Aura(Name.Aura, SkillType.BTOME, 14, Thoron),
    DireThunder(Name.DireThunder, SkillType.BTOME, 9, Thoron) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBrave(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    WeirdingTome(Name.WeirdingTome, SkillType.BTOME, 14, Thoron) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipSpd(armedHero, 3), lv)
    },
    Thani(Name.Thani, SkillType.BTOME, 14, Thoron, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if ((source.armedHero.baseHero.moveType == MoveType.ARMORED || source.armedHero.baseHero.moveType == MoveType.CAVALRY)
                        && source.armedHero.effectiveRange == 2
                        && (!results.any { r -> r.side != battleUnit.side })) damage - damage * 3 / 10 else damage
    },
    Ivaldi(Name.Ivaldi, SkillType.BTOME, 14, Thoron) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipDef(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 3)
    },
    BlueGift(Name.BlueGift, SkillType.BTOME, 8, Thoron) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    BlueGift2(Name.BlueGift2, SkillType.BTOME, 12, BlueGift, RefineSkill.RefineType.Range2) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    HuginnsEgg(Name.HuginnsEgg, SkillType.BTOME, 14, Thoron) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
    },
    WargodsTome(Name.WargodsTome, SkillType.BTOME, 14, Thoron),
    Mjolnir(Name.Mjolnir, SkillType.BTOME, 14, Thoron) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, 6)
    },
    FreshBouquet(Name.FreshBouquet, SkillType.BTOME, 8, Thoron),
    FreshBouquet2(Name.FreshBouquet2, SkillType.BTOME, 12, FreshBouquet, RefineSkill.RefineType.Range2),

    //GTOME
    Wind(Name.Wind, SkillType.GTOME, 4),
    Elwind(Name.Elwind, SkillType.GTOME, 6, Wind),
    Rexcalibur(Name.Rexcalibur, SkillType.GTOME, 9, Elwind),
    Rexcalibur2(Name.Rexcalibur2, SkillType.GTOME, 14, Rexcalibur, RefineSkill.RefineType.Range2),
    Gronnwolf(Name.Gronnwolf, SkillType.GTOME, 6, Elwind, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Gronnwolf2(Name.Gronnwolf2, SkillType.GTOME, 10, Gronnwolf, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    KeenGronnwolf2(Name.KeenGronnwolf2, SkillType.GTOME, 12, Gronnwolf2, RefineSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Gronnraven(Name.Gronnraven, SkillType.GTOME, 7, Elwind) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Gronnraven2(Name.Gronnraven2, SkillType.GTOME, 11, Gronnraven) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Gronnblade(Name.Gronnblade, SkillType.GTOME, 9, Elwind) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Gronnblade2(Name.Gronnblade2, SkillType.GTOME, 13, Gronnblade) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Gronnowl(Name.Gronnowl, SkillType.GTOME, 6, Elwind) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Gronnowl2(Name.Gronnowl2, SkillType.GTOME, 10, Gronnowl, RefineSkill.RefineType.Range2) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    GreenEgg(Name.GreenEgg, SkillType.GTOME, 7, Elwind) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    GreenEgg2(Name.GreenEgg2, SkillType.GTOME, 11, GreenEgg, RefineSkill.RefineType.Range2) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    HibiscusTome(Name.HibiscusTome, SkillType.GTOME, 8, Elwind),
    HibiscusTome2(Name.HibiscusTome2, SkillType.GTOME, 12, HibiscusTome, RefineSkill.RefineType.Range2),
    DancersRing(Name.DancersRing, SkillType.GTOME, 8, Elwind),
    DancersRing2(Name.DancersRing2, SkillType.GTOME, 12, DancersRing, RefineSkill.RefineType.Range2),
    Elivagar(Name.Elivagar, SkillType.GTOME, 14, Rexcalibur),
    Excalibur(Name.Excalibur, SkillType.GTOME, 14, Rexcalibur, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)),
    Naga(Name.Naga, SkillType.GTOME, 14, Rexcalibur, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 2), 2)
    },
    DarkExcalibur(Name.DarkExcalibur, SkillType.GTOME, 14, Rexcalibur) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    DivineNaga(Name.DivineNaga, SkillType.GTOME, 14, Rexcalibur, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit =super.bothEffect( antiBuffBonus(battleUnit, enemy), enemy, lv)
    },
    SpectralTome(Name.SpectralTome, SkillType.GTOME, 8, Elwind),
    SpectralTome2(Name.SpectralTome2, SkillType.GTOME, 12, SpectralTome, RefineSkill.RefineType.Range2),
    Blizzard(Name.Blizzard, SkillType.GTOME, 14, Rexcalibur) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = debuffBonus(battleUnit, enemy)
    },
    GreenGift(Name.GreenGift, SkillType.GTOME, 8, Elwind) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    GreenGift2(Name.GreenGift2, SkillType.GTOME, 12, GreenGift) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy)
    },
    GrimasTruth(Name.GrimasTruth, SkillType.GTOME, 14, Rexcalibur) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipDef(armedHero, 3), lv)
    },
    WindsBrand(Name.WindsBrand, SkillType.GTOME, 14, Rexcalibur, RefineSkill.RefineType.Range2),
    MuninnsEgg(Name.MuninnsEgg, SkillType.BTOME, 14, Rexcalibur) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipRes(armedHero, 3), lv)
    },
    Thunderhead(Name.Thunderhead, SkillType.GTOME, 14, Rexcalibur) {
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    NiflFrostflowers(Name.NiflFrostflowers, SkillType.GTOME, 14, Rexcalibur) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipAtk(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, battleUnit.adjacentUnits * 2), battleUnit.adjacentUnits * 2)
    },

    //DRAGON
    FireBreath(Name.FireBreath, SkillType.DRAGON, 6),
    FireBreath2(Name.FireBreath2, SkillType.DRAGON, 8, FireBreath),
    LightningBreath(Name.LightningBreath, SkillType.DRAGON, 7, FireBreath2) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    LightningBreath2(Name.LightningBreath2, SkillType.DRAGON, 11, LightningBreath, RefineSkill.RefineType.Range1) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Flametongue(Name.Flametongue, SkillType.DRAGON, 11, FireBreath2),
    Flametongue2(Name.Flametongue2, SkillType.DRAGON, 15, Flametongue, RefineSkill.RefineType.Range1),
    LightBreath(Name.LightBreath, SkillType.DRAGON, 9, FireBreath2),
    LightBreath2(Name.LightBreath2, SkillType.DRAGON, 13, LightBreath, RefineSkill.RefineType.Range1),
    DarkBreath(Name.DarkBreath, SkillType.DRAGON, 9, FireBreath),
    DarkBreath2(Name.DarkBreath2, SkillType.DRAGON, 13, DarkBreath, RefineSkill.RefineType.Range1),
    GreatFlame(Name.GreatFlame, SkillType.PENETRATE_DRAGON, 16, Flametongue) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = super.equip(equipAtk(armedHero, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiFollowupDef(battleUnit, enemy, 5)
    },
    Expiration(Name.Expiration, SkillType.PENETRATE_DRAGON, 16, Flametongue) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    WaterBreath(Name.WaterBreath, SkillType.PENETRATE_DRAGON, 10, FireBreath2) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 4), 4)
    },
    WaterBreath2(Name.WaterBreath2, SkillType.PENETRATE_DRAGON, 14, WaterBreath, RefineSkill.RefineType.Range1) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 4), 4)
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name

    override fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        //無効化回避能力は移動系に対する楯しかないので武器特効を上位にすればいいはず。竜特効無効とか出たらやだなあ
        val effectiveAgainstMoved = effectiveAgainstMoveType.fold(battleUnit, { bu, mv -> effectiveAgainst(mv, bu, enemy) })
        return effectiveAgainstWeaponType.fold(effectiveAgainstMoved, { bu, wp -> effectiveAgainst(wp, bu, enemy) })
    }

    //  override fun localeName(locale: Locale): String          = jp.localeName(locale)

    companion object {
        private val itemMap = mutableMapOf<String, Skill>()
        fun spreadItems(none: Boolean = false): List<Skill> = values().fold(if (none) arrayListOf<Skill>(Skill.NONE) else arrayListOf(), { list, e -> list.add(e);list })

        fun valueOfOrNONE(key: String?): Skill = if (key == null) Skill.NONE else try {
            if (itemMap.isEmpty()) {
                values().forEach { e -> itemMap[e.jp.jp] = e;itemMap[e.value] = e;itemMap[e.jp.us] = e;itemMap[e.jp.tw] = e; }
            }
            itemMap[key] ?: valueOf(key)
        } catch (e: Exception) {
            Skill.NONE
        }
    }
}