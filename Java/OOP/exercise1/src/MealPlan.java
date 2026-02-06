class MealPlan extends PaymentMethod {

    MealPlan(String acctHolder, double balance) {
        super(acctHolder, balance);
    }

    @Override
    void validateAccount() {
        if (balance < 0) balance = 0;
    }

    @Override
    public void processPament(double amount) {
        if (amount > balance) {
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
