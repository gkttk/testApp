package org.testApp.hibernateUtil;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;

    static{
        emFactory = Persistence.createEntityManagerFactory("main");
    }

    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

    public static Session getSession(){
        return getEntityManager().unwrap(Session.class);
    }

    public static void close(){
        emFactory.close();
    }

}
