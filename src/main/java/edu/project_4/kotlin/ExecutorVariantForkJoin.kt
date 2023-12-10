package edu.project_4.kotlin

import java.awt.image.BufferedImage
import java.io.FileOutputStream
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import javax.imageio.ImageIO

class ExecutorVariantForkJoin {
    fun withForkJoin(samples: Int, portion: Int, image: BufferedImage) {
        val executorService = Executors.newWorkStealingPool(8)
        val latch = CountDownLatch(portion)
        val tasks = mutableListOf<Runnable>()

        for (i in 1..portion) {
            tasks.add(Runnable {
                val h = HistogramMaker()
                val all = ArrayList<Point>()
                for (j in 1..samples / portion) {
                    h.makeHistogram2()
                    h.deleteFirst()
                    val endPix3 = h.resize(image.width)
                    all.addAll(endPix3)
                }
                synchronized(this) {
                    for (point in all) {
                        image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
                    }

                }
                latch.countDown()
            })
        }
        for (task1 in tasks) {
            executorService.submit(task1)
        }
        try {

            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val blur = Blur()
        executorService.submit {
            blur.blur(image)
        }
        executorService.shutdown()
        FileOutputStream("result14.png").use { out ->
            ImageIO.write(image, "png", out)
        }

    }
}
