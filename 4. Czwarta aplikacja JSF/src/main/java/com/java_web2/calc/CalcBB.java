package com.java_web2.calc;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.ResourceBundle;

@Named("calcBB")
//@RequestScoped
@SessionScoped
//@ViewScoped

public class CalcBB implements Serializable{
    private Double kwota_kredytu = null;
    private Double oprocentowanie = null;
    private Integer lata = null;
    private Double miesieczna_rata;
    private String locale = "pl";
    
    @PostConstruct
    public void init() {
        updateLocale();
    }

    public void setLocale(String language) {
        this.locale = language;
        updateLocale();
    }
    
    public String getLocale() {
        return locale;
    }

    public void updateLocale() {
    FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
    }

    public void resetForm(){
        kwota_kredytu = null;
        oprocentowanie = null;
        lata = null;
        miesieczna_rata = null;
    }
    
    @Inject
    FacesContext ctx;
    
    public Double getKwota_kredytu() {
        return kwota_kredytu;
    }
    public void setKwota_kredytu(Double kwota_kredytu) {
        this.kwota_kredytu = kwota_kredytu;
    }
    public Double getOprocentowanie() {
        return oprocentowanie;
    }
    public void setOprocentowanie(Double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }
    public Integer getLata() {
        return lata;
    }
    public void setLata(Integer lata) {
        this.lata = lata;
    }
    public Double getMiesieczna_rata() {
        return miesieczna_rata;
    }
    public void setMiesieczna_rata(Double miesieczna_rata) {
        this.miesieczna_rata = miesieczna_rata;
    }

    public boolean policzWynik() {
        try {
            double opr = oprocentowanie / 100 / 12;
            int l_mies = lata * 12;

            if (opr == 0) {
                miesieczna_rata = kwota_kredytu / l_mies;
            } else {
                BigDecimal wynik = BigDecimal.valueOf(kwota_kredytu * opr)
                        .divide(BigDecimal.valueOf(1 - Math.pow(1 + opr, -l_mies)), 10, RoundingMode.HALF_UP);
                miesieczna_rata = wynik.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }

            ResourceBundle bundle = ResourceBundle.getBundle("messages", ctx.getViewRoot().getLocale());
            String successMessage = bundle.getString("app.operation_ok");
        
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, successMessage, null));
            return true;
        } catch (Exception e) {
            
            ResourceBundle bundle = ResourceBundle.getBundle("messages", ctx.getViewRoot().getLocale());
            String errorMessage = bundle.getString("app.operation_not_ok");
        
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
            return false;
        }
    }

    public String calc() {
        updateLocale();
        if (policzWynik()) {
            return "showresult";
        }
        return null;
    }

    public String calc_AJAX() {
        updateLocale();
        if (policzWynik()) {
            ResourceBundle bundle = ResourceBundle.getBundle("messages", ctx.getViewRoot().getLocale());
            String rate_pl_en = bundle.getString("app.rate_pl_en");
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, rate_pl_en + miesieczna_rata, null));
        }
        return null;
    }

    public String info() {
        updateLocale();
        return "info";
    }
    
    public String goToIndex() {
    resetForm();
    updateLocale();
    return "index";
    }
}
