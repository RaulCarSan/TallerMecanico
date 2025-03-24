package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

        private Clientes clientes;
        private Vehiculos vehiculos;
        private Trabajos trabajos;

        public Modelo() {
            this.clientes = new ArrayList<>();
            this.vehiculos = new ArrayList<>();
            this.trabajos = new ArrayList<>();
        }


        public void insertar(Cliente cliente) {
            this.clientes.add(cliente);
        }

        public void insertar(Vehiculo vehiculo) {
            vehiculos.add(vehiculo);
        }

        public void insertar(Revision revision) {
            trabajos.add(revision);
        }

        public Cliente buscar(Cliente cliente) {
            return this.clientes.contains(cliente) ? cliente : null;
        }

        public Vehiculo buscar(Vehiculo vehiculo) {
            return vehiculos.contains(vehiculo) ? vehiculo : null;
        }

        public Revision buscar(Revision revision) {
            return trabajos.contains(revision) ? revision : null;
        }

        public Cliente modificar(Cliente cliente, String nombre, String telefono) {
            if (this.clientes.contains(cliente)) {
                cliente.setNombre(nombre);
                cliente.setTelefono(telefono);
                return cliente;
            }
            return null;
        }

        public Revision anadirHoras(Revision revision, int horas)throws TallerMecanicoExcepcion {
            if (trabajos.contains(revision)) {
                revision.anadirHoras(revision.getHoras() + horas);
                return revision;
            }
            return null;
        }

        public Revision anadirPrecioMaterial(Revision revision, float precioMaterial)throws TallerMecanicoExcepcion {
            if (trabajos.contains(revision)) {
                revision.anadirPrecioMaterial(revision.getPrecioMaterial() + precioMaterial);
                return revision;
            }
            return null;
        }

        public Revision cerrar(Revision revision, LocalDate fechaFin)throws TallerMecanicoExcepcion {
            if (trabajos.contains(revision)) {
                revision.cerrar(fechaFin);
                return revision;
            }
            return null;
        }

        public void borrar(Cliente cliente) {
            this.clientes.remove(cliente);
        }

        public void borrar(Vehiculo vehiculo) {
            vehiculos.remove(vehiculo);
        }

        public void borrar(Revision revision) {
            trabajos.remove(revision);
        }

        public List<Cliente> getCliente() {
            return new ArrayList<>(clientes);
        }

        public List<Vehiculo> getVehiculos() {
            return new ArrayList<>(vehiculos);
        }

        public List<Revision> getTrabajos() {
            return new ArrayList<>(trabajos);
        }

        public List<Revision> getRevisiones(Cliente cliente) {
            List<Revision> resultado = new ArrayList<>();
            for (Revision rev : trabajos) {
                if (rev.getCliente().equals(cliente)) {
                    resultado.add(rev);
                }
            }
            return resultado;
        }

        public List<Revision> getRevisiones(Vehiculo vehiculo) {
            List<Revision> resultado = new ArrayList<>();
            for (Revision rev : trabajos) {
                if (rev.getVehiculo().equals(vehiculo)) {
                    resultado.add(rev);
                }
            }
            return resultado;
        }
    }

