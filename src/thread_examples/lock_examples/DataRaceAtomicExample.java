package thread_examples.lock_examples;

import java.util.concurrent.atomic.AtomicInteger;

public class DataRaceAtomicExample {
    private static final int NUM_THREADS = 2;
    private static final int PER_THREAD_COUNT = 100000;
    private static AtomicInteger counter = new AtomicInteger(0);


    public static void main(String[] args) {

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < PER_THREAD_COUNT; j++) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("counter = "+counter+" expected " + (PER_THREAD_COUNT * NUM_THREADS) + " ("+NUM_THREADS+" threads)");
    }
}