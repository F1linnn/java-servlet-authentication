package webApp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionInInitializerError("Ошибка при инициализации Hibernate: " + e.getMessage());
            }
        }
        return sessionFactory;
    }


    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
