import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1Test {

    val expectedResultFor1 = 24000L
    val expectedResultFor2 = 45000L

    @Test
    fun part1() {
        val fileEntries = Day1().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day1.txt")
        val result = Day1().part1(fileEntries)
        assertEquals(expectedResultFor1,result)
    }

    @Test
    fun part2() {
        val fileEntries = Day1().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day1.txt")
        val result = Day1().part2(fileEntries)
        assertEquals(expectedResultFor2,result)
    }
}