package jp.blogspot.turanukimaru.fehs.shogi


/**
 * 将棋の駒が持つ能力
 */
open class ShogiUnit(val name: String, val orientations: List<Int>, val recursiveOrientations: List<Int>, var promotion: ShogiUnit?) {//あれ竜王と竜馬がうまく処理できないなこれ…まぁ継承でなんとかなるか
}

class Fu : ShogiUnit("歩", listOf(0), listOf(), null)
class Kyosya : ShogiUnit("香車", listOf(0), listOf(), null)
class Keima : ShogiUnit("桂馬", listOf(9, 23), listOf(), null)
class Gin : ShogiUnit("銀", listOf(0, 1, 3, 5, 7), listOf(), null)
class Kin : ShogiUnit("金", listOf(0, 1, 2, 4, 6, 7), listOf(), null)
class Kaku : ShogiUnit("角", listOf(1, 3, 5, 7), listOf(1, 3, 5, 7), null)
class Hisya : ShogiUnit("飛車", listOf(0, 2, 4, 6), listOf(0, 2, 4, 6), null)
class Gyoku : ShogiUnit("玉", listOf(0, 1, 2, 3, 4, 5, 6, 7), listOf(), null)
