/**
 * Base class for the knapsack solver
 * @knapsack the knapsack problem
 * @name the name of the solver
 */
abstract class KnapsackSolver(knapsack: KnapsackProblem, val name: String) {

    val items = knapsack.items.toList()
    val capacity = knapsack.capacity

    /**
     * Solves the knapsack problem and measure the time taken by the solution
     */
    fun solve(): KnapsackSolution {
        return measureTime {
            getSolution()
        }
    }

    /**
     * Method to override to return a knapsack solution
     */
    abstract protected fun getSolution (): KnapsackSolution

    /**
     * Measure the time in ms
     */
    inline fun measureTime(block: () -> KnapsackSolution) : KnapsackSolution {
        val start = System.currentTimeMillis()
        val solution = block()
        solution.time = System.currentTimeMillis() - start
        solution.name = name
        return solution
    }

}