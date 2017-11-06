/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.EntityClasses;

import javax.ejb.Stateless;
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
   
   public Request createRequest(Request request) {
      // TODO: Check if a request is valid
      em.persist(request);
      return request;
   }
}
