package ascii_art.user_interface;

import ascii_art.AsciiArtManager;

/**
 * This class is responsible for executing the run command
 * It runs the ascii art algorithm on the image
 */
public class ExecuteRun implements ExecuteUserCommand {

    /* fields */
    private final AsciiArtManager manager;

    /* constructor */

    /**
     * This constructor initializes the manager field
     *
     * @param manager the manager that will be used to run the ascii art algorithm
     */
    public ExecuteRun(AsciiArtManager manager) {
        this.manager = manager;
    }

    /**
     * This method runs the ascii art algorithm on the image
     *
     * @param command the command that the user entered
     */
    @Override
    public void Execute(String command) {
        manager.runAsciiArtAlgorithm();
    }
}
