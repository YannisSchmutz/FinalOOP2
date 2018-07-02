package thread_examples.synchronize_workflow;

public class Channel<T> {

    private T item;

    public synchronized void putItem(T item) throws InterruptedException{
        while (isFull()){
            this.wait();
        }
        this.item = item;
        this.notify();
    }

    public synchronized T getItem() throws InterruptedException{
        while (isEmpty()){
            this.wait();
        }
        T tmp_item = item;
        item = null;
        this.notify();
        return tmp_item;
    }

    public boolean isEmpty(){
        return item == null;
    }

    public boolean isFull(){
        return item != null;
    }
}
