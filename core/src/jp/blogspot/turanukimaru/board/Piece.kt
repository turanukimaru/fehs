package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position
import jp.blogspot.turanukimaru.fehs.FightResult


/**
 * 論理駒。ゲームのルールによらない部分
 */
open class Piece<UNIT, GROUND>(val containUnit: UNIT, var board: Board<UNIT, GROUND>, val owner: Player, private val actionListener: ActionListener) {

    /**
     * 向き。駒の向きで移動する方向が変わるときに使う予定
     */
    var orientation = 0

    /**
     * 操作的な意味での状態。駒を動かせるかとか動かした後だとか。
     */
    var actionPhase = ActionPhase.DISABLED

    //アクション。基本的にはアニメの発声しないアクションだが。うーん難しい。
    fun action(nextPhase: ActionPhase, actionEvent: ActionEvent = ActionEvent.None, fightResult: FightResult? = null) {
        //アクション後、readyかselectedかactedかで3種類はあるな.これ関数であるべきじゃないな…
        actionListener.uiAction(actionEvent, nextPhase, charPosition, fightResult)
        this.actionPhase = nextPhase
    }

    /**
     * 盤上の位置。表示用の位置。移動先を優先して出す
     */
    val charPosition: Position? get() = newPosition ?: existsPosition

    /**
     * 盤上の位置。移動先
     */
    var newPosition: Position? = null

    /**
     * 盤上の位置。確定してる位置
     */
    var existsPosition: Position? = null

    /**
     * 駒の移動可能範囲
     */
    private var searchedRoute = mutableListOf<MutableList<Int>>()
    /**
     * 駒の移動範囲から更に効果を及ぼすことのできる範囲
     */
    private var effectiveRoute: MutableList<MutableList<Int>> = mutableListOf<MutableList<Int>>()

    fun effectiveRouteOf(position: Position): Int {
        if (effectiveRoute.size == 0) {
            effectiveRoute = board.searchEffectiveRoute(this)
        }
        return effectiveRoute[position.x][position.y]
    }

    fun searchedRouteOf(position: Position): Int {
        if (searchedRoute.size == 0) {
            searchedRoute = board.searchRoute(this)
        }
        return searchedRoute[position.x][position.y]
    }

    /**
     * 効果範囲か。再帰して効果範囲を拡大できるかなので名前変えよう
     */
    open fun isEffective(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int): Boolean {
        return false
    }

    /**
     * 動けるか。再帰して移動できるかの意味だから名前変えたほうが良いかなあ
     */
    open fun isMovable(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int): Boolean {
        return piece == null && steps == 0
    }

    /**
     * 引数の枡に移動することで消費する移動力。移動できないときは負の値
     */
    open fun countStep(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int): Int {
        return if (steps == 0) {
            1
        } else {
            -1
        }
    }

    /**
     * この枡に移動することで消費する効果範囲。ただ枡を通過できるかはともかく効果範囲が減るとかいうゲーム無かった気がするから不要か？
     */
    open fun countEffectiveStep(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int): Int = if (steps == 0) 1 else -1


    /**
     * 別のユニットがいた場合にその枡に止まれるか。例えば敵に重なることはできるが味方には重なれないかも。移動範囲内かはこれより先に判定している
     */
    open fun isStoppable(otherUnit: UNIT?): Boolean = otherUnit == null


    /**行動前・選択状態・移動後は行動可能
     *
     */
    val isActionable get() = actionPhase == ActionPhase.READY || actionPhase == ActionPhase.MOVING

    /**
     * ドラッグから指を挙げたときにも反応するのでうまく使えない・・・
     */
    open fun clicked(position: Position): Boolean {
        if (!isActionable) {
            return false
        }
        println("stageDropletImage1がクリックされた！")
        println("stageDropletImage1がクリックされた！")
        return true
    }

    /**
     * タッチから指を離したとき。移動範囲・効果範囲外のときは駒をもとの場所に戻す
     */
    open fun touchUp(position: Position): Boolean {
        return true
    }

    /**
     * LibGDXのアップデートで呼ばれる。
     */
    fun libUpdate() {
        actionListener.libUpdate()
        update()
    }

    open fun update() {
    }

    open fun boardAction(move: Move<UNIT, GROUND>, position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean = true

    open fun boardActionCommit(move: Move<UNIT, GROUND>, position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean = true

    open fun movePiece(): Boolean {
        action(ActionPhase.MOVING, ActionEvent.MoveToCharPosition)
        return true
    }

    /**
     * 移動中。
     */
    open fun boardMove(move: Move<UNIT, GROUND>, position: Position): Boolean {
        val targetRoute = searchedRoute[position.x][position.y]
        //移動範囲外は-1
        if (targetRoute < 0) return false
        this.newPosition = position
        action(ActionPhase.MOVING, ActionEvent.MoveToCharPosition)
        return true
    }

    /**
     * 移動確定。位置を更新して行動後状態にする
     */
    fun boardMoveCommit(move: Move<UNIT, GROUND>, position: Position? = newPosition): Boolean {
        println("ここは？…")
        this.existsPosition = position
        this.newPosition = null
        action(ActionPhase.ACTED, ActionEvent.MoveToCharPosition)
        //次の行動に備えてルートクリア
        clearRoute()
        return true
    }

    private fun clearRoute() {
        println("なぜかルートがクリアされてない…")
        searchedRoute.clear()
        effectiveRoute.clear()
    }


    /**
     * タッチしたときはコマを選択済みの時はその駒にアクションし、そうでないときはその駒を選択状態にする...
     * upのタイミングじゃないとダメか？
     */
    fun touchDown(): Boolean {
        return touched()
    }

    /**
     * オーバーライド用
     */
    open fun touched(): Boolean = true

    /**
     * x,yは移動量。
     * 指に追従して動かしてみるが、実際の操作は枡で判断してるため枡の大きさとキャラの大きさが大幅に違うとおかしくなるのか。ボードのDragにいどうするべきか…
     */
    fun touchDragged(position: Position, x: Float, y: Float): Boolean {
        //行動できないときは反応しない
        if (!isActionable) {
            return false
        }
        //ドラッグしてる絵を動かす
        actionListener.directPos(x, y)
        //移動可能範囲内で移動したらスタックに積む...
        return dragged(position)
    }

    open fun dragged(position: Position): Boolean = true

    /**
     * 移動可能方向
     */
    open fun orientations(): Array<Int> {
        return arrayOf(0, 2, 4, 6)
    }

    /**
     * 攻撃可能方向
     */
    open fun effectiveOrientations(): Array<Int> {
        return arrayOf(0, 2, 4, 6)
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

    /**
     * この駒を行動可能にする。ターン開始時に呼ばれる
     */
    fun ready() {
        println("READY!! $this")
        action(ActionPhase.READY, ActionEvent.Ready)
    }

    fun putOn(x: Int, y: Int) {
        existsPosition = Position(x, y)
        action(ActionPhase.PUTTED, ActionEvent.Put)
    }

    fun startPiece(xyToPosition: Position) {
        board.move.toucheStart(this, xyToPosition)
    }

    fun moveCancel() {
        clearRoute()
        newPosition = null
        action(ActionPhase.READY, ActionEvent.MoveToCharPosition)
    }

    fun select() {
        action(ActionPhase.MOVING, ActionEvent.Selected)
    }

    fun remove() {
        existsPosition = null
        action(ActionPhase.REMOVED)
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
}

