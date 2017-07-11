
abstract class KnapsackSolver(knapsack: KnapsackProblem) {

    val items = knapsack.items
    val capacity = knapsack.capacity

    abstract fun solve(): KnapsackSolution
}