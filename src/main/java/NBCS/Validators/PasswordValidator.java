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
 * PasswordValidator extends the Validator
 *
 * @author Xavier Martinez
 * @author Brian Leathem (Provided example)
 * http://stackoverflow.com/questions/2909021/jsf-2-0-validate-equality-of-2-inputsecret-fields-confirm-password-without-wri
 */
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger("PasswordValidator");

    /**
     * Validates the password of a registering user.
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
        // https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);

        // Cast the value of the entered text of the password field back to String.
        String password = (String) value;

        // Test password for at least 8 characters minimum 1 letter and number
        Matcher m = p.matcher(password);
        boolean b = m.find();

        // Check if the password is actually entered.
        if (password != null) {
            if (!b) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password is invalid.", "Must be at least 8 characters, at least 1 letter and 1 number."));
            }
        } else {
            LOGGER.log(Level.SEVERE, "Password is null.");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Internal error.", "Please try again."));
        }

    }
}
