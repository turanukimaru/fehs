package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import jp.blogspot.turanukimaru.playboard.Piece
import jp.blogspot.turanukimaru.playboard.TouchPhase

/**
 * 駒とLibGDXの間
 * 駒をドラッグしたいところだが、どうもドラッグはactorの座標と指の差分を出力しているらしく
 * actorが移動するケースではまともに機能しないっぽい。
 * 現在はlibGDXからのupdate()を中継してるだけなので
 * ぶっちゃけこのクラスはなくていい。
 */
open class UiPiece(val actor: Actor, val uiBoard: UiBoard,
                   /**
                    * 論理駒
                    */
                   open var piece: Piece<*, *>
) : ClickListener() {
    var x = 0f
    var y = 0f
    /**
     * その駒に触れてる最中かどうか。ドラッグ中とPiece側から状態を取得するために使う。がなくなりそう…
     */
    var touched = TouchPhase.NONE

    /**
     * ドラッグを駒に伝える
     * ドラッグはクリックではない.x,yは移動量。ただこれって枡と同じサイズじゃないと想定通りに動かないんだよな。
     * 駒へのタッチ・タッチアップを盤面に伝えない方法があればいいんだけど…
     * 2回呼ばれてる気がする…x,yはあてにならないなこれ。
     */
    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
        super.touchDragged(event, x, y, pointer)
        //タッチのつもりでドラッグってやっぱりあるのかなぁ
        println("touchDragged($x, $y, $pointer)")
        //盤面のチェックは有るべきか
//        if (!uiBoard.pieceActive) {
//            println("!uiBoard.pieceActive")
//            return
//        }
//        //升目以外にドラッグした場合は無視
//        if (!uiBoard.posIsOnBoard(Vector3(x, y, 0f))) {
//            println("!uiBoard.posIsOnBoard")
//            return
//        }
        //タッチ位置とタッチ開始時の原点との差分からActorの場所を出す。原点が変わるときは補正がいるな…
        val touchPos = Vector3()
        touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        val toX = touchPos.x - this.x
        val toY = uiBoard.height - touchPos.y + this.y
        println("x:$toX y:$toY")
        touched = TouchPhase.DRAG
//        piece.touchDragged(uiBoard.touchedPosition(), toX/2, toY/2)
        //   uiBoard.board.drag(uiBoard.touchedPosition())
    }

    /**
     * 指を離したのを駒に伝える。
     * x,yはきっとタッチ始めた位置からの差分
     */
    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        super.touchUp(event, x, y, pointer, button)
//        if (!uiBoard.pieceActive) return
        actor.zIndex = 0
        touched = TouchPhase.RELEASE
        val position = uiBoard.touchedPosition()
        event!!.cancel() // 今はマップ上の動きだけだがいずれ駒の操作を盤に伝える形にしたい
        piece.touchUp(position)
        uiBoard.board.pieceClick(position, piece)//あーこここれじゃダメなのか
        this.x = 0f
        this.y = 0f
    }

    /**
     * タッチしたのを駒に伝える.x,y は駒の中の位置なので盤面に伝えるタッチ位置は盤面ベースで取得する
     */
    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        val result = super.touchDown(event, x, y, pointer, button)
        println("touchDown($x, $y, $pointer)")
        this.x = x
        this.y = y
        //本当はユニットの情報を表示するとかいろいろある
//        if (!uiBoard.pieceActive) return result
//駒に対する操作を始めたのを伝える
        val position = uiBoard.touchedPosition()
        piece.moveStart(position)
        actor.zIndex = 0
        touched = TouchPhase.TOUCH
//        event!!.cancel()//bubbles = false は機能しないこともあるがcancelは機能するのか…
        piece.touchDown()
        uiBoard.board.pieceTouch(position, piece)
        return result
    }

    /**
     * LibGDXのアップデートで呼ばれる。
     */
    fun libUpdate() {
        if (piece.charPosition == null) {
            return // ボード上に無いときは何もしない。消す処理を書いてもいいか？
        }
        piece.update()//これどっちが先に動くべきなんだろうな…？
        update()
    }

    open fun update() {
    }
}
