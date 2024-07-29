package image_char_matching;

/**
 * A record that holds a character and its brightness.
 */
public record CharBrightness(double brightness, char character) {

    /**
     * A comparator for CharBrightness objects.
     * @param rhs the first CharBrightness object.
     * @param lhs the second CharBrightness object.
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    public static int comparator(CharBrightness rhs, CharBrightness lhs) {
        return Double.compare(rhs.brightness, lhs.brightness);
    }

    /**
     * A comparator for CharBrightness objects.
     * @param o   the reference object with which to compare.
     * @return    boolean value if the objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CharBrightness that)) {
            return false;
        }
        return Double.compare(that.brightness, brightness) == 0 &&
                character == that.character;
    }


}
