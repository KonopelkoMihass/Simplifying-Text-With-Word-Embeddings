package ie.atu.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class implements InputHandler interface.  It is responsible for reading input from the console.
 */
public class ConsoleInputHandler implements InputHandler {

    /**
     * Implements method from interface.
     * Reads a line of text entered by the user to the console and returns it.
     *
     * O(n) - there is an iteration going through the text file, line by line.
     * However, there always should be a single line by design, so it may run closer to O(1).
     *
     * @return The string of text entered by the user.
     */
    public String getLineOfText() {
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            text = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    /**
     * Implements method from interface.
     * Reads a single character entered by the user to the console and returns it.
     *
     * 0(n) - it is so because it uses getLineOfText() method.
     *
     * @return The first character of the input.
     */
    public char getCharacter(){
        return getLineOfText().charAt(0);
    }
}





