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

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * DuplicateFieldValidator extends the Validator class to determine if two fields are duplicates
 * @author Brian Leathem in StackOverflow
 * http://stackoverflow.com/questions/2909021/jsf-2-0-validate-equality-of-2-inputsecret-fields-confirm-password-without-wri
 */
@FacesValidator(value = "duplicateFieldValidator")
public class DuplicateFieldValidator implements Validator {

    /**
     * Validates the equality of two form fields in a form.
     * @param context the Faces context
     * @param component the UIComponent that is being validated
     * @param value the value of the UIComponent to be compared for equality
     * @throws ValidatorException exception thrown when the fields do not match in value
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        // Obtain the client ID of the first field from f:attribute.
        // This must be in the JSF page using <f:attribute ...></f:attribute>
        String field1Id = (String) component.getAttributes().get("field1");

        // Obtain the name of the field that's being compared, to customize error message
        String fieldNames = (String) component.getAttributes().get("fieldNames");

        // Find the actual JSF component for the client ID.
        UIInput textInput = (UIInput) context.getViewRoot().findComponent(field1Id);
        if (textInput == null) {
            throw new IllegalArgumentException(String.format("Unable to find component with id %s", field1Id));
        }
        // Get its value, the entered text of the first field.
        String field1 = (String) textInput.getValue();

        // Cast the value of the entered text of the second field back to String.
        String confirm = (String) value;

        // Check if the first text is actually entered and compare it with second text.
        if (field1 != null && field1.length() != 0 && !field1.equals(confirm)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, fieldNames + " do not match.", "Must match password entered above."));
        }
    }
}
