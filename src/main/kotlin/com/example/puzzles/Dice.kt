package com.example.puzzles

import kotlin.math.min

object Dice {

    fun countDiceCombinations(dice: Int, sides: Int, score: Int): Int {
        if (score < dice || score > dice * sides) {
            return 0
        }

        // The first die - values at the index 1..sides are set to 1 because there is
        // exactly 1 roll of the first die can get that score - 1 roll to get 1, 1 roll ro get 2, etc.
        var lastDie = Array(score + 1) { i -> if (i in 1..sides) 1 else 0 }

        for (die in 1 until dice) {
            lastDie = Array(score + 1) { 0 }.also {
                // Start at the score equals to the total number of dice so far, because
                // the minimum roll of N dice is N, and go up to the current number of dice multiplied
                // by the number of sides (or the requested score, whichever is less)
                // because the total score of N dice cannot be higher than the product of sides and dice
                for (x in die + 1..min(score, (die + 1) * sides)) {
                    // x is the score for that we are calculating the number of rolled combinations;
                    // we can make the score by adding one roll of one die to some lower score
                    for (s in 1..sides) {
                        if (s >= x) {
                            break
                        }
                        it[x] += lastDie[x - s]
                    }
                }
            }
        }

        return lastDie[score]
    }
}
