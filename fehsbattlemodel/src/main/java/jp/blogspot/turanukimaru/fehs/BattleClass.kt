package jp.blogspot.turanukimaru.fehs

import org.omg.PortableInterceptor.NON_EXISTENT
import java.util.*


/**
 * ユニットの持つスキルやレベル・能力値。DBに保存したり、レベルアップや装備の変更をしない限り変わらない部分
 */
class BattleClass(val color: Int = 0, val weaponType: WeaponType = WeaponType.SWORD, val moveType: MoveType = MoveType.INFANTRY, val minRarity: Int = 5, val name: String = "", val usName: String = "", val hitPoint: Int = 0, val attack: Int = 0, val speed: Int = 0, val defense: Int = 0, val resistance: Int = 0, val hpGrowth: Int = 0, val atkGrowth: Int = 0, val spdGrowth: Int = 0, val defGrowth: Int = 0, val resGrowth: Int = 0, var weapon: Skill = Skill.NONE, var assist: Skill = Skill.NONE, var special: Skill = Skill.NONE, var aSkill: Skill = Skill.NONE, var bSkill: Skill = Skill.NONE, var cSkill: Skill = Skill.NONE, var seal: Skill = Skill.NONE, var individuals: Individuals = Individuals()) {

    /**
     * スキルのリスト。戦闘時などにすべてのスキルをなめるのに使う。読み取り専用プロパティにすることで毎回その時のプロパティからリストを作れるはず
     * 個体が編集されているときは編集後のスキルを使う
     */
    val skills get() = if (individuals.name.isEmpty()) listOfNotNull(weapon, assist, special, aSkill, bSkill, cSkill, seal) else individuals.skills

    /**
     * 移動力。直接見てもいいか？いやアイテムやスキルの効果で変動するか。
     */
    val movableSteps: Int
        get() = moveType.steps

    /**
     * 攻撃可能範囲。直接見てもいいか？今のところは変動しないし。
     */
    val effectiveRange: Int
        get() = weaponType.range

    /**
     * killer系武器での加速
     */
    var reduceSpecialCooldown = 0

    /**
     * 能力値。下にあるのは計算用
     */
    val maxHp: Int get() = boonedHp + hpEqp + hpBoost + growths[individuals.rarity - 1][hpGrowth + boonHp]
    val atk: Int get() = boonedAtk + atkEqp + atkBoost + growths[individuals.rarity - 1][atkGrowth + boonAtk]
    val spd: Int get() = boonedSpd + spdEqp + spdBoost + growths[individuals.rarity - 1][spdGrowth + boonSpd]
    val def: Int get() = boonedDef + defEqp + defBoost + growths[individuals.rarity - 1][defGrowth + boonDef]
    val res: Int get() = boonedRes + resEqp + resBoost + growths[individuals.rarity - 1][resGrowth + boonRes]

    var hpEqp: Int = 0
    var atkEqp: Int = 0
    var spdEqp: Int = 0
    var defEqp: Int = 0
    var resEqp: Int = 0

    var hpBoost: Int = 0
    var atkBoost: Int = 0
    var spdBoost: Int = 0
    var defBoost: Int = 0
    var resBoost: Int = 0
    var rarity: Int
        get() = individuals.rarity
        set(value) {
            individuals.rarity = value;lvUpStatus()
        }
    var levelBoost: Int
        get() = individuals.levelBoost
        set(value) {
            individuals.levelBoost = value;lvUpStatus()
        }
    var boon: BoonType
        get() = individuals.boon
        set(value) {
            individuals.boon = value;lvUpStatus()
        }
    var bane: BoonType
        get() = individuals.bane
        set(value) {
            individuals.bane = value;lvUpStatus()
        }
    val boonHp
        get() = when {individuals.boon == BoonType.HP -> 1; individuals.bane == BoonType.HP -> -1;else -> 0
        }
    val boonAtk
        get() = when {individuals.boon == BoonType.ATK -> 1; individuals.bane == BoonType.ATK -> -1;else -> 0
        }
    val boonSpd
        get() = when {individuals.boon == BoonType.SPD -> 1; individuals.bane == BoonType.SPD -> -1;else -> 0
        }
    val boonDef
        get() = when {individuals.boon == BoonType.DEF -> 1; individuals.bane == BoonType.DEF -> -1;else -> 0
        }
    val boonRes
        get() = when {individuals.boon == BoonType.RES -> 1; individuals.bane == BoonType.RES -> -1;else -> 0
        }

    val boonedHp
        get() = hitPoint + boonHp
    val boonedAtk
        get() = attack + boonAtk
    val boonedSpd
        get() = speed + boonSpd
    val boonedDef
        get() = defense + boonDef
    val boonedRes
        get() = resistance + boonRes

