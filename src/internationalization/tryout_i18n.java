package internationalization;

import java.util.Locale;

public class tryout_i18n {

    public static void main(String[] args){

        Locale locale = Locale.getDefault();

        String country = locale.getCountry();
        String language = locale.getLanguage();
        String variant = locale.getVariant();

        System.out.println("Country: " + country);
        System.out.println("Language: " + language);
        System.out.println("Variant: " + variant);

    }
}
