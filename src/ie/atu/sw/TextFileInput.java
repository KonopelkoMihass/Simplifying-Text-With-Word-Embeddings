package ie.atu.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class extends the AbstractTextFile, with purpose being the ability to read files.
 */
public class TextFileInput extends AbstractTextFile {

    /**
     * Constructor.
     * Initializes the container for lines of text.
     */
    public TextFileInput() {
        lines = new ArrayList<String>();
    }

    /**
     * Constructor.
     * Initializes the container for lines of text as well as sets up a filepath for the file.
     *
     * @param pathToFile a location of a file which later would be read.
     */
    public TextFileInput(String pathToFile) {
        filePath = pathToFile;
        lines = new ArrayList<String>();
    }

    /**
     * Returns lines of text it stores.
     *
     *  O(1) - simple get method.
     *
     * @return an ArrayList of strings that contain lines of text.
     */
    public ArrayList<String> getLines() {
        return lines;
    }

    /**
     * Opens a file in a location specified in file path and attempts to read it, split it by lines and store it in an ArrayList.
     *
     * O(fileSize) - it loops through the document lines to read in all the data, so it should be O(n). However, each line has
     * a different amount of characters. Hence, complexity depends on a supplied file.
     *
     */
    public void readFile() {
        try {
            Scanner reader = new Scanner(new File(filePath));
            while (reader.hasNextLine()) lines.add(reader.nextLine());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileError: An error occurred while reading file at " + filePath);
            e.printStackTrace();
        }
    }
}
