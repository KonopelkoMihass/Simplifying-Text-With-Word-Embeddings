package ie.atu.sw;

import java.util.ArrayList;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * The core class of the application where everything happens.
 * It contains all of essential objects and manages the main loop of the program.
 */
public class Program {

    /**
     * The input handler that captures user input from the console.
     */
    ConsoleInputHandler inputHandler;

    /**
     * The menu renderer which displaying menus and messages to the user in console.
     */
    ConsoleMenuRender menuRender;

    /**
     * Manages three essential files for the program.
     */
    FilesHandler filesHandler;

    /**
     * The current state of the program.
     */
    ProgramState currenProgramState;

    /**
     * This object contains logic for performing text simplification.
     */
    WordSimplifier wordSimplifier;

    /**
     * Constructor.
     * Initializes the objects and sets the current state of the program as On Stand By.
     */
    Program () {
        inputHandler = new ConsoleInputHandler();
        menuRender = new ConsoleMenuRender();

        filesHandler = new FilesHandler();
        wordSimplifier = new WordSimplifier();

        currenProgramState = ProgramState.OnStandby;
    }

    /**
     * Starts the program and maintains the loop.  The run process is split into multiple phases:
     * 1. Before the loop initiates, the title of the program is shown to the user.
     * 2. A menu with 5 options is shown, and user must choose what to do.
     * 3. Options 1 to 3 set different file paths - once all three are set, user can continue to simplification execution.
     *    Alternatively, user can (q)uit the program.
     * 4. Option 4 starts the next phase of the program.
     * 5. Program will start computing raw text stored in files within files handler into hashmaps of embeddings.  This process is done once during the run of the program.
     * 6. User is prompted to select the way to provide text for simplification.  Options are either a file or a text typed into the console.
     * 7. Program starts text simplification
     * 8. Program saves the file.
     * 9 Program returns to Step 2.
     *
     * O(2nm) due to the presence of the simplify() method.
     *
     */
    public void run() {
        menuRender.showTitle();
        while (currenProgramState != ProgramState.StopProgram) {
            menuRender.showMenu();
            menuRender.showLineSeparator();
            switch (inputHandler.getCharacter()) {
                case '1':
                    filesHandler.setFilePath(requestFilePath("GloVe Embedding File"), FilesHandler.FileType.GloVeEmbedding);
                    menuRender.gloveEmbeddingIsSet();
                    break;
                case '2':
                    filesHandler.setFilePath(requestFilePath("Google 1000 Words File"), FilesHandler.FileType.Google);
                    menuRender.googleFileIsSet();
                    break;
                case '3':
                    filesHandler.setFilePath(requestFilePath("Output File"), FilesHandler.FileType.Output);
                    break;
                case '4':
                    if (filesHandler.checkIfAllFilePathsFilled(menuRender) ) {
                        currenProgramState = ProgramState.StartExecution;
                    }
                    break;
                case 'q':
                    currenProgramState = ProgramState.StopProgram;
                    break;
                default:
                    menuRender.showMenuInputError();
            }

            if (currenProgramState == ProgramState.StartExecution) {
                // Run this if embeddings were not computed.
                if (!wordSimplifier.areAllEmbeddingsCalculated()) {
                    computeEmbeddings();
                }
                menuRender.showInputRequest();

                filesHandler.insertOutputContent(wordSimplifier.simplify(getTextToSimplify()));

                filesHandler.saveOutputContent();

                menuRender.showLineSeparator();
                menuRender.showSimplificationComplete();
                menuRender.showLineSeparator();

                currenProgramState = ProgramState.OnStandby;
            }
        }
    }

    /**
     * This method triggers the computation of embedding. that will be used for text simplification.  To be calculated only once.
     *
     * O(2(n+m)) - due to the methods eadFiles() that is O(n+m) and prepareEmbeddings() that is O(n+m).
     *
     */
    void computeEmbeddings() {
        filesHandler.readFiles();
        wordSimplifier.prepareEmbeddings(
                filesHandler.getTextFileLines(FilesHandler.FileType.GloVeEmbedding),
                filesHandler.getTextFileLines(FilesHandler.FileType.Google)
        );
    }

    /**
     * Asks the user which way he wants to provide the text for simplification. Options are either manually through the
     * console or from another file.  Selecting the option will also trigger the methods to manage the input and to return
     * it in appropriate format.
     *
     * O(n) - as it reads either the text from the file or from the console.
     *
     * @return An ArrayList of the lines of text for simplification.
     */
    ArrayList<String> getTextToSimplify() {
        ArrayList<String> textToSimplify = new ArrayList<>();
        while (true) {
            switch (inputHandler.getCharacter()) {
                case '1':
                    System.out.println("Enter the text to simplify now:");
                    textToSimplify.add(inputHandler.getLineOfText());
                    return textToSimplify;

                case '2':
                    String inputFilePath = requestFilePath("Input Text");
                    if (FilesHandler.isStringAFilePath(inputFilePath)) {
                        TextFileInput textFileInput = new TextFileInput(inputFilePath);
                        textFileInput.readFile();
                        textToSimplify = textFileInput.getLines();
                        return textToSimplify;
                    }
                    else {
                        System.out.println("error: An error occurred while reading an input file");
                    }
                    break;
                default:
                    menuRender.showMenuInputError();
            }
        }
    }

    /**
     * Requests the user to supply file path to a specified file.  The method will ensure that text provided is a valid filepath.
     *
     * O(1) - no complex computation, just a check for if a text supplied is a filepath.
     *
     * @param nameOfDesiredFile The name of the file for which the user would be asked to provide the file path.
     * @return A valid file path.
     */
    String requestFilePath(String nameOfDesiredFile){
        boolean runTillSuccessOrAbort = true;
        String filepath = "";

        while (runTillSuccessOrAbort) {
            menuRender.showFilePathRequest(nameOfDesiredFile);

            filepath = inputHandler.getLineOfText();
            if (FilesHandler.isStringAFilePath(filepath)) {
                runTillSuccessOrAbort = false;
            }
            else{
                menuRender.showWrongFilepathError();
            }
        }
        return filepath;
    }
}
