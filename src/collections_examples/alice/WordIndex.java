package collections_examples.alice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Builds an index from all words in a text file.
 * It skips all words in that are listed in second text file
 * that contains the stop words. 
 */
public class WordIndex {

  /**
   * A location of a word is line and a column number.
   */
  static class Location {

    int lineNumber;
    int columnNumber;

    Location(int line, int column) {
      lineNumber = line;
      columnNumber = column;
    }

    @Override
    public String toString() {
      return "(" + lineNumber + ", " + columnNumber + ")";
    }
  }

  /**
   * Locations is an ArrayList of Location.
   */
  static class Locations extends ArrayList<Location> {

    /**
     * Add new location .
     */
    void addLocation(int lineNumber, int column) {
      add(new Location(lineNumber, column));
    }
  }

  /**
   * Read the stop words from text file line by line.
   *
   * @param filename file containing the stop words (one per line).
   * @return A hashset containing the stop words.
   */
  private static Set<String> readStopWords(String filename) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filename));
    Set<String> stopWords = new HashSet<>();
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      stopWords.add(line);
    }
    return stopWords;
  }

  /**
   * Read text document and build word index after filtering out stop words.
   *
   * @param filename file containing the document to read
   * @param stopWords set containing the words to skip while building the index.
   * @return a TreeMap that maps from word Strings to Locations.
   */
  private static Map<String, Locations> buildWordIndex(String filename, Set<String> stopWords)
      throws FileNotFoundException {
    Map<String, Locations> index = new TreeMap<>();
    Scanner scanner = new Scanner(new File(filename));
    Pattern wordRegex = Pattern.compile("\\w+");
    for (int lineNumber = 1; scanner.hasNextLine(); lineNumber++) {
      String line = scanner.nextLine();
      Matcher matcher = wordRegex.matcher(line.toLowerCase());
      while (matcher.find()) {
        String word = matcher.group();
        if (!stopWords.contains(word)) {
          int column = matcher.start() + 1;

          Locations locs = index.get(word);
          if (locs == null) {
            // word encountered for the first time
            locs = new Locations();
            index.put(word, locs);
          }
          locs.addLocation(lineNumber, column);
        }
      }
    }
    return index;
  }

  /** Return the number of times a specific word appears */
  static int getNumOfOccurrencesOfWord(String word, Map<String, Locations> wordIndex) {
    Locations locs = wordIndex.get(word);
    if (locs == null) {
      return 0;
    } else {
      return locs.size();
    }
  }



  /**
   * Test program.
   */
  public static void main(String[] args) throws Exception {
    String documentFile = "alice_in_wonderland.txt";
    String stopwordsFile = "english_stopwords_nltk.txt";

    Set<String> stopWords = readStopWords(stopwordsFile);
    System.out.println("Stop Words: " + stopWords);

    Map<String, Locations> wordIndex = buildWordIndex(documentFile, stopWords);

    // Print the first 10 entries in the index.
    System.out.println("\nfirst entries: ");
    int outputCount = 0;
    for (Map.Entry<String, Locations> entry : wordIndex.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
      outputCount += 1;
      if (outputCount == 10) {
        break;
      }
    }

    // Print number of occurrences of the word "alice"
    String word = "alice";
    System.out.println(
        "\n'" + word + "' appears " + getNumOfOccurrencesOfWord(word, wordIndex) + " times.");

    // Print the most frequently occurring word
    String mostFrequentWord = "";
    int highestCount = 0;
    for (Map.Entry<String, Locations> entry : wordIndex.entrySet()) {
      int count = entry.getValue().size();
      if (count > highestCount) {
        highestCount = count;
        mostFrequentWord = entry.getKey();
      }
    }
    System.out.println("\nThe most frequent word is '"+mostFrequentWord+"'.");
    System.out.println("It appears "+highestCount+" times.");
  }

}
