package jp.blogspot.turanukimaru.repos

import android.util.Log
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import jp.blogspot.turanukimaru.fehs.*

/**
 * @RealmClass を忘れると Caused by: java.lang.NullPointerException at io.realm.RealmBattleClassRealmProxy.<init>(RealmBattleClassRealmProxy.java:137) at io.realm.DefaultRealmModuleMediator.newInstance(DefaultRealmModuleMediator.java:91)
 */
@RealmClass
open class RealmBattleClass(
        @PrimaryKey
        var nickname: String = "",
        var baseName: String = "",
        var weapon: String = "NONE",
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
        var atkBuff:Int = 0,
        var spdBuff:Int = 0,
        var defBuff:Int = 0,
        var resBuff:Int = 0,
        var atkSpur:Int = 0,
        var spdSpur:Int = 0,
        var defSpur:Int = 0,
        var resSpur:Int = 0
) : RealmObject() {
    fun toModelObject(): ArmedClass {
        val result = StandardBattleClass.get(baseName)!!
        Log.i("RealmBattleClass","CREATE BattleClass FROM RealmBattleClass")
        Log.i("RealmBattleClass","nickname $nickname ")
        Log.i("RealmBattleClass","weapon $weapon ")
        Log.i("RealmBattleClass","assist $assist ")
        Log.i("RealmBattleClass","special $special ")
        Log.i("RealmBattleClass","aSkill $aSkill ")
        Log.i("RealmBattleClass","bSkill $bSkill ")
        Log.i("RealmBattleClass","cSkill $cSkill ")
        Log.i("RealmBattleClass","seal $seal ")
        Log.i("RealmBattleClass","rarity $rarity ")
        Log.i("RealmBattleClass","levelBoost $boost ")
        Log.i("RealmBattleClass","boon $boon ")
        Log.i("RealmBattleClass","bane $bane ")
        return         ArmedClass(result, nickname, Weapons.valueOfOrNONE(weapon), Assists.valueOfOrNONE(assist), Specials.valueOfOrNONE(special), Skills.valueOfOrNONE(aSkill), Skills.valueOfOrNONE(bSkill), Skills.valueOfOrNONE(cSkill), Seals.valueOfOrNONE(seal), rarity, boost, BoonType.valueOf(boon), BoonType.valueOf(bane), defensiveTerrain, atkBuff, spdBuff, defBuff, resBuff, atkSpur, spdSpur, defSpur, resSpur)
    }
}