package ascii_art;

/**
 * This class is responsible for throwing an exception when the size of the image is invalid
 *
 * @see AsciiArtManager
 * @author shimon ifrach and avi wolf
 */
public class InvalidSizeException extends  Exception {

    /* constructor */

    /**
     * This constructor initializes the massage field
     *
     * @param massage the massage that will be printed when the exception is thrown
     */
    InvalidSizeException(String massage){
        super(massage);
    }
}
