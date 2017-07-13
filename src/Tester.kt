
fun main(args: Array<String>) {

    val problem = KnapsackProblem.fromFile("hard200.txt")
/*
    val bab = BranchAndBoundSolver(problem)
    val solution = bab.solve()
    printSolution(problem, solution)
*/
//    val dp = DPSolver(problem)
  //  val dpSolution = dp.solve()
    //printSolution(dpSolution)


    val greedy = GreedySolver(problem)
    val greedySolution = greedy.solve()
    printSolution(problem, greedySolution)

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

