/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Request;
import NBCS.EntityClasses.ReverseCarStore;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 * @author Anthony Lopez <anthony.lopez@student.csulb.edu>
 */

@Named
@RequestScoped
public class ReverseCarStoreBean implements Serializable {
   
   @EJB
   private ReverseCarStore reverseCarStore;
   private Request newRequest = new Request();
   
   /**
    * Create a request submitted via form
    */
   public void doCreateRequest() {
      newRequest = reverseCarStore.createRequest(newRequest);
         if (newRequest != null) {
            System.out.println("Successfully created a request.");
         }
   }

   // ======================================
   // =          Getters & Setters         =
   // ======================================
   
   /**
    * The request thats currently being created
    * 
    * @return the request thats to be made
    */
   public Request getNewRequest() {
      return newRequest;
   }

   /**
    * Setter for request thats to be submitted via form
    * 
    * @param newRequest request thats to be assigned as new request
    */
   public void setNewRequest(Request newRequest) {
      this.newRequest = newRequest;
   }
   
}
