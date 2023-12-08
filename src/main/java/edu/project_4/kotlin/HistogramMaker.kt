package edu.project_4.kotlin

import java.lang.Math.pow
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

class HistogramMaker {
    private val iterations = 1000

    val points = mutableListOf<Point>()

    fun makeHistogram1() {
        val x1 = ThreadLocalRandom.current().nextDouble(-1.77, 1.77)
        val y1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val first = Point(x1, y1, RGB(0.8, 0.6, 0.4))
        points.clear()
        points.add(first)
        for (i in 1..iterations) {
            var next = points[i - 1]
            next = when {
                i % 2 == 0 -> next.makeLinear2().makeSin()
                i % 5 == 0 -> next.makeLinear1().makeSphere()
                i % 9 == 0 -> next.makeLinear3().makeSphere()
                else -> next.makeLinear3().makeSin()
            }

            next = next.copy(color = RGB(next.color.r + 0.01, next.color.b + 0.03, next.color.g + 0.04))
            points.add(next)
        }
    }

    fun makeHistogram2() {
        val x1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val y1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val first = Point(x1, y1, RGB(0.0, 0.3, 0.3))
        points.clear()
        points.add(first)

        for (i in 1..iterations) {
            var next = points[i - 1]
            next = when {
                i % 2 == 0 -> next.makeLinear2().makeSphere()
                i % 5 == 0 -> next.makeLinear2().makeSin()
                i % 3 == 0 -> next.makeLinear2()
                i % 7 == 0 -> next.makeLinear1().makeSin()
                else -> next.makeLinear2().makeSphere()
            }

            next = next.copy(color = RGB(next.color.r + 0.05, next.color.b + 0.05, next.color.g + 0.05))
            points.add(next)
        }
    }

    fun makeHistogram3() {
        val x1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val y1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val first = Point(x1, y1, RGB(0.7, 0.9, 0.1))
        points.clear()
        points.add(first)

        for (i in 1..iterations) {
            var next = points[i - 1]
            next = when {
                i % 3 == 0 -> next.makeLinear3().makeSphere()
                i % 11 == 0 -> next.makeLinear3().makeSin()
                i % 13 == 0 -> next.makeLinear3().makeSphere()

                else -> next.makeLinear2().makeSphere()
            }
            next = next.copy(color = RGB(next.color.r + 0.003, next.color.b + 0.008, next.color.g + 0.004))
            points.add(next)
        }
    }

    fun makeHistogram4() {
        val x1 = ThreadLocalRandom.current().nextDouble(-1.77, 1.77)
        val y1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val first = Point(x1, y1, RGB(0.7, 0.9, 0.1))
        points.clear()
        points.add(first)
        for (i in 1..iterations) {
            var next = points[i - 1]
            next = next.makeLinear1().makeSin()

            next = next.copy(color = RGB(next.color.r + 0.0001, next.color.b + 0.0005, next.color.g + 0.0004))
            points.add(next)
        }
    }

    fun makeHistogram5() {
        val x1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val y1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val first = Point(x1, y1, RGB(0.0, 0.9, 0.1))
        points.clear()
        points.add(first)
        for (i in 1..iterations) {
            var next = points[i - 1]
            next = next.makeSphere().makeLinear1()

            next = next.copy(color = RGB(next.color.r + 0.0001, next.color.b + 0.0005, next.color.g + 0.0004))
            points.add(next)
        }
    }

    fun deleteFirst() {
        for (i in 0..20) {
            points.removeAt(i)
        }
    }

    fun resize(dim: Int): List<Point> {
        return points.map {
            it.copy(x = abs(it.x * dim), y = abs(it.y * dim))
        }.filter { it.x < dim && it.y < dim }
    }

}

