package jp.blogspot.turanukimaru.playboard

import kotlin.math.abs

//雑多なクラスやEnum

/**
 * プレイヤー。盤の所有者に使う
 * 駒のジェネリックを持ってるべきなんだけどめんどくさいというか必要になってから追加したい。
 */
class Player(val id: Int? = null) {
    companion object {
        val None = Player()
    }

    private val pieceList = mutableListOf<Piece<*, *>>()
    fun takePiece(piece: Piece<*, *>) {
        pieceList.add(piece)
    }
    //駒のリストがあってもいいけど依存多すぎるな…
}

// これこの層じゃないな…いや基本動作はここでいいのか
interface BoardListener {
    //    fun actionDone()
//    fun turnEnd()
    fun showOption(position: Position)
    fun hideOption()
}

/**
 * 盤面の座標
 */
data class Position(val x: Int, val y: Int) {
    fun sub(p: Position): Position = Position(x - p.x, y - p.y)
    fun plus(p: Position): Position = Position(x + p.x, y + p.y)
    fun range(p: Position, max: Int, min: Int = 0) = distance(p) in min..max
    fun distance(p: Position) = abs(x - p.x) + abs(y - p.y)
}

/**
 * 座標＋向き。駒が無ければ位置取りも存在しないし、駒への参照もあったほうがいいかな？あるいは逆に Position に統一するべきなんだろうか…
 */
data class Positioning(val p: Position, val r: Int=0)

val nowhere = Positioning(Position(-1, -1), -1)

class Touch<UNIT, TILE>(
        /**
         * タッチ開始したときの駒。タッチ開始とUp開始が同じ駒の時でないとタッチ判定しないほうが良いだろう
         */
        var touchedPiece: Piece<UNIT, TILE>?,
        /**
         * タッチ開始したときの位置.これは not null が自然か
         */
        var touchedPosition: Position,
        var holdStart: Long,
        var dragged: Boolean
) {
    /**
     *     Process: jp.blogspot.turanukimaru.fehs, PID: 1737
    java.lang.IllegalAccessError: Illegal class access: 'jp.blogspot.turanukimaru.board.Move$override' attempting to access 'kotlin.jvm.internal.DefaultConstructorMarker' (declaration of 'jp.blogspot.turanukimaru.board.Move$override' appears in /data/data/jp.blogspot.turanukimaru.fehs/files/instant-run/dex-temp/reload0x0000.dex)

    インスタントランでコンストラクタにデフォルト引数使うと発生することがある
    デフォルト引数を使わないようにするかコンストラクタを手で作る
     */
    constructor(touchedPiece: Piece<UNIT, TILE>?,
                touchedPosition: Position,
                holdStart: Long) : this(touchedPiece, touchedPosition, holdStart, false)

    private val graspThreshold = 500//長押しホールドに結局要るか…

    val hasPiece: Boolean get() = touchedPiece != null

    /**
     * ドラッグ中か。どこかにホールド判定を入れないとなー
     */
    fun dragging(): Boolean {
        return dragged || System.currentTimeMillis() - holdStart > graspThreshold
    }

    fun drag(position: Position, board: Board<UNIT, TILE>): Boolean {
        dragged = true
        //そこに動けるか判定が先に要るか
        return touchedPiece?.let { it.isActionable && touchedPiece?.owner == board.owner && it.boardDrag(position) }
                ?: false
    }
}

data class PiecesAndTiles<UNIT, TILE>(val Piece0: Piece<UNIT, TILE>?, val Piece1: Piece<UNIT, TILE>?, val Piece_1: Piece<UNIT, TILE>?, val Piece2: Piece<UNIT, TILE>?, val Piece_2: Piece<UNIT, TILE>?
                                      , val TILE0: TILE?, val TILE1: TILE?, val TILE_1: TILE?, val TILE2: TILE?, val TILE_2: TILE?)

enum class TouchPhase {
    NONE,//触ってない
    TOUCH,//触り始めた
    DRAG,//ドラッグ中
    RELEASE,//ドラッグ離した
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

    //行動確定後
    ACTED,

    //取り除いた状態。PositionがNullにもなっているはず
    REMOVED,
}
