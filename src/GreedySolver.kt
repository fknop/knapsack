/**
 * Greedy solver complexity is O(n) but it's not giving the optimal value.
 */
class GreedySolver(problem: KnapsackProblem): KnapsackSolver(problem, "Greedy") {
    override fun getSolution(): KnapsackSolution {
        val sorted = items.sortedByDescending { it.ratio }

        return sorted.fold(KnapsackSolution(), { solution, item  ->
            if (item.weight + solution.weight <= capacity) {
                solution.add(item)
            }
            else {
                solution
            }
        })
    }
}