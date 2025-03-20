package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {

    List<Revision> coleccionDeRevisiones = new ArrayList<>();

    public Revisiones() {
        coleccionDeRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionDeRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();

        for (Revision revision : coleccionDeRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();

        for (Revision revision : coleccionDeRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        if (!comprobarRevision(revision)) {
            boolean tieneRevisionClienteEnCurso = false;
            boolean tieneRevisionClienteAnterior = false;
            boolean tieneRevisionVehiculoEnCurso = false;
            boolean tieneRevisionVehiculoAnterior = false;

            for (Revision existingRevision : coleccionDeRevisiones) {
                if (existingRevision.getCliente().equals(revision.getCliente())) {
                    if (!existingRevision.estaCerrado() ||
                            (existingRevision.estaCerrado() && existingRevision.getFechaFin() != null &&
                                    existingRevision.getFechaFin().isAfter(revision.getFechaInicio()))) {
                        tieneRevisionClienteEnCurso = true;
                    }
                    if (existingRevision.getFechaInicio().isBefore(revision.getFechaInicio())) {
                        tieneRevisionClienteAnterior = true;
                    }
                }
                if (existingRevision.getVehiculo().equals(revision.getVehiculo())) {
                    if (!existingRevision.estaCerrado() ||
                            (existingRevision.estaCerrado() && existingRevision.getFechaFin() != null &&
                                    existingRevision.getFechaFin().isAfter(revision.getFechaInicio()))) {
                        tieneRevisionVehiculoEnCurso = true;
                    }
                    if (existingRevision.getFechaFin() != null && existingRevision.getFechaFin().isAfter(revision.getFechaInicio())) {
                        tieneRevisionVehiculoAnterior = true;
                    }
                }
            }
            if (tieneRevisionClienteEnCurso) {
                throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
            }
            if (tieneRevisionVehiculoEnCurso) {
                throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
            }
            if (tieneRevisionClienteAnterior) {
                throw new TallerMecanicoExcepcion("El cliente ya tiene una revisión anterior.");
            }
            if (tieneRevisionVehiculoAnterior) {
                throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
            }
        }

        coleccionDeRevisiones.add(revision);
    }

    public boolean comprobarRevision(Revision revision) {
        LocalDate fechaInicioNuevaRevision = revision.getFechaInicio();
        boolean sePuedeRealizar = true;
        for (Revision revision1 : coleccionDeRevisiones) {
            boolean esMismoCliente = revision1.getCliente().equals(revision.getCliente());
            boolean esMismoVehiculo = revision1.getVehiculo().equals(revision.getVehiculo());

            if ((esMismoCliente || esMismoVehiculo) &&
                    (!revision1.estaCerrado() ||
                            (revision1.estaCerrado() && revision1.getFechaFin() != null && revision1.getFechaFin().isAfter(fechaInicioNuevaRevision)))) {
                sePuedeRealizar = false;
                break;
            }
        }

        return sePuedeRealizar;
    }

    public Revision getColeccionDeRevisiones(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        if (!coleccionDeRevisiones.contains(revision)){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        int index = 0;
        if (coleccionDeRevisiones.contains(revision)){
            index = coleccionDeRevisiones.indexOf(revision);
        }

        return coleccionDeRevisiones.get(index);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        getColeccionDeRevisiones(revision).anadirHoras(horas);

        return revision;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        getColeccionDeRevisiones(revision).anadirPrecioMaterial(precioMaterial);

        return revision;
    }

    public Revision cerrar(Revision revision,LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        getColeccionDeRevisiones(revision).cerrar(fechaFin);

        return getColeccionDeRevisiones(revision);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");
        if (coleccionDeRevisiones.contains(revision)){
            int clientesIndex = coleccionDeRevisiones.indexOf(revision);
            return coleccionDeRevisiones.get(clientesIndex);
        }else{
            return null;
        }
    }

    public void borrar(Revision revision)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No se puede borrar una revisión nula.");
        if (coleccionDeRevisiones.contains(revision)){
            coleccionDeRevisiones.remove(revision);
        }else {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
    }
}

