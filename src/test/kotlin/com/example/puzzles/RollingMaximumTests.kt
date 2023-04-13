package com.example.puzzles

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RollingMaximumTests {
    @Test
    fun everIncreasingSequence() {
        RollingMaximum.mapSlidingMaximums(arrayOf(1, 2, 3), 2).let {
            val expected = arrayOf(1, 2, 3)
            assertEquals(expected.size, it.size)
            for (i in 0 until expected.size) {
                assertEquals(expected[i], it[i].value)
                assertEquals(i, it[i].index)
            }
        }
    }

    @Test
    fun everDecreasingSequence() {
        RollingMaximum.mapSlidingMaximums(arrayOf(3, 2, 1), 2).let {
            val expected = arrayOf(3, 3, 2)
            assertEquals(expected.size, it.size)
            for (i in 0 until expected.size) {
                assertEquals(expected[i], it[i].value)
                assertEquals(i, it[i].index)
            }
        }
    }

    @Test
    fun randomSequence() {
        RollingMaximum.mapSlidingMaximums(arrayOf(0, 5, 2, 3, 7, 7, 5, 4, 3), 3).let {
            val expected = arrayOf(0, 5, 5, 5, 7, 7, 7, 7, 5)
            assertEquals(expected.size, it.size)
            for (i in 0 until expected.size) {
                assertEquals(expected[i], it[i].value)
                assertEquals(i, it[i].index)
            }
        }
    }

    @Test
    fun sampleSequence() {
        RollingMaximum.mapSlidingMaximums(arrayOf(1, 2, 3, 2, 1), 2).let {
            val expected = arrayOf(1, 2, 3, 3, 2)
            assertEquals(expected.size, it.size)
            for (i in 0 until expected.size) {
                assertEquals(expected[i], it[i].value)
                assertEquals(i, it[i].index)
            }
        }
    }
}
