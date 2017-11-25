package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

/**
 * 盤面とlibGDXの間
 */
class UiBoard(val stage: Stage, val batch: SpriteBatch, val liner: ShapeRenderer, val bitmapFont: BitmapFont, val horizontalLines: Int, val verticalLines: Int, val width: Float, val height: Float, val marginTop: Float, val marginBottom: Float, val marginLeft: Float, val marginRight: Float) : ClickListener() {
    /**
     * 10の位の数字を表示するためのアクション。桁数を増やすなら配列の配列にしないとなあ
     */
    val sequenceNumberB = arrayListOf<SequenceAction>()
    /**
     * 1の位の数字を表示するためのアクション
     */
    val sequenceNumberS = arrayListOf<SequenceAction>()

    val numberRegions = arrayListOf<TextureRegion>()

    /**
     * 盤面がクリックされたときに起動する…のだがタッチとかタッチアップとかとの使い分け方が分からない。これをメインにしないほうが良いかもしれない
     */
    override fun clicked(event: InputEvent, x: Float, y: Float) {
        super.clicked(event, x, y)
        println("UiBoardがクリックされた！")
        println(event.isStopped)
        println(event.isCancelled)
        println(event.isTouchFocusCancel)
        println("UiBoardがクリックされた！")
        //駒がクリックされたのでないときはボードがクリックされたこととする...がとりあえずタッチだけ動かそう
//        if (!event.isStopped) {
//            board?.clicked(event, posToPosition(Vector3(x, y, 0f)))
//        }
    }

    /**
     * 盤面の背景画像
     */
    var stageTexture: Texture? = null
    /**
     * 論理的な盤面。この盤面に操作を伝える
     */
    var board: Board<*>? = null
    /**
     * update時に盤外に表示する関数
     */
    var updateInfo: (uiBoard: UiBoard) -> Boolean = { _ -> true }

    /**
     * 盤面に駒のアクターを配置する
     */
    fun put(piece: UiPiece, x: Int, y: Int) {
        piece.actor.setPosition(squareXtoPosX(x), posYtoSquareY(y))
        stage.addActor(piece.actor)
    }

    init {
        //inputProcessor セットしようとしたがするとアクターが動かない
    }

    /**
     * libGDXのupdateから呼ぶこと。枠線を引いた後に論理盤面のupdateを呼ぶ
     */
    fun update() {
        liner.projectionMatrix = stage.camera.combined
        liner.begin(ShapeRenderer.ShapeType.Line)
        liner.color = Color.WHITE
        val h = squareHeight()
        //枠を引く
        if (verticalLines > 1) {
            liner.line(1f, marginBottom, width, marginBottom)
            (1..verticalLines).forEach { v ->
                liner.line(1f, v * h + marginBottom, width, v * h + marginBottom)

            }
        }
        val w = squareWidth()
        if (horizontalLines > 1) {
            liner.line(1f, marginBottom, 1f, height - marginTop)
            (1..horizontalLines).forEach { v ->
                liner.line(v * w, marginBottom, v * w, height - marginTop)

            }
        }
        liner.end()

        board!!.update()
    }

    /**
     * 盤面がタッチされたときに起動する。盤面の触っている枡の色を変えるコードがあるがこれもう変えたほうが良いか
     */
    fun touched(touchPos: Vector3) {
        val pos = Vector3(touchPos)
        stage.camera.unproject(pos)
        if (pos.y > marginBottom && pos.y < height - marginBottom) {
            val lengthX = squareWidth()
            val lengthY = squareHeight()

            liner.begin(ShapeRenderer.ShapeType.Line)
            liner.color = Color.RED
            liner.rect(posXtoSquareX(pos.x) * lengthX, posYtoSquareY(pos.y) * lengthY + marginBottom, lengthX, lengthY)
            liner.end()
        }

    }

    /**
     * 枡幅
     */
    private fun squareWidth() = width / horizontalLines

    /**
     * 枡高さ
     */
    private fun squareHeight() = (height - marginBottom - marginTop) / verticalLines

    private fun posXtoSquareX(x: Float) = (x / squareWidth()).toInt()

    private fun posYtoSquareY(y: Float) = ((y - marginBottom) / squareHeight()).toInt()

    private fun posYtoSquareY(y: Int) = y * squareHeight() + marginBottom

    private fun squareXtoPosX(x: Int) = x * squareWidth()

    /**
     * 論理盤面をセットする
     */
    fun setBoardListener(board: Board<*>) {
        this.board = board
    }

    /**
     * libGDXの座標から盤面の座標に変換
     */
    private fun posToPosition(pos: Vector3): Position {
        val unProjectedPos = stage.camera.unproject(pos)
        return Position(posXtoSquareX(unProjectedPos.x), posYtoSquareY(unProjectedPos.y))
    }

