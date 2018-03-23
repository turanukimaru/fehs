package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import jp.blogspot.turanukimaru.board.Board
import jp.blogspot.turanukimaru.board.UiBoard

/**
 * Created by turanukimaru on 2018/03/15.
 */
class MyGame(val stage: Stage, val batch: SpriteBatch, val liner: ShapeRenderer, val bitmapFont: BitmapFont, val LOGICAL_WIDTH: Float, val LOGICAL_HEIGHT: Float) {
    val FOOTER_HEIGHT = 80f
    val HEADER_HEIGHT = 160f
    val MARGIN_LEFT = 0f
    val MARGIN_RIGHT = 0f
    val vLines = 8
    val hLines = 6
    val board: Board<Ground> =         Board( hLines, vLines)
    var uiBoard: UiBoard = UiBoard(stage, batch, liner, bitmapFont, LOGICAL_WIDTH, LOGICAL_HEIGHT, HEADER_HEIGHT, FOOTER_HEIGHT, MARGIN_LEFT, MARGIN_RIGHT,board)

    init {
        stage.addListener(uiBoard)
    }
}