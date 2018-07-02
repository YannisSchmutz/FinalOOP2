package thread_examples;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedSizeThreadPool {


    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i<13;i++){  // Number of tasks
            final int taskid = i;   // Todo: Important to be final if declared outside of thread-scope!
            executor.execute(() -> {
                System.out.println(LocalDateTime.now() + ": start task: " + taskid);
                int rand_sleep_time = new Random().nextInt(10000);

                // Simulate task working time
                try {
                    Thread.sleep(rand_sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LocalDateTime.now() + " finished task: " + taskid);
            });
        }

        executor.shutdown(); // Shutdown Thread-pool-factory
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks has been completed!");
    }
}
