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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import objetosAgenda.Cita;
import objetosAgenda.IntervaloFechas;
import objetosAgenda.Persona;
import objetosAgenda.comparaPersonaCitas;

/**
 *
 * @author Hest
 */
public class ejercicioExtension2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA

        //VARIBALES DE SALIDA
        //VARIABLES AUXILIARES
        Set<IntervaloFechas> conjuntoIntervalos = new TreeSet<>();
        IntervaloFechas[] listaIntervalo = new IntervaloFechas[5];
        Persona[] grupoPersonas = {new Persona(0001, "Alexa"), new Persona(0002, "Roberto"), new Persona(0003, "Carlos"), new Persona(0004, "Ana")};

        //Persona[] grupoPersonas = {new Persona("Alexa", 0001), new Persona("Roberto", 0002), new Persona("Carlos", 0003), new Persona("Ana", 0004)};
        String[] descripciones = {"Contabilidad", "Mecanica", "Publicidad", "Dentista", "Ventas", "Veterinario", "Medico"};
        DayOfWeek[] diaSemana = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        List<Cita> listaCitas; //Lista principal
        List<Cita> citasMismoTipo;
        Map<IntervaloFechas, List<Cita>> grupoCitas;
        Map<DayOfWeek, List<Cita>> grupoDiasCitas;
        //Defino enteros
        int dia, hora, min;
        //VARIABLES CONSTANTES
        final int MIN_CLI = 0, MAX_CLI = 3, MIN_MES = 1, MAX_MES = 30, MIN_HORA = 10, MAX_HORA = 20;
        //DEFINO FORMATO FECHAS
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/y", Locale.getDefault());
        //VARIABLES CONSTANTES
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
            listaCitas.add(new Cita(grupoPersonas[MIN_CLI + (int) (Math.random() * (MAX_CLI))], horaFecha, descripciones[0 + (int) (Math.random() * (descripciones.length))]));
        }

        //Meto como clave los intervalos y como valores las citas en grupo citas
        //-----------------------------------------------------------------------
        for (IntervaloFechas listaIntervalo1 : listaIntervalo) {

            citasMismoTipo = new ArrayList<>();
            for (Cita c : listaCitas) {
                if (listaIntervalo1.estaDentroDelIntervalo(LocalDate.parse(c.getFecha(), formato))) {
                    citasMismoTipo.add(c);
                }
                citasMismoTipo.sort((c1, c2) -> c1.getFecha().compareTo(c2.getFecha()));
            }
            grupoCitas.put(listaIntervalo1, citasMismoTipo);

        }

        //Relleno grupo dias citas
        //-------------------------
        List<Cita> dias;
        for (int i = 0; i < diaSemana.length; i++) {
            dias = new ArrayList<>();

            for (Cita c : listaCitas) {
                if (c.getDiaDeLaSemana() == diaSemana[i]) {
                    dias.add(c);
                }
            }
            dias.sort(new comparaPersonaCitas());
            grupoDiasCitas.put(diaSemana[i], dias);
            dias = null;
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
            System.out.println("Cita número: " + i + "\n--------------------------------");
            System.out.println(listaCitas.get(i));
        }

        //Recorremos y mostramos grupoCitas
        System.out.println("----------------------------------------");
        for (int i = 0; i < listaIntervalo.length; i++) {
            System.out.println("         " + listaIntervalo[i]);
            System.out.println("-----------------------------------");
            System.out.println(grupoCitas.get(listaIntervalo[i]));

        }

        //Recorremos y mostramos el map grupo dias citas
        System.out.println("----------------------------------------");

        for (int i = diaSemana.length; i > 0; i--) {
            System.out.println("Dia: " + diaSemana[i - 1]);
            System.out.println("----------------------------------------");

            System.out.println(grupoDiasCitas.get(diaSemana[i - 1]));
        }

    }
}
