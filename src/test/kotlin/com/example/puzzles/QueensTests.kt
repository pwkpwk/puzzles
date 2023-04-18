package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class QueensTests {
    @Test
    fun check8x8Board_92solutions() {
        assertEquals(0, Queens.placeQueens(2).size)
        assertEquals(0, Queens.placeQueens(3).size)
        assertEquals(92, Queens.placeQueens(8).size)
        assertEquals(2680, Queens.placeQueens(11).size)
    }
}
