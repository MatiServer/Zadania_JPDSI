package com.jsfcourse.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    private String sweetMessage; // Pole na komunikat
     
    public String login() 
    {
        if ("admin".equals(username) && "admin".equals(password)) 
        {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("loginMessage", "Zalogowano pomyślnie!");
        return "index.xhtml?faces-redirect=true";
        } else 
        {
            PrimeFaces.current().executeScript("Swal.fire('Error', 'Invalid username or password', 'error');");
            return null;
        }
    }
    
    public void someAction() {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja zakończona pomyślnie", "Witaj w systemie!"));
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
