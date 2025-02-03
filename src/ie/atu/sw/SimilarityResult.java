package ie.atu.sw;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This is a record that will store the word and its similarity score during the simplification procedure.
 */
public record SimilarityResult (String word, double score) {}