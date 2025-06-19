package Proxy;

/**
 * Реальный интернет-доступ.
 */
public class RealInternet implements Internet {
    @Override
    public void connectTo(String site) {
        System.out.println(String.format("Подключение к %s", site));
    }
}
