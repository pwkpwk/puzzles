package com.example.puzzles

import java.util.Arrays
import kotlin.math.max
import kotlin.math.min

object LongestSubstring {

    fun findLongestRepeatingSubstring(text: String): Match {
        val offsets = Array(text.length) { i -> i }
        var maxLength = 0
        var index = 0;

        // Sort substrings pointed by the offsets in the array, which will bring strings
        // with the same prefix together
        Arrays.sort(offsets) { x, y -> compareSubstrings(text, x, y) }

        // Scan the offsets and find the longest prefix of the adjacent substrings pointed by offsets
        for (i in 0 until text.length - 1) {
            commonPrefixLength(text, offsets[i], offsets[i + 1]).let {
                if (it > maxLength) {
                    maxLength = it
                    index = i
                }
            }
        }

        return if (maxLength > 0)
            Match(offsets[index], offsets[index + 1], maxLength)
        else
            Match(0, 0, 0)
    }

    class Match(x: Int, y: Int, l: Int) {
        val first = min(x, y)
        val second = max(x, y)
        val length = l
    }

    private fun compareSubstrings(text: String, offset1: Int, offset2: Int): Int {
        var x = offset1
        var y = offset2

        while (x < text.length && y < text.length) {
            when {
                text[x] < text[y] -> return -1
                text[x] > text[y] -> return 1
            }
            ++x
            ++y
        }

        return 0
    }

    private fun commonPrefixLength(text: String, offset1: Int, offset2: Int): Int {
        var x = offset1
        var y = offset2
        var length = 0

        while (x < text.length && y < text.length && text[x] == text[y]) {
            ++length
            ++x
            ++y
        }

        return length
    }
}
