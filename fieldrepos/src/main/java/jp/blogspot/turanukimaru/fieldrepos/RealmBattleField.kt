package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import jp.blogspot.turanukimaru.fehs.Tile
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.playboard.PhysicalBoard

/**
 * @RealmClass を忘れると Caused by: java.lang.NullPointerException at io.realm.RealmBattleClassRealmProxy.<init>(RealmBattleClassRealmProxy.java:137) at io.realm.DefaultRealmModuleMediator.newInstance(DefaultRealmModuleMediator.java:91)
 */
@RealmClass
open class RealmBattleField(
        @PrimaryKey
        var id: Int? = null,
        var horizontalLines: Int=0,
        var verticalLines: Int=0,
        var name: String = "",//あと地形とユニットか。
        var realmPhysicalBoard: RealmPhysicalBoard?=null
) : RealmObject() {
    constructor() : this(0,0,0,"",null)
    fun toModelObject(realm: Realm): PhysicalBoard<MyPiece, Tile> =// repo は多分更新時に repo のトランザクションを張るのに必要になる…
            PersistPhysicalBoard(horizontalLines, verticalLines,realmPhysicalBoard!!,realm)
}