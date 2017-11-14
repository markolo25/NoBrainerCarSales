package NBCS.EntityClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anthony Lopez <Anthony.Lopez@student.csulb.edu>
 */

@NamedQueries ({
    @NamedQuery(name = User.FIND_USER_BY_SCREENNAME, query = "SELECT u FROM "
            + "User u where u.screenName = :screenName"),
    @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u FROM User u "
            + "where u.email = :email")
})

@Entity
@Table (name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Name of JPQL Query to find User by screen name. */
    public static final String FIND_USER_BY_SCREENNAME = "User.findUserByscreenName";
    /** Name of JPQL query to find User by email. */
    public static final String FIND_USER_BY_EMAIL =  "User.findUserByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private Integer zipCode;
    @Column (unique = true)
    private String screenName;
    private String phone;
    @NotNull
    @Column(length=200)
    private String password;

    @OneToMany (mappedBy="user", cascade=CascadeType.ALL)
    private Collection<Request> requests;

    @OneToMany (mappedBy="user", cascade=CascadeType.ALL)
    private Collection<Car> cars;

    /** Creates new instance of User. */
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets the requests that this user has created
     * @return a collection of requests that this user created
     */
    public Collection<Request> getRequests() {
        return requests;
    }

    /**
     * sets the requests that this user has created
     * @param requests is the collection of requests that this user created
     */
    public void setRequests(Collection<Request> requests) {
        this.requests = requests;
    }

    /**
     * Add a request to the user's set of requests
     * @param request to be added
     */
    public void addRequest(Request request) {
        if (this.requests == null)
            this.requests = new HashSet();
        this.requests.add(request);
    }

    /**
     * gets the cars that this user has added to inventory
     * @return a collection of cars that this user has in inventory
     */
    public Collection<Car> getCars() {
        return cars;
    }

    /**
     * sets the cars that this user has created
     * @param cars is the collection of cars that this user has in inventory
     */
    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    /**
     * Add a car to the user's inventory
     * @param car to be added
     */
    public void addCar(Car car) {
        if (this.cars == null)
            this.cars = new HashSet();
        this.cars.add(car);
    }

    /**
     * determines whether or not the information for this book is valid
     * @param confirmPassword the password to be confirmed
     * @return <code>true</code> if this book has valid information;
     *         <code>false</code> otherwise
     */
    public boolean isInformationValid(String confirmPassword) {
        return (name != null && email != null && password != null
                 && confirmPassword.equals(password));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the email fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return (this.email != null || other.email == null) &&
                (this.email == null || this.email.equals(other.email));
    }

     @Override
    public String toString() {
        return "User[name=" + name + ", email=" + email + ", zipcode=" + zipCode
                + ", screeName=" + screenName + ", phone=" + phone + "]";
    }

}
