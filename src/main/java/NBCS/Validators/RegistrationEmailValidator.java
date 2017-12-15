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
 * user's registered email is not / does not: Less than some number of
 * characters, greater than some number of characters, or contain special
 * characters.
 *
 * @author Xavier Martinez
 * @author BalusC
 * https://stackoverflow.com/questions/20146043/validation-with-an-ejb-service-call-possible-with-jsf-validator-or-jsr303-bean
 */
@Named
@RequestScoped
public class RegistrationEmailValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger("RegistrationEmailValidator");

    @EJB
    private ReverseCarStore reverseCarStore;

    /**
     * Validates the email of a registering user.
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
        // https://stackoverflow.com/questions/742451/what-is-the-simplest-regular-expression-to-validate-emails-to-not-accept-them-bl
        Pattern p = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", Pattern.CASE_INSENSITIVE);

        // Cast the value of the entered text of the email field back to String.
        String email = (String) value;

        // Test email for uniqueness in the system
        boolean a = true;
        User test = reverseCarStore.find(email);
        if (test != null) {
            a = false;
        }

        // Test validate email (simply)
        Matcher m = p.matcher(email);
        boolean b = m.find();

        // Check if the email is actually entered.
        if (email != null) {
            if (!a) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is already registered.", "Try again."));
            } else if (!b) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email format is incorrect.", ""));
            }
        } else {
            LOGGER.log(Level.SEVERE, "Email is null.");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Internal error.", "Please try again."));
        }

    }
}
