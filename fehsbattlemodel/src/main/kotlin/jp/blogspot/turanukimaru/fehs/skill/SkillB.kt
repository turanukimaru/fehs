package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキル。B
 */
enum class SkillB(override val jp: Name, override val type: SkillType = SkillType.B, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val maxLevel: Int = 3, override val spType: SpType = SpType.BASE50) : Skill {
    SwordBreaker(Name.SwordBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.SWORD, lv)
    },
    LanceBreaker(Name.LanceBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.LANCE, lv)
    },
    AxeBreaker(Name.AxeBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.AXE, lv)
    },
    //弓殺しは無色限定
    BowBreaker(Name.BowBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.BOW, lv, 0)
    },
    DaggerBreaker(Name.DaggerBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.DAGGER, lv, 0)
    },
    RTomeBreaker(Name.RTomeBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.RTOME, lv)
    },
    GTomeBreaker(Name.GTomeBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.GTOME, lv)
    },
    BTomeBreaker(Name.BTomeBreaker) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, enemy, WeaponType.BTOME, lv)
    },
    WaryFighter(Name.WaryFighter, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = eachNofollowupable(battleUnit, enemy, lv)
    },
    //あれ？LVの考慮忘れてるわ
    BoldFighter(Name.BoldFighter, spType = SpType.BASE60) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = accelerateAttackCooldown(followupable(battleUnit, 10), 11)
    },
    VengefulFighter(Name.VengefulFighter, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = accelerateAttackCooldown(followupable(battleUnit, 5), 6)
    },
    SpecialFighter(Name.SpecialFighter, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = accelerateAttackCooldown(antiAccelerateCooldown(battleUnit, enemy, lv * 2), lv * 2)
    },
    Desperation(Name.Desperation) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, lv)
    },
    QuickRiposte(Name.QuickRiposte, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, lv)
    },
    BrashAssault(Name.BrashAssault) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brashAssault(battleUnit, enemy, (lv * 10 + 20))
    },
    Windsweep(Name.Windsweep, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = windsweep(battleUnit, enemy, lv)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = noFollowupAttack(fightPlan)
    },
    Watersweep(Name.Watersweep, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = watersweep(battleUnit, enemy, lv)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = noFollowupAttack(fightPlan)
    },
    DullClose(Name.DullClose, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiMeleeWeaponBuffBonus(battleUnit, enemy, lv)
    },
    DullRanged(Name.DullRanged, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiRangedWeaponBuffBonus(battleUnit, enemy, lv)
    },
    WrathfulStaff(Name.WrathfulStaff, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = wrathfulStaff(battleUnit, enemy, lv)
    },
    DazzlingStaff(Name.DazzlingStaff, spType = SpType.BASE60) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = dazzling(battleUnit, enemy, lv)

    },
    Vantage(Name.Vantage) {
        override fun counterPlan(fightPlan: FightPlan, lv: Int): FightPlan = vantage(fightPlan, lv)
    },

    Guard(Name.Guard) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiAccelerateCooldown(battleUnit, enemy, lv)
    },
    ShieldPulse(Name.ShieldPulse, spType = SpType.BASE60) {
        override fun specialPreventTriggered(battleUnit: BattleUnit, damage: Int): Int = damage - 5
    },
    CancelAffinity(Name.CancelAffinity) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            //高いほうが適用されるのでとりあえず自分だけ消す
            battleUnit.colorAdvantageLevel = 0
            val colorAd = battleUnit.colorAdvantage(enemy)
            //2と3は負けてるときだけ発動。両者が持ってるときは両社とも激化倍率がなくなるので問題ない
            when (lv) {
                1 -> {
                    battleUnit.antiColorAdvantage = 0;enemy.antiColorAdvantage = 0
                }
                2 -> if (colorAd == -1) {
                    battleUnit.antiColorAdvantage = 0;enemy.antiColorAdvantage = 0
                }
                3 -> if (colorAd == -1) {
                    battleUnit.antiColorAdvantage = -1;enemy.antiColorAdvantage = -1
                }
            }
            return battleUnit
        }
    },
    Wrath(Name.Wrath, spType = SpType.BASE60) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = wrath(battleUnit, damage, level * 25)
    },
    PoisonStrike(Name.PoisonStrike, spType = SpType.BASE60) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, lv * 3 + 1)
    },
    SacaesBlessing(Name.SacaesBlessing, spType = SpType.LEGEND_S) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = sacasBlessing(battleUnit, enemy, lv)

    },
    BeorcsBlessing(Name.BeorcsBlessing, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = beorcsBlessing(battleUnit, enemy, lv)
    },
    CrusadersWard(Name.CrusadersWard, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int =
                if (source.effectiveRange == 2 && results.isNotEmpty() && results.last().side != battleUnit.side) damage - damage * 8 / 10 else damage
    },
    FollowUpRing(Name.FollowUpRing, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 5)
    },
    Bushido(Name.Bushido, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = wrath(battleUnit, damage, 100)
    },
    SolarBrace(Name.SolarBrace, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
            battleUnit.heal(damage * 3 / 10)
            return damage
        }
    },
    LunarBrace(Name.LunarBrace, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipBlade(armedHero, lv)
        override val stateDamageEnemy: (BattleUnit) -> Int get() = { it.def / 2 }
    },
    DoubleLion(Name.DoubleLion, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpDoubleAttack(battleUnit, 1)
    },
    BindingShield(Name.BindingShield, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (enemy.armedHero.baseHero.weaponType == WeaponType.DRAGON) {
                enemy.cannotCounter = true
                enemy.antiFollowup = true
            }
            return battleUnit
        }
    },
    NullFollowUp(Name.NullFollowUp, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = nullFollowUp(battleUnit, enemy, lv)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = nullFollowUp(battleUnit, enemy, lv)
    },
    SpecialSpiral(Name.SpecialSpiral, spType = SpType.BASE60),//戦闘後効果だからまだ要らないといえば要らないが…
    EscapeRoute(Name.EscapeRoute, spType = SpType.BASE60),
    RecoverRing(Name.RecoverRing, maxLevel = 0, spType = SpType.SHIELD),
    Renewal(Name.Renewal, spType = SpType.BASE60),
    LiveToServe(Name.LiveToServe, spType = SpType.BASE40),
    WingsOfMercy(Name.WingsOfMercy, spType = SpType.BASE60),
    Pass(Name.Pass),
    Obstruct(Name.Obstruct),
    SealAtk(Name.SealAtk),
    SealSpd(Name.SealSpd),
    SealDef(Name.SealDef),
    SealRes(Name.SealRes),
    SealAtkSpd(Name.SealAtkSpd, maxLevel = 2, spType = SpType.BASE100),
    SealAtkDef(Name.SealAtkDef, maxLevel = 2, spType = SpType.BASE100),
    SealDefRes(Name.SealDefRes, maxLevel = 2, spType = SpType.BASE100),
    FlierFormation(Name.FlierFormation),
    BlazeDance(Name.BlazeDance),
    GaleDance(Name.GaleDance),
    EarthDance(Name.EarthDance),
    TorrentDance(Name.TorrentDance),
    GeyserDance(Name.GeyserDance, maxLevel = 2, spType = SpType.BASE120),
    FirestormDance(Name.FirestormDance, maxLevel = 2, spType = SpType.BASE120),
    RockslideDance(Name.RockslideDance, maxLevel = 2, spType = SpType.BASE120),
    FirefloodDance(Name.FirefloodDance, maxLevel = 2, spType = SpType.BASE120),
    DelugeDance(Name.DelugeDance, maxLevel = 2, spType = SpType.BASE120),
    KnockBack(Name.KnockBack, maxLevel = 0, spType = SpType.ASSIST),
    DragBack(Name.DragBack, maxLevel = 0, spType = SpType.ASSIST),
    Lunge(Name.Lunge, maxLevel = 0, spType = SpType.ASSIST),
    HitAndRun(Name.HitAndRun, maxLevel = 0, spType = SpType.ASSIST),
    LiveForBounty(Name.LiveForBounty, maxLevel = 0, spType = SpType.ASSIST),
    LiveForHonor(Name.LiveForHonor, maxLevel = 0, spType = SpType.ASSIST),
    WarpPowder(Name.WarpPowder, maxLevel = 0, spType = SpType.LEGEND_S),
    ChillingSeal(Name.ChillingSeal, maxLevel = 0, spType = SpType.LEGEND_S),
    FreezingSeal(Name.FreezingSeal, maxLevel = 0, spType = SpType.LEGEND_S),
    ChillAtk(Name.ChillAtk, spType = SpType.BASE60),
    ChillSpd(Name.ChillSpd, spType = SpType.BASE60),
    ChillDef(Name.ChillDef, spType = SpType.BASE60),
    ChillRes(Name.ChillRes, spType = SpType.BASE60),
    // TODO:これキラーだっけ・・・？開始時１カウントだっけ…？
    SDrink(Name.SDrink, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipKiller(armedHero, lv)
    },
    SpdFeint(Name.SpdFeint, spType = SpType.BASE60),
    DefFeint(Name.DefFeint, spType = SpType.BASE60),
    AtkSpdLink(Name.AtkSpdLink, spType = SpType.BASE60),
    AtkDefLink(Name.AtkDefLink, spType = SpType.BASE60),
    AtkResLink(Name.AtkResLink, spType = SpType.BASE60),
    SpdResLink(Name.SpdResLink, spType = SpType.BASE60),
    DefResLink(Name.DefResLink, spType = SpType.BASE60),
    Aerobatics(Name.Aerobatics, spType = SpType.BASE60),
    ;

    /**
     * 同じスキルの別レベルを作成する。レベルがそのままなら自分自身のまま。
     * レベルのないスキル、武器や奥義は攻撃力やクールダウンが入っているので注意
     */
    fun lv(lv: Int) = if (level == lv) this else LappedSkill(this, lv)

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     */
    override val value get() = name

    //   override fun localeName(locale: Locale): String=jp.localeName(locale)

    companion object {
        fun spreadItems(none: Boolean = false): List<Skill> = values().fold(if (none) mutableListOf<Skill>(Skill.NONE) else mutableListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else (1..e.maxLevel).forEach { i -> list.add(e.lv(i)) };list
        }

        fun spreadMaxLvItems(none: Boolean = false): List<Skill> = values().fold(if (none) mutableListOf<Skill>(Skill.NONE) else mutableListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else list.add(e.lv(e.maxLevel));list
        }

        private val itemMap = mutableMapOf<String, SkillB>()

        fun valueOfOrNONE(key: String?): Skill = if (key == null) Skill.NONE
        else {
            try {
                if (itemMap.isEmpty()) {
                    values().forEach { e -> itemMap[e.value] = e;itemMap[e.jp.jp] = e;itemMap[e.jp.us] = e;itemMap[e.jp.tw] = e }
                }
                val regex = " \\d".toRegex()

                val lv = regex.find(key)
                if (lv != null) {
                    val skill = regex.replaceFirst(key, "")

                    (itemMap[skill] ?: valueOf(skill)).lv(lv.value.trim().toInt())
                } else itemMap[key] ?: valueOf(key)
            } catch (e: Exception) {
//                println(e)
                Skill.NONE
            }
        }
    }
}