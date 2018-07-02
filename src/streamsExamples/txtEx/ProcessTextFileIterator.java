package streamsExamples.txtEx;


import java.io.FileNotFoundException;

public class ProcessTextFileIterator {

    private static final int USER_ID_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int AGE_COLUMN = 2;
    private static final int GENDER_COLUMN = 3;

    public static void main(String[] args) throws FileNotFoundException {

        TextFileLineIterator lines = new TextFileLineIterator("src/streamsExamples/txtEx/persons.csv");

        int count = 0;
        double ageSum = 0;
        while (lines.hasNext()) {      // iterate over every line in the file
            String line = lines.next();  // get next text line
            String[] columns = line.split(","); // split text line at the comma
            if (columns[GENDER_COLUMN].toUpperCase().equals("F")) {  // filter out females only
                ageSum += Double.parseDouble(columns[AGE_COLUMN]);     // extract and parse 'age' column
                count++;
            }
        }
        System.out.println("females:                " + count);
        System.out.println("average age of females: " + (ageSum/count));
    }

}