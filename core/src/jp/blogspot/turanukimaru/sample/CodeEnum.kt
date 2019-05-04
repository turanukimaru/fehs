package jp.blogspot.turanukimaru.sample

import java.util.*

enum class CodeEnum(parent: String, code: String) {
    //Enum自体はをカテゴリとして扱えるようにするとはかどる
    紀年法("0", "1"),
    年号("0", "2")
    ;

    fun code(code: String): Code {
        //対応するコードをキャッシュから取り出して返す
        return Code()
    }

    fun codeList(time: Date?): List<Code> {
        //対応するコードをキャッシュから取り出して返す
        return listOf(Code(key = 5), Code(key = 6)).map { it.at(time) }
    }

    fun visibleList(time: Date?): List<Code> {
        //カラムに「表示」フラグを持たせると便利。
        fun Code.visible() = true
        //対応するコードをフィルタして返す
        return codeList(time).filter { it.visible() }
    }
}

// 末端はCodeに変換できればそれで充分。
enum class 紀年法(parent: String, code: String) {
    //Enum自体はをカテゴリとして扱えるようにするとはかどる
    西暦("1", "1"),
    和暦("1", "2")
    ;
    fun code(): Code {
        //対応するコードをキャッシュから取り出して返す
        return Code()
    }
}
enum class 和暦(parent: String, code: String) {
    //Enum自体はをカテゴリとして扱えるようにするとはかどる
    平成("2", "1"),
    令和("2", "2")
    ;
    fun code(): Code {
        //対応するコードをキャッシュから取り出して返す
        return Code()
    }
}

class 歴史的人物 {
    var 紀年法コード: String = ""
    var 誕生年: Date = Date()

    fun 誕生年(): Code {
        return CodeEnum.紀年法.code(紀年法コード).at(誕生年)
    }
}

