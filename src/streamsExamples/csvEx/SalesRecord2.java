package streamsExamples.csvEx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


/** A couple of Stream queries on the mocksales.csv data file. */
public class SalesRecord2 {

    /** Class to represent a single sales record. */
    static class SalesRecord {
        private final int transactionId;
        private final LocalDateTime timestamp;
        private final int customerId;
        private final String department;
        private final double amount;

        private SalesRecord(int transactionId, LocalDateTime timestamp,
                            int customerId, String department, double amount) {

            this.transactionId = transactionId;
            this.timestamp = timestamp;
            this.customerId = customerId;
            this.department = department;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.format("%5d %20s %4d %15s %.2f", transactionId, timestamp, customerId,
                    department, amount);
        }

        /** Create sales record from parsing a text line. */
        static SalesRecord fromTextLine(String line) {
            String[] fields = line.split(",");
            if (fields.length != 6) {
                throw new ParseException("6 fields expected, found " + fields.length);
            }
            int transactionId;
            try {
                transactionId = Integer.parseInt(fields[0]);
            } catch (NumberFormatException e) {
                throw new ParseException("cannot parse transaction ID field", e);
            }
            LocalDateTime timestamp;
            try {
                timestamp = LocalDateTime.parse(fields[1] + " " + fields[2],
                        DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss"));
            } catch(DateTimeParseException e) {
                throw new ParseException("cannot parse date and time fields", e);
            }
            int customerId;
            try {
                customerId = Integer.parseInt(fields[3]);
            } catch (NumberFormatException e) {
                throw new ParseException("cannot parse customer ID field", e);
            }

            String department = fields[4];
            double amount;
            try {
                amount = Double.parseDouble(fields[5]);
            } catch (NumberFormatException e) {
                throw new ParseException("cannot parse amount field", e);
            }
            return new SalesRecord(transactionId, timestamp, customerId, department, amount);
        }
    }

    /** Exception of this class are thrown if the record line cannot be parsed in the CSV file. */
    static class ParseException extends RuntimeException {
        ParseException(String message) {
            super(message);
        }
        ParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private static final String FILE_NAME = "mocksales.csv";

    /** Returns a stream of SalesRecord from the mock sales CSV file */
    private static Stream<SalesRecord> createParsedSalesStream() {
        try {
            return Files.lines(Paths.get(FILE_NAME))
                    .skip(1)        // skip header line
                    .map(SalesRecord::fromTextLine);
        } catch(IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    /** Implementation of a couple of queries. */
    public static void main(String[] args) {

        // Query 1
        final int customerId = 4;
        System.out.println("Query 1: Entries for Customer with ID " + customerId);
        long entriesForCustomer = createParsedSalesStream()
                .filter(c -> c.customerId == 4)
                .count();
        System.out.println("result: " + entriesForCustomer + " entries");

        // Query 2
        System.out.println("Query 2: Most expensive purchase made in Grocery department.");
        Optional<SalesRecord> maxPurchase = createParsedSalesStream()
                .filter(c -> c.department.equals("Grocery"))
                .max(Comparator.comparingDouble(record -> record.amount));
        maxPurchase.ifPresent(System.out::println);

        // Query 3
        System.out.println("Query 3: Top-3 least expensive purchases made in Kids department");
        createParsedSalesStream()
                .filter(c -> c.department.equals("Kids"))
                .sorted(Comparator.comparingDouble(record -> record.amount))
                .limit(3)
                .forEach(System.out::println);

        // Query 4
        System.out.println("Query 4: Number of sales transactions for each department");
        Map<String, Integer> countByDepartment = createParsedSalesStream()
                .reduce(new HashMap<>(),
                        (agg, record) -> {
                            agg.put(record.department, agg.getOrDefault(record.department, 0) + 1);
                            return agg;
                        },
                        (agg1, agg2) -> {
                            agg2.forEach((dep, count) ->
                                    agg1.put(dep, agg1.getOrDefault(dep, 0) + count));
                            return agg1;
                        });
        countByDepartment.forEach(
                (department, count) -> System.out.printf("%20s %3d\n", department, count));

        // Query 5
        System.out.println("Query 5: Revenue by department in descending order");
        Map<String, Double> revenueByDepartment = createParsedSalesStream()
                .reduce(new HashMap<>(),
                        (agg, record) -> {
                            agg.put(record.department,
                                    agg.getOrDefault(record.department, 0.0) + record.amount);
                            return agg;
                        },
                        (agg1, agg2) -> {
                            agg2.forEach((dep, sum) ->
                                    agg1.put(dep, agg1.getOrDefault(dep, 0.0) + sum));
                            return agg1;
                        });
        revenueByDepartment.entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> -entry.getValue()))
                .forEach(entry -> System.out.printf("%20s %.2f\n", entry.getKey(), entry.getValue()));
    }
}
