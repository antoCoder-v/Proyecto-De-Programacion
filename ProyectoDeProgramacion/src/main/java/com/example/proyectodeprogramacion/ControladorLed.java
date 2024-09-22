package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControladorLed {

    @FXML
    private Circle patita1;  // Vinculado con fx:id="patita1"

    @FXML
    private Circle patita2;  // Vinculado con fx:id="patita2"

    @FXML
    private Pane ledPane;    // Contenedor del LED y las patitas

    private double offsetX;  // Diferencia en el eje X durante el arrastre
    private double offsetY;  // Diferencia en el eje Y durante el arrastre

    private boolean conectadoProtoboard = false;

    // Inicialización del controlador
    @FXML
    public void initialize() {

        EliminarElementos.habilitarEliminacion(ledPane);

        // Inicialización de las patitas (puedes personalizar los estilos o comportamientos)
        patita1.setFill(javafx.scene.paint.Color.DODGERBLUE);
        patita2.setFill(javafx.scene.paint.Color.DODGERBLUE);

        ledPane.setOnMousePressed(this::onMousePressed);
        ledPane.setOnMouseDragged(this::onMouseDragged);

        // Manejo de la interacción de la patita1
        patita1.setOnMouseClicked(event -> {
            if (conectadoProtoboard) {
                desconectarDeProtoboard("patita1");
            } else {
                conectarAProtoboard("patita1");
            }
        });

        // Manejo de la interacción de la patita2
        patita2.setOnMouseClicked(event -> {
            if (conectadoProtoboard) {
                desconectarDeProtoboard("patita2");
            } else {
                conectarAProtoboard("patita2");
            }
        });
    }

    // Método para capturar el punto inicial de arrastre
    private void onMousePressed(MouseEvent event) {
        offsetX = event.getSceneX() - ledPane.getLayoutX();
        offsetY = event.getSceneY() - ledPane.getLayoutY();
    }

    // Método para mover el LED mientras se arrastra
    private void onMouseDragged(MouseEvent event) {
        ledPane.setLayoutX(event.getSceneX() - offsetX);
        ledPane.setLayoutY(event.getSceneY() - offsetY);
    }

    // Método para conectar una patita a la protoboard
    private void conectarAProtoboard(String patita) {
        conectadoProtoboard = true;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Conexión exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El LED ha sido conectado a la protoboard desde " + patita);
        alert.showAndWait();

        // Cambia el color para indicar conexión
        if (patita.equals("patita1")) {
            patita1.setFill(Color.GREEN);
        } else if (patita.equals("patita2")) {
            patita2.setFill(Color.GREEN);
        }
    }

    // Método para desconectar una patita de la protoboard
    private void desconectarDeProtoboard(String patita) {
        conectadoProtoboard = false;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Desconexión");
        alert.setHeaderText(null);
        alert.setContentText("El LED ha sido desconectado de la protoboard desde " + patita);
        alert.showAndWait();

        // Cambia el color para indicar desconexión
        if (patita.equals("patita1")) {
            patita1.setFill(Color.DODGERBLUE);
        } else if (patita.equals("patita2")) {
            patita2.setFill(Color.DODGERBLUE);
        }
    }
}