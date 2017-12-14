/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.EntityClasses;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Car encapsulates the information representing a car. Each car is
 * automatically assigned an identifier.
 *
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 */

@NamedQueries ({
    @NamedQuery(name = Car.FIND_ALL_CARS, query = "SELECT c FROM Car c" ),
    @NamedQuery(name = Car.FIND_CAR_BY_EMAIL, query = "SELECT c FROM "
            + "Car c WHERE c.user.email = :email"),
    @NamedQuery(name = Car.FIND_CAR_BY_SCREENNAME, query = "SELECT c FROM "
            + "Car c WHERE c.user.screenName = :screenName"),
    @NamedQuery(name = Car.FIND_CAR_BY_CARYEAR, query = "SELECT c FROM Car c WHERE "
            + "c.carYear = :carYear"),
    @NamedQuery(name = Car.FIND_CAR_BY_MAKE, query = "SELECT c FROM Car c WHERE "
            + "c.make = :make"),
    @NamedQuery(name = Car.FIND_CAR_BY_MODEL, query = "SELECT c FROM Car c WHERE "
            + "c.model = :model")
})

@Entity
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Name of JPQL query to find all Cars. */
    public static final String FIND_ALL_CARS = "Car.getAll";
    /** Name of JPQL query to find Cars in a User's Inventory by email. */
    public static final String FIND_CAR_BY_EMAIL = "Car.findCarByEmail";
    /** Name of JPQL query to find Cars in a User's Inventory by screen name. */
    public static final String FIND_CAR_BY_SCREENNAME = "Car.findCarByscreenName";
    /** Name of JPQL query to find Cars by year. */
    public static final String FIND_CAR_BY_CARYEAR = "Car.findCarByCarYear";
    /** Name of JPQL query to find Cars by make. */
    public static final String FIND_CAR_BY_MAKE = "Car.findCarByMake";
    /** Name of JPQL query to find Cars by model. */
    public static final String FIND_CAR_BY_MODEL = "Car.findCarByModel";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vin;
    @NotNull
    private Integer carYear;
    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private Integer mileage;
    @NotNull
    private String titleStatus;
    @Size(max = 2000)
    @Column(length = 2000)
    private String description;
    @Column(name="car_condition")
    private String condition;
    private String status;

    @ManyToOne
    private User user;
    @OneToMany (mappedBy="car", cascade= CascadeType.ALL)
    private Collection<Response> responses;

    /** Creates new instance of Car. */
    public Car() {
        this.status = "Unsold";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getTitleStatus() {
        return titleStatus;
    }

    public void setTitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case
        //the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return carYear + ", " + make + ", " + model + ", " + mileage + " miles, " + titleStatus + ", " + condition + " condition";
    }

}
