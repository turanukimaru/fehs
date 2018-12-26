package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*


/**
 * あーテキストが一意になるようにしないと保存できないか。これはうかつだったな。武器名で統一するか…？追加効果だから困るわけではないのだが。
 * 錬成武器
 */
enum class RefinedWeapon(override val jp: SkillName, val hp: Int, val atk: Int, val spd: Int, val def: Int, val res: Int, override val refinedWeaponType: RefinedWeapon.RefineType = RefinedWeapon.RefineType.NONE, override val preSkill: Skill = Skill.NONE, override val level: Int = 0, override val type: SkillType = SkillType.REFINERY, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf(), override val spType: SpType = SpType.LEGEND_W) : Weapon {
    //基本ルール
    Range1Atk(SkillName.Range1Atk, 5, 2, 0, 0, 0, RefineType.Range1),
    Range1Spd(SkillName.Range1Spd, 5, 0, 3, 0, 0, RefineType.Range1),
    Range1Def(SkillName.Range1Def, 5, 0, 0, 4, 0, RefineType.Range1),
    Range1Res(SkillName.Range1Res, 5, 0, 0, 0, 4, RefineType.Range1),

    Range2Atk(SkillName.Range2Atk, 2, 1, 0, 0, 0, RefineType.Range2),
    Range2Spd(SkillName.Range2Spd, 2, 0, 2, 0, 0, RefineType.Range2),
    Range2Def(SkillName.Range2Def, 2, 0, 0, 3, 0, RefineType.Range2),
    Range2Res(SkillName.Range2Res, 2, 0, 0, 0, 3, RefineType.Range2),

