package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.fehs.skill.RefineSkill
import jp.blogspot.turanukimaru.fehs.skill.Skill
import java.util.*

/**
 * Created by turanukimaru on 2017/11/13.
 */
data class ArmedHero(
        val baseHero: BaseHero,
        val name: String = "",
        var baseWeapon: Skill = Skill.NONE,
        var refinedWeapon: Skill = Skill.NONE,
        var assist: Skill = Skill.NONE,
        var special: Skill = Skill.NONE,
        var aSkill: Skill = Skill.NONE,
        var bSkill: Skill = Skill.NONE,
        var cSkill: Skill = Skill.NONE,
        var seal: Skill = Skill.NONE,
        var rarity: Int = 5,
        var levelBoost: Int = 0,
        var boon: BoonType = BoonType.NONE,
        var bane: BoonType = BoonType.NONE,
        val defensiveTerrain: Boolean = false,
        var atkBuff: Int = 0,
        var spdBuff: Int = 0,
        var defBuff: Int = 0,
        var resBuff: Int = 0,
        var atkSpur: Int = 0,
        var spdSpur: Int = 0,
        var defSpur: Int = 0,
        var resSpur: Int = 0
) {
    val weapon get()= if (refinedWeapon != Skill.NONE) RefineSkill.valueOfWeapon(baseWeapon) ?: baseWeapon else baseWeapon
    /**
     * スキルのリスト。戦闘時などにすべてのスキルをなめるのに使う。読み取り専用プロパティにすることで毎回その時のプロパティからリストを作れるはず
     * 個体が編集されているときは編集後のスキルを使う
     */
    val skills get() = listOfNotNull(weapon, refinedWeapon, assist, special, aSkill, bSkill, cSkill, seal)

    /**
     * 移動力。直接見てもいいか？いやアイテムやスキルの効果で変動するか。
     */
    val movableSteps: Int
        get() = baseHero.movableSteps

    /**
     * 攻撃可能範囲。直接見てもいいか？今のところは変動しないし。
     */
    val effectiveRange: Int
        get() = baseHero.effectiveRange

    /**
     * killer系武器での加速
     */
    var reduceSpecialCooldown = 0

    /**
     * 能力値。下にあるのは計算用
     */
    val maxHp: Int get() = boonedHp + hpEqp + hpBoost + growths[rarity - 1][baseHero.hpGrowth + boonHp]
    val atk: Int get() = boonedAtk + atkEqp + atkBoost + growths[rarity - 1][baseHero.atkGrowth + boonAtk]
    val spd: Int get() = boonedSpd + spdEqp + spdBoost + growths[rarity - 1][baseHero.spdGrowth + boonSpd]
    val def: Int get() = boonedDef + defEqp + defBoost + growths[rarity - 1][baseHero.defGrowth + boonDef]
    val res: Int get() = boonedRes + resEqp + resBoost + growths[rarity - 1][baseHero.resGrowth + boonRes]

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
    val boonHp
        get() = when {boon == BoonType.HP -> 1; bane == BoonType.HP -> -1;else -> 0
        }
    val boonAtk
        get() = when {boon == BoonType.ATK -> 1; bane == BoonType.ATK -> -1;else -> 0
        }
    val boonSpd
        get() = when {boon == BoonType.SPD -> 1; bane == BoonType.SPD -> -1;else -> 0
        }
    val boonDef
        get() = when {boon == BoonType.DEF -> 1; bane == BoonType.DEF -> -1;else -> 0
        }
    val boonRes
        get() = when {boon == BoonType.RES -> 1; bane == BoonType.RES -> -1;else -> 0
        }

    val boonedHp
        get() = baseHero.hitPoint + boonHp
    val boonedAtk
        get() = baseHero.attack + boonAtk
    val boonedSpd
        get() = baseHero.speed + boonSpd
    val boonedDef
        get() = baseHero.defense + boonDef
    val boonedRes
        get() = baseHero.resistance + boonRes
    /**
     * 得意不得意の能力値。最大が0なのはダミーデータ。アーダンの能力値↑は設定限界を超えるため
     */
    val growths = baseHero.growths
    val specialCoolDownTime: Int get() = special.level - reduceSpecialCooldown
    val statusText: String get() = "H%2sA%2sS%2sD%2sR%2s".format(maxHp, atk, spd, def, res)

    init {
        //名前が無いときは変更なしとして扱い、ベースになるクラスの装備を使う
        if (name.isEmpty()) {
            baseWeapon = baseHero.weapon
            assist = baseHero.assist
            special = baseHero.special
            aSkill = baseHero.aSkill
            bSkill = baseHero.bSkill
            cSkill = baseHero.cSkill
        }

        equip()
    }

    override fun toString(): String = "$name MaxHP:$maxHp , totalAtk:$atk , spd:$spd , def:$def , res:$res ,weapon:$weapon, refinedWeapon:$refinedWeapon, assist:$assist, special:$special, skillA,$aSkill, skillB:$bSkill, skillC:$cSkill, seal:$seal, hpEqp:$hpEqp , atkEqp:$atkEqp , spdEqp:$spdEqp , defEqp:$defEqp , resEqp:$resEqp"

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
        return baseHero.weaponType.isMaterial
    }

    /**
     * 攻撃が魔法か。魔法特効って杖にも効くのかな？
     */
    fun isMagicWeapon(): Boolean = baseHero.isMagicWeapon()

    /**
     * 攻撃が魔法か。魔法特効って杖にも効くのかな？
     */
    fun isMeleeWeapon(): Boolean = baseHero.isMeleeWeapon()

    /**
     * 攻撃が魔法か。魔法特効って杖にも効くのかな？
     */
    fun isMissileWeapon(): Boolean = baseHero.isMissileWeapon()

    fun have(weaponType: WeaponType?, moveType: MoveType?): Boolean = (weaponType == null || baseHero.weaponType == weaponType) && (moveType == null || baseHero.moveType == moveType)

    private fun lvUpStatus() {
        hpBoost = 0
        atkBoost = 0
        spdBoost = 0
        defBoost = 0
        resBoost = 0
        if (rarity < 5) {
            val sortedRarityBonus = listOf(Pair(baseHero.attack + 0.4f, BoonType.ATK), Pair(baseHero.speed + 0.3f, BoonType.SPD), Pair(baseHero.defense + 0.2f, BoonType.DEF), Pair(baseHero.resistance + 0.1f, BoonType.RES)).sortedBy { pair -> -pair.first }.plus(Pair(baseHero.hitPoint + 0.5f, BoonType.HP))
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
        val priority = listOf(Pair(baseHero.hitPoint + boonHp + 0.5f, BoonType.HP), Pair(baseHero.attack + boonAtk + 0.4f, BoonType.ATK), Pair(baseHero.speed + boonSpd + 0.3f, BoonType.SPD), Pair(baseHero.defense + boonDef + 0.2f, BoonType.DEF), Pair(baseHero.resistance + boonRes + 0.1f, BoonType.RES)).sortedBy { pair -> -pair.first }
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

    fun equip() {
        hpEqp = 0
        atkEqp = 0
        spdEqp = 0
        defEqp = 0
        resEqp = 0
        reduceSpecialCooldown = 0//ビルドが失敗してこの行だけ反映されないことがある。デバッガで確認すると０が入らないので加速する一方…
        lvUpStatus()
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
        (0 until 5 - rarity).fold(if (baseHero.weaponType == WeaponType.STAFF) Skill.NONE else weapon, { w, _ -> w.preSkill }).equip(this)

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
            BattleParam(growths[rarity - 1][baseHero.hpGrowth + 1] + growths[rarity - 1][baseHero.hpGrowth - 1] - growths[rarity - 1][baseHero.hpGrowth] * 2,
                    growths[rarity - 1][baseHero.atkGrowth + 1] + growths[rarity - 1][baseHero.atkGrowth - 1] - growths[rarity - 1][baseHero.atkGrowth] * 2,
                    growths[rarity - 1][baseHero.spdGrowth + 1] + growths[rarity - 1][baseHero.spdGrowth - 1] - growths[rarity - 1][baseHero.spdGrowth] * 2,
                    growths[rarity - 1][baseHero.defGrowth + 1] + growths[rarity - 1][baseHero.defGrowth - 1] - growths[rarity - 1][baseHero.defGrowth] * 2,
                    growths[rarity - 1][baseHero.resGrowth + 1] + growths[rarity - 1][baseHero.resGrowth - 1] - growths[rarity - 1][baseHero.resGrowth] * 2)

    fun localeName(locale: Locale): String {
        if (name.isNotEmpty()) return name
        return when (locale) {
            Locale.JAPAN -> baseHero.name
            Locale.JAPANESE -> baseHero.name
            else -> baseHero.usName
        }

    }

    /**
     * 簡易ステータス。ロケールの扱いは本当に困るな
     */
    fun statusSkillText(locale: Locale) = "HP:%2sA:%2sS:%2sD:%2sR%2sW:%8s".format(maxHp, atk, spd, def, res, skills.fold("", { string, e -> string + e.localeName(locale) + " " }))

}