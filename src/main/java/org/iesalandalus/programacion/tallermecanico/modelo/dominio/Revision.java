package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Revision extends Trabajo {
   private static final float FACTOR_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente,vehiculo,fechaInicio);

    }

    @Override
    public float getPrecioEspecifico() {
        return FACTOR_HORA * getHoras();
    }

    public Revision(Revision revision){
        super(revision.getCliente(),revision.getVehiculo(),revision.getFechaInicio());
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
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
