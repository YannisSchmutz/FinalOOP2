package streamsExamples.compReduce;


import com.sun.tools.javac.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ComplexReduceExample1 {

    public static void main(String[] args) throws IOException {

        MyPair<Integer, Integer> aggregate = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"))
                .map(line -> Integer.parseInt(line.split(",")[2])) // extract age column
                .reduce(new MyPair<>(0, 0),   // identity value
                        (pair, x) -> new MyPair<>(pair.getFirst() + 1, pair.getSecond() + x),     // accumulator function
                        (p1, p2) -> new MyPair<>(p1.getFirst() + p2.getFirst(), p1.getSecond() + p2.getSecond()));

        int count = aggregate.getFirst();
        int sum = aggregate.getSecond();
        if (count > 0) {
            System.out.printf("average age: %.1f\n", ((double) sum) / count);
        } else {
            System.out.println("count zero, average undefined");

        }
    }
}
