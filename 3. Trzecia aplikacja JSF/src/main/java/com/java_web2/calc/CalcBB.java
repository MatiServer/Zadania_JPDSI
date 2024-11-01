package com.java_web2.calc;

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

@Named("calcBB")
//@RequestScoped
@SessionScoped
//@ViewScoped
public class CalcBB implements Serializable{
    private Double kwota_kredytu = null;
    private Double oprocentowanie = null;
    private Integer lata = null;
    private Double miesieczna_rata;

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

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obliczenie wykonane poprawnie", null));
            return true;
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
            return false;
        }
    }

    public String calc() {
        if (policzWynik()) {
            return "showresult";
        }
        return null;
    }

    public String calc_AJAX() {
        if (policzWynik()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata: " + miesieczna_rata, null));
        }
        return null;
    }

    public String info() {
        return "info";
    }
}
