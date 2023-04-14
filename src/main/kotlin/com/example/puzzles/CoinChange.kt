package com.example.puzzles

object CoinChange {

    /**
     * Give change for the specified sum in the smallest number of coins of the provided denominations
     *
     * @param sum Sum of money to break into coins
     * @param coins Coin denominations, unique and ordered from smallest to largest
     * @return Array of the same size as the denominations with numbers of coins at corresponding indices.
     */
    fun giveChange(sum: Int, coins: Array<Int>): Change {
        val data = arrayOfNulls<Change>(sum + 1)
        val highestCoin = coins[coins.size - 1] // Important that the denominations are ordered!

        data[0] = Change(0, Array<Int>(coins.size) { 0 })

        for (i in 1..sum) {
            // Now the funny part - how can we add one coin to some of the recent solutions
            // so that we'll end up with the minimum of total coins?

            // For each coin denomination do the following:
            // 1. Step back for the value of the coin, because we are planning to add one coin to the next solution
            // 2. Add that one coin to the solution found in the back
            // 3. Find the minimum number of coins in the new solution - minimum of the checked back solutions
            //    plus that one coin
            // Obviously, do not step back beyond the beginning of the solutions array.

            var minCoins = 0
            var minCoinsSolution: Change? = null
            var coin = -1

            for (k in coins.indices) {
                if (i - coins[k] < 0) {
                    break
                }

                if (minCoins == 0 || minCoins > (data[i - coins[k]]!!.totalCoins)) {
                    minCoinsSolution = data[i - coins[k]]
                    minCoins = minCoinsSolution!!.totalCoins
                    coin = k
                }
                ++minCoins // Add the one next coin
            }

            minCoinsSolution?.let { solution ->
                Array(coins.size) { i ->
                    val count = solution.breakdown[i]
                    if (i == coin)
                        count + 1
                    else
                        count
                }.let {
                    data[i] = Change(minCoins, it);
                }
            }

            // A little cleanup - remove the solutiuon that is behind the reach of the algorithm - farther
            // than the highers coin denomination back. Those solutions won't be re-examined.
            if (i >= highestCoin) {
                data[i - highestCoin] = null
            }
        }

        return data[sum]!!
    }

    class Change(val totalCoins: Int, val breakdown: Array<Int>)
}