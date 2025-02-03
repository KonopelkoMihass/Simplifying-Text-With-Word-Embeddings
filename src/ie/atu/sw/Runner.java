package ie.atu.sw;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class is responsible for instantiating the program and running it in the main function.
 */
public class Runner {

	/**
	 * This is the main function.
	 *
	 * @param args A set of arguments that can be passed to the main function when launching the application.
	 */
	public static void main(String[] args) throws Exception {
		Program program = new Program();
		program.run();
	}
}