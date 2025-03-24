package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    List<Trabajo> coleccionDeTrabajos = new ArrayList<>();

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionDeTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> trabajoCliente = new ArrayList<>();

        for (Trabajo trabajo : coleccionDeTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                trabajoCliente.add(trabajo);
            }
        }
        return trabajoCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : coleccionDeTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                trabajosVehiculo.add(trabajo);
            }
        }
        return trabajosVehiculo;
    }


    public Trabajo getColeccionDeTrabajos(Trabajo trabajo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo, "La revision no puede ser nula.");
        if (!coleccionDeTrabajos.contains(trabajo)){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        int index = 0;
        if (coleccionDeTrabajos.contains(trabajo)){
            index = coleccionDeTrabajos.indexOf(trabajo);
        }

        return coleccionDeTrabajos.get(index);
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede insertar una revisión nula.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionDeTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Objects.requireNonNull(fechaRevision, "La fecha de revisión no puede ser nula.");

        for (Trabajo trabajoExistente : coleccionDeTrabajos) {
            if (!trabajoExistente.estaCerrado()) {
                if (trabajoExistente.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (trabajoExistente.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            }

            if (trabajoExistente.estaCerrado()) {
                if (trabajoExistente.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajoExistente.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                }
                if (trabajoExistente.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajoExistente.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }

    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Trabajo trabajoAbiertoMismoVehiculo = null;
        boolean encontrado = false;
        Iterator<Trabajo> iterator = coleccionDeTrabajos.iterator();
        while (iterator.hasNext() && !encontrado) {
            Trabajo trabajo = iterator.next();
            boolean esMismoVehiculo = trabajo.getVehiculo().equals(vehiculo);
            boolean estaAbierto = trabajo.estaCerrado();
            if (estaAbierto && esMismoVehiculo) {
                trabajoAbiertoMismoVehiculo = trabajo;
                encontrado = true;
            }
        }
        if (trabajoAbiertoMismoVehiculo == null){
            throw new TallerMecanicoExcepcion("El trabajo no se encuentra en la lista.");
        }
        return trabajoAbiertoMismoVehiculo;
    }
    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo operar sobre una revisión nula.");
        Trabajo trabajo1;
        trabajo1 = getTrabajoAbierto(trabajo.getVehiculo());
        trabajo1.anadirHoras(horas);
        return trabajo;
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo operar sobre una revisión nula.");
        Trabajo trabajo1;
        trabajo1 = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajo1 instanceof Revision revision){
            throw new TallerMecanicoExcepcion("No se le puede añadir precio al material de una revision.");
        } else if (trabajo1 instanceof Mecanico mecanico){
            mecanico.anadirPrecioMaterial(precioMaterial);
        }
        return trabajo1;
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo operar sobre una revisión nula.");
        getTrabajoAbierto(trabajo.getVehiculo()).cerrar(fechaFin);
        return getColeccionDeTrabajos(trabajo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar una revisión nula.");
        if (coleccionDeTrabajos.contains(trabajo)) {
            int clientesIndex = coleccionDeTrabajos.indexOf(trabajo);
            return coleccionDeTrabajos.get(clientesIndex);
        } else {
            return null;
        }
    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede borrar una revisión nula.");
        if (coleccionDeTrabajos.contains(trabajo)) {
            coleccionDeTrabajos.remove(trabajo);
        } else {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
    }
}
