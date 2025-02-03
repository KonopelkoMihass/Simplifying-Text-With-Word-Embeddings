package ie.atu.sw;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class implements MenuRender interface. It is responsible for displaying console messages by wrapping
 * System.out.println() methods.
 */
public class ConsoleMenuRender implements MenuRender {

    /**
     * Indicator for if the filepath for the GloVe embeddings was set.
     */
    boolean isGloveEmbeddingSet;

    /**
     * Indicator for if the filepath for the Google 1000 words was set.
     */
    boolean isGoogleFileSet;

    /**
     * Indicator for if the filepath for the output text was set.
     */
    boolean isOutputSet;

    /**
     * Constructor.
     * Sets GloVe embeddings and Google 1000 words as false as these files would not have been yet loaded into the system.
     */
    ConsoleMenuRender() {
        isGloveEmbeddingSet = false;
        isGoogleFileSet = false;
        isOutputSet = true;
    }

    /**
     * Implements method from interface.
     * Displays the title information of the program.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showTitle() {
        System.out.println(ConsoleColour.WHITE);
        showLineSeparator();
        System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
        System.out.println("*           Text Simplifier With Word Embeddings           *");
        System.out.println("*                  by Mihass Konopelko                     *");
        System.out.println("*                       G00439360                          *");
        showLineSeparator();
    }

    /**
     * Implements method from interface.
     * Displays the menu options for the user to read.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showMenu() {
        System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
        System.out.println("Select Option:");
        System.out.println("(1) Specify Embeddings File (set: "  + isGloveEmbeddingSet + ")" );
        System.out.println("(2) Specify Google 1000 File (set: " + isGoogleFileSet + ")" );
        System.out.println("(3) Specify an Output File (set: "+ isOutputSet +")");
        System.out.println("(4) Execute, Analyse and Report");
        System.out.println("(q) Quit");
    }

    /**
     * Implements method from interface.
     * Displays the message prompting user to enter filepath for a specific file.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showFilePathRequest(String fileName) {
        System.out.println("Enter the correct directory for the " + fileName + ":");
    }

    /**
     * Implements method from interface.
     * Displays the menu options for when user has opted to start simplification, asking him to provide option for how to deliver text to simplify.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showInputRequest() {
        System.out.println("Select Input Option:");
        System.out.println("(1) Enter Text");
        System.out.println("(2) Specify Input File");
    }

    /**
     * Displays the separator line.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showLineSeparator() {
        System.out.println("************************************************************");
    }

    /**
     * Displays the error message for the wrong filepath the user provided.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showWrongFilepathError() {
        System.out.println("Wrong FilePath. Try again.");
    }

    /**
     * Displays the error message for the wrong option selected by the user provided.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showMenuInputError() {
        System.out.println("Incorrect Option. Try again");
    }

    /**
     * Displays the message for completion of simplification.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void showSimplificationComplete() {
        System.out.println("Simplification complete.");
    }

    /**
     * Sets an indicator for GloVe embedding filepath as true.  Used after the filepath was set elsewhere.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void gloveEmbeddingIsSet() {
        isGloveEmbeddingSet = true;
    }

    /**
     * Sets an indicator for Google 100 words filepath as true.  Used after the filepath was set elsewhere.
     *
     * O(1) - the method always does the same action and is never altered.
     */
    public void googleFileIsSet() {
        isGoogleFileSet = true;
    }
    /**
     * Displays the error message regarding the missing filepaths.
     *
     * O(1) - the method always does the same action and is never altered.
     *
     * @param errorMessage an error message to display.
     */
    public void showFilePathError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
