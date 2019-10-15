package jp.blogspot.turanukimaru.fehs

import com.badlogic.gdx.scenes.scene2d.Actor
import jp.blogspot.turanukimaru.board.UiBoard
import jp.blogspot.turanukimaru.board.UiPiece

/**
 * 駒UIの拡張。Listenerがアクションを受け付けるのでこれ要らなさそう
 */
class MyUiPiece(actor: Actor, uiBoard: UiBoard, private var myPiece: MyPiece) : UiPiece(actor, uiBoard, myPiece) {

    //安全なドラッグ処理を作らないといけないのか…
//    override fun localUpdate() {
//        pieceAt.animationStart = false
////        actionが指定されているときはそれを実行する。それ以外では…どうしようかな。ループアクションをさせたいのだが…
//        //カウントを一つ進める。開始かのBooleanのがいいかなあ
//        pieceAt.animationCount++
//    }

}