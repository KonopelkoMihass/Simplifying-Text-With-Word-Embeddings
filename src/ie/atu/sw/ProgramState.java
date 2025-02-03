package ie.atu.sw;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * Enumerator used to indicate the state of the program.  States are:
 * - OnStandby -> Indicates that the program was just launched and user has not performed anything.
 * - StartExecution -> Indicates that program is ready to start simplification
 * - StopProgram -> Indicates that user requested the end of the program.
 */
public enum ProgramState {

    /**
     * OnStandby -> Indicates that the program was just launched and user has not performed anything.
     */
    OnStandby,

    /**
     * StartExecution -> Indicates that program is ready to start simplification
     */
    StartExecution,

    /**
     * StartExecution -> Indicates that program is ready to start simplification
     */
    StopProgram
}

