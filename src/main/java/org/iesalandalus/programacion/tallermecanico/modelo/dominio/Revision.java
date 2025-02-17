package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyyy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        horas = 0;
        precioMaterial = 0;
        fechaFin = null;
    }

    public Revision(Revision revision){
        Objects.requireNonNull(revision,"La revision no puede ser nula.");
        this.cliente = new Cliente(cliente);
        this.setVehiculo(revision.vehiculo);
        this.setFechaInicio(revision.fechaInicio);


    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(vehiculo,"El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio){
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no pude ser posterior a hoy. ");
        }
        this.fechaInicio = fechaInicio;
    }

    private void setFechaFin(LocalDate fechaFin){
        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha no puede ser posterior a hoy.");
        }

        if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio)){
            throw new IllegalArgumentException("No puede ser posterior");
        }

        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaFin(){
        return fechaFin;
    }

    public int getHoras(){
        return horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    private float getDias(){
        return ChronoUnit.DAYS.between(fechaInicio,fechaFin);
    }

    public void añadirHoras(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("las horas no pueden ser 0 ni menor que 0.");
        }

        this.horas = horas + getHoras();
    }

    public void añadirPrecioMaterial(float precioMaterial){
        if (precioMaterial <= 0){
            throw new IllegalArgumentException("El precio no puede ser menor que 0.");
        }

        this.precioMaterial = precioMaterial + getPrecioMaterial();
    }

    public void cerrar(LocalDate fechaFin){
        setFechaFin(fechaFin);
    }

    public float getPrecio(){
      float precio = 0;
      precio = ((getPrecioMaterial() * PRECIO_MATERIAL) + (getHoras() * PRECIO_HORA) + (getDias() * PRECIO_DIA));
      return  precio;
    }


}
