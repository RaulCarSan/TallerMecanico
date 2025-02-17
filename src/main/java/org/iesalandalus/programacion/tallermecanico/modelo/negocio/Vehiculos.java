package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {
    List<Vehiculo> coleccionVehiculos = new ArrayList<>();

    public Vehiculos(){
        coleccionVehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get(){
        return new ArrayList<>(coleccionVehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"No se puede insertar un vehículo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)){
            coleccionVehiculos.add(vehiculo);
        }else {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo,"No se puede buscar un vehículo nulo.");
        if(coleccionVehiculos.contains(vehiculo)){
            int vehiculosIndex = coleccionVehiculos.indexOf(vehiculo);
            return coleccionVehiculos.get(vehiculosIndex);
        }else{
            return null;
        }

    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"No se puede borrar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)){
            coleccionVehiculos.remove(vehiculo);
        }else {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
    }

}
