package readAndWriteFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Reads all lines of a file and sends them to an output file, preceded by line numbers. Uses the
 * NIO API available since Java 7
 *
 * @author lua1
 */
public class FileModifier {

    /**
     * Main program.
     *
     * @param args unused
     */
    public static void main(String[] args) throws IOException {
        System.out.println("FileModifier");

        // Reads file
        Scanner console = new Scanner(System.in);
        System.out.print("Input file: ");
        String inputFileName = console.next();
        System.out.print("Output file: ");
        String outputFileName = console.next();
        console.close();

        Path in = Paths.get("src/readAndWriteFile/" + inputFileName);

        Path out = Paths.get("src/readAndWriteFile/" + outputFileName);

        List<String> lines = Files.readAllLines(in);

        for (int i = 0; i < lines.size(); i++) {

            String s = lines.get(i);
            lines.set(i, "/* " + (i + 1) + " */ " + s);

        }

        Files.write(out, lines);
        System.out.print("Done!");
    }
}
