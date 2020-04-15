package jp.blogspot.turanukimaru.playboard

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException


class PhysicalBoardTest {
    @Rule
    @JvmField
    var thrown: ExpectedException? = ExpectedException.none()
    @Test
    fun indexesTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        assertThat(physicalBoard.horizontalIndexes, `is`(0 until 3))
        assertThat(physicalBoard.verticalIndexes, `is`(0 until 4))
    }

    @Test
    fun positionIsOnBoardTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        assertThat(physicalBoard.positionIsOnBoard(Position(0, 0)), `is`(true))
        assertThat(physicalBoard.positionIsOnBoard(Position(0, 3)), `is`(true))
        assertThat(physicalBoard.positionIsOnBoard(Position(2, 0)), `is`(true))
        assertThat(physicalBoard.positionIsOnBoard(Position(2, 3)), `is`(true))
        assertThat(physicalBoard.positionIsOnBoard(Position(-1, 0)), `is`(false))
        assertThat(physicalBoard.positionIsOnBoard(Position(0, -1)), `is`(false))
        assertThat(physicalBoard.positionIsOnBoard(Position(-1, -1)), `is`(false))
        assertThat(physicalBoard.positionIsOnBoard(Position(3, 3)), `is`(false))
        assertThat(physicalBoard.positionIsOnBoard(Position(2, 4)), `is`(false))
        assertThat(physicalBoard.positionIsOnBoard(Position(3, 4)), `is`(false))
    }

    @Test
    fun putTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = Piece(DummyPiece.Dummy, board, Player.None)
        physicalBoard.put(piece, 1, 1)
        assertThat(physicalBoard.pieceAt(1, 1), `is`(piece))
        assertThat(piece.existsPosition, `is`(Position(1, 1)))

        physicalBoard.put(piece, 2, 3)
        assertThat(piece.existsPosition, `is`(Position(2, 3)))
        thrown!!.expect(RuntimeException::class.java)
        physicalBoard.put(piece, -1, 3)
        physicalBoard.put(piece, 2, -1)
        physicalBoard.put(piece, 3, 3)
        physicalBoard.put(piece, 2, 4)
    }

    @Test
    fun moveTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = Piece(DummyPiece.Dummy, board, Player.None)
        physicalBoard.put(piece, 1, 1)

        physicalBoard.move(piece, Position(0, 0))
        assertThat(physicalBoard.pieceAt(0, 0), `is`(piece))
        assertThat(physicalBoard.pieceAt(1, 1), `is`(nullValue()))
        assertThat(piece.newPosition, `is`(nullValue()))
        assertThat(piece.existsPosition, `is`(Position(0, 0)))

        physicalBoard.move(piece, Position(2, 3))
        assertThat(physicalBoard.pieceAt(2, 3), `is`(piece))
        assertThat(physicalBoard.pieceAt(0, 0), `is`(nullValue()))
        assertThat(piece.newPosition, `is`(nullValue()))
        assertThat(piece.existsPosition, `is`(Position(2, 3)))

        physicalBoard.move(piece, Position(2, 3))
        assertThat(physicalBoard.pieceAt(2, 3), `is`(piece))
        assertThat(piece.newPosition, `is`(nullValue()))
        assertThat(piece.existsPosition, `is`(Position(2, 3)))

        thrown!!.expect(RuntimeException::class.java)
        physicalBoard.move(piece, Position(-1, 3))
        physicalBoard.move(piece, Position(2, -1))
        physicalBoard.move(piece, Position(3, 3))
        physicalBoard.move(piece, Position(2, 4))
    }

    @Test
    fun removeTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = Piece(DummyPiece.Dummy, board, Player.None)

        physicalBoard.put(piece, 1, 1)
        physicalBoard.remove(piece)
        assertThat(piece.existsPosition, `is`(nowhere))
        assertThat(physicalBoard.pieceAt(1, 1), `is`(nullValue()))

        physicalBoard.put(piece, 1, 1)
        physicalBoard.move(piece, Position(2, 3))
        physicalBoard.remove(piece)
        assertThat(piece.existsPosition, `is`(nowhere))
        assertThat(physicalBoard.pieceAt(2, 3), `is`(nullValue()))
    }

    @Test
    fun copyTilesSwitchXY() {
        val battleGround = arrayOf(
                arrayOf(DummyTile.Plane, DummyTile.Plane, null, null),
                arrayOf(null, DummyTile.Plane, null, null),
                arrayOf(null, DummyTile.Plane, DummyTile.Plane, null)
        )
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(4, 3)
        physicalBoard.copyTilesSwitchXY(battleGround)

        //上下が逆なのでこのラインが一番上
        assertThat(physicalBoard.tileAt(0, 2), `is`(DummyTile.Plane))
        assertThat(physicalBoard.tileAt(1, 2), `is`(DummyTile.Plane))
        assertThat(physicalBoard.tileAt(2, 2), `is`(nullValue()))
        assertThat(physicalBoard.tileAt(3, 2), `is`(nullValue()))

        assertThat(physicalBoard.tileAt(0, 1), `is`(nullValue()))
        assertThat(physicalBoard.tileAt(1, 1), `is`(DummyTile.Plane))
        assertThat(physicalBoard.tileAt(2, 1), `is`(nullValue()))
        assertThat(physicalBoard.tileAt(3, 1), `is`(nullValue()))

        assertThat(physicalBoard.tileAt(0, 0), `is`(nullValue()))
        assertThat(physicalBoard.tileAt(1, 0), `is`(DummyTile.Plane))
        assertThat(physicalBoard.tileAt(2, 0), `is`(DummyTile.Plane))
        assertThat(physicalBoard.tileAt(3, 0), `is`(nullValue()))
    }

    @Test
    fun backupAndRestoreTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = Piece(DummyPiece.Dummy, board, Player.None)

        physicalBoard.put(piece, 1, 1)
        physicalBoard.backup()

        physicalBoard.move(piece, Position(0, 0))
        physicalBoard.restore()
        assertThat(piece.existsPosition, `is`(Position(1, 1)))
        assertThat(physicalBoard.pieceAt(1, 1), `is`(piece))
        assertThat(physicalBoard.pieceAt(0, 0), `is`(nullValue()))

    }
}