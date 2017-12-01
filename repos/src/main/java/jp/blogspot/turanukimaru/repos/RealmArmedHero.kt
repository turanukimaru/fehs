package jp.blogspot.turanukimaru.repos

import android.util.Log
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
        Log.i("RealmArmedHero", "CREATE BaseHero FROM RealmArmedHero")
        Log.i("RealmArmedHero", "nickname $nickname ")
        Log.i("RealmArmedHero", "weapon $weapon ")
        Log.i("RealmArmedHero", "refinedWeapon $refinedWeapon ")
        Log.i("RealmArmedHero", "assist $assist ")
        Log.i("RealmArmedHero", "special $special ")
        Log.i("RealmArmedHero", "SkillA $aSkill ")
        Log.i("RealmArmedHero", "SkillB $bSkill ")
        Log.i("RealmArmedHero", "SkillC $cSkill ")
        Log.i("RealmArmedHero", "seal $seal ")
        Log.i("RealmArmedHero", "rarity $rarity ")
        Log.i("RealmArmedHero", "levelBoost $boost ")
        Log.i("RealmArmedHero", "boon $boon ")
        Log.i("RealmArmedHero", "bane $bane ")
        return ArmedHero(result, nickname, Weapon.valueOfOrNONE(weapon), RefineSkill.valueOfOrNONE(refinedWeapon), Assist.valueOfOrNONE(assist), Special.valueOfOrNONE(special), SkillA.valueOfOrNONE(aSkill), SkillB.valueOfOrNONE(bSkill), SkillC.valueOfOrNONE(cSkill), Seal.valueOfOrNONE(seal), rarity, boost, BoonType.valueOf(boon), BoonType.valueOf(bane), defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur)
    }
}