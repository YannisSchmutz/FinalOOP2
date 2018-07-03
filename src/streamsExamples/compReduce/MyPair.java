package streamsExamples.compReduce;

public class MyPair<T, S> {

    private T first;
    private S second;

    public MyPair(T first, S second){
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
