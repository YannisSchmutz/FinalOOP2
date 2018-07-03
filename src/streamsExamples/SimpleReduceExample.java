package streamsExamples;

import java.util.Optional;
import java.util.stream.Stream;

public class SimpleReduceExample {

    public static void main(String[] args){

        Stream<Integer> s = Stream.of(1,3,4,8,7,2,5,9,6);
        Optional<Integer> sum = s.reduce((x, y) -> x+y);
        //System.out.println(sum);
        sum.ifPresent(x -> System.out.println("sum = " + x));


        // Same with start value
        Stream<Integer> s2 = Stream.of(1,3,4,8,7,2,5,9,6);
        int sum2 = s2.reduce(10, (x, y) -> x + y);
        System.out.println(sum2);

    }
}
