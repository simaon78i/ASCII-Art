package ascii_art.user_interface;

import ascii_art.AsciiArtManager;

/**
 * This class is responsible for executing the chars command
 * It prints all the characters that are used in the ascii art
 *
 * @see ExecuteUserCommand
 */
public class ExecuteChars implements ExecuteUserCommand {

    /* constants */
    private static final String SPACE = " ";

    /* fields */
    private final AsciiArtManager manager;

    /* constructor */

    /**
     * This constructor initializes the manager field
     *
     * @param manager the manager that will be used to print the characters
     */
    public ExecuteChars(AsciiArtManager manager) {
        this.manager = manager;
    }

    /**
     * This method prints all the characters that are used in the ascii art
     *
     * @param command the command that the user entered
     */
    @Override
    public void Execute(String command) {
        for (char c : manager.getCharList()) {
            System.out.print(c + SPACE);
        }
        System.out.println();
    }
}
