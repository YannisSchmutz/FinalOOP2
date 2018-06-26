package exceptions1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * This class reads a text file line by line and computes the number of lines
 * 
 * The exceptions are handled with try-catch-finally (bad example)
 * 
 * @author lua1
 */

public class FileProg2Fin {
  /** Main program.
   * @param args unused
   */
  public static void main(String[] args) {

    FileReader reader = null;
    int lineNumber = 0;
    try {
      reader = new FileReader("src/exceptions1/in.txt");

      Scanner in = new Scanner(reader);

      while (in.hasNextLine()) {
        in.nextLine();

        lineNumber++;
      }
      in.close();
    } catch (IOException e) {
      System.err.println("An Error occured: " + e.getMessage());
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    System.out.println("\nAnzahl der Zeilen: " + lineNumber);
    System.out.println("Done!");

  }
}
