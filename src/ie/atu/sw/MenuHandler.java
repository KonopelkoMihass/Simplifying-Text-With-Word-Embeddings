package ie.atu.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;

public class MenuHandler {
    // Will store the user response to the menu as a character.  Default is a space.
    char userResponse = ' ';

    // Will store current program state.
    ProgramState currenProgramState;

    String embeddingFilePath;
    String google1000WordsFilePath;
    String outputFilePath;


    public MenuHandler() {
        currenProgramState = ProgramState.JUST_LAUNCHED;
        embeddingFilePath = "";
        google1000WordsFilePath = "";
        outputFilePath = "./out.txt";
    }


    public void showMenuScreen() {
        //You should put the following code into a menu or Menu class
        System.out.println(ConsoleColour.WHITE);
        System.out.println("************************************************************");
        System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
        System.out.println("*                                                          *");
        System.out.println("*             Virtual Threaded Text Simplifier             *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");

    }

    public void showSelector() {
        //Output a menu of options and solicit text from the user
        System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
        System.out.println("Select Option:");
        System.out.println("(1) Specify Embeddings File");
        System.out.println("(2) Specify Google 1000 File");
        System.out.println("(3) Specify an Output File (default: ./out.txt)");
        System.out.println("(4) Execute, Analyse and Report");
        System.out.println("(q) Quit");

    }

    public boolean updateMenu() {
        showSelector();
        processUserResponse();

        // While program state is not quitting, return true and keep it running.
        return currenProgramState != ProgramState.STOP_PROGRAM;
    }

    public ProgramState getCurrenProgramState() {
        return currenProgramState;
    }

    void processUserResponse()  {
        userResponse = getUserInput();
        switch (userResponse) {
            case '1':
                embeddingFilePath = requestFilepath("GloVe Embedding File");
                break;
            case '2':
                google1000WordsFilePath = requestFilepath("Google 1000 Words File");
                break;
            case '3':
                outputFilePath = requestFilepath("Output File");
                break;
            case '4':
                runProgram();
                break;
            case 'q':
                quit();
                break;
        }
    }

    void runProgram() {
        if (!checkIfAllFilePathsFilled()) return;
        System.out.println(embeddingFilePath);
        System.out.println(google1000WordsFilePath);
        System.out.println(outputFilePath);
    }


    char getUserInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine().charAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    boolean checkIfAllFilePathsFilled() {
        boolean result = true;
        if (embeddingFilePath.isEmpty()) {
            System.out.println("Filepath is empty for the GloVe Embedding File");
            result = false;
        }
        if (google1000WordsFilePath.isEmpty()) {
            System.out.println("Filepath is empty for the Google 1000 Words File");
            result = false;
        }
        if (outputFilePath.isEmpty()) {
            System.out.println("Filepath is empty for the Output File");
            result = false;
        }
        return result;
    }

    void quit() {
        currenProgramState = ProgramState.STOP_PROGRAM;
    }

    String requestFilepath(String nameOfDesiredFile){
        boolean runTillSuccessOrAbort = true;
        String filepath = "";

        while (runTillSuccessOrAbort) {
            System.out.print("Enter the correct directory for the " + nameOfDesiredFile + " or type 'q' to cancel: ");
            System.out.println();
            filepath = System.console().readLine();

            if (filepath.equals("q")) {
                runTillSuccessOrAbort  = false;
                filepath = "";
            }

            File f = new File(filepath);
            if(f.exists() && !f.isDirectory()) {
                runTillSuccessOrAbort = false;
            }

        }
        return filepath;
    }

}
