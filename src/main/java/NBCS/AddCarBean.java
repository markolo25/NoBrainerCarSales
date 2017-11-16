/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import NBCS.EntityClasses.Car;
import NBCS.EntityClasses.ReverseCarStore;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
@Named
@RequestScoped
public class AddCarBean {

    private Car car;
    @EJB
    private ReverseCarStore reverseCarStore;

    @Inject
    private LoginBean user_session;

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
        this.car.setUser(user_session.getUser());
        car = this.reverseCarStore.addCarToInventory(this.car);
        if (car != null) {
            System.out.println("Successfully added a car.");
        }
    }

}
