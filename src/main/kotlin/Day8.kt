import kotlin.String as String1

class Day8 : Day {

    private lateinit var forest: Array<List<Tree>?>


    override fun part1(entries: List<String1>): Int {
        parseData(entries)
        return forest.sumOf { row -> row?.filter { tree -> tree.visible }?.size ?: 0 }
    }

    override fun part2(entries: List<String1>): Int {
        parseData(entries)
        return forest.maxOfOrNull { row -> row?.maxBy { tree -> tree.scenicScore }?.scenicScore ?: 0 } ?: 0
    }

    private fun parseData(entries: List<String1>) {
        val linesRange = 0 + 1 until entries.size - 1
        var columns = 0
        entries.forEachIndexed { lineIndex, line ->
            if (lineIndex == 0) {
                forest = arrayOfNulls(line.length)
                columns = line.length
            }
            val colRange = 0 + 1 until columns - 1
            forest[lineIndex] = line.toCharArray().mapIndexed { charIndex, char ->
                val defaultTreeVisibility = !(linesRange.contains(lineIndex) && colRange.contains(charIndex))
                Tree(Integer.parseInt(char.toString()), defaultTreeVisibility)
            }
            if (linesRange.contains(lineIndex)) findVisibleTreesInRow(lineIndex)
            calculateHorizontalViewingScores(lineIndex)
        }

        for (i in 1 until columns - 1) {
            findVisibleTreesInColumn(i)
            calculateVerticalViewingScores(i)
        }
    }

    private fun findVisibleTreesInRow(rowIndex: Int) {
        forest[rowIndex]?.let { row ->
            row.forEachIndexed { treeIndex, _ ->
                val colRange = 0 + 1 until row.lastIndex
                if (colRange.contains(treeIndex)) {
                    //split around the tree
                    val leftPart = row.subList(0, treeIndex + 1)
                    val rightPart = row.subList(treeIndex, row.lastIndex + 1)
                    //find the highest from the left side
                    val highestFromLeftIndex = leftPart.withIndex().maxBy { (_, tree) -> tree.height }
                    //find the highest from the right side
                    val highestFromRightIndex = rightPart.withIndex().reversed().maxBy { (_, tree) -> tree.height }
                    val li = highestFromLeftIndex.index
                    val ri = highestFromRightIndex.index + treeIndex
                    //is it visible from the left?
                    if (li == treeIndex) {
                        forest[rowIndex]?.get(li)!!.visible = true
                    }
                    //is it visible from the right?
                    if (ri == treeIndex) {
                        forest[rowIndex]?.get(ri)!!.visible = true
                    }

                }
            }

        }

    }

    private fun findVisibleTreesInColumn(columnIndex: Int) {
        forest.column(columnIndex).let { column ->
            column.forEachIndexed { treeIndex, _ ->
                val rowRange = 0 + 1 until column.lastIndex
                if (rowRange.contains(treeIndex)) {
                    //split around the current tree
                    val abovePart = column.subList(0, treeIndex + 1)
                    val belowPart = column.subList(treeIndex, column.lastIndex + 1)
                    //find the highest from above
                    val highestFromAboveIndex = abovePart.withIndex().maxBy { (_, tree) -> tree!!.height }
                    //find the highest from below
                    val highestFromBelowIndex = belowPart.withIndex().reversed().maxBy { (_, tree) -> tree!!.height }
                    val ai = highestFromAboveIndex.index
                    val bi = highestFromBelowIndex.index + treeIndex
                    //is it visible from above?
                    if (ai == treeIndex) {
                        forest[ai]?.get(columnIndex)!!.visible = true
                    }
                    //is it visible from below?
                    if (bi == treeIndex) {
                        forest[bi]?.get(columnIndex)!!.visible = true
                    }
                }
            }
        }
    }

    private fun calculateHorizontalViewingScores(rowIndex: Int) {
        forest[rowIndex]?.let { row ->
            row.forEachIndexed { treeIndex, currentTree ->
                //split around the current tree
                val leftPart = row.subList(0, treeIndex)
                val rightPart = row.subList(treeIndex + 1, row.lastIndex + 1)
                //find the highest from the left side, defaults to 0
                val blockingFromLeftIndex = if (treeIndex != 0) {
                    leftPart.withIndex().reversed().find { (_, tree) -> tree.height >= currentTree.height }?.index
                        ?: 0
                } else {
                    0
                }
                //find the highest from the right side, default to last index
                val blockingFromRightIndex = if (treeIndex != row.lastIndex) {
                    rightPart.withIndex().find { (_, tree) -> tree.height >= currentTree.height }?.index
                        ?: rightPart.lastIndex
                } else {
                    rightPart.lastIndex
                }
                //left score is difference between tree index and the index of the first blocking tree on the left
                val leftScore = (treeIndex - blockingFromLeftIndex)
                //right score is the index of the first blocking tree on the right plus one
                val rightScore = (blockingFromRightIndex + 1)
                //horizontal score if product of both scores
                forest[rowIndex]?.get(treeIndex)?.horizontalScenicScore = leftScore * rightScore
            }
        }
    }

    private fun calculateVerticalViewingScores(columnIndex: Int) {
        forest.column(columnIndex)?.let { column ->
            column.forEachIndexed { treeIndex, currentTree ->
                val rowRange = 0 + 1 until column.lastIndex
                if (rowRange.contains(treeIndex)) {
                    //split around current tree
                    val abovePart = column.subList(0, treeIndex)
                    val belowPart = column.subList(treeIndex + 1, column.lastIndex + 1)
                    //find the highest from above, defaults to 0
                    val blockingFromAboveIndex = if (treeIndex != 0) {
                        abovePart.withIndex().reversed()
                            .find { (index, tree) -> tree!!.height >= currentTree!!.height }?.index ?: 0
                    } else {
                        0
                    }
                    //find the highest from below, defaults to last index
                    val blockingFromBelowIndex = if (treeIndex != column.lastIndex) {
                        belowPart.withIndex().find { (index, tree) -> tree!!.height >= currentTree!!.height }?.index
                            ?: belowPart.lastIndex
                    } else {
                        belowPart.lastIndex
                    }
                    //above score is difference between tree index and index of the first blocking tree from above
                    val aboveScore = (treeIndex - blockingFromAboveIndex)
                    //below score is the index of the first blocking tree from below plus one
                    val belowScore = (blockingFromBelowIndex + 1)
                    //vertical score is the product of both scores
                    forest[treeIndex]?.get(columnIndex)?.verticalScenicScore = aboveScore * belowScore
                }
            }
        }
    }
}
    fun Array<List<Tree>?>.column(col: Int): List<Tree?> {
        return this.map { row -> row?.get(col) }
    }


data class Tree(
    val height: Int,
    var visible: Boolean,
    var horizontalScenicScore: Int = 0,
    var verticalScenicScore: Int = 0
) {
    override fun toString(): String1 = height.toString()

    val scenicScore: Int
        get() = horizontalScenicScore * verticalScenicScore
}