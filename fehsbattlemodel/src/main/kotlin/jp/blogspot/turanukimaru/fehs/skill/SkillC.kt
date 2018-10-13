package jp.blogspot.turanukimaru.fehs.skill

import jp.blogspot.turanukimaru.fehs.Name
import jp.blogspot.turanukimaru.fehs.SkillType
import jp.blogspot.turanukimaru.fehs.SpType

/**
 * スキル。C
 */
enum class SkillC(override val jp: Name, override val type: SkillType = SkillType.C, override val level: Int = 0, override val preSkill: Skill = Skill.Companion.NONE, override val maxLevel: Int = 3, override val spType: SpType = SpType.BASE50) : Skill {


    SpurAtk(Name.SpurAtk),
    SpurSpd(Name.SpurSpd),
    SpurDef(Name.SpurDef),
    SpurRes(Name.SpurRes),
    SpurSpdDef(Name.SpurSpdDef, maxLevel = 2, spType = SpType.BASE60),
    SpurSpdRes(Name.SpurSpdRes, maxLevel = 2, spType = SpType.BASE60),
    SpurDefRes(Name.SpurDefRes, maxLevel = 2, spType = SpType.BASE60),
    SpurAtkSpd(Name.SpurAtkSpd, maxLevel = 2, spType = SpType.BASE60),
    SpurAtkDef(Name.SpurAtkDef, maxLevel = 2, spType = SpType.BASE60),
    SpurAtkRes(Name.SpurAtkRes, maxLevel = 2, spType = SpType.BASE60),
    ThreatenAtk(Name.ThreatenAtk),
    ThreatenSpd(Name.ThreatenSpd),
    ThreatenDef(Name.ThreatenDef),
    ThreatenRes(Name.ThreatenRes),
    FortifyDef(Name.FortifyDef),
    FortifyRes(Name.FortifyRes),
    FortifyArmor(Name.FortifyArmor, maxLevel = 0, spType = SpType.SHIELD),
    FortifyCavalry(Name.FortifyCavalry, maxLevel = 0, spType = SpType.SHIELD),
    FortifyFliers(Name.FortifyFliers, maxLevel = 0, spType = SpType.SHIELD),
    FortifyDragons(Name.FortifyDragons, maxLevel = 0, spType = SpType.SHIELD),
    HoneAtk(Name.HoneAtk),
    HoneSpd(Name.HoneSpd),
    DriveAtk(Name.DriveAtk, maxLevel = 2, spType = SpType.BASE60),
    DriveDef(Name.DriveDef, maxLevel = 2, spType = SpType.BASE60),
    DriveSpd(Name.DriveSpd, maxLevel = 2, spType = SpType.BASE60),
    DriveRes(Name.DriveRes, maxLevel = 2, spType = SpType.BASE60),
    HoneArmor(Name.HoneArmor, maxLevel = 0, spType = SpType.SHIELD),
    HoneCavalry(Name.HoneCavalry, maxLevel = 0, spType = SpType.SHIELD),
    HoneDragons(Name.HoneDragons, maxLevel = 0, spType = SpType.SHIELD),
    HoneFliers(Name.HoneFliers, maxLevel = 0, spType = SpType.SHIELD),
    GoadArmor(Name.GoadArmor, maxLevel = 0, spType = SpType.SHIELD),
    GoadCavalry(Name.GoadCavalry, maxLevel = 0, spType = SpType.SHIELD),
    GoadDragons(Name.GoadDragons, maxLevel = 0, spType = SpType.SHIELD),
    GoadFliers(Name.GoadFliers, maxLevel = 0, spType = SpType.SHIELD),
    WardArmor(Name.WardArmor, maxLevel = 0, spType = SpType.SHIELD),
    WardCavalry(Name.WardCavalry, maxLevel = 0, spType = SpType.SHIELD),
    WardDragons(Name.WardDragons, maxLevel = 0, spType = SpType.SHIELD),
    WardFliers(Name.WardFliers, maxLevel = 0, spType = SpType.SHIELD),
    SavageBlow(Name.SavageBlow),
    BreathOfLife(Name.BreathOfLife),
    SwordExperience(Name.SwordExperience, spType = SpType.BASE30),
    SwordValor(Name.SwordValor, spType = SpType.BASE30),
    AxeExperience(Name.AxeExperience, spType = SpType.BASE30),
    AxeValor(Name.AxeValor, spType = SpType.BASE30),
    BowExperience(Name.BowExperience, spType = SpType.BASE30),
    LanceValor(Name.LanceValor, spType = SpType.BASE30),
    BowValor(Name.BowValor, spType = SpType.BASE30),
    DaggerValor(Name.DaggerValor, spType = SpType.BASE30),
    RTomeExperience(Name.RTomeExperience, spType = SpType.BASE30),
    RTomeValor(Name.RTomeValor, spType = SpType.BASE30),
    BTomeExperience(Name.BTomeExperience, spType = SpType.BASE30),
    BTomeValor(Name.BTomeValor, spType = SpType.BASE30),
    GTomeExperience(Name.GTomeExperience, spType = SpType.BASE30),
    GTomeValor(Name.GTomeValor, spType = SpType.BASE30),
    DragonValor(Name.DragonValor, spType = SpType.BASE30),
    StaffValor(Name.StaffValor, spType = SpType.BASE30),
    PanicPloy(Name.PanicPloy, spType = SpType.BASE60),
    AtkPloy(Name.AtkPloy, spType = SpType.BASE60),
    SpdPloy(Name.SpdPloy, spType = SpType.BASE60),
    DefPloy(Name.DefPloy, spType = SpType.BASE60),
    ResPloy(Name.ResPloy, spType = SpType.BASE60),
    InfantryPulse(Name.InfantryPulse, spType = SpType.BASE60),
    AtkSmoke(Name.AtkSmoke, spType = SpType.BASE60),
    SpdSmoke(Name.SpdSmoke, spType = SpType.BASE60),
    DefSmoke(Name.DefSmoke, spType = SpType.BASE60),
    ResSmoke(Name.ResSmoke, spType = SpType.BASE60),
    ArmorMarch(Name.ArmorMarch, spType = SpType.BASE60),
    Guidance(Name.Guidance, spType = SpType.BASE60),
    AtkTactic(Name.AtkTactic, spType = SpType.BASE60),
    SpdTactic(Name.SpdTactic, spType = SpType.BASE60),
    DefTactic(Name.DefTactic, spType = SpType.BASE60),
    ResTactic(Name.ResTactic, spType = SpType.BASE60),
    FlierGuidance(Name.FlierGuidance, spType = SpType.BASE60),
    OddAtkWave(Name.OddAtkWave, spType = SpType.BASE60),
    OddSpdWave(Name.OddSpdWave, spType = SpType.BASE60),
    OddDefWave(Name.OddDefWave, spType = SpType.BASE60),
    OddResWave(Name.OddResWave, spType = SpType.BASE60),
    EvenAtkWave(Name.EvenAtkWave, spType = SpType.BASE60),
    EvenSpdWave(Name.EvenSpdWave, spType = SpType.BASE60),
    EvenDefWave(Name.EvenDefWave, spType = SpType.BASE60),
    EvenResWave(Name.EvenResWave, spType = SpType.BASE60),
    InfantryRush(Name.InfantryRush, spType = SpType.BASE60),
    DistantGuard(Name.DistantGuard, spType = SpType.BASE60),
    CloseGuard(Name.CloseGuard, spType = SpType.BASE60),
    InfantryFlash(Name.InfantryFlash, spType = SpType.BASE60),
    OstiasPulse(Name.OstiasPulse, maxLevel = 0, spType = SpType.LEGEND_S),
    WithEveryone(Name.WithEveryone, maxLevel = 0, spType = SpType.LEGEND_S),
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
        fun spreadMaxLvItems(none: Boolean = false): List<Skill> = values().fold(if (none) arrayListOf<Skill>(Skill.NONE) else arrayListOf()) { list, e ->
            if (e.maxLevel == 0) {
                list.add(e)
            } else list.add(e.lv(e.maxLevel));list
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