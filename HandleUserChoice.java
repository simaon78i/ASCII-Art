package ascii_art.user_interface;

import ascii_art.AsciiArtManager;

/**
 * This class is responsible for handling the user choice
 * It executes the user command based on the user input on the Shell
 * It throws an IncorrectFormat exception if the command is incorrect
 */
public class HandleUserChoice {

    /* constants */

    /**
     * The command to add a new charset
     */
    public static final String ADD_COMMAND = "add ";

    /**
     * The command to remove a charset
     */
    public static final String REMOVE_COMMAND = "remove ";

    private static final String CHARS_COMMAND = "chars";
    private static final String RESOLUTION_COMMAND = "res ";
    private static final String RUN_COMMAND = "asciiArt";
    private static final String IMAGE_COMMAND = "image ";
    private static final String OUTPUT_COMMAND = "output ";
    private static final String EXIT_COMMAND = "exit";
    private static final String INCORRECT_COMMAND_MASSAGE = "Did not execute do to incorrect command.";

    /* fields */
    private final AsciiArtManager manager;

    /* constructor */

    /**
     * This constructor initializes the manager field
     *
     * @param manager the manager that will be used to execute the user command
     */
    public HandleUserChoice(AsciiArtManager manager) {
        this.manager = manager;
    }

    /**
     * This method handles the user choice
     *
     * @param command the command that the user entered
     *                the command should start with "chars", "add ", "remove ", "res ", "image ", "output ",
     *                "asciiArt" or "exit"
     *                otherwise an IncorrectFormat exception will be thrown
     * @throws IncorrectFormat if the command is incorrect format
     */
    public void handle(String command) throws IncorrectFormat {
        ExecuteUserCommand executeUserCommand;
        if (command.startsWith(CHARS_COMMAND)) {
            executeUserCommand = new ExecuteChars(manager);
        } else if (command.startsWith(ADD_COMMAND)) {
            executeUserCommand = new ExecuteHandleCharset(manager, ADD_COMMAND);
        } else if (command.startsWith(REMOVE_COMMAND)) {
            executeUserCommand = new ExecuteHandleCharset(manager, REMOVE_COMMAND);
        } else if (command.startsWith(RESOLUTION_COMMAND)) {
            executeUserCommand = new ExecuteResolution(manager);
        } else if (command.startsWith(IMAGE_COMMAND)) {
            executeUserCommand = new ExecuteImage(manager);
        } else if (command.startsWith(OUTPUT_COMMAND)) {
            executeUserCommand = new ExecuteChangeOutput(manager);
        } else if (command.startsWith(RUN_COMMAND)) {
            executeUserCommand = new ExecuteRun(manager);
        } else if (command.startsWith(EXIT_COMMAND)) {
            return;
        } else {
            throw new IncorrectFormat(INCORRECT_COMMAND_MASSAGE);
        }
        executeUserCommand.Execute(command);
    }
}