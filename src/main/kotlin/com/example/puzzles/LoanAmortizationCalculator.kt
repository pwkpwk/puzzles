package com.example.puzzles

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import kotlin.math.pow

object LoanAmortizationCalculator {

    private val currencyFormat by lazy {
        NumberFormat.getCurrencyInstance(Locale.US).apply { roundingMode = RoundingMode.HALF_EVEN }
    }

    fun printLoanSchedule(borrowedAmount: Int, apr: Double, termMonths: Int) {
        val monthlyInterestRate = apr / 12.0
        var remainingBalance = borrowedAmount.toDouble()
        val monthlyPayment = remainingBalance * monthlyInterestRate *
                (1.0 + monthlyInterestRate).pow(termMonths.toDouble()) /
                ((1.0 + monthlyInterestRate).pow(termMonths.toDouble()) - 1.0)

        for (t in 1..termMonths) {
            val termInterest = remainingBalance * monthlyInterestRate
            val principalPayment = monthlyPayment - termInterest

            remainingBalance -= principalPayment

            val ti = currencyFormat.format(termInterest)
            val pp = currencyFormat.format(principalPayment)
            val rb = currencyFormat.format(remainingBalance)
            println("Month: ${t}, Interest: ${ti}, principal: ${pp}, balance: ${rb}")
        }
    }
}
