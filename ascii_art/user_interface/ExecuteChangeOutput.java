package ascii_art.user_interface;

import ascii_art.AsciiArtManager;

/**
 * This class is responsible for changing the output method of the ascii art
 *
 * @see ExecuteUserCommand
 */
public class ExecuteChangeOutput implements ExecuteUserCommand {

    /* constants */
    private static final int BEGIN_INDEX = 7;
    private static final String CONSOLE_COMMAND = "console";
    private static final String HTML_COMMAND = "html";
    private static final String INCORRECT_FORMAT = "Did not change output method due to incorrect format.";

    /* fields */
    private final AsciiArtManager manager;

    /* constructor */

    /**
     * This constructor initializes the manager field
     *
     * @param manager the manager that will be used to change the output method
     */
    public ExecuteChangeOutput(AsciiArtManager manager) {
        this.manager = manager;
    }

    /**
     * This method changes the output method of the ascii art
     *
     * @param command the command that will be used to change the output method
     *                the command should start with "output console" or "output html"
     *                otherwise an IncorrectFormat exception will be thrown
     * @throws IncorrectFormat if the command is incorrect format
     */
    @Override
    public void Execute(String command) throws IncorrectFormat {
        command = command.substring(BEGIN_INDEX);
        int userChoice;
        if (command.startsWith(CONSOLE_COMMAND)) {
            userChoice = AsciiArtManager.CONSOLE_OUTPUT;
        } else if (command.startsWith(HTML_COMMAND)) {
            userChoice = AsciiArtManager.HTML_OUTPUT;
        } else {
            throw new IncorrectFormat(INCORRECT_FORMAT);
        }
        manager.output(userChoice);
    }
}
