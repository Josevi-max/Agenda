/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosAgenda;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Hest
 */
public class IntervaloFechas implements Comparable<IntervaloFechas>{
    //---------
    //ATRIBUTOS
    //---------

    private LocalDate inicio, fin;

    //-------------
    //CONSTRUCTORES
    //--------------
    //PRINCIPAL
    //*********
    public IntervaloFechas(LocalDate fechaInicio, LocalDate fechaFin) throws IllegalArgumentException {
        if (fechaInicio == null | fechaFin == null) {
            throw new IllegalArgumentException("Error, no pueden haber valores nulos");
        }
        this.inicio = fechaInicio;
        this.fin = fechaFin;
    }

    //---------
    //METODOS
    //---------
    //GET
    //---
    public LocalDate getStart() {
        return inicio;
    }

    public LocalDate getEnd() {
        return fin;
    }

    //GENERADORES
    //-----------
    public static IntervaloFechas of(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        return new IntervaloFechas(LocalDate.of(startYear, startMonth, startDay), LocalDate.of(endYear, endMonth, endDay));
    }

    //ACCION
    //------
    public boolean estaDentroDelIntervalo(LocalDate fecha) {
        boolean dentro = false;
        if (fecha.isBefore(fin) & fecha.isAfter(inicio) || fecha.equals(fin) || fecha.equals(inicio)) {
            dentro = true;
        }
        return dentro;
    }
    //---------
    //TO STRING
    //---------

    @Override
    public String toString() {
        return String.format("%d/%d/%d - %d/%d/%d", inicio.getDayOfMonth(), inicio.getMonthValue(), inicio.getYear(), fin.getDayOfMonth(), fin.getMonthValue(), fin.getYear());
    }
    //-------
    //COMPARO
    //-------
    @Override
    public int compareTo(IntervaloFechas t) {
        return this.fin.compareTo(t.fin);
    }
}
