package com.example.puzzles

object Peakfinder {

    /**
     * @param elevation Array od elevation with exactly one peak value
     * @return Index of the peak element
     */
    fun findPeak(elevation: Array<Int>): Int {
        // Box the range with excluded upper and lowed bounds. We'll never examine
        // values at the bounds, and the midpoint will always be between the bounds.
        var l = -1
        var u = elevation.size

        // Put all descending elements to the right of the middle point,
        // so when the loop will have reduced the search range to one element,
        // the peak will be at the upper bound.
        while (l +1 < u) {
            // The midpoint is always between the bounds and is not equal to either,
            // so we'll move the appropriate bound to the midpoint.
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