import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T> identity(t: T) = t

fun <T> stringToType(input: String, mapping: (String) -> T?): List<T> =
    input.split(" ")
        .filter { it.isNotBlank() }
        .mapNotNull { mapping(it) }

fun <T, R> List<T>.foldPairs(initial: R, operation: (acc: R, first: T, second: T) -> R): R {
    if (this.size < 2) {
        throw IllegalArgumentException("List must have at least 2 elements")
    }

    return (1 until this.size).fold(initial) { acc, index ->
        operation(acc, this[index - 1], this[index])
    }
}

fun <T> List<T>.eachRemoved(): List<List<T>> {
    val result = mutableListOf<List<T>>()
    for (i in indices) {
        val subList = subList(0, i) + subList(i + 1, size)
        result.add(subList)
    }
    return result
}