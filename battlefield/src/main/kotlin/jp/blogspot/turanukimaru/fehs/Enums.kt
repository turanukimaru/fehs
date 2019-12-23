package jp.blogspot.turanukimaru.fehs

interface GameInterface {
    fun actionDone()
    fun turnEnd()
}

//realm が typealias 読めねえ。。。
//typealias BattleField= PhysicalBoard<MyPiece, Ground>
//open class BattleField(horizontalLines:Int, verticalLines:Int, id:Int = 0): PhysicalBoard<MyPiece, Ground>(horizontalLines,verticalLines, id)

//これは無いな・・・BoardListener でいいのか
interface GameListener {

}