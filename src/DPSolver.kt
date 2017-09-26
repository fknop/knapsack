/**
 * Dynamic Programming solver.
 * Complexity is O(nC) with n being the number of items and C being the total capacity of the knapsack.
 */
class DPSolver(knapsack: KnapsackProblem): KnapsackSolver(knapsack, "Dynamic Programming") {

    private val cache = mutableMapOf<Pair<Int, Int>, KnapsackSolution>()

    override fun getSolution(): KnapsackSolution {
        return loop(items.size - 1, capacity)
    }

    private fun loop (j: Int, k: Int): KnapsackSolution {

        if (j < 0) {
            return KnapsackSolution()
        }

        val wj = items[j].weight
        // Capacity constraint violated
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

    /**
     * Return the cached value or compute it if not in the cache.
     */
    private fun cached (j: Int, k: Int): KnapsackSolution {
        val pair = Pair(j, k)
        if (pair !in cache) {
            cache[pair] = loop(j, k)
        }

        return cache[pair]!!
    }
}


