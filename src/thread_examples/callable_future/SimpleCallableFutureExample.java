package thread_examples.callable_future;


import java.util.concurrent.*;

public class SimpleCallableFutureExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        //Callable<Integer> callable = () -> {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                try {
                    Thread.sleep(5000); // sleep for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[in callable] returning 42");
                return 42;
            }
        };


        Future<Integer> future = executor.submit(callable);
        System.out.println("[in main] callable submitted.");
        System.out.println("[in main] waiting for future to complete...");

        try {
            int result = future.get();
            System.out.println("[in main] got " + result + " from future.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);}
            catch(InterruptedException ignored) { }
    }
}
