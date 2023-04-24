package com.example.puzzles

import java.util.LinkedList

object Graph {

    fun findCheapestPath(edges: Iterable<Edge>, start: Int, end: Int): Waypoint? {

        // Build the graph
        mutableMapOf<Int, MutableCollection<Neighbor>>().also {
            for (edge in edges) {
                Neighbor(edge.dst, edge.cost).let { neighbor ->
                    val neighbors = it[edge.src]

                    if (neighbors == null) {
                        it[edge.src] = mutableListOf(neighbor)
                    } else {
                        neighbors.add(neighbor)
                    }

                    if (!it.containsKey(edge.dst)) {
                        it[edge.dst] = mutableListOf()
                    }
                }
            }
        }.let { graph ->
            LinkedList<Waypoint>().let { queue ->
                var cheapest: Waypoint? = null
                val visited = mutableSetOf<Int>()
                queue.add(Waypoint(start))

                while (queue.isNotEmpty()) {
                    val wp = queue.removeFirst()

                    if (wp.id == end) {
                        if (cheapest == null || cheapest.totalCost > wp.totalCost) {
                            cheapest = wp
                        }
                    }

                    if (!visited.contains(wp.id)) {
                        visited.add(wp.id)

                        for (neighbor in graph[wp.id]!!) {
                            queue.add(wp.addWaypoint(neighbor.id, neighbor.cost))
                        }
                    }
                }

                return cheapest
            }
        }
    }

    class Edge(val src: Int, val dst: Int, val cost: Int)

    private class Neighbor(val id: Int, val cost: Int)

    class Waypoint {
        private val previous: Waypoint?
        val id: Int
        val totalCost: Int

        constructor(id: Int) {
            this.previous = null
            this.id = id
            this.totalCost = 0
        }

        private constructor(parent: Waypoint, id: Int, costIncrement: Int) {
            this.previous = parent
            this.id = id
            this.totalCost = parent.totalCost + costIncrement
        }

        val path: Iterable<Int> by lazy {
            LinkedList<Int>().also {
                var wp: Waypoint? = this

                while(wp != null) {
                    it.addFirst(wp.id)
                    wp = wp.previous
                }
            }
        }

        fun addWaypoint(id: Int, costIncrement: Int) = Waypoint(this, id, costIncrement)
    }
}
