package jp.blogspot.turanukimaru.fieldrepos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import jp.blogspot.turanukimaru.fehs.EffectiveAgainst
import jp.blogspot.turanukimaru.fehs.SIDES
import jp.blogspot.turanukimaru.fehs.SkillType

@RealmClass
open class RealmBattleUnit(
        @PrimaryKey
        var id: Int? = null
        , var hp:Int=0
        , var specialCount: Int = 0
        , var atkBuff: Int = 0
        , var spdBuff: Int = 0
        , var defBuff: Int = 0
        , var resBuff: Int = 0
        , var atkDebuff: Int = 0
        , var spdDebuff: Int = 0
        , var defDebuff: Int = 0
        , var resDebuff: Int = 0
        //Effectは紋章やスキルによる強化。これは合計していく.戦闘中と戦闘前って分けたほうが良いかなあ
        , var atkEffect: Int = 0
        , var spdEffect: Int = 0
        , var defEffect: Int = 0
        , var resEffect: Int = 0
        , var side: SIDES = SIDES.NONE
        , var phantomSpeed: Int = 0
        , var adjacentUnits: Int = 0
        , var buffDebuffTrigger: Boolean = false
        //スキルの効果
        , var accelerateAttackCooldown: Int = 0
        , var accelerateTargetCooldown: Int = 0
        , var InflictAttackCooldown: Int = 0
        , var InflictTargetCooldown: Int = 0
        , var followupable: Int = 0
        , var antiFollowup: Int = 0
        , var doubleAttack: Boolean = false
        , var colorlessAdvantage: Boolean = false
        , var colorAdvantageLevel: Int = 0
        , var antiColorAdvantage: Int = 1
        , var effectiveAgainst: EffectiveAgainst = EffectiveAgainst.NONE
        , var counterAllRange: Boolean = false
        , var cannotCounter: Boolean = false
        , var neutralizeBuffBonus: Boolean = false
        , var neutralizePenalties: Boolean = false
        , var hpLossAtEndOfFight: Int = 0
        , var blade: Boolean = false
        , var defensiveTerrain: Boolean = false
        , var disableChangePlan: Boolean = false
        , var wrathfulStaff: Boolean = false
        , var additionalDamage: Int = 0
        , var oneTimeOnlyAdditionalDamage: Int = 0
        , var debuffBonus: Int = 0
        , var attacked: Boolean = false
        , var attackedHpLossAtEndOfFight: Int = 0
        , var overrideDamageType: SkillType = SkillType.NONE
        , var bonusPow: Int = 100

//        , val activatedSkills: MutableList<SkillText> = mutableListOf()//これはゲームとしては要らないはず
        , var realmArmedHero: RealmArmedHero = RealmArmedHero()
): RealmObject()