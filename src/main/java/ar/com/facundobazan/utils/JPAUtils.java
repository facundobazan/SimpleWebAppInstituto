package ar.com.facundobazan.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {

    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("mysql");

    public static EntityManager getEntity() {

        return FACTORY.createEntityManager();
    }
}
