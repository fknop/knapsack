import java.util.*

interface ComputeBoundStrategy {
    fun computeBound(items: List<Item>, index: Int, capacity: Int, initialWeight: Int, initialBound: Double): Double
}

/**
 * Default upperbound calculation strategy.
 * Takes items until it doesn't fit in the knapsack and relax the integrity constraint to fit the item in the knapsack.
 */
class DefaultComputeBoundStrategy: ComputeBoundStrategy {
    override fun computeBound(items: List<Item>, index: Int, capacity: Int, initialWeight: Int, initialBound: Double): Double {
        var w = initialWeight
        var bound = initialBound
        var i = index
        while (i < items.size) {
            val item = items[i]

            if (w + item.weight > capacity) break

            bound += item.value
            w += item.weight
            i++
        }

        if (i < items.size) {
            bound += (capacity - w) * (items[i].ratio)
        }

        return bound
    }
}

/**
 * Branch and bound solver.
 * Complexity is O(2^n) but in theory will always be faster because we are able to cut some branches of the tree.
 * It uses Strategy Pattern to define an upperbound calculation
 */
class BranchAndBoundSolver(problem: KnapsackProblem, val boundStrategy: ComputeBoundStrategy = DefaultComputeBoundStrategy()): KnapsackSolver(problem, "Branch And Bound") {

    val sortedItems = items.sortedByDescending { it.ratio }

    /**
     * Inner class to represent a node of the Branch and bound tree.
     * The level represent "how deep" the item is in the tree.
     * The bound is the current upper bound for this node
     * The value is the current knapsack value for this node
     * The weight is the current knapsack weight for this node
     * Taken is the list of items that are in the knapsack currently
     */
    inner class ItemNode(): Comparable<ItemNode> {
        override fun compareTo(other: ItemNode): Int {
            return if (other.bound - bound < 0) -1
            else if (other.bound - bound > 0) 1
            else 0
        }

        var level: Int = -1
        var bound: Double = .0
        var value: Int = 0
        var weight: Int = 0
        var taken = mutableListOf<Item>()

        constructor(parent: ItemNode) : this() {
            this.level = parent.level + 1
            this.bound = parent.bound
            this.value = parent.value
            this.weight = parent.weight
            this.taken = mutableListOf(*parent.taken.toTypedArray())
        }

        fun computeBound() {
            val solver = this@BranchAndBoundSolver
            this.bound = solver.boundStrategy.computeBound(solver.sortedItems, level + 1, solver.capacity, weight, value.toDouble())
        }
    }

    override fun getSolution(): KnapsackSolution {

        var best = ItemNode() // Best item found so far
        val root = ItemNode()
        val pq = PriorityQueue<ItemNode>()

        // The root is not an element of the knapsack
        root.computeBound()
        pq.offer(root)

        while (pq.isNotEmpty()) {

            val item = pq.poll()
            if (item.bound > best.value && item.level < sortedItems.size - 1) {

                // Take item
                val with = ItemNode(item)

                // Calculate the weight and the value if we take that item
                with.weight += sortedItems[with.level].weight
                with.value += sortedItems[with.level].value

                // If we don't break the capacity constraint, add the item to the taken list
                if (with.weight <= capacity) {
                    with.taken.add(sortedItems[with.level])

                    // Recalculate bound
                    with.computeBound()

                    // If the current value is better than the best value, replace the best with the current value
                    if (with.value > best.value) {
                        best = with
                    }

                    // If the bound is greater than the best value, it means that it might have a better solution
                    if (with.bound > best.value) {
                        pq.offer(with)
                    }
                }

                // Don't take item
                val without = ItemNode(item)

                // Recalculate bound
                without.computeBound()

                // If the bound is greater than the best value, it means that it might have a better solution
                if (without.bound > best.value) {
                    pq.offer(without)
                }

            }
        }

        val solution = KnapsackSolution()
        return solution.addAll(best.taken)
    }
}
