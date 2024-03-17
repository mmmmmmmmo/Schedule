package com.moon.widget

import org.junit.Test

import org.junit.Assert.*
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val test:Double = 100000000.0
        val deci =  DecimalFormat("#.##").apply { roundingMode = RoundingMode.DOWN }.format(test)
        println(deci)
        println(String.format("%f",test))
        println(test.toString())
        assertEquals(4, 2 + 2)
    }
}
