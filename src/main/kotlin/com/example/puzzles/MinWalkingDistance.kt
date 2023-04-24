package com.example.puzzles

import kotlin.math.min

object MinWalkingDistance {

    // if presence[i] is true -> distance[i] = 0
    // if presence[i] is false -> distance[i] = min(|i - k|) where presence[k] is true
    fun fillMinimumDistances(presence: BooleanArray): IntArray =
        IntArray(presence.size).also { distances ->
            var lastPresent = -1

            for (i in 0 until presence.size) {
                if (presence[i]) {
                    distances[i] = 0
                    lastPresent = i
                } else {
                    if (lastPresent >= 0) {
                        distances[i] = i - lastPresent
                    } else {
                        distances[i] = Int.MAX_VALUE
                    }
                }
            }

            lastPresent = -1

            for (i in presence.size - 1 downTo 0) {
                if (presence[i]) {
                    distances[i] = 0
                    lastPresent = i
                } else {
                    if (lastPresent >= 0) {
                        distances[i] = min(distances[i], lastPresent - i)
                    }
                }
            }
        }
}
