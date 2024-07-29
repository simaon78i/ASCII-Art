package ascii_art.user_interface;

/**
 * This class is responsible for throwing an exception when the command
 * is in the incorrect format
 *
 * @see ExecuteChangeOutput
 */
public class IncorrectFormat extends Exception {

    /* constructor */

    /**
     * This constructor initializes the massage field
     *
     * @param massage the massage that will be printed when the exception is thrown
     */
    public IncorrectFormat(String massage) {
        super(massage);

    }
}
