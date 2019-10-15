package jp.blogspot.turanukimaru.fehs.shogi

import jp.blogspot.turanukimaru.board.ActionListener
import jp.blogspot.turanukimaru.playboard.*

/**
 * 将棋の駒
 * 成るから追加の操作がいるな。ダイアログが必要か。アクション準備からダイアログを出せるようにすべきなんかな。
 * 一手一手だから移動完了後にフェイズ移行する前にダイアログ出せるか？
 */
class ShogiPiece(containUnit: ShogiUnit, board: Board<ShogiUnit, Ground>, owner: Player, val actionListener: ActionListener) : Piece<ShogiUnit, Ground>(containUnit, board, owner) {
    //相手の枡ではとまれるが自分の枡では泊まれない
    override fun isStoppable(piece: Piece<ShogiUnit, Ground>?): Boolean = piece == null || piece.owner != this.owner

    // straight のときのみ。竜王・竜馬のときは override するしかないな。
    override fun isMovable(piece: Piece<ShogiUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int, straight: Boolean, rotated: Int): Boolean {
        //向きは正しいか && 突入できるか && 直線移動が許されている方向か
//        if(rotated > 7 || rotated < 0)println("orientation:$orientation -> rotated:$rotated")
        return unit.orientations.contains(rotated) && (piece == null || piece.owner != owner) && (steps == 0 || (unit.recursiveOrientations.contains(rotated) && straight && steps < 128))
    }

    override fun isEffective(piece: Piece<ShogiUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int, rotated: Int): Boolean = false

    /**
     * 味方にサポートできる範囲か。将棋にはない
     */
    override fun isSupportable(grounds: PiecesAndGrounds<ShogiUnit, Ground>, orientation: Int, steps: Int, rotated: Int): Boolean = false

    //一歩進む。相手がいたら+128とかにすれば相手で止まれるか？
    override fun countStep(piece: Piece<ShogiUnit, Ground>?, ground: Ground?, orientation: Int, steps: Int, rotated: Int): Int = if (piece == null) steps + 1 else 128

    //将棋にアクションはない
    override fun actionOrientations(): Array<Int> = arrayOf()

    /**
     * 攻撃に関する函数多いなあ…
     */
    override fun actionRange(): Pair<Int, Int> = Pair(0, 0)

    /**
     * タッチされたときの処理。コマの動きは共通処理なのでここは表示とか
     */
    override fun touched(): Boolean {
        actionListener.updateInfo({ b ->
            //フォントサイズ替えたいところではある
            b.bitmapFont.draw(b.batch, unit.name, 80f, 940f)
            true
        }, 1)
        return true
    }

    //これはもう親からも消したほうがいい気がしてきた…
    override fun dragged(position: Position): Boolean {
        return true//showActionResult(position)
    }


    //行動結果表示。ドラッグと同じ
    override fun boardAction(source: Position, target: Position, targetPiece: Piece<ShogiUnit, Ground>): Boolean {
        return true
    }

    //行動。相手がいるかは判定済み。向きをここに入れるかはちょっと難しいな…でも入れるしかないか。補助と言っても回復もあるんだしな
    override fun boardActionCommit(source: Position, target: Position, targetPiece: Piece<ShogiUnit, Ground>): Boolean {
        return true
    }

    /**
     * 移動可能方向
     */
    override fun moveOrientations(): Array<Int> {
        return arrayOf(0, 1, 2, 3, 4, 5, 6, 7)//Int Range は Array にならない…
    }

    /**
     * 成る。containUnit を var にすると　と金が持ち駒になったときに金扱いされてよくない。
     * 対象があるかないかで分けるべきかなあ
     */
    override fun opt(actionTargetPiece: Piece<ShogiUnit, Ground>?, from: Position, actionTargetPos: Position) {
        if (unit.promotion != null) unit.promotion = Kin()
        if (actionTargetPiece == null) return
        board.physic.remove(actionTargetPiece)
        boardMoveCommitAction(actionTargetPos)
        owner.takePiece(actionTargetPiece)
    }


    override fun toString(): String = unit.name
}

