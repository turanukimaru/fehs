package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*


/**
 * あーテキストが一意になるようにしないと保存できないか。これはうかつだったな。武器名で統一するか…？追加効果だから困るわけではないのだが。
 * 錬成武器
 */
enum class RefinedWeapon(override val jp: Name, val hp: Int, val atk: Int, val spd: Int, val def: Int, val res: Int, override val refinedWeaponType: RefinedWeapon.RefineType = RefinedWeapon.RefineType.NONE, override val preSkill: Skill = Skill.NONE, override val level: Int = 0, override val type: SkillType = SkillType.REFINERY, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf(), override val spType: SpType = SpType.LEGEND_W) : Weapon {
    //基本ルール
    Range1Atk(Name.Range1Atk, 5, 2, 0, 0, 0, RefineType.Range1),
    Range1Spd(Name.Range1Spd, 5, 0, 3, 0, 0, RefineType.Range1),
    Range1Def(Name.Range1Def, 5, 0, 0, 4, 0, RefineType.Range1),
    Range1Res(Name.Range1Res, 5, 0, 0, 0, 4, RefineType.Range1),

    Range2Atk(Name.Range2Atk, 2, 1, 0, 0, 0, RefineType.Range2),
    Range2Spd(Name.Range2Spd, 2, 0, 2, 0, 0, RefineType.Range2),
    Range2Def(Name.Range2Def, 2, 0, 0, 3, 0, RefineType.Range2),
    Range2Res(Name.Range2Res, 2, 0, 0, 0, 3, RefineType.Range2),

