package jp.blogspot.turanukimaru.sample

import java.util.*

//Enumがたくさんできるので適当にグループ化する
class 時間的Enum群 {

    enum class 紀年法(override val category: Int, override val key: Int) : CodeEnum<紀年法> {
        西暦(1, 1),
        和暦(1, 2)
        ;

        companion object : CodeCategory<紀年法>(1) {
            fun item(key: Int) = values().find { it.key == key }
        }
    }

    enum class 和暦(override val category: Int, override val key: Int) : CodeEnum<和暦> {
        平成(2, 1),
        令和(2, 2)
        ;

        companion object : CodeCategory<和暦>(2) {
            fun item(key: Int) = values().find { it.key == key }
        }
    }

}

interface CodeEnum<T> {
    val category get() = 0
    val key get() = 0

    //対応するコードをキャッシュから取り出して返す
    val sein get() = Sein<T>()
}

open class CodeCategory<T>(val category: Int) {
    fun sein(key: Int): Sein<T> {
        //対応するコードをキャッシュから取り出して返す
        return Sein()
    }

    val list
        get(): List<Sein<T>> {
            //対応するコードをキャッシュから取り出して返す
            return listOf(Sein(key = 5), Sein(key = 6))
        }
}


class 歴史的人物 {
    var 紀年法コード: Int = 1
    var 誕生年: Date = Date()

    fun 誕生年(): Sein<時間的Enum群.紀年法> {
        return 時間的Enum群.紀年法.sein(紀年法コード).at(誕生年)
    }

    fun 西暦誕生年(): Sein<時間的Enum群.紀年法> {
        return 時間的Enum群.紀年法.西暦.sein.at(誕生年)
    }
}
