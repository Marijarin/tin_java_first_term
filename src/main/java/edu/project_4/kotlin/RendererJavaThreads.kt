package edu.project_4.kotlin

import java.awt.image.BufferedImage

fun main() {
    val image = BufferedImage(2500, 2500, BufferedImage.TYPE_INT_RGB)

    val samples = 1_000_000
    val executor = ExecutorVariantForkJoin()
    executor.withForkJoin(samples, image)
}
