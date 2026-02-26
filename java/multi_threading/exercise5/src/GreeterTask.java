public class GreeterTask implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello from " + Thread.currentThread());
            try {
                Thread.sleep(500) ;
            } catch (InterruptedException e) {
                System.out.println("GreeterTask Thread interrupted");
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
    }
}
