package edu.project_4.kotlin

import java.awt.image.BufferedImage

fun main() {
    val image = BufferedImage(3500, 3500, BufferedImage.TYPE_INT_RGB)
    val samples = 10000
    val executor = ExecutorVariantForkJoin()
    val start = System.currentTimeMillis()
    executor.withForkJoin(samples, 100, image)
    val end = System.currentTimeMillis()
    println((end - start) / 1000)
}
