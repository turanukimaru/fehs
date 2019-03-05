package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position


/**
 * 論理駒。ゲームのルールによらない部分
 */
open class Piece<UNIT, GROUND>(val containUnit: UNIT, var board: Board<UNIT, GROUND>, val owner: Board.Player) {

    /**
     * 向き。駒の向きで移動する方向が変わるときに使う予定
     */
    var orientation = 0

    /**
     * 操作的な意味での状態。駒を動かせるかとか動かした後だとか。
     */
    var actionPhase = ActionPhase.DISABLED
    /**
     * アニメーションのカウント.Uiのほうに移動したいがそうするとリセットしにくいな…
     */
    var animationCount = 0
    var animationStart = false
    var animationTargetPosition: Position? = null
    fun action(action: ActionPhase, target: Position? = null) {
        actionPhase = action
        animationTargetPosition = target
        animationCount = 0
        animationStart = true
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
    var searchedRoute = mutableListOf<MutableList<Int>>()
    /**
     * 駒の移動範囲から更に効果を及ぼすことのできる範囲
     */
    var effectiveRoute = mutableListOf<MutableList<Int>>()

    init {
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

    open fun boardAction(move: Move<UNIT, GROUND>, position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean = true

    open fun boardActionCommit(move: Move<UNIT, GROUND>, position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean = true

    open fun movePiece(): Boolean {
        return true
    }

    /**
     * 移動中。Positionを一つにしてHandに管理責任を持たせるかこっちでもnewPositionを持つか…
     */
    open fun boardMove(move: Move<UNIT, GROUND>, position: Position, targetPiece: Piece<UNIT, GROUND>?): Boolean {
        val targetRoute = searchedRoute[position.x][position.y]
        //移動範囲外は-1
         if (targetRoute < 0) return false
        this.newPosition = position
        action(ActionPhase.MOVING)
        return true
    }

    /**
     * 移動確定。位置を更新して行動後状態にする
     */
    open fun boardMoveCommit(move: Move<UNIT, GROUND>, position: Position): Boolean {
        this.existsPosition = position
        this.newPosition = null
        action(ActionPhase.ACTED)
        return true
    }


    /**
     * タッチしたときはコマを選択済みの時はその駒にアクションし、そうでないときはその駒を選択状態にする...
     * upのタイミングじゃないとダメか？
     */
    fun touchDown(): Boolean {
//        board.move.touchedPiece = this
//        board.move.holdStart = Date().time
        return touched()
    }

    /**
     * オーバーライド用
     */
    open fun touched(): Boolean = true

    /**
     * x,yは移動量。
     */
    fun touchDragged(position: Position): Boolean {
        //行動できないときは反応しない
        if (!isActionable) {
            return false
        }
        //ドラッグしてる絵を動かす
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
        //取り除いた状態。PostionがNullにもなっているはず
        REMOVED,
    }

    /**
     * この駒を行動可能にする。ターン開始時に呼ばれる
     */
    fun ready() {
        print("READY!! ")
        println(this)
        action(ActionPhase.START)
    }

    fun putOn(x: Int, y: Int) {
        existsPosition = Position(x, y)
        action(Piece.ActionPhase.PUTTED)
    }

    fun startPiece(xyToPosition: Position) {
        board.move.startPiece(this, xyToPosition)
    }
}

