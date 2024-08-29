package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Bateria{
    @FXML
    Rectangle rectanguloParaMover;

    @FXML
    Rectangle rec1;

    @FXML
    Rectangle rec2;

    @FXML
    Rectangle rec3;

    @FXML
    Rectangle rec4;

    @FXML
    Rectangle rec5;

    @FXML
    Text sim1;

    @FXML
    Text sim2;

    @FXML
    Text text;

    @FXML
    Button botonCargaNegativa;

    @FXML
    Button botonCargaPositiva;

    @FXML
    Line cablecitoNegativo;

    @FXML
    Line cablecitoPositivo;

    @FXML
    Pane paneBateria;


    boolean movimiento = false;
    boolean movimientoC = false;




    @FXML
    public void initialize() {
        inicializarControladoresEventos();
        cablecitoNegativo = new Line();
        cablecitoPositivo = new Line();
    }

    private void inicializarControladoresEventos() {
        // Variables para almacenar la posición inicial del mouse
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        // Detectar clic para mover la bateria
        rectanguloParaMover.setOnMousePressed(e -> {
            movimiento = true;
            offsetX[0] = e.getX() - rectanguloParaMover.getX();
            offsetY[0] = e.getY() - rectanguloParaMover.getY();
        });

        // Mover la bateria segun el arrastre del ratón
        rectanguloParaMover.setOnMouseDragged(e -> {
            if (movimiento) {
                rectanguloParaMover.setX(e.getX() - offsetX[0]);
                rectanguloParaMover.setY(e.getY() - offsetY[0]);
                rec1.setX(e.getX() - offsetX[0]);
                rec1.setY(e.getY() - offsetY[0]);
                rec2.setX(e.getX() - offsetX[0]);
                rec2.setY(e.getY() - offsetY[0]);
                rec3.setX(e.getX() - offsetX[0]);
                rec3.setY(e.getY() - offsetY[0]);
                rec4.setX(e.getX() - offsetX[0]);
                rec4.setY(e.getY() - offsetY[0]);
                rec5.setX(e.getX() - offsetX[0]);
                rec5.setY(e.getY() - offsetY[0]);
                sim1.setX(e.getX() - offsetX[0]);
                sim1.setY(e.getY() - offsetY[0]);
                sim2.setX(e.getX() - offsetX[0]);
                sim2.setY(e.getY() - offsetY[0]);
                text.setX(e.getX() - offsetX[0]);
                text.setY(e.getY() - offsetY[0]);
                botonCargaNegativa.setLayoutX(e.getX() - offsetX[0] + 45);
                botonCargaNegativa.setLayoutY(e.getY() - offsetY[0] + 105);

                botonCargaPositiva.setLayoutX(e.getX() - offsetX[0] + 155);
                botonCargaPositiva.setLayoutY(e.getY() - offsetY[0] + 105);

                cablecitoNegativo.setStartX(e.getX() - offsetX[0] + 45);
                cablecitoNegativo.setStartY(e.getY() - offsetY[0] + 105);

                cablecitoPositivo.setStartX(e.getX() - offsetX[0] + 155);
                cablecitoPositivo.setStartY(e.getY() - offsetY[0] + 105);



            }
        });

        // Reiniciar las banderas cuando se suelte el ratón
        rectanguloParaMover.setOnMouseReleased(e -> {
            movimiento = false;
        });

    }


    @FXML
    public void botonCargaNegativa (ActionEvent event){
        mostrarMensaje("Ha seleccionado la carga negativa, conecte el cable hacia algun artefacto.");
        botonCargaNegativa.setStyle("-fx-background-color: #ff0000");
        paneBateria.getChildren().add(cablecitoNegativo);
        cablecitoNegativo.setStrokeWidth(10);
        int x = (int) botonCargaNegativa.getLayoutX();
        int y = (int) botonCargaNegativa.getLayoutY();
        cablecitoNegativo.setStartX(x);
        cablecitoNegativo.setStartY(y);

        // Agrega el manejador de eventos para mover el otro extremo de la línea con el mouse
        cablecitoNegativo.setOnMouseMoved(e -> {
            if (estaCerca(cablecitoNegativo.getEndX(), cablecitoNegativo.getEndY(), e.getX(), e.getY())) {
                movimientoC = true;
            }
        });

        // Mover el extremo inicial o final según el arrastre del ratón
        cablecitoNegativo.setOnMouseDragged(e -> {
            if (movimientoC) {
                cablecitoNegativo.setEndX(e.getX());
                cablecitoNegativo.setEndY(e.getY());
            }
        });

        // Reiniciar las banderas cuando se suelte el ratón
        cablecitoNegativo.setOnMouseReleased(e -> {
            movimientoC = false;
        });
    }

    @FXML
    public void botonCargaPositiva (ActionEvent event){
        mostrarMensaje("Ha seleccionado la carga positiva, conecte el cable hacia algun artefacto.");
        //cambiamos el color del botonCargaPositiva a azul
        botonCargaPositiva.setStyle("-fx-background-color: #0000ff");

        paneBateria.getChildren().add(cablecitoPositivo);
        cablecitoPositivo.setStrokeWidth(10);
        int x = (int) botonCargaPositiva.getLayoutX();
        int y = (int) botonCargaPositiva.getLayoutY();
        cablecitoPositivo.setStartX(x);
        cablecitoPositivo.setStartY(y);

        // Agrega el manejador de eventos para mover el otro extremo de la línea con el mouse
        cablecitoPositivo.setOnMouseMoved(e -> {
            if (estaCerca(cablecitoPositivo.getEndX(), cablecitoPositivo.getEndY(), e.getX(), e.getY())) {
                movimientoC = true;
            }
        });

        // Mover el extremo inicial o final según el arrastre del ratón
        cablecitoPositivo.setOnMouseDragged(e -> {
            if (movimientoC) {
                cablecitoPositivo.setEndX(e.getX());
                cablecitoPositivo.setEndY(e.getY());
            }
        });

        // Reiniciar las banderas cuando se suelte el ratón
        cablecitoPositivo.setOnMouseReleased(e -> {
            movimientoC = false;
        });

    }


    // Método para mostrar una alerta de advertencia
    private void mostrarMensaje(String contenido) {
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle("Atencion");
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    // Método auxiliar para detectar si el clic está cerca de un punto
    private boolean estaCerca(double x1, double y1, double x2, double y2) {
        return Math.hypot(x1 - x2, y1 - y2) < 10;
    }

}