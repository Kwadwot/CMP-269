public class BankAccount {

    private int balance = 1000;

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public synchronized void synchronizedWithdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public int getBalance() {
        return balance;
    }
}
