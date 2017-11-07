/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

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
    private String testModel;
    private String modelsDisabled;

    public String getTestModel() {
        return testModel;
    }

    public void setTestModel(String testModel) {
        this.testModel = testModel;
    }

    public void setTestMake(String testMake) {
        this.testMake = testMake;
    }
    private Integer testYear;

    private CarSelections carSelections;

    /**
     * Creates a new instance of AddCarBean
     */
    public AddCarBean() {
//        this.car = new Car();
        this.carSelections = new CarSelections();
        this.modelsDisabled = "true";
    }

    public String getModelsDisabled() {
        return modelsDisabled;
    }

    public void setModelsDisabled(String modelsDisabled) {
        this.modelsDisabled = modelsDisabled;
    }

    public void updateYearsMakesModelsFromVIN() {
        this.carSelections.setYearsMakesModelsByVIN(this.testVin);
    }

    public void makeChangeListener(){
        this.carSelections.setModelsByYearAndMake(String.valueOf(this.testYear), testMake);
        this.modelsDisabled = "false";
    }

    public CarSelections getCarSelections() {
        return carSelections;
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
        System.out.println("VIN: " + this.testVin + " Year: " + this.testYear + " Make: " + this.testMake + " Model: " + this.testModel);
    }

    public String getTestMake() {
        return testMake;
    }

}
