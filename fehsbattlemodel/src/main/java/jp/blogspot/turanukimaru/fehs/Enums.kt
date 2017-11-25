package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.fehs.skill.Skill
import java.util.*

/**
 * Created by turanukimaru on 2017/11/15.
 */

/**
 * 武器種類
 */
enum class WeaponType(val range: Int, val isMaterial: Boolean, val skillType: Skill.SkillType, val sortOrder: Int) {
    SWORD(1, true, Skill.SkillType.SWORD, 0),
    LANCE(1, true, Skill.SkillType.LANCE, 0),
    AXE(1, true, Skill.SkillType.AXE, 0),
    DRAGON(1, false, Skill.SkillType.DRAGON, 1),
    RTOME(2, false, Skill.SkillType.RTOME, 2),
    BTOME(2, false, Skill.SkillType.BTOME, 2),
    GTOME(2, false, Skill.SkillType.GTOME, 2),
    BOW(2, true, Skill.SkillType.BOW, 3),

    DAGGER(2, true, Skill.SkillType.DAGGER, 4),
    STAFF(2, false, Skill.SkillType.STAFF, 5)
    ;

    companion object {
        val weaponTypeMap = mapOf("剣" to WeaponType.SWORD, "槍" to WeaponType.LANCE, "斧" to WeaponType.AXE, "弓" to WeaponType.BOW, "暗器" to WeaponType.DAGGER, "赤魔" to WeaponType.RTOME, "緑魔" to WeaponType.GTOME, "青魔" to WeaponType.BTOME, "杖" to WeaponType.STAFF,"竜" to DRAGON)
        /**
         * 日本語の武器名を変換する。ここにあるべきかは疑問だが将来画面とのやり取り以外にも使うかもしれない
         */
        fun weaponTypeOf(key: String) = when {
            weaponTypeMap.containsKey(key) -> weaponTypeMap[key]
            WeaponType.values().any { e -> e.name == key } -> WeaponType.valueOf(key)
            else -> null
        }

    }

}

/**
 * 移動タイプ
 */
enum class MoveType(val steps: Int) {
    INFANTRY(2),
    CAVALRY(3),
    FLIER(2),
    ARMORED(1)
    ;

    companion object {
        val moveTypeMap = mapOf("歩行" to MoveType.INFANTRY,  "飛行" to MoveType.FLIER, "騎馬" to MoveType.CAVALRY, "重装" to MoveType.ARMORED)
        fun moveTypeOf(key: String) = when {
            moveTypeMap.containsKey(key) -> moveTypeMap[key]
            MoveType.values().any { e -> e.name == key } -> MoveType.valueOf(key)
            else -> null
        }

    }
}

enum class BoonType(val jp: String) {
    NONE("-"),
    HP("HP"),
    ATK("攻撃"),
    SPD("速さ"),
    DEF("守備"),
    RES("魔防")
    ;

    fun localeName(locale: Locale): CharSequence {
        return when (locale) {
            Locale.JAPAN -> jp
            Locale.JAPANESE -> jp
            else -> name
        }
    }

    companion object {
        val boonTypeMap = mapOf("HP" to BoonType.HP, "攻撃" to BoonType.ATK, "速さ" to BoonType.SPD, "守備" to BoonType.DEF, "魔防" to BoonType.RES)
        fun boonTypeOf(key: String) = when {
            boonTypeMap.containsKey(key) -> boonTypeMap[key]!!
            BoonType.values().any { e -> e.name == key } -> BoonType.valueOf(key)
            else -> BoonType.NONE
        }
    }

}

/**
 * 戦闘時に攻撃側か受け側か
 */
enum class SIDES {
    NONE,
    ATTACKER,
    COUNTER
}

/**
 * 特効の種類。移動か武器かどちらかだったら楽だったのに
 */
enum class EffectiveAgainst {
    NONE,
    ARMORED,
    FLIER,
    MAGIC,
    CAVALRY,
    DRAGON
    ;

    companion object {
        fun valueOfMoveType(moveType: MoveType): EffectiveAgainst = when (moveType) {
            MoveType.ARMORED -> ARMORED
            MoveType.FLIER -> FLIER
            MoveType.CAVALRY -> CAVALRY
            else -> NONE
        }

        fun valueOfWeaponType(weaponType: WeaponType): EffectiveAgainst = when (weaponType) {
            WeaponType.RTOME -> MAGIC
            WeaponType.GTOME -> MAGIC
            WeaponType.BTOME -> MAGIC
            WeaponType.DRAGON -> DRAGON
            else -> NONE
        }
    }
}
