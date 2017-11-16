/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Request;
import NBCS.EntityClasses.ReverseCarStore;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 * @author Chaon Chantaduly <chanon.chantaduly@student.csulb.edu>
 */

@Named(value = "requestBean")
@RequestScoped
public class RequestBean {

    @EJB
    private ReverseCarStore reverseCarStore;
    private Request newRequest;
    private Map<String,LocalDate> timeoutChoices;

    /**
     * Creates a new instance of RequestBean
     */
    public RequestBean() {
        this.newRequest = new Request();
        timeoutChoices = new LinkedHashMap<>();
        timeoutChoices.put("5 Days", LocalDate.now().plusDays(5));
        timeoutChoices.put("10 Days", LocalDate.now().plusDays(10));
        timeoutChoices.put("20 Days", LocalDate.now().plusDays(20));
        timeoutChoices.put("30 Days", LocalDate.now().plusDays(30));
    }

    public Request getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(Request newRequest) {
        this.newRequest = newRequest;
    }

    public Map<String, LocalDate> getTimeoutChoices() {
        return timeoutChoices;
    }

    public void doCreateRequest() {
        newRequest = reverseCarStore.createRequest(newRequest);
        if (newRequest != null) {
            System.out.println("Successfully created a request.");
        }
   }
}
