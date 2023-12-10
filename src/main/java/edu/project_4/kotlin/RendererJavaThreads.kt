package edu.project_4.kotlin

import java.awt.image.BufferedImage

fun main() {
    val image = BufferedImage(2500, 2500, BufferedImage.TYPE_INT_RGB)
    val samples = 1_000_000
    val executor = ExecutorVariantForkJoin()
    val start = System.currentTimeMillis()
    executor.withForkJoin(samples, 10000, image)
    val end = System.currentTimeMillis()
    println((end - start) / 1000)
}
