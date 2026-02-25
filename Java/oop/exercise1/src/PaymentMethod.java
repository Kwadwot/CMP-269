abstract class PaymentMethod implements Payable {
    protected String accountHolder;
    protected double balance;

    protected static int totalTransactions;

    PaymentMethod(String accountHolder, double balance) {
        this.accountHolder = "";
        this.balance = 100;
    }

    abstract void validateAccount();

}
