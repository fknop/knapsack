/**
 * Item tuple with value, weight and index in the original list of items
 */
data class Item(val value: Int, val weight: Int, val index: Int) {

    // The ratio value / weight
    val ratio: Double = value.toDouble() / weight.toDouble()
}