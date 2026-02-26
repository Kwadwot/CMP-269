public class GreeterTask implements Runnable {

    private Thread thread = new Thread();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                thread.sleep((int) Math.random()*10 + 1) ;
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}
