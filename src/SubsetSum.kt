/**
 * Greedy solver complexity is O(n) but it's not giving the optimal value.
 */
class SubsetSum(problem: KnapsackProblem): KnapsackSolver(problem, "Greedy") {

    override fun getSolution(): KnapsackSolution {
        return isSubsetSum(items.size, capacity) ?: KnapsackSolution()
    }

    fun isSubsetSum(n: Int, sum: Int): KnapsackSolution? {
        if (sum == 0) {
            return KnapsackSolution()
        }

        if (n <= 0 && sum != 0) {
            return null
        }

        if (sum - items[n-1].weight  < 0) {
            return isSubsetSum(n - 1, sum)
        }
        else {
            val skip = isSubsetSum(n - 1, sum)

            if (skip != null) {
                return skip
            }

            val add = isSubsetSum(n - 1, sum - items[n - 1].weight)?.add(items[n-1])

            if (add != null) {
                return add
            }

            return null
        }
    }
}

