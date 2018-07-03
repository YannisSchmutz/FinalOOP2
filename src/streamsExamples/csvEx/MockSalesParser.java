package streamsExamples.csvEx;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class MockSalesParser {

    // transaction_id, date, time, customer_id, department, amount
    private static int TRANSACTION_ID = 0;
    private static int DATE = 1;
    private static int TIME = 2;
    private static int CUSTOMER_ID = 3;
    private static int DEPARTMENT = 4;
    private static int AMOUNT = 5;

    public static void main(String[] args) throws IOException {
        /*
        Use Java Streams to implement the following queries:
            * Query 1: Show the first 10 sales records.
            * Query 2: Show all sales records for customer with ID 1.
            * Query 3: Show the first 5 sales records in the "Books" department.
            * Query 4: Use sorted() to extract the sales record with the lowest amount in the "Baby" department.
            * Query 5: Use sorted() to extract the sales record the larges amount in the "Baby" department.
         */

        // * Query 1: Show the first 10 sales records.
        System.out.println("1 ---------------------------------");
        Files.lines(Paths.get("src/streamsExamples/csvEx/mocksales.csv"))
                .skip(1)
                .limit(10)
                .forEach(System.out::println);

        // * Query 2: Show all sales records for customer with ID 1.
        System.out.println("2 ---------------------------------");
        Files.lines(Paths.get("src/streamsExamples/csvEx/mocksales.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(col -> Integer.parseInt(col[CUSTOMER_ID]) == 1)
                .map(Arrays::toString)
                .forEach(System.out::println);

        // * Query 3: Show the first 5 sales records in the "Books" department.
        System.out.println("3 ---------------------------------");
        Files.lines(Paths.get("src/streamsExamples/csvEx/mocksales.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(col -> col[DEPARTMENT].equals("Books"))
                .limit(5)
                .map(Arrays::toString)
                .forEach(System.out::println);

        // * Query 4: Use sorted() to extract the sales record with the lowest amount in the "Baby" department.
        System.out.println("4 ---------------------------------");
        Files.lines(Paths.get("src/streamsExamples/csvEx/mocksales.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(col -> col[DEPARTMENT].equals("Baby"))
                .sorted(Comparator.comparingDouble(a -> Double.parseDouble(a[AMOUNT])))
                .limit(1)
                .map(Arrays::toString)
                .forEach(System.out::println);


        // * Query 5: Use sorted() to extract the sales record the larges amount in the "Baby" department.
        System.out.println("5 ---------------------------------");
        Files.lines(Paths.get("src/streamsExamples/csvEx/mocksales.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(col -> col[DEPARTMENT].equals("Baby"))
                .sorted(Comparator.comparingDouble(a -> -Double.parseDouble(a[AMOUNT])))    // todo: sort reverse by putting a - in front!!
                //.limit(1)
                .findFirst()
                .map(Arrays::toString)
                //.forEach(System.out::println);
                .ifPresent(p -> System.out.println("First: " + p));



    }
}
