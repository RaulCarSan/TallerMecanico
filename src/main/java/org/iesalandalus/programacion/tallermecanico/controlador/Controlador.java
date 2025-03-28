package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

public class Controlador implements IControlador {
    private Modelo modelo;
    public Controlador(Modelo modelo){
        this.modelo = new ModeloCascada(FabricaFuenteDatos.MEMORIA);
    }

    @Override
    public void comenzar(){
        modelo.comenzar();
    }

    @Override
    public void terminar(){
        modelo.terminar();
    }

    @Override
    public void actualizar(Evento evento){

    }
}
