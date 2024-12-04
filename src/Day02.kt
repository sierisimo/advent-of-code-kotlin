import kotlin.math.abs

private const val dayName = "Day02"

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { stringToType(it, String::toInt) }
            .map { reports -> allIncreasingRec(reports) or allDecreasingRec(reports) }
            .count { it }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${dayName}_test")
    check(part1(testInput) == 2)
//    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput(dayName)
    part1(input).println()
//    part2(input).println()
}

private fun allIncreasingRec(list: List<Int>): Boolean =
    list.allWithNextRec { prev, next ->
        prev < next && abs(prev - next) <= 3
    }

private fun allDecreasingRec(list: List<Int>): Boolean =
    list.allWithNextRec { prev, next ->
        prev > next && abs(prev - next) <= 3
    }

private fun List<Int>.allWithNextRec(
    index: Int = 0,
    predicate: (Int, Int) -> Boolean
): Boolean {
    if (index >= size - 1) return true

    return predicate(this[index], this[index + 1]) && allWithNextRec(index + 1, predicate)
}