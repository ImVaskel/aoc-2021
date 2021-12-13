package gay.vaskel.aoc2021.days

class DayThree : BaseDay {
    override val day: Int = 3

    override fun part1(): Int {
        val data = getData()
        val bits = mutableListOf<Int>().apply { for (i in 0 until data.first().length) add(0) }

        for (line in data) {
            for ((index, value) in line.withIndex()) {
                if (value == '1') bits[index]++
                else bits[index]--
            }
        }

        val gamma = bits.map { if (it > 1) 1 else 0 }.joinToString("").toInt(2)
        val epsilon = bits.map { if (it > 1) 0 else 1 }.joinToString("").toInt(2)

        return gamma * epsilon
    }

    private fun filter(low: Char, high: Char): String {
        val data = getData().toMutableList()
        for (i in data.first().indices) {
            if (data.size == 1) break
            val sum = data.sumOf {
                if (it[i] == '1') 1 else -1 as Int
            }
            val remove = if (sum >= 0) high else low
            data.removeAll {
                it[i] == remove
            }
        }
        return data.first()
    }

    override fun part2(): Int {
        val oxygen = filter('1', '0').toInt(2)
        val co2 = filter('0', '1').toInt(2)
        return oxygen * co2
    }

}