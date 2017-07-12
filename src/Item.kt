
data class Item(val value: Int, val weight: Int) {

    val ratio: Double = value.toDouble() / weight.toDouble()
}