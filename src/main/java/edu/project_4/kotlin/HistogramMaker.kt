package edu.project_4.kotlin

import java.util.concurrent.ThreadLocalRandom

class HistogramMaker {
    private val iterations = 5000

    private val points = mutableListOf<Point>()

    private val linearList = listOf(
        Point::makeLinear2,
        Point::makeLinear3,
        Point::makeLinear2,
        Point::makeLinear2,
        Point::makeLinear4
    )

    private val nonLinearList = listOf(
        Point::makeSpiral,
        Point::makeSwirl,
        Point::makeSwirl,
        Point::makeSin,
        Point::makeSphere,
        Point::makeSpiral
    )

    fun makeHistogram() {
        val x1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val y1 = ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        val first = Point(x1, y1, RGB(0.1, 0.1, 0.1))
        points.clear()
        points.add(first)
        for (i in 1..iterations) {
            var next = points[i - 1]
            val linChoice = ThreadLocalRandom.current().nextInt(0, 5)
            val funLinChosen = linearList[linChoice]
            val nonLinChoice = ThreadLocalRandom.current().nextInt(0, 6)
            val funNonLinChosen = nonLinearList[nonLinChoice]
            next = funNonLinChosen.invoke(funLinChosen.invoke(next)).copy(color = RGB(0.1*nonLinChoice, 0.7*nonLinChoice, 0.1*linChoice))
            points.add(next.makeLinear4().makeSin().makeSwirl())
            points.add(next.rotate(180.0).makeLinear4().makeSin().makeSwirl())
        }
    }

    fun deleteFirst() {
        for (i in 0..20) {
            points.removeAt(i)
        }
    }

    fun resize(dim: Int, size: Int): List<Point> {
        val newList = points
            .map {
                it.copy(x = it.x * dim / 2 + size / 2, y = it.y * dim / 2 + size / 2)
            }
        return newList.filter { it.x < size && it.x > 0 && it.y > 0 && it.y < size }
    }

}

