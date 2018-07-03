package streamsExamples;

import java.util.stream.Stream;

public class SimpleAllMatch {

    public static void main(String[] args){


        //boolean allMales = Files.lines(Paths.get("persons.csv")) .map(line -> line.split(",")[3])
        boolean allMales = Stream.of("1,2,3,M", "1,2,3,M", "1,2,3,M")
                .map(line -> line.split(",")[3])
                .allMatch(gender -> gender.toUpperCase().equals("M"));
        System.out.println("all males: " + allMales);


        boolean allMales2 = Stream.of("1,2,3,M", "1,2,3,M", "1,2,3,F")
                .map(line -> line.split(",")[3])
                .allMatch(gender -> gender.toUpperCase().equals("M"));
        System.out.println("all males: " + allMales2);

    }
}
