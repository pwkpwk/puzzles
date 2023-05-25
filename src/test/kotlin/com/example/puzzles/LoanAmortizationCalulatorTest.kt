package com.example.puzzles

import java.math.BigDecimal
import java.math.MathContext
import kotlin.test.Test

class LoanAmortizationCalulatorTest {

    @Test
    fun test() {
        LoanAmortizationCalculator.printLoanSchedule(
            100000,
            0.05,
            10
        )
    }
}