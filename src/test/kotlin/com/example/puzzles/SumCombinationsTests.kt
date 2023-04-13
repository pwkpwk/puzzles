package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class SumCombinationsTests {
    @Test
    fun basicTest() {
        SumCombinations().collectCombinations(5, arrayOf(3, 2, 1)).let {
            assertEquals(5, it.size)
        }
    }
}
