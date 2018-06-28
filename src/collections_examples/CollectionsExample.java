package collections_examples;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class CollectionsExample {

    public static void main(String[] args) {

        Collection<String> coll1 = new ArrayList<>();
        coll1.add("Hello");
        coll1.add("World");
        coll1.size();       // returns 2
        System.out.println(coll1); // prints "[Hello, World]"

        Collection<String> coll2 = new LinkedList<>(coll1);
        coll2.add("!");
        coll2.size(); // returns 3
        coll2.remove("World");
        coll2.size();       // returns 2
        System.out.println(coll2);  // prints "[Hello, !]"

        Collection<String> coll3 = new HashSet<>(coll2);
        coll3.add("World");
        coll3.add("World");
        coll3.size(); // returns 3
        System.out.println(coll3); // prints "[!, Hello, World]"
    }
}
