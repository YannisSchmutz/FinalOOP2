package thread_examples.lock_examples;


public class SynchronisedExample {
    private int counter = 0;


    //public synchronized void add(){
    public void add(){
        synchronized (this){
            counter = counter + 1;
        }
    }

    public int getCounter(){
        return counter;
    }

    public static void main(String[] args){
        SynchronisedExample se = new SynchronisedExample();

        Thread[] threads = new Thread[4];
        for(int i=0;i<threads.length; i++){
            threads[i] = new Thread(() -> {
                for(int j=0;j<1000;j++){
                    se.add();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch(InterruptedException e) { e.printStackTrace(); }
        }


        System.out.println("Counter = " + se.getCounter() + " expected: 4000");
    }
}
