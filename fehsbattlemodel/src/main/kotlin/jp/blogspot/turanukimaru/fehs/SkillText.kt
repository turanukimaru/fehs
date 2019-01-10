package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.fehs.skill.Skill

/**
 * スキル効果。これ時間かかるし台湾語は流石に書けないな…追撃・追撃不可のデータ対応先にするべきかなあ
 */
enum class SkillBaseText(val jp: String, val us: String) {
    NONE("", ""),
    //まずは条件ではなく効果だけあればいいか
    TriangleAdept("相性効果 + ", "gain weapon advantage + "),
    AntiTriangleAdept("相性効果反転", " reverses weapon advantage "),
    Breaker("絶対追撃、相手は追撃不可", "makes follow-up attack and prevent foe's follow-up attack"),
    Cancel("相手はカウント減少 -", "inflicts foe's cooldown charge"),
    AntiEffectiveAgainst("特効無効化", "neutralizes effective against bonus"),
    EffectiveAgainst("特効", "gets effective against bonus"),
    DoubleAttack("二回攻撃", "attacks twice"),
    FollowupAttack("追撃", "make follow-up attack"),
    Sweep("追撃不可、相手は反撃不能", "make cannot follow-up attack and foe cannot counter attack"),
    FireSweep("どちらも反撃不能", "make cannot each counter attack"),
    NeutralizeBuffBonus("相手の強化を無効化", "neutralizes foe's bonuses "),
    Blade("攻撃に + を加算", ""),
    Dazzling("相手は反撃不能", ""),
    WrathfulStaff("杖も通常のダメージ", ""),
    NoFollowupAttackEach("どちらも追撃不可", ""),
    AntiFollowupAttack("相手は追撃不可", ""),
    HpLoss("戦闘後HP減少 -", ""),
    HpGain("戦闘後HP回復 +", ""),
    Pain("相手は戦闘後HP減少", ""),
    HeavyBlade("攻撃時カウント減少 + ", ""),
    HeavyPlate("カウント減少 + ", ""),
    DistantDef("遠距離防御時守備, 魔防 + ", ""),
    CounterAllRange("距離に関係なく反撃", ""),
    DisableChangePlan("", ""), Damage("ダメージ + ", ""),
    Atk("攻撃 + ", ""),
    Spd("速さ + ", ""),
    Def("守備 + ", ""),
    Res("魔防 + ", ""),
    AtkSpd("攻撃速さ + ", ""),
    AtkDef("攻撃守備 + ", ""),
    AtkRes("攻撃魔防 + ", ""),
    SpdDef("速さ守備 + ", ""),
    SpdRes("速さ魔防 + ", ""),
    DefRes("守備魔防 + ", ""),
    AtkMinus("相手の攻撃 - ", ""),
    AtkSpdDefRes("攻撃速さ守備魔防 + ", ""),
    AntiAtkSpdDefRes("相手の攻撃速さ守備魔防 - ", ""),
    SteadyStance4("攻撃対象時に相手の奥義カウント－１", ""),
    Breath("攻撃対象時に奥義カウント + １", ""),
    //    FierceBreath("", ""),
    Blow("攻撃時", ""),
    Stance("攻撃対象時", ""),
    Bond("味方隣接時", ""),

    Brazen("戦闘開始時HP80%以下", ""),
    Solo("味方非隣接時に", ""),
    Boost("相手よりHPが3以上大きいときに", ""),
    SorceryBlade("魔法の仲間が隣接しているときに", ""),
    Penetrate("守備と魔防の低いほうを適用", ""),
    AntiPenetrate("守備と魔防の低いほう適用を無効", ""),
    Shield("特効無効", ""),
    Sword("", ""),
    Wary("", ""),
    Fighter("", ""),
    Bold("", ""),
    Vengeful("", ""),
    Special("", ""),
    Desperation("攻撃の後に追撃を行う", ""),
    QuickRiposte("絶対追撃", ""),
//    BrashAssault    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
//    ("",""),
    ;

    /**
     * ロケールから対応する文字列を出す
     */
    fun localeText(l: Locale): String =
            when (l) {
                Locale.JAPANESE -> jp
//                Locale.TAIWAN -> tw
                else -> us
            }
}

class SkillText(val name: Skill, val text: SkillBaseText, var value: String = "", var next: SkillText? = null) {
    private fun localeText(l: Locale): String = when (l) {
        Locale.JAPANESE -> text.localeText(l) + value + (if (next != null) " & " else "") + (next?.localeText(l) ?: "")
        else -> text.localeText(l) + value + (if (next != null) " & " else "") + (next?.localeText(l))
    }

    fun toText(l: Locale): String = when (l) {
        Locale.JAPANESE -> localeText(l) + (if (name != Skill.NONE) " with " + name.localeName(l) else "") + ". "
        else ->  localeText(l) + (if (name != Skill.NONE) " with " + name.localeName(l) else "") + ". "
    }

    fun add(next: SkillText) {
        if (this.next == next || next.next != null) throw RuntimeException("同じSkillTextを追加しようとしています$name/$text")
        if (this.next != null) this.next?.add(next) else this.next = next
    }
}