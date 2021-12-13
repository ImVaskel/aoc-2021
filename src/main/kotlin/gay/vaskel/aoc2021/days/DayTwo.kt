package gay.vaskel.aoc2021.days

class DayTwo: BaseDay {
    override val day: Int = 2

    override fun part1(): Int {
        val data = getData().map { it.split(" ") }
        var (horizontal, depth) = arrayListOf(0, 0)

        data.forEach {
            val (direction, value) = it
            when (direction) {
                "forward" -> horizontal += value.toInt()
                "down" -> depth += value.toInt()
                "up" -> depth -= value.toInt()
            }
        }

        return horizontal * depth
    }

    override fun part2(): Int {
        val data = getData().map { it.split(" ") }
        var (horizontal, depth, aim) = arrayListOf(0, 0, 0)

        data.forEach {
            val (direction, value) = it
            when (direction) {
                "forward" -> {
                    horizontal += value.toInt()
                    depth += value.toInt() * aim
                }
                "down" -> aim += value.toInt()
                "up" -> aim -= value.toInt()
            }
        }

        return horizontal * depth
    }
}