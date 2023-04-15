package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class IslandsTests {
    @Test
    fun allLand_oneIsland() {
        Islands.BitArray(4, 4).let {
            for (x in 0 until it.width) {
                for (y in 0 until it.height) {
                    it[x, y] = true
                }
            }

            assertEquals(1, Islands.countIslands(it))
        }
    }

    @Test
    fun allWater_zeroIslands() {
        Islands.BitArray(4, 4).let {
            assertEquals(0, Islands.countIslands(it))
        }
    }

    @Test
    fun twoSingleCellIslands_twoIslands() {
        Islands.BitArray(4, 4).let {
            it[1, 1] = true
            it[3, 3] = true

            assertEquals(2, Islands.countIslands(it))
        }
    }

    @Test
    fun twoLShapeIslands_twoIslands() {
        Islands.BitArray(4, 4).let {
            it[0, 0] = true
            it[0, 1] = true
            it[1, 0] = true
            it[2, 2] = true
            it[2, 1] = true
            it[1, 2] = true

            assertEquals(2, Islands.countIslands(it))
        }

    }
}
