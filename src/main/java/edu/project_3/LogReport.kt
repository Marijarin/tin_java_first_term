package edu.project_3

import java.nio.file.Files
import java.time.LocalDate
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.writeText

data class LogReport(
    val fileName: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val numberOfRequests: Int,
    val averageResponseSize: Int,
    val resources: SortedMap<String, Int>,
    val codeNames: Map<Int, String>,
    val quantityOfCodes: Map<Int, Int>,
    val successfulAndFailedResponses: Map<String, Int>,
    val mostFrequentUAgent: Pair<String, Int>,
) {
    fun formatReport(outFormat: OutFormat) {
        when (outFormat) {
            OutFormat.CONSOLE_PLAIN -> println(this)
            OutFormat.MARKDOWN -> makeMarcDown()
            OutFormat.CONSOLE -> println(prettyPrint())
        }
    }

    private fun prettyPrint() =
        """#### Общая информация
|        Метрика            |     Значение |
|:-------------------------:|-------------:|
| Файл(-ы)                  |   ${String.format("%-10s", this.fileName)} |
| Начальная дата            |   ${String.format("%-10s", this.startDate)} |
| Конечная дата             |   ${String.format("%-10s", this.endDate)} |
| Количество запросов       |   ${String.format("%-10s", this.numberOfRequests)} |
| Средний размер ответа, b  |   ${String.format("%-10s", this.averageResponseSize)} |

${formatMap()}
${formatTwoMaps()}
${showCodesStats()}
${showUserAgentMostFreq()}
            """

    private fun formatMap(): String {
        val sb = StringBuilder()
        sb.append(
            """#### Запрашиваемые ресурсы

|     Ресурс      | Количество |
|:---------------:|-----------:|"""
        )
        sb.append("\n")
        val maxSize = 3
        if (this.resources.size <= maxSize) {
            for (i in 0 until this.resources.size) {
                val entry = this.resources.pollFirstEntry()
                sb.append("""|    ${entry.key.format("%-10s")}   |  ${entry.value.toString().format("%-10s")}  |""")
                sb.append("\n")
            }
        } else {
            for (i in 0..maxSize) {
                val entry = this.resources.pollFirstEntry()
                sb.append("""|    ${entry.key.format("%-10s")}   |  ${entry.value.toString().format("%-10s")}|""")
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    private fun formatTwoMaps(): String {
        val sb = StringBuilder()
        sb.append(
            """#### Коды ответа

| Код |          Имя          | Количество |
|:---:|:---------------------:|-----------:|
"""
        )
        for (entry in codeNames) {
            sb.append(
                "| ${entry.key} |    ${entry.value.format("%-10s")}}    |  ${
                    String.format(
                        "%-9s",
                        quantityOfCodes.getValue(entry.key)
                    )
                } |"
            )
            sb.append("\n")
        }
        return sb.toString()
    }

    private fun makeMarcDown() {
        Files.deleteIfExists(Path("report_${this.fileName}.md"))
        val file = Files.createFile(Path("report_${this.fileName}.md"))
        file.writeText(prettyPrint())
    }

    private fun showUserAgentMostFreq(): String {
        val sb = StringBuilder()
        sb.append(
            """#### Самый распространенный user agent

|     user agent  | Частота    |
|:---------------:|-----------:|
"""
        )
        sb.append(
            """|    ${String.format("%-10s", mostFrequentUAgent.first)}   |  ${
                String.format(
                    "%-10s",
                    mostFrequentUAgent.second
                )
            }|"""
        )
        sb.append("\n")
        return sb.toString()
    }

    private fun showCodesStats(): String {
        val sb = StringBuilder()
        sb.append(
            """#### Сколько успешных и неуспешных

| Код |          Количество   |
|:---:|:---------------------:|
"""
        )
        for (entry in successfulAndFailedResponses) {
            sb.append("| ${entry.key} |    ${entry.value.toString().format("%-15s")}    |")
            sb.append("\n")
        }
        return sb.toString()
    }
}
