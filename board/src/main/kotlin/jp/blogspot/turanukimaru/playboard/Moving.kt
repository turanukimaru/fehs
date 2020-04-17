package jp.blogspot.turanukimaru.playboard

/**
 * 手／移動のモデル化。Boardのモデルの一部「ではない」ことに注意。つまりアプリに移動しても良い。
 * また、Boardの一部ではないため移動できるかだけ判定し位置関係は判定していないし自動で移動したりもしない。
 * 例えば離れたところに攻撃するときに自動的に隣接したりはしない。
 */
abstract class Moving<UNIT, TILE>(
        /**
         * 手番や盤上へのアクセス手段。なくてもいいが
         */
        open val move: Move<UNIT, TILE>,
        /**
         * 現在盤上で選択されている駒
         */
        open val selectedPiece: Piece<UNIT, TILE>? = null,
        /**
         * 駒を移動中に移動元の枡を記録しておく
         */
        open val from: Position? = null,
        /**
         * 駒を移動中に移動先の枡を記録しておく
         */
        open val to: Position? = null,
        /**
         * アクション先。
         */
        open val actionTargetPiece: Piece<UNIT, TILE>? = null,
        /**
         * アクション先。なくてもいいのだがあったほうが便利
         */
        open val actionTargetPos: Position? = null
) {
    /**
     * 選択した駒があれば移動をキャンセルして非選択にする。
     */
    open fun moveCancel(): Moving<UNIT, TILE> {
        println("moveCancel $selectedPiece")
        selectedPiece?.moveCancel()
        move.board.listener?.hideOption()
        return NoMove(move)
    }

    /**
     * 選択した駒の移動を確定する。移動後確定前にターン終了したときに呼ばれる
     */
    open fun moveCommit(): Moving<UNIT, TILE> {
        println("moveCommit $selectedPiece")
        move.board.physics.move(selectedPiece!!, to!!)//やはり階層がおかしいか…
        move.board.listener?.hideOption()
        return NoMove(move)
    }

    /**
     * 盤をクリックしたときの動作を定義する
     */
    abstract fun boardClick(position: Position): Moving<UNIT, TILE>

    /**
     * 駒をクリックしたときの動作を定義する
     */
    abstract fun pieceClick(position: Position, piece: Piece<UNIT, TILE>): Moving<UNIT, TILE>

    /**
     * 選択済みの駒を動かす。 select でも actionReady でも同じ動作のはず
     */
    fun moveSelectedPiece(position: Position, selectedPiece: Piece<UNIT, TILE>, oldPosition: Position): Moving<UNIT, TILE> {
//        println(" moveSelectedPiece($position: Position) $selectedPiece")
        val movable = selectedPiece.boardMove(position)
//        println("ひょっとして $movable false?")
        if (movable) {
            //TODO:超射程武器は処理分けないとダメだな
//            move.board.findActionRoute(position, selectedPiece.actionRange(), listOf(position), oldPosition, selectedPiece)
// デフォルトで戦闘アイコンを出す。
            move.board.listener?.showOption(position)
            return Grasp(move, selectedPiece, oldPosition, position)
        }
        return moveCancel()
    }

    /**
     * 行動準備/準備後同じ位置をクリックしたときは実行
     */
    fun actionReady(targetPiece: Piece<UNIT, TILE>, targetPos: Position, selectedPiece: Piece<UNIT, TILE>, oldPosition: Position): Moving<UNIT, TILE> {
        println("actionReady $this to $targetPiece ")
        if (selectedPiece.actionableAt(targetPos) < 0) return this //アクション出来ない相手は何も起きない.味方へのアクションも通ってしまえるが、そのあと結局攻撃可能箇所が見つからない
        val attackPosition = move.board.findActionPos(selectedPiece, targetPos, oldPosition)
                ?: return this
        moveSelectedPiece(attackPosition, selectedPiece, oldPosition)
        selectedPiece.boardAction(attackPosition, targetPos, targetPiece)//攻撃準備
        move.board.listener?.showOption(targetPos)
        return ActionReady(move, selectedPiece, oldPosition, attackPosition, targetPiece, targetPos)
    }

    /**
     * アシスト準備/結局行動のコピペだな。
     */
    fun assistReady(targetPiece: Piece<UNIT, TILE>, targetPos: Position, selectedPiece: Piece<UNIT, TILE>, oldPosition: Position): Moving<UNIT, TILE> {
        println("assistReady $this to $targetPiece ")
        if (selectedPiece.actionableAt(targetPos) < 128) return this //アクション出来ない相手は何も起きない.NoMove返してもいいが…
        val assistPosition = move.board.findAssistPos(selectedPiece, targetPos, oldPosition)
                ?: return this
        moveSelectedPiece(assistPosition, selectedPiece, oldPosition)
        selectedPiece.boardAction(assistPosition, targetPos, targetPiece)//補助準備って攻撃準備と違うんだっけ…？
        move.board.listener?.showOption(targetPos)
        return ActionReady(move, selectedPiece, oldPosition, assistPosition, targetPiece, targetPos)
    }

    /**
     * 行動実行。
     */
    fun actionCommit(targetPiece: Piece<UNIT, TILE>, targetPos: Position, selectedPiece: Piece<UNIT, TILE>, selectedPos: Position): Moving<UNIT, TILE> {
        move.board.physics.move(selectedPiece, to!!)
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

    fun intoReady(target: Piece<UNIT, TILE>, position: Position, selectedPiece: Piece<UNIT, TILE>, from: Position): Moving<UNIT, TILE> {
        println("intoReady")
        selectedPiece.intoReady(target, from, position)
        move.board.listener?.showOption(position)
        return Into(move, selectedPiece, from, position, target, position)
    }

    fun intoCommit(target: Piece<UNIT, TILE>, position: Position, selectedPiece: Piece<UNIT, TILE>, from: Position): Moving<UNIT, TILE> {
        println("intoCommit")
        move.board.physics.remove(target)
        move.board.physics.move(selectedPiece, position)
        selectedPiece.intoCommit(target, from, position)
        clear()
        return NoMove(move)
    }

    /**
     * 追加の操作用ボタン。ないことのほうが多そうなので open fun
     * 問題はこれってゲームによって動作が違う事だよな… fun を定義できるようにするか、PieceとBoardに改めて投げるか？
     */
    open fun optionClick(): Moving<UNIT, TILE> = this

}

/**
 * 動く前。コンストラクタ引数に細工をすることでフィールドをNull固定にできる
 */
data class NoMove<UNIT, TILE>(override val move: Move<UNIT, TILE>) : Moving<UNIT, TILE>(move, null, null, null, null, null) {

    /**
     * 何もつかんでおらず盤面タップしても何も起きない
     */
    override fun boardClick(position: Position): Moving<UNIT, TILE> = NoMove(move)

    /**
     * 駒を選択状態にする
     */
    override fun pieceClick(position: Position, piece: Piece<UNIT, TILE>): Moving<UNIT, TILE> {
//        println("pieceClick $position, $piece / ${piece.actionPhase} ${piece.owner}  ${move.board.owner} ${piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)}")
        //対象が操作可能でなければ何も起きない
        if (piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)) return NoMove(move)
        if (piece.actionPhase == ActionPhase.MOVING) println("移動状態バグ：$piece")
        piece.select()
        return Grasp(move, piece, position, position)
    }

}

