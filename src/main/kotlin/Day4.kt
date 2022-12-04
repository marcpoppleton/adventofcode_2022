class Day4:Day {

   override fun part1(entries: List<String>):Long {

        var totalScore = 0L
        entries.forEach { line ->
            val pairs = line.split(",")
            val left = pairs[0].split("-")
            val right = pairs[1].split("-")
            val leftLow = left[0].toLong()
            val leftHigh = left[1].toLong()
            val rightLow = right[0].toLong()
            val rightHigh = right[1].toLong()
            val leftRange = (leftLow .. leftHigh).toList()
            val rightRange = (rightLow .. rightHigh).toList()

            totalScore += if(leftRange.size >= rightRange.size){
                if (leftRange.containsAll(rightRange)) 1L else 0L
            }else{
                if (rightRange.containsAll(leftRange)) 1L else 0L

            }
        }

        return totalScore

    }

    override fun part2(entries: List<String>):Long {
        var totalScore = 0L
        entries.forEach { line ->
            val pairs = line.split(",")
            val left = pairs[0].split("-")
            val right = pairs[1].split("-")
            val leftLow = left[0].toLong()
            val leftHigh = left[1].toLong()
            val rightLow = right[0].toLong()
            val rightHigh = right[1].toLong()
            val leftRange = (leftLow .. leftHigh).toList()
            val rightRange = (rightLow .. rightHigh).toList()

            totalScore+= if(leftRange.intersect(rightRange.toSet()).isNotEmpty()) 1L else 0L
        }

        return totalScore
    }

}