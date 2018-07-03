package recursion;

public class SomeExamples {


    public static int factorial(int n) {
        if(n == 1){
            return n;
        }
        return n * factorial(n-1);
    }

    public static int bunnyEars(int bunnies) {
        /*
        We have a number of bunnies and each bunny has two big floppy ears. We want to compute the total number of
        ears across all the bunnies recursively (without loops or multiplication).
         */
        if(bunnies == 0){
            return 0;
        }
        return 2 + bunnyEars(bunnies - 1);
    }

    public static int fibonacci(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }

        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    public static int bunnyEars2(int bunnies) {
        /*
        We have bunnies standing in a line, numbered 1, 2, ... The odd bunnies (1, 3, ..) have the normal 2 ears.
        The even bunnies (2, 4, ..) we'll say have 3 ears, because they each have a raised foot. Recursively return the
        number of "ears" in the bunny line 1, 2, ... n (without loops or multiplication).
         */
        if(bunnies == 0){
            return 0;
        }
        return 3 - bunnies%2 + bunnyEars2(bunnies-1);
    }

    public static int triangle(int rows) {
        /*
        We have triangle made of blocks. The topmost row has 1 block, the next row down has 2 blocks, the next row has 3
        blocks, and so on. Compute recursively (no loops or multiplication) the total number of blocks in such a
        triangle with the given number of rows.
         */
        if(rows == 0){
            return 0;
        }
        return rows + triangle(rows - 1);
    }

    public static int sumDigits(int n) {
        if(n == 0){
            return 0;
        }

        return n%10 + sumDigits(n/10);
    }

    public static int count7(int n) {
        if(n == 0){
            return 0;
        }

        int s = 0;
        if(n%10 == 7){
            s = 1;
        }
        return s + count7(n/10);
    }

    public static int count8(int n) {
        /*
        Given a non-negative int n, compute recursively (no loops) the count of the occurrences of 8 as a digit,
        except that an 8 with another 8 immediately to its left counts double, so 8818 yields 4.
         */
        if(n == 0){
            return 0;
        }

        int s = 0;
        if(n%10 == 8){
            s = 1;
            if((n/10)%10 == 8){
                s *= 2;
            }
        }

        return s + count8(n/10);
    }

    public static int powerN(int base, int n) {
        // 3^2 == 9
        if(n == 1){
            return base;
        }

        return base * powerN(base, n - 1);
    }

    public int countX(String str) {

        if(str.length() == 0){
            return 0;
        }

        int x = 0;
        if(str.charAt(str.length() -1) == 'x'){ // todo: note == works for chars
            x = 1;
        }

        return x + countX(str.substring(0, str.length()-1));
    }

    public static int countHi(String str) {
        if(str.length() == 0 || str.length() == 1){
            return 0;
        }

        int c = 0;
        if(str.substring(str.length()-2, str.length()).equals("hi")){   // todo: use .equals for strings
            c = 1;
        }

        return c + countHi(str.substring(0, str.length()-1));
    }

    public static String changeXY(String str) {
        if(str.length() == 0){
            return "";
        }
        char x = str.charAt(str.length()-1);
        if(str.charAt(str.length()-1) == 'x'){
            x = 'y';
        }

        return changeXY(str.substring(0, str.length()-1)) + x;

    }

    public static String changePi(String str) {
        // changes pi to "3.14" ins string
        if (str.length() == 0){
            return "";
        }
        if (str.length() == 1){
            return str;
        }

        String is_pi = str.substring(str.length()-2, str.length());
        if (is_pi.equals("pi")){
            is_pi = "3.14";
            return changePi(str.substring(0, str.length()-2)) + is_pi;
        }

        return changePi(str.substring(0, str.length()-1)) + is_pi.substring(1, 2);
    }



    public static void main(String[] args){



    }
}
