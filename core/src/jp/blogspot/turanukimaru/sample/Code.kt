package jp.blogspot.turanukimaru.sample

import java.util.*

data class Code(
        val key: Int = 0,
        val value: String = "",
        val start: Date = Date(),
        val end: Date = Date(),
        val time: Date? = null,//特定時刻. // 時刻が指定されたときのみ値が入る
        val root: Code? = null,//ファサードになる現在のコードを指す.　root の時のみ null
        val prev: Code? = null//時間的に前のコードを優先度順に並べたもの。最後はnull
) {

    /**
     * 時刻を特定しない存在（≒現在の存在）を返す。
     */
    fun now() = root ?: this

    /**
     * 特定時刻の存在.date が指定されなかったりnullだったりすると現在のCodeを出すようにすると使いやすい
     */
    fun at(date: Date? = null): Code = if (date == null) root ?: this else root?.dig(date)
            ?: copy(time = date)// 見つからないときに null を返す設計もありえる

    /**
     * 対象の時間を含む存在を掘る
     */
    private fun dig(date: Date): Code? = if (date.after(start) && date.before(end)) copy(time = date) else prev?.dig(date)

    /**
     * 現在時刻に即した表記を出力する。コード体系によるので継承するか後付けしたほうがよい
     */
    open fun toTimeString(): String = if (time == null) value else "$value : $time - start とか必要に応じて編集"

    /**
     * 年号だけでいいときは value をそのまま出力するので十分だったりする
     */
    override fun toString(): String = value

    /**
     * 同じキーなら別の時間でも同じコード。hash()とかも同様に同じように振舞わせる
     */
    override fun equals(other: Any?): Boolean = other is Code && other.key == this.key
}
