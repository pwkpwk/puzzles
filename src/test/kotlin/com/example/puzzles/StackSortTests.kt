package com.example.puzzles

import java.util.Stack
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StackSortTests {

    @Test
    fun sortStack_correctSortedOrder() {
        val arrayA = arrayOf(1, 2)
        val arrayB = arrayOf(3, 5, 4)
        val arrayC = arrayOf(6, 7)
        val a = Stack<Int>()
        val b = Stack<Int>()
        val c = Stack<Int>()

        StackSort.pushArrayOnStack(arrayA, a)
        StackSort.pushArrayOnStack(arrayB, b)
        StackSort.pushArrayOnStack(arrayC, c)

        val stackCommands = StackSort.sortStacks(a, b, c)
        var t = a.pop()
        while (!a.isEmpty()) {
            val tt = a.pop()
            assertTrue(t < tt)
            t = tt
        }

        val arrayCommands = StackSort.moveBoxes(arrayA, arrayB, arrayC)

        assertEquals(stackCommands.size, arrayCommands.size)
    }

    @Test
    fun shortStacks_replayCommands_sortedStack() {
        StackSort.moveBoxes(arrayOf(1), arrayOf(2), arrayOf(3)).let { commands ->
            val a = Stack<Int>().apply { push(1) }
            val b = Stack<Int>().apply { push(2) }
            val c = Stack<Int>().apply { push(3) }
            var src: Stack<Int> = a
            var dst: Stack<Int> = b

            for (command in commands) {
                command.split(' ').let {
                    when (it[0]) {
                        "a" -> src = a;
                        "b" -> src = b;
                        "c" -> src = c;
                    }
                    when (it[1]) {
                        "a" -> dst = a;
                        "b" -> dst = b;
                        "c" -> dst = c;
                    }
                    dst.push(src.pop())
                }
            }

            assertEquals(3, a.size)
            assertEquals(1, a.pop())
            assertEquals(2, a.pop())
            assertEquals(3, a.pop())
        }
    }
}
