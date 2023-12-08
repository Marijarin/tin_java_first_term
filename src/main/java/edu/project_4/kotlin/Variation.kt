package edu.project_4.kotlin

import kotlin.math.pow
import kotlin.math.sin


fun Point.makeLinear1(): Point {
    val newX = x * 0.134839 + y * 0.269271 - 0.287245
    val newY = x * 0.124935 + y * 1.40241 - 0.169667
    return copy(x = newX, y = newY)
}

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

fun Point.weight(weight: Double): Point {
    val newX = x * weight
    val newY = y * weight
    return copy(x = newX, y = newY)
}




