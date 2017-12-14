package NBCS.EntityClasses;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Response encapsulates the information representing a response. Each response
 * is automatically assigned an identifier. 
 * 
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 */

@NamedQueries ({
    @NamedQuery(name = Response.FIND_ALL_RESPONSES, query = "SELECT "
            + "r from Response r"),
    @NamedQuery(name = Response.FIND_RESPONSE_BY_USER, query = "SELECT r from"
            + " Response r where r.car.user = :user")
})

@Entity
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** Name of JPQL query that finds all Requests. */
    public static final String FIND_ALL_RESPONSES = 
            "Request.findAllResponses";
    /** Name of JPQL query that finds all Requests by User. */
    public static final String FIND_RESPONSE_BY_USER = 
            "Request.findUserResponse";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;
    private String status;
    
    @ManyToOne
    private Car car;
    @ManyToOne
    private Request request;
    
    /**
     * gets the price of this response
     * @return the double representing the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * sets the price of this response
     * @param price the double representing the price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * gets the status of this response
     * @return the string associated with the status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * sets the status of this response
     * @param status the string to be assigned to the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * gets the car of this response
     * @return 
     */
    public Car getCar() {
        return car;
    }
    /**
     * sets the car to this response
     * @param car the car assigned to the response
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
    /**
     * gets the request of this response
     * @return the request assigned to the response
     */
    public Request getRequest() {
        return request;
    }
    
    /**
     * sets the request of this response
     * @param request the request to be assigned to the response
     */
    public void setRequest(Request request) {
        this.request = request;
    }
    
    /**
     * gets the id of this response
     * @return the long representing the id of the response
     */
    public Long getId() {
        return id;
    }
    
    /**
     * sets the id of this response
     * @param id the long representing the id of the response
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Response)) {
            return false;
        }
        Response other = (Response) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NBCS.EntityClasses.Response[ id=" + id + " ]";
    }
    
}
