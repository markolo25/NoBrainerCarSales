/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;

/**
 *
 * @author AnthonyLopez
 */
@Entity
public class RequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Date dateCreated;
    private String make;
    private String model;
    private int carYear;
    private double priceLow;
    private double priceHigh;
    private int maxMileage;
    private String titleStatus;
    private int radiusFromZip;
    private Date timeout;
    private String requestStatus;
    
    public RequestEntity() {
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getdateCreated(){
        return dateCreated;
    }
    
    public void setdateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
    
    public int getcarYear() {
        return carYear;
    }
    
    public void setcarYear(int carYear) {
        this.carYear = carYear;
    }
    
    public double getpriceLow() {
        return priceLow;
    }
    
    public void setpriceLow(double priceLow) {
        this.priceLow = priceLow;
    }
    
    public double getpriceHigh() {
        return priceHigh;
    }
    
    public void setpriceHigh(double priceHigh) {
        this.priceHigh = priceHigh;
    }
    
    public int getmaxMileage() {
        return maxMileage;
    }
    
    public void setmaxMileage(int maxMileage) {
        this.maxMileage = maxMileage;
    }
    
    public String gettitleStatus() {
        return titleStatus;
    }
    
    public void settitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }
    
    public int getradiusFromZip() {
        return radiusFromZip;
    }
    
    public void setradiusFromZip(int radiusFromZip) {
        this.radiusFromZip = radiusFromZip;
    }
    
    public Date gettimeout(){
        return timeout;
    }
    
    public void settimeout(Date timeout) {
        this.timeout = timeout;
    }
    
    public String getrequestStatus() {
        return requestStatus;
    }
    
    public void setrequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
    
    public RequestEntity(String make, String model, int carYear, double priceLow, 
            double priceHigh, int maxMileage, String titleStatus, 
            int radiusFromZip, String requestStatus) {
        //this.dateCreated = dateCreated;
        this.make = make;
        this.model = model;
        this.carYear = carYear;
        this.priceLow = priceLow;
        this.priceHigh = priceHigh;
        this.maxMileage = maxMileage;
        this.titleStatus = titleStatus;
        this.radiusFromZip = radiusFromZip;
        //this.timeout = timeout;
        this.requestStatus = requestStatus;
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
        if (!(object instanceof RequestEntity)) {
            return false;
        }
        RequestEntity other = (RequestEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Request[ id=" + id + " ]";
    }
    
}
