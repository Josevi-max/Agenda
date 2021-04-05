/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objetosAgenda.*;

/**
 *
 * @author Hest
 */
public class leyendo_info_clientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA
        String carpeta;

        //VARIABLES AUXILIARES
        Map<Long, List<Cita>> infoCliente;
        //DEFINICIÓN SCANNER
        Scanner datos = new Scanner(System.in);
        //FOMARTOS HORA/FECHA
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/y HH:mm");
        //----------------
        //ENTRADA DE DATOS
        //----------------
        System.out.println("Introduce el nombre de la carpeta de la que quieras extraer la info de los clientes: ");
        carpeta = datos.nextLine();

        //---------
        //CALCULOS
        //---------
        //Definimos el mapa
        infoCliente = new HashMap<>();
        //Leemos el documento
        try (BufferedReader lectura = new BufferedReader(new FileReader(carpeta + "/clientes.info"))) {
            while (lectura.ready()) {
                String[] info = lectura.readLine().split("::");
                infoCliente.put(Long.parseLong(info[0]), new ArrayList<>());
                String ruta = String.format("%s/%04d.citas", carpeta, Long.parseLong(info[0]));
                try (BufferedReader lectura2 = new BufferedReader(new FileReader(ruta))) {
                    while (lectura2.ready()) {
                        String[] datosPersona = lectura2.readLine().split("::");
                        long id = Long.parseLong(info[0]);
                        infoCliente.get(id).add(new Cita(new Persona( id,info[1]), LocalDateTime.parse(datosPersona[0] + " " + datosPersona[1], formato), datosPersona[2]));
                    }
                } catch (IOException f) {
                    System.err.println("Error obteniendo leyendo los archivos de los clientes...");
                }
                Collections.sort(infoCliente.get(Long.parseLong(info[0])));
                //infoCliente.get(Long.parseLong(info[0])).sort((c1,c2)->c1.getFecha().compareTo(c2.getFecha()));
            }
        } catch (FileNotFoundException f) {
            System.err.println("Error no se encontro la carpeta...");
        } catch (IOException f) {
            System.err.println("Error leyendo el documento...");

        }

        //---------------
        //SALIDA DE DATOS
        //---------------
        System.out.println("--------------------------");
        System.out.println("INFORMACIÓN DE LAS CITAS: ");
        System.out.println("--------------------------");
        for (long id : infoCliente.keySet()) {
            System.out.println(String.format("Sacamos información del cliente con id: %04d", id));
            System.out.println("---------------------------------------------");
            for (int i = 0; i < infoCliente.get(id).size(); i++) {
                System.out.println("Cita número: " + (i + 1));
                System.out.println(infoCliente.get(id).get(i));
            }
        }
    }
}
