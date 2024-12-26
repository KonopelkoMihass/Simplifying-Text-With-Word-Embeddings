package ie.atu.sw;

import java.io.IOException;

record FilePathsStorage(String embedding, String google1000Words, String output) {}

public class MenuHandler {
    // Will store the user response to the menu as a character.  Default is a space.
    char userResponse = ' ';

    // Will store current program state.
    ProgramState currenProgramState;

    // Stores file paths of different essential files  as strings.
    FilePathsStorage filePaths;

    public MenuHandler() {
        currenProgramState = ProgramState.JUST_LAUNCHED;
        filePaths = new FilePathsStorage("","","/out.txt");
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
        System.out.println("(1) Specify Embeddings File");
        System.out.println("(2) Specify Google 1000 File");
        System.out.println("(3) Specify an Output File (default: ./out.txt)");
        System.out.println("(4) Execute, Analyse and Report");
        System.out.println("(?) Quit");
    }

    public void showSelector() {
        //Output a menu of options and solicit text from the user
        System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
        System.out.print("Select Option [1-4]>");
        System.out.println();
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
        // Get response to the menu prompt, then handle it. Parse it as a character,
        // which will always take the first letter of a string.
        try { userResponse = (char) System.in.read(); }
        catch (IOException e) { throw new RuntimeException(e); }

        switch (userResponse) {
            case '1':
                specifyEmbeddingsFile();
                break;
            case '2':
                specifyGoogle1000File();
                break;
            case '3':
                specifyOutputFile();
                break;
            case '4':
                runProgram();
                break;
            case 'q':
                quit();
                break;
        }
    }

    void specifyEmbeddingsFile() {

    }

    void specifyGoogle1000File() {

    }

    void specifyOutputFile() {

    }
    void runProgram() {

    }

    void quit() {
        currenProgramState = ProgramState.STOP_PROGRAM;
    }

}
