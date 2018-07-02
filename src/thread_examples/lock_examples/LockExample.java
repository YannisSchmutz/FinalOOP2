package thread_examples.lock_examples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    public static int counter = 0;

    public static void main(String[] args){

        Lock lock = new ReentrantLock();

        Thread[] threads = new Thread[8];
        for(int i=0; i < threads.length; i++){
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++){
                    lock.lock();
                    counter++;
                    lock.unlock();
                }
            });
            threads[i].start();
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter);
    }
}
