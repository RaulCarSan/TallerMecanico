package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(1,"Esta opción inserta un cliente."),
    BUSCAR_CLIENTES(2,"Esta opcion busca un cliente."),
    BORRAR_CLIENTES(3,"Esta opcion borra un cliente."),
    LISTAR_CLIENTES(4,"Esta opcion muestra una lista de los clientes."),
    MODIFICAR_CLIENTES(5,"Esta opcion modifica los datos de un cliente."),
    BUSCAR_VEHICULO(6,"Esta opcion busca un vehiculo."),
    BORRAR_VEHICULO(7,"Esta opcion borra un vehiculo."),
    LISTAR_VEHICULOS(8,"Esta opcion muestra una lista de los diferentes vehiculos."),
    INSERTAR_REVISION(9,"Esta opcion inserta los datos de una revisión."),
    BUSCAR_REVISION(10,"Esta opcion busca los datos de una revisión."),
    BORRAR_REVISION(11,"Esta opcion borra los datos de una revisión."),
    LISTAR_REVISIONES(12,"Esta opcion muestra una lista de las revisión."),
    LISTAR_REVISIONES_CLIENTE(13,"Esta opcion muestra los datos de un revisión relacionada con un cliente."),
    LISTAR_REVISIONES_VEHICULO(14,"Esta opcion muestra los datos de las revisiones asignadas a un vehiculo."),
    ANADIR_HORAS_REVISION(15,"Esta opcion añade horas a una revisión."),
    ANADIR_PRECIO_MATERIAL_REVISION(16,"Esta opcion añade costo a la revisión."),
    CERRAR_REVISION(17,"Esta opcion cierra una revisión."),
    SALIR(0,"Esta opción cierra el menu.");


    private static int numeroOpcion;
    private String mensaje;
    private static final Map<Integer,Opcion> opciones = new HashMap<>();

    Opcion(int numeroOpcion, String mensaje) {

    }

    static {

        for (Opcion opciones  : values()){

        }

    }
}
