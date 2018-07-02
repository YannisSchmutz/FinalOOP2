package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleSkipNLimitExample {

    public static void main(String[] args) throws IOException {


        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .skip(5)
                .forEach(System.out::println);

        System.out.println("*********");

        Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .limit(3)
                .forEach(System.out::println);
    }
}
