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

    //creacion de las otras clases para no perder referencias
    private Cables cableManager;
    private ControladorBateria controladorBateria;
    private ControladorProtoboard controladorProtoboard;

    //funciones para recibir las referencias de las otras clases
    public void setControladorBateria(ControladorBateria bateria) {
        this.controladorBateria = bateria;
    }

    public void setControladorProtoboard(ControladorProtoboard protoboard) {
        this.controladorProtoboard = protoboard;
    }

    public void setCableManager(Cables cableManager) {
        this.cableManager = cableManager;
    }

    public AnchorPane getPantallaPrincipal() {
        return pantallaPrincipal;
    }

    public Button getBotonPresionado() {
        return botonPresionado;
    }


    @FXML
    public void initialize() {
        // Inicializar la variable estática
        VairablesGlobales.pantallaPrincipal = pantallaPrincipal;

        // Verifica si pantallaPrincipal es null
        if (pantallaPrincipal == null) {
            System.err.println("pantallaPrincipal es null en initialize");
        } else {
            System.out.println("pantallaPrincipal no es null en initialize");
        }
    }

    @FXML
    private void botonAgregaBateria(ActionEvent event) {
        try {
            //Cargamos el fxml manual, para que no se pierdan los datos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bateria.fxml"));
            Parent root = loader.load();
            root.setLayoutX(47);
            root.setLayoutY(53);

            // Obtén el controlador de la batería
            ControladorBateria controladorBateria = loader.getController();

            // Establece la referencia en ControladorPantallaPrincipal
            this.setControladorBateria(controladorBateria);
            pantallaPrincipal.getChildren().add(root);

            aparecioBateria = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            // Cargamos el fxml manual, para que no se pierdan los datos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("protoboard.fxml"));
            Parent root = loader.load();
            root.setLayoutX(47);
            root.setLayoutY(53);

            // Obtén el controlador de la protoboard
            ControladorProtoboard controladorProtoboard = loader.getController();

            // Establece la referencia en ControladorPantallaPrincipal
            controladorProtoboard.setPantalla(pantallaPrincipal);
            this.setControladorProtoboard(controladorProtoboard);
            //cableManager = new Cables();
            

            // Verifica si pantalla en ControladorProtoboard es null
            if (controladorProtoboard.getPantalla() == null) {
                System.err.println("pantalla es null después de llamar a setPantalla");
            } else {
                System.out.println("pantalla no es null después de llamar a setPantalla");
            }

            pantallaPrincipal.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
