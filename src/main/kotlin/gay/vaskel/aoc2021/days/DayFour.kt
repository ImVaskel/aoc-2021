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

    private val nums: List<Int> = getData().first().split(",").map(String::toInt)

    private fun getWinningBoards(): List<Pair<Int, BingoBoard>> {
        val data = getData().toMutableList()
        data.removeFirst()
        data.removeIf(String::isBlank) // remove the newlines, so all we have is bingo boards.

        @Suppress("NestedLambdaShadowedImplicitParameter")
        val rawBingoBoards = data
            .chunked(5) // each bingo board is 5 lines
            .map {
                it.map { it.split("\\s".toRegex()) }.map {
                    it.filter(String::isNotBlank).map(String::toInt)
                }
            }

        val boards: MutableList<BingoBoard> = mutableListOf()
        for (entry in rawBingoBoards) {
            val columns = mutableListOf<List<Int>>()
            val rows = mutableListOf<List<Int>>()
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

        val validBoards: MutableList<Pair<Int, BingoBoard>> = mutableListOf()
        for (i in 0 until nums.count()) {
            val valids = mutableListOf<BingoBoard>()
            for (board in boards) {
                // get concurrent mod if removed while iterating, so we have to remove after iterating
                if (board.checkWinner(nums.subList(0, i+1))) {
                    validBoards.add(Pair(i, board))
                    valids.add(board)
                }
            }
            boards.removeIf { it in valids }
        }

        return validBoards
    }

    override fun part1(): Number {
        val (idx, board) = getWinningBoards().first()
        return nums[idx] * board.getSum(nums.subList(0, idx+1))
    }
    override fun part2(): Number {
        val valid = getWinningBoards()
        val (idx, board) = valid.last()
        return nums[idx] * board.getSum(nums.subList(0, idx+1))
    }
}