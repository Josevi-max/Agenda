/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosAgenda;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 *
 * @author Hest
 */
public class Agenda {
    //---------
    //ATRIBUTOS
    //---------

    private int nMaximoCitasDiarias;
    private final String NOMBREAGENDA;
    private List<Cita> lCitas;
    private final Set DNOLABORABLES;
    private final Map<String, IntervaloFechas> PNOLABORABLES;
    public final static int MAX_CITAS_DIA_CUALQUIER_AGENDA = 40;
    public final static int MIN_CITAS_DIA_CUALQUIER_AGENDA = 5;

    //-------------
    //CONSTRUCTORES
    //--------------
    //PRINCIPAL
    //*********
    public Agenda(String nombreAgenda, int numeroCitasDiasMaximo, Set<DayOfWeek> dNoHabiles, Map<String, IntervaloFechas> pNoLaborables) throws IllegalArgumentException {
        lCitas = new ArrayList<>();
        //Comprobaciones en el nombre de la agenda
        if (nombreAgenda == null || nombreAgenda.isEmpty()) {
            this.NOMBREAGENDA = "DEFAULT";
        } else {
            this.NOMBREAGENDA = nombreAgenda;
        }
        //Comprobaciones en el numero de citas maximas por dia
        if (numeroCitasDiasMaximo < this.MAX_CITAS_DIA_CUALQUIER_AGENDA & numeroCitasDiasMaximo > this.MIN_CITAS_DIA_CUALQUIER_AGENDA) {
            this.nMaximoCitasDiarias = numeroCitasDiasMaximo;
        } else {
            throw new IllegalArgumentException("Error, El número de días de la agenda no cumple los limites establecidos");
        }
        //Comprobaciones en el conjunto de dias no habiles
        if (dNoHabiles != null) {
            this.DNOLABORABLES = dNoHabiles;
        } else {
            this.DNOLABORABLES = new HashSet();
            this.DNOLABORABLES.add(" ");
        }
        //Comprobamos el mapa de fechas no laborables

        if (pNoLaborables != null) {
            this.PNOLABORABLES = pNoLaborables;
        } else {
            this.PNOLABORABLES = new HashMap();
            this.PNOLABORABLES.put(" ", null);

        }
    }

    //---------
    //METODOS
    //---------
    public static Agenda buildSampleAgenda() {

        Set noLaborable = new HashSet();
        noLaborable.add(DayOfWeek.SUNDAY);
        noLaborable.add(DayOfWeek.SATURDAY);
        Map<String, IntervaloFechas> fechasNoLaborables = new TreeMap();
        fechasNoLaborables.put("SEMANASANTA", new IntervaloFechas(LocalDate.of(2020, 4, 4), LocalDate.of(2020, 4, 12)));
        fechasNoLaborables.put("PUENTEDIC", new IntervaloFechas(LocalDate.of(2019, 12, 5), LocalDate.of(2019, 12, 9)));
        fechasNoLaborables.put("MERECIDASVACACIONES", new IntervaloFechas(LocalDate.of(2019, 11, 3), LocalDate.of(2019, 12, 7)));
        fechasNoLaborables.put("NAVIDADES", new IntervaloFechas(LocalDate.of(2019, 12, 21), LocalDate.of(2020, 1, 7)));
        fechasNoLaborables.put("PUENTENOV", new IntervaloFechas(LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 4)));
        Cliente[] clientes = Persona.buildSamplePersonas();
        Cita c1 = new Cita(clientes[0], LocalDateTime.parse("2019-02-19T20:00:00"), "Arreglar frigorífico.");
        Cita c2 = new Cita(clientes[1], LocalDateTime.parse("2019-02-19T18:00:00"), "Arreglar caldera.");
        Cita c3 = new Cita(clientes[2], LocalDateTime.parse("2019-02-19T17:00:00"), "Arreglar lavadora.");
        Cita c4 = new Cita(clientes[3], LocalDateTime.parse("2019-02-19T21:00:00"), "Arreglar secador.");

