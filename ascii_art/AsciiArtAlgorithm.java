package ascii_art;


import image.CalculateBrightness;
import image.Image;
import image.PowerOfTwoImagePadder;
import image_char_matching.SubImgCharMatcher;

import java.util.ArrayList;

import static image.SplitImage.splitImage;

/**
 * This class is responsible for running the ascii art algorithm on an image
 * @author shimon ifrach and avi wolf
 */
public class AsciiArtAlgorithm {

    /* fields */
    private final Image image;
    private final int resolution;
    private final SubImgCharMatcher matcher;
    private ArrayList<ArrayList<Image>> splitImages;

    /**
     * This constructor initializes the image, resolution, and matcher fields
     *
     * @param image      the image that will be used in the ascii art
     * @param resolution the resolution of the ascii art
     * @param matcher    the matcher that will be used to match a character to a sub-image
     */
    public AsciiArtAlgorithm(Image image, int resolution, SubImgCharMatcher matcher) {
        this.image = image;
        this.resolution = resolution;
        this.matcher = matcher;
    }

    /**
     * This method runs the ascii art algorithm on the image
     *
     * @return the ascii art of the image as a 2D array of characters
     */
    public char[][] run() {
        int widthResolution = resolution;
        int heightResolution = resolution;

        if (this.splitImages == null) { // if the image has not been split yet
            splitImages = splitImage(PowerOfTwoImagePadder.padder(image),
                    widthResolution,
                    heightResolution
            );
        }

        char[][] asciiArt = new char[heightResolution][widthResolution];

        for (ArrayList<Image> row : splitImages) {
            for (Image img : row) {
                double brightness = CalculateBrightness.calculateBrightness(img);
                asciiArt[splitImages.indexOf(row)][row.indexOf(img)] = matcher.getCharByImageBrightness(brightness);
            }
        }
        return asciiArt;
    }

    /**
     * This method saves the current state of the split images
     *
     * @return the memento that contains the current state of the split images
     */
    public SplitImageMemento saveToMemento() {
        return new SplitImageMemento(splitImages);
    }

    /**
     * This method restores the split images from a memento
     *
     * @param memento the memento that contains the state of the split images
     */
    public void restoreFromMemento(SplitImageMemento memento) {
        this.splitImages = memento.splitImages;
    }

    /**
     * This class is responsible for saving the state of the split images
     */
    public static class SplitImageMemento {

        private final ArrayList<ArrayList<Image>> splitImages;

        private SplitImageMemento(ArrayList<ArrayList<Image>> splitImages) {
            this.splitImages = splitImages;
        }
    }
}
