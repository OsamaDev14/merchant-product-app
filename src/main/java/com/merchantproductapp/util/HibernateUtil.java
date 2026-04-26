package com.merchantproductapp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//Responsible for creating and managing a single instance of EntityManagerFactory
//EntityManagerFactory is expensive, so we create it once
//Provides EntityManager objects for database operations

public class HibernateUtil {

    // create only ONE factory (heavy object)
    private static EntityManagerFactory emf =Persistence.createEntityManagerFactory("dev");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void closeFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}