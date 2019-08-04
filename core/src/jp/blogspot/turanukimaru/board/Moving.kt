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
        move.board.listener?.hideOption()
        return NoMove(move)
    }

    /**
     * 選択した駒の移動を確定する。移動後確定前にターン終了したときに呼ばれる
     */
    open fun moveCommit(): Moving<UNIT, GROUND> {
        println("moveCancel $selectedPiece")
        move.board.listener?.hideOption()
        return NoMove(move)
    }

    /**
     * 何もつかんでおらず盤面タップしても何も起きない
     */
    abstract fun boardClick(position: UiBoard.Position): Moving<UNIT, GROUND>

    /**
     * 駒を選択状態にする
     */
    abstract fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND>

    /**
     * 選択済みの駒を動かす。 select でも readyAction でも同じ動作のはず
     */
    fun moveSelectedPiece(position: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, oldPosition: UiBoard.Position): Moving<UNIT, GROUND> {
//        println(" moveSelectedPiece($position: UiBoard.Position) $selectedPiece")
        val movable = selectedPiece.boardMove(position)
//        println("ひょっとして $movable false?")
        if (movable) {
            move.board.findActionRoute(position, selectedPiece.actionRange(), listOf(position), oldPosition, selectedPiece)
            move.board.moveToPosition(selectedPiece, position)
            move.board.listener?.showOption(position)
            return Selected(move, selectedPiece, oldPosition, position)
        }
        return moveCancel()
    }

    /**
     * 行動準備/準備後同じ位置をクリックしたときは実行
     */
    fun readyAction(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, oldPosition: UiBoard.Position): Moving<UNIT, GROUND> {
        println("readyAction $this to $targetPiece ")
        if (selectedPiece.actionableAt(targetPos) < 0) return this //アクション出来ない相手は何も起きない.味方へのアクションも通ってしまえるが、そのあと結局攻撃可能箇所が見つからない
        val attackPosition = move.board.findActionPos(selectedPiece, targetPos, oldPosition)
                ?: return this
        moveSelectedPiece(attackPosition, selectedPiece, oldPosition)
        selectedPiece.boardAction(attackPosition, targetPos, targetPiece)//攻撃準備
        move.board.listener?.showOption(targetPos)
        return Ready(move, selectedPiece, oldPosition, attackPosition, targetPiece, targetPos)
    }

    /**
     * アシスト準備/結局行動のコピペだな。
     */
    fun readyAssist(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, oldPosition: UiBoard.Position): Moving<UNIT, GROUND> {
        println("readyAssist $this to $targetPiece ")
        if (selectedPiece.actionableAt(targetPos) < 128) return this //アクション出来ない相手は何も起きない.NoMove返してもいいが…
        val assistPosition = move.board.findAssistPos(selectedPiece, targetPos, oldPosition)
                ?: return this
        moveSelectedPiece(assistPosition, selectedPiece, oldPosition)
        selectedPiece.boardAction(assistPosition, targetPos, targetPiece)//補助準備って攻撃準備と違うんだっけ…？
        move.board.listener?.showOption(targetPos)
        return Ready(move, selectedPiece, oldPosition, assistPosition, targetPiece, targetPos)
    }

    /**
     * 行動実行。
     */
    fun actionCommit(targetPiece: Piece<UNIT, GROUND>, targetPos: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, selectedPos: UiBoard.Position): Moving<UNIT, GROUND> {
        selectedPiece.boardMoveCommit()
        selectedPiece.boardActionCommit(selectedPos, targetPos, targetPiece)
        selectedPiece.clearRoute()
        clear()
        return NoMove(move)
    }

    fun clear() {
        move.routeStack.clear()
        move.board.listener?.hideOption()
    }

    fun into(piece: Piece<UNIT, GROUND>, position: UiBoard.Position, selectedPiece: Piece<UNIT, GROUND>, from: UiBoard.Position): Moving<UNIT, GROUND> {
        selectedPiece.into(piece,from, position)
        return moveCommit()
    }

    /**
     * 追加の操作用ボタン。ないことのほうが多そうなので open fun
     * 問題はこれってゲームによって動作が違う事だよな… fun を定義できるようにするか、PieceとBoardに改めて投げるか？
     */
    open fun optionClick(): Moving<UNIT, GROUND> {
        return this
    }

}

