/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosAgenda;

import java.util.Arrays;

/**
 *
 * @author Hest
 */
public final class Persona implements Cliente,Comparable<Persona> {
    //---------
    //ATRIBUTOS
    //---------

    private final String nombre;
    private final long id;

    //-------------
    //CONSTRUCTORES
    //--------------
    //PRINCIPAL
    //*********
    public Persona(long id,String nombre) throws IllegalArgumentException {
        if (!nombre.isEmpty() & nombre != null) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Error, nombre tiene un valor nulo o esta vacio");
        }
        this.id = id;
    }

    //---------
    //METODOS
    //---------
    //GET
    //---
    
    //ID
    @Override
    public long getId() {
        return id;
    }
    //NOMBRE
    public String getNombre() {
        return nombre;
    }

    //---------
    //TO STRING
    //---------
    
    @Override
    public String toString(){
        return String.format("{ID=%d;NOMBRE=%s}",this.getId(),this.getNombre());
    }
    
    //BuildSamplePersonas
    
    public static Persona [] buildSamplePersonas(){
        Persona p1=new Persona(8889,"Robert");
        Persona p2=new Persona(8888,"Alexa");
        Persona p3=new Persona(8884,"Charlie");
        Persona p4=new Persona(8883,"Anne");
        Persona [] array={p1,p2,p3,p4};
        return array;
    }

    @Override
    public int compareTo(Persona t) {
        return t.getNombre().compareTo(this.nombre);
    }
}
