package edu.project_3

import java.time.LocalDateTime

data class LogRecord(
    val remoteAddress: String,
    val remoteUser: String,
    val timeLocal: LocalDateTime,
    val request: String,
    val code: Int,
    val bytesSent: Int,
    val httpReferer: String,
    val userAgent: String,
    )
