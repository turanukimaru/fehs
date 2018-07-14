import jp.blogspot.turanukimaru.fehs.ArmedHero
import jp.blogspot.turanukimaru.fehs.StandardBaseHero
import org.junit.Test

class ScoreTest {


    @Test
    fun specialPreventTestA() {
        val unitA = ArmedHero(StandardBaseHero.get("アイク")!!)
        val score = unitA.score
        val totalSp = unitA.totalSp
        println("score: $score")
        println("totalSp: $totalSp")
        throw Exception()
    }}