/**
 * 駒選択後。Abstract だが instance が作れないわけではなく、 data class に継承して使わないと上手く動かない
 * コンストラクタを制限してNull固定にしたいがandroid easy ru だっけ？の都合上できない…
 * →本来は抽象クラスを作ってそれを継承しつつコンストラクタを制限すればよい
 */
open class AbstractGrasp<UNIT, TILE>(override val move: Move<UNIT, TILE>, override val selectedPiece: Piece<UNIT, TILE>, override val from: Position, override val to: Position, override val actionTargetPiece: Piece<UNIT, TILE>? = null, override val actionTargetPos: Position? = null) : Moving<UNIT, TILE>(move, selectedPiece, from, to, null, null) {

    /**
     * 選択済みの駒を動かす。 select でも actionReady でも同じ動作のはず
     */
    override fun boardClick(position: Position): Moving<UNIT, TILE> =
            moveSelectedPiece(position, selectedPiece, from)

    override fun pieceClick(position: Position, piece: Piece<UNIT, TILE>): Moving<UNIT, TILE> {
        println("Grasp pieceClick $position, $piece / ${piece.actionPhase} ${piece.owner}  ${move.board.owner} ${piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)}")
        return when {
            piece == selectedPiece -> moveCommit()
            piece.owner == selectedPiece.owner -> assistReady(piece, position, selectedPiece, from)
            selectedPiece.searchedRouteAt(position) >= 0 -> intoReady(piece, position, selectedPiece, from)//相手に侵入できるときはアクションより侵入を優先。進入時にも確認をしたいときはアクションをしてから一歩進めればいいはず。
            else -> actionReady(piece, position, selectedPiece, from)
        }
    }

    override fun optionClick(): Moving<UNIT, TILE> {
        selectedPiece.opt(actionTargetPiece, from, to)
        return moveCommit()
    }

    override fun moveCancel(): Moving<UNIT, TILE> {
        println("moveCancel $selectedPiece")
//        move.board.physics.move(selectedPiece, from)
        selectedPiece.moveCancel()
        clear()
        return NoMove(move)
    }

