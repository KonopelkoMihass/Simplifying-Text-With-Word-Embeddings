package ie.atu.sw;

public enum ProgramState {
    // Indicates that the program was just launched and user has not performed anything.
    JUST_LAUNCHED,
    // Indicates that user has loaded a first file, be it embeddings or Google 1000 Words.
    LOADED_FIRST_FILE,
    // Indicates that both files are loaded, and that user can progress to the next step of the program.
    LOADED_ALL_FILES,
    // Indicates that user has selected where to output result
    // OR/AND
    // Indicates that program is ready to start simplification
    PROGRAM_READY,
    // Indicates the start of the last stage and that user would be prompted to enter text.
    START_TEXT_INPUT,
    // Indicates the beginning of simplification method.
    START_EXECUTION,
    // Indicates that simplification was completed
    SIMPLIFICATION_COMPLETE,
    // Indicates that the resulting text was saved into the output.
    SAVING_COMPLETE,
    // Indicates that user requested the end of the program.
    STOP_PROGRAM
}
