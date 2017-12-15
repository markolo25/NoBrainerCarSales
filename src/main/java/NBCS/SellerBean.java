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
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * SellerBean encapsulates the services that define a seller.
 *
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
@Named
@ViewScoped
public class SellerBean implements Serializable {

    @EJB
    private ReverseCarStore reverseCarStore;
    private List<Response> pinnedCars;
    private List<Request> openRequests;
    private List<Car> cars;
    private Boolean hasCars;
    private double pinPrice;
    private Car pinChoice;
    private Request request;
    private Map<String, Car> carsList;

    /**
     * Initialize the class variables (for use in seller views)
     */
    @PostConstruct
    private void intialize() {
        List<Request> sitewideRequests = reverseCarStore.getAllRequests();
        List<Request> userRequests = reverseCarStore.getRequestsOfUser();
        sitewideRequests.removeAll(userRequests);
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

    /**
     * Get the price set for the pin
     * @return the price
     */
    public double getPinPrice() {
        return pinPrice;
    }

    /**
     * Set the price for the pin
     * @param pinPrice
     */
    public void setPinPrice(double pinPrice) {
        this.pinPrice = pinPrice;
    }

    /**
     * Test for a user having cars in their inventory
     * @return boolean true/false whether the user has cars in their inventory
     */
    public Boolean getHasCars() {
        return hasCars;
    }

    /**
     * Get a Map of Key: Car.toString(), Value: Car pairs of the user
     * @return the map
     */
    public Map<String, Car> getCarsList() {
        return carsList;
    }

    /**
     * Get a list of open requests in the system
     * @return the list of open requests
     */
    public List<Request> getOpenRequests() {
        return openRequests;
    }

    /**
     * Get the car that the user chose to pin
     * @return
     */
    public Car getPinChoice() {
        return pinChoice;
    }

    /**
     * Set the car that the user will pin
     * @param pinChoice
     */
    public void setPinChoice(Car pinChoice) {
        this.pinChoice = pinChoice;
    }

    /**
     * Get the request to be pinned to
     * @return the request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Set the request to be pinned to
     * @param request
     */
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

    /**
     * Persist the pin through the EJB
     */
    public void doCreateResponse() {
        reverseCarStore.createResponse(pinChoice, request, pinPrice);
    }

}
