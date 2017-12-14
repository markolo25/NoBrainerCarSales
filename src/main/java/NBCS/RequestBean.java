/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Request;
import NBCS.EntityClasses.ReverseCarStore;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * RequestBean encapsulates all the services needed to create a request.
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 * @author David Nguyen <David.Nguyen07@student.csulb.edu>
 */
@Named
@RequestScoped
public class RequestBean {

    public static final Logger LOGGER = Logger.getLogger("RequestBean");

    @EJB
    private ReverseCarStore reverseCarStore;
    private Request newRequest;
    private Map<String, LocalDate> timeoutChoices;
    private List<Request> requests;
    private Integer numRequestOfUser;

    /**
     * Creates a new instance of RequestBean.
     */
    public RequestBean() {
        this.newRequest = new Request();
        timeoutChoices = new LinkedHashMap<>();
        timeoutChoices.put("5 Days", LocalDate.now().plusDays(5));
        timeoutChoices.put("10 Days", LocalDate.now().plusDays(10));
        timeoutChoices.put("20 Days", LocalDate.now().plusDays(20));
        timeoutChoices.put("30 Days", LocalDate.now().plusDays(30));
    }

    /**
     * The request that is currently being edited.
     *
     * @return request object
     */
    public Request getNewRequest() {
        return newRequest;
    }

    /**
     * The setter for the request that is to be submitted via the form.
     *
     * @param newRequest
     */
    public void setNewRequest(Request newRequest) {
        this.newRequest = newRequest;
    }

    /**
     * Map of timeout choices available for the request.
     *
     * @return a list of timeout choices.
     */
    public Map<String, LocalDate> getTimeoutChoices() {
        return timeoutChoices;
    }

    /**
     * Creates a request
     *
     * @return message regarding whether it succeeded or not for redirect
     */
    public String doCreateRequest() {
        String result = "Failure";
        newRequest = reverseCarStore.createRequest(newRequest);
        if (newRequest != null) {
            LOGGER.severe("Created request: " + newRequest);
        }
        result = "Success";
        return result;
    }


    /**
     * Get list of All requests from ejb
     * @return list of all requests
     */
    public List<Request> getRequests() {
        if (requests == null || requests.isEmpty()) {
            requests = reverseCarStore.getAllRequests();
        }
        return this.requests;
    }

    /**
     * Get List of Requests for a User
     * @return list of requests that belong to a user
     */
    public List<Request> getRequestOfUser() {
        if (requests == null || requests.isEmpty()) {
            requests = reverseCarStore.getRequestsOfUser();
        }
        return this.requests;
    }

    public Integer getNumRequestOfUser() {
        if (numRequestOfUser == null) {
            numRequestOfUser = getRequestOfUser().size();
        }
        return numRequestOfUser;
    }

}
