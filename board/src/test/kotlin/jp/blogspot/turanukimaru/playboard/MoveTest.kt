package jp.blogspot.turanukimaru.playboard

import org.junit.Test

class MoveTest {
    @Test
    fun backupAndRestoreTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = Piece(DummyPiece.Dummy, board, Player.None)
    }
}