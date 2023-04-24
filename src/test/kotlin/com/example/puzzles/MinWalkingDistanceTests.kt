package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertContentEquals

class MinWalkingDistanceTests {
    @Test
    fun presentInTheMiddle_correctDistances() {
        val presence = booleanArrayOf(false, false, false, true, false, false)
        assertContentEquals(
            intArrayOf(3, 2, 1, 0, 1, 2),
            MinWalkingDistance.fillMinimumDistances(presence)
        )
    }

    @Test
    fun startsWithPresent_correctDistances() {
        val presence = booleanArrayOf(true, false, false, false, true, false)
        assertContentEquals(
            intArrayOf(0, 1, 2, 1, 0, 1),
            MinWalkingDistance.fillMinimumDistances(presence)
        )
    }

    @Test
    fun notPresent_maximumDistances() {
        val presence = booleanArrayOf(false, false, false, false)
        assertContentEquals(
            intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE),
            MinWalkingDistance.fillMinimumDistances(presence)
        )
    }
}
