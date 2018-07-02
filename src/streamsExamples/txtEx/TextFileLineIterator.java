package streamsExamples.txtEx;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** An iterator that open a text file and returns the file line-by-line. */
public class TextFileLineIterator implements Iterator<String> {
    private BufferedReader reader;
    private String line = null;

    public TextFileLineIterator(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public boolean hasNext() {
        if (line == null) {
            tryFetchingNextLine();
        }
        return line != null;
    }

    @Override
    public String next() {
        if (line == null) {
            tryFetchingNextLine();
        }
        if (line == null) {
            throw new NoSuchElementException();
        }
        String result = line;
        line = null;
        return result;
    }

    private void tryFetchingNextLine() {
        try {
            line = reader.readLine();
        } catch(IOException e) { e.printStackTrace(); }
        if (line == null) {
            try { reader.close(); } catch (IOException ignored) { }
        }
    }
}