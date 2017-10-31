/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NBCS;
/**
 *
 * @author David Nguyen <nguyendavid095@gmail.com>
 */

import NBCS.EntityClasses.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value ="registerBean")
@RequestScoped
public class RegisterBean implements Serializable{
    
    private User newUser;
    //@EJB
    //private NBCS nbcs;
    /**
     * Create a new User!
     */
    public RegisterBean(){
        newUser = new User();
    }
   
    /**
     * @return the new User
     */
    public User getNewUser(){
        return newUser;
    }
    /**
     * @param user to the new user
     */
    public void setNewUser(User user){
        this.newUser = user;
    }
    
    //EJB NBCS // ReverseCarStore
    public void doCreateUser(){
        System.out.println(this.newUser);
    }
}
