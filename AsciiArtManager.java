package ascii_art;

import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;
import image_char_matching.CharBrightness;
import image_char_matching.SubImgCharMatcher;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.Collections.sort;

/**
 * This class is responsible for managing the ascii art algorithm
 *  @author shimon ifrach and avi wolf
 */
public class AsciiArtManager {

    /*constants*/
    private static final String OUT_FILE_FOR_HTML = "out.html";
    private static final String FONT_TYPE_FOR_HTML = "Courier New";
    private static final String INVALID_RESOLUTION_MASSAGE = "Did not change resolution " +
            "due to exceeding boundaries.";
    private static final int MIN_SIZE_FOR_RESOLUTION = 1;
    private static final int RESOLUTION_DEFAULT_SIZE = 128;
    private static final int DEFAULT_OUTPUT_CHOICE = 0;
    private static final char[] DEFAULT_CHARSET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final String DEFAULT_IMAGE = "cat.jpeg";
    private static final int RESET_RESOLUTION = 2;
    /**
     * The constant re the html output
     */
    public static final int HTML_OUTPUT = 1;
    /**
     * The constant for the console output
     */
    public static final int CONSOLE_OUTPUT = 0;


    /*fields*/
    private final SubImgCharMatcher matcher;
    private final ArrayList<Character> charList;
    private Image image;
    private int resolution;
    private final AsciiOutput[] outputArray;
    private int userChoice;
    private AsciiArtAlgorithm.SplitImageMemento memento;
    private boolean isChangesOccurred = false;

    /*constructor*/

    /**
     * This constructor initializes the image, matcher, charList, resolution, outputArray, and userChoice fields
     */
    public AsciiArtManager() {
        try {
            this.image = new Image(DEFAULT_IMAGE);
        } catch (IOException e) {
            System.exit(1);
        } // should never get there.
        this.matcher = new SubImgCharMatcher(DEFAULT_CHARSET);
        this.charList = new ArrayList<>();
        this.resolution = RESOLUTION_DEFAULT_SIZE;
        this.outputArray = new AsciiOutput[]{new ConsoleAsciiOutput(), new
                HtmlAsciiOutput(OUT_FILE_FOR_HTML, FONT_TYPE_FOR_HTML)};
        this.userChoice = DEFAULT_OUTPUT_CHOICE;
    }

    /*methods*/

    /**
     * This method adds a character to the list of characters that are used in the ascii art
     *
     * @param c list of characters to add
     */
    public void addCharToList(char[] c) {
        for (char current : c) {
            matcher.addChar(current);
        }
    }

    /**
     * This method removes a character from the list of characters that are used in the ascii art
     *
     * @param c list of characters to remove
     */
    public void removeCharFromList(char[] c) {
        for (char current : c) {
            matcher.removeChar(current);

        }
    }

    /**
     * This method returns the list of characters that are used in the ascii art
     *
     * @return the list of characters that are used in the ascii art
     */
    public ArrayList<Character> getCharList() {
        for (CharBrightness brightness : matcher.getCharBrightness()) {
            charList.add(brightness.character());
        }
        sort(charList);
        return charList;
    }

    /**
     * This method changes the resolution of the ascii art
     * The resolution can be increased or decreased by a factor of 2
     *
     * @param factorToMultiply the factor to multiply the resolution by
     * @return the new resolution
     * @throws InvalidSizeException if the new resolution is invalid
     */
    public int resolutionChanger(double factorToMultiply) throws InvalidSizeException {
        if (!isResolutionValid(factorToMultiply)) {
            return resolution;
        }
        isChangesOccurred = true;
        resolution = (int) (resolution * factorToMultiply);
        return resolution;
    }

    private boolean isResolutionValid(double factorToMultiply) throws InvalidSizeException {
        if (factorToMultiply * resolution > image.getWidth() ||
                factorToMultiply * resolution < Math.max(MIN_SIZE_FOR_RESOLUTION, image.getWidth()/image.getHeight()))
        {
            throw new InvalidSizeException(INVALID_RESOLUTION_MASSAGE);

        }
        return true;


    }

    /**
     * This method changes the image that will be used in the ascii art
     *
     * @param imageFile the path of the image file
     * @throws IOException if there is a problem with the image file
     */
    public void chooseImage(String imageFile) throws IOException {
        this.image = new Image(imageFile);
        try {
            isResolutionValid(1.0);
        } catch (InvalidSizeException e) {
            resolution = RESET_RESOLUTION;
        }
        isChangesOccurred = true;
    }

    /**
     * This method runs the ascii art algorithm
     */
    public void runAsciiArtAlgorithm() {
        AsciiArtAlgorithm asciiArtAlgorithm = new AsciiArtAlgorithm(image, resolution, matcher);
        if (isChangesOccurred) {
            memento = asciiArtAlgorithm.saveToMemento();
            isChangesOccurred = false;
        } else {
            asciiArtAlgorithm.restoreFromMemento(memento);
        }
        outputArray[userChoice].out(asciiArtAlgorithm.run());
    }

    /**
     * This method changes the output format of the ascii art to either console or html
     *
     * @param numFromUser the number from user that choose the format of the output
     *                    could be 0 for console or 1 for html.
     */
    public void output(int numFromUser) {
        userChoice = numFromUser;
    }
}
