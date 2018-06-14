package jp.blogspot.turanukimaru.board

import jp.blogspot.turanukimaru.board.UiBoard.Position
import java.util.*


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
     * アニメーションのカウント
     */
    var animationCount = 0
    /**
     * 盤上の位置。なくても良いが盤上をサーチすると時間かかるんだよな。ボード上でのみ変更すること
     */
    var x:Int? = null

    /**
     * 盤上の位置。なくても良いが盤上をサーチすると時間かかるんだよな。ボード上でのみ変更すること
     */
    var y:Int? = null

    val isOnBoard get() = x != null && y != null

    init {
    }

    /**
     * 効果範囲か。再帰して効果範囲を拡大できるかなので名前変えよう
     */
    open fun isEffective(piece: Piece<*, *>?, ground: GROUND?, orientation: Int, steps: Int): Boolean {
        return false
    }

    /**
     * 動けるか。再帰して移動できるかの意味だから名前変えたほうが良いかなあ
     */
    open fun isMovable(piece: Piece<*, *>?, ground: GROUND?, orientation: Int, steps: Int): Boolean {
        return piece == null && steps == 0
    }

    /**
     * この枡に移動することで消費する移動力
     */
    open fun countStep(piece: Piece<*, *>?, ground: GROUND?, orientation: Int, steps: Int): Int {
        return if (steps == 0) {
            1
        } else {
            -1
        }
    }

    /**
     * この枡に移動することで消費する効果範囲。ただ枡を通過できるかはともかく効果範囲が減るとかいうゲーム無かった気がするから不要か？
     */
    open fun countEffectiveStep(piece: Piece<*, *>?, ground: GROUND?, orientation: Int, steps: Int): Int = if (steps == 0) 1 else -1


    /**
     * 別のユニットがいた場合にその枡に止まれるか。例えば敵に重なることはできるが味方には重なれないかも。移動範囲内かはこれより先に判定している
     */
    open fun isStopable(otherUnit: UNIT?): Boolean = otherUnit == null


    /**行動前・選択状態・移動後は行動可能
     *
     */
    fun isActionable() = actionPhase == ActionPhase.READY || actionPhase == ActionPhase.SELECTED || actionPhase == ActionPhase.DRAGGING || actionPhase == ActionPhase.MOVED

    /**
     * ドラッグから指を挙げたときにも反応するのでうまく使えない・・・
     */
    open fun clicked(position: Position): Boolean {
        if (!isActionable()) {
            return false
        }
        println("stageDropletImage1がクリックされた！")
        println("stageDropletImage1がクリックされた！")
        return true
    }

    /**
     * タッチから指を離したとき。移動範囲・効果範囲外のときは駒をもとの場所に戻す
     */
    fun touchUp(position: Position): Boolean {
        //あードラッグかどうかを先に判定しないといけないのか選択状態化で挙動が変わる
        val selected = board.hand.selectedPiece
        when (selected) {
            //選択状態でなければ選択
            null -> {
                //行動準備時のみ動く
                if (!isActionable()) {
                    return false
                }
                //未選択時は選択。選択後は何もしない。これREADYであるかよりもボードの選択駒がこれかどうかで見るべきだよなあ
                if (this.actionPhase == ActionPhase.READY) {
                    this.actionPhase = ActionPhase.SELECTED
                    board.selectPiece(this)
                }
            }
            this -> {
            }
            else -> {
            }
        }
        //盤外/移動範囲外/効果範囲外は最初に戻す。状態は関係なし
        if (!board.positionIsOnBoard(position) || (board.searchedRoute[position.x][position.y] < 0 && board.effectiveRoute[position.x][position.y] < 0)) {
            board.moveCancel()
            this.actionPhase = ActionPhase.READY
            return false
        }
        if (board.isAnotherPiece(this, position)) {

        }
        return true
    }

    open fun touchRelease(position: Position): Boolean=true

    open fun movePiece(): Boolean {
        return true
    }

    /**
     * 盤をクリックしたときはそこでtouchupしたかのように動く。ただし指の経路がなくなるので攻撃位置を計算する必要が出てくる
     * イベントどうすっかな。なくていいか。後で削除しよう
     */
    open fun boardClicked(hand: Hand<UNIT, GROUND>, position: Position): Boolean {
        //盤外/移動範囲外/効果範囲外は最初に戻す。状態は関係なし
        if (!board.positionIsOnBoard(position) || (board.searchedRoute[position.x][position.y] < 0 && board.effectiveRoute[position.x][position.y] < 0)) {
            board.moveCancel()
            board.updateInfo = { _ -> true }
            this.actionPhase = ActionPhase.READY
            return false
        }
        return true
    }


    /**
     * タッチしたときはコマを選択済みの時はその駒にアクションし、そうでないときはその駒を選択状態にする...
     * upのタイミングじゃないとダメか？
     */
    fun touchDown(): Boolean {
//        board.hand.touchDownPiece = this
//        board.hand.holdStart = Date().time
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
        if (!isActionable()) {
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
     * 駒の状態。準備完了や選択された、移動済みだけど行動は完了していないなど
     */
    enum class ActionPhase {
        //自分の手番でないので動かせない
        DISABLED,
        //自分の手番で動かせる
        READY,
        //選択状態
        SELECTED,
        //ドラッグ中。駒の位置以外はSELECTEDと同じになるはず
        DRAGGING,
        //移動したけど行動が確定してない
        MOVED,
        //移動範囲用だけどなくていいかなあ
        MARK,
        //行動確定後
        ACTED,
    }

    /**
     * この駒を行動可能にする。ターン開始時に呼ばれる
     */
    fun ready() {
        print("READY!! ")
        println(this)
        this.actionPhase = ActionPhase.READY
    }

    fun putOn(x: Int, y: Int) {
        this.x = x
        this.y = y
        actionPhase = Piece.ActionPhase.DISABLED
        animationCount = 0
    }
}

