package NBCS.EntityClasses;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author xaviermartinez
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 */

@NamedQueries ({
    @NamedQuery(name = Request.FIND_REQUEST_BY_EMAIL, query = "SELECT "
            + "r from Request r where r.user.email = :email"),
    @NamedQuery(name = Request.FIND_REQUEST_BY_SCREENNAME, query = "SELECT "
            + "r from Request r where r.user.screenName = :screenName")
})

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Name of JPQL query that finds Requests by User email. */
    public static final String FIND_REQUEST_BY_EMAIL= 
            "Request.findRequestByEmail";
    /** Name of JPQL query that finds Requests by User screen name. */
    public static final String FIND_REQUEST_BY_SCREENNAME = 
            "Request.findRequestByscreenName";

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

    /** Creates a new instance of Request. */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Double getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Double priceLow) {
        this.priceLow = priceLow;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }


    public Double getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(Double priceHigh) {
        this.priceHigh = priceHigh;
    }

    public Integer getRangeFromLocation() {
        return rangeFromLocation;
    }

    public void setRangeFromLocation(Integer rangeFromLocation) {
        this.rangeFromLocation = rangeFromLocation;
    }

    public String getTitleStatus() {
        return titleStatus;
    }

    public LocalDate getTimeout() {
        return timeout;
    }

    public void setTimeout(LocalDate timeout) {
        this.timeout = timeout;
    }


    public void setTitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer milage) {
        this.mileage = milage;
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
        // TODO: Warning - this method won't work in the case the id 
        //fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id))) {
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
