package jp.blogspot.turanukimaru.fieldrepos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.BoonType
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import jp.blogspot.turanukimaru.fehs.skill.*

/**
 * @RealmClass を忘れると Caused by: java.lang.NullPointerException at io.realm.RealmBattleClassRealmProxy.<init>(RealmBattleClassRealmProxy.java:137) at io.realm.DefaultRealmModuleMediator.newInstance(DefaultRealmModuleMediator.java:91)
 */
@RealmClass
open class RealmArmedHero(
        @PrimaryKey
        var id: Int = 0,
        var nickname: String = "",
        var baseName: String = "",
        var weapon: String = "NONE",
        var refinedWeapon: String = "NONE",
        var assist: String = "NONE",
        var special: String = "NONE",
        var aSkill: String = "NONE",
        var bSkill: String = "NONE",
        var cSkill: String = "NONE",
        var seal: String = "NONE",
        var rarity: Int = 5,
        var boost: Int = 0,
        var boon: String = "NONE",
        var bane: String = "NONE",
        var defensiveTerrain: Boolean = false,
        var atkBuff: Int = 0,
        var spdBuff: Int = 0,
        var defBuff: Int = 0,
        var resBuff: Int = 0,
        var atkSpur: Int = 0,
        var spdSpur: Int = 0,
        var defSpur: Int = 0,
        var resSpur: Int = 0
) : RealmObject() {
    fun toModelObject(): ArmedHero {
        val result = StandardBaseHero.get(baseName)!!
//        Log.d("RealmArmedHero", "CREATE BaseHero FROM RealmArmedHero")
//        Log.d("RealmArmedHero", "nickname $nickname ")
//        Log.d("RealmArmedHero", "weapon $weapon ")
//        Log.d("RealmArmedHero", "refinedWeapon $refinedWeapon ")
//        Log.d("RealmArmedHero", "assist $assist ")
//        Log.d("RealmArmedHero", "special $special ")
//        Log.d("RealmArmedHero", "SkillA $aSkill ")
//        Log.d("RealmArmedHero", "SkillB $bSkill ")
//        Log.d("RealmArmedHero", "SkillC $cSkill ")
//        Log.d("RealmArmedHero", "seal $seal ")
//        Log.d("RealmArmedHero", "rarity $rarity ")
//        Log.d("RealmArmedHero", "levelBoost $boost ")
//        Log.d("RealmArmedHero", "boon $boon ")
//        Log.d("RealmArmedHero", "bane $bane ")
//        Log.d("RealmArmedHero", "atkBuff $atkBuff ")
//        Log.d("RealmArmedHero", "spdBuff $spdBuff ")
//        Log.d("RealmArmedHero", "defBuff $defBuff ")
//        Log.d("RealmArmedHero", "resBuff $resBuff ")
//        Log.d("RealmArmedHero", "atkSpur $atkSpur ")
//        Log.d("RealmArmedHero", "spdSpur $spdSpur ")
//        Log.d("RealmArmedHero", "defSpur $defSpur ")
//        Log.d("RealmArmedHero", "resSpur $resSpur ")
        return ArmedHero(result, nickname, Weapon.valueOfOrNONE(weapon), RefinedWeapon.valueOfOrNONE(refinedWeapon), Assist.valueOfOrNONE(assist), Special.valueOfOrNONE(special), SkillA.valueOfOrNONE(aSkill), SkillB.valueOfOrNONE(bSkill), SkillC.valueOfOrNONE(cSkill), Seal.valueOfOrNONE(seal), 0, rarity, boost, BoonType.valueOf(boon), BoonType.valueOf(bane), defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur)
    }
}