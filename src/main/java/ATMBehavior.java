public interface ATMBehavior {

    double moneyInATM();

    void moneyAdd(double expected);

    boolean validateCreditCard(CreditCard card);

    double checkCardBalance() throws NoCardException;

    double withdrawMoney(double amount);
}
