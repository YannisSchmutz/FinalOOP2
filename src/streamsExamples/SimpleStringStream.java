package streamsExamples;

import java.util.stream.Stream;

public class SimpleStringStream {

    public static void main(String[] args){
        Stream<String> stringStream = Stream.of("Alice", "Bob", "Eve", "Gaby");

        stringStream.forEach(System.out::println);

    }
}
