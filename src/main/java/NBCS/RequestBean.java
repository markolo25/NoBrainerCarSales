/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Request;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
@Named(value = "requestBean")
@RequestScoped
public class RequestBean {

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

    // To-do: Integrate with EJB to persist the request
    public void doCreateRequest(){
        System.out.println(this.newRequest);
    }

}
