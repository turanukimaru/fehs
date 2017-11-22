package jp.blogspot.turanukimaru.fehs

/**
 * Created by turanukimaru on 2017/11/13.
 */
data class Equipment(
        val name: String = "",
        var weapon: Skill = Skill.NONE,
        var assist: Skill = Skill.NONE,
        var special: Skill = Skill.NONE,
        var aSkill: Skill = Skill.NONE,
        var bSkill: Skill = Skill.NONE,
        var cSkill: Skill = Skill.NONE,
        var seal: Skill = Skill.NONE,
        var rarity: Int = 5,
        var levelBoost: Int = 0,
        var boon: BoonType = BoonType.NONE,
        var bane: BoonType = BoonType.NONE,
        val defensiveTerrain: Boolean = false,
        var atkBuff: Int = 0,
        var spdBuff: Int = 0,
        var defBuff: Int = 0,
        var resBuff: Int = 0,
        var atkSpur: Int = 0,
        var spdSpur: Int = 0,
        var defSpur: Int = 0,
        var resSpur: Int = 0
) {

    val skills get() = listOfNotNull(weapon, assist, special, aSkill, bSkill, cSkill, seal)

}