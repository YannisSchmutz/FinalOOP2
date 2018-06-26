package internationalization;

import java.text.Collator;
import java.util.Locale;

class CollatorStrengthDemo {
  static void compare(Collator col, String a, String b) {
    if (col.compare(a, b) < 0) {
      System.out.println(a + " < " + b);
    }  
    if (col.compare(a, b) == 0) {
      System.out.println(a + " = " + b);
    }  
    if (col.compare(a, b) > 0) {
      System.out.println(a + " > " + b);
    }  
  }

  public static void main(String[] args) {
    Collator col = Collator.getInstance(Locale.GERMAN);
    System.out.println("Strength = PRIMARY");
    col.setStrength(Collator.PRIMARY);
    // Only differences of basis characters, e.g. "a" is smaller than "b".
    // Akzents, Umlautes, upper and lower cases are treated equally,
    // e.g. "a", "A", "�" and "�" are equal.
    compare(col, "abc", "ABC");
    compare(col, "Qu�ken", "Quaken");
    compare(col, "bo�", "boss");
    compare(col, "bo�", "boxen");
    System.out.printf("%nStrength = SECONDARY%n");
    // Differentiates Akzents and Umlautes
    col.setStrength(Collator.SECONDARY);
    compare(col, "abc", "ABC");
    compare(col, "Qu�ken", "Quaken");
    compare(col, "bo�", "boss");
    compare(col, "bo�", "boxen");
    System.out.printf("%nStrength = TERTIARY%n");
    col.setStrength(Collator.TERTIARY);
    // (DEFAULT) Differentiates upper and lower cases
    compare(col, "abc", "ABC");
    compare(col, "Qu�ken", "Quaken");
    compare(col, "bo�", "boss");
    compare(col, "bo�", "boxen");
  }
}
