/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kil_5
 */
public class ConexionBD {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static String nameC = "SistemaInventarioPU";
    
    public ConexionBD() {
    }
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(nameC);
        em = emf.createEntityManager();
    }

    public static void closeEntityManager() {
        em.close();
        emf.close();
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static EntityManager getEm() {
        return em;
    }
    
}
