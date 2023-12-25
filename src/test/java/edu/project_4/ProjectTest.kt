package edu.project_4

import edu.project_4.kotlin.Blur
import edu.project_4.kotlin.ExecutorVariantForkJoin
import edu.project_4.kotlin.HistogramMaker
import edu.project_4.kotlin.RGB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class ProjectTest {
    @Test
    fun makesImageNotBlack() {
        val image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)

        val samples = 10
        for (i in 1..samples) {
            val h = HistogramMaker()
            h.makeHistogram()
            h.deleteFirst()
            for (point in h.resize(image.width, image.width)) {
                image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
            }
        }

        assertThat((image.getRGB(0, 0) == RGB(0.0, 0.0, 0.0).value)).isFalse()
    }

    @Test
    fun makesNotBlackImageManyThreads() {
        val imageMany = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
        val samples = 10
        val executor = ExecutorVariantForkJoin()
        executor.withForkJoin(samples, 1, imageMany)

        FileOutputStream("resultTest2.png").use { out ->
            ImageIO.write(imageMany, "png", out)
        }

        val colorMany = Color(imageMany.getRGB(0, 0))

        assertThat(Color.BLACK).isNotEqualTo(colorMany)
    }

    @Test
    fun blurChangesImage() {
        val image = BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB)
        val samples = 100000
        for (i in 1..samples) {
            val h = HistogramMaker()
            h.makeHistogram()
            h.deleteFirst()
            for (point in h.resize(image.width, image.width)) {
                image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
            }
        }
        val blur = Blur()
        FileOutputStream("resultTest3.png").use { out ->
            ImageIO.write(image, "png", out)
        }
        val newImage = blur.blur(image)
        FileOutputStream("resultTest4.png").use { out ->
            ImageIO.write(newImage, "png", out)
        }
        val bytesOld = Files.readAllBytes(Path.of("resultTest3.png"))
        val bytesNew = Files.readAllBytes(Path.of("resultTest4.png"))
        assertThat(bytesOld.equals(bytesNew)).isFalse()
    }

    @Test
    fun notBlackImageWithCoroutines(): Unit = runBlocking {
        val image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
        repeat(10) {
            val job = launch {
                val h = HistogramMaker()
                h.makeHistogram()
                h.deleteFirst()
                for (point in h.resize(image.width, image.width)) {
                    image.setRGB(point.x.toInt(), point.y.toInt(), point.color.value)
                }
            }
            job.join()
        }
        FileOutputStream("resultTest5.png").use { out ->
            ImageIO.write(image, "png", out)
        }

        val colorCoroutines = Color(image.getRGB(0, 0))

        assertThat(Color.BLACK).isNotEqualTo(colorCoroutines)
    }
}
