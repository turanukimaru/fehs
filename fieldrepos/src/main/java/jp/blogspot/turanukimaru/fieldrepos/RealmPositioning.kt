package jp.blogspot.turanukimaru.fieldrepos

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class RealmPositioning(
        var x:Int,
        var y :Int,
        var battleUnit: RealmBattleUnit?
        ,var orientation: Int

) :RealmObject(

){
constructor():this(0,0,null,0)
}