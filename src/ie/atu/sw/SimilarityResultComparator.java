package ie.atu.sw;

import java.util.Comparator;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class was written to do a comparison operation for SimilarityResult records and is supplied to the collection
 * where the records will be stored (TreeMap).
 */
class SimilarityResultComparator implements Comparator<SimilarityResult> {

    /**
     * This is an override of a compare method that does comparison using SimilarityScore record's score variable.
     *
     * O(1) - comparison returns 1 of the 3 results: less than (-1), more than (1) or same (0).
     *
     * @return the result of comparison.
     */
    public int compare(SimilarityResult o1, SimilarityResult o2) {
        // Sort in descending order to get the best score at the root of the tree
        return Double.compare(o1.score(), o2.score());
    }
}