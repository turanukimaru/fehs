package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.Name
import jp.blogspot.turanukimaru.fehs.SkillType
import jp.blogspot.turanukimaru.fehs.SpType

/**
 * スキル。C
 */
enum class SkillC(override val jp: Name, override val type: SkillType, override val level: Int = 0, override val preSkill: Skill = Skill.Companion.NONE, override val maxLevel: Int = 3, override val spType: SpType = SpType.BASE50) : Skill {


    SpurAtk(Name.SpurAtk, SkillType.C),
    SpurSpd(Name.SpurSpd, SkillType.C),
    SpurDef(Name.SpurDef, SkillType.C),
    SpurRes(Name.SpurRes, SkillType.C),
    SpurDefRes(Name.SpurDefRes, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    SpurSpdDef(Name.SpurSpdDef, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    SpurAtkSpd(Name.SpurAtkSpd, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    SpurAtkDef(Name.SpurAtkDef, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    SpurAtkRes(Name.SpurAtkRes, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    ThreatenAtk(Name.ThreatenAtk, SkillType.C),
    ThreatenSpd(Name.ThreatenSpd, SkillType.C),
    ThreatenDef(Name.ThreatenDef, SkillType.C),
    ThreatenRes(Name.ThreatenRes, SkillType.C),
    FortifyDef(Name.FortifyDef, SkillType.C),
    FortifyRes(Name.FortifyRes, SkillType.C),
    FortifyArmor(Name.FortifyArmor, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    FortifyCavalry(Name.FortifyCavalry, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    FortifyFliers(Name.FortifyFliers, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    FortifyDragons(Name.FortifyDragons, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    HoneAtk(Name.HoneAtk, SkillType.C),
    HoneSpd(Name.HoneSpd, SkillType.C),
    DriveAtk(Name.DriveAtk, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    DriveDef(Name.DriveDef, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    DriveSpd(Name.DriveSpd, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    DriveRes(Name.DriveRes, SkillType.C, maxLevel = 2, spType = SpType.BASE60),
    HoneArmor(Name.HoneArmor, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    HoneCavalry(Name.HoneCavalry, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    HoneDragons(Name.HoneDragons, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    HoneFliers(Name.HoneFliers, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    GoadArmor(Name.GoadArmor, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    GoadCavalry(Name.GoadCavalry, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    GoadDragons(Name.GoadDragons, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    GoadFliers(Name.GoadFliers, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    WardArmor(Name.WardArmor, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    WardCavalry(Name.WardCavalry, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    WardDragons(Name.WardDragons, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    WardFliers(Name.WardFliers, SkillType.C, maxLevel = 0, spType = SpType.SHIELD),
    SavageBlow(Name.SavageBlow, SkillType.C),
    BreathOfLife(Name.BreathOfLife, SkillType.C),
    SwordExperience(Name.SwordExperience, SkillType.C, spType = SpType.BASE30),
    SwordValor(Name.SwordValor, SkillType.C, spType = SpType.BASE30),
    AxeExperience(Name.AxeExperience, SkillType.C, spType = SpType.BASE30),
    AxeValor(Name.AxeValor, SkillType.C, spType = SpType.BASE30),
    BowExperience(Name.BowExperience, SkillType.C, spType = SpType.BASE30),
    LanceValor(Name.LanceValor, SkillType.C, spType = SpType.BASE30),
    BowValor(Name.BowValor, SkillType.C, spType = SpType.BASE30),
    DaggerValor(Name.DaggerValor, SkillType.C, spType = SpType.BASE30),
    RTomeExperience(Name.RTomeExperience, SkillType.C, spType = SpType.BASE30),
    RTomeValor(Name.RTomeValor, SkillType.C, spType = SpType.BASE30),
    BTomeExperience(Name.BTomeExperience, SkillType.C, spType = SpType.BASE30),
    BTomeValor(Name.BTomeValor, SkillType.C, spType = SpType.BASE30),
    GTomeExperience(Name.GTomeExperience, SkillType.C, spType = SpType.BASE30),
    GTomeValor(Name.GTomeValor, SkillType.C, spType = SpType.BASE30),
    DragonValor(Name.DragonValor, SkillType.C, spType = SpType.BASE30),
    StaffValor(Name.StaffValor, SkillType.C, spType = SpType.BASE30),
    PanicPloy(Name.PanicPloy, SkillType.C, spType = SpType.BASE60),
    AtkPloy(Name.AtkPloy, SkillType.C, spType = SpType.BASE60),
    SpdPloy(Name.SpdPloy, SkillType.C, spType = SpType.BASE60),
    DefPloy(Name.DefPloy, SkillType.C, spType = SpType.BASE60),
    ResPloy(Name.ResPloy, SkillType.C, spType = SpType.BASE60),
    InfantryPulse(Name.InfantryPulse, SkillType.C, spType = SpType.BASE60),
    AtkSmoke(Name.AtkSmoke, SkillType.C, spType = SpType.BASE60),
    SpdSmoke(Name.SpdSmoke, SkillType.C, spType = SpType.BASE60),
    DefSmoke(Name.DefSmoke, SkillType.C, spType = SpType.BASE60),
    ResSmoke(Name.ResSmoke, SkillType.C, spType = SpType.BASE60),
    ArmorMarch(Name.ArmorMarch, SkillType.C, spType = SpType.BASE60),
    Guidance(Name.Guidance, SkillType.C, spType = SpType.BASE60),
    AtkTactic(Name.AtkTactic, SkillType.C, spType = SpType.BASE60),
    SpdTactic(Name.SpdTactic, SkillType.C, spType = SpType.BASE60),
    DefTactic(Name.DefTactic, SkillType.C, spType = SpType.BASE60),
    ResTactic(Name.ResTactic, SkillType.C, spType = SpType.BASE60),
    FlierGuidance(Name.FlierGuidance, SkillType.C, spType = SpType.BASE60),
    OddAtkWave(Name.OddAtkWave, SkillType.C, spType = SpType.BASE60),
    OddResWave(Name.OddResWave, SkillType.C, spType = SpType.BASE60),
    EvenSpdWave(Name.EvenSpdWave, SkillType.C, spType = SpType.BASE60),
    InfantryRush(Name.InfantryRush, SkillType.C, spType = SpType.BASE60),
    OstiasPulse(Name.OstiasPulse, SkillType.C, spType = SpType.EXCLUSIVE),
    DistantGuard(Name.DistantGuard, SkillType.C, spType = SpType.BASE60),
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

    //   override fun localeName(locale: Locale): String =jp.localeName(locale)

    companion object {
        fun spreadItems(none: Boolean = false): List<Skill> = values().fold(if (none) arrayListOf<Skill>(Skill.NONE) else arrayListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else (1..e.maxLevel).forEach { i -> list.add(e.lv(i)) };list
        }

        private val itemMap = mutableMapOf<String, SkillC>()

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