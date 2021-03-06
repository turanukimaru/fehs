package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
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
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import jp.blogspot.turanukimaru.board.ActionListener
import jp.blogspot.turanukimaru.playboard.Board
import jp.blogspot.turanukimaru.playboard.Player

/**
 * ゲーム本体。LibGDXサンプルソースがところどころ残ってるので削除せねば...
 */
class MyGdxGame : ApplicationAdapter() {

    var dropImage: Texture? = null
    var bucketImage: Texture? = null
    var raindrops = mutableListOf<Rectangle>()
    var lastDropTime = 0L
    var batch: SpriteBatch? = null
    var camera: Camera? = null

    //    var bucket: Rectangle? = null
    var stage: Stage? = null
    var liner: ShapeRenderer? = null

    private val logicalWidth = 540f//720f
    private val logicalHeight = 960f//1280f
    var bitmapFont: BitmapFont? = null

    var user = Player()
    var enemy = Player()
    private val myGame: MyGame by lazy {
        MyGame(stage!!, batch!!, liner!!, bitmapFont!!, logicalWidth, logicalHeight, user, enemy
                , board = Board(BattleFieldRepository.create(6, 8)))
    } //マネージドボードをリポジトリから取得

    var textureDisposer = mutableListOf<Texture>()
    var imageDisposer = mutableListOf<Image>()
    var buttons = mutableListOf<Image>()

    var fontGenerator: FreeTypeFontGenerator? = null


    private val battleGround = arrayOf(
            arrayOf(Tile.P, Tile.P, Tile.W, Tile.R, Tile.M, Tile.M),
            arrayOf(Tile.R, Tile.P, Tile.R, Tile.R, Tile.M, Tile.M),
            arrayOf(Tile.P, Tile.P, Tile.M, Tile.M, Tile.M, Tile.M),
            arrayOf(Tile.P, Tile.P, Tile.M, Tile.M, Tile.M, Tile.M),
            arrayOf(Tile.M, Tile.P, Tile.P, Tile.P, Tile.M, Tile.M),
            arrayOf(Tile.M, Tile.P, Tile.P, Tile.P, Tile.P, Tile.P),
            arrayOf(Tile.M, Tile.M, Tile.M, Tile.P, Tile.P, Tile.P),
            arrayOf(Tile.M, Tile.M, Tile.M, Tile.M, Tile.P, Tile.P)
    )

