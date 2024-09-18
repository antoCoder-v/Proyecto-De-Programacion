package com.example.proyectodeprogramacion;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControladorPantallaPrincipal {

    @FXML
    private Button mostrarLed;

    @FXML
    private Button mostrarSwitch;

    @FXML
    private Button agregarProtoboard;

    @FXML
    private Button botonAgregaBateria;

    @FXML
    private AnchorPane pantallaPrincipal;


    @FXML
    public void initialize() {

        // Inicializar la variable estática
        VariablesGlobales.pantallaPrincipal = pantallaPrincipal;
        VariablesGlobales.aparecioBateria = false;

        botonAgregaBateria.setOnAction(event -> cargarInterfacezElementos("bateria.fxml"));
        mostrarLed.setOnAction(event -> cargarInterfacezElementos("led.fxml"));
        mostrarSwitch.setOnAction(event -> cargarInterfacezElementos("switch.fxml"));
        agregarProtoboard.setOnAction(event -> cargarInterfacezElementos("protoboard.fxml"));

    }

    
    // Método que carga la interfaces de los elemtentos de los botones
    private void cargarInterfacezElementos(String nombre){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombre));
            // Carga el archivo FXML
            Parent elemento = loader.load();
            elemento.setLayoutX(47);
            elemento.setLayoutY(53);
            pantallaPrincipal.getChildren().add(elemento);

        } catch (IOException e) {
        
        }
    }
    
}