    /**
     * 得意不得意の能力値。最大が0なのはダミーデータ。アーダンの能力値↑は設定限界を超えるため
     */
    val growths = listOf(listOf(6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26,0),
            listOf(7, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 26, 28,0),
            listOf(7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29,31,33,0 ),
            listOf(8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 31, 33,35,0),
            listOf(8, 10, 13, 15, 17, 19, 22, 24, 26, 28, 30, 33, 35, 37,0))

    val specialCoolDownTime: Int get() = special.level - reduceSpecialCooldown
    val statusText: String get() = "H%2sA%2sS%2sD%2sR%2s".format(maxHp, atk, spd, def, res)

    init {
        equip()
    }

    override fun toString(): String = "$name MaxHP:$maxHp , atk:$atk , spd:$spd , def:$def , res:$res , hpEqp:$hpEqp , atkEqp:$atkEqp , spdEqp:$spdEqp , defEqp:$defEqp , resEqp:$resEqp"

    /**
     * 戦闘効果。スキルの攻撃効果を再帰でなめて攻撃時効果を計算する。主に能力値変化
     */
    fun bothEffect(battleUnit: BattleUnit): BattleUnit {
        return skills.fold(battleUnit, { b, skill -> skill.bothEffect(b) })
    }

    /**
     * 攻撃側戦闘効果。スキルの攻撃効果を再帰でなめて攻撃時効果を計算する。主に能力値変化
     */
    fun attackEffect(battleUnit: BattleUnit): BattleUnit {
        return skills.fold(battleUnit, { b, skill -> skill.attackEffect(b) })
    }

    /**
     * 受け側戦闘効果。スキルの反撃効果を再帰でなめて受け時効果を計算する。主に能力値変化
     */
    fun counterEffect(battleUnit: BattleUnit): BattleUnit {
        return skills.fold(battleUnit, { b, skill -> skill.counterEffect(b) })
    }

    /**
     * 能力値計算後に適応する必要のある攻撃側戦闘効果。
     */
    fun effectedAttackEffect(battleUnit: BattleUnit): BattleUnit {
        return skills.fold(battleUnit, { b, skill -> skill.effectedAttackEffect(b) })
    }

    /**
     * 能力値計算後に適応する必要のある受け側戦闘効果
     */
    fun effectedCcounterEffect(battleUnit: BattleUnit): BattleUnit {
        return skills.fold(battleUnit, { b, skill -> skill.effectedCounterEffect(b) })
    }

    /**
     * 攻撃側戦闘プラン。スキルの攻撃プランを再帰でなめて攻撃時効果を計算する。主に行動順の制御
     */
    fun attackPlan(fightPlan: FightPlan): FightPlan {
        return skills.fold(fightPlan, { fP, skill -> skill.attackPlan(fP) })
    }

    /**
     * 受け側戦闘プラン。スキルの反撃プランを再帰でなめて受け時効果を計算する。主に行動順の制御
     */
    fun counterPlan(fightPlan: FightPlan): FightPlan {
        return skills.fold(fightPlan, { fP, skill -> skill.counterPlan(fP) })
    }

    /**
     * 攻撃側戦闘効果。スキルの攻撃効果を再帰でなめて攻撃時効果を計算する。主に能力値変化
     */
    fun afterFightEffect(battleUnit: BattleUnit): BattleUnit {
        return skills.fold(battleUnit, { b, skill -> skill.afterFightEffect(b) })
    }


    /**
     * 攻撃が物理か。物理でないなら魔法。武器種類側に持たせるのもあり
     */
    fun isMaterialWeapon(): Boolean {
        return weaponType.isMaterial
    }

    /**
     * 攻撃が魔法か。魔法特効って杖にも効くのかな？
     */
    fun isMagicWeapon(): Boolean {
        return weaponType == WeaponType.RTOME || weaponType == WeaponType.GTOME || weaponType == WeaponType.BTOME// || weaponType == WeaponType.STAFF
    }
    /**
     * 攻撃が魔法か。魔法特効って杖にも効くのかな？
     */
    fun isMeleeWeapon(): Boolean {
        return weaponType == WeaponType.SWORD || weaponType == WeaponType.LANCE || weaponType == WeaponType.AXE
    }
    /**
     * 攻撃が魔法か。魔法特効って杖にも効くのかな？
     */
    fun isMissileWeapon(): Boolean {
        return weaponType == WeaponType.BOW || weaponType == WeaponType.DAGGER
    }

    fun have(weaponType: WeaponType?, moveType: MoveType?): Boolean = (weaponType == null || this.weaponType == weaponType) && (moveType == null || this.moveType == moveType)

    fun clone(): BattleClass = BattleClass(
            color, weaponType, moveType, minRarity, name, usName, hitPoint, attack, speed, defense, resistance, hpGrowth, atkGrowth, spdGrowth, defGrowth, resGrowth, weapon, assist, special, aSkill, bSkill, cSkill, seal, individuals.copy()
    )

