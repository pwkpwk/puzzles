package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GraphTests {

    @Test
    fun findCheapestPath_pathExists_findsPath() {

        assertEquals(
            2, Graph.findCheapestPath(
                listOf(
                    Graph.Edge(0, 1, 1),
                    Graph.Edge(0, 2, 1),
                    Graph.Edge(1, 2, 1),
                    Graph.Edge(2, 3, 1),
                ),
                0,
                3
            )!!.totalCost
        )
        assertEquals(
            2, Graph.findCheapestPath(
                listOf(
                    Graph.Edge(0, 1, 2),
                    Graph.Edge(0, 2, 1),
                    Graph.Edge(0, 3, 3),
                    Graph.Edge(2, 1, -1),
                    Graph.Edge(2, 3, 1),
                ),
                0,
                3
            )!!.totalCost
        )
    }

    @Test
    fun findCheapestPath_noPath_returnsNull() {
        assertNull(
            Graph.findCheapestPath(
                listOf(
                    Graph.Edge(0, 1, 1),
                    Graph.Edge(0, 2, 1),
                    Graph.Edge(1, 2, 1),
                    Graph.Edge(2, 3, 1),
                ),
                0,
                4
            )

        )
    }
}
