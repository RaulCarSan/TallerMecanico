package org.iesalandalus.programacion.tallermecanico.vista.ventana;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores.VentanaPrincipal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {
    private GestorEventos gestorEventos = new GestorEventos(Evento.values());
    private VistaGrafica vistaGrafica;
    private static VistaGrafica instancia;

    public static VistaGrafica getInstancia() {
        if (instancia == null) {
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    private VistaGrafica(){}


    @Override
    public GestorEventos getGestorEventos(){
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        LanzadoraVistaGrafica.comenzar();
    }

    @Override
    public void terminar() {

    }

    @Override
    public Cliente leerCliente() {
        return null;
    }

    @Override
    public Cliente leerClienteDni() {
        return null;
    }

    @Override
    public String leerNuevoNombre() {
        return "";
    }

    @Override
    public String leerNuevoTelefono() {
        return "";
    }

    @Override
    public Vehiculo leerVehiculo() {
        return null;
    }

    @Override
    public Vehiculo leerMatriculaVehiculo() {
        return null;
    }

    @Override
    public Revision leerRevision() {
        return null;
    }

    @Override
    public Mecanico leerMecanico() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {

    }

    @Override
    public void mostarClientes(List<Cliente> clientes) {

    }

    @Override
    public void mostarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostarCliente(Cliente cliente) {

    }

    @Override
    public void mostarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }

}
