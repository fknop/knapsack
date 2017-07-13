/**
 * Greedy solver complexity is O(n) but it's not giving the optimal value.
 */
class GreedySolver(problem: KnapsackProblem): KnapsackSolver(problem, "Greedy") {
    override fun getSolution(): KnapsackSolution {
        val sorted = items.sortedByDescending { it.ratio }
        val solution = KnapsackSolution()


        sorted.forEach {
            if (it.weight + solution.weight <= capacity) {
                solution.value += it.value
                solution.weight += it.weight
                solution.items.add(it)
            }
        }

        return solution
    }
}