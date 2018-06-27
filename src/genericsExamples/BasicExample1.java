package genericsExamples;

public class BasicExample1<T> {

    private T value;

    public BasicExample1(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }



    public static void main(String[] args){

        BasicExample1<Integer> ex1 = new BasicExample1<>(123);
        System.out.println(ex1.getValue());

        BasicExample1<String> ex2 = new BasicExample1<>("Hallo Welt!");
        System.out.println(ex2.getValue());


    }
}
