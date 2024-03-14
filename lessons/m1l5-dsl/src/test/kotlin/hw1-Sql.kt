@file:Suppress("unused")

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

// Реализуйте dsl для составления sql запроса, чтобы все тесты стали зелеными.
class Hw1Sql {
    private fun checkSQL(expected: String, sql: SqlSelectBuilder) {
        assertEquals(expected, sql.build())
    }

    @Test
    fun `simple select all from table`() {
        val expected = "select * from table"

        val real = query {
            from("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `check that select can't be used without table`() {
        assertFailsWith<Exception> {
            query {
                select("col_a")
            }.build()
        }
    }

    @Test
    fun `select certain columns from table`() {
        val expected = "select col_a, col_b from table"

        val real = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `select certain columns from table 1`() {
        val expected = "select col_a, col_b from table"

        val real = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, real)
    }

    /**
     * __eq__ is "equals" function. Must be one of char:
     *  - for strings - "="
     *  - for numbers - "="
     *  - for null - "is"
     */
    @Test
    fun `select with complex where condition with one condition`() {
        val expected = "select * from table where col_a = 'id'"

        val real = query {
            from("table")
            where {
                "col_a" eq "id"
             }
        }

        checkSQL(expected, real)
    }

        /**
     * __nonEq__ is "non equals" function. Must be one of chars:
     *  - for strings - "!="
     *  - for numbers - "!="
     *  - for null - "!is"
     */
    @Test
    fun `select with complex where condition with two conditions`() {
        val expected = "select * from table where col_a != 0"

        val real = query {
            from("table")
            where {
                "col_a" nonEq 0
            }
        }

        checkSQL(expected, real)
    }

    @Test
    fun `when 'or' conditions are specified then they are respected`() {
        val expected = "select * from table where (col_a = 4 or col_b !is null)"

        val real = query {
            from("table")
            where {
                or {
                    "col_a" eq 4
                    "col_b" nonEq null
                }
            }
        }

        checkSQL(expected, real)
    }
}


class SqlSelectBuilder {

    private var select = "select *"
    private var from = ""
    private var where = ""
    private var or = "or "



    fun select(vararg strings: String) {
        if (strings.isNotEmpty() && strings.size >= 2) {
            select =  "select ${strings.joinToString(separator = ", ")}"
        }
    }

    fun from(table: String) {
        val ctx = FromContext()
        ctx.from = table
        from = ctx.from
    }

    fun where(block: String.() -> Unit){
        String().apply(block)
        where = where.replace(" #", "")
        where = " where $where"
        where.trim()
    }
    fun or(block: String.() -> Unit){
        String().apply(block)
        if (where.contains("#")) {
            val lastIndexOf = where.lastIndexOf("#")
            val substring = where.substring(0, lastIndexOf)
            val replace = substring.replace("#", or).trim()
            where = "($replace)"
        } else{
            where = "$where"
        }
    }

    infix fun String.eq(b: Any?): String {

        if(b is String)
             where += "$this = '$b' #"
        if(b is Number)
             where += "$this = $b #"
        if (b == null)
            where += "$this is $b #"
        return where
    }
    infix fun String.nonEq(b: Any?): String {
        if(b is String || b is Number)
            where += "$this != $b #"
        if (b == null)
            where += "$this !is $b #"
        return where
    }

    fun build(): String {
        return "${select} from $from$where"
    }


}

fun query(block: SqlSelectBuilder.() -> Unit) = SqlSelectBuilder().apply(block)
