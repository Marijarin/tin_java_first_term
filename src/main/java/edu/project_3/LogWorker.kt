package edu.project_3

import java.nio.file.Path
import java.time.LocalDate
import java.util.regex.Pattern

class LogWorker {
    private val testUrl = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
    fun logAnalytics(filePath: Path, from: LocalDate, to: LocalDate, format: OutFormat = OutFormat.CONSOLE) {
        val numberOfResponses = calculateNumberOfResponses(filePath, from, to)
        val mostFrequentResources = mostFrequentResources(filePath, from, to)
        val mostFrCodes = mostFrequentCodes(filePath, from, to)
        val frCodesQuantity = codesStats(filePath, from, to)
        val responseAverageSize = averageResponseBytes(filePath, from, to)
        val logReport = LogReport(
            filePath = filePath,
            startDate = from,
            endDate = to,
            numberOfRequests = numberOfResponses,
            averageResponseSize = responseAverageSize,
            resources = mostFrequentResources,
            codeNames = mostFrCodes,
            quantityOfCodes = frCodesQuantity,
        )
        logReport.formatReport(format)
    }
    private fun fileToStringList(filePath: Path): List<String> {

    }
    private fun makeLogRecords(list: List<String>): List <LogRecord> {
        val pattern = Pattern.compile("^(?<ip>(\\d+\\.\\d+\\.\\d+\\.\\d+))...(?<remoteUser>.+).(\\[(?<timestamp>(.+))\\]).(\\\"(?<request>(.+\\/.+\\/.+\\/\\d\\.\\d))\\\").(?<code>\\d{3}).(?<bytes>\\d+).(\\\"(?<resourceRequested>.+)\\\").(\\\"(?<userAgent>.+)\\\")")
    }

    private fun calculateNumberOfResponses(filePath: Path, from: LocalDate, to: LocalDate): Int {

    }

    private fun mostFrequentResources(filePath: Path, from: LocalDate, to: LocalDate): Map<String, Int> {

    }

    private fun mostFrequentCodes(filePath: Path, from: LocalDate, to: LocalDate): Map<Int, String> {

    }

    private fun codesStats(filePath: Path, from: LocalDate, to: LocalDate): Map<Int, Int> {

    }

    private fun averageResponseBytes(filePath: Path, from: LocalDate, to: LocalDate): Int {

    }

}
