package recursion;

public class PalindromeChecker {


    private static boolean isPalindrome(String phrase) {

        System.out.println(phrase);
        //System.out.println(phrase.charAt(0));
        //System.out.println(phrase.charAt(phrase.length()-1));


        boolean first_char_is_letter = Character.isLetterOrDigit(phrase.charAt(0));
        boolean last_char_is_letter = Character.isLetterOrDigit(phrase.charAt(phrase.length()-1));
        if (first_char_is_letter && last_char_is_letter) {

            return isPalindrome(phrase.substring(1, phrase.length()-1));
        }
        else if (first_char_is_letter){
            return isPalindrome(phrase.substring(1));
        }else if (last_char_is_letter){
            return isPalindrome(phrase.substring(0, phrase.length()-1));
        }


        if (phrase.length() <= 1) {
            return true;

        } else if (Character.toLowerCase(phrase.charAt(0)) == Character.toLowerCase(phrase.charAt(phrase.length() - 1))) {
            return isPalindrome(phrase.substring(1, phrase.length()-1));
        } else {
            return false;
        }
    }


    public static void main(String[] args){

        String s1 = "Lese Esel";
        String s2 = "A man, a plan, a canal–Panama!";
        String s3 = "Go hang a salami, I'm a lasagna hog";
        String s4 = "Madam, I'm Adam";
        String s5 = "Esope reste ici et se repose";
        String s6 = "Ein Neger mit Gazelle zagt im Regen nie";


        System.out.println(isPalindrome(s1));
    }
}