    /**
     * 初期化。レンダラを毎回作ったりするのは重いのでここで一回で済ます。
     * kotlinでもここ必要なのかな…？
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
//        bucket = Rectangle()
//        bucket!!.x = (800 / 2 - 64 / 2).toFloat() // center the bucket horizontally
//        bucket!!.y = 20f // bottom left corner of the bucket is 20 pixels above the bottom screen edge
//        bucket!!.width = 64f
//        bucket!!.height = 64f

        // create the raindrops array and spawn the first raindrop
        spawnRaindrop()

        stage = Stage(FitViewport(logicalWidth, logicalHeight))
        Gdx.input.inputProcessor = stage

        camera = stage!!.camera

        createStage()
        buildLoadButton()//ここでID渡せばいいかな…
    }

    private fun createStage() {
        myGame.uiBoard.stageTexture = loadTexture("map1.png")

        //ダメージの数字
        val numberTexture = loadTexture("number.png")
        (0..9).forEach { i ->
            val region = TextureRegion(numberTexture, i * 51, 0, 51, 96)
            myGame.uiBoard.numberRegions.add(region)
        }

        //地形を盤面にコピー
        myGame.controller.board.physics.copyTilesSwitchXY(battleGround)

        buildTurnEndButton()

        //一人目のキャラここから
        //グループ作るのやばいな。重い。
        //元々はスプライト actor にクリックリスナをつけていたが、正直重なったときの処理とか大変だし全部ボードへのイベントでいいや
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
        val listener = ActionListener(group, myGame.uiBoard, myGame.controller)
        val piece1 = MyPiece(BattleUnit(ArmedHeroRepository.getById("マルス")!!, 40), myGame.controller.board, user, listener)
        val uiPiece = MyUiPiece(group, myGame.uiBoard, piece1)
//        group.addListener(uiPiece)
        listener.actors.add(medjedImageA)
        listener.actors.add(medjedImageB)
        myGame.put(piece1, 5, 0, uiPiece, group)
        //ここまで。なお現在の一覧画面から移動するようにした奴は別スレッドなのでRealmにアクセスすると落ちる。別インスタンスなら生きてるかな？

        //味方を置いてみる。
        val lucinaTexture0 = loadTexture("lucina.png")
        val lucinaImage0 = Image(lucinaTexture0)
        lucinaImage0.setScale(0.5f)
        imageDisposer.add(lucinaImage0)
        val piece0 = MyPiece(BattleUnit(ArmedHeroRepository.getById("ヴィオール")!!, 40), myGame.controller.board, user, ActionListener(lucinaImage0, myGame.uiBoard, myGame.controller))
        val uiPiece0 = MyUiPiece(lucinaImage0, myGame.uiBoard, piece0)
//        lucinaImage0.addListener(uiPiece0)
        myGame.put(piece0, 5, 2, uiPiece0, lucinaImage0)

        val lucinaTexture = loadTexture("lucina.png")
        val lucinaImage = Image(lucinaTexture)
        lucinaImage.setScale(0.5f)
        imageDisposer.add(lucinaImage)
        val piece2 = MyPiece(BattleUnit(ArmedHeroRepository.getById("ルキナ")!!, 40), myGame.controller.board, enemy, ActionListener(lucinaImage, myGame.uiBoard, myGame.controller))
        val uiPiece2 = MyUiPiece(lucinaImage, myGame.uiBoard, piece2)
//        lucinaImage.addListener(uiPiece2)
        myGame.put(piece2, 1, 3, uiPiece2, lucinaImage)

        val hectorTexture = loadTexture("hector.png")
        val hectorImage = Image(hectorTexture)
        hectorImage.setScale(0.5f)
        imageDisposer.add(hectorImage)
        val piece3 = MyPiece(BattleUnit(ArmedHeroRepository.getById("ヘクトル")!!, 40), myGame.controller.board, enemy, ActionListener(hectorImage, myGame.uiBoard, myGame.controller))
        val uiPiece3 = MyUiPiece(hectorImage, myGame.uiBoard, piece3)
//        hectorImage.addListener(uiPiece3)
        myGame.put(piece3, 3, 3, uiPiece3, hectorImage)
        myGame.playerA = user
        myGame.playerB = enemy
        myGame.controller.board.gameStart(user)
    }

    private fun buildTurnEndButton() {
        //盤外のボタンなど。これも処理考え直す必要がありそう
        val texture = loadTexture("turnend.png")
        val image = Image(texture)
        imageDisposer.add(image)
        image.setPosition(64f, 0f)
        image.setScale(0.5f)
        buttons.add(image)

        image.addListener(object : ClickListener() {
            //ダウンとアップが同じときにクリックと判定するようだが長押し判定が無いので使いにくい…ボタンには使えるがキャラをドラッグした後には使えないなあ
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                println("pushed turnEnd")
                myGame.controller.turnEnd()
            }
        })
        //どこでボタン管理するか考えないとなー
        stage!!.addActor(image)
    }

    private fun buildLoadButton() {
        //デバッグ用にDBから集約を取り出すボタン。結局Androidのライフサイクルは手動で確認するしかないんだよな…
        val texture = loadTexture("bucket.png")
        val image = Image(texture)
        imageDisposer.add(image)
        image.setPosition(192f, 0f)
        buttons.add(image)

        image.addListener(object : ClickListener() {
            //ダウンとアップが同じときにクリックと判定するようだが長押し判定が無いので使いにくい…ボタンには使えるがキャラをドラッグした後には使えないなあ
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                val persistence = BattleFieldRepository.getById(1)!!
                val board = Board(persistence)
                board.load()
                println(board)
                println(board.physics)
                println(board.physics.pieceList)
                board.physics.pieceList.forEach { println(it) }
            }
        })
        //どこでボタン管理するか考えないとなー
        stage!!.addActor(image)
    }

    //ファイルからテクスチャ読み込み。実際には1ファイルに複数テクスチャを入れるので座標とかTextureのリストを返すとかの処理が必要になる
    private fun loadTexture(fileName: String): Texture {
        val texture = Texture(Gdx.files.internal(fileName))
        textureDisposer.add(texture)
        return texture
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
//        println("マイフレーム呼ばれてるはずなんだけどな…？")
        // reset the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to reset the screen.
        //UIBoardに出してもいいけどBoardに出しちゃいけない部分だよなあ
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        // tell the camera to libUpdate its matrices.
        camera!!.update()

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch!!.projectionMatrix = camera!!.combined

        // begin a new batch and draw the bucket and
        // all drops
        batch!!.begin()
        myGame.uiBoard.libUpdateSpriteBatch(batch!!)
//        batch!!.draw(bucketImage, bucket!!.x, bucket!!.y)
        for (raindrop in raindrops) {
            batch!!.draw(dropImage, raindrop.x, raindrop.y)
        }

        val hand = myGame.controller.board.move
        bitmapFont!!.draw(batch, "touchStartX:${myGame.uiBoard.touchStartX}", 50f, 510f)
        bitmapFont!!.draw(batch, "touchStartY:${myGame.uiBoard.touchStartY}", 50f, 540f)
        bitmapFont!!.draw(batch, "from:${hand.moving.from}", 50f, 630f)
        bitmapFont!!.draw(batch, "to:${hand.moving.to}", 50f, 660f)
        myGame.controller.board.physics.pieceList.forEach {
            if (it.charPosition != null) bitmapFont!!.draw(batch, "${it.contains.containUnit.armedHero.name} ${it.charPosition.x} ${it.charPosition.y}\n", myGame.uiBoard.squareXtoPosX(it.charPosition.x), myGame.uiBoard.squareYtoPosY(it.charPosition.y))
        }
        batch!!.end()
        myGame.uiBoard.libUpdate()
        try {
            stage!!.act(Gdx.graphics.deltaTime)
        } catch (e: NullPointerException) {
            e.printStackTrace()
            println(stage!!.actors)
        }
        stage!!.draw()

        // process user input
        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            myGame.uiBoard.touched(touchPos)
            camera!!.unproject(touchPos)
//            bucket!!.x = touchPos.x - 64 / 2
            //yは下から上へ計算する
//            stageBucketImage!!.setPiece(touchPos.x - 64 / 2, touchPos.y - 64 / 2)
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket!!.x -= 200 * Gdx.graphics.deltaTime
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket!!.x += 200 * Gdx.graphics.deltaTime

//        // make sure the bucket stays within the screen bounds
//        if (bucket!!.x < 0) bucket!!.x = 0f
//        if (bucket!!.x > 800 - 64) bucket!!.x = 800f - 64

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop()

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.

        //画面外やバケツに触れたものを消す。けどこれって増える一方だな・・・
//        val newDrops = raindrops.filter { raindrop ->
//            raindrop.y -= 200 * Gdx.graphics.deltaTime
//            (raindrop.y + 64 > 0) && !raindrop.overlaps(bucket)
//        }
        raindrops.clear()
//        raindrops.addAll(newDrops)

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

