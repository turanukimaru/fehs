package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Rtome(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W, override val refinedWeaponType: RefinedWeapon.RefineType = RefinedWeapon.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    Flux(Name.Flux, SkillType.RTOME, 4, Skill.NONE, SpType.IRON),
    Fire(Name.Fire, SkillType.RTOME, 4, Skill.NONE, SpType.IRON),
    Ruin(Name.Ruin, SkillType.RTOME, 6, Flux, SpType.STEEL),
    Elfire(Name.Elfire, SkillType.RTOME, 6, Fire, SpType.STEEL),
    Rauorwolf(Name.Rauorwolf, SkillType.RTOME, 10, Elfire, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Rauorwolf2(Name.Rauorwolf2, SkillType.RTOME, 10, Rauorwolf, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    KeenRauorwolf2(Name.KeenRauorwolf2, SkillType.RTOME, 12, Rauorwolf2, SpType.PLUS, RefinedWeapon.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)),
    Rauorraven(Name.Rauorraven, SkillType.RTOME, 7, Elfire, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Rauorraven2(Name.Rauorraven2, SkillType.RTOME, 11, Rauorraven, SpType.PLUS) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Rauorblade(Name.Rauorblade, SkillType.RTOME, 9, Elfire, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Rauorblade2(Name.Rauorblade2, SkillType.RTOME, 13, Rauorblade, SpType.PLUS) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Rauorowl(Name.Rauorowl, SkillType.RTOME, 6, Elfire, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Rauorowl2(Name.Rauorowl2, SkillType.RTOME, 10, Rauorowl, SpType.PLUS, RefinedWeapon.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    Fenrir(Name.Fenrir, SkillType.RTOME, 9, Elfire, SpType.SILVER),
    Fenrir2(Name.Fenrir2, SkillType.RTOME, 13, Fenrir, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    Bolganone(Name.Bolganone, SkillType.RTOME, 9, Elfire, SpType.SILVER),
    Bolganone2(Name.Bolganone2, SkillType.RTOME, 13, Bolganone, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    TomatoTome(Name.TomatoTome, SkillType.RTOME, 8, Elfire, SpType.SILVER),
    TomatoTome2(Name.TomatoTome2, SkillType.RTOME, 12, TomatoTome, SpType.PLUS, RefinedWeapon.RefineType.Range2),
    Brynhildr(Name.Brynhildr, SkillType.RTOME, 14, Bolganone, SpType.LEGEND_W, RefinedWeapon.RefineType.Range2),
    Cymbeline(Name.Cymbeline, SkillType.RTOME, 14, Bolganone, SpType.LEGEND_W, RefinedWeapon.RefineType.Range2),
    Ragnarok(Name.Ragnarok, SkillType.RTOME, 14, Bolganone) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAtkSpdBonus(battleUnit, 5, 5)
    },
    Valflame(Name.Valflame, SkillType.RTOME, 14, Bolganone),
    Grimoire(Name.Grimoire, SkillType.RTOME, 14, Bolganone),
    Candelabra(Name.Candelabra, SkillType.RTOME, 8, Elfire, SpType.SILVER) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Candelabra2(Name.Candelabra2, SkillType.RTOME, 12, Candelabra, SpType.PLUS, RefinedWeapon.RefineType.Range2) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, 2)
    },
    Gleipnir(Name.Gleipnir, SkillType.RTOME, 14, Bolganone) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, enemy, 3)
    },
    Naglfar(Name.Naglfar, SkillType.RTOME, 14, Bolganone) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Forblaze(Name.Forblaze, SkillType.RTOME, 14, Bolganone2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range2),
    Loptous(Name.Loptous, SkillType.BTOME, 14, Bolganone) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.weapon.effectiveAgainstWeaponType.contains(WeaponType.DRAGON)) {
                enemy.effectiveAgainst = EffectiveAgainst.DRAGON
            } else {
                enemy.atkEffect -= 6
            }
            return battleUnit
        }
    },
    GrimasTruth(Name.GrimasTruth, SkillType.GTOME, 14, Bolganone) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, 3)
    },
    MuspellFireposy(Name.MuspellFireposy, SkillType.RTOME, 14, Bolganone) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, battleUnit.adjacentUnits * 2), battleUnit.adjacentUnits * 2)
    },
    FruitOfIdunn(Name.FruitOfIdunn, SkillType.RTOME, 14, Bolganone) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, 3)
    },
    ReesesTome(Name.ReesesTome, SkillType.RTOME, 14, Bolganone2, SpType.LEGEND_W, RefinedWeapon.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = allBonus(battleUnit, battleUnit.adjacentUnits * 2)
    },
    DawnSuzu(Name.DawnSuzu, SkillType.RTOME, 14, Bolganone2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY, MoveType.ARMORED)) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    AversasNight(Name.AversasNight, SkillType.RTOME, 14, Bolganone2) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
    },
    BookOfDreams(Name.BookOfDreams, SkillType.RTOME, 14, Bolganone2) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, 3)
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = adjacentDebuff(battleUnit, enemy, 4)
    },
    Imhullu(Name.Imhullu, SkillType.BTOME, 14, Bolganone) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, 3)
    },
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}