package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class VentanaPrincipal extends Controlador {
    private static final Image BORRAR = new Image("/imagenes/borrar.jpg");
    private static final Image INSERTAR = new Image("/imagenes/insertar.png");
    private static final Image LISTAR = new Image("/imagenes/listar.jpg");

    @FXML
    private ImageView C1;

    @FXML
    private ImageView C2;

    @FXML
    private ImageView C3;

    @FXML
    private ImageView T1;

    @FXML
    private ImageView T2;

    @FXML
    private ImageView T3;

    @FXML
    private ImageView V1;

    @FXML
    private ImageView V2;

    @FXML
    private ImageView V3;


    @FXML
    void BTInsertarCliente(ActionEvent event) {

    }

    @FXML
    void BTInsertarVehiculos(ActionEvent event) {

    }

    @FXML
    void BTListarClientes(ActionEvent event) {

    }

    @FXML
    void BTborrarCliente(ActionEvent event) {

    }

    @FXML
    void BTborrarTrabajos(ActionEvent event) {

    }

    @FXML
    void BTborrarVehiculos(ActionEvent event) {

    }

    @FXML
    void BTinsertarTrabajos(ActionEvent event) {

    }

    @FXML
    void BTlistarTrabajos(ActionEvent event) {

    }

    @FXML
    void BTlistarVehiculos(ActionEvent event) {

    }

    @FXML
    void ayuda(ActionEvent event) {

    }

    @FXML
    void estadisticasMensuales(ActionEvent event) {

    }

    @FXML
    void informacionAdicional(ActionEvent event) {

    }


    @FXML
    void initialize() {
        C3.setImage(BORRAR);
        C2.setImage(INSERTAR);
        C1.setImage(LISTAR);
        T1.setImage(LISTAR);
        T2.setImage(INSERTAR);
        T3.setImage(BORRAR);
        V1.setImage(LISTAR);
        V2.setImage(INSERTAR);
        V3.setImage(BORRAR);
    }
}
