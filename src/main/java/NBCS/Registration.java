/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

/**
 *
 * Test File Following Monge's example to fit NBCS.
 * 
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 * @author David Nguyen <nguyendavid095@gmail.com>
 * 
 */

import NBCS.EntityClasses.ReverseCarStore;
import NBCS.EntityClasses.User;
import NBCS.EntityClasses.UserExistsException;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class Registration implements Serializable {
    
    public static final Logger LOGGER = Logger.getLogger("Registration");
    
    private User newUser;
    @EJB
    private ReverseCarStore reverseCarStore;

    private String passwordConfirmation;
    /** Creates a new instance of Registration */
    public Registration() {
        newUser = new User();
    }
    /**
     * JSF Action that uses the information submitted in the registration page to add user as a registered Bookstore user.
     * @return either failure, success, or register depending on successful registration.
     */
    public String registerUser() {
        String result;
        if (newUser.isInformationValid(passwordConfirmation));
        String clearText = newUser.getPassword();
        newUser.setPassword(HashPassword.getSHA512Digest(clearText));
        try {
            reverseCarStore.registerUser(newUser, "reverseCarStore.user");
            result = "success";
            LOGGER.severe("Registered user: " + newUser);
            
        } catch (UserExistsException uee) {
            result = "register";
        }
        return result;
    }
    /**
     * @return the user
     */
    public User getNewUser() {
        return newUser;
    }
    /**
     * @param user the user to set
     */
    public void setNewUser(User user) {
        this.newUser = user;
    }
    /**
     * @return the passwordConfirmation
     */
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
    /**
     * @param passwordConfirmation the passwordConfirmation to set
     */
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
