package thread_examples;

public class SimpleInterruptedThread {

    public static void main(String[] args){

        Thread pinger = new Thread(()->{
            while (!Thread.interrupted()) {
                System.out.println("ping...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("interrupted");
                    return; // Todo: This is very important because the flag is already cleared here!
                }
            }
        });
        pinger.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Interrupting...");
        pinger.interrupt();
    }
}
