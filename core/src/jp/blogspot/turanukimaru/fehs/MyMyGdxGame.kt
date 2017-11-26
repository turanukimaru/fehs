package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import jp.blogspot.turanukimaru.board.Board
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.board.UiPiece

/**
 * ゲーム本体。LibGDXサンプルソースがところどころ残ってるので削除せねば
 */
class MyMyGdxGame : ApplicationAdapter() {

    var dropImage: Texture? = null
    var bucketImage: Texture? = null
    var raindrops = arrayListOf<Rectangle>()
    var lastDropTime = 0L
    var batch: SpriteBatch? = null
    var camera: Camera? = null
    var bucket: Rectangle? = null
    var stage: Stage? = null
    var liner: ShapeRenderer? = null

    val LOGICAL_WIDTH = 540f//720f
    val LOGICAL_HEIGHT = 960f//1280f
    val FOOTER_HEIGHT = 80f
    val HEADER_HEIGHT = 160f
    val MARGIN_LEFT = 0f
    val MARGIN_RIGHT = 0f
    val vLines = 8
    val hLines = 6
    var board: Board<Ground>? = null
    var uiBoard: UiBoard? = null

    var textureDisposer = arrayListOf<Texture>()
    var imageDisposer = arrayListOf<Image>()
    var buttons = arrayListOf<Image>()

    var fontGenerator: FreeTypeFontGenerator? = null
    var bitmapFont: BitmapFont? = null

    var user = Board.Player()
    var enemy = Board.Player()

    val battleGround = arrayOf(
            arrayOf(Ground.P, Ground.P, Ground.W, Ground.R, Ground.M, Ground.M),
            arrayOf(Ground.R, Ground.P, Ground.R, Ground.R, Ground.M, Ground.M),
            arrayOf(Ground.P, Ground.P, Ground.M, Ground.M, Ground.M, Ground.M),
            arrayOf(Ground.P, Ground.P, Ground.M, Ground.M, Ground.M, Ground.M),
            arrayOf(Ground.M, Ground.P, Ground.P, Ground.P, Ground.M, Ground.M),
            arrayOf(Ground.M, Ground.P, Ground.P, Ground.P, Ground.P, Ground.P),
            arrayOf(Ground.M, Ground.M, Ground.M, Ground.P, Ground.P, Ground.P),
            arrayOf(Ground.M, Ground.M, Ground.M, Ground.M, Ground.P, Ground.P)

    )

    /**
     * 初期化。レンダラを毎回作ったりするのは重いのでここで一回で済ます。
     */
    override fun create() {
        //レンダラは重い
        liner = ShapeRenderer()
        //フォント対応
        val file = Gdx.files.local("assets/NotoSansCJKjp-Regular1.otf")
        fontGenerator = FreeTypeFontGenerator(file)
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.size = 32
        param.incremental = true
        bitmapFont = fontGenerator!!.generateFont(param)

        //ここからサンプルと言うか雨が落ちてくるアニメ用
        dropImage = Texture(Gdx.files.internal("droplet.png"))
        bucketImage = Texture(Gdx.files.internal("bucket.png"))
        batch = SpriteBatch()

        // create a Rectangle to logically represent the bucket
        bucket = Rectangle()
        bucket!!.x = (800 / 2 - 64 / 2).toFloat() // center the bucket horizontally
        bucket!!.y = 20f // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        bucket!!.width = 64f
        bucket!!.height = 64f

        // create the raindrops array and spawn the first raindrop
        spawnRaindrop()

        stage = Stage(FitViewport(LOGICAL_WIDTH, LOGICAL_HEIGHT))
        Gdx.input.inputProcessor = stage

        camera = stage!!.camera

        uiBoard = UiBoard(stage!!, batch!!, liner!!, bitmapFont!!, hLines, vLines, LOGICAL_WIDTH, LOGICAL_HEIGHT, HEADER_HEIGHT, FOOTER_HEIGHT, MARGIN_LEFT, MARGIN_RIGHT)
        stage!!.addListener(uiBoard)
        stage!!.addListener(uiBoard)
        board = Board(uiBoard!!)
        uiBoard!!.stageTexture = loadTexture("map1.png")

        //ダメージの数字はステージに追加して隠しておく。

        val numberTexture = loadTexture("number.png")
        (0..9).forEach { i ->
            val region = TextureRegion(numberTexture, i * 51, 0, 51, 96)
            uiBoard!!.numberRegions.add(region)

        }
//TODO:一人目のキャラここから
        //グループ作るのやばいな。重い。
        val medjedTexture = loadTexture("medjed.png")
        val regionA = TextureRegion(medjedTexture, 0, 0, 64, 64)
        val medjedImageA = Image(regionA)
        imageDisposer.add(medjedImageA)
        medjedImageA.setPosition(0f, 0f)
        val regionB = TextureRegion(medjedTexture, 64, 0, 64, 64)
        val medjedImageB = Image(regionB)
        imageDisposer.add(medjedImageB)
        medjedImageB.setPosition(0f, 0f)
        val group = Group()
        group.addActor(medjedImageA)
        group.addActor(medjedImageB)
        val piece1 = MyPiece(BattleUnit(ArmedHeroRepository.getById("マルス")!!, 40), UiPiece(group, uiBoard!!), board!!, user)
        user.pieceList.add(piece1)
        group.addListener(piece1.uiPiece)
        piece1.uiPiece.actors.add(medjedImageA)
        piece1.uiPiece.actors.add(medjedImageB)
        board!!.put(piece1, 5, 0)
//TODO:ここまで。なお現在の一覧画面から移動するようにした奴は別スレッドなのでRealmにアクセスすると落ちる。別インスタンスなら生きてるかな？

        val stageDroplet = loadTexture("lucina.png")
        val stageDropletImage1 = Image(stageDroplet)
        stageDropletImage1.setScale(0.5f)
        imageDisposer.add(stageDropletImage1)
        val piece2 = MyPiece(BattleUnit(ArmedHeroRepository.getById("ルキナ")!!, 40), UiPiece(stageDropletImage1, uiBoard!!), board!!, enemy)
        enemy.pieceList.add(piece2)
        stageDropletImage1.addListener(piece2.uiPiece)
        board!!.put(piece2, 1, 3)

        val hectorTexture = loadTexture("hector.png")
        val hectorImage = Image(hectorTexture)
        hectorImage.setScale(0.5f)
        imageDisposer.add(hectorImage)
        val piece3 = MyPiece(BattleUnit(ArmedHeroRepository.getById("ヘクトル")!!, 40), UiPiece(hectorImage, uiBoard!!), board!!, enemy)
        enemy.pieceList.add(piece3)
        hectorImage.addListener(piece3.uiPiece)
        board!!.put(piece3, 3, 3)

        //地形を盤面にコピー
        board!!.copyGroundSwitchXY(battleGround)

        //盤外のボタンなど。これも処理考え直す必要がありそう
        val turnendTexture = loadTexture("turnend.png")
        val turnendImage = Image(turnendTexture)
        imageDisposer.add(turnendImage)
        turnendImage.setPosition(64f, 0f)
        turnendImage.setScale(0.5f)
        buttons.add(turnendImage)
        turnendImage.addListener({ _ -> println("pushed turnend");turnend(board!!) })
        //どこでボタン管理するか考えないとなー
        stage!!.addActor(turnendImage!!)
        board!!.turn(user)
    }

