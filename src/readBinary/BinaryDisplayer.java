package readBinary;


/* COMPILE
 * To compile the file, open your terminal and type
 *   javac filename.java
 *
 * To run the generated class file, use
 *   java filename
 *
 * !!!! If your .java file is placed in a package do as followed: !!!
 *   javac filename.java     // you have to be in the package folder
 *
 *   // the go back to the source folder and call it using "package.file"
 *   java package.filename
 */

/*
 * DEFINE ARGS IN INTELLIJ
 * Top right corner (left of green play symbol) click on dropdown list, then "Edit Configuration"
 * Set Program arguments to
 *      src/readBinary/BinaryDisplayer.class
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BinaryDisplayer {



    public static void main(String[] args) throws IOException{
        System.out.println("BinaryDisplayer");

        // Args are defined in project settings -> Edit Configuration top right corner
        // src/readBinary/BinaryDisplayer.class
        for (String a : args){
            System.out.println(a);
        }

        // Take first argument
        String binary_name = args[0];
        System.out.println(binary_name);

        // Maybe add here "src/readBinary/"
        byte[] bytes = Files.readAllBytes(Paths.get(binary_name));
        int counter = 0;
        for (byte b : bytes){
            System.out.printf("%02x ", b);

            counter++;

            if (counter == 16){
                System.out.println("");
                counter = 0;
            }
        }
    }
}
