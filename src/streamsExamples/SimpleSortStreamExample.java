package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class SimpleSortStreamExample {

    public static void main(String[] args) throws IOException {
        // Todo:
        // todo: Note: Sorting is a "blocking" operation, i.e., the entire stream needs to be read into memory.
        // todo        -> Sorting does not work on unbounded streams.

        Stream<String> stream = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));
        Stream<String> sortedStream = stream
                .map(line -> line.split(","))
                .sorted(Comparator.comparingInt(r -> Integer.parseInt(r[2])))   // Sort by age
                .map(Arrays::toString);
        sortedStream.forEach(System.out::println);


        System.out.println("****************");

        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(","))
                .sorted(Comparator.comparing(n -> n[1]))        // Sort by name
                .map(Arrays::toString)
                .forEach(System.out::println);


    }
}