    //ファイルからテクスチャ読み込み。実際には1ファイルに複数テクスチャを入れるので座標とかTextureのリストを返すとかの処理が必要になる
    private fun loadTexture(fileName: String): Texture {
        val texture = Texture(Gdx.files.internal(fileName))
        textureDisposer.add(texture)
        return texture
    }

    /**
     * ターンを終了して相手にターンを渡す
     */
    private fun turnend(board: Board<Ground>): Boolean {
        println("fire turnend!")
        if (board.owner == user) {
            board.turn(enemy)
        } else {
            board.turn(user)
        }
        return true
    }

    private fun spawnRaindrop() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0, 800 - 64).toFloat()
        raindrop.y = 480f
        raindrop.width = 64f
        raindrop.height = 64f
        raindrops.add(raindrop)
        lastDropTime = TimeUtils.nanoTime()
    }

    override fun render() {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        // tell the camera to update its matrices.
        camera!!.update()

        uiBoard!!.update()
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch!!.projectionMatrix = camera!!.combined

        // begin a new batch and draw the bucket and
        // all drops
        batch!!.begin()
        uiBoard!!.updateSpriteBatch(batch!!)
        batch!!.draw(bucketImage, bucket!!.x, bucket!!.y)
        for (raindrop in raindrops) {
            batch!!.draw(dropImage, raindrop.x, raindrop.y)
        }
        //なぜか消すと画面のFillが表示されなくなる。image.drawでstageとの調整をしているのでその都合かと思われる。
        buttons.forEach { b -> b.draw(batch, 100f) }

        bitmapFont!!.draw(batch, "${board?.oldPosition}\nデバッグ用文字", 50f, 300f)
        batch!!.end()
        stage!!.act(Gdx.graphics.deltaTime)
        stage!!.draw()

        // process user input
        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            uiBoard!!.touched(touchPos)
            camera!!.unproject(touchPos)
            bucket!!.x = touchPos.x - 64 / 2
            //yは下から上へ計算する
//            stageBucketImage!!.setPosition(touchPos.x - 64 / 2, touchPos.y - 64 / 2)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket!!.x -= 200 * Gdx.graphics.deltaTime
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket!!.x += 200 * Gdx.graphics.deltaTime

        // make sure the bucket stays within the screen bounds
        if (bucket!!.x < 0) bucket!!.x = 0f
        if (bucket!!.x > 800 - 64) bucket!!.x = 800f - 64

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop()

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.

        //画面外やバケツに触れたものを消す。けどこれって増える一方だな・・・
        val newDrops = raindrops.filter { raindrop ->
            raindrop.y -= 200 * Gdx.graphics.deltaTime
            (raindrop.y + 64 > 0) && !raindrop.overlaps(bucket)
        }
        raindrops.clear()
        raindrops.addAll(newDrops)

    }

    override fun dispose() {
        // dispose of all the native resources
        dropImage?.dispose()
        bucketImage?.dispose()
//        dropSound.dispose()
//        rainMusic.dispose()
        batch?.dispose()
        liner?.dispose()
    }
}

