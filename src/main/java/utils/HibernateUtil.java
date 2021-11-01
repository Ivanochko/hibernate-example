package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                sessionFactory = new Configuration().configure()
                        .buildSessionFactory();
            }catch (Exception e){
                System.out.print("Exception: " + e.getMessage() + " ~> :: " + Arrays.toString(e.getStackTrace()));
            }
        }
        return sessionFactory;
    }

    public static Session openSession(){
        try {
            return getSessionFactory().openSession();
        }catch (HibernateException e){
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
