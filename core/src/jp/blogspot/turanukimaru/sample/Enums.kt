package jp.blogspot.turanukimaru.sample

//雑多なクラスやEnum
//消すべきだけど消すのもったいないな…
class 駒

class 枡
abstract class 行動(open val 選択駒: 駒?, open val 戦闘駒: 駒?, open val 移動元: 枡?, open val 移動先: 枡?) {
    abstract fun 駒タップ(対象駒: 駒, 対象枡: 枡): 行動
    abstract fun 盤タップ(対象駒: 駒, 対象枡: 枡): 行動

    //他の関数はoverride禁止
    fun 他共通関数() {}
}

class 未選択() : 行動(null, null, null, null) {
    override fun 駒タップ(対象駒: 駒, 対象枡: 枡) = 選択(対象駒, 対象枡, 対象枡)
    override fun 盤タップ(対象駒: 駒, 対象枡: 枡) = 未選択()
}

class 選択(override val 選択駒: 駒, override val 移動元: 枡, override val 移動先: 枡) : 行動(選択駒, null, 移動元, 移動先) {
    override fun 駒タップ(対象駒: 駒, 対象枡: 枡) = if (選択駒 == 対象駒) 未選択() else 戦闘準備(選択駒, 対象駒, 移動元, 移動先)
    override fun 盤タップ(対象駒: 駒, 対象枡: 枡) = 選択(対象駒, 移動元, 対象枡)
}

class 戦闘準備(override val 選択駒: 駒, override val 戦闘駒: 駒, override val 移動元: 枡, override val 移動先: 枡) : 行動(選択駒, 戦闘駒, 移動元, 移動先) {
    override fun 駒タップ(対象駒: 駒, 対象枡: 枡) = 未選択()
    override fun 盤タップ(対象駒: 駒, 対象枡: 枡) = 選択(対象駒, 移動元, 対象枡)
}

open class TestX(private var x: Int, private var y: Int) {
    protected fun setXandY(x: Int, y: Int) {
        this.x = x
        this.y = y

    }

    fun setY(x: Int, y: Int) {
        val yy = TestY(0, 0)
//        yy.setXandY(0,0)
    }

    open class TestY(private var x: Int, private var y: Int) {
        private fun setXandY(x: Int, y: Int) {
            this.x = x
            this.y = y
        }

        fun setX(x: Int, y: Int) {
            val xx = TestX(0, 0)
            xx.setXandY(0, 0)
        }
    }

    open class TestZ(private var x: Int, private var y: Int) {
        private fun setXandY(x: Int, y: Int) {
            this.x = x
            this.y = y
        }

        fun setX(x: Int, y: Int) {
            val xx = TestY(0, 0)
//            xx.setXandY(0,0)
        }
    }
}

class TextXX {
    val map = mapOf<TestX, Int>()
    fun x() {
        fun TestX.aaa() {
            map[this]
        }

        val x = TestX(0, 0)
//        x.setXandY(0,0)
    }
}
