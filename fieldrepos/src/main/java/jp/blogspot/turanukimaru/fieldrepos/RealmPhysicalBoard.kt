package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmPhysicalBoard(
        @PrimaryKey
        var id:Int,
        var battleUnits : RealmList<RealmPositioning>

): RealmObject() {
    constructor() : this(0,RealmList<RealmPositioning>())
    fun put(realm:Realm, id:String,x:Int, y:Int)  {
        realm.beginTransaction()
        battleUnits.add(RealmPositioning(x,y,RealmBattleUnit(id,1),0))
    realm.commitTransaction()
    }
    fun move(realm:Realm, id:String,x:Int, y:Int) = Realm.Transaction {
        realm.beginTransaction()
//        battleUnits.add(RealmPostioning(x, y, RealmBattleUnit(id, 1), 0))
        val target = battleUnits.find { it.battleUnit?.id == id }
        target?.x = x
        target?.y = y
        println("RealmPhysicalBoard move $x, $y")
        realm.commitTransaction()
    }.execute(realm)

}
