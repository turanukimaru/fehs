package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.playboard.ActionEvent
import jp.blogspot.turanukimaru.playboard.ActionPhase
import jp.blogspot.turanukimaru.playboard.Position

interface IActionListener {
    /**
     * 情報枠を更新する。描画関数をリスナに渡してるだけだけど…
     */
    fun updateInfo(myPiece: MyPiece, show: Boolean = false) {
    }

    /**
     * 情報枠を更新する。描画関数をリスナに渡してるだけだけど…
     */
    fun updateActionResult(fightResult: FightResult, show: Boolean = false) {
    }

    /**
     * ゲーム開始時とかやり直し字とか
     */
    fun reset(position: Position)

    /**
     * 場所直接指定。ドラッグ時に指に追従
     */
    fun directPos(position: Position, x: Float, y: Float)

    /**
     * LibGDXのアップデートで呼ばれる。これどこから呼ぶかだよなあ。pieceからでいいがlibGDXに近くてもいい
     */
    fun libUpdate()

    /**
     * 待機中の動作。基本的には ActionReady かつアクション中じゃなければいいだけか？
     */
    fun update()

    /**
     * 位置移動直接。アクションをキャンセルして移動差分を駒に登録
     */
    fun actionSetToPosition(position: Position)

    /**
     * EndOfAnimationActionから呼ばれるコールバック
     */
    fun actionDone()

    /**
     * リスナとして呼ばれるアクション通知
     */
    fun action(actionEvent: ActionEvent, next: ActionPhase, position: Position?, fightResult: FightResult? = null)

    /**
     * 戦闘結果からアクションを作る。攻撃側と受け側を分けるべきなんだろうが同時にアクションさせるシナリオ作成にはこっちのが楽なんだよな。どうせエディタができたら消える関数だし。
     */
    fun attackResultToSeq(fightResult: FightResult, isAttacker: ActionEvent)

    /**
     * 立ってるときのアニメ１ループ分。LibGDXにループ機能はあるが操作統一のため主導でループ
     * 各パーツへばらばらにアクションを追加するからシーケンス返す構造にできない…
     * */
    fun readyAction()
}
