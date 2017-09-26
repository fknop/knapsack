import org.jetbrains.annotations.Mutable

class KnapsackSolution {

    // Name of the solver
    var name: String = "Unnamed"

    // List of items that are taken in the knapsack
    var items = mutableListOf<Item>()

    // indexedItems (for inginious printing)
    var indexedItems = mutableMapOf<Int, Item>()

    // Total value for the knapsack
    var value: Int = 0

    // Total weight for the knapsack
    var weight: Int = 0

    // The time in ms to retrieve the solution
    var time: Long = 0


    /**
     * Add an item to the knapsack
     */
    fun add(item: Item): KnapsackSolution {
        val solution = KnapsackSolution()
        solution.items.addAll(this.items)
        solution.items.add(item)
        solution.value = this.value + item.value
        solution.weight = this.weight + item.weight
        solution.indexedItems.putAll(indexedItems)
        solution.indexedItems.put(item.index, item)
        return solution
    }

    /**
     * Add a list of items to the knapsack
     */
    fun addAll(items: MutableList<Item>): KnapsackSolution {
        val solution = KnapsackSolution()
        solution.items.addAll(this.items)
        solution.items.addAll(items)
        solution.value = this.value + items.sumBy { it.value }
        solution.weight = this.weight + items.sumBy { it.weight }
        solution.indexedItems.putAll(indexedItems)
        items.forEach {
            solution.indexedItems.put(it.index, it)
        }

        return solution
    }

    /**
     * Returns true if the current solution is better than the given solution.
     * A solution is better if its value is greater.
     */
    fun better(solution: KnapsackSolution): Boolean {
        return this.value > solution.value
    }

    /**
     * Check if the solution is valid
     */
    fun checkSolution(capacity: Int): Boolean {
        val w = items.sumBy { it.weight }
        val v = items.sumBy { it.value }


        val indexedOK = items.all { indexedItems.containsKey(it.index) }

        return w == weight && v == value && weight <= capacity && indexedOK
    }

    companion object {
        /**
         * Returns the better solution between two solutions
         */
        fun betterOf(a: KnapsackSolution, b: KnapsackSolution): KnapsackSolution {
            return if (a.better(b)) a else b
        }
    }
}