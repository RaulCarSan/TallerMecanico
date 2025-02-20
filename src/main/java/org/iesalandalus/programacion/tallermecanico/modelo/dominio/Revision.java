package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd");
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
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        this.cliente = new Cliente(revision.cliente);
        this.setVehiculo(revision.vehiculo);
        this.setFechaInicio(revision.fechaInicio);
        this.setFechaFin(revision.fechaFin);
        this.horas = revision.getHoras();
        this.precioMaterial = revision.precioMaterial;

    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio){
        Objects.requireNonNull(fechaInicio,"La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    private void setFechaFin(LocalDate fechaFin){
        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }

        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
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
        if(fechaFin == null){
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(fechaInicio,fechaFin);
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion{
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }

        this.horas = horas + getHoras();
    }

    public void anadirPrecioMaterial(float precioMaterial)throws TallerMecanicoExcepcion{
        if (precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }

        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }


        this.precioMaterial = precioMaterial + getPrecioMaterial();
    }

    public void cerrar(LocalDate fechaFin)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");
        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }

        setFechaFin(fechaFin);
    }

    public float getPrecio(){
      float precio = 0;
      precio = ((getPrecioMaterial() * PRECIO_MATERIAL) + (getHoras() * PRECIO_HORA) + (getDias() * PRECIO_DIA));
      return  precio;
    }

    public boolean estaCerrada(){
        return (fechaFin != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        String fechaInicioStr = fechaInicio.format(FORMATO_FECHA);
        String fechaFinStr = (fechaFin == null) ? " " : fechaFin.format(FORMATO_FECHA);
        if (fechaFin == null){
            return String.format("%s - %s: (%s -%s), %s horas, %.2f € en material", cliente, vehiculo, fechaInicioStr, fechaFinStr, horas, precioMaterial);
        }else {
            return String.format("%s - %s: (%s - %s), %s horas, %.2f € en material, %.2f € total", cliente, vehiculo, fechaInicioStr, fechaFinStr, horas, precioMaterial,getPrecio());
        }
    }
}
