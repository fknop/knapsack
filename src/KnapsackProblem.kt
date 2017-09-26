import java.io.File
import java.util.*

data class KnapsackProblem(val items: Array<Item>, val capacity: Int) {
    companion object {

        /**
         * Create a KnapsackProblem from a file with format:
         * number_of_items capacity
         * item_value_0 item_weight_0
         * item_value_1 item_weight_1
         * ...
         * item_value_n item_weight_n
         */
        fun fromFile (path: String): KnapsackProblem {
            val file = File(path)
            val scanner = Scanner(file)
            val n = scanner.nextInt()
            val capacity = scanner.nextInt()

            val items = 1.rangeTo(n).mapIndexed { index, i ->
                Item(scanner.nextInt(), scanner.nextInt(), index)
            }.toTypedArray()

            return KnapsackProblem(items, capacity)
        }
    }
}