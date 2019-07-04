package jp.blogspot.turanukimaru.board

//雑多なクラスやEnum

/**
 * プレイヤー。盤の所有者に使う
 */
class Player {
    companion object {
        val None = Player()
    }
    //駒のリストがあってもいいけど依存多すぎるな…
}

interface BoardListener {
    fun actionDone()
    fun turnEnd()
    fun updateInfo(updateInfo: (uiBoard: UiBoard) -> Boolean, rank: Int)
}

class Touch<UNIT, GROUND>(
        /**
         * タッチ開始したときの駒。タッチ開始とUp開始が同じ駒の時でないとタッチ判定しないほうが良いだろう
         */
        var touchedPiece: Piece<UNIT, GROUND>? = null,
        /**
         * タッチ開始したときの位置.これは not null が自然か
         */
        var touchedPosition: UiBoard.Position,
        var holdStart: Long = 0L,
        var dragged: Boolean = false
) {
    private val graspThreshold = 500//長押しホールドに結局要るか…

    val hasPiece: Boolean get() = touchedPiece != null
    /**
     * ドラッグ中か。一定値以上ドラッグかホールド時間で判定しとくか
     */
    fun dragging(): Boolean {
        return dragged || System.currentTimeMillis() - holdStart > graspThreshold
    }

    fun drag(position: UiBoard.Position, board: Board<UNIT, GROUND>): Boolean {
        dragged = true
        return touchedPiece?.isActionable ?: false && touchedPiece?.owner == board.owner && touchedPiece?.boardMove(position) ?: false
    }
}

/**
 * アクション開始イベント。
 */
enum class ActionEvent {
    /**
     * アクションなし。アクション終了後これ。Updateが走る
     */
    None,
    /**
     * アクションなし。アクション中・ドラッグ中Updateが走らない
     */
    Direct,
    /**
     * リトライとかで画面表示…イラン気もするな
     */
    Reset,
    /**
     * 動ける状態
     */
    Ready,
    /**
     * アクション中。Updateが走らない
     */
    MoveToCharPosition,
    /**
     * アクション中。Updateが走らない
     */
    Selected,
    /**
     * 戦闘とかのアクション中。Updateが走らない
     */
    Attack,
    /**
     * 戦闘とかのアクション中。Updateが走らない
     */
    Attacked,
    /**
     * 最初に画面上に配置
     */
    Put,
    /**
     * 主導権のない側を棒立ちにして色見を戻す
     */
    Disabled
}

/**
 * 駒の状態。選択・行動前などトランザクションアクションは含まない。そちらは常に一つだからハンド管理
 */
enum class ActionPhase {
    //おかれて初期化を行う前。初期化されたらDisabled
    PUTTED,
    //おかれて初期化を行う前。初期化されたらREADY
    START,
    //自分の手番でないので動かせない
    DISABLED,
    //自分の手番で動かせる
    READY,
    //移動中
    MOVING,
    //攻撃・アニメが終わったらACTEDになる予定だけどHand側に統合されそうな気もしてきた
    ATTACK,
    //攻撃を受ける。同上
    ATTACKED,
    //行動確定後
    ACTED,
    //取り除いた状態。PositionがNullにもなっているはず
    REMOVED,
}

//消すべきだけど消すのもったいないな…
class 駒

class 枡
abstract class 行動(open val 選択駒: 駒?, open val 戦闘駒: 駒?, open val 移動元: 枡?, open val 移動先: 枡?) {
    abstract fun 駒タップ(対象駒: 駒, 対象枡: 枡): 行動
    abstract fun 盤タップ(対象駒: 駒, 対象枡: 枡): 行動
    //他の関数はoverride禁止
    fun 他共通関数() {}
}

class 未選択() : 行動(null, null, null, null) {
    override fun 駒タップ(対象駒: 駒, 対象枡: 枡) = 選択(対象駒, 対象枡, 対象枡)
    override fun 盤タップ(対象駒: 駒, 対象枡: 枡) = 未選択()
}

class 選択(override val 選択駒: 駒, override val 移動元: 枡, override val 移動先: 枡) : 行動(選択駒, null, 移動元, 移動先) {
    override fun 駒タップ(対象駒: 駒, 対象枡: 枡) = if (選択駒 == 対象駒) 未選択() else 戦闘準備(選択駒, 対象駒, 移動元, 移動先)
    override fun 盤タップ(対象駒: 駒, 対象枡: 枡) = 選択(対象駒, 移動元, 対象枡)
}

class 戦闘準備(override val 選択駒: 駒, override val 戦闘駒: 駒, override val 移動元: 枡, override val 移動先: 枡) : 行動(選択駒, 戦闘駒, 移動元, 移動先) {
    override fun 駒タップ(対象駒: 駒, 対象枡: 枡) = 未選択()
    override fun 盤タップ(対象駒: 駒, 対象枡: 枡) = 選択(対象駒, 移動元, 対象枡)
}
