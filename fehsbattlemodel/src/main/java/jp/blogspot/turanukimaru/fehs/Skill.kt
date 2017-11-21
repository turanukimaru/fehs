package jp.blogspot.turanukimaru.fehs

import java.util.*

interface Skill {
    val level: Int get() = 0
    val type: Skill.SkillType get() = SkillType.NONE
    val value: String get() = ""
    val jp: String get() = ""
    val maxLevel: Int get() = 0

    // nullオブジェクト。もっといいやり方があればいいのだが
    val preSkill: Skill get() = Skill.NONE

    class None : Skill
    companion object {
        val NONE = None()
    }


    fun localeName(locale: Locale): String = ""

    /**
     * 戦闘時の効果。基本的にunitの能力値を上下したり
     * 弓だけはここで特効のチェックするか…
     */
    fun bothEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        if (type == SkillType.BOW && battleUnit.enemy!!.battleClass.moveType == MoveType.FLIER) battleUnit.effectiveAgainst = EFFECTIVE_AGAINSTS.FLIER
        return battleUnit
    }

    /**
     * 攻撃時の効果。基本的にunitの能力値を上下したり
     */
    fun attackEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        return battleUnit
    }

    /**
     * 反撃時の効果
     */
    fun counterEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        return battleUnit
    }

    /**
     * 攻撃時の効果。基本的にunitの能力値が上下した後参照したいとき
     */
    fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        return battleUnit
    }

    /**
     * 反撃時の効果。基本的にunitの能力値が上下した後参照したいとき
     */
    fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        return battleUnit
    }

    /**
     * 攻撃時の効果。基本的にunitの能力値を上下したり攻撃の順番を変える
     */
    fun attackPlan(fightPlan: FightPlan, lv: Int = level): FightPlan {
        return fightPlan
    }

    /**
     * 反撃時の効果として用意したけどuni内に攻撃側かどうかが入ってるので削除するべきか
     */
    fun counterPlan(fightPlan: FightPlan, lv: Int = level): FightPlan {
        return fightPlan
    }

    /**
     * ダメージ減少。連撃防御
     */
    fun prevent(battleUnit: BattleUnit, damage: Int, results: List<AttackResult>, lv: Int = level): Int {
        return damage
    }

    /**
     * ダメージ減少。奥義や連撃防御.奥義が2回発動するようなことになりませんように・・・
     */
    fun specialPrevent(battleUnit: BattleUnit, damage: Int, lv: Int = level): Pair<Int, Skill?> {
        return Pair(damage, null)
    }

    /**
     * 奥義発動時の追加効果。ほぼダメージ＋１０のスキル用
     */
    fun specialTriggered(battleUnit: BattleUnit, damage: Int, lv: Int = level): Int {
        return damage
    }

    /**
     * ほぼ奥義専用。攻撃時のダメージ計算。デフォルトで奥義なしのダメージ
     */
    fun damage(battleUnit: BattleUnit, target: BattleUnit, results: List<AttackResult>, skill: Skill? = null): Pair<Int, Skill?> {
        val damage = target.preventByDefResTerrain(battleUnit.colorAttack(), battleUnit.battleClass.weaponType)
        return Pair(if (damage > 0) damage else 0, skill)
    }

    fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
        return damage
    }

    /**
     * 装備時の能力値変化
     */
    fun equip(battleClass: BattleClass, lv: Int = level): BattleClass {
        if (type.isWeapon) {
            battleClass.atkEqp += level
        }
        return battleClass
    }

    /**
     * ターン開始時。鼓舞や自己バフ
     */
    fun turnStart(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        return battleUnit
    }

    /**
     * 先頭終了後のダメージとかバフとか。画面にエフェクトを出す処理を追加する必要があるな
     */
    fun afterFightEffect(battleUnit: BattleUnit, lv: Int = level): BattleUnit {
        return battleUnit
    }

    //ここからスキル効果
