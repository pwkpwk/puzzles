package com.example.puzzles

import java.util.Arrays
import kotlin.math.max
import kotlin.math.min

object LongestSubstring {

    private val noMatch by lazy { Match(0, 0, 0) }

    fun findLongestRepeatingSubstring(text: String): Match =
        Array(text.length) { i -> i }.let {
            var maxLength = 0
            var index = 0

            // Sort substrings pointed by the offsets in the array, which will bring strings
            // with the same prefix together
            Arrays.sort(it, OffsetComparator(text))

            // Scan the offsets and find the longest prefix of the adjacent substrings pointed by offsets
            for (i in 0 until text.length - 1) {
                commonPrefixLength(text, it[i], it[i + 1]).let { length ->
                    if (length > maxLength) {
                        maxLength = length
                        index = i
                    }
                }
            }

            if (maxLength > 0)
                Match(it[index], it[index + 1], maxLength)
            else
                noMatch
        }

    class Match(x: Int, y: Int, val length: Int) {
        val first = min(x, y)
        val second = max(x, y)
    }

    private class OffsetComparator(private val text: String) : Comparator<Int> {

        override fun compare(offset1: Int, offset2: Int): Int {
            var x = offset1
            var y = offset2
            var bound = max(x, y)

            while (bound < text.length) {
                when {
                    text[x] < text[y] -> return -1
                    text[x] > text[y] -> return 1
                    else -> {
                        ++x
                        ++y
                        ++bound
                    }
                }
            }

            return 0
        }
    }

    private fun commonPrefixLength(text: String, offset1: Int, offset2: Int): Int {
        var x = offset1
        var y = offset2
        var bound = max(x, y)
        var length = 0

        while (bound < text.length && text[x] == text[y]) {
            ++length
            ++x
            ++y
            ++bound
        }

        return length
    }
}
