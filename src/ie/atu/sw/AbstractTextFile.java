package ie.atu.sw;

import java.util.ArrayList;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * Abstract class for storing text in ArrayList, as well as the path to the text file.
 * It provides methods to check for presence of text and presence of filepath, as well as setting of filepath.
 */
abstract class AbstractTextFile {

    /** Represents a filepath of the text file. */
    String filePath;

    /** Represents a text, split into lines and stored in an ArrayList that preserves order. */
    ArrayList<String> lines;

    /**
     * Sets the path to the text file.
     *
     * O(1) - simple assignment.
     *
     * @param pathToFile a filepath to set.
     */
    public void setFilePath(String pathToFile) {
        filePath = pathToFile;
    }

    /**
     * Checks that there is no text.
     *
     * O(1) - simple check if variable is empty or not.
     *
     * @return true if file is empty.
     */
    public boolean isEmpty() {
        return lines.isEmpty();
    }


    /**
     * Checks that there is no filepath set.
     *
     * 0(1) - check for null is O(1) and is.Empty() is also 0(1).
     *
     * @return true if filepath is empty or is null.
     */
    public boolean hasNoFilePath() {
        return filePath == null || filePath.isEmpty();
    }
}
