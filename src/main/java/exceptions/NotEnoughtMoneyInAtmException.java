public class NotEnoughtMoneyInAtmException extends RuntimeException {
    public NotEnoughtMoneyInAtmException() {
        super("no money in atm");
    }
}
