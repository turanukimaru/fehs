package jp.blogspot.turanukimaru.board

/**
 * 手／移動のモデル化。コンストラクタは一見無意味に見えるが外部からの参照を可能にしながらNullかどうかの制御をしている
 */
abstract class Moving<UNIT, GROUND>(
        /**
         * 手番や盤上へのアクセス手段。なくてもいいが
         */
        open val move: Move<UNIT, GROUND>,
        /**
         * 現在盤上で選択されている駒
         */
        open val selectedPiece: Piece<UNIT, GROUND>? = null,
        /**
         * 駒を移動中に移動元の枡を記録しておく
         */
        open val from: UiBoard.Position? = null,
        /**
         * 駒を移動中に移動先の枡を記録しておく
         */
        open val to: UiBoard.Position? = null,
        /**
         * アクション先。
         */
        open val actionTargetPiece: Piece<UNIT, GROUND>? = null,
        /**
         * アクション先。なくてもいいのだがあったほうが便利
         */
        open val actionTargetPos: UiBoard.Position? = null
) {
    /**
     * 選択した駒の移動をキャンセルして非選択にする。
     */
    open fun moveCancel(): Moving<UNIT, GROUND> {
        println("moveCancel $selectedPiece")
        return NoMove(move)
    }

    /**
     * 選択した駒の移動を確定する。移動後確定前にターン終了したときに呼ばれる
     */
    open fun moveCommit(): Moving<UNIT, GROUND> {
        println("moveCancel $selectedPiece")
        return NoMove(move)
    }

    /**
     * 何もつかんでおらず盤面タップしても何も起きない
     */
    abstract fun boardClick(position: UiBoard.Position): Moving<UNIT, GROUND>

    /**
     * 駒を選択状態にする
     */
    open fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        //対象が操作可能でなければ何も起きない
        if (piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)) return NoMove(move)
        piece.select()
        return Selected(move, piece, position, position)
    }

    /**
     * 選択済みの駒を動かす。 select でも ready でも同じ動作のはず
     */
    fun moveSelectedPiece(position: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, oldPosition: UiBoard.Position): Moving<UNIT, GROUND> {
        println(" moveSelectedPiece($position: UiBoard.Position) $selectedPiece")
        val movable = selectedPiece.boardMove(position)
        if (movable) {
            move.board.moveToPosition(selectedPiece, position)
            move.stackRoute(position)
            return Selected(move, selectedPiece, oldPosition, position)
        }
        return moveCancel()
    }

    /**
     * 行動準備/準備後同じ位置をクリックしたときは実行
     */
    fun ready(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, oldPosition: UiBoard.Position): Moving<UNIT, GROUND> {
        println("ready $this to $targetPiece ")
        if (targetPiece.owner == move.board.owner) return this //アシストは後で考える
        val targetEffective = selectedPiece.effectiveRouteOf(targetPos)
        if (targetEffective < 0) return this //アクション出来ない相手は何も起きない.NoMove返してもいいが…
//            if (this.actionTargetPos == targetPos) {//これなんだっけ？
//                return actionCommit(targetPiece, targetPos)
//                //ここに後処理がいるとしてとりあえず動くところまで見よう
//            }
        val attackablePosition = move.board.findActionPos(selectedPiece, targetPos, oldPosition)
        move.stackRoute(targetPos)
        //攻撃可能位置が見つからなかったらどうするか…あと途中全部埋めたい
        if (attackablePosition != null && attackablePosition != targetPos) move.stackRoute(attackablePosition)
        moveSelectedPiece(attackablePosition!!, selectedPiece, oldPosition)
        selectedPiece.boardAction(targetPos, targetPiece)//攻撃準備
        return Ready(move, selectedPiece, oldPosition, attackablePosition, targetPiece, targetPos)
    }

    /**
     * 行動実行
     */
    fun actionCommit(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        selectedPiece.boardMoveCommit()
        selectedPiece.boardActionCommit(targetPos, targetPiece)
        move.routeStack.clear()
        return NoMove(move)
    }

}

/**
 * 動く前。コンストラクタ引数に細工をすることでフィールドをNull固定にできる
 */
class NoMove<UNIT, GROUND>(override val move: Move<UNIT, GROUND>) : Moving<UNIT, GROUND>(move, null, null, null, null, null) {
    /**
     * 選択した駒の移動をキャンセルして非選択にする。
     */
    override fun moveCancel(): Moving<UNIT, GROUND> {
        println("moveCancel $selectedPiece")
        return NoMove(move)
    }

    /**
     * 選択した駒の移動を確定する。移動後確定前にターン終了したときに呼ばれる
     */
    override fun moveCommit(): Moving<UNIT, GROUND> {
        return NoMove(move)
    }

    /**
     * 何もつかんでおらず盤面タップしても何も起きない
     */
    override fun boardClick(position: UiBoard.Position): Moving<UNIT, GROUND> {
        return NoMove(move)
    }

    /**
     * 駒を選択状態にする
     */
    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        //対象が操作可能でなければ何も起きない
        if (piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)) return NoMove(move)
        piece.select()
        return Selected(move, piece, position, position)
    }

}

/**
 * 駒選択後。コンストラクタを制限してNull固定にしたいが継承の都合上できない…
 * →本来は抽象クラスを作ってそれを継承しつつコンストラクタを制限すればよい
 */
open class Grasp<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position, override val actionTargetPiece: Piece<UNIT, GROUND>? = null, override val actionTargetPos: UiBoard.Position? = null) : Moving<UNIT, GROUND>(move, selectedPiece, from, to, null, null) {

    /**
     * 選択済みの駒を動かす。 select でも ready でも同じ動作のはず
     */
    override fun boardClick(position: UiBoard.Position): Moving<UNIT, GROUND> {
        return moveSelectedPiece(position, selectedPiece, from)
    }

    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        return when (piece) {
            selectedPiece -> moveCommit()
            else -> ready(piece, position, selectedPiece, from)//ownerによって変えるべきか…？
        }
    }

    override fun moveCancel(): Moving<UNIT, GROUND> {
        println("moveCancel $selectedPiece")
        move.board.moveToPosition(selectedPiece, from)
        selectedPiece.moveCancel()
        move.routeStack.clear()
        return NoMove(move)
    }

    override fun moveCommit(): Moving<UNIT, GROUND> {
        println("move commit")
        if (to == from) moveCancel()
        else selectedPiece.boardMoveCommitAction()
        move.routeStack.clear()
        return NoMove(move)
    }

}

data class Selected<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position) : Grasp<UNIT, GROUND>(move, selectedPiece, from, to, null, null)

open class Ready<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position, override val actionTargetPiece: Piece<UNIT, GROUND>, override val actionTargetPos: UiBoard.Position) : Grasp<UNIT, GROUND>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        return if (piece == selectedPiece || piece == actionTargetPiece) actionCommit(actionTargetPiece, actionTargetPos, selectedPiece)
        else ready(piece, position, selectedPiece, from)//ownerによって変えるべきか…？
    }
}

data class Dragging<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position, override val actionTargetPiece: Piece<UNIT, GROUND>? = null, override val actionTargetPos: UiBoard.Position? = null) : Grasp<UNIT, GROUND>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        return if (piece == selectedPiece) moveCancel() else ready(piece, position, selectedPiece, from).actionCommit(piece, position, selectedPiece)
    }
}
