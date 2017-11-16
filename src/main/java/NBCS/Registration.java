/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

/**
 *
 * @author David Nguyen <nguyendavid095@gmail.com>
 * Test File Following Monge's example.
 */

import NBCS.EntityClasses.ReverseCarStore;
import NBCS.EntityClasses.User;
import NBCS.EntityClasses.UserExistsException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class Registration implements Serializable {

    private User newUser;
    @EJB
    private ReverseCarStore reverseCarStore;

    private String passwordConfirmation;

    public Registration() {
        newUser = new User();
    }

    public String registerUser() {
        String result;
        if (newUser.isInformationValid(passwordConfirmation));
        String clearText = newUser.getPassword();
        newUser.setPassword(HashPassword.getSHA512Digest(clearText));
        try {
            reverseCarStore.registerUser(newUser, "reverseCarStore.user");
            result = "success";
        } catch (UserExistsException uee) {
            result = "register";
        }
        return result;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User user) {
        this.newUser = user;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}