    WrathfulStaff(SkillName.WrathfulStaff, 0, 0, 0, 0, 0, RefineType.Staff) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = wrathfulStaff(battleUnit, enemy, 3)
    },
    DazzlingStaff(SkillName.DazzlingStaff, 0, 0, 0, 0, 0, RefineType.Staff) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = dazzling(battleUnit, enemy, 3)
    },

    //特効武器はバフ無効を持つ
    Armorsmasher(SkillName.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Armorsmasher2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit, enemy), enemy)
    },
    SlayingSpear(SkillName.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.SlayingSpear2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit, enemy), enemy)
    },
    SlayingHammer(SkillName.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.SlayingHammer2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.ARMORED, battleUnit, enemy), enemy)
    },
    Zanbato(SkillName.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Zanbato2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    Ridersbane(SkillName.Nullify, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Ridersbane2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    KeenRauorwolf2(SkillName.Nullify, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.KeenRauorwolf2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    KeenBlarwolf2(SkillName.Nullify, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.KeenBlarwolf2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },
    KeenGronnwolf2(SkillName.Nullify, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.KeenGronnwolf2) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(effectiveAgainst(MoveType.CAVALRY, battleUnit, enemy), enemy)
    },

    //武器自体を置き換えるもの
    SilverSword2(SkillName.SilverSword2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Sword.SilverSword2, 15, SkillType.SWORD),
    SilverLance2(SkillName.SilverLance2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Lance.SilverLance2, 15, SkillType.LANCE),
    SilverAxe2(SkillName.SilverAxe2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Axe.SilverAxe2, 15, SkillType.AXE),
    SilverBow2(SkillName.SilverBow2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Bow.SilverBow2, 14, SkillType.BOW),
    SilverDagger2(SkillName.SilverDagger2, 0, 4, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.SilverDagger2, 10, SkillType.DAGGER),
    Bolganone2(SkillName.Bolganone2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Rtome.Bolganone2, 14, SkillType.RTOME),
    Fenrir2(SkillName.Fenrir2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Rtome.Fenrir2, 14, SkillType.RTOME),
    Thoron2(SkillName.Thoron2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Btome.Thoron2, 14, SkillType.BTOME),
    Rexcalibur2(SkillName.Rexcalibur2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Gtome.Rexcalibur2, 14, SkillType.GTOME),
    Rauorowl2(SkillName.Rauorowl2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Rtome.Rauorowl2, 10, SkillType.RTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Blarowl2(SkillName.Blarowl2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Btome.Blarowl2, 10, SkillType.BTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Gronnowl2(SkillName.Gronnowl2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Gtome.Gronnowl2, 10, SkillType.GTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },

    SmokeDagger2(SkillName.SmokeDagger2, 0, 3, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.SmokeDagger2, 9, SkillType.DAGGER),
    RogueDagger2(SkillName.RogueDagger2, 0, 5, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.RogueDagger2, 6, SkillType.DAGGER),
    Flametongue2(SkillName.Flametongue2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.Flametongue2, 15, SkillType.PENETRATE_DRAGON),
    LightBreath2(SkillName.LightBreath2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.LightBreath2, 13, SkillType.PENETRATE_DRAGON),

    CarrotLance2(SkillName.CarrotLance2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Lance.CarrotLance2, 13, SkillType.LANCE) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotAxe2(SkillName.CarrotAxe2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Axe.CarrotAxe2, 13, SkillType.AXE) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    BlueEgg2(SkillName.BlueEgg2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Btome.BlueEgg2, 11, SkillType.BTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    GreenEgg2(SkillName.GreenEgg2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Gtome.GreenEgg2, 11, SkillType.GTOME) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    DancersFan2(SkillName.DancersFan2, 0, 2, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.DancersFan2, 10, SkillType.DAGGER),

    LightningBreath2(SkillName.LightningBreath2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.LightningBreath2, 11, SkillType.PENETRATE_DRAGON) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    DarkBreath2(SkillName.DarkBreath2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Breath.DarkBreath2, 13, SkillType.PENETRATE_DRAGON),
    BerkutsLance2(SkillName.BerkutsLance2, 0, 1, 0, 0, 0, RefineType.ReplaceWeapon, Lance.BerkutsLance2, 16, SkillType.LANCE) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = res(battleUnit, 4)
    },

    //神器
    Aura(SkillName.RefinedAura, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.Aura) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atk(spd(battleUnit, 5), 5) else battleUnit
    },
    Basilikos(SkillName.LifeAndDeath, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.Basilikos) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = lifeAndDeath(armedHero, 5)
    },
    SolitaryBlade(SkillName.LifeAndDeath, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.SolitaryBlade) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = lifeAndDeath(armedHero, 5)
    },
    BindingBlade(SkillName.BindingBlade, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Sword.BindingBlade, 16, SkillType.SWORD, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = def(res(battleUnit, 4), 4)
    },
    BindingBlade2(SkillName.QuickRiposte, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.BindingBlade) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 5)
    },
    Brynhildr(SkillName.RefinedBrynhildr, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Brynhildr) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiFollowupRangedDef(battleUnit, enemy)
    },
    BreathOfFog(SkillName.RefinedBreathOfFog, 3, 0, 0, 0, 0, RefineType.DependWeapon, Breath.BreathOfFog) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atk(def(battleUnit, 5), 5) else battleUnit
    },
    CamillasAxe(SkillName.CamillasAxe, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.CamillasAxe),
    CherchesAxe(SkillName.PanicPloy, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.CherchesAxe),

    Cymbeline(SkillName.BondFlyingAlly, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Cymbeline) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) res(atk(battleUnit, 5), 5) else battleUnit
    },
    DauntlessLance(SkillName.DauntlessLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.DauntlessLance, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)) {
        //        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spd(def(battleUnit, 4), 4)
    },
    DeathlyDagger(SkillName.DeathlyDagger, 0, 3, 0, 0, 0, RefineType.ReplaceWeapon, Dagger.DeathlyDagger, 11, SkillType.DAGGER) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 10)
    },
    DeathlyDagger2(SkillName.MagicSuppression, 0, 0, 0, 0, 0, RefineType.DependWeapon, Dagger.DeathlyDagger) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.isMagicWeapon()) {
                enemy.cannotCounter = true
            }
            return battleUnit
        }
    },
    Nidhogg(SkillName.Nidhogg, 0, 0, 0, 0, 0, RefineType.DependWeapon, Dagger.DeathlyDagger) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.isMagicWeapon() || enemy.armedHero.baseHero.weaponType == WeaponType.DRAGON) {
                enemy.cannotCounter = true
            }
            return battleUnit
        }
    },
    DraconicPoleax(SkillName.DraconicPoleax, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.DraconicPoleax),
    Eckesachs(SkillName.DauntlessLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Eckesachs) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, 6)
    },
    Excalibur(SkillName.RefinedAura, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.Excalibur) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atk(spd(battleUnit, 5), 5) else battleUnit
    },
    FalchionM(SkillName.AtkSpdDefRes2ToAllies, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.FalchionM) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, battleUnit, enemy)
    },
    FalchionA(SkillName.doubleAttack, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.FalchionA) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.hp == battleUnit.armedHero.maxHp) doubleAttack(battleUnit) else battleUnit
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, battleUnit, enemy)
    },
    FalchionC(SkillName.AtkSpdDefRes4, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.FalchionC) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, if (battleUnit.adjacentUnits > 0) allBonus(battleUnit, 4) else battleUnit, enemy)
    },
    FeliciasPlate(SkillName.FeliciasBlade, 0, 0, 0, 0, 0, RefineType.DependWeapon, Dagger.FeliciasPlate) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = feliciasBlade(battleUnit, enemy)
    },
    Fensalir(SkillName.SpdDefBond, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Fensalir) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) spd(def(battleUnit, 4), 4) else battleUnit
    },
    Fensalir2(SkillName.Fensalir, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Lance.Fensalir, 16, SkillType.LANCE) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = neutralizeBuffBonus(battleUnit, enemy)
    },
    Folkvangr(SkillName.TriangleAdept, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Folkvangr) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, 3)
    },
    Folkvangr2(SkillName.Folkvangr, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Sword.Folkvangr, 16, SkillType.SWORD) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtkDef(battleUnit, 7)
    },
    Forblaze(SkillName.DeathBlow, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Forblaze) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atk(battleUnit, 6)
    },
    FujinYumi(SkillName.Follow, 0, 0, 0, 0, 0, RefineType.DependWeapon, Bow.FujinYumi),
    Hauteclere(SkillName.SpecialDamage, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.Hauteclere) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    HinokasSpear(SkillName.RefinedHinokasSpear, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.HinokasSpear) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atk(spd(battleUnit, 4), 4) else battleUnit
    },
    Vidofnir2(SkillName.Vidofnir, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Vidofinir) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atk(spd(battleUnit, 4), 4) else battleUnit
    },
    Vidofnir(SkillName.Vidofnir, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon,Lance.Vidofinir, 16, SkillType.LANCE) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            val weapon = enemy.armedHero.baseHero.weaponType
            if (weapon == WeaponType.SWORD || weapon == WeaponType.LANCE || weapon == WeaponType.AXE || weapon == WeaponType.DRAGON) {
                battleUnit.defEffect += 7
                battleUnit.resEffect += 7
            }
            return battleUnit
        }
    },
    Mystletainn(SkillName.Fury, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Mystletainn) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = fury(armedHero, 3)
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, 3 * 2)
    },
    NamelessBlade(SkillName.SpecialDamage, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.NamelessBlade) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    Noatun(SkillName.WarpPowder, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.Noatun),
    OdinsGrimoire(SkillName.AtkSpdLink, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.OdinsGrimoire),
    Parthia(SkillName.Parthia, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Bow.Parthia, 14, SkillType.BOW, effectiveAgainstMoveType = arrayOf(MoveType.FLIER)) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (source.armedHero.isMagicWeapon()
                        && (!results.any { r -> r.side != battleUnit.side })) damage - damage * 3 / 10 else damage
    },
    Parthia2(SkillName.AntiRangedWeapon, 0, 0, 0, 0, 0, RefineType.DependWeapon, Bow.Parthia) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atk(battleUnit, if (enemy.armedHero.baseHero.weaponType.range == 2) 6 else 0)
    },
    Ragnarok(SkillName.BrazenAtkSpd, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.Ragnarok) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtk(brazenSpd(battleUnit, 7), 7)
    },
    ReesesTome(SkillName.RefinedReesesTome, 0, 0, 0, 0, 0, RefineType.DependWeapon, Rtome.ReesesTome),
    RegalBlade(SkillName.RefinedRegalBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.RegalBlade) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) allBonus(battleUnit, 3) else battleUnit
    },
    Rhomphaia(SkillName.FlashingBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Rhomphaia) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
    },
    Sieglinde(SkillName.RefinedSieglinde, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Sieglinde),//周りのバフ分捨てアップはこの形式だと書けないよなあ
    Siegmund(SkillName.Pursuit, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.Siegmund) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.hp >= battleUnit.armedHero.maxHp * 9 / 10) {
                battleUnit.followupable = true
            }
            return battleUnit
        }
    },
    SolKatti(SkillName.BrashAssault, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.SolKatti) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brashAssault(battleUnit, enemy, 75)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    WindsBrand(SkillName.Owl, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.WindsBrand) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    WingSword(SkillName.FlashingBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.WingSword) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 1)
    },
    Yato(SkillName.Yato, 0, 0, 0, 0, 0, RefineType.ReplaceWeapon, Sword.Yato, 16, SkillType.SWORD) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Yato2(SkillName.RefinedYato, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.Yato),
    AxeOfVirility(SkillName.Fury, 3, 0, 0, 0, 0, RefineType.DependWeapon, Axe.AxeOfVirility) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = fury(armedHero, 3)
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, 3 * 2)
    },
    FlorinasLance(SkillName.FlorinasLance, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.FlorinasLance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = closeAllBonus(battleUnit, enemy, 4)
    },
    ShannasLance(SkillName.SpecialDamage, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.ShannasLance) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    GoldenDagger(SkillName.GoldenDagger, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.GoldenDagger) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.specialCount >= battleUnit.armedHero.specialCoolDownTime) {
                battleUnit.counterAllRange = true
                allBonus(battleUnit, 4)
            }
            return battleUnit
        }
    },
    WhitewingBlade(SkillName.TriangleAttack, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.WhitewingBlade) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = triangleEffect(battleUnit,enemy)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = triangleAttack(battleUnit)
    },
    WhitewingLance(SkillName.TriangleAttack, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.WhitewingLance) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = triangleEffect(battleUnit,enemy)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = triangleAttack(battleUnit)
    },
    WhitewingSpear(SkillName.TriangleAttack, 3, 0, 0, 0, 0, RefineType.DependWeapon, Lance.WhitewingSpear) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = triangleEffect(battleUnit,enemy)
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = triangleAttack(battleUnit)
    },
    GladiatorsBlade(SkillName.GladiatorsBlade, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.GladiatorsBlade) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atkSpd(battleUnit, 4) else battleUnit
    },
    ScarletSword(SkillName.ScarletSword, 3, 0, 0, 0, 0, RefineType.DependWeapon, Sword.ScarletSword),//ターン開始時効果必要だな…
    TacticalBolt(SkillName.TacticalBolt, 0, 0, 0, 0, 0, RefineType.DependWeapon, Btome.TacticalBolt),
    TacticalGale(SkillName.TacticalGale, 0, 0, 0, 0, 0, RefineType.DependWeapon, Gtome.TacticalGale),

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