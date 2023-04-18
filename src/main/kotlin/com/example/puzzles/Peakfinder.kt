package com.example.puzzles

object Peakfinder {

    /**
     * @param elevation Array od elevation with exactly one peak value
     * @return Index of the peak element
     */
    fun findPeak(elevation: Array<Int>): Int {
        var l = -1
        var u = elevation.size

        // Put all descending elements to the right of the middle point
        while (l +1 < u) {
            val m = (l + u) / 2

            if (m == elevation.size - 1 || elevation[m] > elevation[m + 1]) {
                u = m
            } else {
                l = m
            }
        }

        return u
    }

}