    /**
     * タッチした場所が盤面内に入っているかのチェック
     */
    fun posIsOnBoard(pos: Vector3): Boolean {
        val unProjectedPos = stage.camera.unproject(pos)
        return unProjectedPos.y > marginBottom && unProjectedPos.y < marginBottom + height
    }

    /**
     * タッチされた場所を盤面の座標で出す
     */
    fun touchedPosition(): Position {
        return posToPosition(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
    }

    //TODO:アクターのサイズが分からないから中心が出ない...
    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun actionMoveToPosition(piece: UiPiece, x: Int, y: Int): SequenceAction {
        val seq = SequenceAction()
        val finalX = squareXtoPosX(x)
        val finalY = posYtoSquareY(y)
        seq.addAction(Actions.moveBy(finalX - piece.actor.x, finalY - piece.actor.y, 0.2f))
//        seq.addAction(Actions.moveTo(finalX, finalY))
        return seq
    }

    /**
     * 位置移動のアニメ。移動差分を駒に登録する
     */
    fun setToPosition(piece: UiPiece, x: Int, y: Int) {
        val finalX = squareXtoPosX(x)
        val finalY = posYtoSquareY(y)
        piece.actor.setPosition(finalX, finalY)
    }

    /**
     * 枡を塗る。a blendingを毎回設定してendしてるので無茶苦茶遅いはず。処理の順番見直さないとな
     */
    fun fillSquare(x: Int, y: Int, fillType: FillType) {
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        liner.projectionMatrix = stage.camera.combined
        liner.begin(ShapeRenderer.ShapeType.Filled)
        liner.color = fillType.color
        val lengthX = squareWidth()
        val lengthY = squareHeight()
        liner.rect(x * lengthX, y * lengthY + marginBottom, lengthX, lengthY)
        liner.end()
        Gdx.gl.glDisable(GL20.GL_BLEND)

    }

    /**
     * 盤面の座標
     */
    data class Position(val x: Int, val y: Int)


    /**
     * updateSpriteBatch時に呼ぶこと。背景や盤外の情報を描く。枠線を引くより前に呼ぶ必要があるのでSpriteBatchじゃないほうがいいかも・・・？
     */
    fun updateSpriteBatch(batch: SpriteBatch) {
        batch.draw(stageTexture, 0f, marginBottom)
        //情報更新。場合によってはクリッピング
        updateInfo(this)

    }

    /**
     * 盤面の座標が盤上に有るか。つまりIndexがマイナスになったり幅を超えたりしていないか
     */
    fun positionIsOnBoard(position: Position): Boolean {
        return position.x >= 0 && position.y >= 0 && position.x < horizontalLines && position.y < verticalLines
    }

    /**
     * 特定の座標に3桁までの数字を表示するアクションを追加する。本来は桁数の制限が無いほうが良いのだが
     */
    fun showNumbers(x: Int, y: Int, damage: Int, delay: Float) {
        val d2 = damage / 100
        val d1 = (damage - d2 * 100) / 10
        val d0 = damage - d2 * 100 - d1 * 10
        if (d2 > 0) {
            val image = Image(numberRegions[d2])
            image.isVisible = false
            image.addAction(showNumberAction(x, y, 2, delay))
            stage.addActor(image)
        }
        if (d1 > 0 || d2 > 0) {
            val image = Image(numberRegions[d1])
            image.isVisible = false
            image.addAction(showNumberAction(x, y, 1, delay))
            stage.addActor(image)
        }
        val image = Image(numberRegions[d0])
        image.isVisible = false
        image.addAction(showNumberAction(x, y, 0, delay))
        stage.addActor(image)
    }

    /**
     * 特定の座標に数字を表示するアクションを作る
     */
    private fun showNumberAction(x: Int, y: Int, p: Int, delay: Float): SequenceAction {

        val seq = SequenceAction()
        seq.addAction(Actions.moveTo(squareXtoPosX(x) + 52 - p * 52, posYtoSquareY(y)))
        seq.addAction(Actions.delay(delay))
        seq.addAction(Actions.show())
        seq.addAction(Actions.moveBy(0f, 64f, 0.2f))
        seq.addAction(Actions.moveBy(0f, -64f, 0.2f))
        seq.addAction(Actions.delay(0.6f))
        seq.addAction(Actions.hide())
        //画面外に移動させる。 TODO:アクションが終わったのを検出してactorをremoveしたい
        seq.addAction(Actions.moveTo(width, height))
        return seq
    }

    enum class FillType(val color: Color) {
        MOVABLE(Color.BLUE),
        ATTACKABLE(Color.RED),
        PASS(Color.GREEN)

    }
}
