public class Main {

    public static void main(String[] args) throws InterruptedException {

        // --- Exercise 5.1 --- //

        GreeterTask gTask = new GreeterTask();

        Thread lehmanThread1 = new Thread(gTask);
        Thread lehmanThread2 = new Thread(gTask);

        lehmanThread1.start();
        lehmanThread2.start();

        lehmanThread1.join();
        lehmanThread2.join();

        System.out.println();


        // --- Exercise 5.2 --- //

        // Creating a thread that sleep for 2 second using a lambda expression
        Thread twoSecondSleeper = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // State after creation
        System.out.println("State after creation: " + twoSecondSleeper.getState());

        twoSecondSleeper.start();

        // State after calling start()
        System.out.println("State after calling start(): " + twoSecondSleeper.getState());

        Thread.sleep(500);

        // State after sleeping main thread for 500ms to ensure child is sleeping
        System.out.println("State after 500ms sleep: " + twoSecondSleeper.getState());

        twoSecondSleeper.join();

        // State after it has finished
        System.out.println("State after it has finished: " + twoSecondSleeper.getState());

        System.out.println();


        // --- Exercise 5.3 --- //

        BankAccount bankAcct = new BankAccount();
        Runnable withdrawTask = () -> {
//            bankAcct.withdraw(700);
            bankAcct.synchronizedWithdraw(700);
        };

        Thread husband = new Thread(withdrawTask);
        Thread wife = new Thread(withdrawTask);

        husband.start();
        wife.start();

        husband.join();
        wife.join();

        /*
            NOTE: All my tests with the unsychronized withdraw() method displayed no data corruption,
            resulting in a final balance of 300. I don't know if this is a failure in my code or
            something else.
         */
        System.out.println("Final balance: " + bankAcct.getBalance());

        System.out.println();


        // --- Exercise 5.4 --- //

        Thread heavyCalc = new Thread(() -> {
            long result = 0;

            // Loop to 1 billion
            for (long i = 1; i <= 1_000_000_000L; i++)
                result++;

            System.out.println("Calculation Finished: " + result);
        });

        heavyCalc.start();

        heavyCalc.join();

    }
}
