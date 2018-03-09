package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

interface Skill {
    val level: Int get() = 0
    val type: SkillType get() = SkillType.NONE
    val value: String get() = ""
    val jp: Name get() = Name.NONE
    val maxLevel: Int get() = 0
    val mitMod: Int get() = 0
    val offMlt: Int get() = 10
    val penetrate: Int get() = 0
    val stateDamage: (BattleUnit) -> Int get() = { _ -> 0 }
    val heal: Int get() = 0
    // nullオブジェクト。もっといいやり方があればいいのだが
    val preSkill: Skill get() = Skill.NONE

    class None : Skill
    companion object {
        val NONE = None()
    }

    fun localeName(locale: Locale): String = jp.localeName(locale)

    /**
     * 戦闘時の効果。基本的にunitの能力値を上下したり
     * 弓だけはここで特効のチェックするか…
     */
    fun bothEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int = level): BattleUnit {
        if (type == SkillType.BOW && enemy.armedHero.baseHero.moveType == MoveType.FLIER) battleUnit.effectiveAgainst = EffectiveAgainst.FLIER
        return battleUnit
    }

    /**
     * 攻撃時の効果。基本的にunitの能力値を上下したり
     */
    fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int = level): BattleUnit = battleUnit

    /**
     * 反撃時の効果
     */
    fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int = level): BattleUnit = battleUnit

    /**
     * 攻撃時の効果。基本的にunitの能力値が上下した後参照したいとき
     */
    fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int = level): BattleUnit = battleUnit

    /**
     * 反撃時の効果。基本的にunitの能力値が上下した後参照したいとき
     */
    fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int = level): BattleUnit = battleUnit

    /**
     * 攻撃時の効果。基本的にunitの能力値を上下したり攻撃の順番を変える
     */
    fun attackPlan(fightPlan: FightPlan, lv: Int = level): FightPlan = fightPlan

    /**
     * 反撃時の効果として用意したけどuni内に攻撃側かどうかが入ってるので削除するべきか
     */
    fun counterPlan(fightPlan: FightPlan, lv: Int = level): FightPlan = fightPlan

    /**
     * ダメージ減少。連撃防御
     */
    fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int = level): Int = damage

    /**
     * ダメージ減少。奥義や連撃防御.奥義が2回発動するようなことになりませんように・・・
     */
    fun specialPrevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, lv: Int = level): Pair<Int, Skill> = Pair(damage, Skill.NONE)

    /**
     * 奥義発動時の追加効果。ほぼダメージ＋１０のスキル用
     */
    fun specialTriggered(battleUnit: BattleUnit, damage: Int): Int = damage

    /**
     * 軽減奥義発動時の追加効果。楯の鼓動用。↑と分けたのは軽減奥義＋わとうの時にダメージが増えてしまうから…
     */
    fun specialPreventTriggered(battleUnit: BattleUnit, damage: Int): Int = damage

    /**
     * ほぼ奥義専用。攻撃時のダメージ計算。デフォルトで奥義なしのダメージ
     */
    fun damage(damage: Int, prevent: Int): Int = HandmaidMath.max(damage - prevent, 0) * offMlt / 10

    fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int) = battleUnit.heal(damage * heal / 100)

    /**
     * 装備時の能力値変化
     */
    fun equip(armedHero: ArmedHero, lv: Int = this.level): ArmedHero {
        if (type.isWeapon) {
            armedHero.atkEqp += lv
        }
        return armedHero
    }

    /**
     * ターン開始時。鼓舞や自己バフ
     */
    fun turnStart(battleUnit: BattleUnit, lv: Int = level): BattleUnit = battleUnit

    /**
     * 先頭終了後のダメージとかバフとか。画面にエフェクトを出す処理を追加する必要があるな
     */
    fun afterFightEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit = battleUnit

    /**
     * スキルによるダメージ軽減のあとそれをどうするか。氷鏡専用
     */
    fun preventedDamage(battleUnit: BattleUnit, damage: Int = 0, lv: Int = level): BattleUnit = battleUnit

    //ここからスキル効果
