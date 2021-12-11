fun main() {
    fun add3Int(input: List<String>, start: Int, end: Int): Int {
        var sum = 0
        for (i in start .. end) {
            sum += input[i].toInt()
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        var answer = 0
        var previous = input[0].toInt()
        for (num in input) {
            if (previous < num.toInt()) answer++
            previous = num.toInt()
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        var previous = add3Int(input, 0, 2)
        for (i in 0 until input.size - 3) {
            var next = add3Int(input, i + 1, i + 3)
            if (previous < next) answer++
            previous = next
        }
        return answer
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part2(input))
}
