package thread_examples;

public class SimpleAnonymThread {

    public static void main(String[] args){
        Runnable test_thread = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello Anonymous thread");
            }
        };

        Thread t = new Thread(test_thread);
        t.start();
    }

}
