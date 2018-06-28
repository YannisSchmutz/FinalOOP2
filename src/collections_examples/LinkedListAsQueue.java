package collections_examples;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListAsQueue {

    public static void main(String[] args){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(5);
        queue.add(2);
        queue.add(1);
        System.out.println(queue.element()); // => 5 (don't remove it)
        System.out.println(queue.remove());  // => 5
        System.out.println(queue.remove());  // => 2
        System.out.println(queue.remove());  // => 1

        queue.add(0);
        System.out.println(queue.remove()); // => 0
        System.out.println(queue.poll()); // => null
        System.out.println(queue.remove()); // => NoSuchElementException
    }
}
