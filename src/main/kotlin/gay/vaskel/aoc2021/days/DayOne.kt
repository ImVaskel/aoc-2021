package gay.vaskel.aoc2021.days

// I did this in python before I decided to use kotlin
// So i don't have the pt 2 solution.
class DayOne: BaseDay {
    override val day: Int = 1

    override fun part1(): Int {
        val data = getData().map { it.toInt() }
        var increase = 0;

        for (i in 1 until data.count()) {
            if (data[i] > data[i-1]) {
                increase++
            }
        }

        return increase
    }

    override fun part2(): Int {
        TODO("Not yet implemented")
    }
}