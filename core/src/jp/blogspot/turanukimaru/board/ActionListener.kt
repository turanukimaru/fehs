package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.fehs.FightResult

/**
 * 駒とLibGDXの間
 * 駒をドラッグ中は駒を動かすことになるがそうすると離したときにクリック扱いになるのでonClickを使わないように
 */
open class ActionListener(private val actor: Actor, private val uiBoard: UiBoard) {
//やっぱり相互参照にするしかないか。とりあえずはだけど
    /**
     * 中に含むActorのリスト。アニメーションで体の部位を動かすのに使うのだがボーンモデル別に作るべき
     */
    private val actors = mutableListOf<Actor>()


    /**
     * その駒に触れてる最中かどうか。ドラッグ中とPiece側から状態を取得するために使う
     */
    var touched = TouchPhase.NONE

    /**
     * アクション中か。アクション開始時にtrueにしたいな。無理か。
     */
    private var actionEventNow: Piece.ActionEvent = Piece.ActionEvent.None

    fun directPos(x: Float, y: Float) {
        println("MyUiPiece DRAG ${uiBoard.board.move}")
        actor.clearActions()
        actor.setPosition(actor.x + x, actor.y + y)
        actionEventNow = Piece.ActionEvent.Direct//Direct中はUpdate時にアニメしない
    }

    /**
     * LibGDXのアップデートで呼ばれる。これどこから呼ぶかだよなあ。pieceからでいいがlibGDXに近くてもいい
     */
    fun libUpdate() {
        update()
    }

