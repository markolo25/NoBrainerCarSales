package NBCS;

import NBCS.EntityClasses.ReverseCarStore;
import NBCS.EntityClasses.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mark Mendoza <markolo25@gmail.com>
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersion = 1L;
    private static final Logger logger = Logger.getLogger("LoginBean");
    private User user;

    @EJB
    ReverseCarStore reverseCarStore;

    /**
     * Creates a new instance of LoginBean
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
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String userName = request.getRemoteUser();
            if (userName == null) {
                isAuth = false;
            } else {
                this.user = reverseCarStore.find(userName);
                isAuth = (this.user != null);
                if (isAuth) {
                    logger.log(Level.SEVERE, "UserIdentiy::isUserAuthenticated: Changed session, so now userIdentiy object has user=authenticated user");
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
            logger.log(Level.SEVERE, null, ex);
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
