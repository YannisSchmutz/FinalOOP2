package generalFileAnalyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * This class prints a report of the contents of a file
 *  Number of characters, words, lines and number of a specific letter combination (in this case "at")
 *
 */
public class FileAnalyzer {

    /**
     * Main program.
     *
     * @param args unused
     * @throws IOException if a file cannot be opened, read, or written.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("FileAnalyzer");

        // Reads user input
        System.out.println("Filename: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        in.close();

        System.out.println(name);


        FileCounter counter = new FileCounter();
        // CWD will start in "FinalOOP2"
        counter.read("src/generalFileAnalyzer/" + name);

        System.out.println("Characters: " + counter.getCharacterCount());
        System.out.println("Words: " + counter.getWordCount());
        System.out.println("Lines: " + counter.getLineCount());
        System.out.println("Nbr of 'at': " + counter.getNbrAt());

    }
}
