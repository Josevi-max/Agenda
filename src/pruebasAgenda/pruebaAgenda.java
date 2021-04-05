/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

/**
 *
 * @author Hest
 */
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import objetosAgenda.*;

public class pruebaAgenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Probando la Persona
        System.out.println("Probamos la clase Agenda\n");
        Persona p1 = new Persona( 7789,"Jose");
        Persona p2 = new Persona(6699,"Lucia");
        Persona p3 = new Persona(7925,"Juan");
        Persona p4 = new Persona(6867,"María");
        System.out.println(p1);

        System.out.println(Arrays.toString(Persona.buildSamplePersonas()));

        //Probando las citas
        System.out.println("Probamos la clase cita");
        Cita c1 = new Cita(p4, LocalDateTime.of(2019, Month.MARCH, 5, 15, 30), null);
        System.out.println("Datos cliente");

        System.out.println(c1.getCliente());
        System.out.println("Hora de la cita");
        System.out.println(c1.getHora());
        System.out.println("Fecha de la cita");
        System.out.println(c1.getFecha());
        System.out.println("Dia de la semana en el que cae la cita");
        System.out.println(c1.getDiaDeLaSemana());
        System.out.println("Probamos el metodo toString\n");
        System.out.println(c1);

        //Probando invertablo de fechas
        System.out.println("Probando intervalo de fechas");
        IntervaloFechas f1 = new IntervaloFechas(LocalDate.of(2019, Month.MARCH, 12), LocalDate.of(2019, Month.MARCH, 29));
        System.out.println("Obtengo la fecha de inicio");
        System.out.println(f1.getStart());
        System.out.println("Probando metodo of");
        System.out.println(IntervaloFechas.of(1920, 5, 13, 2020, 3, 13).toString());
        System.out.println("");
        System.out.println(f1.estaDentroDelIntervalo(LocalDate.of(2019,11,2)));

        //Probando agenda
        Set dias=new HashSet();
        dias.add(DayOfWeek.WEDNESDAY);
        Agenda a1 = new Agenda(null, 7, dias, null);
        System.out.println("Probando metodo buildSample y toString\n");
        System.out.println(Agenda.buildSampleAgenda().toString());
        
        //Pruebo el metodo dia no habil
        System.out.println("Probando el metodo no habil con el dia miercoles y domingo");
        System.out.println(Agenda.buildSampleAgenda().esDiaHabil(DayOfWeek.WEDNESDAY));
                System.out.println(Agenda.buildSampleAgenda().esDiaHabil(DayOfWeek.SATURDAY));

        
        //Pruebo el metodo noLaborable
        
        System.out.println(Agenda.buildSampleAgenda().noLaborable(LocalDate.of(2019,12,6)));
        
    }
}