    fun lvUpStatus() {
        hpBoost = 0
        atkBoost = 0
        spdBoost = 0
        defBoost = 0
        resBoost = 0
        if (rarity < 5) {
            val sortedRarityBonus = listOf(Pair(attack + 0.4f, BoonType.ATK), Pair(speed + 0.3f, BoonType.SPD), Pair(defense + 0.2f, BoonType.DEF), Pair(resistance + 0.1f, BoonType.RES)).sortedBy { pair -> -pair.first }.plus(Pair(hitPoint + 0.5f, BoonType.HP))
            (0 until (rarity - 1) * 5 / 2).forEach({ i ->
                when (sortedRarityBonus[i % 5].second) {
                    BoonType.HP -> hpBoost++
                    BoonType.ATK -> atkBoost++
                    BoonType.SPD -> spdBoost++
                    BoonType.DEF -> defBoost++
                    BoonType.RES -> resBoost++
                    else -> null
                }
            })
            hpBoost -= 2
            atkBoost -= 2
            spdBoost -= 2
            defBoost -= 2
            resBoost -= 2
        }
        val priority = listOf(Pair(hitPoint + boonHp + 0.5f, BoonType.HP), Pair(attack + boonAtk + 0.4f, BoonType.ATK), Pair(speed + boonSpd + 0.3f, BoonType.SPD), Pair(defense + boonDef + 0.2f, BoonType.DEF), Pair(resistance + boonRes + 0.1f, BoonType.RES)).sortedBy { pair -> -pair.first }
        (0 until levelBoost * 2).forEach({ i ->
            when (priority[i % 5].second) {
                BoonType.HP -> hpBoost++
                BoonType.ATK -> atkBoost++
                BoonType.SPD -> spdBoost++
                BoonType.DEF -> defBoost++
                BoonType.RES -> resBoost++
                else -> null
            }

        })





    }

    fun equip(individuals: Individuals? = null) {
        this.individuals = individuals ?: this.individuals
        hpEqp = 0
        atkEqp = 0
        spdEqp = 0
        defEqp = 0
        resEqp = 0
        reduceSpecialCooldown = 0//ビルドが失敗してこの行だけ反映されないことがある。デバッガで確認すると０が入らないので加速する一方…
        skills.fold(this, { bc, skill -> skill.equip(bc) })
    }

    /**
     *
     */
    fun lv1equip(): BattleParam {
        hpEqp = 0
        atkEqp = 0
        spdEqp = 0
        defEqp = 0
        resEqp = 0
        reduceSpecialCooldown = 0
        //杖は最初武器を装備していない
        (0 until 5 - rarity).fold(if(weaponType == WeaponType.STAFF) Skill.NONE else weapon, { w, _ -> w.preSkill }).equip(this)

        val result = BattleParam(
                boonedHp + hpEqp + hpBoost,
                boonedAtk + atkEqp + atkBoost,
                boonedSpd + spdEqp + spdBoost,
                boonedDef + defEqp + defBoost,
                boonedRes + resEqp + resBoost
        )
        equip()
        return result

    }

    fun goodStatus(): BattleParam =
            BattleParam(growths[individuals.rarity - 1][hpGrowth + 1] + growths[individuals.rarity - 1][hpGrowth - 1] - growths[individuals.rarity - 1][hpGrowth] * 2,
                    growths[individuals.rarity - 1][atkGrowth + 1] + growths[individuals.rarity - 1][atkGrowth - 1] - growths[individuals.rarity - 1][atkGrowth] * 2,
                    growths[individuals.rarity - 1][spdGrowth + 1] + growths[individuals.rarity - 1][spdGrowth - 1] - growths[individuals.rarity - 1][spdGrowth] * 2,
                    growths[individuals.rarity - 1][defGrowth + 1] + growths[individuals.rarity - 1][defGrowth - 1] - growths[individuals.rarity - 1][defGrowth] * 2,
                    growths[individuals.rarity - 1][resGrowth + 1] + growths[individuals.rarity - 1][resGrowth - 1] - growths[individuals.rarity - 1][resGrowth] * 2)

    fun localeName(locale: Locale): String {
        if (individuals.name.isNotEmpty()) return individuals.name
        return when (locale) {
            Locale.JAPAN -> name
            Locale.JAPANESE -> name
            else -> usName
        }

    }

    /**
     * 簡易ステータス。ロケールの扱いは本当に困るな
     */
    fun statusSkillText(locale: Locale) = if (name.isEmpty()) "-" else "HP:%2sA:%2sS:%2sD:%2sR%2sW:%8s".format(maxHp, atk, spd, def, res, skills.fold("", { string, e -> string + e.localeName(locale) + " " }))

}


