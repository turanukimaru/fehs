package jp.blogspot.turanukimaru.board

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import jp.blogspot.turanukimaru.fehs.FightResult
import jp.blogspot.turanukimaru.fehs.SIDES

/**
 * イベントを受け取って画面上の表示に変換するのが主な仕事。ゲーム内容に依存するので手が空いたらInterfaceにしたい。
 */
open class ActionListener(private val actor: Actor, private val uiBoard: UiBoard) {

    /**
     * 中に含むActorのリスト。アニメーションで体の部位を動かすのに使うのだがボーンモデル別に作るべき
     */
    val actors = mutableListOf<Actor>()

    /**
     * 一回きりのアクション。
     */
    private var actionEventNow: ActionEvent = ActionEvent.None

    /**
     * ループ用アクション。actionEventNow中は無視される
     */
    var actionPhase: ActionPhase = ActionPhase.START


    /**
     * 場所直接指定。ドラッグ時に指に追従
     */
    private fun reset(position: UiBoard.Position) {
        println(this)
        actor.isVisible = true
        actor.color.a = 1f//fadeout した後は 0
        actionSetToPosition(position)
    }

    /**
     * 場所直接指定。ドラッグ時に指に追従
     */
    fun directPos(x: Float, y: Float) {
        println("MyUiPiece DRAG ${uiBoard.board.move}")
        actor.clearActions()
        actor.setPosition(actor.x + x, actor.y + y)
        actionEventNow = ActionEvent.Direct//Direct中はUpdate時にアニメしない
    }

    /**
     * LibGDXのアップデートで呼ばれる。これどこから呼ぶかだよなあ。pieceからでいいがlibGDXに近くてもいい
     */
    fun libUpdate() {
        update()
    }

    /**
     * 待機中の動作。基本的には Ready かつアクション中じゃなければいいだけか？
     */
    open fun update() {
        //アクション中は無視
        if (actionEventNow != ActionEvent.None) return
//        println("ActionListener update : $actionPhase")
        // Enum を直接オーバーライド出来たら面白いのだができなかった！
        when (actionPhase) {
            ActionPhase.READY -> readyAction()
            //現在の位置に灰色で表示.アクションというかシーケンスの最後に移動したいところだけどややこしくなるからここのままのがいいか
            ActionPhase.ACTED -> actors.forEach { a -> a.setColor(0.5f, 0.5f, 0.5f, 1f); }//これ灰色じゃねーな　r+g+b/3　にするのが正しいか？
            ActionPhase.REMOVED -> println("おそらく不要。フェイドアウトは戦闘後アクションで書いてしまっている")
            //行動後の現在位置に表示。ずれてる場合は直接移動させる //問題か Position をどこからとってくるかが
            else -> println("MyUiPiece else ${uiBoard.board.move}")
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
    private fun actionSetToPosition(position: UiBoard.Position) {
        println("actionSetToPosition")
        actor.clearActions()
        val finalX = uiBoard.squareXtoPosX(position.x)
        val finalY = uiBoard.squareYtoPosY(position.y)
        actor.setPosition(finalX, finalY)
    }

    /**
     * EndOfAnimationActionから呼ばれるコールバック
     */
    open fun actionDone() {
        actionEventNow = ActionEvent.None
    }

    /**
     * リスナとして呼ばれるアクション通知
     */
    open fun action(actionEvent: ActionEvent, next: ActionPhase, position: UiBoard.Position?, fightResult: FightResult? = null) {
//ActionEventを入れつつ次のフェイズを設定する。Actionが終了するまではUpdateがAction依存になるためPhaseはこのタイミングで変更しても大丈夫なはず
        actionEventNow = actionEvent
        actionPhase = next
        when (actionEvent) {
            ActionEvent.MoveToCharPosition -> actor.addAction(actionMoveToPosition(position))//MoveToCharPositionの拡張メソッドにしたかったがEnumは拡張できない…
            ActionEvent.Reset -> reset(position!!)
            ActionEvent.Ready -> actor.addAction(actionMoveToPosition(position))//いずれ開始時に武器を構えるアクションにしたい
            ActionEvent.Put -> actionSetToPosition(position!!)
            ActionEvent.Selected -> readyAction()
            ActionEvent.Disabled -> actors.forEach { a -> a.setColor(1f, 1f, 1f, 1f) }
            ActionEvent.Attack -> {
                actionSetToPosition(position!!)
                attackResultToSeq(fightResult!!, actionEvent)
            }
            ActionEvent.Attacked -> {
                actionSetToPosition(position!!)//攻撃を受ける側にこれは実は要らないんだけどな
                attackResultToSeq(fightResult!!, actionEvent)
            }
            ActionEvent.Direct -> println("直接操作中")//なにもしない
            else -> println("今ここには何も来ないはずなのだが…")
        }
    }

    /**
     * 戦闘結果からアクションを作る。攻撃側と受け側を分けるべきなんだろうが同時にアクションさせるシナリオ作成にはこっちのが楽なんだよな。どうせエディタができたら消える関数だし。
     */
    private fun attackResultToSeq(fightResult: FightResult, isAttacker: ActionEvent) {
        var attackCount = 0
        val sourceSeq = SequenceAction()
        val targetSeq = SequenceAction()
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
        sourceSeq.addAction(CallbackAction { true })//Callbackが機能しているかの確認用なのでこの行は不要
        sourceSeq.addAction(EndOfAnimationAction(this, 0f, if (sourceDead) uiBoard.board.listener else null))
        targetSeq.addAction(EndOfAnimationAction(this, 0f, if (targetDead) uiBoard.board.listener else null))
        if (isAttacker == ActionEvent.Attack) {
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

        actionEventNow = ActionEvent.Ready//棒立ちアニメ中、の意味
        val base = actors[0]
        val face = actors[1]
        //clearしないでアクション追加するとめっちゃ落ちる。デフォルト位置に戻す処理もいるな
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
