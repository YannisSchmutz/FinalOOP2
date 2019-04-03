package thread_examples.lock_examples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample2 {

    public static int counter = 0;

    private void produce(){
        while (true){

        }
    }

    public static void main(String[] args){

        Lock lock = new ReentrantLock();

        LockExample2 o = new LockExample2();

        Thread threadA = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                lock.lock();
                System.out.println("AAA");
                lock.unlock();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });
        Thread threadB = new Thread(() -> {
            for(int i=0;i<3;i++){
                lock.lock();
                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();


    }
}
