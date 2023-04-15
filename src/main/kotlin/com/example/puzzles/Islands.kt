package com.example.puzzles

import java.util.LinkedList

object Islands {

    fun countIslands(map: BitArray): Int {
        var islandsNumber = 0

        //
        val explored = BitArray(map.width, map.height)

        for (x in 0 until map.width) {
            for (y in 0 until map.height) {
                if (map[x, y] && !explored[x, y]) {
                    // The marked cell of the Geo map is unexplored, so add a new island to the count
                    // and mark it all as explored with a breadth first search
                    ++islandsNumber
                    markIsland(map, x, y, explored)
                }
            }
        }

        return islandsNumber
    }

    fun markIsland(map: BitArray, x: Int, y: Int, explored: BitArray) {
        val queue = LinkedList<Vertex>()

        queue.add(Vertex(x, y))
        markIsland(queue, map, explored)
    }

    private fun markIsland(queue: LinkedList<Vertex>, map: BitArray, explored: BitArray) {
        while (queue.isNotEmpty()) {
            val root: Vertex = queue.removeFirst()

            explored[root.x, root.y] = true

            if (root.x > 0 && map[root.x - 1, root.y] && !explored[root.x - 1, root.y])
                queue.addLast(Vertex(root.x - 1, root.y))
            if (root.x < map.width - 1 && map[root.x + 1, root.y] && !explored[root.x + 1, root.y])
                queue.addLast(Vertex(root.x + 1, root.y))
            if (root.y > 0 && map[root.x, root.y - 1] && !explored[root.x, root.y - 1])
                queue.addLast(Vertex(root.x, root.y - 1))
            if (root.y < map.height - 1 && map[root.x, root.y + 1] && !explored[root.x, root.y + 1])
                queue.addLast(Vertex(root.x, root.y + 1))
        }
    }

    class Vertex(val x: Int, val y: Int)

    @OptIn(ExperimentalUnsignedTypes::class)
    class BitArray(val width: Int, val height: Int) {
        private val data = UByteArray(width * height / 8 + 1)

        operator fun get(x: Int, y: Int): Boolean {
            val index = (y * width + x)
            val byteIndex = index / 8
            val bitOffset = index % 8
            return (data[byteIndex] and bits[bitOffset]).toInt() != 0
        }

        operator fun set(x: Int, y: Int, value: Boolean) {
            val index = (y * width + x)
            val byteIndex = index / 8
            val bitOffset = index % 8

            if (value) {
                data[byteIndex] = data[byteIndex] or bits[bitOffset]
            } else {
                data[byteIndex] = data[byteIndex] and bits[bitOffset].inv()
            }
        }

        private companion object {
            val bits = UByteArray(8).apply {
                val bit: Int = 1
                for (i in 0..7) {
                    set(i, (bit shl i).toUByte())
                }
            }
        }
    }
}