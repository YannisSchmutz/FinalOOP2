package thread_examples;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        final long start_time = System.currentTimeMillis();
        executor.scheduleAtFixedRate(() ->
                System.out.println((System.currentTimeMillis()-start_time) + " task @fixed rate"), 3,1, TimeUnit.SECONDS
        );


        executor.schedule(() ->
                System.out.println((System.currentTimeMillis()-start_time) + " task @5s"), 5, TimeUnit.SECONDS
        );

        executor.schedule(() -> executor.shutdown(), 12, TimeUnit.SECONDS);


        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
