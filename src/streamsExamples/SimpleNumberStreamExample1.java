package streamsExamples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleNumberStreamExample1 {

    public static void main(String[] args){

        // Create a list of random numbers.
        Random rnd = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            numbers.add(rnd.nextInt(1001));
        }

        // int sum = numbers.parallelStream()
        int sum = numbers.stream() // creates Stream<Integer> from List<Integer>
                .filter(n -> n % 2 == 0) // filters even numbers
                .mapToInt(n -> n - 500)
                .sum();

        System.out.println(sum);

    }
}
