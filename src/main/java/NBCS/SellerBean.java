/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Car;
import NBCS.EntityClasses.Request;
import NBCS.EntityClasses.Response;
import NBCS.EntityClasses.ReverseCarStore;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * SellerBean encapsulates the services that define a seller.
 *
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 */
@Named
@ViewScoped
public class SellerBean implements Serializable{

    @EJB
    private ReverseCarStore reverseCarStore;
    private List<Response> pinnedCars;
    private List<Request> openRequests;
    private List<Car> cars;
    private Boolean hasCars;
    private double pinPrice;
    private Car pinChoice;
    private Request request;
    private Map<String,Car> carsList;

    @PostConstruct
    private void intialize() {
        List<Request> sitewideRequests = reverseCarStore.getAllRequests();
        List<Request> userRequests = reverseCarStore.getRequestsOfUser();
        sitewideRequests.removeAll(userRequests);
//        pinnedCars = reverseCarStore.getPinnedCars();
        openRequests = sitewideRequests;
        hasCars = false;
        cars = reverseCarStore.getCarsOfUser();
        if (!cars.isEmpty()) {
            hasCars = true;
        }
        carsList = new HashMap<>();
        for (Car c : cars) {
            carsList.put(c.toString(), c);
        }
    }

    public double getPinPrice() {
        return pinPrice;
    }

    public void setPinPrice(double pinPrice) {
        this.pinPrice = pinPrice;
    }

    public Boolean getHasCars() {
        return hasCars;
    }

    public Map<String, Car> getCarsList() {
        return carsList;
    }


    public List<Request> getOpenRequests() {
        return openRequests;
    }

    public Car getPinChoice() {
        return pinChoice;
    }

    public void setPinChoice(Car pinChoice) {
        this.pinChoice = pinChoice;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Collection of pinned cars that the seller has pinned to requests.
     *
     * @return pinned cars
     */
    public List<Response> getPinnedCars() {
        return this.pinnedCars;
    }

    /**
     * The collection of cars in a user's garage.
     *
     * @return list of cars in a user's inventory
     */
    public List<Car> getCars() {
        return this.cars;
    }

    public void doCreateResponse() {
        reverseCarStore.createResponse(pinChoice, request, pinPrice);
    }

}
