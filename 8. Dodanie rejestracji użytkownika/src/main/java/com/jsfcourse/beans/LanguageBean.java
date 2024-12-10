package com.jsfcourse.beans;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

@Named("LanguageBean")
@SessionScoped
public class LanguageBean implements Serializable {

    private String locale = "pl";
    
    @PostConstruct
    public void init() {
        updateLocale();
    }

    public void setLocale(String language) {
        this.locale = language;
        
        String scrollPosition = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("scrollPos");
         
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("locale", locale);
        String currentPage = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        String redirectUrl = currentPage + "?scrollPos=" + scrollPosition;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLocale() {
        return locale;
    }

    public void updateLocale() {
    FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
    }
}

