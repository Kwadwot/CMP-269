import java.util.ArrayList;

class Main {

    public static void main(String[] args){
        // Polymorphism in Action Test:
        ArrayList<Payable> paymentQueue = new ArrayList<>();
        paymentQueue.add(new CreditCard("KPT", 5000, 10000));
        paymentQueue.add(new MealPlan("KT", 300));

        for (Payable p: paymentQueue){
            p.processPament(50.0);
        }

        // Checking that totalTransactions is properly incremented
        System.out.println("Number of transactions: " + PaymentMethod.totalTransactions); // Output: Number of transactions: 2
    }
}
