/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import objetosAgenda.*;

/**
 *
 * @author Hest
 */
public class extension_repaso_archivos_01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA

        //VARIBALES DE SALIDA
        //VARIABLES AUXILIARES
        Persona[] grupoPersonas = {new Persona(0001, "Alexa"), new Persona(0002, "Roberto"), new Persona(0003, "Carlos"), new Persona(0004, "Ana")};
        String[] grupoDescripciones = {"Contabilidad", "Dentista", "Mecánica", "Publicidad", "Médico", "Veterinario", "Ventas"};
        List<Cita> listaCitas;
        Map<String, List<Cita>> citasRestructuradas;
        Long idPersona;
        boolean idCorrecto = false;
        //VARIABLES CONSTANTES

        //DEFINICIÓN SCANNER
        Scanner datos = new Scanner(System.in);

        //----------------
        //ENTRADA DE DATOS
        //----------------
        do {
            System.out.println("Introduce el id de un cliente: ");
            System.out.println("-------------------------------");
            idPersona = datos.nextLong();
            for (int i = 0; i < grupoPersonas.length; i++) {
                idCorrecto = idPersona == grupoPersonas[i].getId();
                if (idCorrecto) {
                    i = grupoPersonas.length;
                }
            }
        } while (!idCorrecto);
        //---------
        //GENERO LAS 30 CITAS
        //---------
        listaCitas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Persona personaAleatoria = grupoPersonas[0 + (int) (Math.random() * (3 + 1))];
            LocalDate fechaCita = LocalDate.of(2020, 4, 1 + (int) (Math.random() * (30)));
            LocalTime horaCita = LocalTime.of(10 + (int) (Math.random() * (20 - 10 + 1)), (0 + (int) (Math.random() * (1))) * 30);
            listaCitas.add(new Cita(personaAleatoria, LocalDateTime.of(fechaCita, horaCita), grupoDescripciones[0 + (int) (Math.random() * (grupoDescripciones.length))]));
        }

        //---------------
        //SALIDA DE DATOS DE LAS CITAS
        //---------------
        System.out.println("Estas son las citas generadas: ");
        System.out.println("-------------------------------");
        for (int i = 0; i < listaCitas.size(); i++) {

            System.out.println("Cita número " + (i + 1) + ":");
            System.out.println(listaCitas.get(i));
        }

        //--------------
        //LISTA EN UN MAP
        //---------------
        citasRestructuradas = new HashMap<>();
        for (int i = 0; i < grupoDescripciones.length; i++) {
            citasRestructuradas.put(grupoDescripciones[i], new ArrayList<>());
            for (Cita c : listaCitas) {
                if (c.getDescripcion().equals(grupoDescripciones[i])) {
                    citasRestructuradas.get(grupoDescripciones[i]).add(c);
                }
            }
            citasRestructuradas.get(grupoDescripciones[i]).sort(new comparaCitasFecha());
        }
        listaCitas.clear();

        //--------------
        //GUARDAMOS INFO
        //--------------
            try (PrintWriter escribe = new PrintWriter(new FileWriter(String.format("D:\\archivosClaseProgramas\\repaso_archivos_des\\%04d.citas", idPersona)))){
                for (List<Cita> c : citasRestructuradas.values()) {
                    for (int j = 0; j < c.size(); j++) {
                        if(idPersona==c.get(j).getCliente().getId())
                        escribe.append(String.format("%04d::%s::%s:%s::%s\n", c.get(j).getCliente().getId(), ((Persona)(c.get(j).getCliente())).getNombre(),c.get(j).getFecha(),c.get(j).getHora(),c.get(j).getDescripcion()));

                    }
                }
            } catch (IOException F) {
                System.err.println("Error guardando el documento...");
            }
        

    }

}
