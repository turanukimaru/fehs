package jp.blogspot.turanukimaru.fehs

/**
 * 戦闘中効果。永続化の対象ではないので集約の一部として管理する
 */
data class BattleEffect(
        var atkEffect: Int = 0
        , var spdEffect: Int = 0
        , var defEffect: Int = 0
        , var resEffect: Int = 0
        , var side: SIDES = SIDES.NONE
        , var phantomSpeed: Int = 0
        , var adjacentUnits: Int = 0
        , var buffDebuffTrigger: Boolean = false
        //スキルの効果
        /**
         * 攻撃時スペシャル変動量＋
         */
        , var accelerateAttackCooldown: Int = 0
        /**
         * ダメージを受けたときのスペシャル変動量＋
         */
        , var accelerateTargetCooldown: Int = 0
        /**
         * スペシャル変動量-
         */
        , var InflictAttackCooldown: Int = 0
        , var InflictTargetCooldown: Int = 0

        /**
         * 速さを無視して追撃可能か
         */
        , var followupable: Int = 0
        /**
         * 速さを無視して追撃不可能か
         */
        , var antiFollowup: Int = 0
        /**
         * 勇者武器などで2回攻撃可能か
         */
        , var doubleAttack: Boolean = false
        /**
         * 無色に対する相性補正があるか
         */
        , var colorlessAdvantage: Boolean = false
        /**
         * 激化による相性計算。倍率
         */
        , var colorAdvantageLevel: Int = 0
        /**
         * 激化無効化レベル 普通/無効/反転/1/0/-1
         */
        , var antiColorAdvantage: Int = 1
        /**
         * 特効
         */
        , var effectiveAgainst: EffectiveAgainst = EffectiveAgainst.NONE
        /**
         * 距離に関係なく反撃できるか
         */
        , var counterAllRange: Boolean = false
        /**
         * 火凪などで反撃が封じられているか
         */
        , var cannotCounter: Boolean = false
        /**
         * buffが無効化されているか
         */
        , var neutralizeBuffBonus: Boolean = false
        /**
         * debuffが無効化されているか
         */
        , var neutralizePenalties: Boolean = false
        /**
         * 戦闘後のHP減少
         */
        , var hpLossAtEndOfFight: Int = 0
        /**
         * ブレードか
         */
        , var blade: Boolean = false
        /**
         * 防御地形か
         */
        , var defensiveTerrain: Boolean = false
        /**
         * 攻撃順変更スキルが無効か
         */
        , var disableChangePlan: Boolean = false
        /**
         * 神罰が発動しているか
         */
        , var wrathfulStaff: Boolean = false
        /**
         * ダメージを追加する。武器やスキルに分散させたので使わなくていいはず。あとで消そう。
         */
        , var additionalDamage: Int = 0
        /**
         * 一回だけダメージを追加する。今のところ氷の聖鏡専用。
         */
        , var oneTimeOnlyAdditionalDamage: Int = 0
        /**
         * debuffで強化されるダメージ量。今のところブリザード専用
         */
        , var debuffBonus: Int = 0
        /**
         * 攻撃を実行したか。攻撃時にダメージを受ける効果用
         */
        , var attacked: Boolean = false
        /**
         * 戦闘後のHP減少
         */
        , var attackedHpLossAtEndOfFight: Int = 0
        /**
         * ダメージタイプ置き換え…これブレスとかどうすりゃいいんだ？判定を戻して戦闘時効果で置き換えるか。
         */
        , var overrideDamageType: SkillType = SkillType.NONE
        /**
         * バフのダメージ倍率…
         */
        , var bonusPow: Int = 100
        /**
         * 開始時のHP.ティルフィングでしか使ってないのでとりあえず必要な時だけセットする…
         */
        , var startHp: Int = 0
        , val activatedSkills: MutableList<SkillText> = mutableListOf()//ひょっとしてこれコピーされてるのか

) {

    fun addSkillText(skillText: SkillText): BattleEffect {
        if (activatedSkills.size > 0 && activatedSkills.last().name == skillText.name) activatedSkills.last().add(skillText) else activatedSkills.add(skillText)
        return this
    }
}