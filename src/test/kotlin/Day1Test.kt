import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1Test {

    val expectedResultForSample1 = 24000L
    val expectedResultForSample2 = 45000L
    val expectedResultFor1 = 71924L
    val expectedResultFor2 = 210406L

    @Test
    fun partsample1() {
        val fileEntries = Day1().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day1.txt")
        val result = Day1().part1(fileEntries)
        assertEquals(expectedResultForSample1,result)
    }

    @Test
    fun partsample2() {
        val fileEntries = Day1().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/test/resources/Day1.txt")
        val result = Day1().part2(fileEntries)
        assertEquals(expectedResultForSample2,result)
    }

    fun part1() {
        val fileEntries = Day1().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/main/resources/Day1.txt")
        val result = Day1().part1(fileEntries)
        assertEquals(expectedResultFor1,result)
    }

    @Test
    fun part2() {
        val fileEntries = Day1().getEntries("/Users/marcpoppleton/Code/Adventofcode_2022/src/main/resources/Day1.txt")
        val result = Day1().part2(fileEntries)
        assertEquals(expectedResultFor2,result)
    }
}