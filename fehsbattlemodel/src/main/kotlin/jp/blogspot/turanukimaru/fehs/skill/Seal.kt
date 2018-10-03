package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.*

/**
 * 聖印
 */
enum class Seal(override val jp: Name, override val type: SkillType =  SkillType.SEAL, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val maxLevel: Int = 3, override val spType: SpType = SpType.BASE50) : Skill {

    Hp(Name.Hp) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    HpRes(Name.HpRes, maxLevel = 2, spType = SpType.BASE100) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(equipRes(armedHero, lv), lv + 2)
    },
    Attack(Name.Attack) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, lv)
    },
    AtkDef(Name.AtkDef, SkillType.A, maxLevel = 2, spType = SpType.BASE80) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(armedHero, lv), lv)
    },
    Speed(Name.Speed) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, lv)
    },
    Defense(Name.Defense) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, lv)
    },
    Resistance(Name.Resistance) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, lv)
    },
    SquadAceA(Name.SquadAceA) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    SquadAceB(Name.SquadAceB) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipDef(armedHero, lv)
    },
    SquadAceC(Name.SquadAceC) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipRes(armedHero, lv)
    },
    SquadAceD(Name.SquadAceD) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipSpd(armedHero, lv)
    },
    SquadAceE(Name.SquadAceC) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(armedHero, lv)
    },
    SquadAceF(Name.SquadAceC) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipHp(armedHero, lv + 2)
    },
    CloseDef(Name.CloseDef, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = closeDef(battleUnit, enemy, lv * 2)
    },
    DistantDef(Name.DistantDef, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = distantDef(battleUnit, enemy, lv * 2)
    },
    FortressDef(Name.FortressDef, spType = SpType.BASE40) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipDef(armedHero, lv + 2), -3)
    },
    FortressRes(Name.FortressRes, spType = SpType.BASE40) {
        override fun equip(armedHero: ArmedHero, lv: Int): ArmedHero = equipAtk(equipRes(armedHero, lv + 2), -3)
    },
    BrashAssault(Name.BrashAssault) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = brashAssault(battleUnit, enemy, lv)
    },
    HardyBearing(Name.HardyBearing) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.disableChangePlan = true
            if (battleUnit.hp >= battleUnit.armedHero.maxHp * (150 - lv * 50) / 100) {
                enemy.disableChangePlan = true
            }
            return super.fightEffect(battleUnit, enemy, lv)
        }

    },
    HeavyBlade(Name.BrashAssault, SkillType.B, spType = SpType.BASE60) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = heavyBlade(battleUnit, enemy, lv)
    },
    FlashingBlade(Name.FlashingBlade, spType = SpType.BASE60) {
        override fun effectedAttackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 7 - lv * 2)
        override fun effectedCounterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = flashingBlade(battleUnit, enemy, 7 - lv * 2)
    },
    PhantomSpeed(Name.PhantomSpeed) {
        override fun fightEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit {
            battleUnit.phantomSpeed = when (lv) {1 -> 5
                2 -> 8
                3 -> 10
                else -> 0
            }
            return super.fightEffect(battleUnit, enemy, lv)
        }
    },
    PoisonStrike(Name.PoisonStrike, spType = SpType.BASE60) {
        override fun attackEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = attackPain(battleUnit, enemy, lv * 3 + 1)
    },

    QuickRiposte(Name.QuickRiposte, spType = SpType.BASE60) {
        override fun counterEffect(battleUnit: BattleUnit, enemy: BattleUnit, lv: Int): BattleUnit = followupable(battleUnit, lv)
    },

    /**
     * 連撃防御。武器の種類はなんか定数に定数を持たせるべきか…
     */
    DeflectMagic(Name.DeflectMagic) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int = if (results.isNotEmpty() && results.last().side != battleUnit.side && source.armedHero.isMagicWeapon()) damage - damage * when (lv) {
            1 -> 3
            2 -> 5
            3 -> 8
            else -> 0
        } / 10 else damage
    },

    DeflectMelee(Name.DeflectMelee) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int = if (results.isNotEmpty() && results.last().side != battleUnit.side && source.armedHero.isMagicWeapon()) damage - damage * when (lv) {
            1 -> 3
            2 -> 5
            3 -> 8
            else -> 0
        } / 10 else damage
    },
    DeflectMissile(Name.DeflectMissile) {
        override fun prevent(battleUnit: BattleUnit, damage: Int, source: BattleUnit, results: List<AttackResult>, lv: Int): Int = if (results.isNotEmpty() && results.last().side != battleUnit.side && source.armedHero.isMagicWeapon()) damage - damage * when (lv) {
            1 -> 3
            2 -> 5
            3 -> 8
            else -> 0
        } / 10 else damage
    },
    ArmoredBoots(Name.ArmoredBoots, maxLevel = 0, spType = SpType.STEEL),
    BreathOfLife(Name.BreathOfLife),
    FlierFormation(Name.FlierFormation, spType = SpType.BASE60),
    Guidance(Name.Guidance, spType = SpType.BASE60),
    IotesShield(Name.IotesShield, spType = SpType.SHIELD),
    LiveToServe(Name.LiveToServe, spType = SpType.BASE40),
    Obstruct(Name.Obstruct),
    QuickenedPulse(Name.QuickenedPulse, maxLevel = 0),
    SavageBlow(Name.SavageBlow),

    BlazeDance(Name.BlazeDance),
