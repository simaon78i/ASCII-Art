package ascii_art.user_interface;

import ascii_art.AsciiArtManager;
import ascii_art.InvalidSizeException;

/**
 * This class is responsible for executing the resolution command
 * It changes the resolution of the ascii art
 *
 * @see ExecuteUserCommand
 */
public class ExecuteResolution implements ExecuteUserCommand {

    /* constance */
    private static final int BEGIN_INDEX = 4;
    private static final double DOUBLE = 2.0;
    private static final double HALF = 0.5;
    private static final String UP_COMMAND = "up";
    private static final String DOWN_COMMAND = "down";

    /* messages */
    private static final String RESOLUTION_SET_TO = "Resolution set to %d\n";
    private static final String INCORRECT_FORMAT = "Did not change resolution due to incorrect format.";

    /* fields */
    private final AsciiArtManager manager;

    /**
     * This constructor initializes the manager field
     *
     * @param manager the manager that will be used to change the resolution
     */
    public ExecuteResolution(AsciiArtManager manager) {
        this.manager = manager;
    }

    /**
     * This method changes the resolution of the ascii art
     * The resolution can be increased or decreased by a factor of 2
     *
     * @param command the command that the user entered can be "res up" or "res down"
     */
    @Override
    public void Execute(String command) throws IncorrectFormat {
        command = command.substring(BEGIN_INDEX);
        double userChoice;
        int currentResolution;
        if (command.startsWith(UP_COMMAND)) {
            userChoice = DOUBLE;
        } else if (command.startsWith(DOWN_COMMAND)) {
            userChoice = HALF;
        } else {
            throw new IncorrectFormat(INCORRECT_FORMAT);
        }
        try {
            currentResolution = manager.resolutionChanger(userChoice);
        } catch (InvalidSizeException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf(RESOLUTION_SET_TO, currentResolution);
    }
}

