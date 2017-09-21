import java.io.File
import java.util.*


fun main(args: Array<String>) {
    convert("easy-50.txt", "easy50.txt")
}

fun convert (pathIn: String, pathOut: String) {
    val scanner = Scanner(File(pathIn))
    val count = scanner.nextInt()
    val items = mutableListOf<Item>()
    for (i in 0..count - 1) {
        val ignore = scanner.nextInt()
        val item = Item(scanner.nextInt(), scanner.nextInt(), i)
        items.add(item)
    }

    val capacity = scanner.nextInt()

    File(pathOut).printWriter().use { out ->
        out.write("$count $capacity\n")

        items.forEach {
            out.write("${it.value} ${it.weight}\n")
        }
    }

}