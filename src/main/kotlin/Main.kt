fun main(args: Array<String>) {
    if(args.isEmpty()){
        println("Usage is java -jar advent.jar day_number")
        return
    }
    when(args[0].toInt()){
        1 -> Day1().main(args)
        2 -> Day2().main(args)
        3 -> Day3().main(args)
        4 -> Day4().main(args)
        5 -> Day5().main(args)
        6 -> Day6().main(args)
        7 -> Day7().main(args)
        8 -> Day8().main(args)
        else -> println("${args[0]} is not a valid day number.")
    }
}