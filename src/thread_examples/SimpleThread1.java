package thread_examples;

public class SimpleThread1 implements Runnable{



    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello SimpleThread1");
    }


    public static void main(String[] args){
        Runnable simpl_thread = new SimpleThread1();
        //Thread my_thread = new Thread(new SimpleThread1()); // Needs a runnable, can be done like this or:
        Thread my_thread = new Thread(simpl_thread); // This
        System.out.println("Going to start thread...");
        my_thread.start();
        System.out.println("Thread started...");
    }
}
