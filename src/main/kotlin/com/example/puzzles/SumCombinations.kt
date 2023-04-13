package com.example.puzzles

class SumCombinations {
    /*
     * Assumptions:
     * - The sum is greater than zero
     * - Numbers in the array are unique and greater than zero
     */
    fun collectCombinations(sum: Int, numbers: Array<Int>): List<Array<Int>> =
        mutableListOf<Array<Int>>().also {
            collectCombinations(sum, numbers, 0, 0, Array(numbers.size) { 0 }, it)
        }

    private fun collectCombinations(
        sum: Int,
        numbers: Array<Int>,
        sumSoFar: Int,
        start: Int,
        factors: Array<Int>,
        bag: MutableList<Array<Int>>
    ) {
        if (sumSoFar < sum && start < numbers.size) {
            var i = 0
            var currentSum = sumSoFar

            while (currentSum <= sum) {
                if (currentSum == sum) {
                    print("[ ");
                    for (index in 0 until numbers.size) {
                        if (index > 0) print("+ ")
                        print("${factors[index]}*${numbers[index]} ")
                    }
                    println("]")
                    bag.add(Array(factors.size) { i -> factors[i] })
                    break
                }

                collectCombinations(sum, numbers, currentSum, start + 1, factors, bag)

                factors[start] += 1
                ++i
                currentSum += numbers[start]
            }

            factors[start] -= i
        }
    }
}