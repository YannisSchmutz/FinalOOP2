package internationalization;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class ParseDate {
  /**
   * Main program.
   * @param args unused
   * @throws ParseException if the input was not correct
   */
  public static void main(String[] args) throws ParseException {
    DateFormat dateFormatterShort = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
    Date myDate = dateFormatterShort.parse("04.04.18");
    System.out.println(myDate);

    DateFormat dateFormatterMed = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    dateFormatterMed.setLenient(false);
    myDate = dateFormatterMed.parse("4.4.2018");
    System.out.println(myDate);

    DateFormat dateFormatterFull = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMAN);
    dateFormatterFull.setLenient(false);
    myDate = dateFormatterFull.parse("Mittwoch, 4. April 2018");
    
    System.out.println(myDate);


  }

}
