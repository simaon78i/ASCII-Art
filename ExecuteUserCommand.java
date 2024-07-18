package ascii_art.user_interface;

/**
 * This interface is responsible for executing the user command
 */
public interface ExecuteUserCommand {

    /**
     * This method executes the user command
     *
     * @param command the command that the user entered
     * @throws IncorrectFormat if the command is incorrect format
     */
    void Execute(String command) throws IncorrectFormat;
}
