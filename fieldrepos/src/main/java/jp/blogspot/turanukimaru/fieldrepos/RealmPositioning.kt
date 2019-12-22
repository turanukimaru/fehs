package jp.blogspot.turanukimaru.fieldrepos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmPositioning(
        @PrimaryKey
        var id:String,
        var x:Int,
        var y :Int,
        var battleUnit: RealmBattleUnit?
        ,var orientation: Int = 0

) :RealmObject(

){
constructor():this("",0,0,null,0)
}