//   試しに作ったけど〇〇の覚醒ってターン開始時効果だから今は要らなかったんじゃ・・・？
    fun defiantAtk(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) battleUnit.buffAtk(lv * 2 + 1)
        return battleUnit
    }

    fun defiantSpd(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) battleUnit.buffSpd(lv * 2 + 1)
        return battleUnit
    }

    fun defiantDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) battleUnit.buffDef(lv * 2 + 1)
        return battleUnit
    }

    fun defiantRes(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.armedHero.maxHp / 2) battleUnit.buffRes(lv * 2 + 1)
        return battleUnit
    }

    fun blowAtk(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.atkEffect += lv
        return battleUnit
    }

    fun blowSpd(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.spdEffect += lv
        return battleUnit
    }

    fun blowDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.defEffect += lv
        return battleUnit
    }

    fun blowRes(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.resEffect += lv
        return battleUnit
    }

    fun closeDef(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (enemy.armedHero.baseHero.weaponType.range == 1) {
            battleUnit.defEffect += lv
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun distantDef(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (enemy.armedHero.baseHero.weaponType.range == 2) {
            battleUnit.defEffect += lv
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun weaponBreaker(battleUnit: BattleUnit, enemy: BattleUnit, weapon: WeaponType, lv: Int): BattleUnit {
        log("HP:${battleUnit.hp}")
        log("threashold:${battleUnit.armedHero.maxHp * (lv * 20 - 10) / 100}")
        if ((battleUnit.hp >= battleUnit.armedHero.maxHp * (lv * 20 - 10) / 100) && enemy.armedHero.baseHero.weaponType == weapon) {
            battleUnit.followupable = true
            enemy.antiFollowup = true
        }
        return battleUnit
    }

    /**
     *
     */
    fun boostDef(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - enemy.hp >= 3) {
            battleUnit.defEffect += lv
        }
        return battleUnit
    }

    fun boostSpd(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - enemy.hp >= 3) {
            battleUnit.spdEffect += lv
        }
        return battleUnit
    }

    fun boostAtk(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - enemy.hp >= 3) {
            battleUnit.atkEffect += lv
        }
        return battleUnit
    }

    fun boostRes(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - enemy.hp >= 3) {
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun furry(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.atkEqp += lv
        armedHero.spdEqp += lv
        armedHero.defEqp += lv
        armedHero.resEqp += lv
        return armedHero
    }

    fun lifeAndDeath(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.atkEqp += lv
        armedHero.spdEqp += lv
        armedHero.defEqp -= lv
        armedHero.resEqp -= lv
        return armedHero
    }

    fun log(s: Any) {
//        println(s)
    }

    fun equipHp(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.hpEqp += lv
        return armedHero
    }

    fun equipAtk(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.atkEqp += lv
        return armedHero
    }

    fun equipKiller(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.atkEqp += lv
        armedHero.reduceSpecialCooldown += 1
        return armedHero
    }

    fun equipSpd(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.spdEqp += lv
        return armedHero
    }

    fun equipDef(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.defEqp += lv
        return armedHero
    }

    fun equipRes(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.resEqp += lv
        return armedHero
    }

    fun effectiveAgainst(moveType: MoveType, battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        if (enemy.armedHero.baseHero.moveType == moveType) {
            battleUnit.effectiveAgainst = EffectiveAgainst.Companion.valueOfMoveType(moveType)
        }
        return battleUnit
    }

    fun effectiveAgainst(weaponType: WeaponType, battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        if (enemy.armedHero.baseHero.weaponType == weaponType) {
            battleUnit.effectiveAgainst = EffectiveAgainst.Companion.valueOfWeaponType(weaponType)
        }
        return battleUnit
    }

    fun effectiveAgainstMagic(battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        if (enemy.armedHero.isMagicWeapon()) {
            battleUnit.effectiveAgainst = EffectiveAgainst.Companion.valueOfWeaponType(enemy.armedHero.baseHero.weaponType)
        }
        return battleUnit
    }

    fun doubleAttack(battleUnit: BattleUnit): BattleUnit {
        battleUnit.doubleAttack = true
        return battleUnit
    }

    fun colorAdvantage(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        battleUnit.colorAdvantageLevel = lv
        return battleUnit
    }

    fun vantage(fightPlan: FightPlan, lv: Int): FightPlan {
        if (fightPlan.target.hp <= fightPlan.target.armedHero.maxHp * (25 * lv) / 100) {
            fightPlan.plan.remove(fightPlan.firstCounter)
            fightPlan.plan.add(0, fightPlan.firstCounter)
        }
        return fightPlan
    }

    /**
     * 薙ぎの時は殺し持ってても追撃が出ないという事でいいのかな？同時には持てないか？
     */
    fun noFollowupAttack(fightPlan: FightPlan): FightPlan {
        fightPlan.plan.remove(fightPlan.secondAttack)
        return fightPlan
    }

    fun followupable(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp >= battleUnit.armedHero.maxHp * (100 - 10 * lv) / 100) {
            log(battleUnit.armedHero.name + " followapable")
            battleUnit.followupable = true
        }
        return battleUnit
    }

    fun eachNofollowupable(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp >= battleUnit.armedHero.maxHp * (110 - 20 * lv) / 100) {
            log(battleUnit.armedHero.name + " followapable")
            battleUnit.antiFollowup = true
            enemy.antiFollowup = true
        }
        return battleUnit
    }

    fun desperation(fightPlan: FightPlan, lv: Int): FightPlan {
        if (fightPlan.attacker.hp <= fightPlan.attacker.armedHero.maxHp * (25 * lv) / 100 && fightPlan.plan.contains(fightPlan.secondAttack)) {
            fightPlan.plan.remove(fightPlan.secondAttack)
            fightPlan.plan.add(fightPlan.plan.indexOf(fightPlan.firstAttack) + 1, fightPlan.secondAttack)
        }
        return fightPlan
    }

    fun brashAssault(battleUnit: BattleUnit, enemy: BattleUnit, percentile: Int): BattleUnit {
        if ((battleUnit.armedHero.baseHero.weaponType.range == enemy.armedHero.baseHero.weaponType.range || enemy.counterAllRange)
                && !enemy.cannotCcounter
                && (battleUnit.hp <= battleUnit.armedHero.maxHp * percentile / 100)) {
            battleUnit.followupable = true
        }
        return battleUnit
    }

    fun windsweep(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (enemy.armedHero.baseHero.weaponType.isMaterial
                && (battleUnit.effectedPhantomSpd - enemy.effectedPhantomSpd >= 7 - lv * 2)) {
            enemy.cannotCcounter = true
        }
        return battleUnit
    }

    fun watersweep(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (!enemy.armedHero.baseHero.weaponType.isMaterial
                && (battleUnit.effectedPhantomSpd - enemy.effectedPhantomSpd >= 7 - lv * 2)) {
            enemy.cannotCcounter = true
        }
        return battleUnit
    }

    fun dazzling(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp >= battleUnit.armedHero.maxHp * (150 - lv * 50)) {
            enemy.cannotCcounter = true
        }
        return battleUnit
    }

    fun wrathfulStaff(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp >= battleUnit.armedHero.maxHp * (150 - lv * 50)) {
            battleUnit.wrathfulStaff = true
        }
        return battleUnit
    }

    fun counterAllRange(battleUnit: BattleUnit): BattleUnit {
        battleUnit.counterAllRange = true
        return battleUnit
    }


    fun disableEachCounter(battleUnit: BattleUnit, enemy: BattleUnit, thisLevel: Int): BattleUnit {
        battleUnit.cannotCcounter = true
        enemy.cannotCcounter = true
        return battleUnit
    }

    fun antiFollowupDef(battleUnit: BattleUnit, enemy: BattleUnit, thisLevel: Int): BattleUnit {
        if (battleUnit.def - thisLevel >= enemy.def) {
            enemy.antiFollowup = true
        }
        return battleUnit
    }

    fun sacasBlessing(battleUnit: BattleUnit, enemy: BattleUnit, thisLevel: Int): BattleUnit {
        val enemyType = enemy.armedHero.baseHero.weaponType
        if (enemyType == WeaponType.SWORD || enemyType == WeaponType.LANCE || enemyType == WeaponType.AXE) {
            enemy.cannotCcounter = true
        }
        return battleUnit
    }

    fun beorcsBlessing(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        val enemyType = enemy.armedHero.baseHero.moveType
        if (enemyType == MoveType.CAVALRY || enemyType == MoveType.FLIER) {
            enemy.antiBuffBonus = true
        }
        return battleUnit
    }

    fun dullRanged(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        val enemyType = enemy.armedHero.baseHero.weaponType
        if (enemyType == WeaponType.RTOME || enemyType == WeaponType.BTOME || enemyType == WeaponType.GTOME || enemyType == WeaponType.BOW || enemyType == WeaponType.DAGGER || enemyType == WeaponType.STAFF) {
            enemy.cannotCcounter = true
        }
        return battleUnit
    }

    fun equip(armedHero: ArmedHero, type: SkillType): ArmedHero {
        if (type.isWeapon) {
            armedHero.atkEqp += level
        }
        return armedHero
    }

    fun fullHpBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        if (battleUnit.hp == battleUnit.armedHero.maxHp) {
            battleUnit.atkEffect += 2
            battleUnit.spdEffect += 2
            battleUnit.defEffect += 2
            battleUnit.resEffect += 2
            if (battleUnit.side == SIDES.ATTACKER) {
                battleUnit.hpLossAtEndOfFight += 2
            }
        }
        return battleUnit
    }

    fun allBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        battleUnit.atkEffect += i
        battleUnit.spdEffect += i
        battleUnit.defEffect += i
        battleUnit.resEffect += i
        return battleUnit
    }

    fun enemyFullHpBonus(battleUnit: BattleUnit, enemy: BattleUnit, i: Int): BattleUnit {
        if (enemy.hp == enemy.armedHero.maxHp) {
            battleUnit.atkEffect += i
            battleUnit.spdEffect += i
        }
        return battleUnit
    }


    fun equipBrave(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.atkEqp += lv
        armedHero.spdEqp -= 5
        return armedHero
    }

    fun equipBlade(armedHero: ArmedHero, lv: Int): ArmedHero {
        armedHero.atkEqp += lv
        armedHero.reduceSpecialCooldown -= 1
        return armedHero
    }

    fun equipRaven(battleUnit: BattleUnit): BattleUnit {
        battleUnit.colorlessAdvantage = true
        return battleUnit
    }

    fun bladeEffect(battleUnit: BattleUnit): BattleUnit {
        battleUnit.blade = true
        return battleUnit
    }

    fun fullHpAtkSpdBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        if (battleUnit.hp == battleUnit.armedHero.maxHp) {
            battleUnit.atkEffect += 5
            battleUnit.spdEffect += 5
            if (battleUnit.side == SIDES.ATTACKER) {
                battleUnit.hpLossAtEndOfFight += 5
            }

        }
        return battleUnit
    }

    fun fullHpAllBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        if (battleUnit.hp == battleUnit.armedHero.maxHp) {
            battleUnit.atkEffect += i
            battleUnit.spdEffect += i
            battleUnit.defEffect += i
            battleUnit.resEffect += i
            if (battleUnit.side == SIDES.ATTACKER) {
                battleUnit.hpLossAtEndOfFight += i
            }

        }
        return battleUnit
    }

    fun notFullHpAllBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        if (battleUnit.hp < battleUnit.armedHero.maxHp) {
            battleUnit.atkEffect += i
            battleUnit.spdEffect += i
            battleUnit.defEffect += i
            battleUnit.resEffect += i
        }
        return battleUnit
    }

    fun debuffBonus(battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        battleUnit.debuffBonus = enemy.atkDebuff + enemy.spdDebuff + enemy.defBuff + enemy.resDebuff
        return battleUnit
    }

    fun antiBuffBonus(battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        enemy.antiBuffBonus = true
        return battleUnit
    }

    fun antiMagicBuffBonus(battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        if (enemy.armedHero.baseHero.isMagicWeapon()) {
            enemy.antiBuffBonus = true
        }
        return battleUnit
    }


    fun antiRangedWeaponBuffBonus(battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        if (enemy.armedHero.baseHero.weaponType.range == 2) {
            enemy.antiBuffBonus = true
        }
        return battleUnit
    }

    fun heavyBlade(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.effectedAtk - enemy.effectedAtk > lv) {
            battleUnit.accelerateAttackCooldown = 1
        }
        return battleUnit
    }

    fun flashingBlade(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.effectedPhantomSpd - enemy.effectedPhantomSpd > lv) {
            battleUnit.accelerateAttackCooldown = 1
        }
        return battleUnit
    }

    fun feliciasBlade(battleUnit: BattleUnit, enemy: BattleUnit): BattleUnit {
        if (enemy.armedHero.isMagicWeapon()) {
            //攻撃で儲けでも加速
            battleUnit.accelerateAttackCooldown = 1
            battleUnit.accelerateTargetCooldown = 1
        }
        return battleUnit
    }

    fun accelerateAttackCooldown(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 >= battleUnit.armedHero.maxHp * (11 - lv)) {

            battleUnit.accelerateAttackCooldown = 1
        }
        return battleUnit
    }

    fun antiEffectiveAgainst(battleUnit: BattleUnit, type: EffectiveAgainst): BattleUnit {
        if (battleUnit.effectiveAgainst == type) {
            battleUnit.effectiveAgainst = EffectiveAgainst.NONE
        }
        return battleUnit

    }


    fun antiAccelerateCooldown(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 >= battleUnit.armedHero.maxHp * (11 - lv)) {
            enemy.InflictCooldown = 1
        }
        return battleUnit
    }

    fun attackPain(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
        enemy.hpLossAtEndOfFight += lv
        return battleUnit
    }

    fun attackHpLoss(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.hpLossAtEndOfFight += lv
        return battleUnit
    }

    fun attackHeal(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.hpLossAtEndOfFight -= lv
        return battleUnit
    }

    fun brazenAtk(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 <= battleUnit.armedHero.maxHp * 8) {
            battleUnit.atkEffect += lv
        }
        return battleUnit
    }

    fun brazenSpd(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 <= battleUnit.armedHero.maxHp * 8) {
            battleUnit.spdEffect += lv
        }
        return battleUnit
    }

    fun brazenDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 <= battleUnit.armedHero.maxHp * 8) {
            battleUnit.defEffect += lv
        }
        return battleUnit
    }

    fun brazenRes(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 <= battleUnit.armedHero.maxHp * 8) {
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun wrath(battleUnit: BattleUnit, damage: Int, percentile: Int): Int = if (battleUnit.hp * 100 <= battleUnit.armedHero.maxHp * percentile) damage + 10 else damage

}