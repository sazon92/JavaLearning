package Proxy;

import java.util.List;

/**
 * Прокси, ограничивающий доступ к запрещённым сайтам.
 */
public class ProxyInternet implements Internet {
    private static final List<String> bannedSites = List.of("banned.com", "restricted.com");

    private final Internet realInternet = new RealInternet();

    @Override
    public void connectTo(String site) {
        if (bannedSites.contains(site.toLowerCase())) {
            System.out.println(String.format("Доступ к %s запрещён!", site));
        } else {
            realInternet.connectTo(site);
        }
    }
}
