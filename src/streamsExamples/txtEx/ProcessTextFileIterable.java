package streamsExamples.txtEx;

import java.io.FileNotFoundException;

public class ProcessTextFileIterable {


    public static void main(String [] args){

        int count = 0;
        double ageSum = 0;

        try {
            TextFileLineIterable iterable = new TextFileLineIterable("src/streamsExamples/txtEx/persons.csv");

            for (String line : iterable){
                System.out.println(line);
                String[] entries = line.split(",");
                if (entries[3].toUpperCase().equals("F")){
                    count++;
                    ageSum += Double.parseDouble(entries[2]);
                }
            }

            System.out.println("females:                " + count);
            System.out.println("average age of females: " + (ageSum/count));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
