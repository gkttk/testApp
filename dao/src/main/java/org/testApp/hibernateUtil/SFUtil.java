package org.testApp.hibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.testApp.Theme;
import org.testApp.User;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

//SFUtil не настроен должным образом
public class SFUtil {
    private static final SessionFactory sessionFactory;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("mySQL");
        String driver = resourceBundle.getString("driver");
        String url = resourceBundle.getString("url");
        String login = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        Map<String, String> settings = new HashMap<>();

        settings.put(Environment.DRIVER, driver);
        settings.put(Environment.URL, url);
        settings.put(Environment.USER, login);
        settings.put(Environment.PASS, password);
        settings.put(Environment.HBM2DDL_AUTO, "validate");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.USE_SQL_COMMENTS, "false");
        settings.put(Environment.FORMAT_SQL, "false");
        settings.put(Environment.ISOLATION, "2");
        settings.put(Environment.STORAGE_ENGINE, "innodb");

        serviceRegistryBuilder.applySettings(settings);

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        MetadataSources sources = new MetadataSources(serviceRegistry);

        sources.addAnnotatedClass(Theme.class);
        sources.addAnnotatedClass(User.class);

        Metadata metadata = sources.getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }


    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
