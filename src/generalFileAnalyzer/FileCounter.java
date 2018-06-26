package generalFileAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


/*
 * Counts number of lines, chars, words and specific words

 */
public class FileCounter {

    private List<String> lines;
    private int lineCounter;
    private int charCount;
    private int wordCount;
    private int atCount;

    public FileCounter(){
        lineCounter = 0;
        charCount = 0;
        wordCount = 0;
        atCount = 0;
    }

    public void read(String name) throws IOException{
        Path path = Paths.get(name);    // src/generalFileAnalyzer/blindtext.txt
        lines = Files.readAllLines(path);

        // Number of lines
        lineCounter = lines.size();
        charCount = 0;
        for (String line : lines){
            // Number of chars
            charCount += line.chars().count();

            String help = line;
            int i;
            while ((i = help.indexOf("at")) > 0){
                atCount++;
                help = help.substring(i + 1);
            }

            Scanner lineIn = new Scanner(line);
            // Loop trough string
            while (lineIn.hasNext()){
                lineIn.next();
                wordCount++;
            }
            lineIn.close();
        }
    }

    public int getCharacterCount(){

        return charCount;
    }

    public int getWordCount(){

        return wordCount;
    }

    public int getLineCount(){
        return lineCounter;
    }

    public int getNbrAt(){
        return atCount;
    }
}
