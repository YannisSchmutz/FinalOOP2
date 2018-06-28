package collections_examples;

import java.util.HashMap;
import java.util.Map;

public class MapExample {

    public static void main(String[] args){

        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 4);
        map.put("three", 3);    // replaces the previous value

        System.out.println(map); // prints {one=1, two=2, three=3}
        System.out.println(map.keySet()); // prints [one, two, three]
        System.out.println(map.values()); // prints [1, 2, 3]

        Integer i1 = map.get("one");
        if (i1 != null) System.out.println(i1); // prints 1

        Integer i2 = map.get("four");
        if (i2 != null) System.out.println(i2); // no output

        String key = "one";
        if (map.containsKey(key)) {
            System.out.println(map.get(key)); // prints 1
        }
    }
}
