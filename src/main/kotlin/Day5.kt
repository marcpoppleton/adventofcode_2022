class Day5 : Day {

    private var piles: MutableMap<Int, ArrayDeque<Char>> = mutableMapOf()
    private var instructions:MutableList<Instruction> = mutableListOf()
    override fun part1(entries: List<String>): String {

        var crates = ""

        parseData(entries)
        instructions.forEach{instruction ->
            val(repeats,from,to) = instruction
            repeat(repeats){
                val crate = piles[from]!!.removeLast()
                piles[to]!!.addLast(crate)
            }
        }
        for(i in 1..piles.size){
            crates += piles[i]!!.last()
        }
        return crates

    }

    override fun part2(entries: List<String>): String {
        var crates = ""
        parseData(entries)
        instructions.forEach{instruction ->
            val(repeats,from,to) = instruction
            val temporaryStack:ArrayDeque<Char> = ArrayDeque(repeats)
            repeat(repeats){
                val crate = piles[from]!!.removeLast()
                temporaryStack.addFirst(crate)
            }
                piles[to]!!.addAll(temporaryStack)
        }
        for(i in 1..piles.size){
            crates += piles[i]!!.last()
        }

        return crates
    }

    private fun parseData(entries: List<String>){
        piles = mutableMapOf()
        instructions = mutableListOf()
        val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
        entries.forEach { line ->
            if (line.isNotEmpty()) {
                if (line.startsWith("move")) {
                    val (cal, from, to) = regex.find(line)!!.destructured
                    instructions.add(Instruction(cal.toInt(), from.toInt(), to.toInt()))
                } else {
                    val indices = Regex("\\[").findAll(line)
                        .map { it.range.first }
                        .toList()
                    indices.forEach { index ->
                        val stackNumber = (index / 4) + 1
                        val pile = piles[stackNumber] ?: ArrayDeque()
                        pile.addFirst(line[index + 1])
                        piles[stackNumber] = pile
                    }
                }
            }
        }
    }

}

data class Instruction(val itemsToMove:Int, val fromStack:Int,val toStack:Int)