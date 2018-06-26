package internationalization.properties;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesDemo {

  /**
   * Main program.
   * @param args unused
   */
  public static void main(String[] args) {
    Locale[] supportedLocales = {Locale.FRENCH, Locale.GERMAN, Locale.ENGLISH};

    for (Locale currentLocale : supportedLocales) {

      ResourceBundle labels =
          //ResourceBundle.getBundle("internationalization.properties.resourceBundle.LabelsBundle", currentLocale);
          ResourceBundle.getBundle("internationalization.properties.LabelsBundle", currentLocale);
      Enumeration<String> bundleKeys = labels.getKeys();

      System.out.println("language = " + currentLocale.getDisplayLanguage());

      while (bundleKeys.hasMoreElements()) {
        String key = (String) bundleKeys.nextElement();
        String value = labels.getString(key);
        System.out.println("key = " + key + ", " + "value = " + value);
      }
    }
  }
}
