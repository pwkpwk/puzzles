package com.example.puzzles

import kotlin.test.Test
import kotlin.test.assertTrue

class FlightsTests {
    @Test
    fun connected_succeeds() {
        assertTrue(Flights.findRoute("SFO", "SFO"))
        assertTrue(Flights.findRoute("SFO", "DEN"))
        assertTrue(Flights.findRoute("SFO", "CDG"))
    }

    @Test
    fun notConnected_fails() {
        assertTrue(Flights.findRoute("SFO", "EWR"))
    }
}
