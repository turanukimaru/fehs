package jp.blogspot.turanukimaru.playboard

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert
import org.junit.Test

class MovingTest {
    @Test
    fun noMoveTest() {
        val physicalBoard = PhysicalBoard<DummyPiece, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val move = board.move//操作へのハンドラ
        val piece = Piece(DummyPiece.Dummy, board, Player.None)

        board.physics.put(piece, 1, 1)
        val noMove = NoMove(move)
        val boardClicked = noMove.boardClick(Position(1, 1))
        Assert.assertThat(boardClicked, `is`(NoMove(move) as Moving<DummyPiece, DummyTile>))//盤をクリックしても何も起きない
        Assert.assertThat(noMove.pieceClick(Position(1, 1), piece), `is`(NoMove(move) as Moving<DummyPiece, DummyTile>))//行動可能になってない駒をクリックしても何も起きない
        val another = Piece(DummyPiece.Dummy, board, Player())
        Assert.assertThat(noMove.pieceClick(Position(1, 1), another), `is`((NoMove(move) as Moving<DummyPiece, DummyTile>)))//別のプレイヤーの駒をクリックしても何も起こらない。将来的には移動範囲を表示したりするんだろうが。
        piece.actionPhase = ActionPhase.READY
        Assert.assertThat(noMove.pieceClick(Position(1, 1), piece), `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<DummyPiece, DummyTile>))//駒を掴む。 from と to は同じ…デフォルト値入れようかな？
        piece.actionPhase = ActionPhase.MOVING
        Assert.assertThat(noMove.pieceClick(Position(1, 1), piece), `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<DummyPiece, DummyTile>))//駒を掴む。 理論上ここには来ないはずなのだが…？

    }

    @Test
    fun graspTest() {
        val physicalBoard = PhysicalBoard<ShogiUnit, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = ShogiPiece(ShogiUnit.Fu, board, Player.None)
        Assert.assertThat(piece.contains, `is`(ShogiUnit.Fu))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))// null にするか　nowhere　にするか迷う…
        Assert.assertThat(piece.charPosition, `is`(nowhere))
        physicalBoard.put(piece, 1, 1)
        piece.actionPhase = ActionPhase.READY// put すると PUTTED になるのでおいてから準備状態にする
        val move = board.move//操作へのハンドラ
        val noMove = NoMove(move)
        val grasped = noMove.pieceClick(Position(1, 1), piece)
        Assert.assertThat(grasped, `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<ShogiUnit, DummyTile>))//盤をクリックしても何も起きない
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //範囲外
        Assert.assertThat(grasped.boardClick(Position(0, 0)), `is`(NoMove(move) as Moving<ShogiUnit, DummyTile>))//移動範囲外をクリックしてたら移動キャンセル
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.READY))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //再度掴む
        val grasped1 = noMove.pieceClick(Position(1, 1), piece)
        //一歩前に出る
        val grasped2 = grasped1.boardClick(Position(1, 2))
        Assert.assertThat(grasped2, `is`(Grasp(move, piece, Position(1, 1), Position(1, 2)) as Moving<ShogiUnit, DummyTile>))//移動範囲をクリックしてたら移動中
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //元の場所に戻る
        val grasped3 = grasped1.boardClick(Position(1, 1))
        Assert.assertThat(grasped3, `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<ShogiUnit, DummyTile>))//元の場所に戻っても掴んだまま
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //目の前に別の駒を置く
        val piece1 = ShogiPiece(ShogiUnit.Fu, board, Player())
        physicalBoard.put(piece1, 1, 2)
        val grasped4 = grasped1.pieceClick(Position(1, 2), piece1)//侵入準備
        Assert.assertThat(grasped4, `is`(Into(move, piece, Position(1, 1), Position(1, 2), piece1, Position(1, 2)) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //同じ場所に重ねて別の駒が有る事にする
        val piece2 = ShogiPiece(ShogiUnit.Fu, board, Player())
        val grasped5 = grasped1.pieceClick(Position(1, 2), piece2)//別の駒へ侵入しようとしたらまた別の侵入準備状態
        Assert.assertThat(grasped5, `is`(Into(move, piece, Position(1, 1), Position(1, 2), piece2, Position(1, 2)) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        // キャンセルする
        val grasped6 = grasped1.boardClick(Position(0, 0))//移動できないところクリックしたらキャンセル
        Assert.assertThat(grasped6, `is`(NoMove(move) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.READY))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //侵入を確定する
        val grasped7 = grasped6.pieceClick(Position(1, 1), piece)//また掴む
        val grasped8 = grasped7.pieceClick(Position(1, 2), piece1)//侵入準備
        val grasped9 = grasped8.pieceClick(Position(1, 2), piece1)//侵入完了
        Assert.assertThat(grasped9, `is`(NoMove(move) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.ACTED))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 2)), `is`(piece as Piece<ShogiUnit, DummyTile>))//確定した
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 2)))//確定した
    }

    @Test
    fun moveTest() {
        val physicalBoard = PhysicalBoard<ShogiUnit, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = ShogiPiece(ShogiUnit.Fu, board, Player.None)
        Assert.assertThat(piece.contains, `is`(ShogiUnit.Fu))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))// null にするか　nowhere　にするか迷う…
        Assert.assertThat(piece.charPosition, `is`(nowhere))
        physicalBoard.put(piece, 1, 1)
        piece.actionPhase = ActionPhase.READY// put すると PUTTED になるのでおいてから準備状態にする
        val move = board.move//操作へのハンドラ
        val noMove = NoMove(move)
        val grasped = noMove.pieceClick(Position(1, 1), piece)
        Assert.assertThat(grasped, `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<ShogiUnit, DummyTile>))//盤をクリックしても何も起きない
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //移動（未確定
        val moving = grasped.boardClick(Position(1, 2))
        Assert.assertThat(moving, `is`(Grasp(move, piece, Position(1, 1), Position(1, 2)) as Moving<ShogiUnit, DummyTile>))//盤をクリックしても何も起きない
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //範囲外
        Assert.assertThat(grasped.boardClick(Position(0, 0)), `is`(NoMove(move) as Moving<ShogiUnit, DummyTile>))//移動範囲外をクリックしてたら移動キャンセル
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.READY))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //再度掴む
        val grasped1 = noMove.pieceClick(Position(1, 1), piece)
        val grasped2 = grasped1.boardClick(Position(1, 2))
        Assert.assertThat(grasped2, `is`(Grasp(move, piece, Position(1, 1), Position(1, 2)) as Moving<ShogiUnit, DummyTile>))//移動範囲をクリックしてたら移動中
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //一歩前に
        val grasped3 = grasped1.boardClick(Position(1, 2))
        Assert.assertThat(grasped3, `is`(Grasp(move, piece, Position(1, 1), Position(1, 2)) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない
//クリックして確定
        val grasped4 = grasped3.pieceClick(Position(1, 2), piece)//駒をクリックしたら確定
        Assert.assertThat(grasped4, `is`(NoMove(move) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.ACTED))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 2)), `is`(piece as Piece<ShogiUnit, DummyTile>))//確定した
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 2)))//確定した
    }

    @Test
    fun assistTest() {
        val physicalBoard = PhysicalBoard<ShogiUnit, DummyTile>(3, 4)
        val board = Board(physicalBoard)
        val piece = ShogiPiece(ShogiUnit.Fu, board, Player.None)
        Assert.assertThat(piece.contains, `is`(ShogiUnit.Fu))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))// null にするか　nowhere　にするか迷う…
        Assert.assertThat(piece.charPosition, `is`(nowhere))
        physicalBoard.put(piece, 1, 1)
        piece.actionPhase = ActionPhase.READY// put すると PUTTED になるのでおいてから準備状態にする
        val move = board.move//操作へのハンドラ
        val noMove = NoMove(move)
        //アシストできる位置に味方の駒を置く
        val piece1 = ShogiPiece(ShogiUnit.Fu, board, Player())
        physicalBoard.put(piece1, 2, 2)

        val grasped = noMove.pieceClick(Position(1, 1), piece)
        Assert.assertThat(grasped, `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<ShogiUnit, DummyTile>))//盤をクリックしても何も起きない
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //移動（未確定
        val moving = grasped.boardClick(Position(1, 2))
        Assert.assertThat(moving, `is`(Grasp(move, piece, Position(1, 1), Position(1, 2)) as Moving<ShogiUnit, DummyTile>))//盤をクリックしても何も起きない
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //アシスト準備
        val assistReady = moving.pieceClick(Position(2, 2), piece1)
        Assert.assertThat(assistReady, `is`(Action(move, piece, Position(1, 1), Position(1, 2), piece1, Position(2, 2)) as Moving<ShogiUnit, DummyTile>))//行動準備
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //元の位置に一度戻す
        val back = assistReady.boardClick(Position(1, 1))
        Assert.assertThat(back, `is`(Grasp(move, piece, Position(1, 1), Position(1, 1)) as Moving<ShogiUnit, DummyTile>))//移動範囲をクリックしてたら移動中
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない

        //一歩前に
        val forward = back.boardClick(Position(1, 2))
        val retry = forward.pieceClick(Position(2, 2), piece1)
        Assert.assertThat(retry, `is`(Action(move, piece, Position(1, 1), Position(1, 2), piece1, Position(2, 2)) as Moving<ShogiUnit, DummyTile>))//行動準備
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 1)))
        Assert.assertThat(piece.newPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.MOVING))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 1)), `is`(piece as Piece<ShogiUnit, DummyTile>))//まだ確定してない
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 1)))//まだ確定してない
        //クリックして確定
        val act = retry.pieceClick(Position(2, 2), piece1)//駒をクリックしたら確定
        Assert.assertThat(act, `is`(NoMove(move) as Moving<ShogiUnit, DummyTile>))
        Assert.assertThat(piece.existsPosition, `is`(Position(1, 2)))
        Assert.assertThat(piece.newPosition, `is`(nullValue()))
        Assert.assertThat(piece.actionPhase, `is`(ActionPhase.ACTED))
        Assert.assertThat(physicalBoard.pieceAt(Position(1, 2)), `is`(piece as Piece<ShogiUnit, DummyTile>))//確定した
        Assert.assertThat(physicalBoard.positionOf(piece), `is`(Position(1, 2)))//確定した
    }
}