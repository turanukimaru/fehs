package jp.blogspot.turanukimaru.sample

import java.util.*

data class Sein<T>( // T が若干鬱陶しいが他の型と区別する役に立つ
        val enum: T? = null,// Sein から enum を復元するときに使う。enum に戻す予定がないなら不要。
        val category: Int = 0,
        val key: Int = 0,
        val label: String = "",
        val start: Date = Date(), //InstantはAPIレベルが足りなかった…
        val end: Date = Date(),
        val time: Date? = null,//特定時刻. 時刻が指定されたときのみ値が入る
        val header: Sein<T>? = null,//ファサードになる現在のコードを指す.　header の時のみ null
        val prev: Sein<T>? = null//時間的に前のコードを優先度順に並べたもの。最後はnull
) {

    /**
     * 時刻を特定しない存在（≒現在の存在）を返す。
     */
    fun now() = header ?: this

    /**
     * 特定時刻の存在
     */
    fun at(date: Date? = null): Sein<T> =
            if (date == null) header ?: this // date が指定されなかったりnullだったりすると現在のCodeを出すようにすると使いやすい
            else header?.dig(date) ?: dig(date) //rootから掘り進めて対象の時間のオブジェクトを探す

    /**
     * 対象の時間を含む存在を掘る.有ったら時間指定の上でコピー。最後まで存在しなかったら例外
     */
    private fun dig(date: Date): Sein<T> = if (date.after(start) && date.before(end)) copy(time = date) else prev?.dig(date)
            ?: throw RuntimeException()

    /**
     * 現在時刻に即した表記を出力する.年号だけでいいときは label をそのまま出力するので十分
     */
    fun toTimeString(): String = if (time == null) label else "2019年の時は元年にするとか必要に応じて編集"

    /**
     * 同じカテゴリ同じキーなら別の時間でも同じコード。hash()とかも同様に同じように振舞わせる
     */
    override fun equals(other: Any?): Boolean = other is Sein<*> && other.category == this.category && other.key == this.key

    override fun hashCode(): Int = category.hashCode() + key.hashCode()
}
