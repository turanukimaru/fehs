package jp.blogspot.turanukimaru.playboard

/**
 * 論理駒。ゲームのルールによらない部分
 */
open class Piece<UNIT, GROUND>(private val contain: UNIT?, var board: Board<UNIT, GROUND>, val owner: Player) {

    open val unit: UNIT get() = contain ?: throw NullPointerException()
    /**
     * 操作的な意味での状態。駒を動かせるかとか動かした後だとか。
     */
    var actionPhase = ActionPhase.DISABLED

    /**
     * アクション。アクションと同時に次の状態に移行することを想定しているが…この関数あんまし要らん気がするな
     */
    open fun action(nextPhase: ActionPhase, actionEvent: ActionEvent = ActionEvent.None) {
        //アクション後、readyかselectedかactedかで3種類はあるな.これ関数であるべきじゃないな…
        // TODO: これはアプリ側に移動。ていうかリスナ入れてはいかんのか
//        actionListener.action(actionEvent, nextPhase, charPosition, fightResult)
        this.actionPhase = nextPhase
    }

    /**
     * 盤上の位置。表示用の位置。移動先を優先して出す
     */
    val charPosition: Position? get() = newPosition ?: existsPosition.p

    /**
     * 盤上の位置。移動先
     */
    var newPosition: Position? = null

    /**
     * 盤上の位置。確定してる位置
     */
    var existsPosition: Positioning = nowhere

    //そろそろマトリクスをIntではなく(pass, stop, action)にするべきなんだろうけど１枡ごとにオブジェクト作るのきっと重いよなあ…
    /**
     * 駒の移動可能範囲
     */
    private var passRoute = mutableListOf<MutableList<Int>>()

    /**
     * 駒の移動範囲から更に効果を及ぼすことのできる範囲
     */
    private var actionRoute: MutableList<MutableList<Int>> = mutableListOf()

    /**
     * 対象の枡がアクション対象になるか。
     */
    fun actionableAt(position: Position): Int {
        if (actionRoute.size == 0) {
            actionRoute = board.searchActionRoute(this, existsPosition.p, position)
        }
        return if (board.positionIsOnBoard(position)) actionRoute[position.x][position.y] else -1
    }

    /**
     * 対象の枡が通過できるか。
     */
    fun searchedRouteAt(position: Position): Int {
        if (passRoute.size == 0) {
            passRoute = board.searchRoute(this)
        }
        return if (board.positionIsOnBoard(position)) passRoute[position.x][position.y] else -1
    }

    /**
     * 効果範囲か。再帰して効果範囲を拡大できるかなので名前変えよう
     */
    open fun isEffective(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int, rotated: Int = rotate(orientation)): Boolean {
        return false
    }

    /**
     * 味方にサポートできる範囲か。優先度は攻撃より低いんだっけ？
     */
    open fun isSupportable(grounds: PiecesAndGrounds<UNIT, GROUND>, orientation: Int, steps: Int, rotated: Int = rotate(orientation)): Boolean {
        return false
    }

    /**
     * 動けるか。再帰して移動できるかの意味だから名前変えたほうが良いかなあ
     */
    open fun isMovable(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int, straight: Boolean, rotated: Int = rotate(orientation)): Boolean {
        return piece == null && steps == 0
    }

    /**
     * 引数の枡に移動することで消費する移動力。移動できないときは負の値
     */
    open fun countStep(piece: Piece<UNIT, GROUND>?, ground: GROUND?, orientation: Int, steps: Int, rotated: Int = rotate(orientation)): Int {
        return if (steps == 0) {
            1
        } else {
            -1
        }
    }

    /**
     * 別のユニットがいた場合にその枡に止まれるか。例えば敵に重なることはできるが味方には重なれないかも。移動範囲内かはこれより先に判定している。通れるけど止まれない枡ってあるかな？
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
     * update 時の処理追加用ポイント。
     * 使い道ないから消しそう
     */
    open fun localUpdate() {
    }

    /**
     * アクション準備用ポイント
     */
    open fun boardAction(source: Position, target: Position, targetPiece: Piece<UNIT, GROUND>): Boolean = true

