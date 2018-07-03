package streamsExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectStreamDataExamples {

    public static void main(String[] args) throws IOException {

        // String Array
        String[] names = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(",")[1])
                .toArray(String[]::new); // Reference to array constructor is a matching generator

        for (String name : names) {
            System.out.println(name);
        }

        // Joined string
        String nameStr = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> line.split(",")[1])
                .collect(Collectors.joining(", "));

        System.out.println(nameStr);


        // List of Integers
        List<Integer> ages = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> Integer.parseInt(line.split(",")[2]))
                .collect(Collectors.toList());

        for (int age : ages) {
            System.out.println(age);
        }



    }
}
