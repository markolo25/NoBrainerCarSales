/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.TestingClasses;

import NBCS.EntityClasses.Request;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Anthony Lopez <anthony.lopez@student.csulb.edu>
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 */
public class JPATesting {

    // Create the EntityManager
    // The Persistence Unit is defined in persistence.xml that is
    // part of this application (it is the META-INF folder)
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("NBCS-PU");
    private static final EntityManager ENTITY_MANAGER = EMF.createEntityManager();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JPATesting n = new JPATesting();
        n.testPersistRequest();
    }

    private void testPersistRequest() {
        // An EntityManager object is used to perform persistence tasks such as
        // starting transactions, persisting objects, creating queries, etc.
//        ENTITY_MANAGER.getTransaction().begin();
//        ENTITY_MANAGER.getTransaction().commit();
    }
}
