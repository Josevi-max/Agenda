/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import objetosAgenda.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;

/**
 *
 * @author Hest
 */
public class extension3_mas_extension {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA
        //--------------------
        String ruta;
        //VARIBALES DE SALIDA

        //VARIABLES AUXILIARES
        //-------------------
        //objetos persona
        Persona[] grupoPersonas = {new Persona(0001, "Alexa"), new Persona(0002, "Roberto"), new Persona(0003, "Carlos"), new Persona(0004, "Ana")};

        //Persona[] grupoPersonas = {new Persona("Alexa", 0001), new Persona("Roberto", 0002), new Persona("Carlos", 0003), new Persona("Ana", 0004)};
        //descripciones
        String[] descripciones = {"Contabilidad", "Mecanica", "Publicidad", "Dentista", "Ventas", "Veterinario", "Medico"};

        //Lista
        List<Cita> listaCitas;
        //primitivos
        int dia, hora, min;

        //Mapas
        Map<String, List<Cita>> mapaDescripciones;

        //VARIABLES CONSTANTES
        //---------------------
        final int MIN_CLI = 0, MAX_CLI = 3, MIN_MES = 1, MAX_MES = 30, MIN_HORA = 10, MAX_HORA = 20;

        //DEFINICIÓN SCANNER
        //---------------------
        Scanner datos = new Scanner(System.in);

        //----------------
        //ENTRADA DE DATOS
        //----------------
        System.out.println("Introduce una carpeta para generar un archivo de información de clientes de nombre clientes.info: ");
        ruta = datos.nextLine();

        //---------
        //CALCULOS
        //---------
        //Defino lista citas
        listaCitas = new ArrayList<>();

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

        //Defino el mapa
        mapaDescripciones = new HashMap<>();

        //Relleno mapa descripciones
        //---------------------------
        for (int i = 0; i < descripciones.length; i++) {
            mapaDescripciones.put(descripciones[i], new ArrayList<>());
            for (Cita c : listaCitas) {
                if (c.getDescripcion().equals(descripciones[i])) {
                    mapaDescripciones.get(descripciones[i]).add(c);
                }
            }
            Collections.sort( mapaDescripciones.get(descripciones[i]));
        }

        //Guardamos un archivo de texto de los clientes
        //----------------------------------------------
        try (PrintWriter escribe = new PrintWriter(new FileWriter(ruta + "/clientes.info"))) {
            for (int i = 0; i < grupoPersonas.length; i++) {
                escribe.printf("%04d::%s\n", grupoPersonas[i].getId(), grupoPersonas[i].getNombre());
            }
        } catch (FileNotFoundException f) {
            System.err.println("No se encontro la ruta especificada ");
        } catch (IOException f) {
            System.err.println("Error guardando el archivo...");
        }
        //Guardamos un archivo de texto con info de cada cliente
        //-------------------------------------------------------
        for (int i = 0; i < grupoPersonas.length; i++) {
            String otraRuta = String.format("%s/%04d.citas", ruta, grupoPersonas[i].getId());
            try (PrintWriter escribe = new PrintWriter(new FileWriter(otraRuta))) {
                for (Cita c : listaCitas) {
                    if (c.getCliente().getId() == grupoPersonas[i].getId()) {
                        escribe.printf("%s::%s::%s\n", c.getFecha(), c.getHora(), c.getDescripcion());
                    }
                }
            } catch (FileNotFoundException f) {
                System.err.println("No se encontro la ruta especificada ");

            } catch (IOException f) {
                System.err.println("Error guardando el archivo...");

            }
        }
        //---------------
        //SALIDA DE DATOS
        //---------------
        System.out.println("Obtengo mapa con descripciones: ");
        System.out.println("--------------------------------");
        for (int i = 0; i < descripciones.length; i++) {
            System.out.println("Obtenemos información de las citas de: " + descripciones[i]);
            System.out.println("--------------------------------------------------------");
            for (int j = 0; j < mapaDescripciones.get(descripciones[i]).size(); j++) {
                System.out.println("Cita numero: " + (j + 1) + ": " + mapaDescripciones.get(descripciones[i]).get(j));
            }

            System.out.println("--------------------------------------------------------");

        }
    }
}
