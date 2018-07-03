package streamsExamples;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InfiniteStream {

    public static void main(String[] args){

        Stream<Integer> fibonnaciStream = Stream.generate(new Supplier<Integer>() {
            int next = 1;
            int last = 0;

            @Override
            public Integer get() {
                int result = next;
                next += last;
                last = result;
                return result;
            } });

        System.out.println(fibonnaciStream.limit(10).map(Object::toString) .collect(Collectors.joining(", ")));

    }
}

