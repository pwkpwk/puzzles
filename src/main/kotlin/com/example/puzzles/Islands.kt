package com.example.puzzles

import java.util.LinkedList

object Islands {

    fun countIslands(map: Geo): Int {
        var islandsNumber = 0
        val explored = BooleanArray(map.width * map.height)

        for (x in 0 until map.width) {
            for (y in 0 until map.height) {
                // Find an unexplored island and mark it all as explored
                if (map[x, y] && !explored[y * map.width + x]) {
                    ++islandsNumber
                    markIsland(map, x, y, explored)
                }
            }
        }

        return islandsNumber
    }

    fun markIsland(map: Geo, x: Int, y: Int, explored: BooleanArray) {
        val queue = LinkedList<Vertex>()

        queue.add(Vertex(x, y))
        markIsland(queue, map, explored)
    }

    private fun markIsland(queue: LinkedList<Vertex>, map: Geo, explored: BooleanArray) {
        while (queue.isNotEmpty()) {
            val root: Vertex = queue.removeFirst()

            explored[root.y * map.width + root.x] = true

            if (root.x > 0 && map[root.x - 1, root.y] && !explored[map.width * root.y + root.x - 1])
                queue.addLast(Vertex(root.x - 1, root.y))
            if (root.x < map.width - 1 && map[root.x + 1, root.y] && !explored[map.width * root.y + root.x + 1])
                queue.addLast(Vertex(root.x + 1, root.y))
            if (root.y > 0 && map[root.x, root.y - 1] && !explored[map.width * (root.y - 1) + root.x])
                queue.addLast(Vertex(root.x, root.y - 1))
            if (root.y < map.height - 1 && map[root.x, root.y + 1] && !explored[map.width * (root.y + 1) + root.x])
                queue.addLast(Vertex(root.x, root.y + 1))
        }
    }

    class Vertex(val x: Int, val y: Int)

    class Geo(val width: Int, val height: Int) {
        private val data = BooleanArray(width * height)

        operator fun get(x: Int, y: Int): Boolean = data[unfoldIndex(x, y)]

        operator fun set(x: Int, y: Int, value: Boolean) {
            data[unfoldIndex(x, y)] = value
        }

        private fun unfoldIndex(x: Int, y: Int): Int = y * width + x
    }
}