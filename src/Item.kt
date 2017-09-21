
data class Item(val value: Int, val weight: Int, val index: Int) {

    val ratio: Double = value.toDouble() / weight.toDouble()
}