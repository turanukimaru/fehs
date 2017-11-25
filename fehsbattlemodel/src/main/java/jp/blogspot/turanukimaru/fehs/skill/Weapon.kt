package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*
import java.util.*

/**
 * スキル。武器/補助/奥義
 */
enum class Weapon(override val jp: String, override val type: Skill.SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE) : Skill {


    IronSword("鉄の剣", Skill.SkillType.SWORD, 6),
    SteelSword("鋼の剣", Skill.SkillType.SWORD, 8, IronSword),
    SilverSword("銀の剣", Skill.SkillType.SWORD, 11, SteelSword),
    SilverSword2("銀の剣＋", Skill.SkillType.SWORD, 15, SilverSword),
    ArmorSlayer("アーマーキラー", Skill.SkillType.SWORD, 8, SteelSword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.ARMORED, battleUnit)
    },
    ArmorSlayer2("アーマーキラー＋", Skill.SkillType.SWORD, 12, ArmorSlayer) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.ARMORED, battleUnit)
    },
    BraveSword("勇者の剣", Skill.SkillType.SWORD, 5, SteelSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveSword2("勇者の剣＋", Skill.SkillType.SWORD, 8, BraveSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    RubySword("旭日の剣", Skill.SkillType.SWORD, 8, SteelSword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, 3)
    },
    RubySword2("旭日の剣＋", Skill.SkillType.SWORD, 12, RubySword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, 3)
    },
    KillingEdge("キルソード", Skill.SkillType.SWORD, 7, SteelSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    KillingEdge2("キルソード＋", Skill.SkillType.SWORD, 11, KillingEdge) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    WaoDao("倭刀", Skill.SkillType.SWORD, 9, SteelSword) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int, lv: Int): Int = damage + 10
    },
    WaoDao2("倭刀＋", Skill.SkillType.SWORD, 13, WaoDao) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int, lv: Int): Int = damage + 10
    },
    Zanbato("斬馬刀", Skill.SkillType.SWORD, 10, SteelSword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Zanbato2("斬馬刀＋", Skill.SkillType.SWORD, 14, Zanbato) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    SlayingEdge("キルソード鍛", Skill.SkillType.SWORD, 10, SteelSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
    },
    SlayingEdge2("キルソード鍛＋", Skill.SkillType.SWORD, 14, SlayingEdge) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
    },
    AyrasBlade("瞬閃アイラの剣", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipSpd(armedClass, 3), lv)
        override fun effectedAttackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, 3)
        override fun effectedCounterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, 3)
    },
    Folkvangr("フォルクヴァング", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun turnStart(battleUnit: BattleUnit, lv: Int): BattleUnit = defiantAtk(battleUnit, 2)
    },
    Falchion("ファルシオン", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, battleUnit)
    },
    BindingBlade("封印の剣", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowDef(blowRes(battleUnit, 2), 2)
    },
    Durandal("デュランダル", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowAtk(battleUnit, 4)
    },
    SolKatti("ソール・カティ", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun attackPlan(fightPlan: FightPlan, lv: Int): FightPlan = desperation(fightPlan, 2)
    },
    Yato("夜刀神", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowSpd(battleUnit, 4)
    },
    Raijinto("雷神刀", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Sieglinde("ジークリンデ", Skill.SkillType.SWORD, 16, SilverSword),
    Tyrfing("ティルフィング", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit {
            if (battleUnit.hp <= battleUnit.armedClass.maxHp / 2) {
                battleUnit.defEffect += 4
            }
            return battleUnit
        }
    },
    Mystletainn("ミストルティン", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    Eckesachs("エッケザックス", Skill.SkillType.SWORD, 16, SilverSword),
    Siegfried("ジークフリート", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Ragnell("ラグネル", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    BlazingDurandal("烈剣デュランダル", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipAtk(armedClass, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, 3)
    },
    Amiti("アミーテ", Skill.SkillType.SWORD, 11, SilverSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(equipSpd(armedClass, 3), lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Alondite("エタルド", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    DivineTyrfing("聖剣ティルフィング", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipRes(armedClass, 3), lv)
        override fun prevent(battleUnit: BattleUnit, damage: Int, results: List<AttackResult>, lv: Int): Int {
            return if (results.isEmpty() && battleUnit.enemy!!.armedClass.effectiveRange == 2 && battleUnit.enemy!!.armedClass.isMagicWeapon()) damage - damage / 2 else damage

        }
    },
    RegalBlade("リガルブレイド", Skill.SkillType.SWORD, 16, SilverSword) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = enemyFullHpBonus(battleUnit, lv * 2)
    },
    ResoluteBlade("気鋭ワユの剣", Skill.SkillType.SWORD, 16, WaoDao) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipAtk(armedClass, 3), lv)
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int, lv: Int): Int = damage + 10
    },

    IronLance("鉄の槍", Skill.SkillType.LANCE, 6),
    SteelLance("鋼の槍", Skill.SkillType.LANCE, 8, IronLance),
    SilverLance("銀の槍", Skill.SkillType.LANCE, 11, SteelLance),
    SilverLance2("銀の槍＋", Skill.SkillType.LANCE, 15, SilverLance),
    KillerLance("キラーランス", Skill.SkillType.LANCE, 7, SteelLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    KillerLance2("キラーランス＋", Skill.SkillType.LANCE, 11, KillerLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    BraveLance("勇者の槍", Skill.SkillType.LANCE, 5, SteelLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveLance2("勇者の槍＋", Skill.SkillType.LANCE, 8, BraveLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    SapphireLance("蒼海の槍", Skill.SkillType.LANCE, 8, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, 3)
    },
    SapphireLance2("蒼海の槍＋", Skill.SkillType.LANCE, 12, SapphireLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, 3)
    },
    HeavySpear("貫きの槍", Skill.SkillType.LANCE, 8, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.ARMORED, battleUnit)
    },
    HeavySpear2("貫きの槍＋", Skill.SkillType.LANCE, 12, HeavySpear) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.ARMORED, battleUnit)
    },

    CarrotLance("ニンジンの槍", Skill.SkillType.LANCE, 9, SteelLance) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotLance2("ニンジンの槍＋", Skill.SkillType.LANCE, 13, CarrotLance) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    FirstBite("ファーストバイト", Skill.SkillType.LANCE, 10, SteelLance),
    FirstBite2("ファーストバイト＋", Skill.SkillType.LANCE, 14, FirstBite),
    FiresweepLance("火薙ぎの槍", Skill.SkillType.LANCE, 11, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, 0)
    },
    FiresweepLance2("火薙ぎの槍＋", Skill.SkillType.LANCE, 15, FiresweepLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = disableEachCounter(battleUnit, 0)
    },
    DeftHarpoon("魚を突いた銛", Skill.SkillType.LANCE, 10, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    DeftHarpoon2("魚を突いた銛＋", Skill.SkillType.LANCE, 14, DeftHarpoon) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    Ridersbane("ホースキラー", Skill.SkillType.LANCE, 10, SteelLance) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Ridersbane2("ホースキラー＋", Skill.SkillType.LANCE, 14, Ridersbane) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    BerkutsLance("ベルクトの槍", Skill.SkillType.LANCE, 10, SteelLance) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },
    BerkutsLance2("ベルクトの槍＋", Skill.SkillType.LANCE, 14, BerkutsLance) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = blowRes(battleUnit, 4)
    },
    SlayingLance("キラーランス鍛", Skill.SkillType.LANCE, 10, SteelLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    SlayingLance2("キラーランス鍛＋", Skill.SkillType.LANCE, 14, SlayingLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    Fensalir("フェンサリル", Skill.SkillType.LANCE, 16, SilverLance),
    Siegmund("ジークムント", Skill.SkillType.LANCE, 16, SilverLance),
    Gradivus("グラディウス", Skill.SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    Vidofinir("ヴィドフニル", Skill.SkillType.LANCE, 16, SilverLance) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit {
            val weapon = battleUnit.enemy!!.armedClass.battleClass.weaponType
            if (weapon == WeaponType.SWORD || weapon == WeaponType.LANCE || weapon == WeaponType.AXE) {
                battleUnit.defEffect += 7
            }
            return battleUnit
        }
    },
    CursedLance("魔性の槍", Skill.SkillType.LANCE, 16, SilverLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(equipAtk(equipSpd(armedClass, 2), 2), lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHpLoss(battleUnit, 4)
    },
    Geirskogul("ゲイルスケグル", Skill.SkillType.LANCE, 16, SilverLance) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipDef(armedClass, 3), lv)
    },


    IronAxe("鉄の斧", Skill.SkillType.AXE, 6),
    SteelAxe("鋼の斧", Skill.SkillType.AXE, 8, IronAxe),
    SilverAxe("銀の斧", Skill.SkillType.AXE, 11, SteelAxe),
    SilverAxe2("銀の斧＋", Skill.SkillType.AXE, 15, SilverAxe),
    KillerAxe("キラーアクス", Skill.SkillType.AXE, 7, SteelAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    KillerAxe2("キラーアクス＋", Skill.SkillType.AXE, 11, KillerAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    BraveAxe("勇者の斧", Skill.SkillType.AXE, 5, SteelAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    BraveAxe2("勇者の斧＋", Skill.SkillType.AXE, 8, BraveAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },
    Hammer("ハンマー", Skill.SkillType.AXE, 8, SteelAxe) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.ARMORED, battleUnit)
    },
    Hammer2("ハンマー＋", Skill.SkillType.AXE, 12, Hammer) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.ARMORED, battleUnit)
    },
    EmeraldAxe("深緑の斧", Skill.SkillType.AXE, 8, SteelAxe) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, 3)
    },
    EmeraldAxe2("深緑の斧＋", Skill.SkillType.AXE, 12, EmeraldAxe) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = colorAdvantage(battleUnit, 3)
    },
    SlayingAxe("キラーアクス鍛", Skill.SkillType.AXE, 10, SteelAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    SlayingAxe2("キラーアクス鍛", Skill.SkillType.AXE, 14, SlayingAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },

    CarrotAxe("ニンジンの斧", Skill.SkillType.AXE, 9, SteelAxe) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    CarrotAxe2("ニンジンの斧＋", Skill.SkillType.AXE, 13, CarrotAxe) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    LegionsAxe("ローローの斧", Skill.SkillType.AXE, 10, SteelAxe),
    LegionsAxe2("ローローの斧＋", Skill.SkillType.AXE, 14, LegionsAxe),
    MelonCrusher("スイカ割りの棍棒", Skill.SkillType.AXE, 10, SteelAxe) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    MelonCrusher2("スイカ割りの棍棒＋", Skill.SkillType.AXE, 14, MelonCrusher) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    LilithFloatie("リリスの浮き輪", Skill.SkillType.AXE, 10, SteelAxe),
    LilithFloatie2("リリスの浮き輪＋", Skill.SkillType.AXE, 14, LilithFloatie),
    Noatun("ノーアトゥーン", Skill.SkillType.AXE, 16, SilverAxe),
    Hauteclere("オートクレール", Skill.SkillType.AXE, 16, SilverAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    Armoads("アルマーズ", Skill.SkillType.AXE, 16, SilverAxe) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, lv)
    },

    Urvan("ウルヴァン", Skill.SkillType.AXE, 16, SilverAxe) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
        override fun prevent(battleUnit: BattleUnit, damage: Int, results: List<AttackResult>, lv: Int): Int =
                if (results.isNotEmpty() && results.last().side != battleUnit.side) damage - damage * 8 / 10 else damage
    },
    Uror("ウルズ", Skill.SkillType.AXE, 16, SilverAxe),
    StoutTomahawk("剛斧トマホーク", Skill.SkillType.AXE, 16, SilverAxe) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },


    IronBow("鉄の弓", Skill.SkillType.BOW, 4),
    SteelBow("鋼の弓", Skill.SkillType.BOW, 6, IronBow),
    SilverBow("銀の弓", Skill.SkillType.BOW, 9, SteelBow),
    SilverBow2("銀の弓＋", Skill.SkillType.BOW, 13, SilverBow),
    BraveBow("勇者の弓", Skill.SkillType.BOW, 4, SteelBow) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.attackEffect(doubleAttack(battleUnit), lv)
    },
    BraveBow2("勇者の弓＋", Skill.SkillType.BOW, 7, BraveBow) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.attackEffect(doubleAttack(battleUnit), lv)
    },
    KillerBow("キラーボウ", Skill.SkillType.BOW, 5, SteelBow) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    KillerBow2("キラーボウ＋", Skill.SkillType.BOW, 9, KillerBow) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    AssassinsBow("暗器殺しの弓", Skill.SkillType.BOW, 7, SteelBow) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.bothEffect(weaponBreaker(battleUnit, WeaponType.DAGGER, lv), lv)
    },
    AssassinsBow2("暗器殺しの弓＋", Skill.SkillType.BOW, 11, AssassinsBow) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.bothEffect(weaponBreaker(battleUnit, WeaponType.DAGGER, lv), lv)
    },
    FiresweepBow("火薙ぎの弓", Skill.SkillType.BOW, 7, SteelBow) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.bothEffect(disableEachCounter(battleUnit, 0), lv)
    },
    FiresweepBow2("火薙ぎの弓＋", Skill.SkillType.BOW, 11, FiresweepBow) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.attackEffect(disableEachCounter(battleUnit, 0), lv)
    },
    CupidArrow("キューピッドの矢", Skill.SkillType.BOW, 8, SteelBow),
    CupidArrow2("キューピッドの矢＋", Skill.SkillType.BOW, 12, CupidArrow),
    RefreshingBolt("氷菓子の弓", Skill.SkillType.BOW, 8, SteelBow) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.attackEffect(fullHpBonus(battleUnit, 2), lv)
    },
    RefreshingBolt2("氷菓子の弓＋", Skill.SkillType.BOW, 12, RefreshingBolt) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.attackEffect(fullHpBonus(battleUnit, 2), lv)
    },
    ClarissesBow("クライネの弓", Skill.SkillType.BOW, 7, SteelBow),
    ClarissesBow2("クライネの弓＋", Skill.SkillType.BOW, 11, ClarissesBow),
    SlayingBow("キラーボウ鍛", Skill.SkillType.BOW, 10, SteelBow) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    SlayingBow2("キラーボウ鍛＋", Skill.SkillType.BOW, 12) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipKiller(armedClass, lv)
    },
    Parthia("パルティア", Skill.SkillType.BOW, 14, SilverBow) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.attackEffect(blowRes(battleUnit, 2), lv)
    },
    FujinYumi("風神弓", Skill.SkillType.BOW, 14, SilverBow),
    Nidhogg("ニーズヘッグ", Skill.SkillType.BOW, 14, SilverBow),
    Mulagir("ミュルグレ", Skill.SkillType.BOW, 14, SilverBow) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipDef(armedClass, 3), lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = super.bothEffect(antiBuffBonus(battleUnit), lv)
    },
    MonstrousBow("怪物の弓", Skill.SkillType.BOW, 8, SteelBow),
    MonstrousBow2("怪物の弓+", Skill.SkillType.BOW, 12, MonstrousBow),


    IronDagger("鉄の暗器", Skill.SkillType.DAGGER, 3),
    SteelDagger("鋼の暗器", Skill.SkillType.DAGGER, 5, IronDagger),
    SilverDagger("銀の暗器", Skill.SkillType.DAGGER, 7, SteelDagger),
    SilverDagger2("銀の暗器＋", Skill.SkillType.DAGGER, 10, SilverDagger),
    RogueDagger("盗賊の暗器", Skill.SkillType.DAGGER, 4, SteelDagger),
    RogueDagger2("盗賊の暗器＋", Skill.SkillType.DAGGER, 7, RogueDagger),
    SmokeDagger("紫煙の暗器", Skill.SkillType.DAGGER, 6, SteelDagger),
    SmokeDagger2("紫煙の暗器＋", Skill.SkillType.DAGGER, 9, SmokeDagger),
    PoisonDagger("秘毒の暗器", Skill.SkillType.DAGGER, 2, SteelDagger) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.INFANTRY, battleUnit)
    },
    PoisonDagger2("秘毒の暗器＋", Skill.SkillType.DAGGER, 5, PoisonDagger) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.INFANTRY, battleUnit)
    },
    Seashell("貝殻", Skill.SkillType.DAGGER, 7, SteelDagger) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    Seashell2("貝殻＋", Skill.SkillType.DAGGER, 10, Seashell) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpBonus(battleUnit, 2)
    },
    DancersFan("舞踏祭の扇子", Skill.SkillType.DAGGER, 7, SteelDagger),
    DancersFan2("舞踏祭の扇子＋", Skill.SkillType.DAGGER, 10, DancersFan),
    DeathlyDagger("死神の暗器", Skill.SkillType.DAGGER, 11, SilverDagger) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, 7)
    },
    KittyPaddle("猫の暗器", Skill.SkillType.DAGGER, 5, SteelDagger) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainstMagic(battleUnit)
    },
    KittyPaddle2("猫の暗器＋", Skill.SkillType.DAGGER, 8, KittyPaddle) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainstMagic(battleUnit)
    },

    Assault("アサルト", Skill.SkillType.STAFF, 10),
    Pain("ペイン", Skill.SkillType.STAFF, 3) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, 10)
    },
    Absorb("アブゾーブ", Skill.SkillType.STAFF, 4) {
        override fun absorb(battleUnit: BattleUnit, target: BattleUnit, damage: Int): Int {
            battleUnit.heal(damage * 5 / 10)
            return damage
        }
    },
    Slow("スロウ", Skill.SkillType.STAFF, 5),
    Fear("フィアー", Skill.SkillType.STAFF, 5),
    Panic("パニック", Skill.SkillType.STAFF, 6),
    Gravity("グラビティ", Skill.SkillType.STAFF, 7),
    Candlelight("キャンドルサービス", Skill.SkillType.STAFF, 7),

    Flux("ミィル", Skill.SkillType.RTOME, 4),
    Fire("ファイアー", Skill.SkillType.RTOME, 4),
    Ruin("ルイン", Skill.SkillType.RTOME, 6),
    Elfire("エルファイアー", Skill.SkillType.RTOME, 6),
    Rauorwolf("ラウアウルフ", Skill.SkillType.RTOME, 6) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Rauorwolf2("ラウアウルフ＋", Skill.SkillType.RTOME, 10) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Rauorraven("ラウアレイヴン", Skill.SkillType.RTOME, 7) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Rauorraven2("ラウアレイヴン＋", Skill.SkillType.RTOME, 11) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Rauorblade("ラウアブレード", Skill.SkillType.RTOME, 9) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Rauorblade2("ラウアブレード＋", Skill.SkillType.RTOME, 13) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Rauorowl("ラウアアウル", Skill.SkillType.RTOME, 6),
    Rauorowl2("ラウアアウル＋", Skill.SkillType.RTOME, 10),
    Fenrir("ノスフェラート", Skill.SkillType.RTOME, 9),
    Fenrir2("ノスフェラート＋", Skill.SkillType.RTOME, 14),
    Bolganone("ボルガノン", Skill.SkillType.RTOME, 9),
    Bolganone2("ボルガノン＋", Skill.SkillType.RTOME, 14),
    TomatoTome("トマトの本", Skill.SkillType.RTOME, 8),
    TomatoTome2("トマトの本＋", Skill.SkillType.RTOME, 12),
    Brynhildr("ブリュンヒルデ", Skill.SkillType.RTOME, 14),
    Cymbeline("シムベリン", Skill.SkillType.RTOME, 14),
    Ragnarok("ライナロック", Skill.SkillType.RTOME, 14) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = fullHpAtkSpdBonus(battleUnit, 5)
    },
    Valflame("ファラフレイム", Skill.SkillType.RTOME, 14),
    Grimoire("グリモワール", Skill.SkillType.RTOME, 14, Bolganone),


    Wind("ウィンド", Skill.SkillType.GTOME, 4),
    Elwind("エルウィンド", Skill.SkillType.GTOME, 6),
    Gronnwolf("グルンウルフ", Skill.SkillType.GTOME, 6) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Gronnwolf2("グルンウルフ＋", Skill.SkillType.GTOME, 10) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Gronnraven("グルンレイヴン", Skill.SkillType.GTOME, 7) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Gronnraven2("グルンレイヴン＋", Skill.SkillType.GTOME, 11) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Gronnblade("グルンブレード", Skill.SkillType.GTOME, 9) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Gronnblade2("グルンブレード＋", Skill.SkillType.GTOME, 13) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Gronnowl("グルンアウル", Skill.SkillType.GTOME, 6),
    Gronnowl2("グルンアウル＋", Skill.SkillType.GTOME, 10),
    Rexcalibur("レクスカリバー", Skill.SkillType.GTOME, 9),
    Rexcalibur2("レクスカリバー＋", Skill.SkillType.GTOME, 14),
    GreenEgg("緑の卵", Skill.SkillType.GTOME, 7) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    GreenEgg2("緑の卵＋", Skill.SkillType.GTOME, 11) {
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = attackHeal(battleUnit, 4)
    },
    HibiscusTome("ハイビスカスの本", Skill.SkillType.GTOME, 8),
    HibiscusTome2("ハイビスカスの本＋", Skill.SkillType.GTOME, 12),
    DancersRing("舞踏祭の輪", Skill.SkillType.GTOME, 8),
    DancersRing2("舞踏祭の輪＋", Skill.SkillType.GTOME, 12),
    Elivagar("エリヴァーガル", Skill.SkillType.GTOME, 14),
    Excalibur("エクスカリバー", Skill.SkillType.GTOME, 14) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.FLIER, battleUnit)
    },
    Naga("ナーガ", Skill.SkillType.GTOME, 14) {
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, blowDef(blowRes(battleUnit, 2), 2))
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(WeaponType.DRAGON, battleUnit)
    },
    DarkExcalibur("共鳴エクスカリバー", Skill.SkillType.GTOME, 14) {
        override fun specialTriggered(battleUnit: BattleUnit, damage: Int, lv: Int): Int = damage + 10
    },
    DivineNaga("聖書ナーガ", Skill.SkillType.GTOME, 14) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = antiBuffBonus(effectiveAgainst(WeaponType.DRAGON, battleUnit))
    },
    SpectralTome("ゴーストの魔導書", Skill.SkillType.GTOME, 14),
    SpectralTome2("ゴーストの魔導書+", Skill.SkillType.GTOME, 14, SpectralTome),

    Thunder("サンダー", Skill.SkillType.BTOME, 4),
    Elthunder("エルサンダー", Skill.SkillType.BTOME, 6),
    Blarwolf("ブラーウルフ", Skill.SkillType.BTOME, 6) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Blarwolf2("ブラーウルフ＋", Skill.SkillType.BTOME, 10) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = effectiveAgainst(MoveType.CAVALRY, battleUnit)
    },
    Blarraven("ブラーレイヴン", Skill.SkillType.BTOME, 7) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Blarraven2("ブラーレイヴン＋", Skill.SkillType.BTOME, 11) {
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = equipRaven(battleUnit)
    },
    Blarblade("ブラーブレード", Skill.SkillType.RTOME, 9) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Blarblade2("ブラーブレード＋", Skill.SkillType.RTOME, 13) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun bothEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = bladeEffect(battleUnit)
    },
    Blarowl("ブラーアウル", Skill.SkillType.BTOME, 6),
    Blarowl2("ブラーアウル＋", Skill.SkillType.BTOME, 10),
    Thoron("トロン", Skill.SkillType.BTOME, 9),
    Thoron2("トロン＋", Skill.SkillType.BTOME, 13),
    BlueEgg("青の卵", Skill.SkillType.BTOME, 7),
    BlueEgg2("青の卵＋", Skill.SkillType.BTOME, 11),
    BlessedBouquet("聖なるブーケ", Skill.SkillType.BTOME, 9),
    BlessedBouquet2("聖なるブーケ＋", Skill.SkillType.BTOME, 12),
    SealifeTome("熱帯魚の本", Skill.SkillType.BTOME, 8),
    SealifeTome2("熱帯魚の本＋", Skill.SkillType.BTOME, 12),
    DancersScore("舞踏祭の楽譜", Skill.SkillType.BTOME, 8),
    DancersScore2("舞踏祭の楽譜＋", Skill.SkillType.BTOME, 12),
    DarkAura("共鳴オーラ", Skill.SkillType.BTOME, 14),
    Valaskjalf("ヴラスキャルヴ", Skill.SkillType.BTOME, 14) {
        override fun counterPlan(fightPlan: FightPlan, lv: Int): FightPlan = vantage(fightPlan, 2)
    },
    Aura("オーラ", Skill.SkillType.BTOME, 14),
    DireThunder("ダイムサンダ", Skill.SkillType.BTOME, 9) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBrave(armedClass, lv)
        override fun attackEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = doubleAttack(battleUnit)
    },

    WeirdingTome("奇異ルーテの書", Skill.SkillType.BTOME, 14, Thoron) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = super.equip(equipSpd(armedClass, 3), lv)
    },

    FireBreath("火のブレス", Skill.SkillType.DRAGON, 6),
    FireBreath2("火炎のブレス", Skill.SkillType.DRAGON, 8),
    LightningBreath("雷のブレス", Skill.SkillType.DRAGON, 8) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },
    LightningBreath2("雷のブレス＋", Skill.SkillType.DRAGON, 11) {
        override fun equip(armedClass: ArmedClass, lv: Int): ArmedClass = equipBlade(armedClass, lv)
        override fun counterEffect(battleUnit: BattleUnit, lv: Int): BattleUnit = counterAllRange(battleUnit)
    },

    Flametongue("灼熱のブレス", Skill.SkillType.DRAGON, 11),
    Flametongue2("灼熱のブレス＋", Skill.SkillType.DRAGON, 15),
    LightBreath("光のブレス", Skill.SkillType.DRAGON, 9),
    LightBreath2("光のブレス＋", Skill.SkillType.DRAGON, 13),
    DarkBreath("闇のブレス", Skill.SkillType.DRAGON, 9),
    DarkBreath2("闇のブレス＋", Skill.SkillType.DRAGON, 13),

    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name.replace("2", "+")

    override fun localeName(locale: Locale): String {
        return when (locale) {
            Locale.JAPAN -> jp
            Locale.JAPANESE -> jp
            else -> value
        }
    }

    companion object {
        val itemMap = mutableMapOf<String, Skill>()
        fun spreadItems(): List<Skill> = values().fold(arrayListOf<Skill>(Skill.NONE), { list, e -> list.add(e);list })

        fun valueOfOrNONE(key: String?): Skill = if (key == null) Skill.NONE else try {
            if (itemMap.isEmpty()) {
                values().forEach { e -> itemMap.put(e.jp, e) }
                values().forEach { e -> itemMap.put(e.value, e) }
            }
            itemMap[key] ?: valueOf(key)
        } catch (e: Exception) {
            Skill.NONE
        }
    }
}