package jp.blogspot.turanukimaru.playboard

/**
 * 論理駒。ゲームのルールによらない部分
 * P はルールを織り込んだ駒。駒の能力を直接書いてもいいし、Pieceを継承した駒を一度挟んでも良い。
 * それが常に存在するか？は難しい。単なるマーカーのこともあるし…
 */
open class Piece<P, TILE>(private val contain: P?, var board: Board<P, TILE>, val owner: Player) {

    /**
     * 中身を取り出す。マーカーの時は中身が無いので例外となる
     */
    open val contains: P get() = contain ?: throw NullPointerException()

    /**
     * 操作的な意味での状態。駒を動かせるかとか動かした後だとか。
     */
    var actionPhase = ActionPhase.DISABLED

    /**
     * アクション。アクションと同時に次の状態に移行することを想定しているが…この関数あんまし要らん気がするな
     */
    open fun action(nextPhase: ActionPhase, actionEvent: ActionEvent = ActionEvent.None) {
        //アクション後、readyかselectedかactedかで3種類はあるな.これ関数であるべきじゃないな…
        this.actionPhase = nextPhase
    }

    /**
     * 盤上の位置。表示用の位置。移動先を優先して出す
     */
    val charPosition: Position get() = newPosition ?: existsPosition

    /**
     * 盤上の位置。移動先
     */
    var newPosition: Position? = null

    /**
     * 盤上の位置。確定してる位置
     */
    var existsPosition: Position = nowhere

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
     * 対象の枡がアクション対象になるか。なるときは…なんだっけ？ならないときは-1
     */
    fun actionableAt(position: Position): Int {
        if (existsPosition == nowhere) return -1
        if (actionRoute.size == 0) {
            if (passRoute.size == 0) {
                passRoute = board.searchRoute(this)
            }
            actionRoute = board.searchActionRoute(this, existsPosition)
        }
        return if (board.physics.positionIsOnBoard(position)) actionRoute[position.x][position.y] else -1
    }

    /**
     * 対象の枡が通過できるか。できるときは移動コスト。できないときは-1
     */
    fun searchedRouteAt(position: Position): Int {
        if (passRoute.size == 0) {
            passRoute = board.searchRoute(this)
        }
        return if (board.physics.positionIsOnBoard(position)) passRoute[position.x][position.y] else -1
    }

    /**
     * 基本的に敵への行動範囲か。
     */
    open fun isActionable(piece: Piece<P, TILE>?, tile: TILE?, orientation: Int, payed: Int, rotated: Int = rotate(orientation)): Boolean =
            false

    /**
     * 基本的に味方へのサポート範囲か。
     */
    open fun isSupportable(tiles: PiecesAndTiles<P, TILE>, orientation: Int, payed: Int, rotated: Int = rotate(orientation)): Boolean =
            false

    /**
     * 盤面・移動方向を考慮してそこへ動けるか
     */
    open fun isMovable(piece: Piece<P, TILE>?, tile: TILE?, orientation: Int, payed: Int, ahead: Boolean, rotated: Int = rotate(orientation)): Boolean =//デフォルト動作は対象が空いていて、まだ一歩も動いて無ければ
            piece == null && payed == 0

    /**
     * 対象の枡に移動することで消費する移動力。移動できないときは負の値
     */
    open fun stepCost(piece: Piece<P, TILE>?, tile: TILE?, orientation: Int, payed: Int, rotated: Int = rotate(orientation)): Int {
        //デフォルト実装は「向きに関係なく一歩だけ動ける」
        return if (payed == 0) {
            1
        } else {
            -1
        }
    }

    /**
     * 別のユニットがいた場合にその枡に止まれるか。例えば敵に重なることはできるが味方には重なれないかも。移動範囲内かはこれより先に判定している。通れるけど止まれない枡ってあるかな？
     */
    open fun isStoppable(piece: Piece<P, TILE>?): Boolean = piece == null


    /**行動前・選択状態・移動後は行動可能
     *
     */
    val isActionable get() = actionPhase == ActionPhase.READY || actionPhase == ActionPhase.MOVING

    /**
     * 画面上でクリックされたときに呼び出される。がドラッグから指を挙げたときにも反応するのでうまく使えない・・・
     */
    open fun clicked(position: Position): Boolean {
        if (!isActionable) {
            return false
        }
        return true
    }

    /**
     * 画面上でタッチから指を離したとき。
     */
    open fun touchUp(position: Position): Boolean = true


    /**
     * update 時の処理追加用ポイント。
     * 使い道ないから消しそう
     */
    open fun localUpdate() {
    }

    /**
     * アクション準備用ポイント
     */
    open fun boardAction(source: Position, target: Position, targetPiece: Piece<P, TILE>): Boolean = true

    /**
     * アクション実行用ポイント
     */
    open fun boardActionCommit(source: Position, target: Position, targetPiece: Piece<P, TILE>): Boolean = true

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
        this.existsPosition = position
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
   open fun boardMoveCommit(position: Position? = newPosition): Boolean {
        this.existsPosition = position!!
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
    fun touchDown(): Boolean = touched()

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
     * 移動可能方向...array は mutable だから list のほうが良いかな？
     */
    open fun moveOrientations(): Array<Int> = arrayOf(0, 2, 4, 6)

    /**
     * 攻撃可能方向
     */
    open fun actionOrientations(): Array<Int> = arrayOf(0, 2, 4, 6)

    /**
     * 補助可能方向
     */
    open fun assistOrientations(): Array<Int> = arrayOf(0, 2, 4, 6)

    /**
     * 攻撃可能射程
     */
    open fun actionRange(): Pair<Int, Int> = Pair(1, 1)

    /**
     * 補助可能射程
     */
    open fun assistRange(): Pair<Int, Int> = Pair(0, 0)

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
        existsPosition = Position(x, y, orientation)
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
        existsPosition = nowhere
        action(ActionPhase.REMOVED)
    }

    /**
     * 対象のいる枡に侵入する
     */
    open fun intoReady(piece: Piece<P, TILE>, from: Position, position: Position) {
        println("$this into $position target $piece")
        boardMove(position)
    }

    /**
     * 対象のいる枡に侵入する.owner.takePieceとかは my だな…
     * into event と commit は分けるべきか
     */
    open fun intoCommit(target: Piece<P, TILE>, from: Position, position: Position) {
        println("$this into $position target $target")
//        move側でやってたわ
//        board.physics.remove(piece)
//        board.physics.move(this, position)
        boardMoveCommit(position)
        owner.takePiece(target)
        action(ActionPhase.ACTED, ActionEvent.MoveToCharPosition)// ActionEvent は変えたほうがいいな…ていうか ActionEvent は共通系に書いてはいけないはずだよな
    }

    /**
     * 公道後に選択があるときに使う
     */
    open fun opt(actionTargetPiece: Piece<P, TILE>?, from: Position, actionTargetPos: Position) {
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
     * LibGDXのアップデートで呼ばれる。UIBoardから ActionListener を直接呼ぶほうが良いだろうか…
     */
    open fun update() {
        localUpdate()
    }

}

