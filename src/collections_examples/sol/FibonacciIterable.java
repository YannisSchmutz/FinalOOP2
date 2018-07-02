package collections_examples.sol;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Infinite Fibonacci Sequence as an Iterable.
 *
 * The Fibonacci sequence is obtaied by adding the two last two numbers of
 * the sequence to produce the next. The first two number of the sequence
 * are 1 and 1.
 *
 * Sequence starts with: 1, 1, 2, 3, 5, 8, 13, 21, ...
 */
public class FibonacciIterable implements Iterable<BigInteger> {

  /** Inner class that that for the actual iterator. */
  private class FibonacciIterator implements Iterator<BigInteger> {
    private BigInteger secondLast = BigInteger.ZERO;
    private BigInteger last = null;

    /**
     * The Fibonacci Iterator returns infinitely many elements.
     * @return always true
     */
    @Override
    public boolean hasNext() {
      return true;
    }

    /** Returns the next Fibonacci number from the sequence. */
    @Override
    public BigInteger next() {
      if (last == null) {
        last = BigInteger.ONE;
        return last;
      }
      BigInteger nextNumber = secondLast.add(last);
      secondLast = last;
      last = nextNumber;
      return nextNumber;
    }
  }

  @Override
  public Iterator<BigInteger> iterator() {
    return new FibonacciIterator();
  }

  /**
   * Test the Fibonacci Iterator by printing the first few elements of the sequence.
   */
  public static void main(String[] main) {
    FibonacciIterable fib = new FibonacciIterable();
    long numElements = 10;
    LimitedIterable<BigInteger> limitedFib = new LimitedIterable<>(numElements, fib);
    limitedFib.forEach(System.out::println);
  }
}

