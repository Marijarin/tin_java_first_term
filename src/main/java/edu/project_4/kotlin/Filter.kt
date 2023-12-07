package edu.project_4.kotlin

import java.awt.image.BufferedImage

class Filter {
    fun deNoise(image: BufferedImage) {

        val width = image.width
        val height = image.height
        for (y in 1 until height - 1) {
            for (x in 1 until width - 1) {

                val p1 = image.getRGB(x, y)

                val a1 = (p1 shr 24) and 0xff
                val r1 = (p1 shr 16) and 0xff
                val g1 = (p1 shr 8) and 0xff
                val b1 = p1 and 0xff

                val p = Array(10) { 0 }
                val a = Array(10) { 0 }
                val r = Array(10) { 0 }
                val g = Array(10) { 0 }
                val b = Array(10) { 0 }

                a[9] = a1
                r[9] = r1
                g[9] = g1
                b[9] = b1

                p[1] = image.getRGB(x - 1, y - 1)
                p[2] = image.getRGB(x, y - 1)
                p[3] = image.getRGB(x + 1, y - 1)
                p[4] = image.getRGB(x - 1, y)
                p[5] = image.getRGB(x + 1, y)
                p[6] = image.getRGB(x - 1, y + 1)
                p[7] = image.getRGB(x, y + 1)
                p[8] = image.getRGB(x + 1, y + 1)

                a[1] = (p[1] shr 24) and 0xff
                a[2] = (p[2] shr 24) and 0xff
                a[3] = (p[3] shr 24) and 0xff
                a[4] = (p[4] shr 24) and 0xff
                a[5] = (p[5] shr 24) and 0xff
                a[6] = (p[6] shr 24) and 0xff
                a[7] = (p[7] shr 24) and 0xff
                a[8] = (p[8] shr 24) and 0xff

                r[1] = (p[1] shr 16) and 0xff
                r[2] = (p[2] shr 16) and 0xff
                r[3] = (p[3] shr 16) and 0xff
                r[4] = (p[4] shr 16) and 0xff
                r[5] = (p[5] shr 16) and 0xff
                r[6] = (p[6] shr 16) and 0xff
                r[7] = (p[7] shr 16) and 0xff
                r[8] = (p[8] shr 16) and 0xff


                g[1] = (p[1] shr 8) and 0xff
                g[2] = (p[2] shr 8) and 0xff
                g[3] = (p[3] shr 8) and 0xff
                g[4] = (p[4] shr 8) and 0xff
                g[5] = (p[5] shr 8) and 0xff
                g[6] = (p[6] shr 8) and 0xff
                g[7] = (p[7] shr 8) and 0xff
                g[8] = (p[8] shr 8) and 0xff

                b[1] = (p[1]) and 0xff
                b[2] = (p[2]) and 0xff
                b[3] = (p[3]) and 0xff
                b[4] = (p[4]) and 0xff
                b[5] = (p[5]) and 0xff
                b[6] = (p[6]) and 0xff
                b[7] = (p[7]) and 0xff
                b[8] = (p[8]) and 0xff

                for (k in 1 until 10) {
                    for (l in k + 1 until 10) {
                        if (p[k] > p[l]) {
                            val t = p[k]
                            p[k] = p[l]
                            p[l] = t
                        }
                        if (a[k] > a[l]) {
                            val t = a[k]
                            a[k] = a[l]
                            a[l] = t
                        }
                        if (r[k] > r[l]) {
                            val t = r[k]
                            r[k] = r[l]
                            r[l] = t
                        }
                        if (g[k] > g[l]) {
                            val t = g[k]
                            g[k] = g[l]
                            g[l] = t
                        }

                        if (b[k] > b[l]) {
                            val t = b[k]
                            b[k] = b[l]
                            b[l] = t
                        }
                    }
                }

                val pp = (a[5] shr 24) or (r[5] shl 16) or (g[5] shl 8) or b[5]
                image.setRGB(x, y, pp)

            }

        }
    }
}
