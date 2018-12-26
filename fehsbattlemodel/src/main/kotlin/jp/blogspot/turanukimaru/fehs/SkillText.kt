package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.fehs.skill.Skill

/**
 * スキル効果。これ時間かかるし台湾語は流石に書けないな…追撃・追撃不可のデータ対応先にするべきかなあ
 */
enum class SkillBaseText(val jp: String, val us: String) {
    NONE("", ""),
    //まずは条件ではなく効果だけあればいいか
    TriangleAdept("相性効果 + ", ""),
    Breaker("絶対追撃、相手は追撃不可", ""),
    HpLoss("戦闘後HP減少 -", ""),
    HeavyBlade("カウント減少 + 1", ""),
    DistantDef("遠距離防御時守備, 魔防 + ", ""),
    CloseCounter(" :距離に関係なく 反撃", ""),
    Atk("攻撃 + ", ""),
    Spd("速さ＋", ""),
    Def("守備＋", ""),
    Res("魔防＋", ""),
    AtkSpd("攻撃速さ＋", ""),
    AtkDef("攻撃守備＋", ""),
    AtkRes("攻撃魔防＋", ""),
    SpdDef("速さ守備", ""),
    SpdRes("速さ魔防＋", ""),
    DefRes("守備魔防＋", ""),
    SteadyStance4("攻撃対象時に奥義カウント－１", ""),
    Breath("攻撃対象時に奥義カウント＋１", ""),
    //    FierceBreath("", ""),
    Blow("攻撃時", ""),
    Stance("攻撃対象時", ""),
    Bond("味方隣接時", ""),

    Brazen("戦闘開始時HP80%以下", ""),
    Solo("味方非隣接時に", ""),
    Boost("相手よりHPが3以上大きいときに", ""),
    SorceryBlade("魔法の仲間が隣接しているときに", ""),
    Penetrate("守備と魔防の低いほうを適用", ""),
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

    override fun toString(): String = jp
}

data class SkillText(val name: Skill, val text: SkillBaseText, var value: Int = 0, var next: SkillText? = null) {
    fun toText(l: Locale): String = when (l) {
        Locale.JAPANESE -> "" + text.localeText(l) + value + (next?.toText(l) ?: "") + " with " + name.localeName(l)
        else -> "Get" + text.localeText(l) + value + "with" + name.localeName(l)
    }

    fun append(next: SkillText) {
        if (this.next != null) this.next?.append(next) else this.next = next
    }
}