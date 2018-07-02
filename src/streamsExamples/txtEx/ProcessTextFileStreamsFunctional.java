package streamsExamples.txtEx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Stream;

public class ProcessTextFileStreamsFunctional {

    private static final int USER_ID_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int AGE_COLUMN = 2;
    private static final int GENDER_COLUMN = 3;
    private static int count = 0;
    private static double ageSum = 0;


    public static void main(String[] args) throws IOException {

        Stream<String> stream = Files.lines(Paths.get("src/streamsExamples/txtEx/persons.csv"));

        DoubleSummaryStatistics stats = Files.lines(Paths.get("persons.csv"))
                .map(line -> line.split(","))                                   // split text line at the comma
                .filter(columns -> columns[GENDER_COLUMN].toUpperCase().equals("F"))    // filter out females
                .mapToDouble(columns -> Double.parseDouble(columns[AGE_COLUMN]))        // parse age
                .summaryStatistics();                                               // terminal operator computes statistics min/max/count/sum

        System.out.println("females: " + stats.getCount());
        System.out.println("average age of females: " + stats.getAverage());

    }


}
