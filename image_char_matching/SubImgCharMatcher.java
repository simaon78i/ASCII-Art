package image_char_matching;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class is responsible for matching a character to a sub-image.
 * It uses a TreeMap to store the brightness of each character.
 * The brightness is calculated by the number of white pixels in the sub-image.
 * The class also provides methods to add and remove characters from the map.
 */
public class SubImgCharMatcher {
    ArrayList<CharBrightness> charBrightness = new ArrayList<>();
    TreeMap<Double, Character> normalizedCharBrightness = new TreeMap<>();

    /**
     * Constructs a SubImgCharMatcher object with a given charset.
     * The charset is an array of characters that the user wants to use in the ascii art.
     * The constructor calculates the brightness of each character and stores it in the TreeMap.
     *
     * @param charset the charset to use in the ascii art.
     */
    public SubImgCharMatcher(char[] charset) {
        for (char c : charset) {
            charBrightness.add(
                    new CharBrightness(
                            calculateBrightnessFromBoolArray(CharConverter.convertToBoolArray(c)),
                            c)
            );
        }
        charBrightness.sort(CharBrightness::comparator);
        normalizeBrightness();
    }

    private void normalizeBrightness() {
        // Normalize the brightness values
        double max = charBrightness.get(charBrightness.size() - 1).brightness();
        double min = charBrightness.get(0).brightness();

        normalizedCharBrightness.clear();

        for (CharBrightness cb : charBrightness) {
            double brightnessKey = (cb.brightness() - min) / (max - min);
            if (normalizedCharBrightness.containsKey(brightnessKey) &&
                    normalizedCharBrightness.get(brightnessKey) <= cb.character()) {
                continue;
            }
            normalizedCharBrightness.put(brightnessKey, cb.character());
        }
    }

    /**
     * This method returns the character that best matches the brightness of a sub-image.
     *
     * @param brightness the brightness of the sub-image.
     * @return the character that best matches the brightness of the sub-image.
     */
    public char getCharByImageBrightness(double brightness) {
        double doubleLesser = normalizedCharBrightness.floorKey(brightness);
        double doubleUpper = normalizedCharBrightness.ceilingKey(brightness);

        double key = getMinAbsoluteDistance(brightness, doubleLesser, doubleUpper);
        return normalizedCharBrightness.get(key);
    }

    /**
     * This method adds a character to the ascii art.
     *
     * @param c the character to add.
     */
    public void addChar(char c) {
        double brightness = calculateBrightnessFromBoolArray(CharConverter.convertToBoolArray(c));
        CharBrightness cb = new CharBrightness(brightness, c);
        if (charBrightness.contains(cb)) {
            return;
        }
        charBrightness.add(cb);
        charBrightness.sort(CharBrightness::comparator);
        normalizeBrightness();
    }

    /**
     * This method removes a character from the ascii art.
     *
     * @param c the character to remove.
     */
    public void removeChar(char c) {
        CharBrightness cb = new CharBrightness(
                calculateBrightnessFromBoolArray(CharConverter.convertToBoolArray(c)),
                c);
        int index = charBrightness.indexOf(cb);
        if (index == -1) {
            return;
        }
        charBrightness.remove(index);
        normalizeBrightness();
    }

    private double calculateBrightnessFromBoolArray(boolean[][] matrix) {
        double whitePixels = 0;
        for (boolean[] row : matrix) {
            for (boolean b : row) {
                if (b) {
                    whitePixels++;
                }
            }
        }
        return whitePixels / (CharConverter.DEFAULT_PIXEL_RESOLUTION *
                CharConverter.DEFAULT_PIXEL_RESOLUTION);
    }

    private double getMinAbsoluteDistance(double original, double small, double big) {
        if (Math.abs(original - small) <= Math.abs(original - big)) {
            return small;
        }
        return big;
    }

    /**
     * This method returns the list of characters that are used in the ascii art
     *
     * @return the list of characters that are used in the ascii art as an ArrayList
     * @see CharBrightness
     */
    public ArrayList<CharBrightness> getCharBrightness() {
        return charBrightness;
    }
}

