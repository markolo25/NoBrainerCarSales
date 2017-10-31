package NBCS.EntityClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 */

@Entity(name = "Users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column
    private String firstName;
    @Column
    private String middleInitial;
    @Column
    private String lastName;
    @Column
    private Integer zipCode;
    @Column
    private String screenName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column(length=200, nullable=false)
    private String password;
    
    public User() {
        this.firstName = "";
        this.middleInitial = "";
        this.lastName = "";
        this.zipCode = 90000;
        this.screenName = "";
        this.email = "";
        this.phone = "";
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleInitial() {
        return middleInitial;
    }
    
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Integer getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getScreenName() {
        return screenName;
    }
    
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPassword() {
        return phone;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
