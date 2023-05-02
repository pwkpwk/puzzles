package com.example.puzzles

import java.util.*

object StackSort {
    
    fun moveBoxes(a: Array<Int>, b: Array<Int>, c: Array<Int>): Array<String> {
        val stackA = Stack<Int>()
        val stackB = Stack<Int>()
        val stackC = Stack<Int>()

        pushArrayOnStack(a, stackA)
        pushArrayOnStack(b, stackB)
        pushArrayOnStack(c, stackC)

        return sortStacks(stackA, stackB, stackC).toTypedArray()
    }

    fun pushArrayOnStack(array: Array<Int>, stack: Stack<Int>) {
        for (n in array) {
            stack.push(n)
        }
    }

    fun sortStacks(a: Stack<Int>, b: Stack<Int>, c: Stack<Int>): List<String> {
        val commands = mutableListOf<String>()

        // 1. Move everything from a and c to b
        while (!a.isEmpty()) {
            b.push(a.pop())
            commands.add("a b")
        }

        while (!c.isEmpty()) {
            b.push(c.pop())
            commands.add("c b")
        }

        a.push(b.pop())
        commands.add("b a")

        // Stacks are:  a - the final sorted stack
        //              b - the temporary stack
        //              c - the temporary variable with no more than one element
        while (!b.isEmpty()) {
            // Transfer the top of b to the variable
            c.push(b.pop())
            commands.add("b c")

            while (!a.isEmpty() && c.peek() > a.peek()) {
                b.push(a.pop())
                commands.add("a b")
            }
            a.push(c.pop())
            commands.add("c a")
        }

        return commands
    }
}