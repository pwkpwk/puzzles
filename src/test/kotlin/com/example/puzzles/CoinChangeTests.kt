package com.example.puzzles

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CoinChangeTests {
    @Test
    fun plop() {
        CoinChange.giveChange(2, arrayOf(
            1, 5, 10, 25, 50, 100)).let {
            assertEquals(2, it.totalCoins)
            assertEquals(2, it.breakdown[0])
        }
        CoinChange.giveChange(5, arrayOf(1, 5, 10, 25, 50, 100)).let {
            assertEquals(1, it.totalCoins)
            assertEquals(0, it.breakdown[0])
            assertEquals(1, it.breakdown[1])
        }
        CoinChange.giveChange(100, arrayOf(1, 5, 10, 25, 50, 100)).let {
            assertEquals(1, it.totalCoins)
            assertEquals(0, it.breakdown[0])
            assertEquals(0, it.breakdown[1])
            assertEquals(0, it.breakdown[2])
            assertEquals(0, it.breakdown[3])
            assertEquals(0, it.breakdown[4])
            assertEquals(1, it.breakdown[5])
        }
        CoinChange.giveChange(125, arrayOf(1, 5, 10, 25, 50, 100)).let {
            assertEquals(2, it.totalCoins)
            assertEquals(0, it.breakdown[0])
            assertEquals(0, it.breakdown[1])
            assertEquals(0, it.breakdown[2])
            assertEquals(1, it.breakdown[3])
            assertEquals(0, it.breakdown[4])
            assertEquals(1, it.breakdown[5])
        }
    }
}
