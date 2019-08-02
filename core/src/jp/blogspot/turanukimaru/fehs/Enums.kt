package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.board.UiBoard

data class FightResult(val attacker: BattleUnit, val attackPos: UiBoard.Position, val target: BattleUnit, val targetPos: UiBoard.Position, val attackResults: List<AttackResult>)
