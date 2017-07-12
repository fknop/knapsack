

class KnapsackSolution {

    var name: String = "Unnamed"

    // List of the indices of the items
    var items = mutableListOf<Item>()

    // Total value for the knapsack
    var value: Int = 0

    // Total weight for the knapsack
    var weight: Int = 0

    // The time in ms to retrieve the solution
    var time: Long = 0


    fun add(item: Item): KnapsackSolution {
        val solution = KnapsackSolution()
        solution.items.addAll(this.items)
        solution.items.add(item)
        solution.value = this.value + item.value
        solution.weight = this.weight + item.weight
        return solution
    }

    fun better(solution: KnapsackSolution): Boolean {
        return this.value > solution.value
    }

    companion object {
        fun betterOf(a: KnapsackSolution, b: KnapsackSolution): KnapsackSolution {
            return if (a.better(b)) a else b
        }


    }
}