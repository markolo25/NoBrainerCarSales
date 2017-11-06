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
//import javax.ejb.EJB;

/**
 *
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 * @author Anthony Lopez <anthony.lopez@student.csulb.edu>
 */
public class ReverseCarStoreBean implements Serializable {
   
   @EJB
   private ReverseCarStore reverseCarStore;
   private Request newRequest = new Request();
   
   public void doCreateRequest() {
      newRequest = reverseCarStore.createRequest(newRequest);
         if (newRequest != null) {
            System.out.println("Successfully created a request.");
         }
   }

   public Request getNewRequest() {
      return newRequest;
   }

   public void setNewRequest(Request newRequest) {
      this.newRequest = newRequest;
   }
   
}
