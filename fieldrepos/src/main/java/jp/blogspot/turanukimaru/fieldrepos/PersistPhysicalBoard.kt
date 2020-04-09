package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.fehs.Tile
import jp.blogspot.turanukimaru.playboard.PhysicalBoard
import jp.blogspot.turanukimaru.playboard.Piece

class PersistPhysicalBoard(horizontalLines: Int, verticalLines: Int, var realmPhysicalBoard: RealmPhysicalBoard, val realm: Realm) : PhysicalBoard<MyPiece, Tile>(horizontalLines, verticalLines) {
    override fun localPut(piece: Piece<MyPiece, Tile>, x: Int, y: Int, orientation: Int) {
        super.localPut(piece, x, y, orientation)
        println("PersistPhysicalBoard localPut ${piece.specialized.containUnit.armedHero.baseHero.heroName.jp}")
        realmPhysicalBoard.put(realm, piece.specialized.containUnit, x, y) //ユニットのIDどうすっかな
    }

    //これコミットしたときだけにしたいなあ…
    override fun localMove(piece: Piece<MyPiece, Tile>, x: Int, y: Int, oldX: Int?, oldY: Int?, orientation: Int) {
        super.localMove(piece, x, y, oldX, oldY, orientation)
        println("PersistPhysicalBoard localMove ${piece.specialized.containUnit.armedHero.baseHero.heroName.jp}")
        realmPhysicalBoard.move(realm, piece.specialized.containUnit, x, y)
    }
}