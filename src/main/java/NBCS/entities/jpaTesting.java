/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import NBCS.entities.RequestEntity;

/**
 *
 * @author AnthonyLopez
 */
public class jpaTesting {
    
    // Create the EntityManager
    // sportsPU is a Persistence Unit as defined in persistence.xml that is
    // part of this application (it is the META-INF folder)
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("nbcs493PU");
    private static final EntityManager ENTITY_MANAGER = EMF.createEntityManager();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        jpaTesting n = new jpaTesting();
        n.loadDatabase();
    }
    
    /**
     * Load the database tables with some initial records provided in the code.
     */
    private void loadDatabase() {
        // An EntityManager object is used to perform persistence tasks such as
        // starting transactions, persisting objects, creating queries, etc.
        ENTITY_MANAGER.getTransaction().begin();
        
        // prior to the statement below, each of the Team objects in the teams array
        // is a transient entity, i.e. just a regular non-persistent Java object.

        // All instances at this point are transient... they're "objects" not "entities"
        for (RequestEntity request : REQUEST) {
            ENTITY_MANAGER.persist(request);
        }
        
        // NOTE: Persisting a Player object without assigning a Team fails at run-time. 
//        Player pete = new Player("Nick", "Young", 29, "Swaggy P");
//        ENTITY_MANAGER.persist(pete);
        
        // Now they are all persisted... even players due to the CascadeType (see relationship defined in Team.java)
        ENTITY_MANAGER.getTransaction().commit();
    }
    
    public static final RequestEntity [] REQUEST = new RequestEntity[] {
        new RequestEntity("Honda", "Civic", 2010, 0, 15000, 20000, "Clean", 10, "Active")
    };    
}
