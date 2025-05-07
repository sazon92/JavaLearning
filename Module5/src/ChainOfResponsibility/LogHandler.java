package ChainOfResponsibility;

/**
 * Обработчик логирования.
 */
public class LogHandler extends Handler {
    @Override
    public void handleRequest(String request) {
        if ("log".equalsIgnoreCase(request)) {
            System.out.println("Логирование выполнено.");
        } else if (next != null) {
            next.handleRequest(request);
        }
    }
}
