package edu.project_4.kotlin

import java.awt.image.BufferedImage
import java.io.FileOutputStream
import javax.imageio.ImageIO

fun main() {
    val image = BufferedImage(3500, 3500, BufferedImage.TYPE_INT_RGB)

    val samples = 10000
    val start = System.currentTimeMillis()
    for (i in 1..samples) {
        val h = HistogramMaker()
        h.makeHistogram()
        h.deleteFirst()
        for (point in h.resize(2000, 3500)) {
            image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
        }
    }
    val blur = Blur()
    val blurred = blur.blur(image)
    FileOutputStream("result16.png").use { out ->
        ImageIO.write(blurred, "png", out)
    }
    val end = System.currentTimeMillis()
    println((end - start) / 1000)
}
