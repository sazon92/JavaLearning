package Strategy;

/**
 * Стратегия оплаты через кредитную карту.
 */
public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println(String.format("Оплата %.2f через кредитную карту: %s", amount, cardNumber));
    }
}
