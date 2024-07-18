package ascii_art.user_interface;

import ascii_art.AsciiArtManager;

/**
 * This class is responsible for executing the add and remove commands
 * It adds or removes characters from the ascii art
 *
 * @see ExecuteUserCommand
 */
public class ExecuteHandleCharset implements ExecuteUserCommand {

    /* constants */
    private static final String INCORRECT_FORMAT_ADD = "Did not add due to incorrect format.";
    private static final String INCORRECT_FORMAT_REMOVE = "Did not remove due to incorrect format.";
    private static final String ALL_COMMAND = "all";
    private static final String SPACE_COMMAND = "space";

    /* fields */
    private final AsciiArtManager manager;
    private final String action;

    /* constructor */

    /**
     * This constructor initializes the manager and action fields
     *
     * @param manager the manager that will be used to add or remove characters
     * @param action  the action that will be executed (add or remove)
     */
    public ExecuteHandleCharset(AsciiArtManager manager, String action) {
        this.manager = manager;
        this.action = action;
    }

    /**
     * This method adds or removes characters from the ascii art
     *
     * @param command the command that the user entered
     *                the command should start with "add " or "remove "
     *                followed by a character, a range of characters or "all" or "space"
     *                otherwise an IncorrectFormat exception will be thrown
     * @throws IncorrectFormat if the command is incorrect format
     */
    @Override
    public void Execute(String command) throws IncorrectFormat {

        command = removeTheCommandPrefix(command);
        char[] chars;
        if (command.length() == 1 || // if the command is a single character
                (command.indexOf(' ') != -1 && command.substring(0, command.indexOf(' ')).length() == 1)) {

            chars = new char[]{command.charAt(0)};
        } else if (command.trim().startsWith(ALL_COMMAND)) {  //
            chars = new char[127 - 32];
            for (int i = 32; i < 127; i++) {
                chars[i - 32] = (char) i;
            }

        } else if (command.trim().startsWith(SPACE_COMMAND)) {
            chars = new char[]{' '};
        } else if ( // if the command is a range of characters
                command.length() >= 3 &&
                        command.charAt(1) == '-' &&
                        (command.length() == 3 || command.charAt(3) == ' ')
        ) {
            int max = command.charAt(0);
            int min = command.charAt(2);
            if (max < min) {
                int temp = max;
                max = min;
                min = temp;
            }
            chars = new char[max - min + 1];
            for (int i = min; i <= max; i++) {
                chars[i - min] = (char) i;
            }
        } else {
            String message = INCORRECT_FORMAT_ADD;
            if (action.equals(HandleUserChoice.REMOVE_COMMAND)) {
                message = INCORRECT_FORMAT_REMOVE;
            }
            throw new IncorrectFormat(message);
        }
        executeAction(chars);
    }

    private void executeAction(char[] chars) {
        switch (action) {
            case HandleUserChoice.ADD_COMMAND:
                manager.addCharToList(chars);
                break;
            case HandleUserChoice.REMOVE_COMMAND:
                manager.removeCharFromList(chars);
        }
    }

    private String removeTheCommandPrefix(String command) {
        switch (action) {
            case HandleUserChoice.ADD_COMMAND:
                command = command.substring(4);
                break;
            case HandleUserChoice.REMOVE_COMMAND:
                command = command.substring(7);
        }
        return command;
    }
}
