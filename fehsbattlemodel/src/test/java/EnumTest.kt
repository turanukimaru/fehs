import org.junit.Test

/**
 * Created by turanukimaru on 2017/11/02.
 */
class EnumTest {
    @Test
    fun mainTest() {
        main()
    }
}

interface A {
    fun hoge()
    fun hage() {
        println(getInt())
    }

    fun hugo() {
        hage()
    }

    fun hageInt(i: Int) {
        println("base $i")
    }

    fun callHageInt() {
        hageInt(0)
    }

    fun getInt(): Int {
        return 0
    }

    val prop: String
    fun oops(i: Int) {

    }

    fun oopsI(i: Int = getInt()) {
        println("oops $i")
    }
}

class AImpl() : A {
    override val prop: String
        get() = "Delegation is pretty!"

    override fun hoge() {
        println("Hello delegation!")
    }

    override fun hageInt(i: Int) {
        println(i)
    }
}

class B(a: A) : A by a
class C(val a: A) : A by a {
    override fun getInt(): Int {
        return 3
    }

    override fun hoge() {
        a.hoge()
        println("Override method!")
    }

    override fun hugo() {
        //a.hage()が呼ばれる…まではいいのだがそこからのgetInt()がaのgetInt()だわ
        hage()
    }

    override fun callHageInt() {
        hageInt(10)
    }

}

class D(var i: Int) : A {
    override val prop: String
        get() = "Delegation is pretty!"

    override fun getInt(): Int {
        return i
    }

    override fun hoge() {
        println("Override method!")
    }

    override fun oops(i: Int) {
        this.i = i
    }
}

enum class Outs(val a: A) : A by a {
    X(D(5))
}

fun main() {
    val a = AImpl()
    val b = B(a)
    b.hoge() // Hello delegation!
    println(b.prop) // Delegation is pretty!

    val c = C(a)
    c.hoge() // Hello delegation!(line break)Override method!
    c.hugo()
    c.callHageInt()
    val d = D(1)
    d.hugo()
    d.i = 10
    d.hugo()
    println(Outs.X.getInt())
    Outs.X.a.oops(10)
    //こんなのenumじゃないわ！ただのシングルトンよ！でもJavaもそうか
    println(Outs.X.getInt())

    println(c.oopsI())

}