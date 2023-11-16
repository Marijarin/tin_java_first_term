package edu.project_3

import java.time.LocalDate

data class LogRecord(
    val remoteAddress: String,
    val remoteUser: String,
    val dateLocal: LocalDate,
    val request: String,
    val code: Int,
    val bytesSent: Int,
    val httpReferer: String,
    val userAgent: String,
    )
