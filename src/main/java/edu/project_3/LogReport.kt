package edu.project_3

import java.nio.file.Path
import java.time.LocalDate

data class LogReport(
    val fileName: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val numberOfRequests: Int,
    val averageResponseSize: Int,
    val resources: Map<String, Int>,
    val codeNames: Map<Int, String>,
    val quantityOfCodes: Map<Int, Int>,
)
fun LogReport.formatReport(outFormat: OutFormat){
    if (outFormat == OutFormat.CONSOLE) {
        println(this)
    } else if (outFormat == OutFormat.MARCDOWN){
        Unit
    }
}
