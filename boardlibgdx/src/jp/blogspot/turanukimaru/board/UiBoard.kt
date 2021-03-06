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
import jp.blogspot.turanukimaru.playboard.Board
import jp.blogspot.turanukimaru.playboard.Position

/**
 * 盤面とlibGDXの間
 */
class UiBoard(val stage: Stage, val batch: SpriteBatch, private val liner: ShapeRenderer, val bitmapFont: BitmapFont, private val width: Float, val height: Float, private val marginTop: Float, private val marginBottom: Float, private val marginLeft: Float, private val marginRight: Float,
              /**
               * 論理的な盤面。この盤面に操作を伝える
               */
              val board: Board<*, *>
) : ClickListener() {

    /**
     * 行動時に表示されるボタン
     */
    var optionButton: Image? = null

    /**
     * 数字表示用テクスチャ
     */
    val numberRegions = mutableListOf<TextureRegion>()

    /**
     * libGDX の localUpdate を伝えるためのリスト
     */
    val uiPieceList = mutableListOf<UiPiece>()

    /**
     * 盤面の背景画像
     */
    var stageTexture: Texture? = null

    /**
     * 枡幅
     */
    private val squareWidth = (width - marginLeft - marginRight) / board.physics.horizontalLines

    /**
     * 枡高さ
     */
    private val squareHeight = (height - marginBottom - marginTop) / board.physics.verticalLines

    private fun posXtoSquareX(x: Float) = minOf(((x - marginLeft) / squareWidth).toInt(), board.physics.horizontalLines - 1)

    private fun posYtoSquareY(y: Float) = minOf(((y - marginBottom) / squareHeight).toInt(), board.physics.verticalLines - 1)

    fun squareYtoPosY(y: Int) = y * squareHeight + marginBottom

    fun squareXtoPosX(x: Int) = x * squareWidth + marginLeft

    private fun xyToPosition(x: Float, y: Float) = Position(posXtoSquareX(x), posYtoSquareY(y))

    /**
     *     ドラッグ判定用X
     */
    var touchStartX = 0f

    /**
     *     ドラッグ判定用Y
     */
    var touchStartY = 0f

    /**
     * update時に盤外に表示する関数。
     */
    private var updateInfo: (uiBoard: UiBoard) -> Boolean = { _ -> true }

    /**
     *
     */
    private var infoRank = 0

    /**
     * update時に盤外に表示する
     * rank は優先度で、0のときは強制。ところでなんで優先度なんて入れたんだっけ…？
     */
    fun setInfo(updateInfo: (uiBoard: UiBoard) -> Boolean, rank: Int) {
        println("rank : $rank / infoRank : $infoRank")
        if (rank in 1 until infoRank) return
        this.updateInfo = updateInfo
        infoRank = rank
    }

    /**
     * 盤面がクリックされたときに起動する…のだがタッチとタッチアップが同じときはクリックと判定するので全体を覆うときは実質TouchUp
     * touchUp と違ってドラッグ後は出たりでなかったりするから使えないな…
     */
    override fun clicked(event: InputEvent, x: Float, y: Float) {
    }

    /**
     * タッチ検出。ボードにタッチ位置をPositionで伝える。
     */
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        touchStartX = x
        touchStartY = y
        val result = super.touchDown(event, x, y, pointer, button)
        board.touch(xyToPosition(x, y))
        return result
    }

    /**
     * タッチ終了検出。ボードにタッチ位置をPositionで伝える。
     * タッチしたら。盤面内だったらBoardにメッセージが飛ぶ。ただ、盤面買いをクリックしたらキャンセルとか必要になるかもなあ。
     * いや、そのときはキャンセルボタンがあるべきか？
     */
    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        super.touchUp(event, x, y, pointer, button)
        println("${event?.isHandled} ならさらなるハンドリングを防ぐ")
        if (event?.isHandled == true) return // この形なら warning 出ないけどなんか釈然としないな…
        if (y > marginBottom && y < height - marginBottom) board.click(xyToPosition(x, y))
