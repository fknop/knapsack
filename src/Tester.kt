
fun main(args: Array<String>) {

    val problem = KnapsackProblem.fromFile("easy50.txt")

    val dp = DPSolver(problem)
    val greedy = GreedySolver(problem)
    val dpSolution = dp.solve()
    printSolution(dpSolution)
    val greedySolution = greedy.solve()
    printSolution(greedySolution)
}

fun printSolution (solution: KnapsackSolution) {

    println(solution.name)
    println("=================")

    solution.items.forEach {
        println(it)
    }

    println("ms: ${solution.time}")
    println("weight: ${solution.weight}")
    println("value: ${solution.value}")
    println("=================")
    println()
}

