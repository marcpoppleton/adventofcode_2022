class Day2:Day {

    // A, B or C => Elve
    // X, Y, Z => Your play

    val values:HashMap<String,Long> = hashMapOf(
        "A" to 1, "X" to 1, // A or X Rock, value 1
        "B" to 2, "Y" to 2, // B or Y Paper, value 2
        "C" to 3, "Z" to 3 // C or Z Scissors, value 3
    )

    // score 0 if lost
    // score 3 if draw
    // score 6 if win
    val a_scores:HashMap<String,Long> = hashMapOf(
        "A" to 3, "X" to 3, // Rock draws Rock
        "B" to 0, "Y" to 0, // Rock looses Paper
        "C" to 6, "Z" to 6 // Rock wins Scissors
    )
    val b_scores:HashMap<String,Long> = hashMapOf(
        "B" to 3, "Y" to 3, // Paper draws Paper
        "C" to 0, "Z" to 0, // Paper looses Scissors
        "A" to 6, "X" to 6 // Paper wins Rock
    )
    val c_scores:HashMap<String,Long> = hashMapOf(
        "C" to 3, "Z" to 3, // Scissors draws Scissors
        "A" to 0, "X" to 0, // Scissors looses Rock
        "B" to 6, "Y" to 6 // Scissors wins Paper
    )
    val scores:HashMap<String,HashMap<String,Long>> = hashMapOf(
        "A" to a_scores, "X" to a_scores,
        "B" to b_scores, "Y" to b_scores,
        "C" to c_scores, "Z" to c_scores,
    )

    // X means loose
    // Y means draw
    // Z means win
    val a_actions:HashMap<String,String> = hashMapOf(
        "X" to "C",
        "Y" to "A",
        "Z" to "B"
    )
    val b_actions:HashMap<String,String> = hashMapOf(
        "X" to "A",
        "Y" to "B",
        "Z" to "C"
    )
    val c_actions:HashMap<String,String> = hashMapOf(
        "X" to "B",
        "Y" to "C",
        "Z" to "A"
    )
    val actions:HashMap<String,HashMap<String,String>> = hashMapOf(
        "A" to a_actions,
        "B" to b_actions,
        "C" to c_actions
    )
    val action_score:HashMap<String,Long> = hashMapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    override fun part1(entries: List<String>):Long {
        var total_score = 0L
        entries.forEach { line ->
            val shapes = line.split(" ")
            val you = shapes[1]
            val elve = shapes[0]
            val shape_value: Long = values[you] ?: 0L
            val round_score: Long = scores[you]!![elve] ?: 0L
            val score:Long = shape_value + round_score
            score.let { total_score += it }
        }

        return total_score

    }

    override fun part2(entries: List<String>):Long {
        var total_score = 0L
        entries.forEach { line ->
            val shapes = line.split(" ")
            val action = shapes[1]
            val elve = shapes[0]
            val shape_to_play: String = actions[elve]!![action] ?: ""
            val shape_value: Long = values[shape_to_play] ?: 0L
            val round_score: Long = action_score[action] ?: 0L
            val score:Long = shape_value + round_score
            score.let { total_score += it }
        }

        return total_score
    }

}