package ascii_art;

import ascii_art.user_interface.HandleUserChoice;
import ascii_art.user_interface.IncorrectFormat;

import static ascii_art.KeyboardInput.readLine;

/**
 * This class is responsible for running the shell
 * It runs the shell that the user can interact with
 * and execute commands
 *
 * @see HandleUserChoice
 * @author shimon ifrach and avi wolf
 */
public class Shell {

    /* constants */
    private static final String LINE_OPENING = ">>>  ";
    private static final String EXIT = "exit";

    /**
     * This method runs the shell
     * It runs the shell that the user can interact with
     * and execute commands.
     * The shell will run until the user enters the exit command
     */
    public void run() {
        String command;
        AsciiArtManager manager = new AsciiArtManager();
        HandleUserChoice handleUserChoice = new HandleUserChoice(manager);
        do {
            System.out.print(LINE_OPENING);
            command = readLine();
            try {
                handleUserChoice.handle(command);
            } catch (IncorrectFormat e){
                System.out.println(e.getMessage());
            }
        }
        while (!command.equals(EXIT));
    }
}
