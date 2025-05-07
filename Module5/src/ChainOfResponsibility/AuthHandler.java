package ChainOfResponsibility;

/**
 * Обработчик авторизации.
 */
public class AuthHandler extends Handler {
    @Override
    public void handleRequest(String request) {
        if ("auth".equalsIgnoreCase(request)) {
            System.out.println("Авторизация выполнена.");
        } else if (next != null) {
            next.handleRequest(request);
        }
    }
}
