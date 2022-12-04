import java.io.File
import java.nio.file.Paths

interface Day {


    fun main(args: Array<String>){
        try {
            val entries = getEntries(args[0])
            val start = System.currentTimeMillis()
            val part1 = part1(entries)
            val end = System.currentTimeMillis()
            println("Magic part 1 is : $part1 in ${end - start} millis")
            val start2 = System.currentTimeMillis()
            val part2 = part2(entries)
            val end2 = System.currentTimeMillis()
            println("Magic part 2 is : $part2 in ${end2 - start2} millis")
        } catch (e: java.io.FileNotFoundException) {
            println("${args[0]} is not a valid file.")
        }
    }

    fun part1(entries:List<String>):Long?
    fun part2(entries:List<String>):Long?

    fun getEntries(day: String): List<String> {
        val filename =  Paths.get("").toAbsolutePath().toString() + "/src/main/resources/Day${day}.txt"
        println("File to load : $filename")
        return File(filename).readLines()
    }

}
    fun getDay(day:String):Day{
        return Class.forName("Day$day").newInstance() as Day
    }
