package org.example.userservice.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.log4j.Logger;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);

    static {
        try {
            Dotenv dotenv = Dotenv.configure().load();

            Configuration configuration = new Configuration()
                    .configure() // загружает hibernate.cfg.xml
                    .setProperty("hibernate.connection.url", dotenv.get("DB_URL"))
                    .setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"))
                    .setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
