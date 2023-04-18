package com.example.puzzles

import kotlin.math.absoluteValue

object Queens {

    fun placeQueens(boardSize: Int): List<Array<Int>> =
        mutableListOf<Array<Int>>().also {
            val board = Array(boardSize) { 0 }
            placeNextQueen(0, board, it)
        }

    private fun placeNextQueen(column: Int, board: Array<Int>, solutions: MutableList<Array<Int>>) {
        // Straightforward recursive algorithm that places one queen in each available row of the next column
        // and calls self for the rest of the board until queens have been placed in all columns.
        if (column == board.size) {
            // Found a solution
            solutions.add(Array(board.size) { i -> board[i] })
        } else for (row in board.indices) {
            board[column] = row

            if (validBoard(column, board)) {
                placeNextQueen(column + 1, board, solutions)
            }
        }
    }

    private fun validBoard(column: Int, board: Array<Int>): Boolean {
        for (otherQueenColumn in 0 until column) {
            val dy = (board[column] - board[otherQueenColumn]).absoluteValue

            if (dy == 0 || column - otherQueenColumn == dy) {
                return false
            }
        }

        return true
    }
}
