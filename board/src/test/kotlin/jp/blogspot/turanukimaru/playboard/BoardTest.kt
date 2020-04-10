package jp.blogspot.turanukimaru.playboard

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class BoardTest {
    @Test
   fun  positionIsOnBoardTest(){
        val board = Board<Piece<DummyUnit,DummyTile>, DummyTile>(3, 4)
       assertThat( board.positionIsOnBoard(Position(0,0)),`is`(true))
    }
}