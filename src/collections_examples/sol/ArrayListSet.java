package collections_examples.sol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrayListSet<T> implements Set<T> {
  private List<T> elements = new ArrayList<>();

  public ArrayListSet() {

  }

  public ArrayListSet(Collection<? extends T> coll) {
    this.addAll(coll);
  }

  @Override
  public int size() {
    return elements.size();
  }

  @Override
  public boolean isEmpty() {
    return elements.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return elements.contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    return elements.iterator();
  }

  @Override
  public Object[] toArray() {
    return elements.toArray();
  }

  @Override
  public <E> E[] toArray(E[] array) {
    return elements.toArray(array);
  }

  @Override
  public boolean add(T element) {
    if (element == null) {
      throw new NullPointerException();
    }
    if (elements.contains(element)) {
      return false; // element already exists
    } else {
      return elements.add(element);
    }
  }

  @Override
  public boolean remove(Object o) {
    return elements.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> coll) {
    return elements.containsAll(coll);
  }

  @Override
  public boolean addAll(Collection<? extends T> coll) {
    boolean result = false;
    for (T element: coll) {
      result = result | this.add(element);
    }
    return result;
  }

  @Override
  public boolean retainAll(Collection<?> coll) {
    return elements.retainAll(coll);
  }

  @Override
  public boolean removeAll(Collection<?> coll) {
    return elements.removeAll(coll);
  }

  @Override
  public void clear() {
    elements.clear();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    ArrayListSet<T> other = (ArrayListSet<T>) obj;
    return elements.containsAll(other.elements) && other.elements.containsAll(elements);
  }

  @Override
  public String toString() {
    return "ArrayListSet" + elements;
  }
}
