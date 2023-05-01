package com.example.puzzles

object Flights {

    fun listFlights(origin: String): Iterable<String> =
        when (origin) {
            "SFO" -> listOf("DEN", "NYC", "ABC")
            "NYC" -> listOf("EER", "BDC")
            "DEN" -> listOf("SFO", "ATL", "CDG")
            else -> listOf()
        }

    fun findRoute(origin: String, destination: String) = findRouteInternal(origin, destination, mutableSetOf())

    private fun findRouteInternal(origin: String, destination: String, visited: MutableSet<String>): Boolean {
        if (origin == destination) {
            return true;
        }

        visited.add(origin);

        for (o in listFlights(origin)) {
            if (!visited.contains(o) && findRouteInternal(o, destination, visited)) {
                return true;
            }
        }

        return false;
    }
}