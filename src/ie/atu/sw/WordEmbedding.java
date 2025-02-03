package ie.atu.sw;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class wraps around the list of doubles that stores embeddings (for a single word).
 * The class provides methods to access the list amd perform mathematical calculations necessary to perform similarity search.
 * Math methods are used for a Cosine Similarity Search Algorithm.
 */
public class WordEmbedding {

    /**
     * Contains a set of doubles that make up an embedding and are used to calculate similarity score.
     */
    List<Double> embeddings;

    /**
     * Pre-calculated part of an equation to measure distance between two embeddings.
     */
    double unSquaredRootedDistance;

    static final int TOTAL_EMBEDDINGS_PER_WORD = 50;


    /**
     * Constructor.
     * Takes in the stringified set of embeddings and converts them to the list of doubles, as well as calculates the un-squared-rooted distance.
     *
     * O(1) - Although it iterates though a list of strings, converts them, and inserts them into embedding, there is always a set number
     * of elements, which is 50.
     *
     * @param stringEmb a set of embeddings in a form of a comma-separated string.
     */
    WordEmbedding (List<String> stringEmb) {
        // Used this site to create to effectively convert list of strings into a list of doubles:
        // https://www.geeksforgeeks.org/program-to-convert-list-of-string-to-list-of-integer-in-java/
        embeddings = stringEmb.stream()
                .map(Double::valueOf)
                .collect(Collectors.toList());

        calculateUnSquaredRootedDistance();
    }

    /**
     * Get method that returns a list of embeddings.
     *
     * O(1) - simple getter.
     *
     * @return The List of doubles containing embeddings.
     */
    public List<Double> getList() {
        return embeddings;
    }

    /**
     * Get method that returns an un-squared-rooted distance of this embedding.
     *
     * O(1) - simple getter.
     *
     * @return The un-squared-rooted distance.
     */
    public double getUnSquaredRootedDistance() {
        return unSquaredRootedDistance;
    }

    /**
     * Calculates Dot Product and returns it.
     *
     * O(1) - Although it has a loop (which would make it O(n) ), the number of iterations is based on a number
     * of embeddings per word, which is always the same - 50.
     *
     * @param anotherEmbeddings second embedding which is used to calculate the dot product.
     *
     * @return The dot product.
     */
    public double calculateDotProduct(WordEmbedding anotherEmbeddings) {
        // Stores the result of operation.
        double result = 0;

        // Loop through features of two embeddings, perform multiplication of parallel features and addition to the result.
        for (int i = 0; i < TOTAL_EMBEDDINGS_PER_WORD; i++) {
            result += (embeddings.get(i) * anotherEmbeddings.getList().get(i));
        }
        // Returns results.
        return result;
    }

    /**
     * Calculates Distance and returns it.
     *
     * O(1) - simple getter.
     *
     * @param anotherEmbeddings second embedding which is used to calculate the distance.
     *
     * @return The distance.
     */
    public double calculateDistance(WordEmbedding anotherEmbeddings){
        return Math.sqrt(unSquaredRootedDistance * anotherEmbeddings.getUnSquaredRootedDistance());
    }

    /**
     * Calculates the part of a Distance, specifically part prior to square rooting.
     *
     * O(1) - same as with calculateDotProduct() - it has a loop, but it will only iterate 50 times.
     */
    private void calculateUnSquaredRootedDistance() {
        unSquaredRootedDistance = 0;
        // Loop through features of embedding, perform double multiplication
        // of each feature and addition to the result.
        for (var embedding : embeddings){
            unSquaredRootedDistance += Math.pow(embedding, 2);
        }
    }
}



