/**
 * Special case where all of the items are the same value/weight and a sum of items can reach the final capacity
 */
class SubsetSum(problem: KnapsackProblem): KnapsackSolver(problem, "SubsetSum") {

    override fun getSolution(): KnapsackSolution {
        return loop(items.size, capacity) ?: KnapsackSolution()
    }

    fun loop(n: Int, sum: Int): KnapsackSolution? {
        if (sum == 0) {
            return KnapsackSolution()
        }

        if (n <= 0 && sum != 0) {
            return null
        }

        if (sum - items[n-1].weight  < 0) {
            return loop(n - 1, sum)
        }
        else {
            val skip = loop(n - 1, sum)

            if (skip != null) {
                return skip
            }

            val add = loop(n - 1, sum - items[n - 1].weight)?.add(items[n-1])

            if (add != null) {
                return add
            }

            return null
        }
    }
}

