package edu.project_3

import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import java.io.File

class Project3Test {
    private val logWorker = LogWorker()
    private val fileName = "test_logs.txt"
    private val from: LocalDate = LocalDate.of(2023, 5, 16)
    private val to: LocalDate = LocalDate.now()
    private val format = "markdown"

    @Test
    fun canMakeReportMD() {
        logWorker.logAnalytics(fileName, from, to, format)

        assertThat(File("report_test_logs.md").exists())
    }

    @Test
    fun canMakeStringsOfLogs() {
        val list = logWorker.fileToStringList(fileName)

        val firstLine = "178.62.69.228 - remote user 1 [19/May/2023:07:05:41 +0000]"

        assertThat(list.first).contains(firstLine)
    }

    @Test
    fun canMakeLogRecords() {
        val list = logWorker.fileToStringList(fileName)

        val logRecord1 = logWorker.makeLogRecords(list).first

        val logRecord = LogRecord(
            remoteAddress = "178.62.69.228",
            remoteUser = "remote user 1",
            dateLocal = LocalDate.of(2023, 5, 19),
            request = "GET /downloads/product_1 HTTP/1.1",
            code = 404,
            bytesSent = 335,
            httpReferer = "resource requested",
            userAgent = "user agent 1",
        )

        assertThat(logRecord1).isEqualTo(logRecord)
    }

    @Test
    fun canCalculateNumberOfRequests() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val number = logWorker.calculateNumberOfRequests(logRecords, from, to)

        assertThat(number).isEqualTo(2)
    }

    @Test
    fun findsMostFrequentResources() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val resource1 = logWorker.mostFrequentResources(logRecords, from, to).keys.first()

        assertThat(resource1).isEqualTo("other resource")
    }

    @Test
    fun findsMostFrequentCodes() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val code1 = logWorker.mostFrequentCodes(logRecords, from, to).entries.first().toPair()

        assertThat(code1).isEqualTo(Pair(304, "redirection"))
    }

    @Test
    fun canMakeCodeStats() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val code1 = logWorker.codesStats(logRecords, from, to).entries.first().toPair()

        assertThat(code1).isEqualTo(Pair(304, 1))
    }

    @Test
    fun findsAverageResponseBytes() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val averageBytes = logWorker.averageResponseBytes(logRecords, from, to)

        assertThat(averageBytes).isEqualTo(335)
    }

    @Test
    fun findsErrorAndNotErrorResponses() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val errorsAndNot = logWorker.errorAndNotErrorResponses(logRecords, from, to).entries.last().toPair()

        assertThat(errorsAndNot).isEqualTo(Pair("request failed", 1))
    }

    @Test
    fun findsMostFrequentUA() {
        val list = logWorker.fileToStringList(fileName)
        val logRecords = logWorker.makeLogRecords(list)
        val mostFrequentUserAgent = logWorker.mostFrequentUA(logRecords, from, to)

        assertThat(mostFrequentUserAgent).isEqualTo(Pair("user agent 1", 1))
    }

}
