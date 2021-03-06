package jp.blogspot.turanukimaru.shogi

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
    override fun isMovable(piece: Piece<ShogiUnit, Ground>?, tile: Ground?, orientation: Int, payed: Int, ahead: Boolean, rotated: Int): Boolean =//向きは正しいか && 突入できるか && 直線移動が許されている方向か
//        if(rotated > 7 || rotated < 0)println("orientation:$orientation -> rotated:$rotated")
            contains.orientations.contains(rotated) && (piece == null || piece.owner != owner) && (payed == 0 || (contains.recursiveOrientations.contains(rotated) && ahead && payed < 128))

    override fun isActionable(piece: Piece<ShogiUnit, Ground>?, tile: Ground?, orientation: Int, payed: Int, rotated: Int): Boolean = false

    /**
     * 味方にサポートできる範囲か。将棋にはない
     */
    override fun isSupportable(tiles: PiecesAndTiles<ShogiUnit, Ground>, orientation: Int, payed: Int, rotated: Int): Boolean = false

    //一歩進む。相手がいたら+128とかにすれば相手で止まれるか？
    override fun stepCost(piece: Piece<ShogiUnit, Ground>?, tile: Ground?, orientation: Int, payed: Int, rotated: Int): Int = if (piece == null) payed + 1 else 128

    //将棋にアクションはない
    override fun actionOrientations(): Array<Int> = arrayOf()

    /**
     * 攻撃に関する函数多いなあ…
     */
    override fun actionRange(): Pair<Int, Int> = Pair(0, 0)

    /**
     * タッチされたときの処理。将棋なので別に必要だけど作る必要ない気はするな
     */
    override fun touched(): Boolean =//        actionListener.updateInfo({ b ->
//            //フォントサイズ替えたいところではある
//            b.bitmapFont.draw(b.batch, unit.name, 80f, 940f)
//            true
//        }, 1)
            true

    //これはもう親からも消したほうがいい気がしてきた…
    override fun dragged(position: Position): Boolean = true//showActionResult(position)


    //行動結果表示。ドラッグと同じ
    override fun boardAction(source: Position, target: Position, targetPiece: Piece<ShogiUnit, Ground>): Boolean =
            true

    //行動。相手がいるかは判定済み。向きをここに入れるかはちょっと難しいな…でも入れるしかないか。補助と言っても回復もあるんだしな
    override fun boardActionCommit(source: Position, target: Position, targetPiece: Piece<ShogiUnit, Ground>): Boolean =
            true

    /**
     * 移動可能方向
     */
    override fun moveOrientations(): Array<Int> =
            arrayOf(0, 1, 2, 3, 4, 5, 6, 7)//Int Range は Array にならない…

    /**
     * 成る。containUnit を var にすると　と金が持ち駒になったときに金扱いされてよくない。
     * 対象があるかないかで分けるべきかなあ
     */
    override fun opt(actionTargetPiece: Piece<ShogiUnit, Ground>?, from: Position, actionTargetPos: Position) {
        if (contains.promotion != null) contains.promotion = Kin()
        if (actionTargetPiece == null) return
        board.physics.remove(actionTargetPiece)
        boardMoveCommit(actionTargetPos)
        owner.takePiece(actionTargetPiece)
    }


    override fun toString(): String = contains.name
}

