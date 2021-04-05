/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import objetosAgenda.Cita;

/**
 *
 * @author Hest
 */
public class comparaCitasFecha implements Comparator<Cita> {
    
    @Override
    public int compare(Cita t, Cita t1) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/y", Locale.getDefault());
        
        return LocalDate.parse(t.getFecha(),formato).compareTo(LocalDate.parse(t1.getFecha(),formato));
    }
    
}
