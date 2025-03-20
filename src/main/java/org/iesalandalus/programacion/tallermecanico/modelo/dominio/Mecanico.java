package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {
    private static final float FACTOR_HORA = 30;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;
    private float precioMaterial = 0 ;


    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente,vehiculo,fechaInicio);

    }

    @Override
    public float getPrecioEspecifico() {
        return (FACTOR_HORA * getHoras()) + (precioMaterial * FACTOR_PRECIO_MATERIAL);
    }

    public void anadirPrecioMaterial(float precioMaterial){
        precioMaterial = precioMaterial + getPrecioMaterial();
    }

    public float getPrecioMaterial(){
        return precioMaterial;
    }


    public Mecanico(Mecanico mecanico){
        super(mecanico.getCliente(),mecanico.getVehiculo(),mecanico.getFechaInicio());
        Objects.requireNonNull(mecanico,"La revisión no puede ser nula.");
    }

    @Override
    public String toString() {
        String fechaInicioStr = getFechaInicio().format(FORMATO_FECHA);
        String fechaFinStr = (getFechaFin() == null) ? " " : getFechaFin().format(FORMATO_FECHA);
        if (getFechaFin() == null){
            return String.format("%s - %s: (%s -%s), %s horas, %.2f € en material", getCliente(),getVehiculo() , fechaInicioStr, fechaFinStr, getHoras(), getPrecioMaterial());
        }else {
            return String.format("%s - %s: (%s - %s), %s horas, %.2f € en material, %.2f € total", getCliente(), getVehiculo(), fechaInicioStr, fechaFinStr, getHoras(), getPrecioMaterial(),getPrecio());
        }
    }
}
