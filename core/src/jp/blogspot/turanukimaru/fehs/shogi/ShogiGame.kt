package jp.blogspot.turanukimaru.fehs.shogi

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.playboard.Board
import jp.blogspot.turanukimaru.playboard.BoardListener
import jp.blogspot.turanukimaru.playboard.Player
import jp.blogspot.turanukimaru.playboard.Position

/**
 * Created by turanukimaru on 2018/03/15.
 */
class ShogiGame(stage: Stage, batch: SpriteBatch, liner: ShapeRenderer, bitmapFont: BitmapFont, localWidth: Float, localHeight: Float) : BoardListener {
    override fun showOption(position: Position) {
        uiBoard.showOptionButton(position)
    }

    override fun hideOption() {
        uiBoard.hideOptionButton()
    }

    /**
     * ターンを終了して相手にターンを渡す...にしても雑だな
     */
    override fun turnEnd() {
        board.move.moveCommit()
        if (board.owner == playerA) {
            board.turnStart(playerB!!)
        } else {
            board.turnStart(playerA!!)
        }
    }

    override fun actionDone() {
        if (board.physic.pieceList.none { it.owner == playerA }) {
            println("TODO:playerBの勝利")
            board.gameReset(playerA!!)
        }
        if (board.physic.pieceList.none { it.owner == playerB }) {
            println("TODO:playerAの勝利")
            board.gameReset(playerA!!)
        }
    }

    var playerA: Player? = null
    var playerB: Player? = null
    private val footerHeight = 240f
    private val headerHeight = 240f
    private val marginLeft = 0f
    private val marginRight = 0f
    private val vLines = 9
    private val hLines = 9
    val board: Board<ShogiUnit, Ground> = Board(hLines, vLines)
    val uiBoard: UiBoard = UiBoard(stage, batch, liner, bitmapFont, localWidth, localHeight, headerHeight, footerHeight, marginLeft, marginRight, board)

    init {
        stage.addListener(uiBoard)
        board.listener = this
    }

    fun put(piece2: ShogiPiece, x: Int, y: Int, uiPiece2: MyUiPiece, actor: Actor, orientation: Int = 0) {
        board.physic.put(piece2, x, y, orientation)
        uiBoard.uiPieceList.add(uiPiece2)
        uiBoard.stage.addActor(actor)
    }
}