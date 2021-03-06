package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.playboard.*

/**
 * Created by turanukimaru on 2018/03/15.
 */
class MyGame(stage: Stage, batch: SpriteBatch, liner: ShapeRenderer, bitmapFont: BitmapFont, localWidth: Float, localHeight: Float, var playerA: Player, var playerB: Player,
             private val vLines: Int = 8,
             private val hLines: Int = 6,
             val board: Board<MyPiece, Tile> = Board(PhysicalBoard(hLines, vLines))
) : BoardListener {
    override fun showOption(position: Position) {
        uiBoard.showOptionButton(position)
    }

    override fun hideOption() {
        uiBoard.hideOptionButton()
    }


    private val footerHeight = 80f
    private val headerHeight = 160f
    private val marginLeft = 0f
    private val marginRight = 0f

    //    val physicalBoard = BattleFieldContent
    val controller: Game<MyPiece, Tile> = Game(board, playerA, playerB)
    val uiBoard: UiBoard = UiBoard(stage, batch, liner, bitmapFont, localWidth, localHeight, headerHeight, footerHeight, marginLeft, marginRight, controller.board)

    init {
        stage.addListener(uiBoard)
        controller.board.listener = this
    }

    fun put(piece2: MyPiece, x: Int, y: Int, uiPiece2: MyUiPiece, actor: Actor) {
        controller.board.physics.put(piece2, x, y)
        uiBoard.uiPieceList.add(uiPiece2)
        uiBoard.stage.addActor(actor)
    }
}