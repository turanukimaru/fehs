package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキルA
 */
enum class SkillA(override val jp: Name, override val type: SkillType = SkillType.A, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val maxLevel: Int = 3, override val spType: SpType = SpType.BASE50) : Skill {
    Hp(Name.Hp, spType = SpType.BASE40) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    HpAtk(Name.HpAtk, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipAtk(armedHero, lv), lv + 2)
    },
    HpSpd(Name.HpSpd, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipSpd(armedHero, lv), lv + 2)
    },
    HpDef(Name.HpDef, maxLevel = 2, spType = SpType.BASE50) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipDef(armedHero, lv), lv + 2)
    },
    HpRes(Name.HpRes, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipRes(armedHero, lv), lv + 2)
    },
    Attack(Name.Attack, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, lv)
    },
    AtkSpd(Name.AtkSpd, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipSpd(armedHero, lv), lv)
    },
    AtkDef(Name.AtkDef, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(armedHero, lv), lv)
    },
    AtkRes(Name.AtkRes, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipRes(armedHero, lv), lv)
    },
    Speed(Name.Speed, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, lv)
    },
    SpdDef(Name.SpdDef, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipDef(armedHero, lv), lv)
    },
    SpdRes(Name.SpdRes, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipRes(armedHero, lv), lv)
    },
    DefRes(Name.DefRes, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipRes(armedHero, lv), lv)
    },
    Defense(Name.Defense, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, lv)
    },
    Resistance(Name.Resistance, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, lv)
    },

    TriangleAdept(Name.TriangleAdept) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, lv)
    },
    LifeAndDeath(Name.LifeAndDeath) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = lifeAndDeath(armedHero, lv + 2)
    },
    Fury(Name.Fury) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = fury(armedHero, lv)
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, lv * 2)
    },
    FortressDef(Name.FortressDef) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(armedHero, lv + 2), -3)
    },
    FortressRes(Name.FortressRes) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipRes(armedHero, lv + 2), -3)
    },
    FortressDefRes(Name.FortressDef) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(equipRes(armedHero, if (lv == 3) 6 else lv + 2), if (lv == 3) 6 else lv + 2), if (lv == 3) -2 else -3)
    },

    HeavyBlade(Name.HeavyBlade, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 7 - lv * 2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 7 - lv * 2)
    },
    FlashingBlade(Name.FlashingBlade, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 7 - lv * 2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 7 - lv * 2)
    },

    DistantDef(Name.DistantDef, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, lv * 2)
    },
    CloseDef(Name.CloseDef, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = closeDef(battleUnit, enemy, lv * 2)
    },

    CloseCounter(Name.CloseCounter, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },

    DistantCounter(Name.DistantCounter, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    RDuelFlying(Name.RDuelFlying, spType = SpType.BASE70) {
        override fun totalParam(n: Int): Int = 170

        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    GDuelInfantry(Name.GDuelInfantry, spType = SpType.BASE70) {
        override fun totalParam(n: Int): Int = 170
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    CDuelInfantry(Name.GDuelInfantry, spType = SpType.BASE70) {
        override fun totalParam(n: Int): Int = 170
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    DeathBlow(Name.DeathBlow, maxLevel = 4) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, lv * 2)
    },
    DartingBlow(Name.DartingBlow) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, lv * 2)
    },
    ArmoredBlow(Name.ArmoredBlow) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(battleUnit, lv * 2)
    },
    WardingBlow(Name.WardingBlow) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, lv * 2)
    },
    SwiftSparrow(Name.SwiftSparrow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, lv * 2), lv * 2)
    },
    SturdyBlow(Name.SturdyBlow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowDef(battleUnit, lv * 2), lv * 2)
    },
    MirrorStrike(Name.MirrorStrike, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowRes(battleUnit, lv * 2), lv * 2)
    },
    SteadyBlow(Name.SteadyBlow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(blowDef(battleUnit, lv * 2), lv * 2)
    },
    SwiftStrike(Name.SwiftStrike, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(blowSpd(battleUnit, lv * 2), lv * 2)
    },
    BracingBlow(Name.BracingBlow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(blowDef(battleUnit, lv * 2), lv * 2)
    },

    FierceStance(Name.FierceStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, lv * 2)
    },
    DartingStance(Name.DartingStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, lv * 2)
    },
    SteadyStance(Name.SteadyStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (lv == 4) {
                enemy.InflictAttackCooldown = 1
                enemy.InflictTargetCooldown = 1
                //これ相手が攻撃を受けたときも効果があるか調べないとなあ。逆に隊形の効果も。今はどちらにしろ変動しないって判断だもんな
            }
            return blowDef(battleUnit, lv * 2)
        }
    },
    WardingStance(Name.WardingStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, lv * 2)
    },
    KestrelStance(Name.KestrelStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, lv * 2), lv * 2)
    },
    SturdyStance(Name.SturdyStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowDef(battleUnit, lv * 2), lv * 2)
    },
    MirrorStance(Name.MirrorStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowRes(battleUnit, lv * 2), lv * 2)
    },
    SteadyPosture(Name.SteadyPosture, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(blowDef(battleUnit, lv * 2), lv * 2)
    },
    SwiftStance(Name.SwiftStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowSpd(blowRes(battleUnit, lv * 2), lv * 2)
    },
    BracingStance(Name.BracingStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, lv * 2), lv * 2)
    },
    FierceBreath(Name.FierceBreath, maxLevel = 0, spType = SpType.BREATH) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return blowAtk(battleUnit, 4)
        }
    },
    WardingBreath(Name.WardingBreath, maxLevel = 0, spType = SpType.BREATH) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return blowRes(battleUnit, 4)
        }
    },
    SteadyBreath(Name.SteadyBreath, maxLevel = 0, spType = SpType.BREATH) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return blowDef(battleUnit, 4)
        }
    },
    AtkSpdBond(Name.AtkSpdBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowSpd(battleUnit, lv + 2), lv + 2) else battleUnit
    },
    AtkDefBond(Name.AtkDefBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowDef(battleUnit, lv + 2), lv + 2) else battleUnit
    },
    AtkResBond(Name.AtkResBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowAtk(blowRes(battleUnit, lv + 2), lv + 2) else battleUnit
    },
    SpdDefBond(Name.SpdDefBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowSpd(blowDef(battleUnit, lv + 2), lv + 2) else battleUnit
    },
    SpdResBond(Name.SpdResBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) blowSpd(blowRes(battleUnit, lv + 2), lv + 2) else battleUnit
    },

    BrazenAtkSpd(Name.BrazenAtkSpd, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtk(brazenSpd(battleUnit, lv * 2 + 1), lv * 2 + 1)
    },
    BrazenAtkDef(Name.BrazenAtkDef, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtk(brazenDef(battleUnit, lv * 2 + 1), lv * 2 + 1)
    },
    BrazenAtkRes(Name.BrazenAtkRes, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtk(brazenRes(battleUnit, lv * 2 + 1), lv * 2 + 1)
    },
    BrazenDefRes(Name.BrazenAtkSpd, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenDef(brazenRes(battleUnit, lv * 2 + 1), lv * 2 + 1)
    },

    AtkSpdPush(Name.AtkSpdPush) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAtkSpdBonus(battleUnit, lv + 2, 1)
    },
    AtkSpdSolo(Name.AtkSpdSolo, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits == 0) blowAtk(blowSpd(battleUnit, lv * 2), lv * 2) else battleUnit
    },
    AtkResSolo(Name.AtkResSolo, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits == 0) blowAtk(blowRes(battleUnit, lv * 2), lv * 2) else battleUnit
    },

    DefiantAtk(Name.DefiantAtk, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, lv)
    },
    DefiantSpd(Name.DefiantSpd, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantSpd(battleUnit, lv)
    },
    DefiantDef(Name.DefiantDef, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantDef(battleUnit, lv)
    },
    DefiantRes(Name.DefiantRes, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantRes(battleUnit, lv)
    },
    FireBoost(Name.FireBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostAtk(battleUnit, enemy, lv * 2)
    },
    WindBoost(Name.WindBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostSpd(battleUnit, enemy, lv * 2)
    },
    EarthBoost(Name.EarthBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostDef(battleUnit, enemy, lv * 2)
    },
    WaterBoost(Name.WaterBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostRes(battleUnit, enemy, lv * 2)
    },
    SorceryBlade(Name.SorceryBlade) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit{
            if (battleUnit.adjacentUnits > 0 && battleUnit.hp >= battleUnit.armedHero.maxHp * (150 - lv * 50)){//HP制限は後で考えよう…
                battleUnit.overrideDamageType = SkillType.SORCERY_DAGGER
            }
                return battleUnit
        }
    },

    OstianCounter(Name.OstianCounter, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowAtk(blowDef(counterAllRange(battleUnit), 4), 4)
    },

    SvalinnShield(Name.SvalinnShield, maxLevel = 0, spType = SpType.SHIELD) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.ARMORED)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.ARMORED)
    },

    IotesShield(Name.IotesShield, maxLevel = 0, spType = SpType.SHIELD) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
    },

    Dragonskin(Name.Dragonskin, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 4), 4)
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
    },

    GranisShield(Name.GranisShield, maxLevel = 0, spType = SpType.SHIELD) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.CAVALRY)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.CAVALRY)
    },
    LawsOfSacae(Name.LawsOfSacae, maxLevel = 0) {
        //実際は2以上。これ比較対象をユニットに持たせなきゃだめだな
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) allBonus(battleUnit, 4) else battleUnit
    },
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

    // override fun localeName(locale: Locale): String = jp.localeName(locale)

    companion object {
        fun spreadItems(none: Boolean = false): List<Skill> = values().fold(if (none) mutableListOf<Skill>(Skill.NONE) else mutableListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else (1..e.maxLevel).forEach { i -> list.add(e.lv(i)) };list
        }

        fun spreadMaxLvItems(none: Boolean = false): List<Skill> = values().fold(if (none) mutableListOf<Skill>(Skill.NONE) else mutableListOf()) { list, e ->
            when (e.maxLevel) {
                0 -> list.add(e)
                4 -> {
                    list.add(e.lv(3));list.add(e.lv(4))
                }
                else -> list.add(e.lv(e.maxLevel))
            }
            list
        }

        private val itemMap = mutableMapOf<String, SkillA>()

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