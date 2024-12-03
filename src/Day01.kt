import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (left, right) = input.mapPartitionAndTransform(mapping = { it.toInt() }, transform = { it.sorted() })
        return left.zip(right).sumOf { (left, right) -> abs(left - right) }
    }

    fun part2(input: List<String>): Int {
        val (left, right) = input.mapPartitionAndTransform(mapping = String::toInt, transform = ::identity)
        val rightCounts = right.groupingBy { it }.eachCount()
        return left.sumOf { it * rightCounts.getOrDefault(it, 0) }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun <T, R> List<String>.mapPartitionAndTransform(
    mapping: (String) -> T,
    transform: (List<T>) -> List<R>
): Pair<List<R>, List<R>> {
    val left = mutableListOf<T>()
    val right = mutableListOf<T>()
    for (line in this) {
        val nums = line.split(" ")
            .filter(String::isNotBlank)
            .map(mapping)

        left.add(nums[0])
        right.add(nums[1])
    }
    return Pair(transform(left), transform(right))
}