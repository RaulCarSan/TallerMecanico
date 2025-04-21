package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos implements IVehiculos {
    private static final String FICHEROS_VEHICULOS = String.format("%s%s%s","datos", File.separator,"vehiculos.xml");
    private static final String RAIZ = "vehiculos";
    private static final String VEHICULO = "vehiculo";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";
    private static Vehiculos instancia;
    List<Vehiculo> coleccionVehiculos = new ArrayList<>();

    public Vehiculos(){
        coleccionVehiculos = new ArrayList<>();
    }

    static Vehiculos getInstancia(){
        if (instancia == null){
            instancia = new Vehiculos();
        }
        return instancia;
    }

    private void procesarDocumentoXml(Document documentoXml) {
        NodeList vehiculos = documentoXml.getElementsByTagName(VEHICULO);
        for (int i = 0;i < vehiculos.getLength(); i++){
            Node vehiculo = vehiculos.item(i);
            if (vehiculo.getNodeType() == Node.ELEMENT_NODE){
                try {
                    insertar(getVehiculo((Element) vehiculo));
                } catch (TallerMecanicoExcepcion|IllegalArgumentException|NullPointerException e) {
                    System.out.printf("Error al procesar al procesar el cliente%s:  %s",i,e.getMessage());
                }
            }
        }
    }

    private Vehiculo getVehiculo(Element elemento) {
        String marca = (elemento).getAttribute(MARCA);
        String modelo = (elemento).getAttribute(MODELO);
        String matricula = (elemento).getAttribute(MATRICULA);
        return new Vehiculo(marca,modelo,matricula);

    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Vehiculo vehiculo : coleccionVehiculos){
                Element vehiculos = getElemento(documentoXml, vehiculo);
                documentoXml.getDocumentElement().appendChild(vehiculos);
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml,Vehiculo vehiculo){
        Element elementovehiculo = documentoXml.createElement(VEHICULO);
        elementovehiculo.setAttribute(MARCA,vehiculo.marca());
        elementovehiculo.setAttribute(MATRICULA, vehiculo.matricula());
        elementovehiculo.setAttribute(MODELO, vehiculo.modelo());
        return elementovehiculo;
    }

    @Override
    public List<Vehiculo> get(){
        return new ArrayList<>(coleccionVehiculos);
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"No se puede insertar un vehículo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)){
            coleccionVehiculos.add(vehiculo);
        }else {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo,"No se puede buscar un vehículo nulo.");
        if(coleccionVehiculos.contains(vehiculo)){
            int vehiculosIndex = coleccionVehiculos.indexOf(vehiculo);
            return coleccionVehiculos.get(vehiculosIndex);
        }else{
            return null;
        }

    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"No se puede borrar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)){
            coleccionVehiculos.remove(vehiculo);
        }else {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
    }

    @Override
    public void comenzar() {
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHEROS_VEHICULOS);
        if (documentoXml != null){
            procesarDocumentoXml(documentoXml);
            System.out.println("Ficheros vehiculos leído correctamente.");
        }
    }

    @Override
    public void terminar() {
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml,FICHEROS_VEHICULOS);
        System.out.println("Fichero vehiculos correctamente.");
    }

}
