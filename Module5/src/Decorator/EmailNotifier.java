package Decorator;

/**
 * Базовая реализация отправки уведомлений.
 */
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println(String.format("Email: %s", message));
    }
}
