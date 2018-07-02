//package collections_examples.sol;
//
//// UNIT TEST
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//import org.junit.jupiter.api.Test;
//
//class ArrayListSetTest {
//
//  /**
//   * The number of elements to place in collections, arrays, etc.
//   */
//  private static final int SIZE = 20;
//
//  /**
//   * Returns a new set of given size containing consecutive Integers 0 ... n and
//   * checks whether the set was created successfully.
//   */
//  private ArrayListSet<Integer> populatedSet(int n) {
//    ArrayListSet<Integer> set = new ArrayListSet<>();
//    assertTrue(set.isEmpty());
//    // insert half of the elements in descending order
//    for (int i = n - 1; i >= 0; i -= 2) {
//      assertTrue(set.add(i));
//    }
//    // insert the other half of the elements in ascending order
//    for (int i = (n & 1); i < n; i += 2) {
//      assertTrue(set.add(i));
//    }
//    assertFalse(set.isEmpty());
//    assertEquals(n, set.size());
//    return set;
//  }
//
//  @Test
//  void testAddingFirst5Elements() {
//    Set<Integer> q = new ArrayListSet<>();
//    assertTrue(q.isEmpty());
//    q.add(1);
//    q.add(2);
//    q.add(3);
//    q.add(4);
//    q.add(5);
//    assertEquals(5, q.size());
//  }
//
//  @Test
//  void testRetainAll() {
//    Set<Integer> s1 = new ArrayListSet<>();
//    s1.add(1);
//    s1.add(2);
//    s1.add(3);
//    s1.add(4);
//    s1.add(5);
//    Set<Integer> s2 = new ArrayListSet<>();
//    s2.add(3);
//    s2.add(4);
//    s2.add(5);
//    s2.add(6);
//    s2.add(7);
//
//    s1.retainAll(s2);
//    assertTrue(s1.contains(3));
//    assertTrue(s1.contains(4));
//    assertTrue(s1.contains(5));
//    assertFalse(s1.contains(1));
//    assertFalse(s1.contains(2));
//    assertFalse(s1.contains(6));
//    assertFalse(s1.contains(7));
//  }
//
//  @Test
//  void testNewSetHasEmptyCapacity() {
//    assertEquals(0, new ArrayListSet<Integer>().size());
//  }
//
//  @Test
//  void testConstructorFromNullThrowsException() {
//    //noinspection ConstantConditions
//    assertThrows(NullPointerException.class, () ->
//        new ArrayListSet<Integer>(null));
//  }
//
//  @Test
//  void testConstructorFromCollection() {
//    List<Integer> list = new ArrayList<>();
//    for (int i = 0; i < SIZE; ++i) {
//      list.add(i);
//    }
//    Set<Integer> set = new ArrayListSet<>(list);
//    for (int i = 0; i < SIZE; ++i)
//      assertTrue(set.contains(list.get(i)));
//  }
//
//  @Test
//  void testEmptyBeforeAddFalseAfterwards() {
//    Set<Integer> set = new ArrayListSet<>();
//    assertTrue(set.isEmpty());
//    set.add(1);
//    assertFalse(set.isEmpty());
//    set.add(2);
//    set.remove(1);
//    set.remove(2);
//    assertTrue(set.isEmpty());
//  }
//
//  @Test
//  void testSizeChangesWhenElementsAreAddedAndRemoved() {
//    Set<Integer> set = populatedSet(SIZE);
//    for (int i = 0; i < SIZE; ++i) {
//      assertEquals(SIZE - i, set.size());
//      set.remove(i);
//    }
//    for (int i = 0; i < SIZE; ++i) {
//      assertEquals(i, set.size());
//      set.add(i);
//    }
//  }
//
//  @Test
//  void testAddNullThrowsException() {
//    Set<Integer> set = populatedSet(SIZE);
//    assertThrows(NullPointerException.class, () -> set.add(null));
//  }
//
//  @Test
//  void testAddDuplicate() {
//    ArrayListSet<Integer> q = new ArrayListSet<>();
//    assertTrue(q.add(0));
//    assertFalse(q.add(0));
//  }
//
//  @Test
//  void testAddAllOnNullThrowsException() {
//    Set<Integer> set = new ArrayListSet<>();
//    //noinspection ConstantConditions
//    assertThrows(NullPointerException.class, () -> set.addAll(null));
//  }
//
//  @Test
//  void testAddAll() {
//    List<Integer> list = new ArrayList<>(SIZE);
//    for (int i = 0; i < SIZE; ++i) {
//      list.add(i);
//    }
//    ArrayListSet<Integer> set = new ArrayListSet<>();
//    assertFalse(set.addAll(new ArrayList<>()));
//    assertTrue(set.addAll(list));
//    for (Integer i : list) {
//      assertTrue(set.contains(i));
//    }
//  }
//
//  @Test
//  void testRemoveRemovesElementsAndReturnsTrue() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    for (int i = 1; i < SIZE; i += 2) {
//      assertTrue(set.contains(i));
//      assertTrue(set.remove(i));
//      assertFalse(set.contains(i));
//      assertTrue(set.contains(i - 1));
//    }
//    for (int i = 0; i < SIZE; i += 2) {
//      assertTrue(set.contains(i));
//      assertTrue(set.remove(i));
//      assertFalse(set.contains(i));
//      assertFalse(set.remove(i + 1));
//      assertFalse(set.contains(i + 1));
//    }
//    assertTrue(set.isEmpty());
//  }
//
//  @Test
//  void testContainsReturnsTrueForAddedElements() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    for (int i = 0; i < SIZE; ++i) {
//      assertTrue(set.contains(i));
//      set.remove(i);
//      assertFalse(set.contains(i));
//    }
//  }
//
//  @Test
//  void testClearRemovesAllElements() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    set.clear();
//    assertTrue(set.isEmpty());
//    assertEquals(0, set.size());
//    set.add(1);
//    assertFalse(set.isEmpty());
//    set.clear();
//    assertTrue(set.isEmpty());
//  }
//
//  @Test
//  void testContainsAll() {
//    ArrayListSet<Integer> set1 = populatedSet(SIZE);
//    ArrayListSet<Integer> set2 = new ArrayListSet<>();
//    for (int i = 0; i < SIZE; ++i) {
//      assertTrue(set1.containsAll(set2));
//      assertFalse(set2.containsAll(set1));
//      set2.add(i);
//    }
//    assertTrue(set2.containsAll(set1));
//  }
//
//  @Test
//  void testRemoveAll() {
//    for (int i = 1; i < SIZE; ++i) {
//      ArrayListSet<Integer> set1 = populatedSet(SIZE);
//      ArrayListSet<Integer> set2 = populatedSet(i);
//      assertTrue(set1.removeAll(set2));
//      assertEquals(SIZE - i, set1.size());
//    }
//  }
//
//  @Test
//  void testToArrayContainsAllElementsInOrder() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    Object[] elements = set.toArray();
//    for (Object e : elements) {
//      //noinspection SuspiciousMethodCalls
//      assertTrue(set.contains(e));
//    }
//  }
//
//  @Test
//  void testToArrayOutContainsAllElementsInOrder() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    Integer[] ints = new Integer[SIZE];
//    Integer[] array = set.toArray(ints);
//    assertSame(ints, array);
//    for (Integer anInt : ints) {
//      assertTrue(set.contains(anInt));
//    }
//  }
//
//  @Test
//  void testIteratorGoesOverAllElements() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    Iterator<Integer> it = set.iterator();
//    int i;
//    for (i = 0; it.hasNext(); i++)
//      assertTrue(set.contains(it.next()));
//    assertEquals(i, SIZE);
//
//  }
//
//  @Test
//  void testEmptySetIteratorHasNoElements() {
//    assertFalse(new ArrayListSet<Integer>().iterator().hasNext());
//  }
//
//  @Test
//  void testToStringContainsAllElements() {
//    ArrayListSet<Integer> set = populatedSet(SIZE);
//    String s = set.toString();
//    for (int i = 0; i < SIZE; ++i) {
//      assertTrue(s.contains(String.valueOf(i)));
//    }
//  }
//
//}
