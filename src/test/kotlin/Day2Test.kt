import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day2Test {

    val expectedResultForSample1 = 15L
    val expectedResultForSample2 = 12L
    val expectedResultFor1 = 12772L
    val expectedResultFor2 = 11618L

    @Test
    fun partSample1() {
        val fileEntries = Day2().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day2.txt")
        val result = Day2().part1(fileEntries)
        assertEquals(expectedResultForSample1,result)
    }

    @Test
    fun partSample2() {
        val fileEntries = Day2().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day2.txt")
        val result = Day2().part2(fileEntries)
        assertEquals(expectedResultForSample2,result)
    }

    @Test
    fun part1() {
        val fileEntries = Day2().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/main/resources/Day2.txt")
        val result = Day2().part1(fileEntries)
        assertEquals(expectedResultFor1,result)
    }

    @Test
    fun part2() {
        val fileEntries = Day2().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/main/resources/Day2.txt")
        val result = Day2().part2(fileEntries)
        assertEquals(expectedResultFor2,result)
    }
}