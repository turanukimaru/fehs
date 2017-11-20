package jp.blogspot.turanukimaru.fehs

import java.util.*

class LappedSkill(val skill: Skill, override val level: Int, val containSkill: Skill? = null) : Skill by skill {


    //enum側に持たせたし不要かなあ
    companion object {
        val NONE = LappedSkill(Skill.NONE, 0)
        fun nameOf(weapon: String): Skill {
//            val name = weapon.mat
            return LappedSkill(Skills.valueOf(weapon), 0)
        }
    }

    override fun localeName(locale: Locale): String {
        return when (locale){
            Locale.JAPAN ->skill.jp + if (level > 0) " " + level else ""
            Locale.JAPANESE ->skill.jp + if (level > 0) " " + level else ""
            else->skill.value + if (level > 0) " " + level else ""
        }
    }

    /**
     * スキル名
     */
    override val value: String get() = skill.value + if (level > 0) " " + level else ""

    /**
     * スキル名＋あればレベル
     */
    override fun toString(): String {
        return value
    }

    /**
     * スキルとレベルが等しいときはequal.Skills側でガードはしているがついで。
     */
    override fun equals(other: Any?): Boolean = when (other) {
        is Skills -> other == skill && other.level == level
        is LappedSkill -> other.skill == skill && other.level == level
        else -> false
    }
}