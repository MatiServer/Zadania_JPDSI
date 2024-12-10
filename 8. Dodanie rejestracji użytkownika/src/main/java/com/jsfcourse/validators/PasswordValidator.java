package com.jsfcourse.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String confirmPassword = (String) value;
        UIComponent passwordComponent = component.getParent().findComponent("password");
        String password = (String) ((jakarta.faces.component.EditableValueHolder) passwordComponent).getSubmittedValue();
        
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);
        
        if (password == null || !password.equals(confirmPassword)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
    }
}
