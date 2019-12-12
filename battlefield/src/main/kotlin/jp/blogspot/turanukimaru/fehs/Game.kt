package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.playboard.Board
import jp.blogspot.turanukimaru.playboard.Player

/**
 * Created by turanukimaru on 2018/03/15.
 */
class Game<UNIT, GROUND>(val board: Board<UNIT, GROUND>, var playerA: Player, var playerB: Player) : GameInterface {

    /**
     * ターンを終了して相手にターンを渡す...にしても雑だな
     */
    override fun turnEnd() {
        board.move.moveCommit()
        if (board.owner == playerA) {
            board.turnStart(playerB)
        } else {
            board.turnStart(playerA)
        }
    }

    override fun actionDone() {
        if (board.physics.pieceList.none { it.owner == playerA }) {
            println("TODO:playerBの勝利")
            board.gameReset(playerA)
        }
        if (board.physics.pieceList.none { it.owner == playerB }) {
            println("TODO:playerAの勝利")
            board.gameReset(playerA)
        }
    }

}