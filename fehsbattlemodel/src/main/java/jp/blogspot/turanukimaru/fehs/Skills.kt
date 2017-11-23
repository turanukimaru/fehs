package jp.blogspot.turanukimaru.fehs

import java.util.*

/**
 * スキル。A/B/C/聖印。聖印は未実装
 */
enum class Skills(override val jp: String, override val type: Skill.SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val maxLevel: Int = 3) : Skill {
    Hp("HP", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipHp(armedClass, lv)
    },
    HpSpd("HP速さ", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipSpd(equipHp(armedClass, lv), lv)
    },
    HpRes("HP魔防", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipRes(equipHp(armedClass, lv), lv)
    },
    Attack("攻撃", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipAtk(armedClass, lv)
    },
    AtkSpd("攻撃速さ", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipSpd(equipAtk(armedClass, lv), lv)
    },
    AtkRes("攻撃魔防", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipRes(equipAtk(armedClass, lv), lv)
    },
    Speed("速さ", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipSpd(armedClass, lv)
    },
    SpdRes("速さ魔防", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipRes(equipSpd(armedClass, lv), lv)
    },
    SpdDef("速さ守備", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipDef(equipSpd(armedClass, lv), lv)
    },
    Defense("守備", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipDef(armedClass, lv)
    },
    AtkDef("攻撃守備", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipDef(equipAtk(armedClass, lv), lv)
    },
    HpDef("HP守備", Skill.SkillType.A, maxLevel = 2) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipDef(equipHp(armedClass, lv), lv)
    },
    Resistance("魔防", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipRes(armedClass, lv)
    },

    DefiantAtk("攻撃の覚醒", Skill.SkillType.A) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, lv)
    },
    DefiantSpd("速さの覚醒", Skill.SkillType.A) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantSpd(battleUnit, lv)
    },
    DefiantDef("守備の覚醒", Skill.SkillType.A) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantDef(battleUnit, lv)
    },
    DefiantRes("魔防の覚醒", Skill.SkillType.A) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantRes(battleUnit, lv)
    },

    TriangleAdept("相性激化", Skill.SkillType.A) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, lv)
    },
    LifeAndDeath("死線", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = lifeAndDeath(armedClass, lv + 2)
    },
    Furry("獅子奮迅", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = furry(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHpLoss(battleUnit, lv*2)
    },
    FortressDef("守備の城塞", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipAtk(equipDef(armedClass, lv + 2), -3)
    },
    FortressRes("魔防の城塞", Skill.SkillType.A) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipAtk(equipRes(armedClass, lv + 2), -3)
    },
    ArmoredBlow("金剛の一撃", Skill.SkillType.A) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowDef(battleUnit, lv * 2)
    },
    WardingBlow("明鏡の一撃", Skill.SkillType.A) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, lv * 2)
    },
    DeathBlow("鬼神の一撃", Skill.SkillType.A) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, lv * 2)
    },
    DartingBlow("飛燕の一撃", Skill.SkillType.A) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, lv * 2)
    },
    SwiftSparrow("鬼神飛燕の一撃", Skill.SkillType.A, maxLevel = 2) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowAtk(blowSpd(battleUnit, lv * 2), lv * 2)
    },
    SturdyBlow("鬼神金剛の一撃", Skill.SkillType.A, maxLevel = 2) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowAtk(blowDef(battleUnit, lv * 2), lv * 2)
    },
    SteadyBlow("飛燕金剛の一撃", Skill.SkillType.A, maxLevel = 2) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowSpd(blowDef(battleUnit, lv * 2), lv * 2)
    },
    MirrorStrike("鬼神明鏡の一撃", Skill.SkillType.A, maxLevel = 2) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowAtk(blowRes(battleUnit, lv * 2), lv * 2)
    },
    BracingBlow("金剛明鏡の一撃", Skill.SkillType.A, maxLevel = 2) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowRes(blowDef(battleUnit, lv * 2), lv * 2)
    },
    SwiftStrike("飛燕明鏡の一撃", Skill.SkillType.A, maxLevel = 2) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowRes(blowSpd(battleUnit, lv * 2), lv * 2)
    },

    DistantDef("遠距離防御", Skill.SkillType.A) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, lv * 2)
    },

    CloseDef("近距離防御", Skill.SkillType.A) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = closeDef(battleUnit, lv * 2)
    },

    FierceStance("鬼神の構え", Skill.SkillType.A) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = steadyAtk(battleUnit, lv * 2)
    },
    SteadyStance("金剛の構え", Skill.SkillType.A) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = steadyDef(battleUnit, lv * 2)
    },
    WardingStance("明鏡の構え", Skill.SkillType.A) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = steadyRes(battleUnit, lv * 2)
    },
    SteadyBreath("金剛の呼吸", Skill.SkillType.A, maxLevel = 0) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit {
            battleUnit.accelerateAttackCooldown = 1
            return steadyDef(battleUnit, 2)
        }

    },

    EarthBoost("生命の大地", Skill.SkillType.A) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = boostDef(battleUnit, lv * 2)
    },
    WindBoost("生命の疾風", Skill.SkillType.A) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = boostSpd(battleUnit, lv * 2)
    },
    FireBoost("生命の業火", Skill.SkillType.A) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = boostAtk(battleUnit, lv * 2)
    },
    WaterBoost("生命の静水", Skill.SkillType.A) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = boostRes(battleUnit, lv * 2)
    },

    HeavyBlade("剛剣", Skill.SkillType.A) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, 7-lv*2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, 7-lv*2)
    },
    FlashingBlade("柔剣", Skill.SkillType.A) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, 7-lv*2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, 7-lv*2)
    },

    CloseCounter("近距離反撃", Skill.SkillType.A, maxLevel = 0) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },

    DistantCounter("遠距離反撃", Skill.SkillType.A, maxLevel = 0) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },

    SvalinnShield("スヴェルの盾", Skill.SkillType.A, maxLevel = 0) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, EFFECTIVE_AGAINSTS.ARMORED)
        override fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, EFFECTIVE_AGAINSTS.ARMORED)
    },

    IotesShield("アイオテの盾", Skill.SkillType.A, maxLevel = 0) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, EFFECTIVE_AGAINSTS.FLIER)
        override fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, EFFECTIVE_AGAINSTS.FLIER)
    },

    GranisShield("グラニの盾", Skill.SkillType.A, maxLevel = 0) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, EFFECTIVE_AGAINSTS.CAVALRY)
        override fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiEffectiveAgainst(battleUnit, EFFECTIVE_AGAINSTS.CAVALRY)
    },
    AtkResBond( "攻撃魔防の絆",Skill.SkillType.A, maxLevel = 3) ,

    SwordBreaker("剣殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.SWORD, lv)
    },
    LanceBreaker("槍殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.LANCE, lv)
    },
    AxeBreaker("斧殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.AXE, lv)
    },
    BowBreaker("弓殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.BOW, lv)
    },
    DaggerBreaker("暗器殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.DAGGER, lv)
    },
    RTomeBreaker("赤魔殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.RTOME, lv)
    },
    GTomeBreaker("緑魔殺し", Skill.SkillType.B) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.GTOME, lv)
    },
    BTomeBreaker("青魔殺し", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = weaponBreaker(battleUnit, WeaponType.BTOME, lv)
    },
    EscapeRoute("離脱の行路", Skill.SkillType.B),
    Vantage("待ち伏せ", Skill.SkillType.B) {
        override fun counterPlan(fightPlan: FightPlan, lv: Int): FightPlan = vantage(fightPlan, lv)
    },
    WaryFighter("守備隊形", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = eachNofollowupable(battleUnit, lv)

    },
    Desperation("攻め立て", Skill.SkillType.B) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, lv)

    },
    QuickRiposte("切り返し", Skill.SkillType.B) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, lv)
    },

    BrashAssault("差し違え", Skill.SkillType.B) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = brashAssault(battleUnit, lv)

    },
    Renewal("回復", Skill.SkillType.B),
    LiveToServe("ご奉仕の喜び", Skill.SkillType.B),
    WingsOfMercy("救援の行路", Skill.SkillType.B),
    Pass("すり抜け", Skill.SkillType.B),
    Obstruct("進軍阻止", Skill.SkillType.B),
    SealAtk("攻撃封じ", Skill.SkillType.B),
    SealSpd("速さ封じ", Skill.SkillType.B),
    SealDef("守備封じ", Skill.SkillType.B),
    SealRes("魔防封じ", Skill.SkillType.B),
    SealAtkSpd("攻撃速さ封じ", Skill.SkillType.B, maxLevel = 2),
    SealAtkDef("攻撃守備封じ", Skill.SkillType.B, maxLevel = 2),
    Windsweep("風薙ぎ", Skill.SkillType.B) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = windsweep(battleUnit, lv)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = noFollowupAttack(fightPlan)
    },
    Watersweep("水薙ぎ", Skill.SkillType.B) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = watersweep(battleUnit, lv)
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = noFollowupAttack(fightPlan)
    },

    Guard("キャンセル", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiAccelerateCooldown(battleUnit, lv)
    },
    ShieldPulse("盾の鼓動", Skill.SkillType.B),
    //TODO:杖の処理が終わったら
    WrathfulStaff("神罰の杖", Skill.SkillType.B),
    DazzlingStaff("幻惑の杖", Skill.SkillType.B) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = dazzling(battleUnit, lv)

    },
    CancelAffinity("相性相殺", Skill.SkillType.B) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit {
            //高いほうが適用されるのでとりあえず自分だけ消す
            battleUnit.colorAdvantageLevel = 0
            val colorAd = battleUnit.colorAdvantage()
            //2と3は負けてるときだけ発動。両者が持ってるときは両社とも激化倍率がなくなるので問題ない
            when (lv) {
                1 -> {
                    battleUnit.antiColorAdvantage = 0;battleUnit.enemy!!.antiColorAdvantage = 0
                }
                2 -> if (colorAd == -1) {
                    battleUnit.antiColorAdvantage = 0;battleUnit.enemy!!.antiColorAdvantage = 0
                }
                3 -> if (colorAd == -1) {
                    battleUnit.antiColorAdvantage = -1;battleUnit.enemy!!.antiColorAdvantage = -1
                }
            }
            return battleUnit
        }
    },
    Wrath("怒り", Skill.SkillType.B),
    FlierFormation("編隊飛行", Skill.SkillType.B),
    BlazeDance("業火の舞い", Skill.SkillType.B),
    GaleDance("疾風の舞い", Skill.SkillType.B),
    TorrentDance("静水の舞い", Skill.SkillType.B),
    GeyserDance("大地静水の舞い", Skill.SkillType.B, maxLevel = 2),
    KnockBack("叩き込み", Skill.SkillType.B, maxLevel = 0),
    DragBack("引き込み", Skill.SkillType.B, maxLevel = 0),
    Lunge("切り込み", Skill.SkillType.B, maxLevel = 0),
    HitAndRun("一撃離脱", Skill.SkillType.B, maxLevel = 0),
    FollowUpRing("追撃のリング", Skill.SkillType.B, maxLevel = 0) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, 5)
    },
    RecoverRing("リカバーリング", Skill.SkillType.B, maxLevel = 0),
    LiveForBounty("豊穣の喜び", Skill.SkillType.B, maxLevel = 0),
    LiveForHonor("栄誉の喜び", Skill.SkillType.B, maxLevel = 0),
    SacaesBlessing("サカの加護", Skill.SkillType.B) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = sacasBlessing(battleUnit, lv)

    },
    BeorcsBlessing("ベオクの加護", Skill.SkillType.B, maxLevel = 0) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = beorcsBlessing(battleUnit, lv)
    },
    CrusadersWard("聖騎士の加護", Skill.SkillType.B, maxLevel = 0) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, results: List<AttackResult>, lv: Int): Int =
                if (battleUnit.enemy!!.effectiveRange == 2 && results.isNotEmpty() && results.last().side != battleUnit.side) damage - damage * 8 / 10 else damage
    },


    SpurAtk("攻撃の紋章", Skill.SkillType.C),
    SpurSpd("速さの紋章", Skill.SkillType.C),
    SpurDef("守備の紋章", Skill.SkillType.C),
    SpurRes("魔防の紋章", Skill.SkillType.C),
    SpurDefRes("守備魔防の紋章", Skill.SkillType.C, maxLevel = 2),
    SpurSpdDef("速さ守備の紋章", Skill.SkillType.C, maxLevel = 2),
    ThreatenAtk("攻撃の威嚇", Skill.SkillType.C),
    ThreatenSpd("速さの威嚇", Skill.SkillType.C),
    ThreatenDef("守備の威嚇", Skill.SkillType.C),
    ThreatenRes("魔防の威嚇", Skill.SkillType.C),
    FortifyDef("守備の鼓舞", Skill.SkillType.C),
    FortifyRes("魔防の鼓舞", Skill.SkillType.C),
    FortifyArmor("重盾の鼓舞", Skill.SkillType.C, maxLevel = 0),
    FortifyCavalry("騎盾の鼓舞", Skill.SkillType.C, maxLevel = 0),
    FortifyFliers("飛盾の鼓舞", Skill.SkillType.C, maxLevel = 0),
    FortifyDragons("竜盾の鼓舞", Skill.SkillType.C, maxLevel = 0),
    HoneAtk("攻撃の鼓舞", Skill.SkillType.C),
    HoneSpd("速さの鼓舞", Skill.SkillType.C),
    DriveAtk("攻撃の大紋章", Skill.SkillType.C, maxLevel = 2),
    DriveDef("守備の大紋章", Skill.SkillType.C, maxLevel = 2),
    DriveSpd("速さの大紋章", Skill.SkillType.C, maxLevel = 2),
    DriveRes("魔防の大紋章", Skill.SkillType.C, maxLevel = 2),
    HoneArmor("重刃の鼓舞", Skill.SkillType.C, maxLevel = 0),
    HoneCavalry("騎刃の鼓舞", Skill.SkillType.C, maxLevel = 0),
    HoneFliers("飛刃の鼓舞", Skill.SkillType.C, maxLevel = 0),
    GoadArmor("重刃の紋章", Skill.SkillType.C, maxLevel = 0),
    GoadCavalry("騎刃の紋章", Skill.SkillType.C, maxLevel = 0),
    GoadFliers("飛刃の紋章", Skill.SkillType.C, maxLevel = 0),
    WardArmor("重盾の紋章", Skill.SkillType.C, maxLevel = 0),
    WardCavalry("騎盾の紋章", Skill.SkillType.C, maxLevel = 0),
    WardFliers("飛盾の紋章", Skill.SkillType.C, maxLevel = 0),
    SavageBlow("死の吐息", Skill.SkillType.C),
    BreathOfLife("生の息吹", Skill.SkillType.C),
    AxeExperience("斧の経験", Skill.SkillType.C),
    BowExperience("弓の経験", Skill.SkillType.C),
    BTomeExperience("青魔の経験", Skill.SkillType.C),
    SwordExperience("剣の経験", Skill.SkillType.C),
    LanceValor("槍の技量", Skill.SkillType.C),
    AxeValor("斧の技量", Skill.SkillType.C),
    SwordValor("剣の技量", Skill.SkillType.C),
    DaggerValor("暗器の技量", Skill.SkillType.C),
    GTomeValor("緑魔の技量", Skill.SkillType.C),
    BTomeValor("青魔の技量", Skill.SkillType.C),
    AtkPloy("攻撃の謀策", Skill.SkillType.C),
    PanicPloy("恐慌の奇策", Skill.SkillType.C),
    DefPloy("守備の謀策", Skill.SkillType.C),
    ResPloy("魔防の謀策", Skill.SkillType.C),
    SpdPloy("速さの謀策", Skill.SkillType.C),
    InfantryPulse("歩行の鼓動", Skill.SkillType.C),
    AtkSmoke("攻撃の紫煙", Skill.SkillType.C),
    SpdSmoke("速さの紫煙", Skill.SkillType.C),
    ArmorMarch("重装の行軍", Skill.SkillType.C),
    Guidance("空からの先導", Skill.SkillType.C),
    PoisonStrike("蛇毒", Skill.SkillType.B) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit  =attackPain(battleUnit,lv*3+1)
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

    override fun localeName(locale: Locale): String {
        return when (locale) {
            Locale.JAPAN -> jp
            Locale.JAPANESE -> jp
            else -> value
        }
    }

    companion object {
        fun spreadItems():List<Skill> = values().fold(arrayListOf<Skill>(Skill.NONE),{list,e->(1..e.maxLevel).forEach({i->list.add(e.lv(i))});list})
        val itemMap = mutableMapOf<String, Skills>()

        fun valueOfOrNONE(key: String?): Skill = if (key == null) Skill.NONE
        else {
            try {
                if (itemMap.isEmpty()) {
                    values().forEach { e -> itemMap.put(e.jp, e) }
                    values().forEach { e -> itemMap.put(e.value, e) }
                }
                val regex = " \\d".toRegex()

                val lv = regex.find(key)
                if (lv!= null) {
                    val skill = regex.replaceFirst(key, "")

                    (itemMap[skill] ?: valueOf(skill)).lv(lv.value.trim().toInt())
                } else itemMap[key] ?: valueOf(key)
            } catch (e: Exception) {
                println(e)
                Skill.NONE
            }
        }
    }
}