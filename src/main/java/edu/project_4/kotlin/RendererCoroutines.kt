package edu.project_4.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.FileOutputStream
import javax.imageio.ImageIO


val image = BufferedImage(3500, 3500, BufferedImage.TYPE_INT_RGB)

suspend fun main(): Unit = coroutineScope{
    val start = System.currentTimeMillis()
    repeat(10_000) {
        launch (Dispatchers.Default){
            val h = HistogramMaker()
            h.makeHistogram()
            h.deleteFirst()
            for (point in h.resize(2000, image.width)) {
                image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
            }
        }
    }
    launch (Dispatchers.Default) {
        val blur = Blur()
        val blurred = blur.blur(image)
        withContext(Dispatchers.IO) {
            FileOutputStream("result1.png").use { out ->
                ImageIO.write(blurred, "png", out)
            }
        }
        val end = System.currentTimeMillis()
        println((end - start) / 1000)
    }
}

