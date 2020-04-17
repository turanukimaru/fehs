package jp.blogspot.turanukimaru.fieldrepos

import io.realm.Realm
import jp.blogspot.turanukimaru.fehs.MyPiece
import jp.blogspot.turanukimaru.fehs.Tile
import jp.blogspot.turanukimaru.playboard.*

/**
 * TODO:作成時に駒のマトリクスを展開するか、駒のマトリクスをrealmPhysicalBoard依存にするか…とりあえず展開するか
 */
class PersistPhysicalBoard(horizontalLines: Int, verticalLines: Int, var realmPhysicalBoard: RealmPhysicalBoard, val realm: Realm, id: Int) : PhysicalBoard<MyPiece, Tile>(horizontalLines, verticalLines, id) {
    override fun load(board: Board<MyPiece, Tile>, matrix: MutableList<MutableList<Piece<MyPiece, Tile>?>>, map: MutableMap<Piece<MyPiece, Tile>, Position>) {
        realmPhysicalBoard.battleUnits.forEach {
            println("it:$it")
            val piece = MyPiece(it.battleUnit!!.toModelObject(), board, Player.None)//アクションリスナ入れられねえ！後でやるしかないか
            println("battleUnit:${it.battleUnit}")
            println("it:${it.battleUnit!!.realmArmedHero}")
            println("toModelObject:${it.battleUnit!!.toModelObject()}")
            println("piece:$piece")
            matrix[it.x][it.y] = piece
            map[piece] = Position(it.x, it.y)
        }
    }

    override fun localPut(piece: Piece<MyPiece, Tile>, x: Int, y: Int, orientation: Int) {
        super.localPut(piece, x, y, orientation)
        println("PersistPhysicalBoard localPut ${piece.contains.containUnit.armedHero.baseHero.heroName.jp}")
        realmPhysicalBoard.put(realm, piece.contains.containUnit, x, y)
    }

    //これコミットしたときだけにしたいなあ…
    override fun localMove(piece: Piece<MyPiece, Tile>, x: Int, y: Int, oldX: Int, oldY: Int, orientation: Int) {
        super.localMove(piece, x, y, oldX, oldY, orientation)
        println("PersistPhysicalBoard localMove ${piece.contains.containUnit.armedHero.baseHero.heroName.jp}")
        realmPhysicalBoard.move(realm, piece.contains.containUnit, x, y)
    }
}