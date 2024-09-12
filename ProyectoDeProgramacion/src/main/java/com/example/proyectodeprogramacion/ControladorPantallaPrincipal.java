package com.example.proyectodeprogramacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.event.ActionEvent;
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
    private Button mostrarCable;

    @FXML
    private Button agregarProtoboard;

    @FXML
    private AnchorPane pantallaPrincipal;

    //variable para controlar si aparecio la bateria
    private boolean aparecioBateria = false;

    private Button botonPresionado;

    //Controladores de las clases
    ControladorBateria controladorBateria;
    ControladorProtoboard protoboard;

    public AnchorPane getPantallaPrincipal() {
        return pantallaPrincipal;
    }

    public Button getBotonPresionado() {
        return botonPresionado;
    }


    @FXML
    public void initialize() {
    }

    @FXML
    private void botonAgregaBateria(ActionEvent event) {

    }

    @FXML
    private void botonAgregaSwitch(ActionEvent event) {
        cargarInterfacezElementos("switch.fxml");
    }

    @FXML
    private void botonAgregaLed(ActionEvent event) {
        cargarInterfacezElementos("led.fxml");
    }

    @FXML
    private void agregarProtoboard(ActionEvent event) {
        System.out.println("ENTRA A BOTON PROTOBOARD------!\n\n\n");
        cargarInterfacezElementos("protoboard.fxml");
    }
    
    // Método que carga la interfaces de los elemtentos de los botones
    private void cargarInterfacezElementos(String nombre){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombre));
            // Carga el archivo FXML
            Parent elemento = loader.load();
            // Cargamos el archivo nombre.fxml
            if (nombre.equals("switch.fxml")) {
                ControladorSwitch switchController = loader.getController();
                //switchController.setProtoboard(this); // Pasar la instancia de Protoboard
            }else if(nombre.equals("protoboard.fxml")){ //NO SE PORQUE NO ENTRA ACA T-T
                System.out.println("ENTRA A PROTOBOARD Y PASA DATOS------!\n\n\n");
                ControladorProtoboard controlProtoboard = loader.getController();
                System.out.println(pantallaPrincipal); // Debería imprimir una referencia válida y no null
                controlProtoboard.setPantallaPrincipal(this);
            }
            elemento.setLayoutX(47);
            elemento.setLayoutY(53);
            pantallaPrincipal.getChildren().add(elemento);

        } catch (IOException e) {
             // Captura el mensaje de la excepción
            String errorMessage = e.getMessage();
            
            // Captura la pila de llamadas
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            
            // Imprime o registra la información
            System.err.println("Error: " + errorMessage);
            System.err.println("Stack Trace: " + stackTrace);
        }
    }
    
}
