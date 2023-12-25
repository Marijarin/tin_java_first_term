package edu.project_4.kotlin;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

@SuppressWarnings("MagicNumber")
public class Blur {
    final int blurSize = 4;

    public BufferedImage blur(BufferedImage image) {
        int[] destPixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        int[] sourcePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                destPixels[y * image.getWidth() + x] = getOutValue(sourcePixels, image.getWidth(), x, y);
            }
        }
        return image;
    }

    private int getOutValue(int[] source, int line, int x, int y) {
        int halfSize = blurSize / 2;
        int count = 0;
        int rsum = 0;
        int gsum = 0;
        int bsum = 0;
        for (int i = y - halfSize; i <= y + halfSize; i++) {
            for (int j = x - halfSize; j <= x + halfSize; j++) {
                if (j >= 0 && i >= 0 && j < line && (i < source.length / line)) {
                    count += 1;
                    rsum += (source[i * line + j] & 0xff0000) >> 16;
                    gsum += (source[i * line + j] & 0xff00) >> 8;
                    bsum += (source[i * line + j] & 0xff);
                }
            }
        }
        return 0xff000000 | ((rsum / count) << 16) | ((gsum / count) << 8) | (bsum / count);
    }
}
