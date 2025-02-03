package ie.atu.sw;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * Interface for the Menu rendering.
 */
public interface MenuRender {

    /**
     * Displays the title information of the program.
     */
    void showTitle();

    /**
     * Displays the menu options for the user to read.
     */
    void showMenu();

    /**
     * Displays the message prompting user to enter filepath for a specific file.
     *
     * @param fileName name of the file for which filepath was requested
     */
    void showFilePathRequest(String fileName);

    /**
     * Displays the menu options for when user has opted to start simplification, asking him to provide option for how to deliver text to simplify.
     */
    void showInputRequest();
}
