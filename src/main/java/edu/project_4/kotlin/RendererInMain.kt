package edu.project_4.kotlin

import java.awt.image.BufferedImage
import java.io.FileOutputStream
import javax.imageio.ImageIO

fun main() {
    val image = BufferedImage(2500, 2500, BufferedImage.TYPE_INT_RGB)

    val samples = 1_000_000
    val start = System.currentTimeMillis()
    for (i in 1..samples) {
        val h = HistogramMaker()
        h.makeHistogram2()
        h.deleteFirst()
        for (point in h.resize(image.width)) {
            image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
        }
    }
    val blur = Blur()
    val blurred = blur.blur(image)
    FileOutputStream("result13.png").use { out ->
        ImageIO.write(blurred, "png", out)
    }
    val end = System.currentTimeMillis()
    println((end - start) / 1000)
}
