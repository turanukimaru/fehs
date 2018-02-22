package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.AttackResult
import jp.blogspot.turanukimaru.fehs.BattleUnit
import jp.blogspot.turanukimaru.fehs.SkillType

/**
 * これテスト死ぬほど大変だな…
 * Created by turanukimaru on 2018/02/20.
 */
class Damage(val source: BattleUnit, val special: Skill, val weaponType: SkillType, val flat: Int, val staff: Int, val results: List<AttackResult>) {
    val deal: (BattleUnit) -> Pair<Int, Skill?> = { target ->
        target.preventBySkill(special.damage(source, target.preventByDefResTerrain(weaponType, special.penetrate)) * staff + flat, results)
    }

}

//等価
fun damageCodeBlock(source: BattleUnit, special: Skill, weaponType: SkillType, flat: Int, staff: Int, results: List<AttackResult>): (BattleUnit) -> Pair<Int, Skill?> = { target ->
    target.preventBySkill(special.damage(source, target.preventByDefResTerrain(weaponType, special.penetrate)) * staff + flat, results)
}
