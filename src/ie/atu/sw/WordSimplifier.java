package ie.atu.sw;

import java.util.*;

/**
 * @author Mihass Konopelko
 * @version 1.0
 * @since 1.8
 *
 * This class contains calculated embeddings and performs the Cosine Similarity Search per word in the supplied text.
 */
public class WordSimplifier {

	/**
	 * This contains the key-value pair of a word and an embeddings from GloVe.
	 */
	HashMap<String, WordEmbedding> gloveEmbeddings = new HashMap<>();

	/**
	 * This contains the key-value pair of a word and an embeddings build from Google 100 words. GloVe embeddings are used
	 * to make this embedding.
	 */
	HashMap<String, WordEmbedding> googleEmbeddings = new HashMap<>();


	/**
	 * An indicator that all embeddings were calculated.
	 */
	boolean calculatedAllEmbeddings = false;

	/**
	 * This method populates GloVe Embeddings by taking a line from raw string line (from TextFileInput), splits up and
	 * populates GloVe Hashmap.  Afterward, a raw Google text is iterated through and used to create Google Embeddings.
	 *
	 * O(n+m) - has two separate loops, one that prepares GloVe Embeddings, and another that prepares Google Embeddings.
	 *
	 * @param rawGloveEmbeddings An ArrayList of strings containing lines with a word and numerical values separated by comma.
	 * @param googleWords A list of 1000 words.
	 */
	public void prepareEmbeddings(ArrayList<String> rawGloveEmbeddings, ArrayList<String> googleWords) {
		for (String line : rawGloveEmbeddings) {
			// Split into 51 segment.
			String[] parts = line.split(",");

			// First entry is always a word.
			String word = parts[0];

			// Removes first element (a word) from partitions using Array.copyOfRange method and saves it
			// as a list of strings.
			List<String> strEmbeddingsArray = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));

			gloveEmbeddings.put(word, new WordEmbedding(strEmbeddingsArray));
		}
		// Interesting observation: rawEmbedding had 60100 lines in it, a line per word.  Once I split it into
		// the proper embedding using HashMap, the resulting number of embeddings was 59768.  Few minutes of
		// investigation revealed that the raw file contained duplicates, like a word "shoes" being present
		// 3 times with the same values.

		// Iterate through Google 1000 words and attach embeddings from GloVe Embeddings.
		for (String word : googleWords) {
			googleEmbeddings.put(word, gloveEmbeddings.get(word));
		}
		calculatedAllEmbeddings = true;
	}


	/**
	 * Method that indicates that all embeddings were calculated.
	 *
	 * O(1) - simple return of a boolean.
	 *
	 * @return true if prepareEmbeddings method ran anc completed once.
	 */
	public boolean areAllEmbeddingsCalculated() {
		return calculatedAllEmbeddings;
	}


	/**
	 * This method takes in the lines of text to simplify, calls method to split lines into separate words, then runs a
	 * list of checks outlined below, after which the words are passed to a method for similarity search.
	 * There are many edge cases which are taken into consideration:
	 * - If the original word is absent in GloVe Embeddings, it is left as it is.
	 * - Capital Letters are preserved (for names and first letters in the sentence).
	 * - Numbers and sentence marks ( . , ? ! ) are preserved.
	 * - Line Breaks are preserved.
	 *
	 * O(2nm) - I may be off, as this method is rather colossal (and alas I have no time to refactor and improve it).
	 *  - There is a O(nm) method at a start that splits the lines of text into words.
	 *  - Then there is a main loop for each word in the text (assume it is "n" as in for each "word".
	 *  - Within loop, there are multiple check of which check word's characters ("c") +- for a first or last character.
	 *  - Then there is O(e) method that does simplification, where "e" is a size of embedding.
	 *
	 *  Consensus is - there are two loops, one for each word in a text, another is for each word in embedding.
	 *  Both are defined before the start of this loop and vary in size, hence why it is O(2nm)
	 *
	 * @param text the input text to simplify.
	 *
	 * @return simplified text.
	 */
	public String simplify(ArrayList<String> text) {
		StringBuilder simplifiedText = new StringBuilder();
		ArrayList<String> splitText = splitLinesIntoWords(text);

		for (String word : splitText) {
			// If word is a new line, append and continue.
			if (word.equals("\n")) {
				simplifiedText.append(word);
				continue;
			}

			// Checks for non-alphabet and put them in simplified text immediately.
			// https://stackoverflow.com/questions/10575624/how-do-i-check-if-a-string-contains-only-numbers-and-not-letters
			if (word.matches("[^a-zA-Z]+")) {
				simplifiedText.append(word);
				simplifiedText.append(" ");
				continue;
			}

			// Lastly, check for periods and other marks at the end of the word.
			// Save them for later.
			char lastChar = word.charAt(word.length() - 1);
			boolean notAlphabetPresentAtEnd = !Character.isLetter(lastChar);

			// Clean the word of non-alphabet.
			word = word.replaceAll("[^a-zA-Z]", "");

			// Check for a capital letter.
			// https://www.baeldung.com/java-check-first-letter-uppercase
			boolean hadCapitalLetter = word.matches("[A-Z]\\w*");

			// Replace with simpler word.
			String similarWord = performSimilaritySearch(word.toLowerCase());

			if (hadCapitalLetter) {
				similarWord = Character.toTitleCase(similarWord.charAt(0)) + similarWord.substring(1);
			}

			simplifiedText.append(similarWord);

			if (notAlphabetPresentAtEnd) simplifiedText.append(lastChar);
			simplifiedText.append(" ");
		}
		return simplifiedText.toString();
	}

	/**
	 * This splits a line of text into a list of words.
	 *
	 * O(n*m) - Iterates though the array of lines (n) and separates them into word (m).
	 *
	 * @param lines target text line to split.
	 *
	 * @return the ArrayList of words.
	 */
	ArrayList<String> splitLinesIntoWords(ArrayList<String> lines) {
		ArrayList<String> splitText = new ArrayList<String>();
		for (String line : lines) {
			splitText.addAll(Arrays.asList(line.split(" ")));
			splitText.add("\n");
		}
		return splitText;
	}

	/**
	 * This method encapsulates the similarity search logic.
	 *
	 * @param word the word to simplify.
	 *
	 * O(n) - iterates through a Google embeddings. Although it is stated that there is precisely 1000 words, I think it
	 * is a stretch to call it O(1) as user may load in "Google 2000 words" instead. Insertion into a tree is O(log n) and
	 * access of hashmap is O (log n)
	 *
	 * @return the simplified word.
	 */
	String performSimilaritySearch(String word) {
		// We get word embedding for parameter word.
		WordEmbedding wordEmbedding = gloveEmbeddings.get(word);

		// If word is not present, return it.
		if (wordEmbedding == null) {
			return word;
		}

		TreeSet<SimilarityResult> results = new TreeSet<>(new SimilarityResultComparator());

		for (Map.Entry<String, WordEmbedding> entry : googleEmbeddings.entrySet()) {
			String keyWord = entry.getKey();
			WordEmbedding testEmbedding = entry.getValue();

			// Calculate Dot Product and distance.
			double dotProduct = wordEmbedding.calculateDotProduct(testEmbedding);
			double distance = wordEmbedding.calculateDistance(testEmbedding);

			double similarity = dotProduct / distance;

			results.add(new SimilarityResult(keyWord, similarity));
		}

		return results.last().word();
	}
}
