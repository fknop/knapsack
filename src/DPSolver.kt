/**
 * Dynamic Programming solver.
 * Complexity is O(nC) with n being the number of items and C being the total capacity of the knapsack.
 */
class DPSolver(knapsack: KnapsackProblem): KnapsackSolver(knapsack, "Dynamic Programming") {

    val cache = mutableMapOf<Pair<Int, Int>, KnapsackSolution>()

    override fun getSolution(): KnapsackSolution {
        val solution = loop(items.size - 1, capacity)
        return solution
    }

    private fun loop (j: Int, k: Int): KnapsackSolution {

        if (j < 0) {
            return KnapsackSolution()
        }

        val wj = items[j].weight
        if (wj > k) {
            // Don't take this item, skip to next
            return cached(j - 1, k)
        }
        else {
            val skipSolution = cached(j - 1, k)
            val takeSolution = cached(j - 1, k - wj).add(items[j])
            return KnapsackSolution.betterOf(skipSolution, takeSolution)
        }
    }

    private fun cached (j: Int, k: Int): KnapsackSolution {
        val pair = Pair(j, k)
        if (pair !in cache) {
            cache[pair] = loop(j, k)
        }

        return cache[pair]!!
    }
}


