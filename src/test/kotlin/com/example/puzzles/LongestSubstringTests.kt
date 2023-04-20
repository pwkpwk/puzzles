package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals

class LongestSubstringTests {
    @Test
    fun emptyText_noMatches() {
        LongestSubstring.findLongestRepeatingSubstring("").let {
            assertEquals(0, it.length)
            assertEquals(0, it.first)
            assertEquals(0, it.second)
        }
    }

    @Test
    fun noMatches_noMatches() {
        LongestSubstring.findLongestRepeatingSubstring("abcdefg").let {
            assertEquals(0, it.length)
            assertEquals(0, it.first)
            assertEquals(0, it.second)
        }
    }

    @Test
    fun matches_findsLongest() {
        LongestSubstring.findLongestRepeatingSubstring("abcdefgefefgcdef").let {
            assertEquals(4, it.length)
            assertEquals(2, it.first)
            assertEquals(12, it.second)
        }
    }
}
