package NBCS.EntityClasses;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author xaviermartinez
 */
@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private Integer yearFrom;
    @Column
    private Integer yearTo;
    @Column
    private Double priceLow;
    @Column
    private Double priceHigh;
    @Column
    private Integer rangeFromLocation;
    @Column
    private String titleStatus;
    @Column
    private Integer milage;
    @Column
    private String status;
    @Column
    private LocalDate timeout;

    /**
     * Creates a new instance of Request
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
        this.milage = 0;
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

    public Integer getMilage() {
        return milage;
    }

    public void setMilage(Integer milage) {
        this.milage = milage;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", make=" + make + ", model=" + model + ", yearFrom=" + yearFrom + ", yearTo=" + yearTo + ", priceLow=" + priceLow + ", priceHigh=" + priceHigh + ", rangeFromLocation=" + rangeFromLocation + ", titleStatus=" + titleStatus + ", milage=" + milage + ", status=" + status + ", timeout=" + timeout + '}';
    }

}
