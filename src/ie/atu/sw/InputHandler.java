package ie.atu.sw;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * Interface for the Input handling.
 */
public interface InputHandler {

    /**
     * Reads a line of text entered by the user and returns it.
     *
     * @return the user input as a string.
     */
    String getLineOfText();

    /**
     * Reads a single character entered by the user and returns it.
     *
     * @return the user input as a character.
     */
    char getCharacter();
}
