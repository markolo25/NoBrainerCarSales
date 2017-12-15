package NBCS.EntityClasses;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Request encapsulates the information representing a request. Each request is
 * automatically assigned an identifier.
 *
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 */
@NamedQueries({
    @NamedQuery(name = Request.FIND_ALL_REQUESTS, query = "SELECT "
            + "r from Request r")
    ,
    @NamedQuery(name = Request.FIND_ALL_REQUESTS_BY_STATUS, query = "SELECT "
            + "r from Request r where r.status = :status ")
    ,
    @NamedQuery(name = Request.FIND_REQUEST_BY_EMAIL, query = "SELECT "
            + "r from Request r where r.user.email = :email")
    ,
    @NamedQuery(name = Request.FIND_REQUEST_BY_ID, query = "SELECT "
            + "r from Request r where r.id = :id")
})

@Entity
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Name of JPQL query that finds all Requests.
     */
    public static final String FIND_ALL_REQUESTS
            = "Request.findAllRequests";
    /**
     * Name of JPQL query that finds all Requests by status.
     */
    public static final String FIND_ALL_REQUESTS_BY_STATUS
            = "Request.findAllRequestsByStatus";
    /**
     * Name of JPQL query that finds Requests by User email.
     */
    public static final String FIND_REQUEST_BY_EMAIL
            = "Request.findRequestByEmail";
    /**
     * Name of JPQL query that finds Requests by id.
     */
    public static final String FIND_REQUEST_BY_ID
            = "Request.findRequestByID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String make;
    private String model;
    private Integer yearFrom;
    private Integer yearTo;
    private Double priceLow;
    private Double priceHigh;
    private Integer rangeFromLocation;
    private String titleStatus;
    private Integer mileage;
    private String status;
    private LocalDate timeout;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Collection<Response> responses;

    /**
     * Creates a new instance of Request.
     */
    public Request() {
        this.make = "Any";
        this.model = "Any";
        this.yearFrom = 1980;
        this.yearTo = 2017;
        this.priceLow = 0.0;
        this.priceHigh = 0.0;
        this.rangeFromLocation = 99;
        this.titleStatus = "Any";
        this.mileage = 0;
        this.timeout = LocalDate.now();
        this.status = "Open";
    }

    /**
     * gets the id of this request
     *
     * @return the integer representing the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id of this request
     *
     * @param id the integer representing the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * gets the status of this request.
     *
     * @return the string representing the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * sets the status for this request
     *
     * @param status the string representing the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * gets the make for this request
     *
     * @return the string representing the make
     */
    public String getMake() {
        return make;
    }

    /**
     * sets the make for this request
     *
     * @param make the string representing the make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * gets the model for this request
     *
     * @return the string representing the model
     */
    public String getModel() {
        return model;
    }

    /**
     * sets the model for this request
     *
     * @param model the string representing the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * gets the minimum price for this request
     *
     * @return the double representing the minimum price
     */
    public Double getPriceLow() {
        return priceLow;
    }

    /**
     * sets the minimum price for this request
     *
     * @param priceLow the double representing the minimum price
     */
    public void setPriceLow(Double priceLow) {
        this.priceLow = priceLow;
    }

    /**
     * gets the minimum year for this request
     *
     * @return the integer representing the minimum year
     */
    public Integer getYearFrom() {
        return yearFrom;
    }

    /**
     * sets the minimum year for this request
     *
     * @param yearFrom the integer representing the minimum year
     */
    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    /**
     * gets the maximum year for this request
     *
     * @return the integer representing the maximum year
     */
    public Integer getYearTo() {
        return yearTo;
    }

    /**
     * sets the maximum year for this request
     *
     * @param yearTo the integer representing the maximum year
     */
    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    /**
     * gets the maximum price for this request
     *
     * @return the double representing the maximum price
     */
    public Double getPriceHigh() {
        return priceHigh;
    }

    /**
     * sets the maximum price for this request
     *
     * @param priceHigh the double representing the maximum price
     */
    public void setPriceHigh(Double priceHigh) {
        this.priceHigh = priceHigh;
    }

    /**
     * gets the range from the user's location for this request
     *
     * @return the integer representing the amount of distance from the user
     */
    public Integer getRangeFromLocation() {
        return rangeFromLocation;
    }

    /**
     * sets the range from the user's location for this request
     *
     * @param rangeFromLocation the integer representing the amount of distance
     * from the user
     */
    public void setRangeFromLocation(Integer rangeFromLocation) {
        this.rangeFromLocation = rangeFromLocation;
    }

    /**
     * gets the timeout of this request
     *
     * @return the localdate representing the amount of time before timeout
     */
    public LocalDate getTimeout() {
        return timeout;
    }

    /**
     * sets the timeout of this request.
     *
     * @param timeout the localdate representing the amount of time before
     * timeout
     */
    public void setTimeout(LocalDate timeout) {
        this.timeout = timeout;
    }

    /**
     * gets the title status of this request
     *
     * @return the string representing the title status
     */
    public String getTitleStatus() {
        return titleStatus;
    }

    /**
     * set the title status of this request
     *
     * @param titleStatus the string representing the title status
     */
    public void setTitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    /**
     * gets the mileage of this request.
     *
     * @return the integer representing the mileage
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * sets the mileage of this request
     *
     * @param milage the integer representing the mileage
     */
    public void setMileage(Integer milage) {
        this.mileage = milage;
    }

    /**
     * gets the user of this request
     *
     * @return the user associated with this request
     */
    public User getUser() {
        return user;
    }

    /**
     * sets the user to this request
     *
     * @param user the user to be assigned to this request
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
        // TODO: Warning - this method won't work in the case the id
        //fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", make=" + make + ", model="
                + model + ", yearFrom=" + yearFrom + ", yearTo=" + yearTo
                + ", priceLow=" + priceLow + ", priceHigh=" + priceHigh
                + ", rangeFromLocation=" + rangeFromLocation + ", titleStatus="
                + titleStatus + ", milage=" + mileage + ", status=" + status
                + ", timeout=" + timeout + '}';
    }

}
