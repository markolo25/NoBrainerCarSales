/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
@Named(value = "addCarBean")
@RequestScoped
public class AddCarBean {

//    private Car car;
//    @Inject
//    private UserBean seller;
    private String testVin;
    private String testMake;

    public void setTestMake(String testMake) {
        this.testMake = testMake;
    }
    private Integer testYear;
    private Map<String,Integer> yearChoices;
    private CarSelections carSelections;

    /**
     * Creates a new instance of AddCarBean
     */
    public AddCarBean() {
//        this.car = new Car();
        this.yearChoices = new LinkedHashMap<>();
        for (int i = 1980; i <= LocalDate.now().getYear(); i++){
            this.yearChoices.put(String.valueOf(i), i);
        }
        this.carSelections = new CarSelections();
    }

    public CarSelections getCarSelections() {
        return carSelections;
    }

    public void setCarSelections(CarSelections carSelections) {
        this.carSelections = carSelections;
    }

    public Integer getTestYear() {
        return testYear;
    }

    public void setTestYear(Integer testYear) {
        this.testYear = testYear;
    }

    public String getTestVin() {
        return testVin;
    }

    public void setTestVin(String testVin) {
        this.testVin = testVin;
    }

    public void doAddCar() {
        this.carSelections = new CarSelections();
        System.out.println("VIN: " + this.testVin + " Year: " + this.testYear + " Make: " + this.testMake);
        this.carSelections.setModels(String.valueOf(this.testYear), this.testMake);
        System.out.println(this.carSelections.getModels());
    }

    public String getTestMake() {
        return testMake;
    }

    public Map<String, Integer> getYearChoices() {
        return yearChoices;
    }

}
