package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class SimpleFlatMapStreamExample {

    public static void main(String[] args) throws IOException {

        Stream<String> stream = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));
        // Extract columns elements using flatMap
        Stream<String> columns_ = stream.flatMap(line -> {
            String[] colArray = line.split(","); // Split string into array at "," and
            Stream<String> cols = Arrays.stream(colArray); // convert String array into Stream using
            return cols; // Arrays helper class
        });

        Stream<String> stream2 = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));
        // Same as above but as a one-liner.
        Stream<String> columns = stream2.flatMap(line -> Arrays.stream(line.split(",")));
        columns.forEach(System.out::println);
    }
}
