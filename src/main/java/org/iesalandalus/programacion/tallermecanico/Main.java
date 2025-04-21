package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;

public class Main {
    public static void main(String[] args) {
        FabricaModelo modelo = FabricaModelo.CASCADA;
        FabricaVista vista = FabricaVista.TEXTO;
        FabricaFuenteDatos fuenteDatos = FabricaFuenteDatos.MEMORIA;
        Controlador controlador = new Controlador(modelo,fuenteDatos,vista);

        try {
            controlador.comenzar();
        } catch (TallerMecanicoExcepcion e) {
            System.out.println("ERROR: "+ e.getMessage());
        }
    }
}