//   試しに作ったけど〇〇の覚醒ってターン開始時効果だから今は要らなかったんじゃ・・・？
    fun defiantAtk(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.battleClass.maxHp / 2) battleUnit.buffAtk(lv * 2 + 1)
        return battleUnit
    }

    fun defiantSpd(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.battleClass.maxHp / 2) battleUnit.buffSpd(lv * 2 + 1)
        return battleUnit
    }

    fun defiantDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.battleClass.maxHp / 2) battleUnit.buffDef(lv * 2 + 1)
        return battleUnit
    }

    fun defiantRes(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp <= battleUnit.battleClass.maxHp / 2) battleUnit.buffRes(lv * 2 + 1)
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

    fun steadyDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.defEffect += lv
        return battleUnit
    }

    fun steadyRes(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.resEffect += lv
        return battleUnit
    }

    fun steadyAtk(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.atkEffect += lv
        return battleUnit
    }

    fun closeDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.battleClass.weaponType.range == 1) {
            battleUnit.defEffect += lv
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun distantDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.battleClass.weaponType.range == 2) {
            battleUnit.defEffect += lv
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun weaponBreaker(battleUnit: BattleUnit, weapon: WeaponType, lv: Int): BattleUnit {
        log("HP:${battleUnit.hp}")
        log("threashold:${battleUnit.battleClass.maxHp * (lv * 20 - 10) / 100}")
        if ((battleUnit.hp >= battleUnit.battleClass.maxHp * (lv * 20 - 10) / 100) && battleUnit.enemy!!.battleClass.weaponType == weapon) {
            battleUnit.followupable = true
            battleUnit.enemy!!.antiFollowup = true
        }
        return battleUnit
    }

    /**
     *
     */
    fun boostDef(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - battleUnit.enemy!!.hp >= 3) {
            battleUnit.defEffect += lv
        }
        return battleUnit
    }

    fun boostSpd(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - battleUnit.enemy!!.hp >= 3) {
            battleUnit.spdEffect += lv
        }
        return battleUnit
    }

    fun boostAtk(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - battleUnit.enemy!!.hp >= 3) {
            battleUnit.atkEffect += lv
        }
        return battleUnit
    }

    fun boostRes(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp - battleUnit.enemy!!.hp >= 3) {
            battleUnit.resEffect += lv
        }
        return battleUnit
    }

    fun furry(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.atkEqp += lv
        battleClass.spdEqp += lv
        battleClass.defEqp += lv
        battleClass.resEqp += lv
        return battleClass
    }

    fun lifeAndDeath(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.atkEqp += lv
        battleClass.spdEqp += lv
        battleClass.defEqp -= lv
        battleClass.resEqp -= lv
        return battleClass
    }

    fun log(s: Any) {
        println(s)
    }

    fun equipHp(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.hpEqp += lv + 2
        return battleClass
    }

    fun equipAtk(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.atkEqp += lv
        return battleClass
    }

    fun equipKiller(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.atkEqp += lv
        battleClass.reduceSpecialCooldown += 1
        return battleClass
    }

    fun equipSpd(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.spdEqp += lv
        return battleClass
    }

    fun equipDef(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.defEqp += lv
        return battleClass
    }

    fun equipRes(battleClass: BattleClass, lv: Int): BattleClass {
        battleClass.resEqp += lv
        return battleClass
    }

    fun effectiveAgainst(moveType: MoveType, battleUnit: BattleUnit): BattleUnit {
        if (battleUnit.enemy!!.battleClass.moveType == moveType) {
            battleUnit.effectiveAgainst = EFFECTIVE_AGAINSTS.valueOfMoveType(moveType)
        }
        return battleUnit
    }

    fun effectiveAgainst(weaponType: WeaponType, battleUnit: BattleUnit): BattleUnit {
        if (battleUnit.enemy!!.battleClass.weaponType == weaponType) {
            battleUnit.effectiveAgainst = EFFECTIVE_AGAINSTS.valueOfWeaponType(weaponType)
        }
        return battleUnit
    }

    //TODO:種類が１：１でないときの処理を考えないと
    fun effectiveAgainstMagic(battleUnit: BattleUnit): BattleUnit {
        if (battleUnit.enemy!!.battleClass.isMagicWeapon()) {
            battleUnit.effectiveAgainst = EFFECTIVE_AGAINSTS.valueOfWeaponType(battleUnit.enemy!!.battleClass.weaponType)
        }
        return battleUnit
    }

    fun doubleAttack(battleUnit: BattleUnit): BattleUnit {
        battleUnit.doubleAttack = true;return battleUnit
    }

    fun colorAdvantage(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.colorAdvantageLevel = lv
        return battleUnit
    }

    fun vantage(fightPlan: FightPlan, lv: Int): FightPlan {
        if (fightPlan.target.hp <= fightPlan.target.battleClass.maxHp * (25 * lv) / 100) {
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
        if (battleUnit.hp >= battleUnit.battleClass.maxHp * (100 - 10 * lv) / 100) {
            log(battleUnit.battleClass.name + " followapable")
            battleUnit.followupable = true
        }
        return battleUnit
    }

    fun eachNofollowupable(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp >= battleUnit.battleClass.maxHp * (110 - 20 * lv) / 100) {
            log(battleUnit.battleClass.name + " followapable")
            battleUnit.antiFollowup = true
            battleUnit.enemy!!.antiFollowup = true
        }
        return battleUnit
    }

    fun desperation(fightPlan: FightPlan, lv: Int): FightPlan {
//        log(fightPlan.attacker.battleClass.maxHp * (25 * lv) / 100)
//        log(fightPlan.attacker.hitPoint <= fightPlan.attacker.battleClass.maxHp * (25 * lv) / 100)
        if (fightPlan.attacker.hp <= fightPlan.attacker.battleClass.maxHp * (25 * lv) / 100 && fightPlan.plan.contains(fightPlan.secondAttack)) {
//            log("desperation triggered")
            fightPlan.plan.remove(fightPlan.secondAttack)
            fightPlan.plan.add(fightPlan.plan.indexOf(fightPlan.firstAttack) + 1, fightPlan.secondAttack)
        }
        return fightPlan
    }

    fun brashAssault(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if ((battleUnit.battleClass.weaponType.range == battleUnit.enemy!!.battleClass.weaponType.range || battleUnit.enemy!!.counterAllRange)
                && !battleUnit.enemy!!.cannotCcounter
                && (battleUnit.hp <= battleUnit.battleClass.maxHp * (lv * 10 + 20) / 100)) {
            battleUnit.followupable = true
        }
        return battleUnit
    }

    fun windsweep(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.enemy!!.battleClass.weaponType.isMaterial
                && (battleUnit.effectedPhantomSpd - battleUnit.enemy!!.effectedPhantomSpd >= 7 - lv * 2)) {
            battleUnit.enemy!!.cannotCcounter = true
        }
        return battleUnit
    }

    fun watersweep(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (!battleUnit.enemy!!.battleClass.weaponType.isMaterial
                && (battleUnit.effectedPhantomSpd - battleUnit.enemy!!.effectedPhantomSpd >= 7 - lv * 2)) {
            battleUnit.enemy!!.cannotCcounter = true
        }
        return battleUnit
    }

    fun dazzling(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp >= battleUnit.battleClass.maxHp * (150 - lv * 50)) {
            battleUnit.enemy!!.cannotCcounter = true
        }
        return battleUnit
    }

    fun counterAllRange(battleUnit: BattleUnit): BattleUnit {
        battleUnit.counterAllRange = true
        return battleUnit
    }


    fun disableEachCounter(battleUnit: BattleUnit, thisLevel: Int): BattleUnit {
        battleUnit.cannotCcounter = true
        battleUnit.enemy!!.cannotCcounter = true
        return battleUnit
    }

    fun sacasBlessing(battleUnit: BattleUnit, thisLevel: Int): BattleUnit {
        val enemyType = battleUnit.enemy!!.battleClass.weaponType
        if (enemyType == WeaponType.SWORD || enemyType == WeaponType.LANCE || enemyType == WeaponType.AXE) {
            battleUnit.enemy!!.cannotCcounter = true
        }
        return battleUnit
    }

    fun beorcsBlessing(battleUnit: BattleUnit, thisLevel: Int): BattleUnit {
        val enemyType = battleUnit.enemy!!.battleClass.moveType
        if (enemyType == MoveType.CAVALRY || enemyType == MoveType.FLIER) {
            battleUnit.enemy!!.antiBuffBonus = true
        }
        return battleUnit
    }

    fun equip(battleClass: BattleClass, type: SkillType): BattleClass {
        if (type.isWeapon) {
            battleClass.atkEqp += level
        }
        return battleClass
    }

    fun fullHpBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        if (battleUnit.hp == battleUnit.battleClass.maxHp) {
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

    fun enemyFullHpBonus(battleUnit: BattleUnit, i: Int): BattleUnit {
        if (battleUnit.enemy!!.hp == battleUnit.enemy!!.battleClass.maxHp) {
            battleUnit.atkEffect += 2
            battleUnit.spdEffect += 2
        }
        return battleUnit
    }


    fun equipBrave(battleClass: BattleClass, level: Int): BattleClass {
        battleClass.atkEqp += level
        battleClass.spdEqp -= 5
        return battleClass
    }

    fun equipBlade(battleClass: BattleClass, level: Int): BattleClass {
        battleClass.atkEqp += level
        battleClass.reduceSpecialCooldown -= 1
        return battleClass
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
        if (battleUnit.hp == battleUnit.battleClass.maxHp) {
            battleUnit.atkEffect += 5
            battleUnit.spdEffect += 5
            if (battleUnit.side == SIDES.ATTACKER) {
                battleUnit.hpLossAtEndOfFight += 5
            }

        }
        return battleUnit
    }

    fun antiBuffBonus(battleUnit: BattleUnit): BattleUnit {
        battleUnit.enemy!!.antiBuffBonus = true
        return battleUnit
    }

    fun heavyBlade(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.effectedAtk - battleUnit.enemy!!.effectedAtk > lv) {
            battleUnit.accelerateAttackCooldown = 1
        }
        return battleUnit
    }

    fun flashingBlade(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.effectedPhantomSpd - battleUnit.enemy!!.effectedPhantomSpd > lv) {
            battleUnit.accelerateAttackCooldown = 1
        }
        return battleUnit
    }

    fun antiEffectiveAgainst(battleUnit: BattleUnit, type: EFFECTIVE_AGAINSTS): BattleUnit {
        if (battleUnit.effectiveAgainst == type) {
            battleUnit.effectiveAgainst = EFFECTIVE_AGAINSTS.NONE
        }
        return battleUnit

    }


    fun antiAccelerateCooldown(battleUnit: BattleUnit, lv: Int): BattleUnit {
        if (battleUnit.hp * 10 >= battleUnit.battleClass.maxHp * (11-lv)) {
            battleUnit.enemy!!.InflictCooldown = 1
        }
        return battleUnit
    }

    fun attackPain(battleUnit: BattleUnit, lv: Int): BattleUnit {
        battleUnit.enemy!!.hpLossAtEndOfFight += lv
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


    enum class SkillType(val jp: String, val isWeapon: Boolean) {
        NONE("", false),
        A("", false),
        B("", false),
        C("", false),
        SWORD("剣", true),
        LANCE("槍", true),
        AXE("斧", true),
        RTOME("赤魔", true),
        BTOME("青魔", true),
        GTOME("緑魔", true),
        BREATH("竜石", true),
        BOW("弓", true),
        DAGGER("暗器", true),
        STAFF("杖", true),
        ASSIST("", false),
        SPECIAL_A("", false),
        SPECIAL_B("", false),
        SPECIAL_C("", false),
        SPECIAL_D("", false),
        SEAL("", false)

    }
}