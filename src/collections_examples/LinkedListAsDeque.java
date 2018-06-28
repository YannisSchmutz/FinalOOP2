package collections_examples;

import java.util.Deque;
import java.util.LinkedList;

public class LinkedListAsDeque {


    public static void main(String[] args){
        Deque<Integer> deque = new LinkedList<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addLast(4);
        System.out.println(deque); // prints [2, 1, 3, 4]

        System.out.println(deque.removeFirst()); // prints 2
        System.out.println(deque.removeLast()); // prints 4
        System.out.println(deque.removeFirst()); // prints 1
        System.out.println(deque.pollLast()); // prints 3
        System.out.println(deque.pollFirst()); // prints null
        System.out.println(deque.removeLast()); // throws NoSuchElementException

    }
}
