package edu.project_3

import java.io.File
import java.lang.StringBuilder
import java.nio.file.Files
import java.time.LocalDate
import java.util.SortedMap
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
            OutFormat.MARCDOWN -> makeMarcDown()
            OutFormat.CONSOLE -> println(prettyPrint())
        }
    }

    fun prettyPrint() =
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
            """

    //`${}`
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
                sb.append("""|    ${String.format("%-10s", entry.key)}   |  ${String.format("%-10s", entry.value)}|""")
                sb.append("\n")
            }
        } else {
            for (i in 0..maxSize) {
                val entry = this.resources.pollFirstEntry()
                sb.append("""|    ${String.format("%-10s", entry.key)}   |  ${String.format("%-10s", entry.value)}|""")
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    private fun formatTwoMaps(): String {
        val sb = StringBuilder()
        sb.append("""#### Коды ответа

| Код |          Имя          | Количество |
|:---:|:---------------------:|-----------:|
""")
        for (entry in codeNames) {
            sb.append("| ${entry.key} |    ${String.format("%-15s", entry.value)}    |  ${String.format("%-9s", quantityOfCodes.getValue(entry.key))} |")
            sb.append("\n")
        }
        return sb.toString()
    }

    private fun makeMarcDown() {
        Files.deleteIfExists(Path("report_${this.fileName}.md"))
        val file = Files.createFile(Path("report_${this.fileName}.md"))
        file.writeText(prettyPrint())
    }
}
