/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetosAgenda;

import java.util.Comparator;

/**
 *
 * @author Hest
 */

public class comparaPersonaCitas implements Comparator<Cita> {
 @Override
 public int compare(Cita c1,Cita c2){
        Persona p1=(Persona)c1.getCliente();
        Persona p2=(Persona)c2.getCliente();
        return p1.getNombre().compareTo(p2.getNombre());
       
                
                }  
    
}
