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
 * ZipCodeValidator extends the Validator class
 *
 * @author Xavier Martinez
 * @author Brian Leathem (Provided example)
 * http://stackoverflow.com/questions/2909021/jsf-2-0-validate-equality-of-2-inputsecret-fields-confirm-password-without-wri
 */
@FacesValidator(value = "zipCodeValidator")
public class ZipCodeValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger("ZipCodeValidator");

    /**
     * Validates a zip code
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

        // Cast the value of the entered text of the zipCode field back to String.
        Integer zipCode = (Integer) value;
        String zipCodeString = "";
        try {
            zipCodeString = zipCode.toString();
        } catch(NullPointerException np) {
            LOGGER.log(Level.SEVERE, np.toString());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Internal error.", "Please try again."));
        }

        if (zipCode < 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zip code cannot be negative.", "Must be a positive 5 digit number."));
        } else if (zipCodeString.length() < 5) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zip code is too small.", "Must be a positive 5 digit number."));
        } else if (zipCodeString.length() > 5) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zip code is too large.", "Must be a positive 5 digit number."));
        }

    }
}
