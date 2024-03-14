fun main() {
    var somesum = SomeSum ()

    println(somesum.add(20))
}

interface Sum {
    val result: Long

}

class SomeSum : Sum {
    override var result: Long = 0L

    fun add(summand: Long): Long {
        result += summand
        return result
    }




}