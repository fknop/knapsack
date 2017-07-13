import java.util.*

interface ComputeBoundStrategy {
    fun computeBound(items: List<Item>, index: Int, capacity: Int, initialWeight: Int, initialBound: Double): Double
}

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
        bound += (capacity - w) * (items[i - 1].ratio)

        return bound
    }
}

class BranchAndBoundSolver(problem: KnapsackProblem, val boundStrategy: ComputeBoundStrategy = DefaultComputeBoundStrategy()): KnapsackSolver(problem, "Branch And Bound") {

    val sortedItems = items.sortedByDescending { it.ratio }

    inner class ItemNode(): Comparable<ItemNode> {
        override fun compareTo(other: ItemNode): Int {
            return other.bound.toInt() - bound.toInt()
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

        var best = ItemNode()
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

                with.weight += sortedItems[with.level].weight
                with.value += sortedItems[with.level].value

                if (with.weight <= capacity) {
                    with.taken.add(sortedItems[with.level])
                    with.computeBound()
                    println(with.bound)
                    if (with.value > best.value) {
                        best = with
                    }

                    if (with.bound > best.value) {
                        pq.offer(with)
                    }
                }

                // Don't take item
                val without = ItemNode(item)
                without.computeBound()

                if (without.bound > best.value) {
                    pq.offer(without)
                }

            }
        }

        val solution = KnapsackSolution()
        solution.value = best.value
        solution.weight = best.weight
        solution.items = best.taken

        return solution
    }
}