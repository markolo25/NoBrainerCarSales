/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.EntityClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 * @author Anthony Lopez <anthony.lopez@student.csulb.edu>
 */
@Stateless
public class ReverseCarStore {

    @PersistenceContext(unitName = "csulb.cecs493_NBCS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     * Creates a request to be added to the buyer's list
     *
     * @param request the request to be added to the database
     *
     * @return request object
     */
    @RolesAllowed("reverseCarStore.user")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Request createRequest(Request request) {
        // TODO: Check if a request is valid
        em.persist(request);
        return request;
    }

    @RolesAllowed("reverseCarStore.user")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Car addCarToInventory(Car car) {
        //TODO: Check if a car is valid
        em.persist(car);
        return car;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void registerUser(User user, String groupName) throws UserExistsException {
        if (null == em.find(User.class, user.getEmail())) {
            Group group = em.find(Group.class, groupName);
            if (group == null) {
                group = new Group(groupName);
            }
            user.addGroup(group);
            group.addUser(user);
            em.persist(user);
            em.flush();
        } else {
            throw new UserExistsException();
        }
    }
    
    /**
     * finds a user given the username
     * @param username the string what is the username to be found
     * @return the user with the matching username; <code>null</code> otherwise
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User find(String userName) {
        return em.find(User.class, userName);
    }

}
