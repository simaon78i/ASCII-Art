package ascii_art.user_interface;

import ascii_art.AsciiArtManager;

import java.io.IOException;

/**
 * This class is responsible for executing the image command
 * It changes the image that is used in the ascii art
 *
 * @see ExecuteUserCommand
 */
public class ExecuteImage implements ExecuteUserCommand {

    /* constants */
    private static final String INCORRECT_FORMAT = "Did not change image due to incorrect format.";
    private static final String PROBLEM_WITH_IMAGE_FILE = "Did not execute do to problem with image file.";

    /* fields */
    private final AsciiArtManager manager;

    /* constructor */

    /**
     * This constructor initializes the manager field
     *
     * @param manager the manager that will be used to change the image
     */
    public ExecuteImage(AsciiArtManager manager) {
        this.manager = manager;
    }

    /**
     * This method changes the image that is used in the ascii art
     *
     * @param command the command that the user entered
     *                the command should start with "image " followed by the path of the image
     *                otherwise an IncorrectFormat exception will be thrown
     * @throws IncorrectFormat if the command is incorrect format
     */
    @Override
    public void Execute(String command) throws IncorrectFormat {
        command = command.substring(6);
        if (command.isEmpty()) {
            throw new IncorrectFormat(INCORRECT_FORMAT);
        }
        try {
            manager.chooseImage(command);
        } catch (IOException e) {
            System.out.println(PROBLEM_WITH_IMAGE_FILE);
        }
    }
}
