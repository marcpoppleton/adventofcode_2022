fun main(args: Array<String>) {
    if(args.isEmpty()){
        println("Usage is java -jar advent.jar day_number arguments_for_daily_challenge")
        return
    }
    when(args[0].toInt()){
        1 -> Day1().main(args)
        2 -> Day2().main(args)
        else -> println("${args[0]} is not a valid day number.")
    }
}