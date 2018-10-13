package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。武器
 */
enum class Dagger(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.LEGEND_W,override  val refinedSkillType: RefinedSkill.RefineType = RefinedSkill.RefineType.NONE, override val effectiveAgainstMoveType: Array<MoveType> = arrayOf(), override val effectiveAgainstWeaponType: Array<WeaponType> = arrayOf()) : Weapon {
    IronDagger(Name.IronDagger, SkillType.DAGGER, 3, Skill.NONE, SpType.IRON),
    SteelDagger(Name.SteelDagger, SkillType.DAGGER, 5, IronDagger, SpType.STEEL),
    SilverDagger(Name.SilverDagger, SkillType.DAGGER, 7, SteelDagger, SpType.SILVER),
    SilverDagger2(Name.SilverDagger2, SkillType.DAGGER, 10, SilverDagger, SpType.PLUS, RefinedSkill.RefineType.Range2),
    RogueDagger(Name.RogueDagger, SkillType.DAGGER, 4, SteelDagger, SpType.SILVER),
    RogueDagger2(Name.RogueDagger2, SkillType.DAGGER, 7, RogueDagger, SpType.PLUS, RefinedSkill.RefineType.Range2),
    SmokeDagger(Name.SmokeDagger, SkillType.DAGGER, 6, SteelDagger, SpType.SILVER),
    SmokeDagger2(Name.SmokeDagger2, SkillType.DAGGER, 9, SmokeDagger, SpType.PLUS, RefinedSkill.RefineType.Range2),
    PoisonDagger(Name.PoisonDagger, SkillType.DAGGER, 2, SteelDagger, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.INFANTRY)),
    PoisonDagger2(Name.PoisonDagger2, SkillType.DAGGER, 5, PoisonDagger, SpType.PLUS, effectiveAgainstMoveType = arrayOf(MoveType.INFANTRY)),
    Seashell(Name.Seashell, SkillType.DAGGER, 7, SteelDagger, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    Seashell2(Name.Seashell2, SkillType.DAGGER, 10, Seashell, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    DancersFan(Name.DancersFan, SkillType.DAGGER, 7, SteelDagger, SpType.SILVER),
    DancersFan2(Name.DancersFan2, SkillType.DAGGER, 10, DancersFan, SpType.PLUS, RefinedSkill.RefineType.Range2),
    DeathlyDagger(Name.DeathlyDagger, SkillType.DAGGER, 11, SilverDagger, SpType.LEGEND_W, RefinedSkill.RefineType.Range2) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, 7)
    },
    KittyPaddle(Name.KittyPaddle, SkillType.DAGGER, 5, SteelDagger, SpType.SILVER, effectiveAgainstWeaponType = arrayOf(WeaponType.RTOME, WeaponType.BTOME, WeaponType.GTOME)),
    KittyPaddle2(Name.KittyPaddle2, SkillType.DAGGER, 8, KittyPaddle, SpType.PLUS, effectiveAgainstWeaponType = arrayOf(WeaponType.RTOME, WeaponType.BTOME, WeaponType.GTOME)),
    KagamiMochi(Name.KagamiMochi, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    KagamiMochi2(Name.KagamiMochi2, SkillType.DAGGER, 12, KagamiMochi, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    FeliciasPlate(Name.FeliciasPlate, SkillType.PENETRATE_DAGGER, 14, SilverDagger2, SpType.LEGEND_W, RefinedSkill.RefineType.Range2),
    Peshkatz(Name.Peshkatz, SkillType.DAGGER, 14, SilverDagger),
    LethalCarrot(Name.LethalCarrot, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    LethalCarrot2(Name.LethalCarrot2, SkillType.DAGGER, 12, LethalCarrot, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage + 10
    },
    BarbShuriken(Name.BarbShuriken, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    BarbShuriken2(Name.BarbShuriken2, SkillType.DAGGER, 12, BarbShuriken, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    TheCleaner(Name.TheCleaner, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER) {
        override fun stateFlat(battleUnit: BattleUnit, enemy: BattleUnit): Int = enemy.totalBuff
    },
    TheCleaner2(Name.TheCleaner2, SkillType.DAGGER, 12, TheCleaner, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun stateFlat(battleUnit: BattleUnit, enemy: BattleUnit): Int = enemy.totalBuff
    },
    Starfish(Name.Starfish, SkillType.DAGGER, 10, SteelDagger, SpType.SILVER) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    Starfish2(Name.Starfish2, SkillType.DAGGER, 12, Starfish, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 3)
    },
    CloudMaiougi(Name.CloudMaiougi, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    CloudMaiougi2(Name.CloudMaiougi2, SkillType.DAGGER, 12, CloudMaiougi, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstWeaponType = arrayOf(WeaponType.DRAGON)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    SkyMaiougi(Name.SkyMaiougi, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    SkyMaiougi2(Name.SkyMaiougi2, SkillType.DAGGER, 12, SkyMaiougi, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.ARMORED)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    DuskUchiwa(Name.SkyMaiougi, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    DuskUchiwa2(Name.SkyMaiougi2, SkillType.DAGGER, 12, DuskUchiwa, SpType.PLUS, RefinedSkill.RefineType.Range2, effectiveAgainstMoveType = arrayOf(MoveType.CAVALRY)) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = hardyBearing(battleUnit, enemy, 3)
    },
    HoarfrostKnife(Name.HoarfrostKnife, SkillType.DAGGER, 14, SilverDagger) {
        override fun localEquip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
        //相手が反撃可能かを判定した後判定
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.effectiveRange == 1) {
                battleUnit.defEffect += 20
                if (enemy.counterAllRange) {
                    battleUnit.followupable = true
                }
            }
            return battleUnit
        }
    },
    BottledJuice(Name.BottledJuice, SkillType.DAGGER, 8, SteelDagger, SpType.SILVER) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiAccelerateCooldown(battleUnit, enemy, 11)
    },
    BottledJuice2(Name.BottledJuice2, SkillType.DAGGER, 12, DuskUchiwa, SpType.PLUS, RefinedSkill.RefineType.Range2) {
        override fun localFightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiAccelerateCooldown(battleUnit, enemy, 11)
    },
   ;
    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name
}