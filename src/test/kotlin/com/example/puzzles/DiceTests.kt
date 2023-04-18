package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class DiceTests {
    @Test
    fun validRoll_countDiceCombinations_correctResults() {
        assertEquals(6, Dice.countDiceCombinations(2, 6, 7));
        assertEquals(1, Dice.countDiceCombinations(3, 6, 18));
        assertEquals(1, Dice.countDiceCombinations(2, 6, 2));
    }

    @Test
    fun invalidRoll_countDiceCombinations_returnsZero() {
        assertEquals(0, Dice.countDiceCombinations(2, 6, 20));
        assertEquals(0, Dice.countDiceCombinations(2, 6, 1));
    }
}
