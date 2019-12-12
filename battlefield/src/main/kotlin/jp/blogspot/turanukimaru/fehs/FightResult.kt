package jp.blogspot.turanukimaru.fehs

import jp.blogspot.turanukimaru.playboard.Position

data class FightResult(val attacker: BattleUnit, val attackPos: Position, val target: BattleUnit, val targetPos: Position, val attackResults: List<AttackResult>) {
}