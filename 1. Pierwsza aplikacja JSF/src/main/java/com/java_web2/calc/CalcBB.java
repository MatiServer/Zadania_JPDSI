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
    private String kwota_kredytu;
    private String oprocentowanie;
    private String lata;
    private Double miesieczna_rata;

    @Inject
    FacesContext ctx;

    public String getKwota_kredytu() {
        return kwota_kredytu;
    }
    public void setKwota_kredytu(String kwota_kredytu) {
        this.kwota_kredytu = kwota_kredytu;
    }
    public String getOprocentowanie() {
        return oprocentowanie;
    }
    public void setOprocentowanie(String oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }
    public String getLata() {
        return lata;
    }
    public void setLata(String lata) {
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
            double kwo = Double.parseDouble(this.kwota_kredytu);
            double opr = Double.parseDouble(this.oprocentowanie) / 100 / 12;
            int l_mies = Integer.parseInt(this.lata) * 12;

            if (opr == 0) {
                miesieczna_rata = kwo / l_mies;
            } else {
                BigDecimal wynik = BigDecimal.valueOf(kwo * opr)
                    .divide(BigDecimal.valueOf(1 - Math.pow(1 + opr, -l_mies)), 10, RoundingMode.HALF_UP);
            miesieczna_rata = wynik.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obliczenie wykonane poprawnie", null));
            return true;
        } catch (Exception e) {
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
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
