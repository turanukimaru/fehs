package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import jp.blogspot.turanukimaru.fehs.BattleUnit

@RealmClass
open class RealmPhysicalBoard(
        @PrimaryKey
        var id: Int = 0,
        var battleUnits: RealmList<RealmPositioning> = RealmList<RealmPositioning>()
) : RealmObject() {
    //    constructor() : this(0,RealmList<RealmPositioning>())
    fun put(realm: Realm, piece: BattleUnit, x: Int, y: Int) = realm.executeTransaction {
        val last = (realm.where(RealmBattleUnit::class.java).max("id") ?: 0).toInt() + 1
        val unit = realm.createObject(RealmBattleUnit::class.java, last)
        val armedHero = realm.createObject(RealmArmedHero::class.java, last)
        unit.realmArmedHero = armedHero
        armedHero.nickname = piece.armedHero.name
        armedHero.baseName = piece.armedHero.name
        unit.hp = piece.hp
        piece.armedHero.id = last
        val pos = realm.createObject(RealmPositioning::class.java, last)
        pos.x = x
        pos.y = y
        pos.battleUnit = unit
        battleUnits.add(pos)
        println("RealmPhysicalBoard put $id : $x, $y")
        println("battleUnits $battleUnits")
    }

    fun move(realm: Realm, piece: BattleUnit, x: Int, y: Int) = realm.executeTransaction {
        println("battleUnits $battleUnits")
        val target = battleUnits.find { it.id == piece.armedHero.id }
        target?.x = x
        target?.y = y
        //これ別スレッドエラーで落ちないんだっけ…？
        println("RealmPhysicalBoard move ${piece.armedHero.id}: $x, $y, ${target}, ${target?.battleUnit}")
    }

}
