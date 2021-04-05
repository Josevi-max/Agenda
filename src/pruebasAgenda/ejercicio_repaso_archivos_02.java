/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasAgenda;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import objetosAgenda.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author Hest
 */
public class ejercicio_repaso_archivos_02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VARIABLES DE ENTRADA
        Long idCliente = null;
        Boolean idCorrecto = true;


        //VARIABLES AUXILIARES
        Map<String, List<Cita>> citasUsuario;
    
        //DEFINICIÓN SCANNER
        Scanner datos = new Scanner(System.in);

        //----------------
        //ENTRADA DE DATOS
        //----------------
        do {
            datos = new Scanner(System.in);
            idCorrecto = true;
            try {
                System.out.println("Introduce el id del cliente cuyas citas quieras conocer: ");
                System.out.println("---------------------------------------------------------");
                idCliente = datos.nextLong();
            } catch (InputMismatchException F) {
                System.err.println("Error, número no reconocido");
                idCorrecto = false;
            }
        } while (!idCorrecto);

        //---------
        //CALCULOS
        //---------
        citasUsuario = new HashMap<>();
        try (BufferedReader lectura = new BufferedReader(new FileReader(String.format("D:\\archivosClaseProgramas\\repaso_archivos_des\\%04d.citas", idCliente)))) {
            while (lectura.ready()) {
                String[] lineaTexto = lectura.readLine().split("::");
                if (!citasUsuario.containsKey(lineaTexto[3])) {
                    citasUsuario.put(lineaTexto[3], new ArrayList<>());
                }
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/y HH mm", Locale.getDefault());
                citasUsuario.get(lineaTexto[3]).add(new Cita(new Persona(Long.parseLong(lineaTexto[0]), lineaTexto[1]), LocalDateTime.parse(lineaTexto[2].replace(":", " "), formato), lineaTexto[3]));
            }
        } catch (IOException F) {
            System.err.println("Error, no se encontro el archivo especificado...");
        }
        //---------------
        //SALIDA DE DATOS
        //---------------
        for (String des : citasUsuario.keySet()) {
            citasUsuario.get(des).sort(new comparaCitasFecha());
            System.out.println("----------------\n" + des + "\n----------------\n");
            for (int i = 0; i < citasUsuario.get(des).size(); i++) {

                System.out.println(String.format("Cita número %d: \n %s", (i + 1), citasUsuario.get(des).get(i)));

            }
        }
    }
}
