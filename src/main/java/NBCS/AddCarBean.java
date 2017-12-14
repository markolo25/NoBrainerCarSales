/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * AddCarBean encapsulates the services that allow a user to add a car to their garage.
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

//    private CarSelections carSelections;
    /**
     * Creates a new instance of AddCarBean.
     */
    public AddCarBean() {
        this.car = new Car();
//        this.carSelections = new CarSelections();
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

//    public void updateYearsMakesModelsFromVIN() {
//        this.carSelections.setYearsMakesModelsByVIN(this.testVin);
//    }
//    public void yearChangeListener(){
//        this.setMakesDisabled("false");
//    }
//    public void makeChangeListener(){
//        this.setModelsDisabled("false");
//        this.carSelections.setModelsByYearAndMake(String.valueOf(this.testYear), this.testMake);
//    }
//    public CarSelections getCarSelections() {
//        return carSelections;
//    }
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

    public Integer getNumCars() {
        if (numCars == null) {
            numCars = getCars().size();
        }
        return this.numCars;
    }

    }
