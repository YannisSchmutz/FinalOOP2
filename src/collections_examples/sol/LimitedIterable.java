package collections_examples.sol;

import java.util.Iterator;

/**
 * A limited iterable wraps an iterable but returns at most the specified
 * number of elements.
 *
 * @param <T>
 */
public class LimitedIterable<T> implements Iterable<T> {

  private Iterable<T> source;
  private final long numElements;

  /**
   * Create new LimitedIterable around the source
   * @param numElements maximum number of elements this iterable returns
   * @param source source iterable
   */
  public LimitedIterable(long numElements, Iterable<T> source) {
    this.source = source;
    this.numElements = numElements;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Iterator<T> iter = source.iterator();
      long elementCount = 0;

      @Override
      public boolean hasNext() {
        return iter.hasNext() && elementCount < numElements;
      }

      @Override
      public T next() {
        elementCount++;
        return iter.next();
      }
    };
  }
}