package NBCS.EntityClasses;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.persistence.TypedQuery;

/**
 * ReverseCarStore is a stateless EJB that encapsulates the basic reverse car
 * store services. Such services involve creating a request, adding a car to the
 * inventory, retrieving a list of cars from the inventory and etc.
 *
 * @author Chanon Chantaduly <chanon.chantaduly@student.csulb.edu>
 * @author Anthony Lopez <anthony.lopez@student.csulb.edu>
 * @author Mark Mendoza <markolo25@gmail.com>
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
@Stateless
@RolesAllowed({"reverseCarStore.user"})
public class ReverseCarStore {

    private static final Logger LOGGER = Logger.getLogger("ReverseCarStore");

    @PersistenceContext(unitName = "csulb.cecs493_NBCS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     * creates a request to be added to the buyer's list. Only a registered user
     * is allowed.
     *
     * @param request the request to be added to the database
     * @return request object
     */
    @RolesAllowed("reverseCarStore.user")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Request createRequest(Request request) {
        String userName = getUsernameFromSession();
        if (userName == null) {
            return null;
        } else {
            User user = find(userName);
            if (user != null) {
                user.addRequest(request);
                request.setUser(user);
            } else {
                return null;
            }
        }
        em.persist(request);
        return request;
    }

    /**
     * adds a car to the user's inventory. Only a registered user is allowed.
     *
     * @param car the car to be added to the inventory
     * @return the car object
     */
    @RolesAllowed("reverseCarStore.user")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Car addCarToInventory(Car car) {
        String userName = getUsernameFromSession();
        if (userName == null) {
            return null;
        } else {
            User user = find(userName);
            if (user != null) {
                user.addCar(car);
                car.setUser(user);
            } else {
                return null;
            }
        }
        em.persist(car);
        return car;
    }

    /**
     * registers a new user to the application. Anyone is allowed to do this.
     *
     * @param user the user to be added to the database
     * @param groupName the groupname to be assigned to the user
     * @throws UserExistsException the exception thrown if a user exists
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @PermitAll
    public void registerUser(User user, String groupName) throws UserExistsException {
        if (null == em.find(User.class, user.getEmail())) {
            Group group = em.find(Group.class, groupName);
            if (group == null) {
                group = new Group(groupName);
            }
            user.addGroup(group);
            group.addUser(user);
            em.persist(user);
            em.flush();
        } else {
            throw new UserExistsException();
        }
    }

    /**
     * gets the user name of the user from the session.
     *
     * @return the name of the user.
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String getUsernameFromSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String userName = request.getRemoteUser();
        return userName;
    }

    /**
     * finds a user given an email
     *
     * @param email the string that is the email to be found
     * @return the user with the matching email; <code>null</code> otherwise
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User find(String email) {
        return em.find(User.class, email);
    }

    /**
     * finds a user given the screen name
     *
     * @param screenName the string that is the screen name to be found
     * @return the user with the matching screen name; <code>null</code>
     * otherwise
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User findByScreenName(String screenName) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_USER_BY_SCREENNAME, User.class);
        query.setParameter("screenName", screenName);
        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException nr) {
            LOGGER.log(Level.SEVERE, nr.toString());
            LOGGER.log(Level.SEVERE, "No User by the screen name: {0}", screenName);
        }
        return result;
    }

    /**
     * gets the list of all cars currently in the car database
     *
     * @return the list of Cars in the Inventory
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Car> getCars() {
        TypedQuery<Car> query = em.createNamedQuery(Car.FIND_ALL_CARS, Car.class);
        return query.getResultList();
    }

    /**
     * gets the list of all cars currently in the car database owned by a user
     *
     * @author Mark Mendoza <markolo25@gmail.com>
     * @return the list of Cars in the Inventory belonging to user
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Car> getCarsOfUser() {
        TypedQuery<Car> query = em.createNamedQuery(Car.FIND_CAR_BY_EMAIL, Car.class);
        String userName = getUsernameFromSession();
        if (userName == null) {
            return null;
        } else {
            User user = find(userName);
            if (user != null) {
                query.setParameter("email", user.getEmail());
            } else {
                return null;
            }
        }
        return query.getResultList();
    }

    /**
     * Gets all requests from entity manager
     *
     * @return return list of all requests
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Request> getAllRequests() {
        TypedQuery<Request> query = em.createNamedQuery(Request.FIND_ALL_REQUESTS, Request.class);
        return query.getResultList();
    }

    /**
     * @author Mark Mendoza <markolo25@gmail.com>
     * Query Entity Manager for all requests of a certain user
     * @return Collection of requests that belong to a user
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Request> getRequestsOfUser() {
        TypedQuery<Request> query = em.createNamedQuery(Request.FIND_REQUEST_BY_EMAIL, Request.class);
        String userName = getUsernameFromSession();
        if (userName == null) {
            return null;
        } else {
            User user = find(userName);
            if (user != null) {
                query.setParameter("email", user.getEmail());
            } else {
                return null;
            }
        }
        return query.getResultList();
    }

    /**
     * Get the pins of the system
     *
     * @return the pins
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Response> getActiveRequests() {
        TypedQuery<Response> query = em.createNamedQuery(Response.FIND_ALL_RESPONSES, Response.class);
        return query.getResultList();
    }

    /**
     * gets cars pinned to a request by a seller
     *
     * @return the list of cars pinned by a seller
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Response> getPinnedCars() {
        TypedQuery<Response> query = em.createNamedQuery(Response.FIND_RESPONSE_BY_USER, Response.class);
        return query.getResultList();
    }

    /**
     * Persist a response (pin) into the database
     *
     * @param pin the car being pinned
     * @param request the request being pinned to
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @PermitAll
    public void createResponse(Car pin, Request request, double price) {
        Response response = new Response();
        response.setCar(pin);
        response.setRequest(request);
        response.setPrice(price);
        response.setStatus("Open");
        em.persist(response);
        em.flush();
    }
}
