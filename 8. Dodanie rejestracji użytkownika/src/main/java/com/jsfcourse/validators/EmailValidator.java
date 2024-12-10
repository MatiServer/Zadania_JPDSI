package com.jsfcourse.validators;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.regex.Pattern;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String> {
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new ValidatorException(new FacesMessage("Invalid email format"));
        }
    }
}
