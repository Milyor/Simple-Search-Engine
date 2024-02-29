package search
import java.io.File

fun main(args: Array<String>) {
    val users = mutableMapOf<String, MutableList<Int>>()
    if (args.contains("--data")){
        // Read lines form the text file
        val line = File(args.last()).readLines()
        for ((lineNumber, item) in line.withIndex()){
            val tokens = item.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})")
            for (token in tokens) {
                if (token.contains("@")){
                    users.getOrPut(token) { mutableListOf() }.add(lineNumber)
                } else {
                    users.getOrPut(token) { mutableListOf() }.add(lineNumber)
                }
            }
        }
    }

    while (true) {
        println("""
                === Menu === 
                1. Find a person
                2. Print all people
                0. Exit 
                """.trimIndent()
        )
        var action = readln().toInt()
        when(action){
            1 -> newSearch(users)
            2 -> printAll(users)
            0 -> {
                println("\nBye!")
                return
            }
            else -> {
                println("\nIncorrect option! Try again.")
                continue
            }
        }
    }
}

private fun printAll(users: Map<String, MutableList<Int>>) {
    println("\n=== List of people ===")
    for ((token, _ ) in users){
        println(token)
    }
}

private fun newSearch(users: Map<String, MutableList<Int>>){
    println("\nSelect a matching search strategy: ALL, ANY, NONE")
    when(val input = readln().uppercase()){
        "ANY" ->{
            println("\nEnter a name or email to search all suitable people.")
            // Takes new input to search
            val newInput = readln().lowercase().split(" ")
            // Makes sure that it's not just 1 character
            error(newInput)
            // Check if there is just one name
            val filteredResults = newInput.map { input ->
                users.keys.filter { it.lowercase().contains(input) }
            }

            val result = filteredResults.reduceOrNull { acc, set -> acc.union(set.toSet()).toList() }
            if (result.isNullOrEmpty()){
                println("No matching people found.")
            } else{
                println("${result.size} ${if (result.size == 1) "person" else "persons"} found:")
                result.forEach{ println(it) }
            }
        }
        "ALL" ->{
            println("\nEnter a name or email to search all suitable people.")
            val newInput = readln().lowercase().split(" ")
            error(newInput)

            // Filter the results for each word in the input
            val filteredResults = newInput.map { _ ->
                users.keys.filter { it.lowercase().contains(input) }
            }

            // Find the intersection of all filtered results
            val intersection = filteredResults.reduceOrNull { acc, list -> acc.intersect(list.toSet()).toList() }

            if (intersection.isNullOrEmpty()) {
                println("No matching people found.")
            } else {
                println("${intersection.size} ${if (intersection.size == 1) "person" else "persons"} found:")
                intersection.forEach { println(it) }
            }
            }

        "NONE" -> {
            println("\nEnter a name or email to search all suitable people.")
            val newInput = readln().lowercase().split(" ")
            error(newInput)
            val usersCopy = users.toMutableMap()
            if (newInput[1].isEmpty()) {
                usersCopy.entries.removeIf { it.key.lowercase().contains(newInput[0]) }
                printAll(usersCopy)
            } else {
                usersCopy.entries.removeIf { it.key.lowercase().contains(newInput[0]) }
                usersCopy.entries.removeIf { it.key.lowercase().contains(newInput[1]) }
                printAll(usersCopy)
            }
        }
        else -> return
    }
}

private fun error(input: List<String>){
    if (input[0].length == 1) {
        println("No matching people found.")
        return
    }
}