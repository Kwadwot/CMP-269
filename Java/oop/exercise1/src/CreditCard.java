class CreditCard extends PaymentMethod {
    private double creditLimit;

    CreditCard(String acctHolder, double balance, double creditLimit) {
        super(acctHolder, balance);
        this.creditLimit = creditLimit;
    }

    @Override
    void validateAccount() {

    }

    @Override
    public void processPament(double amount) {
        if (amount > balance + creditLimit) {
            System.out.println("Transaction Declined");
        }
        else {
            balance -= amount;
            totalTransactions ++;
        }
    }

    @Override
    public String getPaymentStatus() {
        return "";
    }
}
