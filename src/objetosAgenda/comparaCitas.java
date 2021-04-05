/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosAgenda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author Hest
 */
public class comparaCitas implements Comparator<Cita> {

    @Override
    public int compare(Cita c1, Cita c2) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-y", Locale.getDefault());
        LocalDate fecha1 = LocalDate.parse(c1.getFecha().replace("/", "-"), formato);
        LocalDate fecha2 = LocalDate.parse(c2.getFecha().replace("/", "-"), formato);
        if (fecha1.equals(fecha2)) {
            LocalTime hora1 = LocalTime.parse(c1.getHora());
            LocalTime hora2 = LocalTime.parse(c2.getHora());
            return hora1.compareTo(hora2);
        } else {
            return fecha1.compareTo(fecha2);
        }
    }

    public int compareHoras(Cita c1, Cita c2) {
        LocalTime t1 = LocalTime.parse(c1.getHora());
        LocalTime t2 = LocalTime.parse(c2.getHora());

        return t1.compareTo(t2);
    }

    public int compareDescripciones(Cita c1, Cita c2) {
        String d1=c1.getDescripcion(),d2=c2.getDescripcion();
        return d1.compareTo(d2);
    }
    public int compareFechas(Cita c1,Cita c2){
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-y", Locale.getDefault());
        LocalDate fecha1 = LocalDate.parse(c1.getFecha().replace("/", "-"), formato);
        LocalDate fecha2 = LocalDate.parse(c2.getFecha().replace("/", "-"), formato); 
                    return fecha1.compareTo(fecha2);

    }
   
}
