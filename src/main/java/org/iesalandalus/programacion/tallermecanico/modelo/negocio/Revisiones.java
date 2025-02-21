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
        Objects.requireNonNull(revision, "No se puede insertar una revision nula.");
        if (!comprobarRevision(revision)){
            throw new TallerMecanicoExcepcion("No se puede insertar una revision invalida.");
        }
        coleccionDeRevisiones.add(revision);
    }

    public boolean comprobarRevision(Revision nuevaRevision) {
        LocalDate fechaInicioNuevaRevision = nuevaRevision.getFechaInicio();
        boolean sePuedeRealizar = true;
        for (Revision revision : coleccionDeRevisiones) {
            boolean esMismoCliente = revision.getCliente().equals(nuevaRevision.getCliente());
            boolean esMismoVehiculo = revision.getVehiculo().equals(nuevaRevision.getVehiculo());
            if ((esMismoCliente || esMismoVehiculo) && (!revision.estaCerrada() || (revision.estaCerrada() && revision.getFechaFin() != null && revision.getFechaFin().isAfter(fechaInicioNuevaRevision)))) {
                sePuedeRealizar = false;
                break;
            }
        }

        return sePuedeRealizar;
    }

    public Revision getColeccionDeRevisiones(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        if (!coleccionDeRevisiones.contains(revision)){
            throw new TallerMecanicoExcepcion("La revision no se encuentra en la lista.");
        }
        int index = 0;
        if (coleccionDeRevisiones.contains(revision)){
            index = coleccionDeRevisiones.indexOf(revision);
        }

        return coleccionDeRevisiones.get(index);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        getColeccionDeRevisiones(revision).anadirHoras(horas);

        return revision;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        getColeccionDeRevisiones(revision).anadirPrecioMaterial(precioMaterial);

        return revision;
    }

    public Revision cerrar(Revision revision,LocalDate fechaFin) throws TallerMecanicoExcepcion{
        getColeccionDeRevisiones(revision).cerrar(fechaFin);

        return getColeccionDeRevisiones(revision);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision,"No se puede buscar un cliente nulo.");
        if (coleccionDeRevisiones.contains(revision)){
            int clientesIndex = coleccionDeRevisiones.indexOf(revision);
            return coleccionDeRevisiones.get(clientesIndex);
        }else{
            return null;
        }
    }

    public void borrar(Revision revision)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No se puede borrar un cliente nulo.");
        if (coleccionDeRevisiones.contains(revision)){
            coleccionDeRevisiones.remove(revision);
        }else {
            throw new TallerMecanicoExcepcion("No existe ningúna revision.");
        }
    }
}

