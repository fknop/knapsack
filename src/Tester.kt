fun main(args: Array<String>) {

    val items = arrayOf<Item>(
            Item(10, 5),
            Item(5, 3),
            Item(15, 9),
            Item(7, 7),
            Item(8, 4),
            Item(10, 3),
            Item(11, 4),
            Item(13, 5)
    )

    val capacity = 20

    val problem = KnapsackProblem(items, capacity)

    val solver = DynamicProgrammingSolver(problem)

    val solution = solver.solve()

    solution.items.forEach {
        println(it)
    }

    println(solution.weight)
    println(solution.value)

}