    override fun moveCommit(): Moving<UNIT, TILE> {
        println("move commit but $to == $from")
        if (to == from) moveCancel()
        else selectedPiece.boardMoveCommitAction()
        clear()
        return NoMove(move)
    }

}

/**
 * Grasp を data class にしたもの
 */
data class Grasp<UNIT, TILE>(override val move: Move<UNIT, TILE>, override val selectedPiece: Piece<UNIT, TILE>, override val from: Position, override val to: Position) : AbstractGrasp<UNIT, TILE>(move, selectedPiece, from, to, null, null)

open class ActionReady<UNIT, TILE>(override val move: Move<UNIT, TILE>, override val selectedPiece: Piece<UNIT, TILE>, override val from: Position, override val to: Position, override val actionTargetPiece: Piece<UNIT, TILE>, override val actionTargetPos: Position) : AbstractGrasp<UNIT, TILE>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: Position, piece: Piece<UNIT, TILE>): Moving<UNIT, TILE> {
        println("ActionReady pieceClick $position, $piece / ${piece.actionPhase} ${piece.owner}  ${move.board.owner} ${piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)}")
        return when {
            (piece == selectedPiece || piece == actionTargetPiece) -> actionCommit(actionTargetPiece, actionTargetPos, selectedPiece, to)
            selectedPiece.searchedRouteAt(position) >= 0 -> intoReady(piece, position, selectedPiece, from)
            else -> actionReady(piece, position, selectedPiece, from)//ownerによって変えるべきか…？
        }
    }

    override fun optionClick(): Moving<UNIT, TILE> {
        selectedPiece.opt(actionTargetPiece, from, actionTargetPos)
        return actionCommit(actionTargetPiece, actionTargetPos, selectedPiece, to)
    }

}

abstract class IntoReady<UNIT, TILE>(override val move: Move<UNIT, TILE>, override val selectedPiece: Piece<UNIT, TILE>, override val from: Position, override val to: Position, override val actionTargetPiece: Piece<UNIT, TILE>, override val actionTargetPos: Position) : AbstractGrasp<UNIT, TILE>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: Position, piece: Piece<UNIT, TILE>): Moving<UNIT, TILE> {
        println("ActionReady pieceClick $position, $piece / ${piece.actionPhase} ${piece.owner}  ${move.board.owner} ${piece.owner != move.board.owner || (piece.actionPhase != ActionPhase.MOVING && piece.actionPhase != ActionPhase.READY)}")
        return when {//into と action が同時に成立することはあり得ないから先に into 判定をすれば十分か？
            (piece == selectedPiece || piece == actionTargetPiece) -> intoCommit(actionTargetPiece, actionTargetPos, selectedPiece, to)
            selectedPiece.searchedRouteAt(position) >= 0 -> intoReady(piece, position, selectedPiece, from)
            else -> actionReady(piece, position, selectedPiece, from)//ownerによって変えるべきか…？
        }
    }

    override fun optionClick(): Moving<UNIT, TILE> {
        selectedPiece.opt(actionTargetPiece, from, actionTargetPos)
        return intoCommit(actionTargetPiece, actionTargetPos, selectedPiece, to)
    }

}

data class Into<UNIT, TILE>(override val move: Move<UNIT, TILE>, override val selectedPiece: Piece<UNIT, TILE>, override val from: Position, override val to: Position, override val actionTargetPiece: Piece<UNIT, TILE>, override val actionTargetPos: Position) : IntoReady<UNIT, TILE>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos)

data class Dragging<UNIT, TILE>(override val move: Move<UNIT, TILE>, override val selectedPiece: Piece<UNIT, TILE>, override val from: Position, override val to: Position, override val actionTargetPiece: Piece<UNIT, TILE>?, override val actionTargetPos: Position?) : AbstractGrasp<UNIT, TILE>(move, selectedPiece, from, to, actionTargetPiece, actionTargetPos) {
    override fun pieceClick(position: Position, piece: Piece<UNIT, TILE>): Moving<UNIT, TILE> {
        if (from == position) return moveCancel() // ↓たまに行動完了になってない…
        if (selectedPiece.searchedRouteAt(position) >= 0) intoReady(piece, position, selectedPiece, from).intoCommit(piece, position, selectedPiece, from)// commit はしないほうがいいかオプションで変えられるようにすべき
        val ok = actionReady(piece, position, selectedPiece, from)
        return if (ok == this) moveCancel() else ok.actionCommit(piece, position, selectedPiece, ok.to!!)

    }

    /**
     * 選択済みの駒を動かす。 select でも actionReady でも同じ動作のはず
     */
    override fun boardClick(position: Position): Moving<UNIT, TILE> =
            moveSelectedPiece(position, selectedPiece, from)

}
