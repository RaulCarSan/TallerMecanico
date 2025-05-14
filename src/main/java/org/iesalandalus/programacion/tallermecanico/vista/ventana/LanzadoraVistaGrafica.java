package org.iesalandalus.programacion.tallermecanico.vista.ventana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controladores;

import static javafx.application.Application.launch;

public class LanzadoraVistaGrafica extends Application {
    public static void comenzar(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VentanaPrincipal ventanaPrincipal= (VentanaPrincipal) Controladores.get("/vistas/VentanaPrincipal.fxml","Taller mecanico",null);
        ventanaPrincipal.getEscenario().show();
        ventanaPrincipal.centrar();
    }
}
