package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.Name
import jp.blogspot.turanukimaru.fehs.SkillType
import jp.blogspot.turanukimaru.fehs.SpType


/**
 * スキル。補助
 */
enum class Assist(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.NONE, override val spType: SpType = SpType.ASSIST) : Skill {


    RallyAttack(Name.RallyAttack, SkillType.ASSIST),
    RallySpeed(Name.RallySpeed, SkillType.ASSIST),
    RallyDefense(Name.RallyDefense, SkillType.ASSIST),
    RallyResistance(Name.RallyResistance, SkillType.ASSIST),
    RallyAtkSpd(Name.RallyAtkSpd, SkillType.ASSIST, spType = SpType.ASSIST2),
    RallyAtkDef(Name.RallyAtkDef, SkillType.ASSIST, spType = SpType.ASSIST2),
    RallyAtkRes(Name.RallyAtkRes, SkillType.ASSIST, spType = SpType.ASSIST2),
    RallyDefRes(Name.RallyDefRes, SkillType.ASSIST, spType = SpType.ASSIST2),
    RallySpdDef(Name.RallySpdDef, SkillType.ASSIST, spType = SpType.ASSIST2),
    RallySpdRes(Name.RallySpdRes, SkillType.ASSIST, spType = SpType.ASSIST2),
    Sing(Name.Sing, SkillType.ASSIST),
    Dance(Name.Dance, SkillType.ASSIST),
    ReciprocalAid(Name.ReciprocalAid, SkillType.ASSIST),
    ArdentSacrifice(Name.ArdentSacrifice, SkillType.ASSIST),
    Swap(Name.Swap, SkillType.ASSIST),
    Pivot(Name.Pivot, SkillType.ASSIST),
    Reposition(Name.Reposition, SkillType.ASSIST),
    DrawBack(Name.DrawBack, SkillType.ASSIST),
    Sacrifice(Name.Shove, SkillType.ASSIST),
    Shove(Name.Shove, SkillType.ASSIST),
    Smite(Name.Smite, SkillType.ASSIST),
    HarshCommand(Name.HarshCommand, SkillType.ASSIST),
    FutureVision(Name.FutureVision, SkillType.ASSIST, spType = SpType.LEGEND),
    //杖の＋どうするかな。実装のメリットあんましないよなあ.でもSP計算に要るのか…
    Heal(Name.Heal, SkillType.ASSIST),
    Reconcile(Name.Reconcile, SkillType.ASSIST),
    Rehabilitate(Name.Rehabilitate, SkillType.ASSIST),
    Rehabilitate2(Name.Rehabilitate2, SkillType.ASSIST),
    Mend(Name.Mend, SkillType.ASSIST),
    Recover(Name.Recover, SkillType.ASSIST),
    Recover2(Name.Recover2, SkillType.ASSIST),
    Restore(Name.Restore, SkillType.ASSIST),
    Restore2(Name.Restore2, SkillType.ASSIST),
    Physic(Name.Physic, SkillType.ASSIST),
    Physic2(Name.Physic2, SkillType.ASSIST),
    Martyr(Name.Martyr, SkillType.ASSIST),
    Martyr2(Name.Martyr2, SkillType.ASSIST),
    ;

    /**
     * nameは誤動作するので共通処理としてはvalueを使う。もっといい名前があるか？
     * なお2を＋に置き換える。
     */
    override val value get() = name.replace("2", "+")

    //  override fun localeName(locale: Locale): String =jp.localeName(locale)

    companion object {
        private val itemMap = mutableMapOf<String, Skill>()
        fun spreadItems(none: Boolean = false): List<Skill> = values().fold(if (none) arrayListOf<Skill>(Skill.NONE) else arrayListOf()) { list, e -> list.add(e);list }

        fun valueOfOrNONE(key: String?): Skill = if (key == null) Skill.NONE else try {
            if (itemMap.isEmpty()) {
                values().forEach { e -> itemMap[e.value] = e;itemMap[e.jp.jp] = e;itemMap[e.jp.us] = e;itemMap[e.jp.tw] = e }
            }
            itemMap[key] ?: valueOf(key)
        } catch (e: Exception) {
            Skill.NONE
        }
    }
}
