package gay.vaskel.aoc2021.days

class DayFour: BaseDay {
    data class BingoBoard(val rows: List<List<Int>>, val columns: List<List<Int>>) {
        fun checkWinner(nums: List<Int>): Boolean {
            return columns.any { it.intersect(nums).size == 5 } || rows.any { it.intersect(nums).size == 5 }
        }

        fun getSum(nums: List<Int>): Int {
            return rows.sumOf { lst ->
                lst.sumOf {
                    if (!nums.contains(it)) it else 0 as Int
                }
            }
        }
    }

    override val day: Int = 4

    override fun part1(): Number {
        val data = getData().toMutableList()
        val nums = data.removeFirst().split(",").map(String::toInt)
        data.removeIf { it.isBlank() }

        val ints = data.chunked(5).map { lst ->
            lst.map { it.split("\\s".toRegex()) }
                .map { i -> i.filter { e -> e.isNotBlank() }.map { e -> e.toInt() } }
        }
        val boards: MutableList<BingoBoard> = mutableListOf()

        for (entry in ints) {
            val columns: MutableList<List<Int>> = mutableListOf()
            val rows: MutableList<List<Int>> = mutableListOf()
            for (i in entry.first().indices) {
                val column = mutableListOf<Int>()
                val row = mutableListOf<Int>()

                for (j in entry.indices) {
                    column.add(entry[j][i])
                    row.add(entry[i][j])
                }
                columns.add(column)
                rows.add(row)
            }
            boards.add(BingoBoard(rows, columns))
        }

        for (i in nums.indices) {
            for (board in boards) {
                if (board.checkWinner(nums.subList(0, i+1))) {
                    return nums[i] * board.getSum(nums.subList(0, i+1))
                }
            }
        }

        return 0
    }
    override fun part2(): Number {
        return 0
    }
}