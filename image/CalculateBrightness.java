package image;

import java.awt.*;

/**
 * A class for calculating the brightness of an image.
 * The brightness is calculated by the formula:
 * brightness = (red * 0.2126 + green * 0.7152 + blue * 0.0722) / 255
 *  @author shimon ifrach and avi wolf
 */
public class CalculateBrightness {

    /*constants for calculating brightness*/
    private static final double RED_FACTOR = 0.2126;
    private static final double GREEN_FACTOR = 0.7152;
    private static final double BLUE_FACTOR = 0.0722;
    private static final double MAX_RGB_SCORE = 255.0;

    /**
     * Calculates the brightness of an image.
     * @param image the image to calculate the brightness of.
     * @return the brightness of the image.
     */
    public static double calculateBrightness(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();

        double brightness = 0.0;
        Color pixel;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixel = image.getPixel(i, j);
                brightness +=
                        pixel.getRed() * RED_FACTOR +
                        pixel.getGreen() * GREEN_FACTOR +
                        pixel.getBlue() * BLUE_FACTOR;
            }
        }
        return brightness / (width * height) / MAX_RGB_SCORE;
    }
}
