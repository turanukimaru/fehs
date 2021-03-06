package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.fehs.skill.Skill

/**
 * 戦闘結果
 */
data class AttackResult(val source: BattleUnit, val target: BattleUnit, val damage: Int, val overkill: Int?, val sourceSpecial: Skill?, val targetSpecial: Skill?, val side: SIDES = SIDES.ATTACKER) {
    /**
     * 攻撃側と防御側を入れ替える。戦闘の途中経過を表示するなら攻撃を仕掛けた側を固定するほうが扱いやすかった…
     */
    fun flip(): AttackResult = copy(source = target, target = source, side = SIDES.COUNTER)

    /**
     * やり取りのメッセージ化...本来はこれは画面側に回すべき
     */
    fun details(): String =
            when (side) {
                SIDES.ATTACKER -> "${source.armedHero.name} attacks $damage buildDamage"
                else -> "${target.armedHero.name} attacks $damage buildDamage"
            } + if (sourceSpecial != null || targetSpecial != null) " wakeup ${sourceSpecial?.value
                    ?: ""} ${targetSpecial?.value ?: ""}" else ""

    /**
     * やり取りの文字列表現
     */
    fun detailsShort(baseSide: SIDES, locale: Locale): String =
            when (baseSide) {
                side -> "↓$damage "
                else -> "↑$damage "
            } + sourceSpecialFire(locale) + targetSpecialFire(locale) + (if (overkill != null) " over $overkill" else "")

    private fun sourceSpecialFire(locale: Locale) = (if (sourceSpecial != null && sourceSpecial != Skill.NONE) "☆${sourceSpecial.localeName(locale)} " else "")
    private fun targetSpecialFire(locale: Locale) = (if (targetSpecial != null && targetSpecial != Skill.NONE) "☆${targetSpecial.localeName(locale)} " else "")
}