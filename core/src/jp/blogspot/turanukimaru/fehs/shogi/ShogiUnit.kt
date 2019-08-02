package jp.blogspot.turanukimaru.fehs.shogi


/**
 * 要らない気もしたけどあっても困らないな。名前とかこっちにあったほうが区別しやすいし
 */
open class ShogiUnit (val name:String, val orientations:List<Int>, val steps : Int, val promotion : ShogiUnit?){//あれ竜王と竜馬がうまく処理できないなこれ…まぁ継承でなんとかなるか
}
class Fu : ShogiUnit("歩", listOf(0),1,null)
class Kyosya : ShogiUnit("香車", listOf(0),9,null)
class Keima : ShogiUnit("桂馬", listOf(9,23),1,null)
class Gin : ShogiUnit("銀", listOf(0,1,3,5,7),1,null)
class Kin : ShogiUnit("金", listOf(0,1,2,4,6,7),1,null)
class Kaku : ShogiUnit("角", listOf(1,3,5,7),9,null)
class Hisya : ShogiUnit("飛車", listOf(0,2,4,6),9,null)
class Gyoku : ShogiUnit("玉", listOf(0,1,2,3,4,5,6,7),1,null)
