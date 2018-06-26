package regex_examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex1 {


    public static void check_regex(String input, String regex_str){
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex_str);

        // Now create matcher object.
        Matcher matcher = pattern.matcher(input);
        //System.out.println(matcher);

        System.out.println("Current REGEX is: "+regex_str);
        System.out.println("Current INPUT is: "+input);

        System.out.println("lookingAt(): "+matcher.lookingAt());
        if (matcher.lookingAt()){
            System.out.println(matcher.group(0));
        }
        System.out.println("Whole match: "+matcher.matches());
        System.out.println("\n");
    }


    public static void main(String[] args){

        String line = "aabbaabbaabb";
        String regex_str = "(aabb){1}";
        check_regex(line, regex_str);

        String line2 = "foo23";
        String regex_str2 = "foo[0-9&&[^13579]]";
        check_regex(line2, regex_str2);



    }
}
