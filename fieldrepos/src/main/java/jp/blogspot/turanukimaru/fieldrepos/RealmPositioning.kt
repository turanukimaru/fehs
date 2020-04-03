package jp.blogspot.turanukimaru.fieldrepos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmPositioning(
        @PrimaryKey
        var id:Int? = null,
        var x:Int = 0,
        var y :Int = 0,
        var battleUnit: RealmBattleUnit? = null
        ,var orientation: Int = 0

) :RealmObject()
