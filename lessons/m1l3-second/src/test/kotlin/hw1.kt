import junit.framework.TestCase.assertEquals
import kotlin.test.Test

/*
* Реализовать функцию, которая преобразует список словарей строк в ФИО
* Функцию сделать с использованием разных функций для разного числа составляющих имени
* Итого, должно получиться 4 функции
*
* Для успешного решения задания, требуется раскомментировать тест, тест должен выполняться успешно
* */
class HomeWork1Test {

    @Test
    fun mapListToNamesTest() {
        val input = listOf(
            mapOf(
                "first" to "Иван",
                "middle" to "Васильевич",
                "last" to "Рюрикович",
            ),
            mapOf(
                "first" to "Петька",
            ),
            mapOf(
                "first" to "Сергей",
                "last" to "Королев",
            ),
        )
        val expected = listOf(
            "Рюрикович Иван Васильевич",
            "Петька",
            "Королев Сергей",
        )
        val res = mapListToNames(input)
        assertEquals(expected, res)


    }

}
    private fun mapListToNames(input: List<Map<String, String>>): List<String> {
        var list = mutableListOf<String>()
        input.forEach{
                   list.add(concat3(it.get("last") ?: "", it.get("first") ?: "", it.get("middle") ?: "").trim())
        }
        return list
    }

private fun concat3(x: String, y: String, z: String): String = "$x $y $z"
private fun concat2(x: String?, y: String?): String = "$x $y"
private fun concat1(x: String?): String = "$x"
