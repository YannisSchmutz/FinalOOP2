package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SimpleDistinctStreamExample {

    public static void main(String[] args) throws IOException {

        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(",")[3])
                .distinct()
                .forEach(System.out::println);  // Just  M, F

        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(",")[1])
                .distinct()
                .forEach(System.out::println);  // all Names -> they are all distinct
    }
}
