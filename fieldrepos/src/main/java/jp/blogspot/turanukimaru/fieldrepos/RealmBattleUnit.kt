package jp.blogspot.turanukimaru.fieldrepos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmBattleUnit(
        @PrimaryKey
        var id : Int = 0
        ,var hp:Int = 0
        // id 参照か実体を持つかは難しいところ。別ドメインなら参照だが同一ドメインなら実体だ…
//,var realmArmedHero: RealmArmedHero
): RealmObject() {
//        constructor() : this("",0)
}