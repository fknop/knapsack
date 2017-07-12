
abstract class KnapsackSolver(knapsack: KnapsackProblem, val name: String) {

    val items = knapsack.items.toList()
    val capacity = knapsack.capacity

    fun solve(): KnapsackSolution {
        return measureTime {
            getSolution()
        }
    }

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