package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class  Trabajo {
    public static final float FACTOR_DIAS = 10;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Trabajo(Cliente cliente,Vehiculo vehiculo,LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        horas = 0;
        fechaFin = null;
    }

    public Trabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        this.cliente = new Cliente(trabajo.cliente);
        this.setVehiculo(trabajo.vehiculo);
        this.setFechaInicio(trabajo.fechaInicio);
        this.setFechaFin(trabajo.fechaFin);
        this.horas = trabajo.getHoras();
    }

    public static Trabajo copiar(Trabajo trabajo){
        Trabajo trabajoCopia = null;
        if (trabajo instanceof Revision){
            trabajoCopia = new Revision(trabajo.cliente,trabajo.vehiculo,trabajo.fechaInicio);
        }
        if (trabajo instanceof Mecanico){
            trabajoCopia = new Mecanico(trabajo.cliente,trabajo.vehiculo,trabajo.fechaInicio);
        }
        return trabajoCopia;
    }

    public static Trabajo get(Vehiculo vehiculo){
        Cliente cliente = new Cliente("Bob","77695990v","723727839");
        LocalDate fecha = LocalDate.now();
        return new Revision(cliente,vehiculo,fecha);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    protected void setFechaFin(LocalDate fechaFin) {
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    private float getDias() {
        if (fechaFin == null) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que el trabajo está cerrado.");
        }

        this.horas = horas + getHoras();
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }

        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }


        this.precioMaterial = precioMaterial + getPrecioMaterial();
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("El trabajo ya está cerrado.");
        }

        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        return (getPrecioFijo() + getPrecioEspecifico());
    }

    public boolean estaCerrado() {
        return (fechaFin != null);
    }

    public float getPrecioFijo(){
        float precio = 0;
        precio = (getDias() * FACTOR_DIAS);
        return precio;
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio,getHoras() ) && Objects.equals(cliente, getCliente()) && Objects.equals(vehiculo, getVehiculo());
    }

    @Override
    public int hashCode() {
        return Objects.hash( cliente, vehiculo,fechaInicio);
    }
}
