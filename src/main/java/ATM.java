public class ATM implements ATMBehavior {

    private CreditCard card;

    private double moneyInATM;

    public ATM() {
    }

    public ATM(double v) {
        this.moneyInATM = v;
    }

    public double moneyInATM() {
        return moneyInATM;
    }

    public void moneyAdd(double expected) {
        if (expected <= 0){
            throw new IllegalArgumentException();
        }
        moneyInATM += expected;
    }

    public boolean validateCreditCard(CreditCard card) {
        if (card == null){
            return false;
        }
        this.card = card;
        return card.isValid();
    }

    public double checkCardBalance() {
        if (card == null){
            throw new NoCardException();
        }
        return card.balance();
    }

    public double withdrawMoney(double amount) {
        if (moneyInATM < amount) {
            throw new NotEnoughtMoneyInAtmException();
        } else return 0;

    }
}
