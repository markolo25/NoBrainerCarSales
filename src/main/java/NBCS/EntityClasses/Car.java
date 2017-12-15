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
@NamedQueries({
    @NamedQuery(name = Car.FIND_ALL_CARS, query = "SELECT c FROM Car c")
    ,
    @NamedQuery(name = Car.FIND_CAR_BY_EMAIL, query = "SELECT c FROM "
            + "Car c WHERE c.user.email = :email")
    ,
    @NamedQuery(name = Car.FIND_CAR_BY_SCREENNAME, query = "SELECT c FROM "
            + "Car c WHERE c.user.screenName = :screenName")
    ,
    @NamedQuery(name = Car.FIND_CAR_BY_CARYEAR, query = "SELECT c FROM Car c WHERE "
            + "c.carYear = :carYear")
    ,
    @NamedQuery(name = Car.FIND_CAR_BY_MAKE, query = "SELECT c FROM Car c WHERE "
            + "c.make = :make")
    ,
    @NamedQuery(name = Car.FIND_CAR_BY_MODEL, query = "SELECT c FROM Car c WHERE "
            + "c.model = :model")
})

@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Name of JPQL query to find all Cars.
     */
    public static final String FIND_ALL_CARS = "Car.getAll";
    /**
     * Name of JPQL query to find Cars in a User's Inventory by email.
     */
    public static final String FIND_CAR_BY_EMAIL = "Car.findCarByEmail";
    /**
     * Name of JPQL query to find Cars in a User's Inventory by screen name.
     */
    public static final String FIND_CAR_BY_SCREENNAME = "Car.findCarByscreenName";
    /**
     * Name of JPQL query to find Cars by year.
     */
    public static final String FIND_CAR_BY_CARYEAR = "Car.findCarByCarYear";
    /**
     * Name of JPQL query to find Cars by make.
     */
    public static final String FIND_CAR_BY_MAKE = "Car.findCarByMake";
    /**
     * Name of JPQL query to find Cars by model.
     */
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
    @Column(name = "car_condition")
    private String condition;
    private String status;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Collection<Response> responses;

    /**
     * Creates new instance of Car.
     */
    public Car() {
        this.status = "Unsold";
    }

    /**
     * Get the id of the car
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id of the car
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the vin of the car
     *
     * @return the vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * Set the vin of the car
     *
     * @param vin
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Get the year of the car
     *
     * @return the year
     */
    public Integer getCarYear() {
        return carYear;
    }

    /**
     * Set the year of the car
     *
     * @param carYear
     */
    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    /**
     * Get the make of the car
     *
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * Set the make of the car
     *
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Get the model of the car
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model of the car
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the mileage of the car
     *
     * @return the mileage
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * Set the mileage of the car
     *
     * @param mileage
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * Get the title status of the car
     *
     * @return the title status
     */
    public String getTitleStatus() {
        return titleStatus;
    }

    /**
     * Set the title status of the car
     *
     * @param titleStatus
     */
    public void setTitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    /**
     * Get the description of the car
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the car
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the condition of the car
     *
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Set the condition of the car
     *
     * @param condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Get the status of the car
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the car
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the user of the car
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user of the car
     *
     * @param user
     */
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
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return carYear + ", " + make + ", " + model + ", " + mileage + " miles, " + titleStatus + ", " + condition + " condition";
    }

}
