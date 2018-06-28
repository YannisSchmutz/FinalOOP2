package collections_examples;

import java.util.HashSet;
import java.util.Set;

public class SetExample {


    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        set.contains("Foo"); // returns false
        set.add("Foo");
        set.add("Bar");
        set.contains("Foo"); // returns true
        set.add("Foo");
        System.out.println(set); // prints [Bar, Foo]
        set.remove("Bar");
        set.contains("Bar"); // returns false
    }
}
