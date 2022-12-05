import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths

abstract class DayTest {

    abstract val testResultPart1:Any
    abstract val testResultPart2:Any
    abstract val runResultPart1:Any
    abstract val runResultPart2:Any
    abstract val day:String


    private fun getTestEntries(): List<String> {
        val filename =  Paths.get("").toAbsolutePath().toString() + "/src/test/resources/Day$day.txt"
        return File(filename).readLines()
    }
    private fun getRunEntries(): List<String> {
        val filename =  Paths.get("").toAbsolutePath().toString() + "/src/main/resources/Day$day.txt"
        return File(filename).readLines()
    }

    @Test
    fun partSample1() {
        val result = getDay(day).part1(getTestEntries())
        Assertions.assertEquals(testResultPart1, result)
    }

    @Test
    fun partSample2() {
        val result = getDay(day).part2(getTestEntries())
        Assertions.assertEquals(testResultPart2, result)
    }

    @Test
    fun part1() {
        val result = getDay(day).part1(getRunEntries())
        Assertions.assertEquals(runResultPart1, result)
    }

    @Test
    fun part2() {
        val result = getDay(day).part2(getRunEntries())
        Assertions.assertEquals(runResultPart2, result)
    }
}