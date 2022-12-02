import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2Test {

    val expectedResultFor1 = 15L
    val expectedResultFor2 = 12L

    @Test
    fun part1() {
        val fileEntries = Day2().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day2.txt")
        val result = Day2().part1(fileEntries)
        assertEquals(expectedResultFor1,result)
    }

    @Test
    fun part2() {
        val fileEntries = Day2().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day2.txt")
        val result = Day2().part2(fileEntries)
        assertEquals(expectedResultFor2,result)
    }
}