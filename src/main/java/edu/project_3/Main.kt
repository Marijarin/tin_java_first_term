package edu.project_3

import java.time.LocalDate

class Main {
    fun main(args: Array<String>) {
        if (args.isNotEmpty()) {
            LogWorker().logAnalytics(args[0], LocalDate.parse(args[1]), format = args[2])
        } else {
            LogWorker().logAnalytics("logs.txt", LocalDate.of(2015, 5, 16), format = "markdown")
        }
    }
}
