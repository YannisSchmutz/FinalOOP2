package exceptions1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * This class reads a text file line by line and computes the number of lines
 * Contains UNHANDLED EXCEPTIONS (CTE) !!!!
 * @author lua1
 */

public class FileProg1 {
    /**
     * Main program.
     *
     * @param args unused
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader reader;
        int lineNumber = 0;

        // Unhandled exception type FileNotFoundException
        reader = new FileReader("src/exceptions1/in.txt");

        Scanner in = new Scanner(reader);

        while (in.hasNextLine()) {
            in.nextLine();

            lineNumber++;
        }

        //Unhandled exception type IOException
        reader.close();

        in.close();

        System.out.println("Anzahl der Zeilen: " + lineNumber);
        System.out.println("Done!");

    }
}
