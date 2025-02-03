package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class extends the AbstractTextFile, with purpose being the ability to write files
 */
public class TextFileOutput extends AbstractTextFile {
    /**
     * Constructor.
     * Initializes the container for lines of text as well as sets up a filepath for the file.
     *
     * @param pathToFile a location of a file which later would be read.
     */
    TextFileOutput(String pathToFile) {
        filePath = pathToFile;
        lines = new ArrayList<String>();
    }

    /**
     * Provides a way to add new lines of text to this object.
     *
     * O(1) - insert operation into an array list is at 0(1).
     *
     * @param line a line of text to insert.
     */
    public void insert(String line) {
        lines.add(line);
    }

    /**
     * Writes the content stored in lines of text into the file.  File location and name is based on filePath.
     *
     * O(n) - the method loops through text in order to save it into a file.
     *
     * @param flush empties the content of the ArrayList that stores lines of text.
     */
    public void writeFile(boolean flush)  {
        File file = new File(filePath);

        try {
            // If file doesn't exist, then create it.
            if (!file.exists()) {
                file.createNewFile();
            }
            // Create File Writer and open the output file.
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);

            // Create a buffer into which the text will be written.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write into file.
            for(String line : lines) {
                bufferedWriter.write(line);
            }

            // Close the buffer.
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(flush) lines.clear();
    }
}
