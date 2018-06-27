package genericsExamples.wildcards_ex;

public class Box <T> {

    private T content;

    public Box(T content){
        this.content = content;
    }
}
