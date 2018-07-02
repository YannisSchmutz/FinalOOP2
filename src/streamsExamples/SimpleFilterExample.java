package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class SimpleFilterExample {

    public static void main(String[] args) throws IOException {
        Stream<String> stream = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));

        Stream<String> maleStream = stream
                .map(line -> line.split(","))
                .filter(cols -> cols[3].toUpperCase().equals("M"))
                .map(Arrays::toString);

        maleStream.forEach(System.out::println);

    }
}
