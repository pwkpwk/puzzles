package com.example.puzzles

import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RendevousHashTests {
    @Test
    fun returnsDifferentResources() {
        val s1 = RendevousHash.selectResources("elephant", listOf("server1", "server2", "server3"), 2)
        val s2 = RendevousHash.selectResources("elephant", listOf("server2", "server3", "server1"), 2)

        assertEquals(2, s1.size)
        assertEquals(2, s2.size)
        assertEquals(s1[0], s2[0])
        assertEquals(s1[1], s2[1])
    }

    @Test
    fun removeResource_select_returnsNewResources() {
        val set = listOf("server1", "server2", "server3")
        RendevousHash.removeResourceAndRelocate("penguin", set, set[0], 2).let {
            assertEquals(2, it.size)
            assertContains(it, "server2")
            assertContains(it, "server3")
        }
    }
}
