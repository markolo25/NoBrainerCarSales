/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2013-2017 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */
package NBCS.Validators;

import NBCS.EntityClasses.ReverseCarStore;
import NBCS.EntityClasses.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 * RegistrationNameValidator extends the Validator class Validate that the
 user's registered screenName is not / does not: Less than some number of
 characters, greater than some number of characters, or
 contain special characters.
 *
 * @author Xavier Martinez
 * @author BalusC
 * https://stackoverflow.com/questions/20146043/validation-with-an-ejb-service-call-possible-with-jsf-validator-or-jsr303-bean
 */
@Named
@RequestScoped
public class RegistrationScreenNameValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger("ZipCodeValidator");

    @EJB
    private ReverseCarStore reverseCarStore;

    /**
     * Validates the screen name of a registering user.
     *
     * @param context the Faces context
     * @param component the UIComponent that is being validated
     * @param value the value of the UIComponent to be validated
     * @throws ValidatorException exception thrown when the value does not meet
     * the business requirements
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        // Compile Regex pattern for validation
        // https://stackoverflow.com/questions/1795402/java-check-a-string-if-there-is-a-special-character-in-it
        Pattern p = Pattern.compile("([^a-zA-Z0-9-]|\\^|_)", Pattern.CASE_INSENSITIVE);

        // Cast the value of the entered text of the screenName field back to String.
        String screenName = (String) value;

        // Test screenName for uniqueness in the system
        boolean a = true;
        User test = reverseCarStore.findByScreenName(screenName);
        if (test != null) {
            a = false;
        }

        // Test screenName for special characters that are not "-"
        Matcher m = p.matcher(screenName);
        boolean b = m.find();



        // Check if the screenName is actually entered.
        if (screenName != null) {
            // Check for names exceeding max size, being too small, or containing special characters (except "-")
            if (screenName.length() > 20) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Screen name is too large.", "Must be less than 20 characters."));
            } else if (screenName.length() < 4) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Screen name is too small.", "Must be greater than 3 characters."));
            } else if (!a) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Screen name is already taken.", "Try again."));
            } else if (b) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name contains special characters.", "Must not contain `~!@#$%^&*()_+={[}]|\\;:'\",<.>/?"));
            }
        } else {
            LOGGER.log(Level.SEVERE, "Screen name is null.");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Internal error.", "Please try again."));
        }

    }
}
