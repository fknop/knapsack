import java.io.File
import java.util.*

data class KnapsackProblem(val items: Array<Item>, val capacity: Int) {
    companion object {
        fun fromFile (path: String): KnapsackProblem {
            val file = File(path)
            val scanner = Scanner(file)
            val n = scanner.nextInt()
            val capacity = scanner.nextInt()

            val items = 1.rangeTo(n).map {
                val value = scanner.nextInt()
                val weight = scanner.nextInt()
                Item(value, weight)
            }.toTypedArray()

            return KnapsackProblem(items, capacity)
        }
    }
}