package thread_examples;

public class SimpleLambdaThread {

    public static void main(String[] args){
        Runnable lambda_t = () ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello Lambda thread");
        };

        Thread t = new Thread(lambda_t);
        t.start();

    }
}
