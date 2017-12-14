/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Response;
import NBCS.EntityClasses.ReverseCarStore;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *BuyerBean encapsulates the services that define a buyer.
 * 
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 */
@Named
@RequestScoped
public class BuyerBean {
   
   @EJB
   private ReverseCarStore reverseCarStore;
   private List<Response> activeRequests;
   /**
    * Collection of active requests that the buyer has made.
    * 
    * @return  active requests
    */
   public List<Response> getActiveRequests() {
      if (activeRequests == null || activeRequests.isEmpty()) {
         
            activeRequests = reverseCarStore.getActiveRequests();
            
        }
      return this.activeRequests;
   }
}
