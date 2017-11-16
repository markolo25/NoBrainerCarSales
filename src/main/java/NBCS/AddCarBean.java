/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Car;
import NBCS.EntityClasses.ReverseCarStore;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * 
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 * @author Mark Mendoza <markolo25@gmail.com>
 */
@Named
@RequestScoped
public class AddCarBean {

    private Car car;
    @EJB
    private ReverseCarStore reverseCarStore;
    private List<Car> cars;

//    private CarSelections carSelections;
    /**
     * Creates a new instance of AddCarBean
     */
    public AddCarBean() {
        this.car = new Car();
//        this.carSelections = new CarSelections();
    }

    public Car getCar() {
        return car;
    }

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
    public void doAddCar() {
        car = this.reverseCarStore.addCarToInventory(this.car);
        if (car != null) {
            System.out.println("Successfully added a car.");
        }
    }

    public List<Car> getCars() {
        if (cars == null || cars.isEmpty()) {
            //cars = reverseCarStore.getCars(); //ALL Cars
            cars = reverseCarStore.getCarsOfUser(); //Cars only of user
        }
            return this.cars;
        }

    }