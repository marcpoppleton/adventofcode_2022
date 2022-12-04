class Day3:Day {

    private val lowPriorities = ('a'..'z').toList()
    private val highPriorities = ('A'..'Z').toList()

    private val priorities = listOf(lowPriorities,highPriorities).flatten()
    override fun part1(entries: List<String>):Long {
        var totalScore = 0L
        entries.forEach { line ->
            val left:Set<Char> = line.substring(0,(line.length/2)).toSet()
            val right:Set<Char> = line.substring((line.length/2),line.length).toSet()
            val item:Char  = left.intersect(right).first()
            val score = priorities.indexOf(item)+1
            totalScore += score
        }

        return totalScore

    }

    override fun part2(entries: List<String>):Long {
        var totalScore = 0L
        var team:MutableList<Set<Char>> = mutableListOf()
        entries.forEach { line ->
            team.add(line.toSet())
            if(team.size==3){
                val item:Char = team[0].intersect(team[1]).intersect(team[2]).first()
                val score = priorities.indexOf(item)+1
                totalScore += score
                team.removeAt(2)
                team.removeAt(1)
                team.removeAt(0)
            }
        }

        return totalScore
    }

}