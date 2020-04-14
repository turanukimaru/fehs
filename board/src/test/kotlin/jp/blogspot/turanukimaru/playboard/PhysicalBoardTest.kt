package jp.blogspot.turanukimaru.playboard

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class PhysicalBoardTest {
    @Test
    fun positionIsOnBoardTest() {
        val board = Board<DummyUnit, DummyTile>(3, 4)
        val physicalBoard = board.physics
//        physicalBoard.localPut(Piece(DummyUnit.Dummy, board, Player.None), 0, 1, 0)
        Assert.assertThat(board.positionIsOnBoard(Position(0, 0)), CoreMatchers.`is`(true))
    }

    @Test
    fun putTest() {
        val board = Board<DummyUnit, DummyTile>(3, 4)
        val physicalBoard = board.physics
//        physicalBoard.localPut(Piece(DummyUnit.Dummy, board, Player.None), 0, 1, 0)
        Assert.assertThat(board.positionIsOnBoard(Position(0, 0)), CoreMatchers.`is`(true))
    }

}