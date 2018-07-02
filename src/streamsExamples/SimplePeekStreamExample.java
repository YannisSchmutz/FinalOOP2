package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SimplePeekStreamExample {

    public static void main(String[] args) throws IOException {


        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(","))
                .peek(fields -> System.out.println("Before filter: " + Arrays.toString(fields)))
                .filter(fields -> fields[3].toUpperCase().equals("M"))
                .peek(fields -> System.out.println("After filter: " + Arrays.toString(fields)))
                //.mapToInt(fields -> Integer.parseInt(fields[2]))
                .map(Arrays::toString)
                .forEach(System.out::println);

        System.out.println("***********************************");

        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(","))
                .peek(fields -> System.out.println("Before filter: " + Arrays.toString(fields)))
                .filter(fields -> fields[3].toUpperCase().equals("M"))
                .peek(fields -> System.out.println("After filter: " + Arrays.toString(fields)))
                .mapToInt(fields -> Integer.parseInt(fields[2]))
                .forEach(System.out::println);
    }
}