//    GaleDance(Name.GaleDance),
    EarthDance(Name.EarthDance),
//    TorrentDance(Name.TorrentDance),
    SpurAtk(Name.SpurAtk),
    SpurSpd(Name.SpurSpd),
    SpurDef(Name.SpurDef),
    SpurRes(Name.SpurRes),
    PanicPloy(Name.PanicPloy, spType = SpType.BASE60),
    AtkPloy(Name.AtkPloy, spType = SpType.BASE60),
    SpdPloy(Name.SpdPloy, spType = SpType.BASE60),
    DefPloy(Name.DefPloy, spType = SpType.BASE60),
    ResPloy(Name.ResPloy, spType = SpType.BASE60),
    DriveAtk(Name.DriveAtk, maxLevel = 2, spType = SpType.BASE60),
    DriveDef(Name.DriveDef, maxLevel = 2, spType = SpType.BASE60),
    DriveSpd(Name.DriveSpd, maxLevel = 2, spType = SpType.BASE60),
//    DriveRes(Name.DriveRes, maxLevel = 2, spType = SpType.BASE60),
    DefTactic(Name.DefTactic, spType = SpType.BASE60),
    ResTactic(Name.ResTactic, spType = SpType.BASE60),
    AttackSmoke(Name.AtkSmoke, spType = SpType.BASE60),
    SpdSmoke(Name.SpdSmoke, spType = SpType.BASE60),
    FortifyDef(Name.FortifyDef),
    FortifyRes(Name.FortifyRes),
    HoneAtk(Name.HoneAtk),
    HoneSpd(Name.HoneSpd),
    SealAtk(Name.SealAtk, spType = SpType.BASE40),
    SealSpd(Name.SealSpd, spType = SpType.BASE40),
    ThreatenAtk(Name.ThreatenAtk),
    ThreatenSpd(Name.ThreatenSpd),
    ThreatenDef(Name.ThreatenDef),
//    ThreatenRes(Name.ThreatenRes),

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

    companion object {
        fun spreadItems(none: Boolean = false): List<Skill> = values().fold(if (none) arrayListOf<Skill>(Skill.NONE) else arrayListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else (1..e.maxLevel).forEach { i -> list.add(e.lv(i)) };list
        }
        fun spreadMaxLvItems(none: Boolean = false): List<Skill> = values().fold(if (none) arrayListOf<Skill>(Skill.NONE) else arrayListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else list.add(e.lv(e.maxLevel));list
        }

        private val itemMap = mutableMapOf<String, Seal>()

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