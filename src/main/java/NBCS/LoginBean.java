package NBCS;

import NBCS.EntityClasses.ReverseCarStore;
import NBCS.EntityClasses.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *LoginBean encapsulates the services that allow the user to login.
 * 
 * @author Mark Mendoza <markolo25@gmail.com>
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersion = 1L;
    private static final Logger LOGGER = Logger.getLogger("UserBean");
    @Produces
    private User user;

    @EJB
    ReverseCarStore reverseCarStore;

    /**
     * Creates a new instance of LoginBean.
     */
    public LoginBean() {
        user = null;
    }

    /**
     * Determine if user is logged in, if he is then make the session scope for
     * that user
     *
     * @return true if user is logged in, else false
     */
    public boolean isUserAuthenticated() {
        boolean isAuth = true;
        if (null == this.user) {
            String userName = reverseCarStore.getUsernameFromSession();
            if (userName == null) {
                isAuth = false;
            } else {
                this.user = reverseCarStore.find(userName);
                isAuth = (this.user != null);
                if (isAuth) {
                    LOGGER.log(Level.SEVERE, "UserIdentiy::isUserAuthenticated: Changed session, so now userIdentiy object has user=authenticated user");
                }
            }
        }
        return isAuth;
    }

    /**
     * Prof.Monge: Logout the user and invalidate the session
     *
     * @return success if user is logged out and session invalidated, failure
     * otherwise.
     */
    public String logout() {
        String result = "failure";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            user = null;
            result = "success";
        } catch (ServletException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        return result;
    }

    /* Encapsulation*/
    /**
     * The user that's currently being edited.
     * 
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * The setter for the user that is to be submitted via the form.
     * 
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
