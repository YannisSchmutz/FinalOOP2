package thread_examples.synchronize_workflow;

import java.util.Random;

public class ProducerConsumer {

    public static void main(String[] args){

        Channel<Integer> channel = new Channel<>();

        Thread producer = new Thread(() -> {
            Random random_nbr_generater = new Random();
            for (int i = 0; i < 10; i++){

                while (!Thread.interrupted()){
                    int item = random_nbr_generater.nextInt(100);
                    System.out.println("Going to put " + item + " into channel");

                    try {
                        channel.putItem(item);
                        Thread.sleep(random_nbr_generater.nextInt(1000));   // ms
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        return; // TODO: Important to return cause "interrupted"-flag is already reset!!!!
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            Random rng = new Random();

            for (int i=0; i < 10; i++){
                while (!Thread.interrupted()){
                    try {
                        Thread.sleep(rng.nextInt(1000));
                        int item = channel.getItem();
                        System.out.println("Pulled " + item + " out of channel.");
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        return; // TODO: Important to return cause "interrupted"-flag is already reset!!!!
                    }
                }
            }
        });



        // start threads and let them run for some
        producer.start();
        consumer.start();
        System.out.println("waiting " + 10 + " seconds until interrupting producer and consumer threads");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupting producer and consumer threads");
        producer.interrupt();
        consumer.interrupt();





    }
}
