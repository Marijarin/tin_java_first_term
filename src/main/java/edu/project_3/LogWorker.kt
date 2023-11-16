package edu.project_3

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.regex.Matcher
import java.util.regex.Pattern

class LogWorker {
    private val filePath = Path.of("/logs.txt")
    fun logAnalytics(filePath: Path, from: LocalDate, to: LocalDate, format: OutFormat = OutFormat.CONSOLE) {
        val logRecords = makeLogRecords(fileToStringList(filePath))

        val numberOfResponses = calculateNumberOfResponses(logRecords, from, to)
        val mostFrequentResources = mostFrequentResources(logRecords, from, to)
        val mostFrCodes = mostFrequentCodes(logRecords, from, to)
        val frCodesQuantity = codesStats(logRecords, from, to)
        val responseAverageSize = averageResponseBytes(logRecords, from, to)
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
        val list = mutableListOf<String>()
        try {
            val inChannel = FileChannel.open(filePath)
            inChannel.use {
                val byteBuffer = ByteBuffer.allocate(64)
                val sb = StringBuilder()
                var bytesRead = inChannel.read(byteBuffer)
                while (bytesRead != -1) {
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        when (val c = byteBuffer.getChar()) {
                            '\n' -> {
                                if (sb.isNotEmpty() && sb.isNotBlank()) {
                                    list.add(sb.toString())
                                    sb.delete(0, sb.length)
                                }
                            }

                            else -> {
                                sb.append(c)
                            }
                        }
                        byteBuffer.clear();
                        bytesRead = inChannel.read(byteBuffer)
                    }
                }
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return list.toList()
    }

    private fun makeLogRecords(list: List<String>): List<LogRecord> {
        val logRecords = mutableListOf<LogRecord>()
        val pattern = Pattern.compile("^(?<ip>(\\d+\\.\\d+\\.\\d+\\.\\d+))...(?<remoteUser>.+).(\\[(?<timestamp>(.+))\\]).(\\\"(?<request>(.+\\/.+\\/.+\\/\\d\\.\\d))\\\").(?<code>\\d{3}).(?<bytes>\\d+).(\\\"(?<resourceRequested>.+)\\\").(\\\"(?<userAgent>.+)\\\")")
        for (string in list) {
            val m = pattern.matcher(string)
            if (m.matches()) {
                val logRec = LogRecord (
                    remoteAddress = m.group("ip"),
                    remoteUser = m.group("remoteUser"),
                    dateLocal = LocalDate.parse(m.group("timestamp")),
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

    private fun calculateNumberOfResponses(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Int {

    }

    private fun mostFrequentResources(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<String, Int> {

    }

    private fun mostFrequentCodes(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<Int, String> {

    }

    private fun codesStats(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Map<Int, Int> {

    }

    private fun averageResponseBytes(logRecords: List<LogRecord>, from: LocalDate, to: LocalDate): Int {

    }

}
