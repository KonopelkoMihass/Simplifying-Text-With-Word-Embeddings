package ie.atu.sw;

import java.io.File;
import java.util.*;


/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class handles three files the program would use to operate:
 *  - GloVe embeddings
 *  - Google 100 words
 *  - Output file
 *  This class manages these three objects and is used to interact with them.
 *
 */
public class FilesHandler {

    /**
     * Enumerator used to indicate the type of file being handled. Types of file are:
     */
    public static enum FileType {
        /**
         * GloVeEmbedding -> the shortened to 59k+ set of word embeddings.
         */
        GloVeEmbedding,

        /**
         * Google -> a collection fo 1000 most used words, by Google.
         */
        Google,

        /**
         * Output -> a potential output.
         */
        Output,
    }

    /** The object that will store GloVe embedding file and later be processed elsewhere */
    TextFileInput embeddingFile;

    /** The object that will store Google 1000 words file and later be processed elsewhere */
    TextFileInput google1000WordsFile;

    /** The object that will store output (simplified) text and would write it */
    TextFileOutput outputFile;

    /**
     * Constructor.
     * Initializes file managing objects
     */
    FilesHandler() {
        embeddingFile = new TextFileInput();
        google1000WordsFile = new TextFileInput();
        outputFile = new TextFileOutput("./out.txt");
    }

    /**
     * Sets the filepath for one of the file managing objects based on a file type.
     *
     * @param filePath The path to the file.
     * @param type The type of file stated in Enumerator FileType.
     *
     * O(1) - calls a method setFilePath() which is O(1).  No loops or other complications.
     */
    public void setFilePath(String filePath, FileType type) {
        switch (type) {
            case GloVeEmbedding -> embeddingFile.setFilePath(filePath);
            case Google -> google1000WordsFile.setFilePath(filePath);
            case Output-> outputFile.setFilePath(filePath);
        }
    }

    /**
     * Checks that all filepaths are set and prints an error in a case when a filepath is absent.
     *
     * O(1) - calls a method hasNoFilePath() which is O(1). Even if it triggers all statements, it would still be O(1).
     *
     * @param menuRender instance of ConsoleMenuRender
     *
     * @return true if all file paths are set.
     */
    public boolean checkIfAllFilePathsFilled(ConsoleMenuRender menuRender) {
        String errorMessage = "";
        if (embeddingFile.hasNoFilePath()) errorMessage
                += "Error: Filepath is empty for the GloVe Embedding File\n";
        if (google1000WordsFile.hasNoFilePath()) errorMessage
                += "Error: Filepath is empty for the Google 1000 Words File\n";
        if (outputFile.hasNoFilePath()) errorMessage
                += "Error: Filepath is empty for the Output File\n";

        if (errorMessage.isEmpty()) return true;
        else menuRender.showFilePathError(errorMessage);
        return false;
    }

    /**
     * Triggers reading of files for GloVe embeddings and Google 1000 words.
     *
     * O(n + m) - calls a method readFile() which is O(n) twice.  Since each file varies in content, it is not O(2n), but rather O(n+m).
     *
     */
    public void readFiles() {
        embeddingFile.readFile();
        google1000WordsFile.readFile();
    }


    /**
     * Retrieves the lines of text from one of the files based on the type of the file.
     *
     * O(1) - retrieval of values via get methods will be constant.
     *
     * @param type The type of file, either Google or Embedding.
     * @return The content of the file split into lines as ArrayList.
     */
    public ArrayList<String> getTextFileLines(FileType type) {
        return switch (type) {
            case GloVeEmbedding -> embeddingFile.getLines();
            case Google -> google1000WordsFile.getLines();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    /**
     * Static method that checks if a string is a filepath.
     *
     * O(1) - Simple boolean checks.
     *
     * @param filepath The potential filepath.
     * @return true if it is a filepath .
     */
    public static boolean isStringAFilePath(String filepath) {
        File f = new File(filepath);
        return f.exists() && !f.isDirectory();
    }

    /**
     * Inserts content (a simplified text) into a text file that would be used as an output.
     *
     * O(1) - insert operation into an array list is at 0(1).
     *
     * @param content The text to be inserted into the output file.
     */
    public void insertOutputContent(String content) {
        outputFile.insert(content);
    }

    /**
     * Triggers the write file method in the output object.
     *
     * O(n) - writeFile() runs at O(n).
     *
     */
    public void saveOutputContent() {
        outputFile.writeFile(true);
    }
}
