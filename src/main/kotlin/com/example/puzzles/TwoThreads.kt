package com.example.puzzles

import java.util.concurrent.Semaphore

object TwoThreads {

    fun startTwoThreads() {
        // Threads will only share the synchronization object; each thread will have its own state - the next
        // number to print.

        // Semaphore that allows the odd number print
        val oddPrints = Semaphore(1)
        // Semaphore that allows the even number thread print - initially drained so the even thread is blocked
        val evenPrints = Semaphore(1).apply { drainPermits() }
        // The thread that prints odd numbers - 1, 3, 5, 7...
        val oddThread = Thread {
            var i = 1

            while (i < 100) {
                // Wait for the even thread to release the semaphore and print the next number
                oddPrints.acquire()
                println("${i}")
                // Let the even thread print the next number
                evenPrints.release()
                i += 2
            }

        }
        // The thread that prints the odd numbers - 2, 4, 6, 8...
        val evenThread = Thread {
            var i = 2

            while (i <= 100) {
                // Wait for the odd thread to release the semaphore (that is initially drained)
                evenPrints.acquire()
                println("${i}")
                // Let the odd thread print the next number
                oddPrints.release()
                i += 2
            }
        }

        // Start both threads (does not matter in which order)
        oddThread.start()
        evenThread.start()

        // Wait for both threads to finish
        oddThread.join()
        evenThread.join()
    }

}