    /**
     * アクション実行用ポイント
     */
    open fun boardActionCommit(source: Position, target: Position, targetPiece: Piece<UNIT, GROUND>): Boolean = true

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
     * 誰かに移動させられた時。アクションはとるが状態は変わらない
     */
    open fun boardSlide(position: Position): Boolean {
        this.existsPosition = Positioning(position, existsPosition.r)
        this.newPosition = null
        action(actionPhase, ActionEvent.MoveToCharPosition)
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
        this.existsPosition = Positioning(position!!, existsPosition.r)
        this.newPosition = null
        clearRoute()
        return true
    }

    /**
     * 探索したルートを消す。移動後とかには呼ぶ必要がある。
     */
    fun clearRoute() {
        passRoute.clear()
        actionRoute.clear()
    }


    /**
     * タッチ用ポイント
     */
    fun touchDown(): Boolean {
        return touched()
    }

    /**
     * タッチ用ポイント
     */
    open fun touched(): Boolean = true

    /**
     * x,yは移動量。
     * 指に追従して動かしてみるが、実際の操作は枡で判断してるため枡の大きさとキャラの大きさが大幅に違うとおかしくなるのか。
     * 駒から盤にメッセージを送る形にしたいけどどうやればいいかわからん。
     */
    open fun touchDragged(position: Position, dx: Float, dy: Float): Boolean {
        //行動できないときは反応しない
        if (!isActionable) {
            return false
        }
        //ドラッグしてる絵を動かす
//        actionListener.directPos(position, dx, dy)
        //移動可能範囲内で移動したらスタックに積む...
        return dragged(position)
    }

    open fun dragged(position: Position): Boolean = true

    /**
     * 移動可能方向
     */
    open fun moveOrientations(): Array<Int> {
        return arrayOf(0, 2, 4, 6)
    }

    /**
     * 攻撃可能方向
     */
    open fun actionOrientations(): Array<Int> {
        return arrayOf(0, 2, 4, 6)
    }

    /**
     * 補助可能方向
     */
    open fun assistOrientations(): Array<Int> {
        return arrayOf(0, 2, 4, 6)
    }

    /**
     * 攻撃可能射程
     */
    open fun actionRange(): Pair<Int, Int> {
        return Pair(1, 1)
    }

    /**
     * 補助可能射程
     */
    open fun assistRange(): Pair<Int, Int> {
        return Pair(0, 0)
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

    fun putOn(x: Int, y: Int, orientation: Int = 0) {
        existsPosition = Positioning(Position(x, y), orientation)
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
        println("select $this")
        action(ActionPhase.MOVING, ActionEvent.Selected)
    }

    //戦闘でアクションするかRemoveでアクションするかどちらかにしないと自分から戦闘を仕掛けて死ぬと落ちる…
    fun remove() {
//        existsPosition = null
        action(ActionPhase.REMOVED)
    }

    /**
     * 対象のいる枡に侵入する
     */
    open fun intoReady(piece: Piece<UNIT, GROUND>, from: Position, position: Position) {
        println("$this into $position target $piece")
        boardMove(position)
    }

    /**
     * 対象のいる枡に侵入する.owner.takePieceとかは my だな…
     */
    open fun intoCommit(piece: Piece<UNIT, GROUND>, from: Position, position: Position) {
        println("$this into $position target $piece")
//        board.physic.remove(piece)
//        board.physic.move(this, position)
        boardMoveCommit(position)
        owner.takePiece(piece)
        action(ActionPhase.ACTED, ActionEvent.MoveToCharPosition)// ActionEvent は変えたほうがいいな…ていうか ActionEvent は共通系に書いてはいけないはずだよな
    }

    open fun opt(actionTargetPiece: Piece<UNIT, GROUND>?, from: Position, actionTargetPos: Position) {
    }

    /**
     * 供給された orientation を自分の orientation 分逆回転させる。整数論は苦手だ…
     */
    private fun rotate(orientation: Int): Int =
            when {
                orientation < 8 -> if (orientation >= existsPosition.r) (orientation - existsPosition.r) else (orientation - existsPosition.r + 8)
                orientation < 24 -> if (orientation >= existsPosition.r * 3) (orientation - existsPosition.r * 3) else (orientation - existsPosition.r * 3 + 24)
                else -> orientation
            }

    /**
     * LibGDXのアップデートで呼ばれる。
     */
    open fun update() {
        localUpdate()
    }

}

