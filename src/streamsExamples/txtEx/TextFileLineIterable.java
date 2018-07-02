package streamsExamples.txtEx;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class TextFileLineIterable implements Iterable<String>{

    private String file;
    private TextFileLineIterator myIterator;

    public TextFileLineIterable(String file) throws FileNotFoundException {
        this.file = file;
        myIterator = new TextFileLineIterator(file);

    }

    @Override
    public Iterator<String> iterator() {
        return myIterator;
    }

//    @Override
//    public void forEach(Consumer<? super String> action) {
//
//    }
//
//    @Override
//    public Spliterator<String> spliterator() {
//        return null;
//    }
}