/**
 * 動く前。コンストラクタ引数に細工をすることでフィールドをNull固定にできる
 */
class NoMove<UNIT, GROUND>(override val move: Move<UNIT, GROUND>) : Moving<UNIT, GROUND>(move, null, null, null, null, null) {

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
        println("pieceClick $position, $piece / ${piece.actionPhase} ${piece.owner}  ${move.board.owner} ${piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)}")
        //対象が操作可能でなければ何も起きない
        if (piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)) return NoMove(move)
        piece.select()
        return Selected(move, piece, position, position)
    }

}

/**
 * 駒選択後。コンストラクタを制限してNull固定にしたいがandroid easy ru だっけ？の都合上できない…
 * →本来は抽象クラスを作ってそれを継承しつつコンストラクタを制限すればよい
 */
open class Grasp<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position, override val actionTargetPiece: Piece<UNIT, GROUND>? = null, override val actionTargetPos: UiBoard.Position? = null) : Moving<UNIT, GROUND>(move, selectedPiece, from, to, null, null) {

    /**
     * 選択済みの駒を動かす。 select でも readyAction でも同じ動作のはず
     */
    override fun boardClick(position: UiBoard.Position): Moving<UNIT, GROUND> {
        return moveSelectedPiece(position, selectedPiece, from)
    }

    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        return when {
            piece == selectedPiece -> moveCommit()
            selectedPiece.searchedRouteAt(position) >= 0 -> into(piece, position, selectedPiece, from)//相手に侵入できるときはアクションより侵入を優先。進入時にも確認をしたいときはアクションをしてから一歩進めればいいはず。
            piece.owner == selectedPiece.owner -> readyAssist(piece, position, selectedPiece, from)
            else -> readyAction(piece, position, selectedPiece, from)
        }
//        return when (piece) {
//            selectedPiece -> moveCommit()
//            else -> readyAction(piece, position, selectedPiece, from)//ownerによって変えるべきか…？ TODO: into 入れるならここかな？
//        }
    }

    override fun moveCancel(): Moving<UNIT, GROUND> {
        println("moveCancel $selectedPiece")
        move.board.moveToPosition(selectedPiece, from)
        selectedPiece.moveCancel()
        clear()
        return NoMove(move)
    }

    override fun moveCommit(): Moving<UNIT, GROUND> {
        println("move commit")
        if (to == from) moveCancel()
        else selectedPiece.boardMoveCommitAction()
        clear()
        return NoMove(move)
    }

}

data class Selected<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position) : Grasp<UNIT, GROUND>(move, selectedPiece, from, to, null, null)

open class Ready<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position, override val actionTargetPiece: Piece<UNIT, GROUND>, override val actionTargetPos: UiBoard.Position) : Grasp<UNIT, GROUND>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        return if (piece == selectedPiece || piece == actionTargetPiece) actionCommit(actionTargetPiece, actionTargetPos, selectedPiece, to)
        else readyAction(piece, position, selectedPiece, from)//ownerによって変えるべきか…？
    }

    override fun optionClick(): Moving<UNIT, GROUND> {
        selectedPiece.opt(actionTargetPiece,from, actionTargetPos)
        return moveCommit()
    }

}

data class Dragging<UNIT, GROUND>(override val move: Move<UNIT, GROUND>, override val selectedPiece: Piece<UNIT, GROUND>, override val from: UiBoard.Position, override val to: UiBoard.Position, override val actionTargetPiece: Piece<UNIT, GROUND>?, override val actionTargetPos: UiBoard.Position?) : Grasp<UNIT, GROUND>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: UiBoard.Position, piece: Piece<UNIT, GROUND>): Moving<UNIT, GROUND> {
        if (from == position) return moveCancel()
        val ok = readyAction(piece, position, selectedPiece, from)
        return if (ok == this) moveCancel() else ok.actionCommit(piece, position, selectedPiece, ok.to!!)

    }

    /**
     * 選択済みの駒を動かす。 select でも readyAction でも同じ動作のはず
     */
    override fun boardClick(position: UiBoard.Position): Moving<UNIT, GROUND> {
        return moveSelectedPiece(position, selectedPiece, from)
    }

}