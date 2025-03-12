package org.iesalandalus.programacion.tallermecanico.vista;

import com.sun.source.tree.BreakTree;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

    public static void mostrarCabecera(String mensaje){
        System.out.printf("%n%s%n", mensaje);
        System.out.printf("-".repeat(mensaje.length()).concat("%n%n"));
    }

    public static void mostrarMenu(){
        mostrarCabecera("Gestión de un taller mecánico");
        for (Opcion opcion : Opcion.values()){
            System.out.print(opcion);
        }
    }

    public static Opcion elegirOpcion(){
        Opcion opcion = null;
        do {
            try {
                opcion = opcion.get(leerEntero("\n Elige una opción"));

            }catch (IllegalArgumentException e){
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
        }while (opcion == null);
        return opcion;
    }

    public static int leerEntero(String mensaje){
        System.out.printf(mensaje);
        return Entrada.entero();
    }

    public static float leerReal(String mensaje){
        System.out.printf(mensaje);
        return Entrada.real();
    }

    public static String leerCadena(String mensaje){
        System.out.printf(mensaje);
        return Entrada.cadena();
    }

    public static LocalDate leerFecha(String mensaje){
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        mensaje = String.format("%s%n: ", mensaje, CADENA_FORMATO_FECHA);
        try {
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);
        }catch (DateTimeException e){
            fecha = null;
        }
        return fecha;
    }

    public static Cliente leerCliente(){
        String nombre = leerCadena("Introduce el nombre del cliente.");
        String dni = leerCadena("Introduce el dni del cliente.");
        String telefono = leerCadena("Introduce el telefono del cliente.");
        return new Cliente(nombre,dni,telefono);
    }

    public static Cliente leerClienteDni(){
        return Cliente.get(leerCadena("Introduce el dni del cliente."));
    }

    public static String leerNuevoNombre(){
        return leerCadena("Introduce un nuevo nombre.");
    }

    public static String leerNuevoTelefono(){
        return leerCadena("Introduce un nuevo telefono.");
    }

    public static Vehiculo leerVehiculo(){
        String marca = leerCadena("Introduce la marca del vehiculo.");
        String modelo = leerCadena("Introduce el modelo del vehiculo.");
        String matricula = leerCadena("Introduce la matricula del vehiculo.");
        return new Vehiculo(marca,modelo,matricula);
    }

    public static Vehiculo leerMatriculaVehiculo(){
        return Vehiculo.get(leerCadena("Introduce la matricula del vehiculo."));
    }

    public static Revision leerRevision() {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculo();
        LocalDate fechaInicio = leerFecha("Introduce la fecha.");

        return new Revision(cliente,vehiculo,fechaInicio);
    }


}
