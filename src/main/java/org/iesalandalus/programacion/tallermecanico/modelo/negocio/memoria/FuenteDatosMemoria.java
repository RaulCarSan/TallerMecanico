package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatosMemoria;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

public class FuenteDatosMemoria implements IFuenteDatosMemoria {
    @Override
    public IClientes crearIClientes(){
        return new Clientes();
    }
    @Override
    public ITrabajos crearITrabajos(){
        return new Trabajos();
    }
    @Override
    public IVehiculos crearIVehiculos(){
        return new Vehiculos();
    }
}
