package edu.project_4.kotlin

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Point.makeLinear2(): Point {
    val newX = x * (-0.625568) + y * (-0.515804) + 0.620578
    val newY = x * (-0.616063) + y * 0 + 0.0682234
    return copy(x = newX, y = newY)
}

fun Point.makeLinear3(): Point {
    val newX = x * (-0.695157) + y * 0.00633793 + 0.0475732
    val newY = x * (-0.644204) + y * (-0.270785) + 0.892879
    return copy(x = newX, y = newY)
}
fun Point.makeLinear4(): Point {
    val newX = x * (-0.5) + y * 0.5
    val newY = x * (0.5) + y * (-0.270785)
    return copy(x = newX, y = newY)
}

fun Point.makeSin(): Point {
    val newX = sin(x)
    val newY = sin(y)
    return copy(x = newX, y = newY)
}

fun Point.makeSphere(): Point {
    val r2 = x.pow(2) + y.pow(2)
    val newX = x / r2
    val newY = y / r2
    return copy(x = newX, y = newY)
}

fun Point.rotate(angle: Double): Point {
    val theta = Math.toRadians(angle)
    val newX = x * cos(theta) - y * sin(theta)
    val newY = x * sin(theta) + y * cos(theta)
    return copy(x = newX, y = newY)
}

fun Point.makeSwirl(): Point {
    val r2 = x.pow(2) + y.pow(2)
    val newX = x * sin(r2) - y* cos(r2)
    val newY = x * cos(r2) + y* sin(r2)
    return copy(x = newX, y = newY)
}
fun Point.makeSpiral(): Point {
    val r = sqrt( x.pow(2) + y.pow(2))
    val newX =  (cos(Math.toRadians(41.0)) + sin(r))/r
    val newY = (sin(Math.toRadians(41.0)) - cos(r))/r
    return copy(x = newX, y = newY)
}




