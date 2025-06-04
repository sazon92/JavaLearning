package Decorator;

/**
 * Декоратор для добавления SMS-уведомлений.
 */
public class SMSNotifier implements Notifier {
    private final Notifier wrappee;

    public SMSNotifier(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        System.out.println(String.format("SMS: %s", message));
    }
}
