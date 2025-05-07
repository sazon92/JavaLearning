package Strategy;

/**
 * Стратегия оплаты через PayPal.
 */
public class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println(String.format("Оплата %.2f через PayPal аккаунт: %s", amount, email));
    }
}

