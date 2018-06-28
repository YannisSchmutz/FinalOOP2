package collections_examples;
import java.util.Iterator;


public class RangeExample implements Iterable<Integer>{

    private final int lowerBound;
    private final int upperBound;
    private final int step;

    public RangeExample(int lower, int upper, int step){
        this.lowerBound = lower;
        this.upperBound = upper;
        this.step = step;

    }

    public RangeExample(int lower, int upper) {
        this(lower, upper, 1);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    /****** Nested Class *************************************************/
    private class RangeIterator implements Iterator<Integer>{

        private int nextValue;
        RangeIterator() {
            nextValue = lowerBound;
        }

        @Override
        public boolean hasNext() {
            return nextValue <= upperBound;
        }

        @Override
        public Integer next() {
            int result = nextValue;
            nextValue = nextValue + step;
            return result;
        }
    }
    /*******************************************************************/

    public static void main(String[] args){


        // While example
        RangeExample range = new RangeExample(1, 5);
        // Iterator in while loop
        Iterator<Integer> rangeIter = range.iterator();
        while (rangeIter.hasNext()) {
            System.out.println(rangeIter.next());
        }

        // For example
        RangeExample range2 = new RangeExample(1, 10, 2);
        // Iterator in for loop
        for (Iterator<Integer> iter = range2.iterator(); iter.hasNext(); ) { // empty update clause.
            System.out.println(iter.next());
        }

        // For-each example
        RangeExample range3 = new RangeExample(95, 99);
        // Iterable in foreach loop
        for (Integer i : range3) {
            System.out.println(i);
        }

    }
}
