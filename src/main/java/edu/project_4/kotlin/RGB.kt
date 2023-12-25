package edu.project_4.kotlin

data class RGB(val r: Double, val g: Double, val b: Double) {
    val value: Int = toColorComponent(r, 16) + toColorComponent(g, 8) + toColorComponent(b, 0)

    private fun toColorComponent(number: Double, shift: Int): Int = (number * 255).toInt() shl shift
}
