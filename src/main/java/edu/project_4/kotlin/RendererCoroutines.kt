package edu.project_4.kotlin

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.image.BufferedImage
import java.io.FileOutputStream
import javax.imageio.ImageIO


val image = BufferedImage(2500, 2500, BufferedImage.TYPE_INT_RGB)

fun main(): Unit = runBlocking {
    val start = System.currentTimeMillis()
    repeat(1_000_000) {
        val job1 = launch {
            val h = HistogramMaker()
            h.makeHistogram2()
            h.deleteFirst()
            for (point in h.resize(image.width)) {
                image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
            }
        }
        job1.join()
    }
//    val blur = Blur()
//    val blurred = blur.blur(image)
    FileOutputStream("result15.png").use { out ->
        ImageIO.write(image, "png", out)
    }
    val end = System.currentTimeMillis()
    println((end - start) / 1000)
}

