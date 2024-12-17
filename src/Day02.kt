import kotlin.math.abs

private const val dayName = "Day02"

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { stringToType(it, String::toInt) }
            .map { reports -> reports.isSafe() }
            .count { it }
    }

    fun part2(input: List<String>): Int {
        return input.map { stringToType(it, String::toInt) }
            .map { report ->
                report.isSafe() or report.eachRemoved().any { it.isSafe() }
            }
            .count { it }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${dayName}_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput(dayName)
    part1(input).println()
    part2(input).println()
}

private fun List<Int>.isSafe(): Boolean = (allIncreasing() or allDecreasing()) and allValidDiff()

private fun List<Int>.allIncreasing(): Boolean = foldPairs(true) { acc, first, second -> acc && (first < second) }

private fun List<Int>.allDecreasing(): Boolean = foldPairs(true) { acc, first, second -> acc && (first > second) }

private fun List<Int>.allValidDiff(): Boolean =
    foldPairs(true) { acc, first, second -> acc && abs(first - second) in 1..3 }