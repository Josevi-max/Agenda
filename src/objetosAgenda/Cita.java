/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosAgenda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Hest
 */
public class Cita implements Comparable<Cita> {
    //---------
    //ATRIBUTOS
    //---------

    private Cliente cliente;
    private LocalDateTime fechaHora;
    private String descripcion;

    //-------------
    //CONSTRUCTORES
    //--------------
    //PRINCIPAL
    //*********
    public Cita(Cliente cliente, LocalDateTime fechaHora, String descripcion) throws IllegalArgumentException {
        if (cliente == null | fechaHora == null) {
            throw new IllegalArgumentException("Error, ni el cliente ni la fecha/hora pueden ser nulos");
        }
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
    }

    //---------
    //METODOS
    //---------
    //GET
    //---
    //Cliente
    public Cliente getCliente() {
        return cliente;
    }

    //Hora
    public String getHora() {
        return String.format("%d:%02d", this.fechaHora.getHour(), this.fechaHora.getMinute());
    }

    //Fecha
    public String getFecha() {
        return String.format("%d/%d/%d", this.fechaHora.getDayOfMonth(), this.fechaHora.getMonthValue(), this.fechaHora.getYear());
    }

    //Dia semana
    public DayOfWeek getDiaDeLaSemana() {
        return this.fechaHora.getDayOfWeek();
    }
    //Descripcion
    
    public String getDescripcion(){
        return this.descripcion;
    }

    //---------
    //TO STRING
    //---------
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Cliente .............: ").append(this.getCliente());
        resultado.append("\nDía de la cita ......: ").append(this.getFecha());
        resultado.append("\nHora de la cita .....: ").append(this.getHora());
        resultado.append("\nDescripción .........: ").append(this.descripcion).append("\n");
        return resultado.toString();
    }
    //----------------
    //COMPARANDO CITAS
    //----------------
    @Override
    public int compareTo(Cita cita) {
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("d-M-y",Locale.getDefault());
        LocalDate fecha1 = LocalDate.parse(this.getFecha().replace("/", "-"),formato);
        LocalDate fecha2 = LocalDate.parse(cita.getFecha().replace("/","-"),formato);
        if(fecha1.equals(fecha2)){   
            LocalTime hora1=LocalTime.parse(this.getHora());
            LocalTime hora2=LocalTime.parse(cita.getHora());
            return hora1.compareTo(hora2);
        }else{
        return fecha1.compareTo(fecha2);
        }
    }

    }
