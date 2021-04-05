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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import objetosAgenda.*;

public class ejercicioExtension {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA

        //VARIBALES DE SALIDA
        //VARIABLES AUXILIARES
        //Defino arrays
        Persona[] grupoPersonas = {new Persona( 0001,"Alexa"), new Persona(0002,"Roberto"), new Persona( 0003,"Carlos"), new Persona(0004,"Ana")};
        String[] descripciones = {"Contabilidad", "Mecanica", "Publicidad", "Dentista", "Ventas", "Veterinario", "Medico"};
        //Defino enteros
        int dia, hora, min;

        //Defino Maps
        Map<String, List<Cita>> grupoCitas;
        Map<LocalDate, List<Cita>> grupoCitasFecha;
        //Defino lists
        List<Cita> citaMismaFecha;
        List<Cita> citasMismoTipo;
        List<Cita> listaCitas; //Lista principal

        //VARIABLES CONSTANTES
        final int MIN_CLI = 0, MAX_CLI = 3, MIN_MES = 1, MAX_MES = 30, MIN_HORA = 10, MAX_HORA = 20;
        //DEFINO FORMATO FECHAS
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d-M-y", Locale.getDefault());

        //---------
        //CALCULOS
        //---------
        //Aquí le asigno el tipo a las listas
        listaCitas = new ArrayList<>();
        citaMismaFecha = new ArrayList<>();

        //Aquí le asigno el tipo a los Map
        grupoCitasFecha = new HashMap<>();
        grupoCitas = new HashMap<>();

        //Calculo las 30 citas aleatorias
        //--------------------------------
        for (int i = 0; i < 30; i++) {
            dia = MIN_MES + (int) (Math.random() * (MAX_MES));
            hora = MIN_HORA + (int) (Math.random() * (MAX_HORA - MIN_HORA + 1));
            if (1 + (int) (Math.random() * (2)) == 1) {
                min = 30;
            } else {
                min = 00;
            }
            LocalDateTime horaFecha = LocalDateTime.of(2020, Month.APRIL, dia, hora, min);
            listaCitas.add(new Cita(grupoPersonas[MIN_CLI + (int) (Math.random() * (MAX_CLI+1))], horaFecha, descripciones[0 + (int) (Math.random() * (descripciones.length))]));
        }
        //Meto el contenido de la lista principal en citas mismo tipo
        //-------------------------------------------------------------
        for (int i = 0; i < descripciones.length; i++) {
            citasMismoTipo = new ArrayList<>();

            /*      Iterator<Cita> itera = copiaListaCitas.iterator();
            while (itera.hasNext()) {
                Cita cita = itera.next();
                if (cita.getDescripcion().equals(descripciones[i])) {
                    citasMismoTipo.add(cita);
                  //  itera.remove();

                }
            }*/
            for (Cita c : listaCitas) {
                if (c.getDescripcion().equals(descripciones[i])) {

                    citasMismoTipo.add(c);
                }
                citasMismoTipo.sort((c1, c2) -> c1.getHora().compareTo(c2.getHora()));
                citasMismoTipo.sort(new comparaPersonaCitas());

            }

            grupoCitas.put(descripciones[i], citasMismoTipo);
            // citasMismoTipo.clear(); 
            citasMismoTipo = null;
        }
        //Meto el contenido de la lista principal en citas mismo fecha
        //-------------------------------------------------------------
    
        for (int i = 1; i < 30; i++) { //Cambiar teniendo en cuenta que en una lista no pueden haber dos datos iguales
            citaMismaFecha = new ArrayList<>();
            LocalDate fechaLista = LocalDate.of(2020, 4, i);
            for (Cita c : listaCitas) {
                LocalDate fecha2 = LocalDate.parse(c.getFecha().replace("/", "-"), formato);
                if (fecha2.equals(fechaLista)) {
                    citaMismaFecha.add(c);
                }
            }
            if (citaMismaFecha.size() > 0) {
                citaMismaFecha.sort((c1,c2)-> c1.getDescripcion().compareTo(c2.getDescripcion()));
                grupoCitasFecha.put(fechaLista, citaMismaFecha);
            }
            citaMismaFecha = null;
        }

        //---------------
        //SALIDA DE DATOS
        //---------------
        
        //Recorremos y mostramos la lista de citas original
        for (int i = 0; i < listaCitas.size(); i++) {
            System.out.println("Cita número: " + i + "\n--------------------------------");
            System.out.println(listaCitas.get(i));
        }
        //Recorremos 
        System.out.println("----------------------------------------");
        for (int i = 0; i < descripciones.length; i++) {
            System.out.println("         " + descripciones[i]);
            System.out.println("-----------------------------------");
            System.out.println(grupoCitas.get(descripciones[i]));

        }
        System.out.println("Lo guardamos por fecha\n--------------------------------");
        for (LocalDate f : grupoCitasFecha.keySet()) {
            System.out.println("Citas que pertenecen a la fecha: " + f + " \n---------------------");
            System.out.println(grupoCitasFecha.get(f));
        }

    }
}
