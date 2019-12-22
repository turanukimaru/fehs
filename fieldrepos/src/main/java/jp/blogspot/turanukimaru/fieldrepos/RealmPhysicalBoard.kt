package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import jp.blogspot.turanukimaru.fehs.BattleUnit
import kotlin.math.exp

@RealmClass
open class RealmPhysicalBoard(
        @PrimaryKey
        var id:Int,
        var battleUnits : RealmList<RealmPositioning>

): RealmObject() {
    constructor() : this(0,RealmList<RealmPositioning>())
    fun put(realm:Realm, id:String,x:Int, y:Int)  =realm.executeTransaction{
//        val last = realm.where(RealmBattleUnit::class.java).max("id")
        val unit = realm.createObject<RealmBattleUnit>(RealmBattleUnit::class.java,id)
        unit.hp = 1
        val pos = realm.createObject<RealmPositioning>(RealmPositioning::class.java,id)
        pos.x = x
        pos.y = y
        pos.battleUnit = unit
        battleUnits.add(pos)
        println("RealmPhysicalBoard put $id : $x, $y")
    }
    fun move(realm:Realm, id:String,x:Int, y:Int) = realm.executeTransaction{
        val target = battleUnits.find { it.battleUnit?.id == id }
        target?.x = x
        target?.y = y
        println("RealmPhysicalBoard move $id: $x, $y, ${target?.battleUnit}")
    }

}