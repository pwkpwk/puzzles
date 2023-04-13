package com.example.puzzles

object RollingMaximum {

    fun mapSlidingMaximums(data: Array<Int>, windowSize: Int): List<Maximum> = mutableListOf<Maximum>().apply {
        var maximum = data[0]
        var tailIndex = 0

        // The first element is always the first maximum
        add(Maximum(0, maximum))

        for (frontIndex in 1 until data.size) {
            if (data[frontIndex] >= maximum) {
                if (data[frontIndex] > maximum) {
                    // Found the new maximum
                    maximum = data[frontIndex]
                }
                tailIndex = frontIndex
            } else if (frontIndex - tailIndex >= windowSize) {
                // The tail needs to move and the tail points at the current window maximum
                maximum = data[++tailIndex]
                for (i in tailIndex + 1..frontIndex) {
                    if (data[i] > maximum) {
                        tailIndex = i
                        maximum = data[i]
                    }
                }
            }

            add(Maximum(frontIndex, maximum))
        }
    }

    class Maximum(val index: Int, val value: Int)
}
