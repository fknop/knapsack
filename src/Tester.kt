
fun main(args: Array<String>) {

    val problem = KnapsackProblem.fromFile("instanceB.txt")

    val greedy = GreedySolver(problem)
    val solution = greedy.solve()

    printForInginious(problem, solution)
    println(solution.weight)
}

fun printForInginious(problem: KnapsackProblem, solution: KnapsackSolution) {
    println(solution.value)
    problem.items.forEachIndexed { index, item ->
        if (solution.indexedItems.containsKey(index)) {
            print(1)
        }
        else {
            print(0)
        }

        print(" ")
    }
}

fun printSolution (problem: KnapsackProblem, solution: KnapsackSolution) {

    println(solution.name)
    println("=================")

    solution.items.forEach {
        println(it)
    }

    println("ms: ${solution.time}")
    println("weight: ${solution.weight}")
    println("value: ${solution.value}")
    println("check: ${solution.checkSolution(problem.capacity)}")
    println("=================")
    println()
}

