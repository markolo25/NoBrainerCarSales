package NBCS;

import NBCS.EntityClasses.Car;
import NBCS.EntityClasses.ReverseCarStore;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * AddCarBean encapsulates the services that allow a user to add a car to their
 * garage.
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 * @author Mark Mendoza <markolo25@gmail.com>
 */
@Named
@RequestScoped
public class AddCarBean {

    private static final Logger LOGGER = Logger.getLogger("AddCarBean");

    private Car car;
    @EJB
    private ReverseCarStore reverseCarStore;
    private List<Car> cars;
    private Integer numCars;

    /**
     * Creates a new instance of AddCarBean.
     */
    public AddCarBean() {
        this.car = new Car();
    }

    /**
     * The car that's currently being edited.
     *
     * @return car object
     */
    public Car getCar() {
        return car;
    }

    /**
     * The setter for the car that is to be submitted via the form.
     *
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Persist car into database through EJB
     *
     * @return "Success"/"Failure"
     */
    public String doAddCar() {
        String result = "Failure";
        car = this.reverseCarStore.addCarToInventory(this.car);
        if (car != null) {
            LOGGER.log(Level.SEVERE, "Added car to inventory: {0}", car);
        }
        result = "Success";
        return result;
    }

    /**
     * The collection of cars in a user's garage.
     *
     * @return list of cars in a user's inventory
     */
    public List<Car> getCars() {
        if (cars == null || cars.isEmpty()) {
            //cars = reverseCarStore.getCars(); //ALL Cars
            cars = reverseCarStore.getCarsOfUser(); //Cars only of user
        }
        return this.cars;
    }

    /**
     * Obtain the number of cars in a user's garage
     *
     * @return number of cars
     */
    public Integer getNumCars() {
        if (numCars == null) {
            numCars = getCars().size();
        }
        return this.numCars;
    }

}
