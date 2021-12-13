package gay.vaskel.aoc2021.days

import java.io.File
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

interface BaseDay {
    val day: Int

    /**
     * Returns the data from `data/input_day<day>.txt`.
     * This file should contain the data for the final solution.
     *
     * @see getTestData for getting the example problem data.
     *
     * @return The data, split by newline and trimmed.
     */
    fun getData(): List<String> {
        return File("data/input_day${day}.txt")
            .readText()
            .split("\n")
            .map(String::trim)
    }

    /**
     * Returns the data from ``data/test_input_day<day>.txt``.
     * This file should contain the data from the example given in the problems, for testing.
     *
     * @see getData for getting the problem data.
     *
     * @return The data, split by newline and trimmed.
     */
    fun getTestData(): List<String> {
        return File("data/test_input_day${day}.txt")
            .readText()
            .split("\n")
            .map(String::trim)
    }

    fun solution() {
        val timeOne: Double = measureTimeMillis { part1() } / 1000.toDouble()
        val timeTwo = measureTimeMillis { part2() } / 1000.toDouble()

        println("Part 1:")
        println(part1())
        println("(took $timeOne seconds)")
        println("Part 2: ")
        println(part2())
        println("(took $timeTwo seconds)")
    }

    fun part1(): Number

    fun part2(): Number

}