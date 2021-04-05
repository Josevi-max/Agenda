/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import objetosAgenda.Cita;
import objetosAgenda.IntervaloFechas;
import objetosAgenda.Persona;

/**
 *
 * @author Hest
 */
public class extension3_sin_archivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA
        int hora2 = 0,min2=0;
        
        //VARIBALES DE SALIDA
        //VARIABLES AUXILIARES
        Persona[] grupoPersonas = {new Persona(0001, "Alexa"), new Persona(0002, "Roberto"), new Persona(0003, "Carlos"), new Persona(0004, "Ana")};
        String[] descripciones = {"Contabilidad", "Mecanica", "Publicidad", "Dentista", "Ventas", "Veterinario", "Medico"};
        Set<IntervaloFechas> conjuntoIntervalos = new TreeSet<>();
        IntervaloFechas[] listaIntervalo = new IntervaloFechas[5];
        DayOfWeek[] diaSemana = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        List<Cita> listaCitas; //Lista principal
        List<Cita> citasMismoTipo;
        Map<IntervaloFechas, Map<Long, List<Cita>>> grupoCitas;
        Map<DayOfWeek, List<Cita>> grupoDiasCitas;

        //DEFINICIÓN SCANNER
        Scanner datos ;

        //Defino enteros
        int dia, hora, min;
        //VARIABLES CONSTANTES
        final int MIN_CLI = 0, MAX_CLI = 3, MIN_MES = 1, MAX_MES = 30, MIN_HORA = 10, MAX_HORA = 20;
        //DEFINO FORMATO FECHAS
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/y", Locale.getDefault());

        //-----------------
        //ENTRADA DE DATOS
        //-----------------
       /* do {
         datos = new Scanner(System.in);
            try {
            
                System.out.println("Introduce la hora: ");
                hora2 = datos.nextInt();
            } catch (InputMismatchException f) {
                System.out.println("Error");
            }
        } while (hora2 > 20 | hora2 < 10);
        
        do{
            try{
            System.out.println("Introduce los minutos: ");
            }catch(InputMismatchException f){
                
            }
        }while();*/

        //---------
        //CALCULOS
        //---------
        listaCitas = new ArrayList<>();
        grupoCitas = new HashMap<>();
        grupoDiasCitas = new HashMap<>();
        //GENERA UN SET DE 5 INTERVALOS DE FECHAS Y LO GUARDO EN INTERVALO DE FECHAS
        //--------------------------------------------------------------------------
        for (int i = 0; i < listaIntervalo.length; i++) {
            listaIntervalo[i] = new IntervaloFechas(LocalDate.of(2020, 3, 30).plusDays(i * 7), LocalDate.of(2020, 4, 5).plusDays(i * 7));
            conjuntoIntervalos.add(listaIntervalo[i]);
        }

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
            listaCitas.add(new Cita(grupoPersonas[MIN_CLI + (int) (Math.random() * (MAX_CLI + 1))], horaFecha, descripciones[0 + (int) (Math.random() * (descripciones.length))]));
        }

        //MAPA DENTRO DE UN MAPA (ME QUEDE SIN IDEAS PARA UN BUEN NOMBRE DESCRIPTIVO)
        //---------------------------------------------------------------------------
        grupoCitas = new HashMap<>();
        Iterator<IntervaloFechas> recorreIntervalo = conjuntoIntervalos.iterator();
        while (recorreIntervalo.hasNext()) {
            IntervaloFechas in = recorreIntervalo.next();
            grupoCitas.put(in, new HashMap<>());
            for (int i = 0; i < grupoPersonas.length; i++) {
                grupoCitas.get(in).put(grupoPersonas[i].getId(), new ArrayList<Cita>());
                for (Cita c : listaCitas) {
                    if (grupoPersonas[i].getId() == c.getCliente().getId() & in.estaDentroDelIntervalo(LocalDate.parse(c.getFecha(), formato))) {
                        grupoCitas.get(in).get(c.getCliente().getId()).add(c);
                    }
                }
            }
        }
      
        //---------------
        //SALIDA DE DATOS
        //---------------
        Iterator<IntervaloFechas> recorreSet = conjuntoIntervalos.iterator();

        while (recorreSet.hasNext()) {
            IntervaloFechas intervalo = recorreSet.next();
            System.out.println(intervalo);
        }
        //Recorremos y mostramos la lista de citas original
        for (int i = 0; i < listaCitas.size(); i++) {
            System.out.println("Cita número: " + (i + 1) + "\n--------------------------------");
            System.out.println(listaCitas.get(i));
        }
        //OBTENEMOS INFORMACIÓN DE CADA CITA POR INTERVALO Y PERSONA (ID)
        System.out.println("*************************************");
        Iterator<IntervaloFechas> recorreIn = conjuntoIntervalos.iterator();
        while (recorreIn.hasNext()) {
            IntervaloFechas inte = recorreIn.next();
            System.out.println("Las citas dentro del intervalo: " + inte);
            System.out.println("///////////////////////////////////////");
            for (int i = 0; i < grupoCitas.get(inte).size(); i++) {
                System.out.println("La persona con id: " + grupoPersonas[i]);
                System.out.println("---------------------------------------");
                if(grupoCitas.get(inte).get(grupoPersonas[i].getId()).isEmpty()){
                    System.out.println("No tiene citas...\n--------------------------------");
                }
                for (int j = 0; j < grupoCitas.get(inte).get(grupoPersonas[i].getId()).size(); j++) {
                    
                    System.out.println("Cita número: " + (j + 1));
                    System.out.println("-------------------");
                    System.out.println(grupoCitas.get(inte).get(grupoPersonas[i].getId()).get(j));
                  
                }
            }
        }
    }
}
