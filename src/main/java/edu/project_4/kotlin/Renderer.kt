package edu.project_4.kotlin

import java.awt.image.BufferedImage
import java.io.FileOutputStream
import javax.imageio.ImageIO

fun main() {
    val image = BufferedImage(2500, 2500, BufferedImage.TYPE_INT_RGB)
    val h = HistogramMaker()
    var i = 0
    while (i < 1_000_000) {
//         h.makeHistogram1()
//        h.deleteFirst()
//        val endP = h.resize(image.width)
//        for (j in endP.indices) {
//            image.setRGB(endP[j].x.toInt(), endP[j].y.toInt(), endP[j].color.value)
//        }
//      h.makeHistogram2()
//        h.deleteFirst()
//        val endP1 = h.resize(image.width)
//        for (j in endP1.indices) {
//            image.setRGB(endP1[j].x.toInt(), endP1[j].y.toInt(), endP1[j].color.value)
//
//        }
//        h.makeHistogram4()
//        h.deleteFirst()
//        val endP4 = h.resize(image.width)
//        for (j in endP4.indices) {
//            image.setRGB(endP4[j].x.toInt(), endP4[j].y.toInt(), endP4[j].color.value)
//      }
//        h.makeHistogram5()
//        h.deleteFirst()
//        val endP4 = h.resize(image.width)
//        for (j in endP4.indices) {
//            image.setRGB(endP4[j].x.toInt(), endP4[j].y.toInt(), endP4[j].color.value)
//        }

        h.makeHistogram3()
        h.deleteFirst()
        val endP2 = h.resize(image.width)
        for (j in endP2.indices) {
            image.setRGB(endP2[j].x.toInt(), endP2[j].y.toInt(), endP2[j].color.value)
        }
        i++
    }
//   val f = Filter()
//    f.deNoise(image)
    val blur = Blur()
    val i1 = blur.blur(image)
    FileOutputStream("result5.bmp").use { out ->
        ImageIO.write(i1, "bmp", out)
    }
}
