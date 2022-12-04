class Day1:Day {

    override fun part1(entries: List<String>):Long {
        var currentSum = 0L
        var maxSum = 0L
        entries.forEach { line ->
            when(line.isNotEmpty()){
                true -> currentSum+=line.toLong()
                else -> {
                    if (currentSum>maxSum) maxSum=currentSum
                    currentSum = 0L
                }
            }
        }
        //We've reached the end of the file, process last line
        if (currentSum>maxSum) maxSum=currentSum

         return maxSum
    }

    override fun part2(entries: List<String>):Long {
        var currentSum = 0L
        var sums = mutableListOf<Long>()
        entries.forEach { line ->
            when(line.isNotEmpty()){
                true -> currentSum+=line.toLong()
                else -> {
                    sums.add(currentSum)
                    currentSum = 0L
                }
            }
        }
        //We've reached the end of the file, process last line
        sums.add(currentSum)

        return sums.sortedDescending().subList(0,3).sum()
    }

}