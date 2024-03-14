import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertNotEquals
import java.util.*
import kotlin.test.Test

//@Ignore
class Hw1KtTest {
    // task 1 - make a Rectangle class that will have width and height
    // as well as the area calculation method - area()
    // the test below should pass - uncomment the code in it
    @Test
    fun rectangleArea() {
        val r = Rectangle(10, 20)
        assertEquals(200, r.area())
        assertEquals(10, r.width)
        assertEquals(20, r.height)
    }

    class Rectangle(override val width: Int, override var height: Int): Figure(width, height) {
        override fun area(): Int = width * height
        override fun toString(): String {
            return "Rectangle(${width}x$height)"
        }

        override fun equals(other: Any?): Boolean {
            return other is Rectangle
                    && this.height == other.height
                    && this.width == other.width
        }

        override fun hashCode(): Int {
            return Objects.hash(this.height * 31) + Objects.hash(this.width * 31)
        }
    }

    // task 2 - make the Rectangle.toString() method
    // the test below should pass - uncomment the code in it
    @Test
    fun rectangleToString() {
        val r = Rectangle(10, 20)
        assertEquals("Rectangle(10x20)", r.toString())
    }

    // task 3 - make Rectangle.equals() and Rectangle.hashCode() methods
    // the test below should pass - uncomment the code in it
    @Test
    fun rectangleEquals() {
        val a = Rectangle(10, 20)
        val b = Rectangle(10, 20)
        val c = Rectangle(20, 10)
        assertEquals(a, b)
        assertEquals(a.hashCode(), b.hashCode())
        assertFalse (a === b)
        assertNotEquals(a, c)
    }

    // task 4 - make the Square class
    // the test below should pass - uncomment the code in it
    @Test
    fun squareEquals() {
        val a = Square(10)
        val b = Square(10)
        val c = Square(20)
        assertEquals(a, b)
        assertEquals(a.hashCode(), b.hashCode())
        assertFalse (a === b)
        assertNotEquals(a, c)
        println(a)
    }

    class Square(override val width: Int, height: Int = width): Figure(width, height) {
        override fun equals(other: Any?): Boolean {
            return other is Square
                    && this.width == other.width
        }

        override fun hashCode(): Int {
            return Objects.hash(this.width * 31)
        }

        override fun toString(): String {
            return "Square($width)"
        }
    }

    // task 5 - make the Figure interface with the area() method, inherit Rectangle and Square from it
    // the test below should pass - uncomment the code in it
    @Test
    fun figureArea() {
        var f : Figure = Rectangle(10, 20)
        assertEquals(f.area(), 200)

        f = Square(10)
        assertEquals(f.area(), 100)
    }

    open class Figure(
        open val width: Int,
        open var height: Int = 0){


        open fun area(): Int{
            return width * height
        }


    }

    // task 6 - make the diffArea(a, b) method
    // the test below should pass - uncomment the code in it
    @Test
    fun diffArea() {
        val a = Rectangle(10, 20)
        val b = Square(10)
        assertEquals(diffArea(a, b), 100)
    }

    private fun diffArea(area: Rectangle, otherArea: Square): Int {
        val x: Int
        val a = area.area()
        val b = otherArea.area()

        if (a > b )
            x = a - b
        else x = b - a
        return x

    }

}
