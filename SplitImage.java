package image;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class is responsible for splitting an image into smaller images.
 *
 * @author shimon ifrach and avi wolf
 */
public class SplitImage {

    /**
     * Splits an image into smaller images.
     *
     * @param image         the image to split
     * @param rowResolution the number of smaller images in each row
     * @param colResolution the number of smaller images in each column
     * @return an ArrayList of ArrayLists of Images, where each inner ArrayList represents a row of the split images
     * @see Image
     */
    public static ArrayList<ArrayList<Image>> splitImage(Image image, int rowResolution, int colResolution) {
        int width = image.getWidth();
        int height = image.getHeight();

        int splitWidth = width / colResolution;
        int splitHeight = height / rowResolution;

        ArrayList<ArrayList<Image>> splitImages = new ArrayList<>(rowResolution);
        for (int i = 0; i < rowResolution; i++) {
            ArrayList<Image> row = new ArrayList<>(colResolution);
            for (int j = 0; j < colResolution; j++) {
                Color[][] newPixelArray = new Color[splitHeight][splitWidth];
                for (int k = 0; k < splitHeight; k++) {
                    for (int l = 0; l < splitWidth; l++) {
                        newPixelArray[k][l] = image.getPixel(i * splitHeight + k, j * splitWidth + l);
                    }
                }
                row.add(new Image(newPixelArray, splitWidth, splitHeight));
            }
            splitImages.add(row);
        }
        return splitImages;
    }
}
