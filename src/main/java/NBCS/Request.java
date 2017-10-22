package NBCS;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author xaviermartinez
 */
@Named(value = "request")
@RequestScoped
public class Request {

    private String make;
    private String model;
    private Integer yearFrom;
    private Integer yearTo;
    private Double priceLow;
    private Double priceHigh;
    private Double rangeFromLocation;
    private String titleStatus;
    private Double milage;

    /**
     * Creates a new instance of Request
     */
    public Request() {
        this.make = "Any";
        this.model = "Any";
        this.yearFrom = null;
        this.yearTo = null;
        this.priceLow = null;
        this.priceHigh = null;
        this.rangeFromLocation = null;
        this.titleStatus = "Any";
        this.milage = null;
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

    public Double getRangeFromLocation() {
        return rangeFromLocation;
    }

    public void setRangeFromLocation(Double rangeFromLocation) {
        this.rangeFromLocation = rangeFromLocation;
    }

    public String getTitleStatus() {
        return titleStatus;
    }

    public void setTitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    public Double getMilage() {
        return milage;
    }

    public void setMilage(Double milage) {
        this.milage = milage;
    }

    @Override
    public String toString() {
        return "Request{" + "make=" + make + ", model=" + model + ", yearFrom=" + yearFrom + ", yearTo=" + yearTo + ", priceLow=" + priceLow + ", priceHigh=" + priceHigh + ", rangeFromLocation=" + rangeFromLocation + ", titleStatus=" + titleStatus + ", milage=" + milage + '}';
    }

}
