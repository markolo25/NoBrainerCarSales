package NBCS.Validators;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * RegistrationNameValidator extends the Validator class Validate that the
 * user's registered name is not / does not: Less than some number of
 * characters, greater than some number of characters, or contain special
 * characters.
 *
 * @author Xavier Martinez
 * @author Brian Leathem (Provided example)
 * http://stackoverflow.com/questions/2909021/jsf-2-0-validate-equality-of-2-inputsecret-fields-confirm-password-without-wri
 */
@FacesValidator(value = "registrationNameValidator")
public class RegistrationNameValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger("RegistrationEmailValidator");

    /**
     * Validates the name of a registering user.
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

        // Cast the value of the entered text of the name field back to String.
        String name = (String) value;

        // Test name for special characters that are not "-"
        Matcher m = p.matcher(name);
        boolean b = m.find();

        // Check if the name is actually entered.
        if (name != null) {
            // Check for names exceeding max size, being too small, or containing special characters (except "-")
            if (name.length() > 20) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is too large.", "Must be less than 20 characters."));
            } else if (name.length() < 4) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is too small.", "Must be greater than 3 characters."));
            } else if (b) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name contains special characters.", "Must not contain `~!@#$%^&*()_+={[}]|\\;:'\",<.>/?"));
            }
        } else {
            LOGGER.log(Level.SEVERE, "Name is null.");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Internal error.", "Please try again."));
        }

    }
}
