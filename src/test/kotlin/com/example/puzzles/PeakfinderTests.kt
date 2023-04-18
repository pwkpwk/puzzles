package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class PeakfinderTests {
    @Test
    fun plop() {
        assertEquals(3, Peakfinder.findPeak(arrayOf(1, 2, 3, 4, 3, 2, 1)))
        assertEquals(1, Peakfinder.findPeak(arrayOf(1, 4, 3, 2, 1)))
        assertEquals(0, Peakfinder.findPeak(arrayOf(4, 3, 2, 1)))
        assertEquals(4, Peakfinder.findPeak(arrayOf(1, 2, 3, 4, 5)))
    }
}
