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
class MyGame(stage: Stage, batch: SpriteBatch, liner: ShapeRenderer, bitmapFont: BitmapFont, localWidth: Float, localHeight: Float) {
    private val footerHeight = 80f
    private val headerHeight = 160f
    private val marginLeft = 0f
    private val marginRight = 0f
    private val vLines = 8
    private val hLines = 6
    val board: Board<BattleUnit, Ground> = Board(hLines, vLines)
    val uiBoard: UiBoard = UiBoard(stage, batch, liner, bitmapFont, localWidth, localHeight, headerHeight, footerHeight, marginLeft, marginRight, board)

    init {
        stage.addListener(uiBoard)
    }
}