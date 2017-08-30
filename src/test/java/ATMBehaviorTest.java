import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ATMBehaviorTest {

    @Test
    public void moneyInAtmIsZero(){
        ATMBehavior atm = new ATM();
        double expected = 0.0;
        double actual = atm.moneyInATM();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void createAtmWithSomeMoney(){
        double expected = 10.0;
        ATMBehavior atm = new ATM(10.0);
        assertEquals(expected, atm.moneyInATM(), 0.0);
    }

    @Test
    public void moneyInAtmAdd(){
        double expected = 10.0;
        ATMBehavior atm = new ATM();
        atm.moneyAdd(expected);
        double actual = atm.moneyInATM();
        assertEquals(expected, actual, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyInAtmAddNegative(){
        ATMBehavior atm = new ATM();
        double sum = -10.0;
        atm.moneyAdd(sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moneyInAtmAddZero(){
        ATMBehavior atm = new ATM();
        double sum = 0.0;
        atm.moneyAdd(sum);
    }

    @Test
    public void validateCreditCardWithNull(){
        ATMBehavior atm = new ATM();
        boolean actual = atm.validateCreditCard(null);
        assertFalse(actual);
    }

    @Test
    public void validateCreditCardWithValidCard(){
        ATMBehavior atm = new ATM();
        CreditCard card = mock(CreditCard.class);
        when(card.isValid()).thenReturn(Boolean.TRUE);
        boolean actual = atm.validateCreditCard(card);
        assertTrue(actual);
    }

    @Test
    public void validateCreditCardWithInvalidCard(){
        ATMBehavior atm = new ATM();
        CreditCard card = mock(CreditCard.class);
        when(card.isValid()).thenReturn(Boolean.FALSE);
        boolean actual = atm.validateCreditCard(card);
        assertFalse(actual);
    }

    @Test(expected = NoCardException.class)
    public void checkCardBalanceWithOutCard(){
        ATMBehavior atm = new ATM();
        atm.checkCardBalance();
    }

    @Test(expected = NotValidCardException.class)
    public void checkCardBalanceWithInvalidCard(){
        ATMBehavior atm = new ATM();
        CreditCard card = mock(CreditCard.class);
        when(card.isValid()).thenReturn(Boolean.FALSE);
        when(card.balance()).thenThrow(NotValidCardException.class);
        atm.validateCreditCard(card);
        atm.checkCardBalance();
    }

    @Test
    public void checkCardBalanceWithValidCard(){
        ATMBehavior atm = new ATM();
        CreditCard card = mock(CreditCard.class);
        double sum = 100.1;
        when(card.isValid()).thenReturn(Boolean.FALSE);
        when(card.balance()).thenReturn(sum);
        atm.validateCreditCard(card);
        double actual = atm.checkCardBalance();
        double expected = sum;
        assertEquals(expected, actual, 0.0);
    }

    @Test(expected = NotEnoughtMoneyInAtmException.class)
    public void withdrawMoneyFromEmptyAtm() {
        ATMBehavior atm = new ATM();
        CreditCard card = mock(CreditCard.class);
        atm.validateCreditCard(card);
        atm.withdrawMoney(100);
    }

    @Test(expected = NotEnoughtMoneyInAtmException.class)
    public void withdrawMoneyFromFullAtmWithValidCard() {
        ATMBehavior atm = new ATM();
        CreditCard card = mock(CreditCard.class);
        atm.validateCreditCard(card);
        atm.withdrawMoney(100);
    }
}
