package Strategy;

/**
 * Класс, использующий стратегию оплаты.
 */
public class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия оплаты не установлена.");
        }
        strategy.pay(amount);
    }
}
