package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * スキルA
 */
enum class SkillA(override val jp: SkillName, override val type: SkillType = SkillType.A, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val maxLevel: Int = 3, override val spType: SpType = SpType.BASE50) : Skill {
    Hp(SkillName.Hp, spType = SpType.BASE40) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    HpAtk(SkillName.HpAtk, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipAtk(armedHero, lv), lv + 2)
    },
    HpSpd(SkillName.HpSpd, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipSpd(armedHero, lv), lv + 2)
    },
    HpDef(SkillName.HpDef, maxLevel = 2, spType = SpType.BASE50) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipDef(armedHero, lv), lv + 2)
    },
    HpRes(SkillName.HpRes, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipRes(armedHero, lv), lv + 2)
    },
    Attack(SkillName.Attack, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, lv)
    },
    AtkSpd(SkillName.AtkSpd, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipSpd(armedHero, lv), lv)
    },
    AtkDef(SkillName.AtkDef, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(armedHero, lv), lv)
    },
    AtkRes(SkillName.AtkRes, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipRes(armedHero, lv), lv)
    },
    Speed(SkillName.Speed, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, lv)
    },
    SpdDef(SkillName.SpdDef, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipDef(armedHero, lv), lv)
    },
    SpdRes(SkillName.SpdRes, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(equipRes(armedHero, lv), lv)
    },
    DefRes(SkillName.DefRes, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(equipRes(armedHero, lv), lv)
    },
    Defense(SkillName.Defense, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, lv)
    },
    Resistance(SkillName.Resistance, spType = SpType.BASE30) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, lv)
    },

    TriangleAdept(SkillName.TriangleAdept) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, enemy, lv)
    },
    LifeAndDeath(SkillName.LifeAndDeath) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = lifeAndDeath(armedHero, lv + 2)
    },
    Fury(SkillName.Fury) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = fury(armedHero, lv)
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fightHpLoss(battleUnit, lv * 2)
    },
    FortressDef(SkillName.FortressDef) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(armedHero, lv + 2), -3)
    },
    FortressRes(SkillName.FortressRes) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipRes(armedHero, lv + 2), -3)
    },
    FortressDefRes(SkillName.FortressDef) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(equipRes(armedHero, if (lv == 3) 6 else lv + 2), if (lv == 3) 6 else lv + 2), if (lv == 3) -2 else -3)
    },

    HeavyBlade(SkillName.HeavyBlade, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 7 - lv * 2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, 7 - lv * 2)
    },
    FlashingBlade(SkillName.FlashingBlade, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 7 - lv * 2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 7 - lv * 2)
    },

    DistantDef(SkillName.DistantDef, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, lv * 2)
    },
    CloseDef(SkillName.CloseDef, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = closeDef(battleUnit, enemy, lv * 2)
    },

    CloseCounter(SkillName.CloseCounter, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },

    DistantCounter(SkillName.DistantCounter, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    RDuelFlying(SkillName.RDuelFlying, spType = SpType.BASE70) {
        override fun totalParam(n: Int): Int = 170

        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    GDuelInfantry(SkillName.GDuelInfantry, spType = SpType.BASE70) {
        override fun totalParam(n: Int): Int = 170
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    CDuelInfantry(SkillName.GDuelInfantry, spType = SpType.BASE70) {
        override fun totalParam(n: Int): Int = 170
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    DeathBlow(SkillName.DeathBlow, maxLevel = 4) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atk(battleUnit, lv * 2, this)
    },
    DartingBlow(SkillName.DartingBlow) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spd(battleUnit, lv * 2)
    },
    ArmoredBlow(SkillName.ArmoredBlow) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = def(battleUnit, lv * 2)
    },
    WardingBlow(SkillName.WardingBlow) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = res(battleUnit, lv * 2)
    },
    SwiftSparrow(SkillName.SwiftSparrow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkSpd(battleUnit, lv * 2)
    },
    SturdyBlow(SkillName.SturdyBlow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkDef(battleUnit, lv * 2)
    },
    MirrorStrike(SkillName.MirrorStrike, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkRes(battleUnit, lv * 2)
    },
    SteadyBlow(SkillName.SteadyBlow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spdDef(battleUnit, lv * 2)
    },
    SwiftStrike(SkillName.SwiftStrike, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spdRes(battleUnit, lv * 2)
    },
    BracingBlow(SkillName.BracingBlow, maxLevel = 2, spType = SpType.BASE120) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defRes(battleUnit,  lv * 2)
    },

    FierceStance(SkillName.FierceStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atk(battleUnit, lv * 2)
    },
    DartingStance(SkillName.DartingStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spd(battleUnit, lv * 2)
    },
    SteadyStance(SkillName.SteadyStance, maxLevel = 4) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (lv == 4) {
                enemy.InflictAttackCooldown = 1
                enemy.InflictTargetCooldown = 1
                //これ相手が攻撃を受けたときも効果があるか調べないとなあ。逆に隊形の効果も。今はどちらにしろ変動しないって判断だもんな
            }
            return def(battleUnit, lv * 2)
        }
    },
    WardingStance(SkillName.WardingStance) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = res(battleUnit, lv * 2)
    },
    KestrelStance(SkillName.KestrelStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkSpd(battleUnit, lv * 2)
    },
    SturdyStance(SkillName.SturdyStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkDef(battleUnit, lv * 2)
    },
    MirrorStance(SkillName.MirrorStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkRes(battleUnit, lv * 2)
    },
    SteadyPosture(SkillName.SteadyPosture, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spdDef(battleUnit, lv * 2)
    },
    SwiftStance(SkillName.SwiftStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = spdRes(battleUnit, lv * 2)
    },
    BracingStance(SkillName.BracingStance, maxLevel = 2, spType = SpType.BASE120) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = defRes(battleUnit, lv * 2)
    },
    FierceBreath(SkillName.FierceBreath, maxLevel = 0, spType = SpType.BREATH) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return atk(battleUnit, 4)
        }
    },
    WardingBreath(SkillName.WardingBreath, maxLevel = 0, spType = SpType.BREATH) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return res(battleUnit, 4)
        }
    },
    SteadyBreath(SkillName.SteadyBreath, maxLevel = 0, spType = SpType.BREATH) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
            return def(battleUnit, 4)
        }
    },
    AtkSpdBond(SkillName.AtkSpdBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atkSpd(battleUnit, lv + 2) else battleUnit
    },
    AtkDefBond(SkillName.AtkDefBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atkDef(battleUnit, lv + 2) else battleUnit
    },
    AtkResBond(SkillName.AtkResBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) atkRes(battleUnit, lv + 2) else battleUnit
    },
    SpdDefBond(SkillName.SpdDefBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) spdDef(battleUnit, lv + 2) else battleUnit
    },
    SpdResBond(SkillName.SpdResBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) spdRes(battleUnit, lv + 2) else battleUnit
    },
    DefResBond(SkillName.DefResBond, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits > 0) defRes(battleUnit, lv + 2) else battleUnit
    },

    BrazenAtkSpd(SkillName.BrazenAtkSpd, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtkSpd(battleUnit, lv * 2 + 1)
    },
    BrazenAtkDef(SkillName.BrazenAtkDef, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtkDef(battleUnit, lv * 2 + 1)
    },
    BrazenAtkRes(SkillName.BrazenAtkRes, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenAtkRes(battleUnit, lv * 2 + 1)
    },
    BrazenDefRes(SkillName.BrazenAtkSpd, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brazenDefRes(battleUnit, lv * 2 + 1)
    },

    AtkSpdPush(SkillName.AtkSpdPush) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = fullHpAtkSpdBonus(battleUnit, lv + 2, 1)
    },
    AtkSpdSolo(SkillName.AtkSpdSolo, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits == 0) atkSpd(battleUnit, lv * 2) else battleUnit
    },
    AtkDefSolo(SkillName.AtkDefSolo, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits == 0) atkDef(battleUnit, lv * 2) else battleUnit
    },
    AtkResSolo(SkillName.AtkResSolo, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = if (battleUnit.adjacentUnits == 0) atkRes(battleUnit, lv * 2) else battleUnit
    },

    DefiantAtk(SkillName.DefiantAtk, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, lv)
    },
    DefiantSpd(SkillName.DefiantSpd, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantSpd(battleUnit, lv)
    },
    DefiantDef(SkillName.DefiantDef, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantDef(battleUnit, lv)
    },
    DefiantRes(SkillName.DefiantRes, spType = SpType.BASE40) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantRes(battleUnit, lv)
    },
    FireBoost(SkillName.FireBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostAtk(battleUnit, enemy, lv * 2)
    },
    WindBoost(SkillName.WindBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostSpd(battleUnit, enemy, lv * 2)
    },
    EarthBoost(SkillName.EarthBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostDef(battleUnit, enemy, lv * 2)
    },
    WaterBoost(SkillName.WaterBoost) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = boostRes(battleUnit, enemy, lv * 2)
    },
    SorceryBlade(SkillName.SorceryBlade) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.adjacentUnits > 0 && battleUnit.hp >= battleUnit.armedHero.maxHp * (150 - lv * 50)) {//HP制限は後で考えよう…
                battleUnit.overrideDamageType = SkillType.SORCERY_DAGGER
            }
            return battleUnit
        }
    },

    OstianCounter(SkillName.OstianCounter, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = atkDef(counterAllRange(battleUnit), 4)
    },

    SvalinnShield(SkillName.SvalinnShield, maxLevel = 0, spType = SpType.SHIELD) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.ARMORED)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.ARMORED)
    },

    IotesShield(SkillName.IotesShield, maxLevel = 0, spType = SpType.SHIELD) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
    },

    Dragonskin(SkillName.Dragonskin, maxLevel = 0, spType = SpType.LEGEND_S) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = def(res(battleUnit, 4), 4)
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.FLIER)
    },

    GranisShield(SkillName.GranisShield, maxLevel = 0, spType = SpType.SHIELD) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.CAVALRY)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, enemy, EffectiveAgainst.CAVALRY)
    },
    LawsOfSacae(SkillName.LawsOfSacae, maxLevel = 0) {
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