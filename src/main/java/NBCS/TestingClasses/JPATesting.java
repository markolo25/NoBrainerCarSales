/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.TestingClasses;

import NBCS.EntityClasses.Car;
import NBCS.EntityClasses.Request;
import NBCS.EntityClasses.User;
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

        ENTITY_MANAGER.getTransaction().begin();
        Request request = new Request();
        request.setMake("PERSISTANCETEST");
        request.setModel("PERSISTANCETEST");
        
        User user = new User();
        user.setName("PERSISTANCETEST");
        user.setEmail("PERSISTANCETEST");
        
        Car car = new Car();
        car.setMake("PERSISTANCETEST");
        car.setModel("PERSISTANCETEST");
        
        ENTITY_MANAGER.persist(request);
        ENTITY_MANAGER.persist(user);
        ENTITY_MANAGER.persist(car);
        ENTITY_MANAGER.getTransaction().commit();
        
//        ENTITY_MANAGER.getTransaction().begin();
//        ENTITY_MANAGER.getTransaction().commit();
    }
}
