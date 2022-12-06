class Day6 : Day {


    override fun part1(entries: List<String>): List<Int> {
        var result = mutableListOf<Int>()
        entries.forEach { line ->
            val buffer:ArrayDeque<Char> = ArrayDeque(4)
            line.toCharArray().forEachIndexed { index, char ->
                buffer.addFirst(char)
                if (buffer.size == 5) buffer.removeLast()
                if (buffer.distinct().size == 4 && buffer.size==4) {
                    result.add(index+1)
                    return@forEach
                }
            }
        }

        return result

    }

    override fun part2(entries: List<String>): List<Int> {
        var result = mutableListOf<Int>()
        entries.forEach { line ->
            val buffer:ArrayDeque<Char> = ArrayDeque(14)
            line.toCharArray().forEachIndexed { index, char ->
                buffer.addFirst(char)
                if (buffer.size == 15) buffer.removeLast()
                if (buffer.distinct().size == 14 && buffer.size==14) {
                    result.add(index+1)
                    return@forEach
                }
            }
        }

        return result
    }

}