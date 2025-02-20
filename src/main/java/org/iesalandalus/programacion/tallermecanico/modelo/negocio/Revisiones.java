package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

import java.util.ArrayList;
import java.util.List;

public class Revisiones {

    List<Revision> coleccionDeRevisiones = new ArrayList<>();

    public Revisiones(){
        coleccionDeRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionDeRevisiones);
    }

    public List<Revisiones> get(Cliente cliente){

    }
}
