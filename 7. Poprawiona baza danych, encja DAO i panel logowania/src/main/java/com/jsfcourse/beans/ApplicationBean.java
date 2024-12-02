package com.jsfcourse.beans;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Iterator;

@Named("applicationBean")
@ViewScoped
public class ApplicationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sweetMessage;
    
    @PostConstruct
    public void init() {
        sweetMessage = "Komunikat wywołany przyciskiem!";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", sweetMessage));
    }

    public String getSweetMessage() {
        return sweetMessage;
    }

    public void setSweetMessage(String sweetMessage) {
        this.sweetMessage = sweetMessage;
    }

    public boolean hasMessage() {
        Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance().getMessages();
        return iterator != null && iterator.hasNext();
    }

    public String getMessageSummary() {
        Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance().getMessages();
        return iterator.hasNext() ? iterator.next().getSummary() : "";
    }

    public String getMessageDetail() {
        Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance().getMessages();
        return iterator.hasNext() ? iterator.next().getDetail() : "";
    }
    
    public boolean getHasMessage() {
    Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance().getMessages();
    return (iterator != null) && (iterator.hasNext());
}
    
}
