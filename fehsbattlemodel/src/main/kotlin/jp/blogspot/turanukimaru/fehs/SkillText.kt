package jp.blogspot.turanukimaru.fehs

/**
 * スキル効果。これ時間かかるし台湾語は流石に書けないな…追撃・追撃不可のデータ対応先にするべきかなあ
 */
enum class SkillBaseText(val jp: String, val us: String) {
    TriangleAdept("相性効果 + ", ""),
    Breaker(" : 絶対追撃、相手は追撃不可", ""),
    HpLoss(" : HP減少 -", ""),
    heavyBlade(" : カウント減少 + 1", ""),
    DistantDef(" : 守備, 魔防 + ", ""),
    CloseCounter(" : 反撃可能", ""),
    DeathBlow(" : 攻撃 + ", ""),
    DartingBlow("", ""),
    ArmoredBlow("", ""),
    WardingBlow("", ""),
    SwiftSparrow("", ""),
    SturdyBlow("", ""),
    MirrorStrike("", ""),
    SteadyBlow("", ""),
    SwiftStrike("", ""),
    BracingBlow("", ""),
    SteadyStance("", ""),
    FierceBreath("", ""),
    AtkSpdBond("", ""),
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
    fun localeName(l: Locale): String =
            when (l) {
                Locale.JAPANESE -> jp
//                Locale.TAIWAN -> tw
                else -> us
            }

    override fun toString(): String = jp
}

data class SkillText(val name: String, val skill: SkillBaseText, var preText: PlusText = PlusText("")) {
    fun toText(l: Locale): String = "Get" + preText.text + skill + "with" + name
    fun append(text: String) {
        preText = preText.append(text)
    }
}

data class PlusText(val text: String) {
    fun append(text: String) = PlusText(this.text + text)
}