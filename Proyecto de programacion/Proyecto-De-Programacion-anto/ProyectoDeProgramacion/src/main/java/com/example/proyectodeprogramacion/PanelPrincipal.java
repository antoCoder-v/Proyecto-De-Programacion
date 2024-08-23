package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class PanelPrincipal {
    @FXML
    private Button mostrarLed;

    @FXML
    private Button mostrarSwitch;

    @FXML
    private Button mostrarCable;
    
    @FXML
    private Button botonAgregaBateria;

    @FXML
    private Pane panelPrincipal;


    @FXML
    public void initialize() {
        mostrarLed.setOnAction(event -> cargarInterfacezElementos("led.fxml"));
    }

    // Método que carga la interfaces de los elemtentos de los botones 
    private void cargarInterfacezElementos(String nombre){
        try {
            // Cargamos el archivo nombre.fxml
            Parent elemento = FXMLLoader.load(getClass().getResource(nombre));
            //panelPrincipal.getChildren().clear();  //si limpiamos se borran los otros elementos
            panelPrincipal.getChildren().add(elemento); // Añadimos la interfaz del nombre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Controlamos la bateria que agregamos a la interfaz
    @FXML
    private void botonAgregaBateria(ActionEvent event) {
        cargarInterfacezElementos("Bateria.fxml");
        
    }

    @FXML
    private void botonAgregarProtoboard(ActionEvent event) {
        cargarInterfacezElementos("Protoboard.fxml");
    }
    
}
