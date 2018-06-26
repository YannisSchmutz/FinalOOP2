package internationalization;

import java.text.MessageFormat;
import java.util.Date;

public class MessageFormatTest {

  /**
   * Main program.
   * @param args unused
   */
  public static void main(String[] args) {

    //V1
    Object[] arguments =  { new Integer(7), new Date(System.currentTimeMillis()),
        "a disturbance in the Force" };
    String result =  MessageFormat.format(
        "At {1,time,short} on {1,date}, there was {2} on planet {0,number,integer}.",
        arguments); 

    System.out.println(result);

    //V2
    String result2 =  MessageFormat.format(
        "At {1,time,short} on {1,date}, there was {2} on planet {0,number,integer}.",
        new Integer(7), new Date(System.currentTimeMillis()),
        "a disturbance in the Force"); 

    System.out.println(result2);

    
  }

}
