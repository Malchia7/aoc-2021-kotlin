fun main() {
    fun part1(input: List<String>): Int {
        var increments = 0;
        var lastNumber = input.first().toInt()
        for(i in 1 until input.size){
            val number = input[i].toInt()
            if(number > lastNumber){
                increments++
            }
            lastNumber = number
        }
        return increments
    }

    data class ThreeValueSlidingWindow(var first: Int, var second: Int, var third: Int)

    fun part2(input: List<String>): Int {
        var increments = 0
        val listOfSlidingWindows = mutableListOf<ThreeValueSlidingWindow>()
        for(line in input){
            val number = line.toInt()
            for(t in 0 until listOfSlidingWindows.size){
                val window = listOfSlidingWindows[t]
                if(window.second == -1){
                    window.second = number
                } else if (window.third == -1){
                    window.third = number
                    // this window is now complete, so we should be able to compare it to the one before, as long as its not the first
                    if(t != 0){
                        val lastWindow = listOfSlidingWindows[t-1]
                        if(lastWindow.first + lastWindow.second + lastWindow.third < window.first + window.second + window.third){
                            increments++
                        }
                    }
                }
            }
            listOfSlidingWindows.add(ThreeValueSlidingWindow(number, -1, -1))
            if(listOfSlidingWindows.size >= 2 && listOfSlidingWindows[1].third != -1){
                // if the second window is complete, we're done with the first, and we
                // can drop it to keep our space constant
                listOfSlidingWindows.removeFirst()
            }
        }
        return increments
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
