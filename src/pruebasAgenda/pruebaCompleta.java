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
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;
import objetosAgenda.*;

public class pruebaCompleta {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
       /* System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");

        try {
            System.out.println("Intentando crear agenda con citas por día de más:");
            Agenda a = new Agenda(null, Agenda.MAX_CITAS_DIA_CUALQUIER_AGENDA + 1, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("\t>" + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR, excepción no esperada:" + e.getMessage());
        }

        try {
            System.out.println("Intentando crear agenda con citas por día de menos:");
            Agenda a = new Agenda(null, Agenda.MIN_CITAS_DIA_CUALQUIER_AGENDA - 1, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("\t>" + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR, excepción no esperada:" + e.getMessage());
        }

        try {
            System.out.println("Intentando crear agenda con 10 citas por día:");
            Agenda a = new Agenda(null, 10, null, null);
            System.out.println("\t>OK");
        } catch (Exception e) {
            System.err.println("ERROR, excepción no esperada:" + e.getMessage());
        }

        System.out.println("--------");
        System.out.println("3º PARTE");
        System.out.println("--------\n");

        System.out.println("==========================");
        System.out.println("Salida esperada:");
        System.out.println("**********************************\n");
        System.out.println("Salida esperada:");
        System.out.println("\n\nSalida para tu implementación:");
        System.out.println("**********************************\n");
        Agenda a = Agenda.buildSampleAgenda();

        System.out.println(a.toString());

        System.out.println("--------");
        System.out.println("4º PARTE");
        System.out.println("--------\n");
        System.out.println("**********************************\n");
        Agenda b = Agenda.buildSampleAgenda();
        for (DayOfWeek m : DayOfWeek.values()) {
            System.out.println(String.format("¿Es %s hábil? \t%s", m, a.esDiaHabil(m) ? "Si" : "No"));
        }
        System.out.println("--------");
        System.out.println("5º PARTE");
        System.out.println("--------\n");
        String[] fechasAcomprobar = {"2019-09-02", "2019-09-04", "2019-10-31",
            "2019-11-01", "2019-11-02", "2019-11-03", "2019-11-04",
            "2019-11-05", "2019-12-04", "2019-12-05", "2019-12-06",
            "2019-12-07", "2019-12-09", "2019-12-10", "2019-12-20",
            "2019-12-21", "2019-12-28", "2020-01-07", "2020-01-08",
            "2020-01-15", "2020-04-03", "2020-04-04", "2020-04-09",
            "2020-04-12", "2020-04-13", "2020-04-25"};
        System.out.println("**********************************\n");

        Agenda c = Agenda.buildSampleAgenda();

        for (int i = 0; i < fechasAcomprobar.length; i++) {
            LocalDate fechaAComprobar = LocalDate.parse(fechasAcomprobar[i]);
            String v = a.noLaborable(fechaAComprobar).stream().collect(Collectors.joining(" y "));
            if (v.length() > 0) {
                System.out.println(fechaAComprobar + " cae en " + v + ".");
            } else {
                System.out.println(fechaAComprobar + " no cae en no laborable.");
            }
        }
        System.out.println("--------");
        System.out.println("6/7º PARTE");
        System.out.println("--------\n");
        System.out.println("**********************************\n");
        Cliente[] clientes = {new Persona(8888,"ALEXA"), new Persona( 8889,"ROBERT")};

        System.out.println("Intentando buscar la cita de un cliente a través del ID:");
        System.out.println("--------------------------------------------------------");
       
        System.out.println(a.buscarCitaCliente(clientes[0].getId()));
        System.out.println();

        System.out.println("Intentando buscar la cita de un cliente a través de la instancia de cliente:");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(a.buscarCitaCliente(clientes[1]));
        System.out.println();

        System.out.println("Intentando buscar la cita de un cliente sin cita usando instancia de cliente");
        System.out.println("----------------------------------------------------------------------------");

        System.out.println(a.borrarCitaCliente(new Persona( 9999,"TEST")));
        System.out.println();

        System.out.println("Intentando buscar la cita de un cliente sin cita usando ID de cliente");
        System.out.println("---------------------------------------------------------------------");
        System.out.println(a.borrarCitaCliente(938484));
        System.out.println();

        try {
            System.out.println("Intentando buscar la cita de un cliente \"null\": ");
            System.out.println("-------------------------------------------------");
            a.borrarCitaCliente(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Mensaje de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("¡¡ERROR!! No se ha generado la excepción adecuada.");
        }
        System.out.println("--------");
        System.out.println("8º PARTE");
        System.out.println("--------\n");
        System.out.println("**********************************\n");

        System.out.println("Obteniendo lista de citas del día desordenadas:");
        System.out.println("---------------------------------------------------");
        System.out.println(a.obtenerCitasFecha(LocalDate.parse("2019-02-19"),false)); 
        System.out.println();
        
        System.out.println("Obteniendo lista de citas del día ordenadas:");
        System.out.println("---------------------------------------------------");
        System.out.println(a.obtenerCitasFecha(LocalDate.parse("2019-02-19"),true));
        System.out.println();
    
    */
    //PROBAMOS AGREGAR AGENDA
    System.out.println("**********************************\n");
        Agenda a=Agenda.buildSampleAgenda();
        Cliente cliente=new Persona( 8810,"PATRICK");      
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        try
        {
            System.out.printf("Intentando añadir una cita 'null'\n");
            System.out.println("-----------------------------------------------");
            Cita c=null;
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalArgumentException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-14T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no hábil (%s %s)\n",
                    fecha.format(formatoFecha), fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-15T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no hábil (%s %s)\n",
                    fecha.format(formatoFecha),  fecha.getDayOfWeek().name());
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();

        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2019-11-04T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.format(formatoFecha));
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        }  catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
         
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2019-12-05T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.format(formatoFecha));
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2019-12-27T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.format(formatoFecha));
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-04-06T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.format(formatoFecha));
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-04-08T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día no laborable (%s)\n",fecha.format(formatoFecha));
            System.out.println("-----------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita (y no debería haberse agregado)");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-12T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita en día hábil y laborable (%s %s)\n",
                    fecha.format(formatoFecha),  fecha.getDayOfWeek().name());
            System.out.println("---------------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡CORRECTO!! Se agregó la cita.");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    + "IllegalAgendaStateException cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();
        
        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-12T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir una cita con un cliente ya citado\n");
            System.out.println("---------------------------------------------------");
            Cita c=new Cita(cliente,fecha,"Cortar cables.");
            a.agregarCita(c);
            System.out.println("¡¡ERROR!! Se agregó la cita, pero el cliente ya tenía cita.");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción:"+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
        
        System.out.println();

        try
        {
            LocalDateTime fecha=LocalDateTime.parse("2020-03-12T16:00:00");
            fecha.format(formatoFecha);
            System.out.printf("Intentando añadir más de 10 citas una cita en un día\n");
            System.out.println("----------------------------------------------------");
            for (int i=0;i<12;i++)
            {
                final int j=i+9999;
                Cita c=new Cita(()->(long)j,fecha.plusMinutes(i),"Cortar cables.");
                a.agregarCita(c);
            }
            System.out.println("¡¡ERROR!! Se agregaron más de 10 citas en un mísmo día en la agenda de ejemplo.");
        } catch (IllegalAgendaStateException ex) {
            System.out.println("Texto excepción: "+ex.getMessage());
            System.out.println("Motivo excepción: "+ex.getMotivo());
        } catch (Exception ex) {
            System.out.println("¡¡ERROR!! Ha saltado una excepción del tipo "
                    +  ex.getClass().getCanonicalName() + "cuando no debería.\n"+
                    "El mensaje es:"+ex.getMessage());
        }
}
}