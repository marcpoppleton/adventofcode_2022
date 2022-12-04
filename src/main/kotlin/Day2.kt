class Day2:Day {

    // A, B or C => Elve
    // X, Y, Z => Your play

    private val values:HashMap<String,Long> = hashMapOf(
        "A" to 1, "X" to 1, // A or X Rock, value 1
        "B" to 2, "Y" to 2, // B or Y Paper, value 2
        "C" to 3, "Z" to 3 // C or Z Scissors, value 3
    )

    // score 0 if lost
    // score 3 if draw
    // score 6 if win
    private val aScores:HashMap<String,Long> = hashMapOf(
        "A" to 3, "X" to 3, // Rock draws Rock
        "B" to 0, "Y" to 0, // Rock looses Paper
        "C" to 6, "Z" to 6 // Rock wins Scissors
    )
    private val bScores:HashMap<String,Long> = hashMapOf(
        "B" to 3, "Y" to 3, // Paper draws Paper
        "C" to 0, "Z" to 0, // Paper looses Scissors
        "A" to 6, "X" to 6 // Paper wins Rock
    )
    private val cScores:HashMap<String,Long> = hashMapOf(
        "C" to 3, "Z" to 3, // Scissors draws Scissors
        "A" to 0, "X" to 0, // Scissors looses Rock
        "B" to 6, "Y" to 6 // Scissors wins Paper
    )
    private val scores:HashMap<String,HashMap<String,Long>> = hashMapOf(
        "A" to aScores, "X" to aScores,
        "B" to bScores, "Y" to bScores,
        "C" to cScores, "Z" to cScores,
    )

    // X means loose
    // Y means draw
    // Z means win
    private val aActions:HashMap<String,String> = hashMapOf(
        "X" to "C",
        "Y" to "A",
        "Z" to "B"
    )
    private val bActions:HashMap<String,String> = hashMapOf(
        "X" to "A",
        "Y" to "B",
        "Z" to "C"
    )
    private val cActions:HashMap<String,String> = hashMapOf(
        "X" to "B",
        "Y" to "C",
        "Z" to "A"
    )
    private val actions:HashMap<String,HashMap<String,String>> = hashMapOf(
        "A" to aActions,
        "B" to bActions,
        "C" to cActions
    )
    private val actionScore:HashMap<String,Long> = hashMapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    override fun part1(entries: List<String>):Long {
        var totalScore = 0L
        entries.forEach { line ->
            val shapes = line.split(" ")
            val you = shapes[1]
            val elve = shapes[0]
            val shapeValue: Long = values[you] ?: 0L
            val roundScore: Long = scores[you]!![elve] ?: 0L
            val score:Long = shapeValue + roundScore
            score.let { totalScore += it }
        }

        return totalScore

    }

    override fun part2(entries: List<String>):Long {
        var totalScore = 0L
        entries.forEach { line ->
            val shapes = line.split(" ")
            val action = shapes[1]
            val elve = shapes[0]
            val shapeToPlay: String = actions[elve]!![action] ?: ""
            val shapeValue: Long = values[shapeToPlay] ?: 0L
            val roundScore: Long = actionScore[action] ?: 0L
            val score:Long = shapeValue + roundScore
            score.let { totalScore += it }
        }

        return totalScore
    }

}