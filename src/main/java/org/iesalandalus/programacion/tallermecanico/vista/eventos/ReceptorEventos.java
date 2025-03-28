package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;

public interface ReceptorEventos extends IControlador {

    @Override
    void actualizar(Evento evento);
}
