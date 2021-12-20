import kotlin.math.pow

fun main() {
    fun getDecimalNumber(binaryNumber: Long): Long {
        var binaryNumber = binaryNumber
        var decimalNo = 0L
        var power = 0

        while (binaryNumber > 0) {
            val r = binaryNumber % 10
            decimalNo = (decimalNo + r * Math.pow(2.0, power.toDouble())).toLong()
            binaryNumber /= 10
            power++
        }
        return decimalNo
    }

    fun part1(input: List<String>): Int {
        var gammaRate = 0
        var length = input[0].toCharArray().size
        var tmpArr = IntArray(length)

        for (diagnostic in input) {
            var split = diagnostic.toCharArray()
            for ((index, item) in split.withIndex()) {
                when(item) {
                    '1' -> tmpArr[index] ++
                    '0' -> tmpArr[index] --
                }
            }
        }

        for (i in 0 until length) {
            gammaRate = if (tmpArr[i] > 0) {
                gammaRate * 2 + 1
            } else {
                gammaRate * 2
            }
        }

        return gammaRate * (2.0.pow(length) - 1 - gammaRate).toInt()
    }
    fun findIndexPickNum(inputs: List<String>, index: Int, isOxygen: Boolean): Int {
        var ans = 0
        for (input in inputs) {
            if (input.toCharArray()[index] == '1') ans ++ else ans --
        }
        if (ans == 0 ) return if (isOxygen) 1 else 0
        return if ((ans > 0) xor isOxygen) 0 else 1
    }
    fun findMostList(inputs: List<String>, pickNum: Int, index: Int, isOxygen: Boolean): List<String> {
        var ans: ArrayList<String> = ArrayList()
        for (input in inputs) {
            if (input.toCharArray()[index] == ('0' + pickNum)) ans.add(input)
        }
        println(ans.joinToString())
        return if (ans.size == 1) ans
        else findMostList(ans, findIndexPickNum(ans, index+1, isOxygen), index+1, isOxygen)
    }


    fun part2(input: List<String>): Long {
        var oxygen = findMostList(input, findIndexPickNum(input, 0, true), 0, true)[0]
        var scrubber = findMostList(input, findIndexPickNum(input, 0, false), 0, false)[0]

        println("$oxygen, $scrubber, ans=${getDecimalNumber(oxygen.toLong())*getDecimalNumber(scrubber.toLong())}")


        return getDecimalNumber(oxygen.toLong())*getDecimalNumber(scrubber.toLong())
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230L)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
