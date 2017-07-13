
fun main(args: Array<String>) {

    val problem = KnapsackProblem.fromFile("really-small.txt")

    val bab = BranchAndBoundSolver(problem)
    val solution = bab.solve()
    printSolution(problem, solution)
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

