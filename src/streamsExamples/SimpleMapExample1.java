package streamsExamples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleMapExample1 {

    public static void main(String[] args) throws FileNotFoundException, IOException{

        Stream<String> stream = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));
        Stream<String> nameString = stream.map(line -> line.split(",")[1]);
        nameString.forEach(System.out::println);

        // Todo: "stream" is exhausted, create a new one
        Stream<String> stream2 = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));
        // IntStream uses special "mapToInt" function
        IntStream ageStream = stream2.mapToInt(line -> Integer.parseInt(line.split(",")[2]));
        ageStream.forEach(System.out::println);
    }
}
