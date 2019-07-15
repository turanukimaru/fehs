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
        actionListener.action(actionEvent, nextPhase, charPosition, fightResult)
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

    //そろそろマトリクスをIntではなく(pass, stop, action)にするべきなんだろうけど１枡ごとにオブジェクト作るのきっと重いよなあ…
    /**
     * 駒の移動可能範囲
     */
    private var passRoute = mutableListOf<MutableList<Int>>()
    /**
     * 駒の移動範囲で止まれるところ
     */
    private var stopRoute = mutableListOf<MutableList<Int>>()
    /**
     * 駒の移動範囲から更に効果を及ぼすことのできる範囲
     */
    private var actionRoute: MutableList<MutableList<Int>> = mutableListOf()

    fun effectiveRouteAt(position: Position): Int {

        if (actionRoute.size == 0) {
            actionRoute = board.searchEffectiveRoute(this)
        }
        return actionRoute[position.x][position.y]
    }

    fun searchedRouteAt(position: Position): Int {
        if (passRoute.size == 0) {
            passRoute = board.searchRoute(this)
        }
        return passRoute[position.x][position.y]
    }

    /**
     * 効果範囲か。再帰して効果範囲を拡大できるかなので名前変えよう
     */
    open fun isEffective(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int): Boolean {
        return false
    }
    /**
     * 味方にサポートできる範囲か。優先度は攻撃より低いんだっけ？
     */
    open fun isSupportable(grounds: PiecesAndGrounds<UNIT, GROUND>, orientation: Int, steps: Int): Boolean {
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
    open fun isStoppable(piece: Piece<UNIT, GROUND>?): Boolean = piece == null


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

    open fun boardAction(position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean = true

    open fun boardActionCommit(position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean = true

    open fun movePiece(): Boolean {
        action(ActionPhase.MOVING, ActionEvent.MoveToCharPosition)
        return true
    }

    /**
     * 移動中。
     */
    open fun boardMove(position: Position): Boolean {
        val targetRoute = searchedRouteAt(position)
        //移動範囲外は-1
        if (targetRoute < 0) return false
        this.newPosition = position
        action(ActionPhase.MOVING, ActionEvent.MoveToCharPosition)
        return true
    }

    /**
     * 移動中。
     */
    open fun boardDrag(position: Position): Boolean {
        val targetRoute = searchedRouteAt(position)
        //移動範囲外は-1
        if (targetRoute < 0) return false
        this.newPosition = position
        return true
    }

    /**
     * 移動確定。位置を更新して行動後状態にする
     */
    fun boardMoveCommitAction(position: Position? = newPosition): Boolean {
        boardMoveCommit(position)
        action(ActionPhase.ACTED, ActionEvent.MoveToCharPosition)
        //次の行動に備えてルートクリア
        clearRoute()
        return true
    }

    /**
     * 移動確定。位置を更新だけ
     */
    fun boardMoveCommit(position: Position? = newPosition): Boolean {
        this.existsPosition = position
        this.newPosition = null
        clearRoute()
        return true
    }

    fun clearRoute() {
        println("clearRoute!! $this")
        passRoute.clear()
        stopRoute.clear()
        actionRoute.clear()
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
     * この駒を行動可能にする。ターン開始時に呼ばれる
     */
    fun reset() {
        println("RESET!! $this")
        clearRoute()
        action(ActionPhase.START, ActionEvent.Reset)
    }

    /**
     * この駒を行動可能にする。ターン開始時に呼ばれる
     */
    fun ready() {
        println("READY!! $this")
        action(ActionPhase.READY, ActionEvent.Ready)
    }

    fun disabled() {
        println("DISABLED!! $this")
        action(ActionPhase.DISABLED, ActionEvent.Disabled)
    }

    fun putOn(x: Int, y: Int) {
        existsPosition = Position(x, y)
        action(ActionPhase.PUTTED, ActionEvent.Put)
    }

    fun moveStart(xyToPosition: Position) {
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

    //戦闘でアクションするかRemoveでアクションするかどちらかにしないと自分から戦闘を仕掛けて死ぬと落ちる…
    fun remove() {
//        existsPosition = null
        action(ActionPhase.REMOVED)
    }

}

