package image;

import java.awt.*;

/**
    this class is responsible for padding an image to the next power of two
 */
public class PowerOfTwoImagePadder {

    /**
     * Pads an image to the next power of two
     * @param image the image to pad
     * @return the padded image
     *
     *
     * @see Image
     */
    public static Image padder(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int newWidth = nextPowerOfTwo(width);
        int newHeight = nextPowerOfTwo(height);

        int widthAddition = (newWidth - width)/2;
        int heightAddition = (newHeight - height)/2;

        Color[][] newPixelArray = new Color[newHeight][newWidth];

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                if (
                        (heightAddition <= i && i < height + heightAddition) &&
                        (widthAddition <= j && j < width + widthAddition)
                ) {
                    newPixelArray[i][j] = image.getPixel(i - heightAddition, j - widthAddition);
                } else {
                    newPixelArray[i][j] = new Color(255, 255, 255);
                }
            }
        }
        return new Image(newPixelArray, newWidth, newHeight);
    }

    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }

}
