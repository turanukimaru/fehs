package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import jp.blogspot.turanukimaru.fehs.Ground
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.playboard.PhysicalBoard
import jp.blogspot.turanukimaru.playboard.Piece

class PersistPhysicalBoard(horizontalLines: Int, verticalLines: Int, var realmPhysicalBoard :RealmPhysicalBoard, val realm: Realm) : PhysicalBoard<MyPiece,Ground>(horizontalLines, verticalLines) {
override fun localPut(piece: Piece<MyPiece, Ground>, x: Int, y: Int, orientation: Int) {
    super.localPut(piece, x, y, orientation)
    println("PersistPhysicalBoard localPut $realmPhysicalBoard")
    realmPhysicalBoard.put(realm,piece.unit.containUnit.armedHero.baseHero.heroName.jp,x,y) //DB登録名でいいはず…
}

    override fun localMove(piece: Piece<MyPiece, Ground>, x: Int, y: Int, orientation: Int) {
        super.localMove(piece, x, y, orientation)
        println("PersistPhysicalBoard localMove $realmPhysicalBoard")
        realmPhysicalBoard.move(realm,piece.unit.containUnit.armedHero.name,x,y)
    }
}