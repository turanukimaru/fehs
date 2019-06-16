package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.fehs.FightResult
import jp.blogspot.turanukimaru.fehs.SIDES

/**
 * イベントを受け取って画面上の表示に変換するのが主な仕事。ゲーム内容に依存するので手が空いたらListenerにしたい。
 */
open class ActionListener(private val actor: Actor, private val uiBoard: UiBoard) {

    /**
     * 中に含むActorのリスト。アニメーションで体の部位を動かすのに使うのだがボーンモデル別に作るべき
     */
    val actors = mutableListOf<Actor>()

    /**
     * 一回きりのアクション。
     */
    private var actionEventNow: Piece.ActionEvent = Piece.ActionEvent.None

    /**
     * ループ用アクション。actionEventNow中は無視される
     */
    var actionPhase: Piece.ActionPhase = Piece.ActionPhase.START


    /**
     * 場所直接指定。ドラッグ時に指に追従
     */
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

    /**
     * 待機中の動作を入れたいのだが。基本的には Ready かつアクション中じゃなければいいだけか？
     */
    open fun update() {
        //アクション中は無視
        if (actionEventNow != Piece.ActionEvent.None) return
//        println("ActionListener update : $actionPhase")
        // Enum を直接オーバーライド出来たら面白いのだができなかった！
        when (actionPhase) {
            Piece.ActionPhase.READY -> {
//                println("readyAction() は呼ばれてるのに見た目が変わらんな…...呼ばれすぎだな")
                readyAction()
            }
            Piece.ActionPhase.ACTED -> {//現在の位置に灰色で表示.アクションというかシーケンスの最後に移動したいところだけどややこしくなるからここのままのがいいか
//                println("MyUiPiece ACTED ${uiBoard.board.move}")
                actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f) }//これ灰色じゃねーな全部　r+g+b/3　にするのが正しいか？
            }
            Piece.ActionPhase.REMOVED -> {
//                println("MyUiPiece ACTED ${uiBoard.board.move}")
//                actors.forEach { a -> a.touchable = Touchable.disabled;a.sizeBy(0f) }//フェイドアウトさせたい…けどアクションに追加するほうが楽か？
            }
            else -> {//行動後の現在位置に表示。ずれてる場合は直接移動させる //問題か Position をどこからとってくるかが
                println("MyUiPiece else ${uiBoard.board.move}")
            }

        }
    }

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
        return seq
    }

    /**
     * 位置移動直接。アクションをキャンセルして移動差分を駒に登録
     */
    private fun actionSetToPosition(position: UiBoard.Position?) {
        println("actionSetToPosition")
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
        println("uiActionDone!")
        println("uiActionDone!!")
        println("uiActionDone!!!")
//        touched = TouchPhase.NONE//RELEASE->NONEのはず
        actionEventNow = Piece.ActionEvent.None
    }

    fun uiAction(actionEvent: Piece.ActionEvent, next: Piece.ActionPhase, position: UiBoard.Position?, fightResult: FightResult? = null) {
//ActionEventを入れつつ次のフェイズを設定する。Actionが終了するまではUpdateがAction依存になるためPhaseはこのタイミングで変更しても大丈夫なはず
        actionEventNow = actionEvent
        actionPhase = next
        when (actionEvent) {
            Piece.ActionEvent.MoveToCharPosition -> actor.addAction(actionMoveToPosition(position))//MoveToCharPositionの拡張メソッドにしたい
            Piece.ActionEvent.Ready -> actor.addAction(actionMoveToPosition(position))//開始時に武器を構えるアクションにしたい
            Piece.ActionEvent.Put -> actionSetToPosition(position)//時間おかずに移動させたい
            Piece.ActionEvent.Selected -> readyAction()
            Piece.ActionEvent.Disabled -> actors.forEach { a -> a.setColor(1f, 1f, 1f, 1f) }
            Piece.ActionEvent.Attack -> {
                actionSetToPosition(position)
                attackResultToSeq(fightResult!!, actionEvent)
            }
            Piece.ActionEvent.Attacked -> {
                actionSetToPosition(position)
                attackResultToSeq(fightResult!!, actionEvent)
            }
            Piece.ActionEvent.Direct -> {
            }//なにもしない
            else -> {
            }
        }
    }

    private fun attackResultToSeq(fightResult: FightResult, isAttacker: Piece.ActionEvent) {
        var attackCount = 0
        val sourceSeq = SequenceAction()
        val targetSeq = SequenceAction()
        //ウンコみたいなコードだな。。。fightResultを配列からクラスに変更するべきか
        var targetDead = false
        var sourceDead = false
        for (result in fightResult!!.attackResults) {
            println(result)
            //ダメージを与える側が攻撃側の時
            if (result.side == SIDES.ATTACKER) {
                sourceSeq.addAction(attackAction(fightResult.attackPos.x, fightResult.attackPos.y, fightResult.targetPos.x, fightResult.targetPos.y))
                targetSeq.addAction(Actions.delay(0.4f))
                uiBoard.showNumbers(fightResult.targetPos.x, fightResult.targetPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
            } else {
                targetSeq.addAction(attackAction(fightResult.targetPos.x, fightResult.targetPos.y, fightResult.attackPos.x, fightResult.attackPos.y))
                sourceSeq.addAction(Actions.delay(0.4f))
                uiBoard.showNumbers(fightResult.attackPos.x, fightResult.attackPos.y, result.damage, attackCount++ * 0.4f + 0.2f)
            }
            if (result.source.hp == 0) {
                sourceSeq.addAction(Actions.fadeOut(1f))
                sourceDead = true
            }
            if (result.target.hp == 0) {
                targetSeq.addAction(Actions.fadeOut(1f))
                targetDead = true
            }
        }
        sourceSeq.addAction(CallbackAction { true })
        sourceSeq.addAction(EndOfAnimationAction(this, 0f))
        sourceSeq.addAction(EndOfAnimationAction(this, 0f))
        targetSeq.addAction(EndOfAnimationAction(this, 0f))
        if (isAttacker == Piece.ActionEvent.Attack) {
            actor.addAction(sourceSeq)
            if (sourceDead) {
                actor.touchable = Touchable.disabled
            }
        } else {
            actor.addAction(targetSeq)
            if (targetDead) {
                actor.touchable = Touchable.disabled
            }
        }

        //行動終了
        actionEventNow = isAttacker
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
     * 各パーツへばらばらにアクションを追加するからシーケンス返す構造にできない…
     * */
    private fun readyAction() {
        if (actors.size == 0) return
        println("start readyAction $actors")

        actionEventNow = Piece.ActionEvent.Ready//棒立ちアニメ中、の意味
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
        seq1.addAction(EndOfAnimationAction(this, 1f))
        base.addAction(seq1)
        val seq2 = SequenceAction()
        seq2.addAction(Actions.moveBy(6.0f, 6.0f, 0.5f))
        seq2.addAction(Actions.moveBy(-6.0f, -6.0f, 0.5f))
        face.addAction(seq2)
        return
    }
}
