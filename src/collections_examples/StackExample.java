package collections_examples;

import java.util.Stack;

public class StackExample {


    public static void main(String [] args){


        Stack<String> stack = new Stack<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        //System.out.println(stack.pop());      // Would cause a "java.util.EmptyStackException"

    }
}
