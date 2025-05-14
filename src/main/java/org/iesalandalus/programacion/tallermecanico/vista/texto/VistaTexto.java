package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.iesalandalus.programacion.tallermecanico.vista.texto.Consola.*;

public class VistaTexto implements Vista {
    private GestorEventos gestorEventos;

    @Override
    public GestorEventos getGestorEventos(){
        return gestorEventos;
    }

    public VistaTexto(){
        gestorEventos = new GestorEventos(Evento.values());
    }

    @Override
    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
    }

    @Override
    public void terminar(){
        System.out.println("Me doy el piro, vampiro!");
    }

    private void ejecutar(Evento evento)  {
        Consola.mostrarCabecera(evento.toString());
        getGestorEventos().notificar(evento);
    }

    @Override
    public Cliente leerCliente(){
        String nombre = leerCadena("Introduce el nombre del cliente.");
        String dni = leerCadena("Introduce el dni del cliente.");
        String telefono = leerCadena("Introduce el telefono del cliente.");
        return new Cliente(nombre,dni,telefono);
    }

    @Override
    public Cliente leerClienteDni(){
        return Cliente.get(leerCadena("Introduce el dni del cliente."));
    }

    @Override
    public String leerNuevoNombre(){
        return leerCadena("Introduce un nuevo nombre.");
    }

    @Override
    public String leerNuevoTelefono(){
        return leerCadena("Introduce un nuevo telefono.");
    }

    @Override
    public Vehiculo leerVehiculo(){
        String marca = leerCadena("Introduce la marca del vehiculo.");
        String modelo = leerCadena("Introduce el modelo del vehiculo.");
        String matricula = leerCadena("Introduce la matricula del vehiculo.");
        return new Vehiculo(marca,modelo,matricula);
    }

    @Override
    public Vehiculo leerMatriculaVehiculo(){
        return Vehiculo.get(leerCadena("Introduce la matricula del vehiculo."));
    }

    @Override
    public Revision leerRevision() {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculo();
        LocalDate fechaInicio = leerFecha("Introduce la fecha.");

        return new Revision(cliente,vehiculo,fechaInicio);
    }

    @Override
    public Mecanico leerMecanico() {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculo();
        LocalDate fechaInicio = leerFecha("Introduce la fecha.");

        return new Mecanico(cliente,vehiculo,fechaInicio);
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        Vehiculo vehiculo = leerVehiculo();
        return Trabajo.copiar(Trabajo.get(vehiculo));
    }

    @Override
    public int leerHoras(){
        return Consola.leerEntero("Introduce las horas.");
    }

    @Override
    public float leerPrecioMaterial(){
        return Consola.leerReal("Introduce el precio del material.");
    }

    @Override
    public LocalDate leerFechaCierre(){
        return Consola.leerFecha("Introduzca la fecha de cierre.");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        if (exito){
            System.out.println(texto);
        }else {
            System.out.printf("ERROR: %s%n",texto);
        }
    }

    @Override
    public void mostarClientes(List<Cliente> clientes){
        Objects.requireNonNull(clientes,"Los clientes no pueden se nulos.");
        Consola.mostrarCabecera("Listado de clientes");
        clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }
        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    @Override
    public void mostarVehiculos(List<Vehiculo> vehiculos){
        Objects.requireNonNull(vehiculos,"Los vehiculos no pueden se nulos.");
        Consola.mostrarCabecera("Listado de vehículos");
        vehiculos.sort(Comparator.comparing(Vehiculo::marca).thenComparing(Vehiculo::modelo).thenComparing(Vehiculo::matricula));
        if (!vehiculos.isEmpty()){
            for (Vehiculo vehiculo : vehiculos){
                System.out.println(vehiculo);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    @Override
    public void mostarTrabajos(List<Trabajo> trabajos){
        Objects.requireNonNull(trabajos,"Los trabajos no pueden se nulos.");
        Consola.mostrarCabecera("Listado de trabajos");
        Comparator<Cliente> comparadorClientes = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
        trabajos.sort(Comparator.comparing(Trabajo::getFechaInicio).thenComparing(Trabajo::getCliente,comparadorClientes));
        if (!trabajos.isEmpty()){
            for (Trabajo trabajo : trabajos){
                System.out.println(trabajo);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    @Override
    public void mostarTrabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        System.out.println(trabajo);
    }

    @Override
    public void mostarVehiculo(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        System.out.println(vehiculo);
    }

    @Override
    public void mostarCliente(Cliente cliente){
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        System.out.println(cliente);
    }

    public LocalDate leerMes(){
        return leerFecha("Pon la fecha.");
    }

    public void mostarEstadisticasMensuales(Map<TipoTrabajo,Integer> estadisticas){
        Objects.requireNonNull(estadisticas,"Las estadisticas no pueden ser nulas.");
        System.out.println(estadisticas);
    }


}