        Agenda agenda = new Agenda("SAMPLE", 10, noLaborable, fechasNoLaborables);
        agenda.lCitas.add(c1);
        agenda.lCitas.add(c2);
        agenda.lCitas.add(c3);
        agenda.lCitas.add(c4);
        return agenda;
    }

    //dia habil
    public boolean esDiaHabil(DayOfWeek dia) {
        boolean habil = true;
        if (this.DNOLABORABLES.contains(dia)) {
            habil = false;
        }
        return habil;
    }
    //metodo noLaborable 

    public Set<String> noLaborable(LocalDate fecha) {
        Set nombreNoPuente = new HashSet();
        for (String nombre : this.PNOLABORABLES.keySet()) {
            if (this.PNOLABORABLES.get(nombre).estaDentroDelIntervalo(fecha)) {
                nombreNoPuente.add(nombre);
            }
        }
        return nombreNoPuente;
    }

    //Metodo buscar cita
    public Cita buscarCitaCliente(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Error, el cliente no puede ser nulo");
        }

        Cita resultado = null;
        for (Cita c : lCitas) {
            if (c.getCliente().getId() == id) {
                resultado = c;
            }
        }
        return resultado;
    }

    public Cita buscarCitaCliente(Cliente cliente) throws IllegalArgumentException {
        if (cliente == null) {
            throw new IllegalArgumentException("Error, el cliente no puede estar vacio");
        }
        return this.buscarCitaCliente(cliente.getId());
    }

    //Metodo borrar citas
    public Cita borrarCitaCliente(long id) throws IllegalArgumentException {
        Cita cita;
        Long prueba = id;
        if (prueba == null) {
            throw new IllegalArgumentException("Error, el id del cliente no puede ser nulo...");
        }
        cita = this.buscarCitaCliente(id);
        this.lCitas.remove(cita);
        return cita;
    }

    public Cita borrarCitaCliente(Cliente cliente) throws IllegalArgumentException {
        if (cliente == null) {
            throw new IllegalArgumentException("Error, El cliente no puede ser nulo...");
        }
        return this.borrarCitaCliente(cliente.getId());
    }

    //Metodo obtener cita fecha
    public List<Cita> obtenerCitasFecha(LocalDate fecha, boolean orden) throws ParseException {
        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : lCitas) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-y", Locale.getDefault());

            //   String esp=new SimpleDateFormat("yyyy-MM-dd").format(cita.getFecha());
            // SimpleDateFormat formatoEng=new SimpleDateFormat("yyyy-MM-dd");
            //SimpleDateFormat formatoEsp=new SimpleDateFormat("dd-MM-yyyy");
            //String fechaBienFormada=formatoEsp.format(cita.getFecha());
            // DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");//Revisar si esta cambiando bien el formato
            //Date citaFecha =formatoEsp.parse(cita.getFecha());
            //  String prueba=formatoEng.format(citaFecha);
            // LocalDate fechaCita = LocalDate.parse(cita.getFecha().replace("/", "-"),dtf);
            if (LocalDate.parse(cita.getFecha().replace("/", "-"), dtf).equals(fecha)) {
                resultado.add(cita);
            }
        }
        if (orden) {
            // Collections.sort(resultado, new comparaCitas());
            Collections.sort(resultado);
            return resultado;
        } else {
            return resultado;
        }
    }

    //AGREGAR CITAS AGENDA
    //---------------------
    public void agregarCita(Cita cita) throws IllegalAgendaStateException, IllegalArgumentException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-y", Locale.getDefault());
        //COMPRUEBO SI LA CITA ES NULA O NO
        if (cita == null) {
            throw new IllegalArgumentException("Error la cita es nula");

        }
        LocalDate fechaCita = LocalDate.parse(cita.getFecha().replace("/", "-"), dtf);
        //COMPRUEBO SI CAE EN UN PERIOSO VACACIONAL COMO NAVIDADES ETC...
        if (!this.noLaborable(fechaCita).isEmpty()) {
            Set<String> diasF = this.noLaborable(fechaCita);
            throw new IllegalAgendaStateException("La agenda no se puede crear por que coincide con " + this.noLaborable(fechaCita), IllegalAgendaStateException.Motivo.DIA_NO_DISPONIBLE);
        }
        //COMPRUEBO SI EL DIA DE LA SEMANA ES LABORABLE 
        if (this.DNOLABORABLES.contains(cita.getDiaDeLaSemana())) {
            throw new IllegalAgendaStateException("La cita no se puede crear porque coincide con dias no laborables: " + cita.getDiaDeLaSemana(), IllegalAgendaStateException.Motivo.DIA_NO_DISPONIBLE);
       //COMPRUEBO QUE NO EXISTA YA LA CITA
        } else if (lCitas.contains(this.buscarCitaCliente(cita.getCliente()))) {
            throw new IllegalAgendaStateException("La cita introducida ya exite", IllegalAgendaStateException.Motivo.YA_EXISTE_CITA);
        }

        //COMPRUEBO SI SUPERA O NO EL MAXIMO DE CITAS DEL DIA
        try {
            if (this.obtenerCitasFecha(fechaCita, true).size() > this.nMaximoCitasDiarias) {
                throw new IllegalAgendaStateException("Se ha superado el número de citas maximas para ese día", IllegalAgendaStateException.Motivo.CITAS_MAXIMAS_SUPERADAS);
            }
        } catch (ParseException f) {
            System.err.println("Error con la cita");
        }
      //SI TODO SALE BIEN

        this.lCitas.add(cita);

    }

    //---------
    //TO STRING
    //---------
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("AGENDA: ").append(this.NOMBREAGENDA);
        resultado.append("\nCitas máximas por día: ").append(this.nMaximoCitasDiarias);
        resultado.append("\nDías no hábiles: ").append(this.DNOLABORABLES);
        resultado.append("\nRango de fechas no laborables:\n\n..............................");
        Iterator<Map.Entry<String, IntervaloFechas>> recorreMap = this.PNOLABORABLES.entrySet().iterator();
        while (recorreMap.hasNext()) {
            resultado.append("\n").append(recorreMap.next());
        }
        return resultado.toString();
    }
}
