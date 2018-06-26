package internationalization;

import java.text.ChoiceFormat;
import java.text.MessageFormat;

public class ChoiceFormatter {
  
  /**
   * Main program.
   * @param args unused
   */
  public static void main(String[] args) {

    // ChoiceFormat directly
    double[] values = {0, 1, 2};
    String[] output = {"no apples", "an apple", "apples"};
    ChoiceFormat cf = new ChoiceFormat(values, output);
    String one = cf.format(1);
    System.out.println(one);

    // in combination with MessageFormat
    MessageFormat mymsgfmt = new MessageFormat(
        "There {1, choice, 0#are no files|1#is one file|1<are {1, number, integer} files} in {0}.");
    Object[] messageArgs = {"c:/myPrograms", null};
    for (int fileNum = 0; fileNum < 8; fileNum++) {
      messageArgs[1] = new Integer(fileNum);
      System.out.println(mymsgfmt.format(messageArgs));
    }
  }
}
