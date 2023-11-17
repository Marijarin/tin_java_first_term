package edu.project_3

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern


fun main() {
    val fileName = "logs.txt"
    val lw = LogWorker()
    val from = LocalDate.of(2015, 5, 18)
    lw.logAnalytics(fileName, from)
}

@Suppress("RegExpRedundantEscape")
class LogWorker {
    private val codes = mapOf(
        Pair(1, "informational response"),
        Pair(2, "successful"),
        Pair(3, "redirection"),
        Pair(4, "client error"),
        Pair(5, "server error")
    )

    fun logAnalytics(fileName: String, from: LocalDate, to: LocalDate = LocalDate.now(), format: OutFormat = OutFormat.CONSOLE) {
        val logRecords = makeLogRecords(fileToStringList(fileName))

        val numberOfResponses = calculateNumberOfRequests(logRecords, from, to)
        val mostFrequentResources = mostFrequentResources(logRecords, from, to)
        val mostFrCodes = mostFrequentCodes(logRecords, from, to)
        val frCodesQuantity = codesStats(logRecords, from, to)
        val responseAverageSize = averageResponseBytes(logRecords, from, to)
        val successfulAndFailed = errorAndNotErrorResponses(logRecords, from, to)
        val mostFreqUA = mostFrequentUA(logRecords, from, to)
        val logReport = LogReport(
            fileName = fileName,
            startDate = from,
            endDate = to,
            numberOfRequests = numberOfResponses,
            averageResponseSize = responseAverageSize,
            resources = mostFrequentResources.toSortedMap(),
            codeNames = mostFrCodes,
            quantityOfCodes = frCodesQuantity,
            successfulAndFailedResponses = successfulAndFailed,
            mostFrequentUAgent = mostFreqUA
        )
        logReport.formatReport(OutFormat.MARCDOWN)
    }

    private fun fileToStringList(fileName: String): List<String> {
        return File(fileName).useLines { it.toList() }
    }

    /*** In fun makeLogRecords the regular expression to get info from nginx log is used.
     *  It suits for all logs having scheme like that:
     *  '$remote_address - $remote_user \\[$time_local] ' '"$request" $status $body_bytes_sent ' '"$http_referer" "$http_user_agent"'
     *  All fields listed in the scheme are captured into pattern groups and are available as MatcherResult
     *  To check these groups in detail, please, visit https://regex101.com/
     * ***/
    private fun makeLogRecords(list: List<String>): List<LogRecord> {
        val logRecords = mutableListOf<LogRecord>()
        val pattern = Pattern.compile("^(?<ip>(\\d+\\.\\d+\\.\\d+\\.\\d+))...(?<remoteUser>.+).(\\[(?<timestamp>(.+))\\]).(\\\"(?<request>(.+\\/.+\\/.+\\/\\d\\.\\d))\\\").(?<code>\\d{3}).(?<bytes>\\d+).(\\\"(?<resourceRequested>.+)\\\").(\\\"(?<userAgent>.+)\\\")")
        for (string in list) {
            val m = pattern.matcher(string)
            if (m.matches()) {
                val dtf = DateTimeFormatter.ofPattern("dd/MMM/uuuu:HH:mm:ss Z", Locale.ENGLISH)
                val logRec = LogRecord(
                    remoteAddress = m.group("ip"),
                    remoteUser = m.group("remoteUser"),
                    dateLocal = LocalDate.parse(m.group("timestamp"), dtf),
                    request = m.group("request"),
                    code = m.group("code").toInt(),
                    bytesSent = m.group("bytes").toInt(),
                    httpReferer = m.group("resourceRequested"),
                    userAgent = m.group("userAgent"),
                )
                logRecords.add(logRec)
            }
        }
        return logRecords.toList()
    }

    private fun calculateNumberOfRequests(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Int {
        return logRecords.filter { it.dateLocal.isAfter(from) && it.dateLocal.isBefore(to) }.size
    }

    private fun mostFrequentResources(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<String, Int> {
        return logRecords.filter { logRecord ->
            logRecord.httpReferer.isNotBlank()
                && logRecord.httpReferer.isNotEmpty()
                && logRecord.dateLocal.isAfter(from)
                && logRecord.dateLocal.isBefore(to)
        }
            .groupingBy { it.httpReferer }.eachCount().toSortedMap()
    }

    private fun convertCode(code: Int): String {
        val strCode = codes[code / 100]
        if (strCode != null) {
            return strCode
        }
        return "code is unknown"
    }

    private fun mostFrequentCodes(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<Int, String> {
        return codesStats(logRecords, from, to).mapValues {
            convertCode(it.key)
        }
    }

    private fun codesStats(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<Int, Int> {
        return logRecords.filter { logRecord ->
            logRecord.dateLocal.isAfter(from)
                && logRecord.dateLocal.isBefore(to)
        }.groupingBy { it.code }.eachCount().toSortedMap()
    }

    private fun averageResponseBytes(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Int {
        return logRecords
            .filter { logRecord -> logRecord.bytesSent > 0 }
            .filter { lrd -> lrd.dateLocal.isAfter(from) && lrd.dateLocal.isBefore(to) }
            .map { it.bytesSent }
            .average().toInt()
    }

    private fun errorAndNotErrorResponses(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<String, Int> {
        return logRecords.filter { logRecord ->
            logRecord.dateLocal.isAfter(from)
                && logRecord.dateLocal.isBefore(to)
        }.groupingBy { explainCode(it.code) }.eachCount().toSortedMap()
    }

    private fun explainCode(code: Int) =
        when (code / 100) {
            1, 2, 3 -> "successful request"
            4, 5 -> "request failed"
            else -> "unknown code of response"
        }

    private fun mostFrequentUA(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Pair<String, Int> {
        return logRecords.filter { logRecord ->
            logRecord.dateLocal.isAfter(from)
                && logRecord.dateLocal.isBefore(to)
        }.groupingBy { it.userAgent }.eachCount().toSortedMap().maxBy { it.value }.toPair()
    }

}