    WrathfulStaff(Name.WrathfulStaff, 0, 0, 0, 0, 0, RefineType.Staff) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = wrathfulStaff(battleUnit, enemy, 3)
    },
    DazzlingStaff(Name.DazzlingStaff, 0, 0, 0, 0, 0, RefineType.Staff) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = dazzling(battleUnit, enemy, 3)
    },

    //特効武器はバフ無効を持つ
    Armorsmasher(Name.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Armorsmasher2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit, enemy), enemy)
    },
    SlayingSpear(Name.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.SlayingSpear2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit, enemy), enemy)
    },
    SlayingHammer(Name.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.SlayingHammer2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit, enemy), enemy)
    },
    Zanbato(Name.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Zanbato2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    Ridersbane(Name.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Ridersbane2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    KeenRauorwolf2(Name.Nullify, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.KeenRauorwolf2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    KeenBlarwolf2(Name.Nullify, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.KeenBlarwolf2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    KeenGronnwolf2(Name.Nullify, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.KeenGronnwolf2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },

    //武器自体を置き換えるもの
    SilverSword2(Name.SilverSword2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Sword.SilverSword2, 15, SkillType.SWORD),
    SilverLance2(Name.SilverLance2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Lance.SilverLance2, 15, SkillType.LANCE),
    SilverAxe2(Name.SilverAxe2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Axe.SilverAxe2, 15, SkillType.AXE),
    SilverBow2(Name.SilverBow2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Bow.SilverBow2, 14, SkillType.BOW),
    SilverDagger2(Name.SilverDagger2, 0, 4, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.SilverDagger2, 10, SkillType.DAGGER),
    Bolganone2(Name.Bolganone2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Rtome.Bolganone2, 14, SkillType.RTOME),
    Fenrir2(Name.Fenrir2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Rtome.Fenrir2, 14, SkillType.RTOME),
    Thoron2(Name.Thoron2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Btome.Thoron2, 14, SkillType.BTOME),
    Rexcalibur2(Name.Rexcalibur2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Gtome.Rexcalibur2, 14, SkillType.GTOME),
    Rauorowl2(Name.Rauorowl2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Rtome.Rauorowl2, 10, SkillType.RTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Blarowl2(Name.Blarowl2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Btome.Blarowl2, 10, SkillType.BTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Gronnowl2(Name.Gronnowl2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Gtome.Gronnowl2, 10, SkillType.GTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },

    SmokeDagger2(Name.SmokeDagger2, 0, 3, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.SmokeDagger2, 9, SkillType.DAGGER),
    RogueDagger2(Name.RogueDagger2, 0, 5, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.RogueDagger2, 6, SkillType.DAGGER),
    Flametongue2(Name.Flametongue2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.Flametongue2, 15, SkillType.PENETRATE_DRAGON),
    LightBreath2(Name.LightBreath2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.LightBreath2, 13, SkillType.PENETRATE_DRAGON),

    CarrotLance2(Name.CarrotLance2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Lance.CarrotLance2, 13, SkillType.LANCE) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotAxe2(Name.CarrotAxe2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Axe.CarrotAxe2, 13, SkillType.AXE) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    BlueEgg2(Name.BlueEgg2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Btome.BlueEgg2, 11, SkillType.BTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    GreenEgg2(Name.GreenEgg2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Gtome.GreenEgg2, 11, SkillType.GTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    DancersFan2(Name.DancersFan2, 0, 2, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.DancersFan2, 10, SkillType.DAGGER),

    LightningBreath2(Name.LightningBreath2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.LightningBreath2, 11, SkillType.PENETRATE_DRAGON) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    DarkBreath2(Name.DarkBreath2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.DarkBreath2, 13, SkillType.PENETRATE_DRAGON),
    BerkutsLance2(Name.BerkutsLance2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Lance.BerkutsLance2, 16, SkillType.LANCE) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },

    //神器
    Aura(Name.RefinedAura, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.Aura) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 5), 5) else battleUnit
    },
    Basilikos(Name.LifeAndDeath, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.Basilikos) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = lifeAndDeath(armedHero, 5)
    },
    SolitaryBlade(Name.LifeAndDeath, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.SolitaryBlade) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = lifeAndDeath(armedHero, 5)
    },
    BindingBlade(Name.BindingBlade, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Sword.BindingBlade, 16, SkillType.SWORD, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 4), 4)
    },
    BindingBlade2(Name.QuickRiposte, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.BindingBlade) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 5)
    },
    Brynhildr(Name.RefinedBrynhildr, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Brynhildr) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiFollowupRangedDef(battleUnit, enemy)
    },
    BreathOfFog(Name.RefinedBreathOfFog, 3, 0, 0, 0, 0, RefineType.DependWeapon, Breath.BreathOfFog) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowDef(battleUnit, 5), 5) else battleUnit
    },
    CamillasAxe(Name.CamillasAxe, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.CamillasAxe),
    CherchesAxe(Name.PanicPloy, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.CherchesAxe),

    Cymbeline(Name.BondFlyingAlly, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Cymbeline) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowRes(blowAtk(battleUnit, 5), 5) else battleUnit
    },
    DauntlessLance(Name.DauntlessLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.DauntlessLance, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)) {
        //        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(blowDef(battleUnit, 4), 4)
    },
    DeathlyDagger(Name.DeathlyDagger, 0, 3, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.DeathlyDagger, 11, SkillType.DAGGER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 10)
    },
    DeathlyDagger2(Name.MagicSuppression, 0, 0, 0, 0, 0, RefineType.DependWeapon, Dagger.DeathlyDagger) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.isMagicWeapon()) {
                enemy.cannotCounter = true
            }
            return battleUnit
        }
    },
    Nidhogg(Name.Nidhogg, 0, 0, 0, 0, 0, RefineType.DependWeapon, Dagger.DeathlyDagger) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.isMagicWeapon() || enemy.armedHero.baseHero.weaponType == WeaponType.DRAGON) {
                enemy.cannotCounter = true
            }
            return battleUnit
        }
    },
    DraconicPoleax(Name.DraconicPoleax, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.DraconicPoleax),
    Eckesachs(Name.DauntlessLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Eckesachs) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    Excalibur(Name.RefinedAura, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.Excalibur) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 5), 5) else battleUnit
    },
    FalchionM(Name.AtkSpdDefRes2ToAllies, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.FalchionM) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, battleUnit, enemy)
    },
    FalchionA(Name.doubleAttack, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.FalchionA) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.hp == battleUnit.armedHero.maxHp) doubleAttack(battleUnit) else battleUnit
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, battleUnit, enemy)
    },
    FalchionC(Name.AtkSpdDefRes4, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.FalchionC) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, if (battleUnit.adjacentUnits > 0) allBonus(battleUnit, 4) else battleUnit, enemy)
    },
    FeliciasPlate(Name.FeliciasBlade, 0, 0, 0, 0, 0, RefineType.DependWeapon, Dagger.FeliciasPlate) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = feliciasBlade(battleUnit, enemy)
    },
    Fensalir(Name.SpdDefBond, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Fensalir) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowSpd(blowDef(battleUnit, 4), 4) else battleUnit
    },
    Fensalir2(Name.Fensalir, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Lance.Fensalir, 16, SkillType.LANCE) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(battleUnit, enemy)
    },
    Folkvangr(Name.TriangleAdept, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Folkvangr) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    Forblaze(Name.DeathBlow, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Forblaze) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, 6)
    },
    FujinYumi(Name.Follow, 0, 0, 0, 0, 0, RefineType.DependWeapon, Bow.FujinYumi),
    Hauteclere(Name.SpecialDamage, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.Hauteclere) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    HinokasSpear(Name.RefinedHinokasSpear, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.HinokasSpear) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 4), 4) else battleUnit
    },
    Vidofnir2(Name.Vidofnir, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Vidofinir) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, 4), 4) else battleUnit
    },
    Vidofnir(Name.Vidofnir, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon,Lance.Vidofinir, 16, SkillType.LANCE) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            val weapon = enemy.armedHero.baseHero.weaponType
            if (weapon == WeaponType.SWORD || weapon == WeaponType.LANCE || weapon == WeaponType.AXE || weapon == WeaponType.DRAGON) {
                battleUnit.defEffect += 7
                battleUnit.resEffect += 7
            }
            return battleUnit
        }
    },
    Mystletainn(Name.Fury, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Mystletainn) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = fury(armedHero, 3)
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, 3 * 2)
    },
    NamelessBlade(Name.SpecialDamage, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.NamelessBlade) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Noatun(Name.WarpPowder, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.Noatun),
    OdinsGrimoire(Name.AtkSpdLink, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.OdinsGrimoire),
    Parthia(Name.Parthia, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Bow.Parthia, 14, SkillType.BOW, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (source.armedHero.isMagicWeapon()
                        && (!results.any { r -> r.side != battleUnit.side })) damage - damage * 3 / 10 else damage
    },
    Parthia2(Name.AntiRangedWeapon, 0, 0, 0, 0, 0, RefineType.DependWeapon, Bow.Parthia) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, if (enemy.armedHero.baseHero.weaponType.range == 2) 6 else 0)
    },
    Ragnarok(Name.BrazenAtkSpd, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Ragnarok) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtk(brazenSpd(battleUnit, 7), 7)
    },
    ReesesTome(Name.RefinedReesesTome, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.ReesesTome),
    RegalBlade(Name.RefinedRegalBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.RegalBlade) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) allBonus(battleUnit, 3) else battleUnit
    },
    Rhomphaia(Name.FlashingBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Rhomphaia) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
    },
    Sieglinde(Name.RefinedSieglinde, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Sieglinde),//周りのバフ分捨てアップはこの形式だと書けないよなあ
    Siegmund(Name.Pursuit, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Siegmund) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.hp >= battleUnit.armedHero.maxHp * 9 / 10) {
                battleUnit.followupable = true
            }
            return battleUnit
        }
    },
    SolKatti(Name.BrashAssault, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.SolKatti) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brashAssault(battleUnit, enemy, 75)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    WindsBrand(Name.Owl, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.WindsBrand) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    WingSword(Name.FlashingBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.WingSword) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
    },
    Yato(Name.Yato, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Sword.Yato, 16, SkillType.SWORD) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Yato2(Name.RefinedYato, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Yato),
    AxeOfVirility(Name.Fury, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.AxeOfVirility) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = fury(armedHero, 3)
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, 3 * 2)
    },
    FlorinasLance(Name.FlorinasLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.FlorinasLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(closeDef(battleUnit, enemy, 4), enemy, 4)
    },
    ShannasLance(Name.ShannasLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.ShannasLance) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    GoldenDagger(Name.GoldenDagger, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.GoldenDagger) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.specialCount >= battleUnit.armedHero.specialCoolDownTime) {
                battleUnit.counterAllRange = true
                allBonus(battleUnit, 4)
            }
            return battleUnit
        }
    },

    ;

    override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero {
        //println("$jp hp:$hp")
        equipHp(armedHero, hp)
        equipAtk(armedHero, atk)
        equipSpd(armedHero, spd)
        equipDef(armedHero, def)
        equipRes(armedHero, res)
        return super.equip(armedHero, lv)
    }

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。ただしこのスキルに限りスキル名はそのままは使わない
     */
    override val value get() = name

    companion object {
        private val itemMap = mutableMapOf<String, Skill>()
        fun spreadItems(weapon: Skill, range: RefineType = (weapon as? Weapon)?.refinedWeaponType
                ?: RefineType.NOT_WEAPON): List<Skill> = values().fold(mutableListOf<Skill>(Skill.NONE)) { list, e -> if (e.refinedWeaponType == range || (e.refinedWeaponType == RefineType.DependWeapon && (e.preSkill == weapon || e.preSkill == weapon.preSkill))) list.add(e);list }

        fun valueOfWeapon(weapon: Skill) = values().find { e -> e.refinedWeaponType == RefineType.ReplaceWeapon && e.preSkill == weapon }
        fun valueOfOrNONE(key: String?): Skill = if (key == null || key.isEmpty()) Skill.NONE else try {
            if (itemMap.isEmpty()) {
                values().forEach { e -> itemMap[e.value] = e;itemMap[e.jp.jp] = e; itemMap[e.jp.us] = e; itemMap[e.jp.tw] = e }
            }
            itemMap[key] ?: valueOf(key)
        } catch (e: Exception) {
            Skill.NONE
        }
    }

    /**
     * 錬成タイプ。種類によって基本的なルールが異なる。でもこれ使い道の違うタイプが混ざってるんだよな分けたほうがいいかなあ
     */
    enum class RefineType {
        NONE,
        Range1,
        Range2,
        Staff,
        DependWeapon,
        ReplaceWeapon,
        NOT_WEAPON,
    }
}