    open fun update() {
        //アクション中じゃないのでループアニメ
        if (actionEventNow == Piece.ActionEvent.None) {
            println("ActionListener update : $this")
            when (actionPhase) {//phaseはPieceから渡す形のほうがいいかなあ。UpdateではなくActionPhaseBroadcastみたいな
                //これは不要になるな
                Piece.ActionPhase.PUTTED -> {//おかれた直後なので初期化してDisabled.いずれ増援が出たr
//                    actionSetToPosition(position)
//                        action(Piece.ActionPhase.DISABLED)
                }
                Piece.ActionPhase.START -> {//おかれた直後なので初期化してREADY
//                    actionSetToPosition(position)
//                uiBoard.stage.addActor(actor)
//                        piece.action(Piece.ActionPhase.READY)
                }
                Piece.ActionPhase.DISABLED ->//ターンが違うなど操作不能の時は枡に合わせてセット
                {}//                    actor.setPosition(uiBoard.squareXtoPosX(position!!.x), uiBoard.squareYtoPosY(position!!.y))
                Piece.ActionPhase.READY -> {
                    //ドラッグしてるときはアクションをクリアしてドラッグに追従表示.タッチじゃなくてドラッグ判定にせねば。実機では正しく動くがシミュ上ではめっちゃぶれる
                    when (touched) {
                        TouchPhase.DRAG -> {
                        }
                        TouchPhase.RELEASE -> {
                            println("MyUiPiece RELEASE ${uiBoard.board.move}")
                            //離した後は枡にフィットさせる。
                        }
                        TouchPhase.NONE -> {
                            startAction { readyAction() }
                        }
                        TouchPhase.TOUCH -> {
                            println("MyUiPiece TOUCH ${uiBoard.board.move}")
                        }
                    }
                }
                Piece.ActionPhase.ATTACK -> {//戦闘中のアクションはそのうち考える
                    //戦闘結果を持ってるときはそれを動かす
//                        if (myPiece.fightResult != null) {
//                            attackResultToSeq(
//                                    myPiece.fightResult!!
//                                    , true)
//                            myPiece.fightResult = null
//                        } else {
//                            //アニメーション中でなければ灰色にする
//                            actor.clearActions()
//                            actionEventNow = false//一括クリアもいるなこれは
//                            actionSetToPosition(position)
//                            actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f) }
//                        }
                }
                Piece.ActionPhase.ACTED -> {//現在の位置に灰色で表示
                    println("MyUiPiece ACTED ${uiBoard.board.move}")
//                    actionSetToPosition(position)
                    actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f) }//これ灰色じゃねーな全部　r+g+b/3　にするのが正しいか？
                }
                Piece.ActionPhase.REMOVED -> {//画面から消す
                }
                else -> {//行動後の現在位置に表示。ずれてる場合は直接移動させる
                    println("MyUiPiece else ${uiBoard.board.move}")
//                    actionSetToPosition(position)
                    actors.forEach { a -> a.setColor(1f, 1f, 1f, 1f) }
                }

            }

        }
    }

    // action前に更新してactionNowが終わったらこの状態のループアクションを取ろう
    var actionPhase: Piece.ActionPhase = Piece.ActionPhase.START

    //アクターのサイズが分からないから中心が出ない...けど画像の管理はまた別問題だな
    /**
     * 位置移動のアニメ。移動差分を駒に登録する.移動した後にどうなるかがまた別に必要か、確定アクションを別に作るか、か
     */
    private fun actionMoveToPosition(position: UiBoard.Position?): SequenceAction {
        val seq = SequenceAction()
        if (position == null) return seq
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY = uiBoard.squareYtoPosY(position.y)
        seq.addAction(Actions.moveBy(finalX - actor.x, finalY - actor.y, 0.1f))
        seq.addAction(EndOfAnimationAction(this, 0.1f))
//        piece.uiActionStart()
        return seq
    }

    //これ多分要らなくなるな
    private fun startAction(action: () -> SequenceAction) {
//        println("actionEventNow : $actionEventNow のせいかな？アクションが登録されない")//trueになっとる…actionが登録できるかの判定ではなくクリアが必要なのかな
//        if (actionEventNow) return
        actor.addAction(action())
//        actionEventNow = Piece.ActionEvent.None
    }

    fun noAction() = SequenceAction()
    /**
     * 位置移動直接。アクションをキャンセルして移動差分を駒に登録
     */
    fun actionSetToPosition(position: UiBoard.Position?) {
        if (position == null) return
        actor.clearActions()
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY = uiBoard.squareYtoPosY(position.y)
        actor.setPosition(finalX, finalY)
    }

    /**
     * EndOfAnimationActionから呼ばれるコールバック
     */
    fun uiActionDone() {
        println("uiActionDone")
        println("uiActionDone")
        println("uiActionDone")
//        touched = TouchPhase.NONE//RELEASE->NONEのはず
        actionEventNow = Piece.ActionEvent.None
    }

    fun uiAction(actionEvent: Piece.ActionEvent, next: Piece.ActionPhase, position: UiBoard.Position?) {
        when (actionEvent) {
            Piece.ActionEvent.MoveToCharPosition -> actor.addAction(actionMoveToPosition(position))//MoveToCharPositionの拡張メソッドにしたい
            Piece.ActionEvent.Ready -> actor.addAction(actionMoveToPosition(position))//開始時に武器を構えるアクションにしたい
            Piece.ActionEvent.Direct -> {
            }//なにもしない
            else ->{}
        }
    }

    private fun attackResultToSeq(fightResult: FightResult, isAttacker: Boolean) {
        var attackCount = 0
        val sourceSeq = SequenceAction()
        val targetSeq = SequenceAction()
//        for (result in myPiece.fightResult!!.attackResults) {
//            println(result)
//            //ダメージを与える側が攻撃側の時
//            if (result.side == SIDES.ATTACKER) {
//                sourceSeq.addAction(attackAction(fightResult.attackPos.x, fightResult.attackPos.y, fightResult.targetPos.x, fightResult.targetPos.y))
//                targetSeq.addAction(Actions.delay(0.4f))
//                uiBoard.showNumbers(fightResult.targetPos.x, fightResult.targetPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
//            } else {
//                targetSeq.addAction(attackAction(fightResult.targetPos.x, fightResult.targetPos.y, fightResult.attackPos.x, fightResult.attackPos.y))
//                sourceSeq.addAction(Actions.delay(0.4f))
//                uiBoard.showNumbers(fightResult.attackPos.x, fightResult.attackPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
//            }
//            if (result.source.hp == 0) {
//                sourceSeq.addAction(Actions.fadeOut(1f))
//            }
//            if (result.target.hp == 0) {
//                targetSeq.addAction(Actions.fadeOut(1f))
//            }
//        }
        sourceSeq.addAction(CallbackAction { true })
        sourceSeq.addAction(EndOfAnimationAction(this, 4.0f))
        if (isAttacker) sourceSeq.addAction(EndOfAnimationAction(this, 4.0f)) else targetSeq.addAction(EndOfAnimationAction(this, 4.0f))

        //行動終了
        actor.addAction(sourceSeq)
//    fightResult.attackResults.last().target.uiPiece!!.actor.addAction(targetSeq)

    }

    /**
     * 攻撃処理のアニメ。移動差分を駒に登録する.TODO:アクターのサイズが分からないから中心が出ない...あと単にタイミングがおかしい
     * */
    private fun attackAction(x: Int, y: Int, targetX: Int, targetY: Int): SequenceAction {
        val seq = SequenceAction()
        val toX = targetX - x
        val toY = targetY - y
        seq.addAction(Actions.moveBy(96 * toX.toFloat(), 96 * toY.toFloat(), 0.2f))
        seq.addAction(Actions.moveBy(-96 * toX.toFloat(), -96 * toY.toFloat(), 0.2f))
        return seq
    }

    /**
     * 立ってるときのアニメ１ループ分。LibGDXにループ機能はあるが操作統一のため主導でループ
     * */
    private fun readyAction(): SequenceAction {
        if (actors.size == 0) return SequenceAction()
        val base = actors[0]
        val face = actors[1]
        //clearしないでアクション追加するとめっちゃ落ちる。デフォルト位置に戻す処理もいるな
//        actor.setPosition(uiBoard.squareXtoPosX(piece.charPosition!!.x), uiBoard.squareYtoPosY(piece.charPosition!!.y))
        base.setPosition(0f, 0f)
        face.setPosition(0f, 0f)
        actor.clearActions()
        base.clearActions()
        face.clearActions()
        val seq1 = SequenceAction()
        seq1.addAction(Actions.moveBy(2.0f, 2.0f, 0.5f))
        seq1.addAction(Actions.moveBy(-2.0f, -2.0f, 0.5f))
        seq1.addAction(EndOfAnimationAction(this, 1f))//seq関係なくすぐ呼び出される…
        base.addAction(seq1)
        val seq2 = SequenceAction()
        seq2.addAction(Actions.moveBy(6.0f, 6.0f, 0.5f))
        seq2.addAction(Actions.moveBy(-6.0f, -6.0f, 0.5f))
        face.addAction(seq2)
        return seq1
    }

    val attackAction: (actor: Actor) -> Boolean = {
        it.clearActions()
        true
    }

}
