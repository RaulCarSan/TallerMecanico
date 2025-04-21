package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private static final String FICHERO_CLIENTES = String.format("%s%s%s", "datos", File.separator, "clientes.xml");
    private static final String RAIZ = "clientes";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";
    private static Clientes instancia;
    List<Cliente> coleccionClientes = new ArrayList<>();

    private Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    @Override
    public List<Cliente> get() {
        List<Cliente> nuevoCliente = coleccionClientes;
        return coleccionClientes;
    }

    static Clientes getInstancia() {
        if (instancia == null) {
            instancia = new Clientes();
        }
        return instancia;
    }

    private void procesarDocumentoXml(Document documentoXml) {
        NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);
        for (int i = 0; i < clientes.getLength(); i++) {
            Node cliente = clientes.item(i);
            if (cliente.getNodeType() == Node.ELEMENT_NODE) {
                try {
                    insertar(getCliente((Element) cliente));
                } catch (TallerMecanicoExcepcion | IllegalArgumentException | NullPointerException e) {
                    System.out.printf("Error al procesar el cliente %s:  %s%n", i, e.getMessage());
                }
            }
        }
    }

    private Cliente getCliente(Element elemento) {
        String nombre = (elemento).getAttribute(NOMBRE);
        String dni = (elemento).getAttribute(DNI);
        String telefono = (elemento).getAttribute(TELEFONO);
        return new Cliente(nombre, dni, telefono);

    }

    private Document crearDocumentoXml() {
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Cliente cliente : coleccionClientes) {
                Element clientes = getElemento(documentoXml, cliente);
                documentoXml.getDocumentElement().appendChild(clientes);
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Cliente cliente) {
        Element elementoCliente = documentoXml.createElement(CLIENTE);
        elementoCliente.setAttribute(DNI, cliente.getDni());
        elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
        elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
        return elementoCliente;
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (!coleccionClientes.contains(cliente)) {
            coleccionClientes.add(cliente);
        } else {
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");

        if (coleccionClientes.contains(cliente)) {

            if ((nombre != null) && !nombre.isBlank()) {
                buscar(cliente).setNombre(nombre);
            }

            if ((telefono != null) && !telefono.isBlank()) {
                buscar(cliente).setTelefono(telefono);
            }

            return buscar(cliente);
        }

        if (!coleccionClientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }

        return buscar(cliente);
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            int clientesIndex = coleccionClientes.indexOf(cliente);
            return coleccionClientes.get(clientesIndex);
        } else {
            return null;
        }
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            coleccionClientes.remove(cliente);
        } else {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
    }

    @Override
    public void comenzar() {
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_CLIENTES);
        if (documentoXml != null) {
            procesarDocumentoXml(documentoXml);
            System.out.println("Ficheros clientes leído correctamente.");
        }

    }

    @Override
    public void terminar() {
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_CLIENTES);
        System.out.println("Fichero clientes escrito correctamente.");

    }


}