//        //クリックかどうかを判定するコード。superからの移植だが初期化されず動作終わったフラグが立ってるだけなのでそのまま動く
//        val touchUpOver = isOver(event?.listenerActor ?: return, x, y)
//        // Ignore touch up if the wrong mouse button.
//        if (touchUpOver && pointer == 0 && this.button != -1 && button != this.button) return
//        //trueのときはsuperでクリックが起動されている
//        if (!touchUpOver) {
//            board.move.drop(xyToPosition(x, y))
//        }
//        board.move.touchUp()//elseにすべきか…？
        touchStartX = 0f
        touchStartY = 0f
//        println("$event: InputEvent?, $x: Float, $y: Float, $pointer: Int, $button: Int")
    }

    /**
     * ドラッグ検出。ボードにドラッグ位置をPositionで伝える。
     * こっちの x,y は絶対値なのか…ん？つまり移動量ではなくactorの中での座標を指すのか！なるほど動くactorでは使いにくいわけだ
     *
     */
    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
        super.touchDragged(event, x, y, pointer)
        //チャタリング対策。実機だとちゃたるんだろうなあ
        //touchStartX += x.toInt()
        //touchStartY += y.toInt()
        //if (touchStartX > 14 || touchStartX < -14 || touchStartY > 14 || touchStartY < -14)
        board.drag(xyToPosition(x, y), x - touchStartX, y - touchStartY)
    }

    init {
        //inputProcessor セットしようとしたがするとアクターが動かない
    }

    /**
     * libGDXのupdateから呼ぶこと。枠線を引いた後に論理盤面のupdateを呼ぶ
     */
    fun libUpdate() {
        liner.projectionMatrix = stage.camera.combined
        liner.begin(ShapeRenderer.ShapeType.Line)
        liner.color = Color.WHITE
        //枠を引く
        if (board.physics.verticalLines > 1) {
            liner.line(1f, marginBottom, width, marginBottom)
            (1..board.physics.verticalLines).forEach { v ->
                liner.line(1f, v * squareHeight + marginBottom, width, v * squareHeight + marginBottom)
            }
        }
        if (board.physics.horizontalLines > 1) {
            liner.line(1f, marginBottom, 1f, height - marginTop)
            (1..board.physics.horizontalLines).forEach { v ->
                liner.line(v * squareWidth, marginBottom, v * squareWidth, height - marginTop)
            }
        }
        liner.end()
        //駒が選択されていたら移動範囲を表示する。モードを切り替えるならボード側に色情報を持たせる必要があるな
//        println(board.move.moving.selectedPiece)
        if (board.move.moving.selectedPiece != null) {
            board.physics.horizontalIndexes.forEach { x ->
                board.physics.verticalIndexes.forEach { y ->
                    when {//優先順位はアシスト可能・移動可能・攻撃可能・なら大丈夫かな？
                        board.move.moving.selectedPiece!!.actionableAt(Position(x, y)) >= 128 -> {//自分の位置を緑に塗ってしまうバグがあるので直したいが…
                            fillSquare(x, y, FillType.SUPPORTABLE)
                        }
                        board.move.moving.selectedPiece!!.searchedRouteAt(Position(x, y)) >= 0 -> {
                            fillSquare(x, y, FillType.MOVABLE)
                        }
                        board.move.moving.selectedPiece!!.actionableAt(Position(x, y)) >= 0 -> {
                            fillSquare(x, y, FillType.ACTIONABLE)
                        }
                    }
                }
            }
            //ルートから外れたら掘りなおさないとなあ。移動力超えたらか？直線矢印で十分かなあ
            //This inspection reports any declarations that can be destructuredが出たらこう書ける
            board.move.routeStack.forEach { (x, y) ->
                fillSquare(x, y, FillType.PASS)
            }
        }
        uiPieceList.forEach { it.libUpdate() }
    }

    /**
     * 盤面がタッチされたときにupdateタイミングで呼び出される。
     * 盤面の触っている枡の色を変えるコードがあるが一般的にはカーソルとかエフェクトが出るやつ
     * clickListener とは別ルートで fire させてるだけなので消えそう
     * ずれがある…ClickListenerのがずれが少ないからこっちはないか枠以外のエフェクトにしたほうがよさそう
     */
    fun touched(touchPos: Vector3) {
        val pos = Vector3(touchPos)
        stage.camera.unproject(pos)
        if (pos.y > marginBottom && pos.y < height - marginBottom) {
            liner.begin(ShapeRenderer.ShapeType.Line)
            liner.color = Color.RED
            liner.rect(posXtoSquareX(pos.x) * squareWidth, posYtoSquareY(pos.y) * squareHeight + marginBottom, squareWidth, squareHeight)
            liner.end()
        }
    }

    /**
     * libGDXの座標から盤面の座標に変換
     */
    private fun posToPosition(pos: Vector3): Position {
        val unProjectedPos = stage.camera.unproject(pos)
        return Position(posXtoSquareX(unProjectedPos.x), posYtoSquareY(unProjectedPos.y))
    }

    /**
     * タッチされた場所を盤面の座標で出す
     */
    fun touchedPosition(): Position =
            posToPosition(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))

    /**
     * 枡を塗る。a blendingを毎回設定してendしてるので無茶苦茶遅いはず。処理の順番見直さないとな
     */
    private fun fillSquare(x: Int, y: Int, fillType: FillType) {
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA)
        liner.projectionMatrix = stage.camera.combined
        liner.begin(ShapeRenderer.ShapeType.Filled)
        liner.setColor(fillType.r, fillType.g, fillType.b, fillType.a)
        val lengthX = squareWidth
        val lengthY = squareHeight
        liner.rect(x * lengthX, y * lengthY + marginBottom, lengthX, lengthY)
        liner.end()
        Gdx.gl.glDisable(GL20.GL_BLEND)

    }

    /**
     * updateSpriteBatch時に呼ばれる。
     * 背景画像と情報枠を描く。
     * もう一つのUpdateと違うのはスプライトバッチだから画像の転送を目的としていること。
     */
    fun libUpdateSpriteBatch(batch: SpriteBatch) {
        batch.draw(stageTexture, 0f, marginBottom)
        //情報更新。場合によってはクリッピング
        let(updateInfo)
    }

    /**
     * オプションを表示する
     */
    fun showOptionButton(position: Position) {
        if (optionButton == null) return
        optionButton!!.x = squareXtoPosX(position.x)
        optionButton!!.y = squareYtoPosY(position.y) + squareHeight
        optionButton!!.toFront()
        optionButton!!.isVisible = true
    }

    /**
     * オプションを消す.隠す位置は適当
     */
    fun hideOptionButton() {
        println("hideOptionButton")
        optionButton?.x = -128f
        optionButton?.y = -128f
        optionButton?.isVisible = false
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
        image.addAction(showNumberAction(x, y, 0, delay) { image.remove() })
        stage.addActor(image)
    }

    /**
     * 特定の座標に数字を表示するアクションを作る
     */
    private fun showNumberAction(x: Int, y: Int, p: Int, delay: Float, terminateAction: () -> Boolean = { true }): SequenceAction {
        val seq = SequenceAction()
        seq.addAction(Actions.moveTo(squareXtoPosX(x) + 52 - p * 52, squareYtoPosY(y)))
        seq.addAction(Actions.delay(delay))
        seq.addAction(Actions.show())
        seq.addAction(Actions.moveBy(0f, 64f, 0.2f))
        seq.addAction(Actions.moveBy(0f, -64f, 0.2f))
        seq.addAction(Actions.delay(0.6f))
        seq.addAction(Actions.hide())
        seq.addAction(CallbackAction(terminateAction))
        return seq
    }

    /**
     * 枡の色
     */
    enum class FillType(val r: Float, val g: Float, val b: Float, val a: Float) {
        MOVABLE(0f, 0f, 1f, 0.75f),
        ACTIONABLE(1f, 0f, 0f, 0.75f),
        SUPPORTABLE(0f, 1f, 0f, 0.75f),
        PASS(0f, 0f, 1f, 0.4f),
    }

}

