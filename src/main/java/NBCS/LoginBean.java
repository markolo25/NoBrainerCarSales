package NBCS;

import NBCS.EntityClasses.ReverseCarStore;
import NBCS.EntityClasses.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Mark Mendoza <markolo25@gmail.com>
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable {
    
    
    /*
    Notes for the team, 
    */

    private static final long serialVersion = 1L;
    private User user;

    
    
    @EJB
    ReverseCarStore reverseCarStore;
    
    
    /**
     * 
     */
    public LoginBean(){
        user = null;
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
