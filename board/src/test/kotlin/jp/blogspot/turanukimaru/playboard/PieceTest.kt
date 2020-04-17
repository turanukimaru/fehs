package jp.blogspot.turanukimaru.playboard

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert
import org.junit.Test

class PieceTest {
    @Test
    fun initTest() {
        val physicalBoard = PhysicalBoard<ShogiUnit, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = ShogiPiece(ShogiUnit.Fu, board, Player.None)
        Assert.assertThat(piece.contains, `is`(ShogiUnit.Fu))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))// null にするか　nowhere　にするか迷う…
        Assert.assertThat(piece.charPosition, `is`(nowhere))//配置していないのでPositionはnowhere
//        Assert.assertThat(piece.searchedRouteAt(Position(0, 0)), `is`(-1))//配置していないので移動できない
//        Assert.assertThat(piece.actionableAt(Position(0, 0)), `is`(-1))//配置していないので行動できない
        physicalBoard.put(piece, 1, 1)
        Assert.assertThat(piece.searchedRouteAt(Position(0, 0)), `is`(-1))
        Assert.assertThat(piece.searchedRouteAt(Position(1, 2)), `is`(1))//前方へ一歩動けるはず
        Assert.assertThat(piece.actionableAt(Position(0, 0)), `is`(-1))
        Assert.assertThat(piece.actionableAt(Position(2, 2)), `is`(1))//一歩前に出て右にはアクション出来るはず
        Assert.assertThat(piece.actionableAt(Position(0, 2)), `is`(-1))//左は orientation はあるが able にならない
        Assert.assertThat(piece.actionableAt(Position(2, 1)), `is`(1))//その場から右へアクション出来る

    }
}

