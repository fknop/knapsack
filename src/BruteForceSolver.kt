/**
 * The brute force solver tries out all the combinations.
 * It takes O(2^n) but always give the optimal value.
 */
class BruteForceSolver(problem: KnapsackProblem): KnapsackSolver(problem, "Brute Force") {

    private var best: KnapsackSolution = KnapsackSolution()

    override fun getSolution(): KnapsackSolution {
        val sets = subsets(items.toMutableList())

        sets.forEach {
            val weight = getWeight(it)
            if (weight <= capacity) {
                val value = getValue(it)
                if (value > best.value) {
                    best.value = value
                    best.weight = weight
                    best.items = it
                }
            }
        }

        return best
    }

    private fun getValue(items: MutableList<Item>): Int {
        return items.sumBy { it.value }
    }

    private fun getWeight(items: MutableList<Item>): Int {
        return items.sumBy { it.weight }
    }

    // https://github.com/patrickherrmann/Knapsack/blob/master/BruteForceSolver.java
    private fun subsets(items: MutableList<Item>): MutableList<MutableList<Item>> {

        val subsets = mutableListOf<MutableList<Item>>()
        if (items.isEmpty()) {
            subsets.add(mutableListOf<Item>())
            return subsets
        }

        val first = items[0]

        val subsubsets = subsets(items.subList(1, items.size))

        subsubsets.forEach {
            subsets.add(it)
            val augmented = mutableListOf(*it.toTypedArray())
            augmented.add(first)
            subsets.add(augmented)
        }

        return subsets
    }
}